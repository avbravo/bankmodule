/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.peopleinmotion.horizonreinicioremoto.utils;

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
}
