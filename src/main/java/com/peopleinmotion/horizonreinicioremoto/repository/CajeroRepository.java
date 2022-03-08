/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.peopleinmotion.horizonreinicioremoto.repository;

import com.peopleinmotion.horizonreinicioremoto.entity.Banco;
import com.peopleinmotion.horizonreinicioremoto.entity.Cajero;
import com.peopleinmotion.horizonreinicioremoto.paginator.QuerySQL;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author avbravo
 */
public interface CajeroRepository {

    public List<Cajero> findAll();

    public Optional<Cajero> find(BigInteger id);
    public Optional<Cajero> findByCajeroId(BigInteger CAJEROID);

    public List<Cajero> findByCajeroIdAndActivo(BigInteger CAJEROID, String ACTIVO);

    public List<Cajero> findByBancoId(Banco BANCOID);
    public List<Cajero> findByBancoIdAndActivo(Banco BANCOID, String ACTIVO);

    public Boolean create(Cajero cajero);
    public List<Cajero> findByCajeroIdBancoIdAndActivo(Banco BANCOID, String CAJERO, String ACTIVO);
    public List<Cajero> findByDireccionCortaBancoIdAndActivo(Banco BANCOID, String DIRECCIONCORTA, String ACTIVO);

    
     public Boolean changed(Cajero cajero);

    public List<Cajero> sql(QuerySQL querySQL);

    public List<Cajero> pagination(QuerySQL querySQL, Integer pageNumber, Integer rowForPage);

    public int count(QuerySQL querySQL);
    
        public int countBancoIdAndActivo(Banco BANCOID, String ACTIVO);
        public List<Cajero> findBancoIdAndActivoPaginacion(Banco BANCOID, String ACTIVO, Integer pageNumber, Integer rowForPage);
        
        public int countCajeroBancoIdAndActivoLike(String cajero,Banco BANCOID, String ACTIVO);
 public List<Cajero> findCajeroBancoIdAndActivoLikePaginacion(String cajero, Banco BANCOID, String ACTIVO, Integer pageNumber, Integer rowForPage);
 public List<Cajero> findCajeroBancoIdAndActivoLike(String cajero, Banco BANCOID, String ACTIVO);
 
 public int countDireccionBancoIdAndActivoLike(String DIRECCION,Banco BANCOID, String ACTIVO);
 public List<Cajero> findDireccionBancoIdAndActivoLikePaginacion(String DIRECCION, Banco BANCOID, String ACTIVO, Integer pageNumber, Integer rowForPage);
 
 public List<Cajero> findDireccionBancoIdAndActivoLike(String DIRECCION, Banco BANCOID, String ACTIVO);
}
