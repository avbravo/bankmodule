/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.peopleinmotion.horizonreinicioremoto.repository;

import com.peopleinmotion.horizonreinicioremoto.entity.Accion;
import com.peopleinmotion.horizonreinicioremoto.entity.Estado;
import com.peopleinmotion.horizonreinicioremoto.entity.GrupoEstado;
import com.peopleinmotion.horizonreinicioremoto.facade.EstadoFacade;
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
public class EstadoRepositoryImpl implements EstadoRepository {

    @Inject
    EstadoFacade estadoFacade;

    @Override
    public List<Estado> findAll() {
        return estadoFacade.findAll();
    }

    
    @Override
    public Boolean create(Estado estado) {
        try {
            estadoFacade.create(estado);
            return true;
        } catch (Exception e) {
            // System.out.println("create() " + e.getLocalizedMessage());
        }
        return false;
    }

    @Override
    public Optional<Estado> findByEstadoId(BigInteger ESTADOID) {
       return estadoFacade.findByEstadoId(ESTADOID);
    }

    @Override
    public List<Estado> findByGrupoEstadoId(GrupoEstado GRUPOESTADOID) {
        return estadoFacade.findByGrupoEstadoId(GRUPOESTADOID);
    }

    @Override
    public Optional<Estado> findByPredeterminado(String PREDETERMINADO) {
       return estadoFacade.findByPredeterminado(PREDETERMINADO);
    }

    @Override
    public List<Estado> findByActivo(String arg0) {
       return estadoFacade.findByActivo("SI");
    }

    @Override
    public Optional<Estado> findByPredeterminadoAndActivo(String PREDETERMINADO, String ACTIVO) {
        return estadoFacade.findByPredeterminadoAndActivo(PREDETERMINADO, ACTIVO);
    }

    @Override
    public Optional<Estado> find(BigInteger id) {
       return estadoFacade.find(id);
    }

}
