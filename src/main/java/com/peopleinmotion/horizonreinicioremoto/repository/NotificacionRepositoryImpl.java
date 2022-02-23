/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.peopleinmotion.horizonreinicioremoto.repository;

import com.peopleinmotion.horizonreinicioremoto.entity.Notificacion;
import com.peopleinmotion.horizonreinicioremoto.facade.NotificacionFacade;
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
public class NotificacionRepositoryImpl implements NotificacionRepository {

    @Inject
    NotificacionFacade notificacionFacade;

    @Override
    public List<Notificacion> findAll() {
        return notificacionFacade.findAll();
    }

    @Override

    public Optional<Notificacion> findByNotificacionId(BigInteger BANCOID) {
        return notificacionFacade.findByNotificacionId(BANCOID);
    }

    // <editor-fold defaultstate="collapsed" desc="Boolean create(Notificacion notificacion) ">
    @Override
    public Boolean create(Notificacion notificacion) {
        try {
            notificacionFacade.create(notificacion);
            return true;
        } catch (Exception e) {
            // System.out.println("create() " + e.getLocalizedMessage());
        }
        return false;
    }
// </editor-fold>

   

    @Override
    public Boolean update(Notificacion notificacion) {
        try {
            notificacionFacade.edit(notificacion);
            return true;
        } catch (Exception e) {
            // System.out.println("update() " + e.getLocalizedMessage());
        }
        return false;
    }

    @Override
    public Boolean delete(Notificacion notificacion) {
        try {
            notificacionFacade.remove(notificacion);
            return true;
        } catch (Exception e) {
            // System.out.println("update() " + e.getLocalizedMessage());
        }
        return false;
    }

    
// <editor-fold defaultstate="collapsed" desc="Boolean changed(Notificacion notificacion)>

    @Override
    public Boolean changed(Notificacion notificacion) {
        try {
            
            Optional<Notificacion> live = notificacionFacade.find(notificacion.getNOTIFICACIONID());
            if (!live.isPresent()) {
                return Boolean.TRUE;
            }
            String jsonLive = live.get().toJSON();

            String json =notificacion.toJSON();

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
    public Optional<Notificacion> find(BigInteger id) {
        return notificacionFacade.find(id);
    }

    
    @Override
    public List<Notificacion> sql(QuerySQL querySQL) {
        return notificacionFacade.sql(querySQL);
    }
    @Override
    public List<Notificacion> pagination(QuerySQL querySQL, Integer pageNumber, Integer rowForPage) {
        return notificacionFacade.pagination(querySQL, pageNumber, rowForPage);
    }

    @Override
    public int count(QuerySQL querySQL) {
       return notificacionFacade.count(querySQL);
    }

   

  
   
}
