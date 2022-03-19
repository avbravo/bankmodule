/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.peopleinmotion.horizonreinicioremoto.controller;

import com.peopleinmotion.horizonreinicioremoto.domains.MessagesForm;
import com.peopleinmotion.horizonreinicioremoto.entity.Banco;
import com.peopleinmotion.horizonreinicioremoto.entity.Usuario;
import com.peopleinmotion.horizonreinicioremoto.interfaces.Page;
import com.peopleinmotion.horizonreinicioremoto.jmoordb.JmoordbContext;
import com.peopleinmotion.horizonreinicioremoto.repository.UsuarioRepository;
import com.peopleinmotion.horizonreinicioremoto.utils.ConsoleUtil;
import com.peopleinmotion.horizonreinicioremoto.utils.JsfUtil;
import com.peopleinmotion.horizonreinicioremoto.utils.PasswordValidator;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import lombok.Data;

/**
 *
 * @author avbravo
 */
@Named
@ViewScoped
@Data

public class CambioPasswordController implements Serializable, Page {

    // <editor-fold defaultstate="collapsed" desc="field ">
    private static final long serialVersionUID = 1L;

    Usuario user = new Usuario();
    Banco bank = new Banco();
    private String passwordOld = "";
    private String passwordNew = "";
    private String passwordRepetido = "";



// </editor-fold>
// <editor-fold defaultstate="collapsed" desc="@Inject ">
    @Inject
    UsuarioRepository usuarioRepository;
    @Inject
    PasswordValidator passwordValidator;
// </editor-fold>

    /**
     * Creates a new instance of CajeroAccionController
     */
    public CambioPasswordController() {
    }

    // <editor-fold defaultstate="collapsed" desc="init() ">
    @PostConstruct
    public void init() {
        try {
    
            if (JmoordbContext.get("user") == null) {

            } else {

                user = (Usuario) JmoordbContext.get("user");
                bank = (Banco) JmoordbContext.get("banco");

            }
        } catch (Exception e) {
            JsfUtil.errorMessage(JsfUtil.nameOfMethod() + " " + e.getLocalizedMessage());

        }

    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="String onCommandButtonCambioPassword()">
    public String onCommandButtonCambiarPassword() {
        try {
            user = (Usuario) JmoordbContext.get("user");

            String passwordDesencriptado = JsfUtil.desencriptar(user.getPASSWORD());
            ConsoleUtil.info("userPassword desencriptado " + passwordDesencriptado);
          
          
                ConsoleUtil.info("passwordOld " + passwordOld);
                if (!passwordValidator.checkNull(passwordOld, passwordNew, passwordRepetido, passwordDesencriptado)) {
                    return "";
                }
            
              
         

           
            if (!passwordValidator.isValid(passwordNew)) {
                JsfUtil.warningMessage("La nueva contraseña no cumple los requisitos para crearla. Consulte la ayuda.");
                return "";
            }
             user.setPASSWORD(JsfUtil.encriptar(passwordNew));
            if (usuarioRepository.update(user)) {
                JmoordbContext.put("user", user);
                passwordNew = "";
                passwordOld = "";
                passwordRepetido = "";
              
             
             
                //JsfUtil.successMessage("Se realizo con éxito el cambio de contraseña");
                MessagesForm messagesForm = new MessagesForm.Builder()
                        .errorWindows(Boolean.FALSE)
                        .id(user.getNOMBRE())
                        .header("Operación exitosa")
                        .header2("La acción se realizó exitosamente")
                        .image("atm-green01.png")
                        .libary("images")
                        .titulo("Cambio de contraseña")
                        .mensaje("Se realizó exitosamente el cambio de contraseña")
                        .returnTo("dashboard.xhtml")
                        .build();
                JmoordbContext.put("messagesForm", messagesForm);

                JmoordbContext.put("pageInView", "messagesform.xhtml");
                return "messagesform.xhtml";
            } else {
                JsfUtil.warningMessage("No se logro realizar el cambio de contraseña");
            }
        } catch (Exception e) {
            JsfUtil.errorMessage(JsfUtil.nameOfMethod() + " " + e.getLocalizedMessage());
        }
        return "";
    }
    // </editor-fold> 

    // <editor-fold defaultstate="collapsed" desc="void onCommandButtonShowAyuda()>
    public void onCommandButtonShowAyuda() {
        try {

        } catch (Exception e) {
            JsfUtil.errorMessage(JsfUtil.nameOfMethod() + " " + e.getLocalizedMessage());
        }

    }
    // </editor-fold> 
   
    
   
}
