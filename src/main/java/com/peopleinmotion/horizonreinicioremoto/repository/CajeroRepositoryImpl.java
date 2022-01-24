/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.peopleinmotion.horizonreinicioremoto.repository;

import com.peopleinmotion.horizonreinicioremoto.entity.Banco;
import com.peopleinmotion.horizonreinicioremoto.entity.Cajero;
import com.peopleinmotion.horizonreinicioremoto.facade.CajeroFacade;
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


  
    
    
    
}