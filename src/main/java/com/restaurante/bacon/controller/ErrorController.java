/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.restaurante.bacon.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 *
 * @author jean
 */
@RequestMapping("/errorBacon")
@Controller
public class ErrorController {
    @RequestMapping("/500")
    public String error_500(Model modelo) {
        return "error/error-500";
    } 
    
}
