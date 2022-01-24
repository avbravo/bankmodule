/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.peopleinmotion.horizonreinicioremoto.repository;

import com.peopleinmotion.horizonreinicioremoto.entity.GrupoUsuario;
import com.peopleinmotion.horizonreinicioremoto.entity.Usuario;
import com.peopleinmotion.horizonreinicioremoto.entity.Grupo;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author daniel
 */
public interface GrupoUsuarioRepository {
    public List<GrupoUsuario> findAll();
    public Optional<GrupoUsuario> find(BigInteger id);
    public Optional<GrupoUsuario> findByGrupoUsuarioId(BigInteger GRUPOUSUARIOID);
    public List<GrupoUsuario> findByUsuarioId(Usuario USUARIOID);
    public List<GrupoUsuario> findByGrupoId(Grupo GRUPOID);
     public List<GrupoUsuario> findByUsuarioIdAndGrupoId(Usuario USUARIOID, Grupo GRUPOID);
    public Boolean create(GrupoUsuario grupoUsuario);
    public Boolean update(GrupoUsuario grupoUsuario);
    public Boolean delete(GrupoUsuario grupoUsuario);
}
