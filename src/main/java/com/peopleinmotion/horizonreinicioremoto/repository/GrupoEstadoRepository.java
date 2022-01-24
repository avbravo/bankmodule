/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.peopleinmotion.horizonreinicioremoto.repository;

import com.peopleinmotion.horizonreinicioremoto.entity.GrupoEstado;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author avbravo
 */
public interface GrupoEstadoRepository {
    public List<GrupoEstado> findAll();
    public Optional<GrupoEstado> find(BigInteger id);
    public Optional<GrupoEstado> findByGrupoEstadoId(BigInteger GRUPOACCIONID);
    public Boolean create(GrupoEstado grupoEstado);
    public List<GrupoEstado> findByActivo(String ACTIVO) ;
    
}
