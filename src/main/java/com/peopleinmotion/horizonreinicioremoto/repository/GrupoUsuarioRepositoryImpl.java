/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.peopleinmotion.horizonreinicioremoto.repository;

import com.peopleinmotion.horizonreinicioremoto.entity.Estado;
import com.peopleinmotion.horizonreinicioremoto.entity.Grupo;
import com.peopleinmotion.horizonreinicioremoto.entity.GrupoUsuario;
import com.peopleinmotion.horizonreinicioremoto.entity.Usuario;
import com.peopleinmotion.horizonreinicioremoto.facade.GrupoUsuarioFacade;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author daniel
 */
@Stateless
public class GrupoUsuarioRepositoryImpl implements GrupoUsuarioRepository{
    
    @Inject
    GrupoUsuarioFacade grupoUsuarioFacade;
    
    @Override
    public List<GrupoUsuario> findAll() {
        return grupoUsuarioFacade.findAll();
    }

    @Override
    public Optional<GrupoUsuario> findByGrupoUsuarioId(BigInteger GRUPOUSUARIOID) {
        return grupoUsuarioFacade.findByGrupoUsuarioId(GRUPOUSUARIOID);
    }
    
    @Override
    public List<GrupoUsuario> findByUsuarioId(Usuario USUARIOID) {
        return grupoUsuarioFacade.findByUsuarioId(USUARIOID);
    }
    
    @Override
    public List<GrupoUsuario> findByGrupoId(Grupo GRUPOID) {
        return grupoUsuarioFacade.findByGrupoId(GRUPOID);
    }

    @Override
    public Boolean create(GrupoUsuario grupoUsuario) {
        try {
            grupoUsuarioFacade.create(grupoUsuario);
            return true;
        } catch (Exception e) {
            System.out.println("create() " + e.getLocalizedMessage());
        }
        return false;
    }
    
    @Override
    public Boolean update(GrupoUsuario grupoUsuario) {
     try {
            grupoUsuarioFacade.edit(grupoUsuario);
            return true;
        } catch (Exception e) {
            System.out.println("update() " + e.getLocalizedMessage());
        }
        return false;
    }
    
    @Override
    public Boolean delete(GrupoUsuario grupoUsuario) {
          try {
            grupoUsuarioFacade.remove(grupoUsuario);
            return true;
        } catch (Exception e) {
            System.out.println("delete() " + e.getLocalizedMessage());
        }
        return false;
    }

    @Override
    public List<GrupoUsuario> findByUsuarioIdAndGrupoId(Usuario USUARIOID, Grupo GRUPOID) {
      return grupoUsuarioFacade.findByUsuarioIdAndGrupoId(USUARIOID, GRUPOID);
    }

    @Override
    public Optional<GrupoUsuario> find(BigInteger id) {
      return grupoUsuarioFacade.find(id);
    }
}
