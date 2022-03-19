/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.peopleinmotion.horizonreinicioremoto.utils;

import com.peopleinmotion.horizonreinicioremoto.entity.Usuario;

/**
 *
 * @author avbravo
 */
public interface PasswordValidator {
 public Boolean isValid(final String password);
 public Boolean checkNull(String passwordOld, String passwordNew, String passwordRepetido, String passwordDesencriptado);
}
