/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.peopleinmotion.horizonreinicioremoto.repository;


import com.peopleinmotion.horizonreinicioremoto.entity.Historial;
import com.peopleinmotion.horizonreinicioremoto.facade.HistorialFacade;
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
public class HistorialRepositoryImpl implements HistorialRepository {

    @Inject
    HistorialFacade historialFacade;

    @Override
    public List<Historial> findAll() {
        return historialFacade.findAll();
    }

   
    // <editor-fold defaultstate="collapsed" desc="Boolean create(Historial historial) ">
    @Override
    public Boolean create(Historial historial) {
        try {
            historialFacade.create(historial);
            return true;
        } catch (Exception e) {
            // System.out.println("create() " + e.getLocalizedMessage());
        }
        return false;
    }
// </editor-fold>

    
   

    @Override
    public Boolean update(Historial historial) {
        try {
            historialFacade.edit(historial);
            return true;
        } catch (Exception e) {
            // System.out.println("update() " + e.getLocalizedMessage());
        }
        return false;
    }

    @Override
    public Boolean delete(Historial historial) {
        try {
            historialFacade.remove(historial);
            return true;
        } catch (Exception e) {
            // System.out.println("update() " + e.getLocalizedMessage());
        }
        return false;
    }

    
// <editor-fold defaultstate="collapsed" desc="Boolean changed(Historial historial)>

    @Override
    public Boolean changed(Historial historial) {
        try {
            
            Optional<Historial> live = historialFacade.find(historial.getHISTORIALID());
            if (!live.isPresent()) {
                return Boolean.TRUE;
            }
            String jsonLive =live.get().toJSON();

            String json = historial.toJSON();

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
    public Optional<Historial> find(BigInteger id) {
        return historialFacade.find(id);
    }

    
    @Override
    public List<Historial> sql(QuerySQL querySQL) {
        return historialFacade.sql(querySQL);
    }
    @Override
    public List<Historial> pagination(QuerySQL querySQL, Integer pageNumber, Integer rowForPage) {
        return historialFacade.pagination(querySQL, pageNumber, rowForPage);
    }

    @Override
    public int count(QuerySQL querySQL) {
       return historialFacade.count(querySQL);
    }

   

  
   
}
