/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.peopleinmotion.horizonreinicioremoto.repository;

import com.peopleinmotion.horizonreinicioremoto.entity.Estado;
import com.peopleinmotion.horizonreinicioremoto.entity.GrupoEstado;
import com.peopleinmotion.horizonreinicioremoto.facade.GrupoEstadoFacade;
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
public class GrupoEstadoRepositoryImpl implements GrupoEstadoRepository {

    @Inject
    GrupoEstadoFacade grupoEstadoFacade;

    @Override
    public List<GrupoEstado> findAll() {
        return grupoEstadoFacade.findAll();
    }

    @Override

    public Optional<GrupoEstado> findByGrupoEstadoId(BigInteger GRUPOESTADOID) {
        return grupoEstadoFacade.findByGrupoEstadoId(GRUPOESTADOID);
    }

    @Override
    public Boolean create(GrupoEstado grupoEstado) {
        try {
            grupoEstadoFacade.create(grupoEstado);
            return true;
        } catch (Exception e) {
            // System.out.println("create() " + e.getLocalizedMessage());
        }
        return false;
    }
// <editor-fold defaultstate="collapsed" desc="List<GrupoEstado> findByActivo(String ACTIVO)">


    @Override
    public List<GrupoEstado> findByActivo(String ACTIVO) {
        return grupoEstadoFacade.findByActivo(ACTIVO);
    }
// </editor-fold>

    @Override
    public Optional<GrupoEstado> find(BigInteger id) {
      return grupoEstadoFacade.find(id); 
    }
}
