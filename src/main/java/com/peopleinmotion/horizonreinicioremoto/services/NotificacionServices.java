/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.peopleinmotion.horizonreinicioremoto.services;

import com.peopleinmotion.horizonreinicioremoto.entity.Notificacion;
import com.peopleinmotion.horizonreinicioremoto.paginator.QuerySQL;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author avbravo
 */
public interface NotificacionServices {
   public List<Notificacion> findAll();

    public Optional<Notificacion> find(BigInteger id);

    public Optional<Notificacion> findByNotificacionId(BigInteger NOTIFICACIONID);

public Boolean process(BigInteger ID, String TIPODID) ;
    public Boolean create(Notificacion  notificacion);

    public Boolean update(Notificacion notificacion);

    public Boolean delete(Notificacion notificacion);

     public Optional<Notificacion>  findByIDANDTIPOID(BigInteger ID, String TIPODID) ;
     int countBancoIdAndActivo(BigInteger ID, String TIPODID);
    
    /*
    Query
     */

    public Boolean changed(Notificacion notificacion);

    public List<Notificacion> sql(QuerySQL querySQL);

    public List<Notificacion> pagination(QuerySQL querySQL, Integer pageNumber, Integer rowForPage);

    public int count(QuerySQL querySQL);

}
