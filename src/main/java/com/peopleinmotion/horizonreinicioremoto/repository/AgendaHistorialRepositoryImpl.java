/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.peopleinmotion.horizonreinicioremoto.repository;

import com.peopleinmotion.horizonreinicioremoto.entity.AgendaHistorial;
import com.peopleinmotion.horizonreinicioremoto.facade.AgendaHistorialFacade;
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
public class AgendaHistorialRepositoryImpl implements AgendaHistorialRepository {

    @Inject
    AgendaHistorialFacade agendaHistorialFacade;

    @Override
    public List<AgendaHistorial> findAll() {
        return agendaHistorialFacade.findAll();
    }

 

   

    @Override
    public Boolean create(AgendaHistorial agendaHistorial) {
        try {
            agendaHistorialFacade.create(agendaHistorial);
            return true;
        } catch (Exception e) {
            // System.out.println("create() " + e.getLocalizedMessage());
        }
        return false;
    }

    @Override
    public Optional<AgendaHistorial> findByAgendaHistorialId(BigInteger AGENDAHISTORIALID) {
        return agendaHistorialFacade.findByAgendaHistorialId(AGENDAHISTORIALID);
    }

    @Override
    public Optional<AgendaHistorial> find(BigInteger id) {
        return agendaHistorialFacade.find(id);
    }

    

}
