/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.peopleinmotion.horizonreinicioremoto.utils;

import com.peopleinmotion.horizonreinicioremoto.entity.Usuario;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.ejb.Stateless;

/**
 *
 * @author avbravo
 */
@Stateless
public class PasswordValidatorImpl implements PasswordValidator {

    private static final String PASSWORD_PATTERN
            = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#()–[{}]:;',?/*~$^+=<>]).{8,20}$";
   private static final Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
   
    private static final String NUMBER_PATTERN
            = "^(?=.*[0-9])$";
   private static final Pattern patternNumber = Pattern.compile(NUMBER_PATTERN);
   
   

    public Boolean isValid(final String password) {
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

    @Override
    public Boolean checkNull(String passwordOld, String passwordNew, String passwordRepetido,  String passwordDesencriptado) {
        try {
            if (passwordOld == null || passwordOld.equals("")) {
                JsfUtil.warningMessage("Ingrese la contraseña anterior");
                return Boolean.FALSE;
            }
            if (passwordNew == null || passwordNew.equals("")) {
                JsfUtil.warningMessage("Ingrese la nueva contraseña");
                return Boolean.FALSE;
            }
            if (passwordRepetido == null || passwordRepetido.equals("")) {
                JsfUtil.warningMessage("Ingrese la contraseña en la casilla de contraseña de confirmación");
                return Boolean.FALSE;
            }
            if (!passwordOld.trim().equals(passwordDesencriptado.trim())) {
                JsfUtil.warningMessage("La contraseña anterior no coincide con que tiene registrada en la base de datos");
                return Boolean.FALSE;
            }
            if (!passwordNew.equals(passwordRepetido)) {
                JsfUtil.warningMessage("La nueva contraseña no coincide con la contraseña de confirmación.");
                return Boolean.FALSE;
            }

            if (passwordOld.equals(passwordNew)) {
                JsfUtil.warningMessage("La contraseña nueva debe ser diferente de la contraseña anterior");
                return Boolean.FALSE;
            }
            return Boolean.TRUE;
        } catch (Exception e) {
            JsfUtil.errorMessage(JsfUtil.nameOfMethod() + " " + e.getLocalizedMessage());
        }
         return Boolean.FALSE;
    }
}
