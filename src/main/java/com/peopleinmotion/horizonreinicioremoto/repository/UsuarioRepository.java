/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.peopleinmotion.horizonreinicioremoto.repository;

import com.peopleinmotion.horizonreinicioremoto.entity.Banco;
import com.peopleinmotion.horizonreinicioremoto.entity.Usuario;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author avbravo
 */
public interface UsuarioRepository {
    public List<Usuario> findAll();
    public Optional<Usuario> find(BigInteger id) ;
    public Optional<Usuario> findByUsuarioId(BigInteger USUARIOID) ;
    public List<Usuario> findByUsername(String USERNAME);
    public List<Usuario> findByBancoIdAndActivo(Banco BANCOID, String ACTIVO);
    public Boolean create(Usuario usuario);
    
}
