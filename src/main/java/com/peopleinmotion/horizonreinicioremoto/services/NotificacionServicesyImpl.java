/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.peopleinmotion.horizonreinicioremoto.services;

import com.peopleinmotion.horizonreinicioremoto.entity.Notificacion;
import com.peopleinmotion.horizonreinicioremoto.facade.NotificacionFacade;
import com.peopleinmotion.horizonreinicioremoto.paginator.QuerySQL;
import com.peopleinmotion.horizonreinicioremoto.utils.ConsoleUtil;
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
public class NotificacionServicesyImpl implements NotificacionServices {

    @Inject
    NotificacionFacade notificacionFacade;

    @Override
    public List<Notificacion> findAll() {
        return notificacionFacade.findAll();
    }

    @Override

    public Optional<Notificacion> findByNotificacionId(BigInteger NOTIFICACIONID) {
        return notificacionFacade.findByNotificacionId(NOTIFICACIONID);
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

    @Override
    public Optional<Notificacion> findByIDANDTIPOID(BigInteger ID, String TIPODID) {
        return notificacionFacade.findByIDANDTIPOID(ID, TIPODID);
    }

    @Override
    public int countBancoIdAndActivo(BigInteger ID, String TIPODID) {
      return notificacionFacade.countBancoIdAndActivo(ID, TIPODID);
    }

    @Override
    public Boolean process(BigInteger ID, String TIPODID) {
        
        try {
            
            Optional<Notificacion> optional =notificacionFacade.findByIDANDTIPOID( ID, TIPODID);
            if(optional.isPresent()){
               Notificacion notificacion = optional.get();
               notificacion.setTRANSACCION(JsfUtil.generateUniqueID());
               if(update(notificacion)){
                   return Boolean.TRUE;
               }else{
                   ConsoleUtil.warning("No se pudo actualizar el codigo de transacción de la notificacion");
               }
            }else{
                 Notificacion notificacion = new  Notificacion();
                 notificacion.setID(ID);
                 notificacion.setTIPODID(TIPODID);
                 
               notificacion.setTRANSACCION(JsfUtil.generateUniqueID());
                if(create(notificacion)){
                   return Boolean.TRUE;
               }else{
                   ConsoleUtil.warning("No se pudo crear el registro  de la notificacion");
               }
            }
            
            return Boolean.TRUE;
        } catch (Exception e) {
            ConsoleUtil.error(JsfUtil.nameOfClass() + "."+JsfUtil.nameOfMethod() + " "+e.getLocalizedMessage());
        }
        return Boolean.FALSE;
    }

   

  
   
}
