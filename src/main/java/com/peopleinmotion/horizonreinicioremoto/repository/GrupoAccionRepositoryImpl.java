/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.peopleinmotion.horizonreinicioremoto.repository;

import com.peopleinmotion.horizonreinicioremoto.entity.Accion;
import com.peopleinmotion.horizonreinicioremoto.entity.GrupoAccion;
import com.peopleinmotion.horizonreinicioremoto.facade.GrupoAccionFacade;
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
public class GrupoAccionRepositoryImpl implements GrupoAccionRepository {

    @Inject
    GrupoAccionFacade grupoAccionFacade;

    @Override
    public List<GrupoAccion> findAll() {
        return grupoAccionFacade.findAll();
    }

    @Override

    public Optional<GrupoAccion> findByGrupoAccionId(BigInteger GRUPOACCIONID) {
        return grupoAccionFacade.findByGrupoAccionId(GRUPOACCIONID);
    }

    @Override
    public Boolean create(GrupoAccion grupoAccion) {
        try {
            grupoAccionFacade.create(grupoAccion);
            return true;
        } catch (Exception e) {
            // System.out.println("create() " + e.getLocalizedMessage());
        }
        return false;
    }

    @Override
    public Optional<GrupoAccion> find(BigInteger id) {
       return grupoAccionFacade.find(id);
    }

}
