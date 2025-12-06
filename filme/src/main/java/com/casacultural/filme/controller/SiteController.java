/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.casacultural.filme.controller;

import com.casacultural.filme.model.Preferencia;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author GFS_Mac
 */
@Controller
public class SiteController {

    @RequestMapping("/preferencias")

    public String preferencias(@CookieValue(name = "pref-estilo", defaultValue = "style_claro") String tema,
            Model model) {
        model.addAttribute("css", tema);
        return "preferencias";
    }

    @PostMapping("/preferencias")
    public ModelAndView gravaPreferencias(@ModelAttribute Preferencia pref, HttpServletResponse response) {

        Cookie cookiePrefEstilo = new Cookie("pref-estilo", pref.getEstilo());
        cookiePrefEstilo.setDomain("localhost"); //disponível apenas no domínio "localhost" 

        cookiePrefEstilo.setHttpOnly(true); //acessível apenas por HTTP, JS não 

        cookiePrefEstilo.setMaxAge(86400); //1 dia 

        response.addCookie(cookiePrefEstilo);
        return new ModelAndView("redirect:/"); //"index"; 
    }

    @RequestMapping("/")
    public String index(@CookieValue(name = "pref-estilo", defaultValue = "style_claro") String tema,
            Model model) {
        model.addAttribute("css", tema);
        return "index";

    }
}
