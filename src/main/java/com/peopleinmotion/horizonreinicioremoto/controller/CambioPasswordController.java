/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.peopleinmotion.horizonreinicioremoto.controller;

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
          ConsoleUtil.info("passwordOld "+passwordOld);
          ConsoleUtil.info("userPassword desencriptado"+JsfUtil.desencriptar(user.getPASSWORD()));
            if (passwordOld == null || passwordOld.equals("")) {
                JsfUtil.warningMessage("Ingrese el password anterior");
                return "";
            }
            if (passwordNew == null || passwordNew.equals("")) {
                JsfUtil.warningMessage("Ingrese el password nuevo");
                return "";
            }
            if (passwordRepetido == null || passwordRepetido.equals("")) {
                JsfUtil.warningMessage("Ingrese el password repetido");
                return "";
            }
            if (!passwordOld.trim().equals(JsfUtil.desencriptar(user.getPASSWORD()).trim())) {
                JsfUtil.warningMessage("El password anterior no coincide con su password");
                return "";
            }
            if (!passwordNew.equals(passwordRepetido)) {
                JsfUtil.warningMessage("El password nuevo no coincide con el password repetido");
                return "";
            }

            if (passwordOld.equals(passwordNew)) {
                JsfUtil.warningMessage("La contraseña nuevo debe ser diferente del password anterior");
                return "";
            }

            user.setPASSWORD(JsfUtil.encriptar(passwordNew));
            if(!passwordValidator.isValid(passwordNew)){
                      JsfUtil.warningMessage("No es una contraseña valida..");
                      return "";
            }
            if (usuarioRepository.update(user)) {
                passwordNew="";
                passwordOld="";
                passwordRepetido="";
                JsfUtil.successMessage("Se realizo con éxito el cambio de contraseña");
            } else {
                JsfUtil.warningMessage("No se logro realizar el cambio de contraseña");
            }
        } catch (Exception e) {
            JsfUtil.errorMessage(JsfUtil.nameOfMethod() + " " + e.getLocalizedMessage());
        }
        return "";
    }
    // </editor-fold> 
    
      // <editor-fold defaultstate="collapsed" desc="String onCommandButtonShowAyuda()>
    public String onCommandButtonShowAyuda(){
        try {
            
        } catch (Exception e) {
        }
        return "";
    }
    // </editor-fold> 
}
