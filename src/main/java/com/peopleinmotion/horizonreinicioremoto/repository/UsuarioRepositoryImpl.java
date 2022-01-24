/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.peopleinmotion.horizonreinicioremoto.repository;

import com.peopleinmotion.horizonreinicioremoto.entity.Banco;
import com.peopleinmotion.horizonreinicioremoto.entity.Usuario;
import com.peopleinmotion.horizonreinicioremoto.facade.UsuarioFacade;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author avbravo
 */
@Stateless
public class UsuarioRepositoryImpl implements UsuarioRepository {

    @Inject
    UsuarioFacade usuarioFacade;

    @Override
    public List<Usuario> findAll() {
        return usuarioFacade.findAll();
    }

    @Override
    public Optional<Usuario> findByUsuarioId(BigInteger USUARIOID) {
        return usuarioFacade.findByUsuarioId( USUARIOID);
    }

     @Override
    public List<Usuario> findByUsername(String USERNAME) {
        return usuarioFacade.findByUsername(USERNAME);
    }

    @Override
    public Boolean create(Usuario usuario) {
        try {
            usuarioFacade.create(usuario);
            return true;
        } catch (Exception e) {
            // System.out.println("create() " + e.getLocalizedMessage());
        }
        return false;
    }

    @Override
    public List<Usuario> findByBancoIdAndActivo(Banco BANCOID, String ACTIVO) {
        return usuarioFacade.findByBancoIdAndActivo(BANCOID, ACTIVO);
    }
    @Override
    public Optional<Usuario> find(BigInteger id) {
        return usuarioFacade.find(id);
    }

   
}
