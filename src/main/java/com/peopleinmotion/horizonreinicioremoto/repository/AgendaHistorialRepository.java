/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.peopleinmotion.horizonreinicioremoto.repository;

import com.peopleinmotion.horizonreinicioremoto.entity.AgendaHistorial;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author avbravo
 */
public interface AgendaHistorialRepository {
    public List<AgendaHistorial> findAll();
    public Optional<AgendaHistorial> find(BigInteger id);
    public Optional<AgendaHistorial> findByAgendaHistorialId(BigInteger AGENDAHISTORIALID);
    public Boolean create(AgendaHistorial agendaHistorial);
 
    
}
