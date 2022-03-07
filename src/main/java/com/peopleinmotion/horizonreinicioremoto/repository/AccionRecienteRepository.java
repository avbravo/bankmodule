/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.peopleinmotion.horizonreinicioremoto.repository;

import com.peopleinmotion.horizonreinicioremoto.entity.AccionReciente;
import com.peopleinmotion.horizonreinicioremoto.entity.Banco;
import com.peopleinmotion.horizonreinicioremoto.paginator.QuerySQL;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author avbravo
 */
public interface AccionRecienteRepository {
    public List<AccionReciente> findAll();
    public Optional<AccionReciente> find(BigInteger id);
    public Optional<AccionReciente> findByAccionRecienteId(BigInteger ACCIONRECIENTEID);
    public List<AccionReciente> findByBancoId(BigInteger BANCOID) ;
    public List<AccionReciente> findByCajeroId(BigInteger CAJEROID);
    public List<AccionReciente> findByBancoIdAndCajeroId(BigInteger BANCOID, BigInteger CAJEROID);
    public List<AccionReciente> findByCajeroIdAndVistoBanco( BigInteger CAJEROID, String VISTOBANCO);
    
    public List<AccionReciente> findByCajeroIdAndVistoTecnico( BigInteger CAJEROID, String VISTOTECNICO);
    public List<AccionReciente> findByBancoIdAndVistoBanco( BigInteger BANCOID, String VISTOBANCO);
    public List<AccionReciente> findByBancoIdAndVistoTecnico( BigInteger BANCOID, String VISTOTENICO);
    
            
    public Boolean create(AccionReciente accionReciente);
     public Boolean update(AccionReciente accionReciente);
    public Optional<AccionReciente> findByBancoIdAndCajeroIdUltimaAccionReciente(BigInteger BANCOID, BigInteger CAJEROID);
    public Optional<AccionReciente> findByBancoIdAndCajeroIdUltimaAccionDisponible(BigInteger BANCOID, BigInteger CAJEROID);
   public List<AccionReciente> findBancoIdEntreFechasTypeDate(BigInteger BANCOID, Date DESDE, Date HASTA, String ACTIVO);
    public List<AccionReciente> findBancoIdEntreFechasTypeDateEstadoPendienteOProgreso(BigInteger BANCOID, Date DESDE, Date HASTA, String ACTIVO, BigInteger ESTADOIDPROCESANDO , BigInteger ESTADOIDEJECUTADA);
    public List<AccionReciente> findBancoIdEntreFechasTypeLocalDate(BigInteger BANCOID, LocalDateTime DESDE, LocalDateTime HASTA, String ACTIVO);
    public List<AccionReciente> findByBancoIdAndActivo(BigInteger BANCOID, String ACTIVO);
     public Boolean changed(AccionReciente accionReciente);


    public List<AccionReciente> sql(QuerySQL querySQL);

    public List<AccionReciente> pagination(QuerySQL querySQL, Integer pageNumber, Integer rowForPage);

    public int count(QuerySQL querySQL);
    
    public int countBancoIdAndActivo(BigInteger BANCOID, String ACTIVO);
     public List<AccionReciente> findBancoIdAndActivoPaginacion(BigInteger BANCOID, String ACTIVO, Integer pageNumber, Integer rowForPage);
}
