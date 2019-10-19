/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.restaurante.bacon.controller;

import com.restaurante.bacon.dto.Mesa;
import com.restaurante.bacon.dto.Receta;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author jean
 */
@RequestMapping("/cliente/pedirOrdenExtra")
@Controller
public class ClientePedirOrdenExtraController {
    
    
    @RequestMapping("/")
    public ModelAndView pedirOrdenExtra(Model model, HttpSession sesion){
                
        return new ModelAndView("redirect:/cliente/pedirOrden/?tipo=2");
        
    }
   
}
