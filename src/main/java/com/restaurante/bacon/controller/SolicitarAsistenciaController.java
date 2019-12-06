/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.restaurante.bacon.controller;

import com.restaurante.bacon.config.UserRol;
import com.restaurante.bacon.dao.ProcedureSolicitarAsistencia;
import com.restaurante.bacon.dto.Cliente;
import com.restaurante.bacon.dto.Notificacion;
import com.restaurante.bacon.dto.Personal;
import com.restaurante.bacon.dto.Rol;
import com.restaurante.bacon.service.PersonalService;
import com.restaurante.bacon.service.SolicitarAsistenciaService;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author estebantoledo
 */
@RequestMapping("/cliente")
@Controller
public class SolicitarAsistenciaController {

    @Autowired
    PersonalService personalService;
    @Autowired
    SolicitarAsistenciaService solicitarAsistencia;
    @Autowired
    ProcedureSolicitarAsistencia solicitudAsistencia;

    @RequestMapping("/add")

    public String añadirAsistencia(Model modelo,
            HttpSession sesion) {

        Cliente cliente = (Cliente) sesion.getAttribute("sesionCliente");
        Notificacion notificacion = new Notificacion();
        Integer id = cliente.getIdCliente();
        String nombre = cliente.getNombre();

        notificacion.setDescripcion(" El Cliente: " + nombre + " , Ha Solicitado Asistencia");
        notificacion.setAsunto("Cliente: " + id + " solicita Asistencia");
        notificacion.setEstado(BigInteger.valueOf(Integer.parseInt("0")));
        notificacion.setFechaNotificacion(new Date());
        Rol rol = new Rol();
        rol.setIdRol(4);
        notificacion.setIdRol(rol);
        this.solicitarAsistencia.add(notificacion);

        modelo.addAttribute("asistencias", this.solicitudAsistencia.asistenciasByIdCliente(id));

        return "users/cliente/visualizarAsistencias";

    }

    @RequestMapping("/addAsistenciaPago")
    public String añadirAsistenciaPago(Model modelo,
            HttpSession sesion) {

        Cliente cliente = (Cliente) sesion.getAttribute("sesionCliente");
        Notificacion notificacion = new Notificacion();
        Integer id = cliente.getIdCliente();
        String nombre = cliente.getNombre();

        notificacion.setDescripcion(" El Cliente: " + nombre + ", Ha Solicitado Pago");
        notificacion.setAsunto("Cliente: " + id + " solicita Pago");
        notificacion.setEstado(BigInteger.valueOf(Integer.parseInt("0")));
        notificacion.setFechaNotificacion(new Date());
        Rol rol = new Rol();
        rol.setIdRol(4);
        notificacion.setIdRol(rol);
        this.solicitarAsistencia.add(notificacion);

        modelo.addAttribute("asistencias", this.solicitudAsistencia.asistenciasByIdCliente(id));

        return "users/cliente/visualizarAsistencias";

    }

    @RequestMapping("/cancel")
    public String botonCancel() {
        return "users/cliente/visualizarAsistencias";

    }
}
