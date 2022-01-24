/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.peopleinmotion.horizonreinicioremoto.services;

import com.peopleinmotion.horizonreinicioremoto.entity.Banco;
import com.peopleinmotion.horizonreinicioremoto.entity.Usuario;
import com.peopleinmotion.horizonreinicioremoto.repository.BancoRepository;
import com.peopleinmotion.horizonreinicioremoto.repository.UsuarioRepository;
import javax.inject.Inject;

/**
 *
 * @author avbravo
 */
public interface AccessServices {
public String loadConfigurationPropeties();
public Boolean validateCredentials(Usuario usuario, String username, String password, Banco banco);
public Boolean validateCredentials(Usuario usuario, String username, String password);
}
