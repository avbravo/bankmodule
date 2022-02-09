/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.peopleinmotion.horizonreinicioremoto.services;

import com.peopleinmotion.horizonreinicioremoto.entity.Accion;
import com.peopleinmotion.horizonreinicioremoto.entity.Agenda;
import com.peopleinmotion.horizonreinicioremoto.entity.Cajero;
import com.peopleinmotion.horizonreinicioremoto.entity.Estado;
import com.peopleinmotion.horizonreinicioremoto.entity.Usuario;
import java.math.BigInteger;
import java.util.Date;
import java.util.Optional;

/**
 *
 * @author avbravo
 */
public interface AgendaServices {
    public Optional<Agenda> create(Cajero cajero, Usuario usuario, Estado estado, Accion accion, Date fechaAgendada, Date fechaBaja);
    
public int countAgendamiento(BigInteger BANCOID,BigInteger CAJEROID,BigInteger ACCIONID, BigInteger ESTADOID,Date FECHAAGENDADA , String ACTIVO);
}
