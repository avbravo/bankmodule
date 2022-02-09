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
import com.peopleinmotion.horizonreinicioremoto.repository.AgendaRepository;
import com.peopleinmotion.horizonreinicioremoto.utils.DateUtil;
import com.peopleinmotion.horizonreinicioremoto.utils.JsfUtil;
import java.math.BigInteger;
import java.util.Date;
import java.util.Optional;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author avbravo
 */
@Stateless
public class AgendaServicesImpl implements AgendaServices {
// <editor-fold defaultstate="collapsed" desc="inject() ">

    @Inject
    AgendaRepository agendaRepository;

// </editor-fold>
    @Override
    public Optional<Agenda> create(Cajero cajero, Usuario usuario, Estado estado, Accion accion, Date fechaAgendada, Date fechaEjecucion) {
        try {
            Agenda agenda = new Agenda();
            agenda.setACTIVO("SI");
            agenda.setCODIGOTRANSACCION(JsfUtil.generateUniqueID());
            agenda.setCAJEROID(cajero.getCAJEROID());
            agenda.setCAJERO(cajero.getCAJERO());
            agenda.setBANCOID(cajero.getBANCOID().getBANCOID());
            agenda.setESTADOID(estado.getESTADOID());
            agenda.setACCIONID(accion.getACCIONID());
            agenda.setFECHA(DateUtil.getFechaHoraActual());
            agenda.setFECHAAGENDADA(fechaAgendada);
            agenda.setFECHAEJECUCION(fechaEjecucion);
            agenda.setUSUARIOIDATIENDE(JsfUtil.toBigInteger(0));
            agenda.setUSUARIOIDSOLICITA(usuario.getUSUARIOID());
            if (agendaRepository.create(agenda)) {

                Optional<Agenda> agendaOptional = agendaRepository.findByCodigoTransaccion(agenda.getCODIGOTRANSACCION());
                if (!agendaOptional.isPresent()) {
               //     JsfUtil.warningMessage("No se encontro la agenda con ese codigo de transaccion");

                } else {
                    return agendaOptional;
                }
            }
        } catch (Exception e) {
            JsfUtil.errorMessage(JsfUtil.nameOfClass() + "."+ JsfUtil.nameOfMethod() + " "+e.getLocalizedMessage());
        }
        return Optional.empty();
    }

    @Override
    public int countAgendamiento(BigInteger BANCOID, BigInteger CAJEROID, BigInteger ACCIONID, BigInteger ESTADOID, Date FECHAAGENDADA, String ACTIVO) {
          return agendaRepository.countAgendamiento(BANCOID, CAJEROID,ACCIONID,  ESTADOID, FECHAAGENDADA , ACTIVO);
    }

}
