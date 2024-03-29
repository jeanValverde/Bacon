/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.restaurante.bacon.service;

import static com.restaurante.bacon.controller.PersonalController.UPLOAD_DIR_IMAGEN;
import com.restaurante.bacon.dao.IIPersonalDao;
import com.restaurante.bacon.dto.Personal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.restaurante.bacon.dao.IPersonalDao;
import com.restaurante.bacon.dao.IRolDao;
import com.restaurante.bacon.dao.ProcedureQueryPersonal;
import com.restaurante.bacon.dto.Rol;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Optional;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author jean
 *
 * Esta clase es para crear los metodos para la capa de servicio
 *
 * Este servicio esta implementado de UserDetailsService por que se necesita
 * sobreescribir el metodo loadUserByUsername para gestionar la dependencia de
 * seguridad de spring e iniciar sesión
 *
 */
@Service
public class PersonalService implements UserDetailsService {

    //se obtienen los metodos generados automaticamente por la interfaz
    @Autowired
    IPersonalDao personalDao;

    @Autowired
    IIPersonalDao personaldao2;

    @Autowired
    ProcedureQueryPersonal procedureQuery;
    @Autowired
    IRolDao rol;

    //se implementa el metodo de la interfaz 
    public List<Personal> getAllUsuario() {
        return this.personalDao.findAll();
    }

    //se implementan los metodos de la interfaz 
    public Personal findByRut(String rut) {
        return this.personalDao.findByRutPersonal(rut);
    }

    //se implementan los metodos de la interfaz 
    public Personal addPersonal(Personal personal) {
        return this.personalDao.save(personal);
    }

    //busca al personal de la sesión activa segun el rut 
    //elimina la contraseña para mantener la seguridad de la página 
    public Personal getPersonalSesion(String rut) {
        Personal personal = findByRut(rut);
        return personal;
    }

    public String subirImagen(MultipartFile[] file) {
        try {
            StringBuilder filename = new StringBuilder();

            String nombreArchivo = file[0].getOriginalFilename();

            String extencion = "";

            int index = nombreArchivo.lastIndexOf('.');
            if (index == -1) {
                extencion = "";
            } else {
                extencion = nombreArchivo.substring(index + 1);
            }

            UUID uuid = UUID.randomUUID();

            nombreArchivo = uuid.toString() + "." + extencion; //nonmbre del archivo con formato unico 

            Path fileNamePath = Paths.get(UPLOAD_DIR_IMAGEN, nombreArchivo);

            filename.append(nombreArchivo);

            Files.write(fileNamePath, file[0].getBytes());
            return nombreArchivo;
        } catch (Exception ex) {
            return null;
        }
    }

    public boolean comprobarImagen(String nombreArchivo) {
        File f = new File(UPLOAD_DIR_IMAGEN + '/' + nombreArchivo);
        if (f.exists() && !f.isDirectory()) {
            return true;
        } else {
            return false;
        }
    }

    public void eliminarImagen(String nombreArchivo) {
        Path fileNamePath = Paths.get(UPLOAD_DIR_IMAGEN, nombreArchivo);
        try {
            Files.delete(fileNamePath);
        } catch (IOException ex) {
            Logger.getLogger(PersonalService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //busca el usuario en  la base de datos y carga la 
    //autentificación en Spring Boot Securiry dependencia
    @Override
    public UserDetails loadUserByUsername(String rut) throws UsernameNotFoundException {

        Personal personal = this.personalDao.findByRutPersonal(rut);

        List<GrantedAuthority> roles = new ArrayList<>();

        roles.add(new SimpleGrantedAuthority(personal.getIdRol().getDescripcionRol()));

        UserDetails userDetails = new User(personal.getRutPersonal(),
                personal.getContrasenaPersonal(), roles);

        return userDetails;

    }

    //se obtiene la edad a partir de un date 
    public String getEdad(Date fechaNacimiento) {
        if (fechaNacimiento != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            StringBuilder result = new StringBuilder();
            if (fechaNacimiento != null) {
                result.append(sdf.format(fechaNacimiento));
                result.append(" (");
                Calendar c = new GregorianCalendar();
                c.setTime(fechaNacimiento);
                result.append(calcularEdad(c));
                result.append(" años)");
            }
            return result.toString();
        }
        return "";
    }

    //calcula la edad a partir de la fecha  
    private static int calcularEdad(Calendar fechaNac) {
        Calendar today = Calendar.getInstance();
        int diffYear = today.get(Calendar.YEAR) - fechaNac.get(Calendar.YEAR);
        int diffMonth = today.get(Calendar.MONTH) - fechaNac.get(Calendar.MONTH);
        int diffDay = today.get(Calendar.DAY_OF_MONTH) - fechaNac.get(Calendar.DAY_OF_MONTH);
        // Si está en ese año pero todavía no los ha cumplido
        if (diffMonth < 0 || (diffMonth == 0 && diffDay < 0)) {
            diffYear = diffYear - 1;
        }
        return diffYear;
    }

    /**
     * Permite convertir un String en fecha (Date).
     *
     * @param fecha Cadena de fecha dd/MM/yyyy
     * @return Objeto Date
     */
    public static Date ParseFecha(String fecha) {
        SimpleDateFormat formato = new SimpleDateFormat("MM/dd/yyyy");
        Date fechaDate = null;
        try {
            fechaDate = formato.parse(fecha);
        } catch (ParseException ex) {
            System.out.println(ex);
        }
        return fechaDate;
    }

    public boolean insertPersonal(Personal personal) {
        return this.procedureQuery.InsertPersonal(personal.getRutPersonal(), personal.getNombresPersonal(), personal.getApePaternoPersonal(), personal.getApeMaternoPersonal(), personal.getFechaNacimientoPersonal(), personal.getCelularPersonal(), personal.getCorreoPersonal(), personal.getContrasenaPersonal(), personal.getEstadoPersonal(), personal.getIdRol().getIdRol());
    }

    public boolean modificarPersonal(Personal personal) {

        return this.procedureQuery.modificarPersonal(personal.getIdPersonal(), personal.getRutPersonal(), personal.getNombresPersonal(), personal.getApePaternoPersonal(), personal.getApeMaternoPersonal(), personal.getFechaNacimientoPersonal(), personal.getCelularPersonal(), personal.getCorreoPersonal(), personal.getEstadoPersonal(), personal.getIdRol().getIdRol());
    }

    public List<Rol> listarRol() {
        return this.rol.findAll();
    }

    public void deletePersonalByID(String id) {

        this.personalDao.deleteById(id);
    }

    public Personal retornarPersonalById(BigDecimal idPersonal) {

        return this.personaldao2.findByIdPersonal(idPersonal);
    }
    
    public Rol retornarRolById(Integer idRol) {
        Optional<Rol> optinalEntity = rol.findById(idRol);
        Rol rol = optinalEntity.get();
        return rol;
    }

    public List<Personal> filtrarPersonalByRut(String rut) {
        return this.procedureQuery.filtrarPersonalByRut(rut);
    }

    public List<Personal> filtrarPersonalByNombre(String nombrePersonal) {
        return this.procedureQuery.filtroPorNombre(nombrePersonal);
    }

}
