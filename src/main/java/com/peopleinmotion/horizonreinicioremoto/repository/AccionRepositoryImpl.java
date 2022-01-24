/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.peopleinmotion.horizonreinicioremoto.repository;

import com.peopleinmotion.horizonreinicioremoto.entity.Accion;
import com.peopleinmotion.horizonreinicioremoto.entity.Banco;
import com.peopleinmotion.horizonreinicioremoto.entity.GrupoAccion;
import com.peopleinmotion.horizonreinicioremoto.facade.AccionFacade;
import com.peopleinmotion.horizonreinicioremoto.paginator.QuerySQL;
import com.peopleinmotion.horizonreinicioremoto.utils.JsfUtil;
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
public class AccionRepositoryImpl implements AccionRepository {
    @Inject
    AccionFacade accionFacade;

    @Override
    public List<Accion> findAll() {
       return accionFacade.findAll();
    }

    
     @Override
    public Optional<Accion> findByAccionId(BigInteger ACCIONID) {
        return accionFacade.findByAccionId(ACCIONID);
    }
    
    @Override
    public List<Accion> findByGrupoAccionId(GrupoAccion GRUPOACCIONID) {
       return accionFacade.findByGrupoAccionId(GRUPOACCIONID);
    }
@Override
    public List<Accion> findByAccionIdAndActivo(BigInteger ACCIONID, String ACTIVO) {
        return accionFacade.findByAccionIdAndActivo(ACCIONID,ACTIVO);
    }
    
   
    @Override
    public Boolean create(Accion accion) {
        try {
            accionFacade.create(accion);
            return true;
        } catch (Exception e) {
            JsfUtil.warningMessage("create() "+e.getLocalizedMessage());
            // System.out.println("create() "+e.getLocalizedMessage());
        }
        return false;
    }
   
    @Override
    public Boolean update(Accion accion) {
        try {
            accionFacade.edit(accion);
            return true;
        } catch (Exception e) {
            JsfUtil.warningMessage("update() "+e.getLocalizedMessage());
            // System.out.println("update() "+e.getLocalizedMessage());
        }
        return false;
    }

    @Override
    public List<Accion> findByGrupoAccionIdAndPredeterminado( GrupoAccion GRUPOACCIONID, String PREDETERMINADO) {
         return accionFacade.findByGrupoAccionIdAndPredeterminado(GRUPOACCIONID, PREDETERMINADO);
    }

    @Override
    public Optional<Accion> find(BigInteger id) {
       return accionFacade.find(id);
    }

    // <editor-fold defaultstate="collapsed" desc=Boolean changed(Accion accion)>

    @Override
    public Boolean changed(Accion accion) {
        try {
            
            Optional<Accion> live = accionFacade.find(accion.getACCIONID());
            if (!live.isPresent()) {
                return Boolean.TRUE;
            }
            String jsonLive = live.get().toJSON();

            String json =accion.toJSON();

            if (!json.equals(jsonLive)) {
                //Otro usuario lo cambio mientras se estaba procesando
                return Boolean.TRUE;
            }
        } catch (Exception e) {
            JsfUtil.errorMessage(JsfUtil.nameOfMethod() + " " + e.getLocalizedMessage());
        }
        return Boolean.FALSE;
    }
    // </editor-fold>

     
    @Override
    public List<Accion> sql(QuerySQL querySQL) {
        return accionFacade.sql(querySQL);
    }
    @Override
    public List<Accion> pagination(QuerySQL querySQL, Integer pageNumber, Integer rowForPage) {
        return accionFacade.pagination(querySQL, pageNumber, rowForPage);
    }

    @Override
    public int count(QuerySQL querySQL) {
       return accionFacade.count(querySQL);
    }

    
    
}
