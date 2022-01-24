/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.peopleinmotion.horizonreinicioremoto.services;

import com.peopleinmotion.horizonreinicioremoto.entity.Agenda;
import com.peopleinmotion.horizonreinicioremoto.entity.AgendaHistorial;
import com.peopleinmotion.horizonreinicioremoto.entity.Usuario;
import com.peopleinmotion.horizonreinicioremoto.repository.AgendaHistorialRepository;
import com.peopleinmotion.horizonreinicioremoto.utils.DateUtil;
import com.peopleinmotion.horizonreinicioremoto.utils.JsfUtil;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author avbravo
 */
@Stateless
public class AgendaHistorialServicesImpl implements AgendaHistorialServices {
// <editor-fold defaultstate="collapsed" desc="@Inject ">

    @Inject
    AgendaHistorialRepository agendaHistorialRepository;
// </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Boolean createHistorial(Agenda agenda, String EVENTOOCURRIDO, Usuario usuario)">

    @Override
    public Boolean createHistorial(Agenda agenda, String EVENTOOCURRIDO, Usuario usuario) {
        try {
            AgendaHistorial agendaHistorial = new AgendaHistorial();
            agendaHistorial.setACCIONID(agenda.getACCIONID());
            agendaHistorial.setACTIVO("SI");
            agendaHistorial.setAGENDAID(agenda.getAGENDAID());
            agendaHistorial.setCAJEROID(agenda.getCAJEROID());
            agendaHistorial.setCAJERO(agenda.getCAJERO());
            agendaHistorial.setBANCOID(agenda.getBANCOID());
            agendaHistorial.setESTADOID(agenda.getESTADOID());
            agendaHistorial.setFECHA(agenda.getFECHA());
            agendaHistorial.setFECHAAGENDADA(agenda.getFECHAAGENDADA());
            agendaHistorial.setFECHAEJECUCION(agenda.getFECHAAGENDADA());
            agendaHistorial.setUSUARIOIDATIENDE(agenda.getUSUARIOIDATIENDE());
            agendaHistorial.setUSUARIOIDSOLICITA(agenda.getUSUARIOIDSOLICITA());
            agendaHistorial.setUSUARIOEVENTO(usuario.getUSUARIOID());
            agendaHistorial.setFECHAEVENTO(DateUtil.fechaHoraActual());
            agendaHistorial.setEVENTOOCURRIDO(EVENTOOCURRIDO);
            if (agendaHistorialRepository.create(agendaHistorial)) {
                return Boolean.TRUE;
            }
            JsfUtil.warningMessage("No se guardo el historial de la agenda");

        } catch (Exception e) {
                JsfUtil.errorMessage(JsfUtil.nameOfMethod() + " " + e.getLocalizedMessage());
        }
        return Boolean.FALSE;
    }
    // </editor-fold>

}
