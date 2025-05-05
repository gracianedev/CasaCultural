// Lista filmes
function loadFilms() {
    $.getJSON('/api/filme', function (filmes) {
        const $body = $('#tabelaFilmes').empty();
        filmes.forEach(f => {
            $body.append(`
        <tr>
          <td>${f.id}</td>
          <td>${f.titulo}</td>
          <td>${f.genero}</td>
          <td>${f.ano}</td>
          <td>
            <button class="btn-opcao btn-vermais" data-id="${f.id}"> <i class="bi bi-eye"></i> Ver mais</button>
            <button class="btn-opcao btn-avaliar" data-id="${f.id}"> <i class="bi bi-star"></i> Avaliar</button>
            <button class="btn-opcao btn-editar" data-id="${f.id}">  <i class="bi bi-pencil-square"></i> Editar</button>
            <button class="btn-opcao btn-excluir" data-id="${f.id}"> <i class="bi bi-trash"></i> Excluir</button>
          </td>
        </tr>`);
        });
    });
}

$(document).ready(function () {
    loadFilms();

    // Ver mais
    $('#tabelaFilmes').on('click', '.btn-vermais', function () {
        const id = $(this).data('id');
        window.location.href = '/filme/detalhe/' + id;
    });

    // Avaliar
    $('#tabelaFilmes').on('click', '.btn-avaliar', function () {
        const id = $(this).data('id');
        window.location.href = '/filme/analise/' + id;
    });

    // Editar (modal)
    $('#tabelaFilmes').on('click', '.btn-editar', function () {
        const id = $(this).data('id');

        // Buscar informações do filme para preencher o modal
        $.getJSON(`/api/filme/${id}`, function (filme) {
            $('#editTitulo').val(filme.titulo);
            $('#editGenero').val(filme.genero);
            $('#editAno').val(filme.ano);
            $('#editSinopse').val(filme.sinopse);

            // Abre o modal
            $('#editModal').modal('show');

            // Quando clicar em salvar alterações
            $('#saveEdit').off('click').on('click', function () {
                const updatedFilme = {
                    id: id,
                    titulo: $('#editTitulo').val(),
                    genero: $('#editGenero').val(),
                    ano: parseInt($('#editAno').val()),
                    sinopse: $('#editSinopse').val()
                };

                $.ajax({
                    url: `/api/filme/${id}`,
                    method: 'PUT',
                    contentType: 'application/json',
                    data: JSON.stringify(updatedFilme)
                }).done(function () {
                    $('#editModal').modal('hide');
                    loadFilms();
                }).fail(function () {
                    alert('Erro ao atualizar filme');
                });
            });
        }).fail(function () {
            alert('Erro ao carregar filme para edição');
        });
    });


    // Excluir
    $('#tabelaFilmes').on('click', '.btn-excluir', function () {
        const id = $(this).data('id');
        if (confirm('Deseja realmente excluir este filme?')) {
            $.ajax({
                url: `/api/filme/${id}`,
                method: 'DELETE'
            }).done(loadFilms)
                    .fail(() => alert('Erro ao excluir filme'));
        }
    });

    // Cadastrar novo filme
    $('#formAddFilme').on('submit', function (e) {
        e.preventDefault();

        const filme = {
            titulo: $('#titulo').val(),
            sinopse: $('#sinopse').val(),
            genero: $('#genero').val(),
            ano: $('#ano').val()
        };

        $.ajax({
            url: '/filmes',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(filme)
        }).done(function () {
            alert('Filme cadastrado com sucesso!');
            $('#formAddFilme')[0].reset();
            loadFilms();
        }).fail(function (xhr) {
            alert('Erro ao cadastrar filme: ' + xhr.responseText);
        });
    });

    // Página Analise
    if ($('#formAvaliacao').length) {

        var pathArray = window.location.pathname.split('/');
        var filmeId = pathArray[pathArray.length - 1];

        $('#formAvaliacao').on('submit', function (e) {
            e.preventDefault();

            const notaSelecionada = $('input[name="nota"]:checked').val();

            if (!notaSelecionada) {
                alert('Por favor, selecione uma nota de 1 a 5.');
                return;
            }

            const nota = parseInt(notaSelecionada);

            if (nota < 1 || nota > 5) {
                alert('Nota inválida. Selecione uma nota entre 1 e 5.');
                return;
            }

            const avaliacao = {
                filmeId: filmeId,
                textoAnalise: $('#analiseFilme').val(),
                nota: nota
            };

            $.ajax({
                url: `/api/analise/filme/${filmeId}`,
                method: 'POST',
                contentType: 'application/json',
                data: JSON.stringify(avaliacao)
            }).done(function () {
                alert('Avaliação enviada com sucesso!');
                window.location.href = "/filme/lista";
            }).fail(function () {
                alert('Erro ao enviar avaliação');
            });
        });
    }



    // Página detalhes
    if ($('#filmDetails').length) {
        const pathParts = window.location.pathname.split('/');
        const filmeId = pathParts[pathParts.length - 1];

        $.getJSON(`/api/filme/${filmeId}`, function (filme) {
            $('#filmDetails').html(`
      <h2 class="text-center mb-0">
        <span class="text-dark">Detalhes do filme</span>
        <strong class="text-primary fs-3">${filme.titulo}</strong>
      </h2>
      <p><strong>Gênero:</strong> ${filme.genero}</p>
      <p><strong>Ano de lançamento:</strong> ${filme.ano}</p>
      <p><strong>Sinopse:</strong> ${filme.sinopse}</p>
      <hr/>
      <p><strong>Número de avaliações:</strong> ${filme.analise.length}</p>
      <div id="comments">
        ${filme.analise.map(a => `
          <div class="mb-3 p-3 border rounded">
            <p>${a.textoAnalise}</p>
            <p><strong>Nota:</strong>
              ${'<i class="bi bi-star-fill star-filled "></i>'.repeat(a.nota)}
              ${'<i class="bi bi-star text-secondary"></i>'.repeat(5 - a.nota)}
            </p>
          </div>
        `).join('')}
      </div>
    `);
        }).fail(() => alert('Erro ao carregar detalhes do filme'));
    }

});
