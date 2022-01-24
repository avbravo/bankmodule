/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.peopleinmotion.horizonreinicioremoto.services;

import com.peopleinmotion.horizonreinicioremoto.domains.TokenReader;
import com.peopleinmotion.horizonreinicioremoto.entity.Token;
import com.peopleinmotion.horizonreinicioremoto.entity.Usuario;

/**
 *
 * @author avbravo
 */
public interface TokenServices {
    public String marcarToken(String numero, String tokenIngresado);
    public TokenReader marcarToken(String numero, TokenReader tokenReader);
      public Boolean validateToken(Usuario usuario, String tokenIngresado) ;
      public Token supplier();
}
