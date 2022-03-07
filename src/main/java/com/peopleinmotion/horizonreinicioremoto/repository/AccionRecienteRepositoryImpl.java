/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.peopleinmotion.horizonreinicioremoto.repository;


import com.peopleinmotion.horizonreinicioremoto.entity.AccionReciente;
import com.peopleinmotion.horizonreinicioremoto.entity.Banco;
import com.peopleinmotion.horizonreinicioremoto.facade.AccionRecienteFacade;
import com.peopleinmotion.horizonreinicioremoto.paginator.QuerySQL;
import com.peopleinmotion.horizonreinicioremoto.utils.JsfUtil;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author avbravo
 */
@Stateless
public class AccionRecienteRepositoryImpl implements AccionRecienteRepository {

    @Inject
    AccionRecienteFacade accionRecienteFacade;

    @Override
    public List<AccionReciente> findAll() {
        return accionRecienteFacade.findAll();
    }

 

   

    @Override
    public Boolean create(AccionReciente accionReciente) {
        try {
            accionRecienteFacade.create(accionReciente);
            return true;
        } catch (Exception e) {
            // System.out.println("create() " + e.getLocalizedMessage());
        }
        return false;
    }
    @Override
    public Boolean update(AccionReciente accionReciente) {
        try {
            accionRecienteFacade.edit(accionReciente);
            return true;
        } catch (Exception e) {
            // System.out.println("create() " + e.getLocalizedMessage());
        }
        return false;
    }

    @Override
    public Optional<AccionReciente> findByAccionRecienteId(BigInteger ACCIONRECIENTEID) {
        return accionRecienteFacade.findByAccionRecienteId(ACCIONRECIENTEID);
    }

    @Override
    public List<AccionReciente> findByBancoId(BigInteger BANCOID) {
      return accionRecienteFacade.findByBancoId(BANCOID);
    }

    @Override
    public List<AccionReciente> findByCajeroId(BigInteger CAJEROID) {
      return accionRecienteFacade.findByCajeroId(CAJEROID);
    }

    @Override
    public List<AccionReciente> findByBancoIdAndCajeroId(BigInteger BANCOID, BigInteger CAJEROID) {
        return accionRecienteFacade.findByBancoIdAndCajeroId(BANCOID, CAJEROID) ;
        
    }

    @Override
    public List<AccionReciente> findByCajeroIdAndVistoBanco(BigInteger CAJEROID, String VISTOBANCO) {
        return accionRecienteFacade.findByCajeroIdAndVistoBanco( CAJEROID,  VISTOBANCO);
    }

    @Override
    public List<AccionReciente> findByCajeroIdAndVistoTecnico(BigInteger CAJEROID, String VISTOTECNICO) {
         return accionRecienteFacade.findByCajeroIdAndVistoTecnico(CAJEROID, VISTOTECNICO);
    }

    @Override
    public List<AccionReciente> findByBancoIdAndVistoBanco(BigInteger BANCOID, String VISTOBANCO) {
       return accionRecienteFacade.findByBancoIdAndVistoBanco(BANCOID,VISTOBANCO);
    }

    @Override
    public List<AccionReciente> findByBancoIdAndVistoTecnico(BigInteger BANCOID, String VISTOTENICO) {
       return accionRecienteFacade.findByBancoIdAndVistoTecnico(BANCOID,VISTOTENICO);
    }

    @Override
    public Optional<AccionReciente> findByBancoIdAndCajeroIdUltimaAccionReciente(BigInteger BANCOID, BigInteger CAJEROID) {
       return accionRecienteFacade.findByBancoIdAndCajeroIdUltimaAccionReciente(BANCOID, CAJEROID);
    }
    @Override
    public Optional<AccionReciente> findByBancoIdAndCajeroIdUltimaAccionDisponible(BigInteger BANCOID, BigInteger CAJEROID) {
       return accionRecienteFacade.findByBancoIdAndCajeroIdUltimaAccionDisponible(BANCOID, CAJEROID);
    }

    @Override
    public List<AccionReciente> findBancoIdEntreFechasTypeDate(BigInteger BANCOID, Date DESDE, Date HASTA, String ACTIVO) {
          return accionRecienteFacade.findBancoIdEntreFechasTypeDate(BANCOID,DESDE, HASTA,ACTIVO);
    }
  @Override
     public List<AccionReciente> findBancoIdEntreFechasTypeDateEstadoPendienteOProgreso(BigInteger BANCOID, Date DESDE, Date HASTA, String ACTIVO, BigInteger ESTADOIDPROCESANDO , BigInteger ESTADOIDEJECUTADA){
         return accionRecienteFacade.findBancoIdEntreFechasTypeDateEstadoPendienteOProgreso(BANCOID, DESDE, HASTA, ACTIVO, ESTADOIDPROCESANDO, ESTADOIDEJECUTADA);
     }
     @Override
    public List<AccionReciente> findBancoIdEntreFechasTypeLocalDate(BigInteger BANCOID, LocalDateTime DESDE, LocalDateTime HASTA, String ACTIVO) {
         return accionRecienteFacade.findBancoIdEntreFechasTypeLocalDateTime(BANCOID,DESDE, HASTA,ACTIVO);
    }

    @Override
    public List<AccionReciente> findByBancoIdAndActivo(BigInteger BANCOID, String ACTIVO) {
      return accionRecienteFacade.findByBancoIdAndActivo(BANCOID, ACTIVO);
    }

    @Override
    public Optional<AccionReciente> find(BigInteger id) {
       return accionRecienteFacade.find(id);
    }

    // <editor-fold defaultstate="collapsed" desc="Boolean changed(Banco banco)>

    @Override
    public Boolean changed(AccionReciente accionReciente) {
        try {
            
            Optional<AccionReciente> live = accionRecienteFacade.find(accionReciente.getACCIONRECIENTEID());
            if (!live.isPresent()) {
                return Boolean.TRUE;
            }
            String jsonLive =live.get().toJSON() ;

            String json = accionReciente.toJSON();

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
    public List<AccionReciente> sql(QuerySQL querySQL) {
        return accionRecienteFacade.sql(querySQL);
    }
    @Override
    public List<AccionReciente> pagination(QuerySQL querySQL, Integer pageNumber, Integer rowForPage) {
        return accionRecienteFacade.pagination(querySQL, pageNumber, rowForPage);
    }

    @Override
    public int count(QuerySQL querySQL) {
       return accionRecienteFacade.count(querySQL);
    }

    @Override
    public int countBancoIdAndActivo(BigInteger BANCOID, String ACTIVO) {
       return accionRecienteFacade.countBancoIdAndActivo(BANCOID, ACTIVO);
    }

    @Override
    public List<AccionReciente> findBancoIdAndActivoPaginacion(BigInteger BANCOID, String ACTIVO, Integer pageNumber, Integer rowForPage) {
        return accionRecienteFacade.findBancoIdAndActivoPaginacion(BANCOID, ACTIVO, pageNumber, rowForPage);
    }

}
