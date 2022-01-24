/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.peopleinmotion.horizonreinicioremoto.repository;

import com.peopleinmotion.horizonreinicioremoto.entity.GrupoAccion;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author avbravo
 */
public interface GrupoAccionRepository {
    public List<GrupoAccion> findAll();
    public Optional<GrupoAccion> find(BigInteger id);
    public Optional<GrupoAccion> findByGrupoAccionId(BigInteger GRUPOACCIONID);
    public Boolean create(GrupoAccion grupoAccion);
    
}
