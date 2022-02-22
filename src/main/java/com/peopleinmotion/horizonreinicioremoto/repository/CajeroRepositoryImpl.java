/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.peopleinmotion.horizonreinicioremoto.repository;

import com.peopleinmotion.horizonreinicioremoto.entity.Banco;
import com.peopleinmotion.horizonreinicioremoto.entity.Cajero;
import com.peopleinmotion.horizonreinicioremoto.facade.CajeroFacade;
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
public class CajeroRepositoryImpl implements CajeroRepository {
    @Inject
    CajeroFacade cajeroFacade;

    @Override
    public List<Cajero> findAll() {
       return cajeroFacade.findAll();
    }

    
     @Override
    public Optional<Cajero> findByCajeroId(BigInteger ACCIONID) {
        return cajeroFacade.findByCajeroId(ACCIONID);
    }
    
    @Override
   public List<Cajero> findByBancoId(Banco BANCOID) {
       return cajeroFacade.findByBancoId(BANCOID);
    }
@Override
    public List<Cajero> findByCajeroIdAndActivo(BigInteger ACCIONID, String ACTIVO) {
        return cajeroFacade.findByCajeroIdAndActivo(ACCIONID,ACTIVO);
    }
    
   
    @Override
    public Boolean create(Cajero cajero) {
        try {
            cajeroFacade.create(cajero);
            return true;
        } catch (Exception e) {
            // System.out.println("create() "+e.getLocalizedMessage());
        }
        return false;
    }

    @Override
    public List<Cajero> findByBancoIdAndActivo(Banco banco, String ACTIVO) {
        return cajeroFacade.findByBancoIdAndActivo(banco, ACTIVO);
    }

    @Override
    public List<Cajero> findByCajeroIdBancoIdAndActivo(Banco BANCOID, String CAJERO, String ACTIVO) {
        return cajeroFacade.findByCajeroIdBancoIdAndActivo(BANCOID,  CAJERO, ACTIVO);
        
    }

    @Override
    public List<Cajero> findByDireccionCortaBancoIdAndActivo(Banco BANCOID, String DIRECCIONCORTA, String ACTIVO) {
      return findByDireccionCortaBancoIdAndActivo(BANCOID, DIRECCIONCORTA,ACTIVO);
    }

    @Override
    public Optional<Cajero> find(BigInteger id) {
       return cajeroFacade.find(id);
    }

// <editor-fold defaultstate="collapsed" desc="Boolean changed(Cajero cajero)>

    @Override
    public Boolean changed(Cajero cajero) {
        try {
            
            Optional<Cajero> live = cajeroFacade.find(cajero.getCAJEROID());
            if (!live.isPresent()) {
                return Boolean.TRUE;
            }
            String jsonLive = live.get().toJSON();

            String json =cajero.toJSON();

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
    public List<Cajero> sql(QuerySQL querySQL) {
       return cajeroFacade.sql(querySQL);
    }

    @Override
    public List<Cajero> pagination(QuerySQL querySQL, Integer pageNumber, Integer rowForPage) {
     return cajeroFacade.pagination(querySQL, pageNumber, rowForPage);
    }

    @Override
    public int count(QuerySQL querySQL) {
       return cajeroFacade.count(querySQL);
    }

  
    
    
    
}