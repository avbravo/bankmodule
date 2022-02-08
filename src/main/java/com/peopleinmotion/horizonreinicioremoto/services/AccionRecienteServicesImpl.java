/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.peopleinmotion.horizonreinicioremoto.services;

import com.peopleinmotion.horizonreinicioremoto.entity.Accion;
import com.peopleinmotion.horizonreinicioremoto.entity.AccionReciente;
import com.peopleinmotion.horizonreinicioremoto.entity.Agenda;
import com.peopleinmotion.horizonreinicioremoto.entity.Banco;
import com.peopleinmotion.horizonreinicioremoto.entity.Cajero;
import com.peopleinmotion.horizonreinicioremoto.entity.Estado;
import com.peopleinmotion.horizonreinicioremoto.entity.GrupoAccion;
import com.peopleinmotion.horizonreinicioremoto.repository.AccionRecienteRepository;
import com.peopleinmotion.horizonreinicioremoto.utils.JsfUtil;
import java.util.Optional;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author avbravo
 */
@Stateless
public class AccionRecienteServicesImpl implements AccionRecienteServices {

    // <editor-fold defaultstate="collapsed" desc="@Inkect ">
    @Inject
    AccionRecienteRepository accionRecienteRepository;

// </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="AccionReciente create(Agenda agenda, Banco banco, Cajero cajero, Accion accion, GrupoAccion grupoAccion, Estado estado,String autorizado) ">
    @Override
    public AccionReciente create(Agenda agenda, Banco banco, Cajero cajero, Accion accion, GrupoAccion grupoAccion, Estado estado, String autorizado) {
        AccionReciente accionReciente = new AccionReciente();
        try {

            accionReciente.setACCIONID(agenda.getACCIONID());
            accionReciente.setACTIVO("SI");
            accionReciente.setAUTORIZADO(autorizado);
            accionReciente.setAGENDAID(agenda.getAGENDAID());
            accionReciente.setESTADO(estado.getESTADO());
            accionReciente.setESTADOID(estado.getESTADOID());
            accionReciente.setBANCOID(banco.getBANCOID());
            accionReciente.setCAJEROID(cajero.getCAJEROID());
            accionReciente.setCAJERO(cajero.getCAJERO());
            accionReciente.setMENSAJE(accion.getACCION());
            accionReciente.setTITULO(grupoAccion.getGRUPOACCION());
            accionReciente.setFECHA(agenda.getFECHA());
            accionReciente.setFECHAAGENDADA(agenda.getFECHAAGENDADA());
            accionReciente.setFECHAEJECUCION(agenda.getFECHAEJECUCION());
            accionReciente.setVISTOBANCO("NO");
            accionReciente.setVISTOTECNICO("NO");
            if (accionRecienteRepository.create(accionReciente)) {
                //  return Boolean.TRUE;
            } else {
                JsfUtil.warningMessage("No se pudo guardar la acción reciente...");
            }

        } catch (Exception e) {
            JsfUtil.errorMessage(JsfUtil.nameOfMethod() + " " + e.getLocalizedMessage());
        }
        return accionReciente;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Boolean renderedByEstadoSolicitado(AccionReciente accionReciente) ">
    @Override
    public Boolean renderedByEstadoSolicitado(AccionReciente accionReciente) {
        try {
            if (JsfUtil.contextToBigInteger("grupoEstadoSolicitadoId").equals(accionReciente.getESTADOID())) {
                return Boolean.TRUE;
            }
            return Boolean.FALSE;
        } catch (Exception e) {
            JsfUtil.errorMessage(JsfUtil.nameOfMethod() + " " + e.getLocalizedMessage());
        }
        return Boolean.FALSE;
    }
// </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Boolean renderedByEstadoFinalizado(AccionReciente accionReciente) ">
    @Override
    public Boolean renderedByEstadoFinalizado(AccionReciente accionReciente) {
        try {
            if (JsfUtil.contextToBigInteger("grupoEstadoFinalizadoId").equals(accionReciente.getESTADOID())) {
                return Boolean.TRUE;
            }
            return Boolean.FALSE;
        } catch (Exception e) {
            JsfUtil.errorMessage(JsfUtil.nameOfMethod() + " " + e.getLocalizedMessage());
        }
        return Boolean.FALSE;
    }
// </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Boolean renderedByEstadoEnProceso(AccionReciente accionReciente) ">
    @Override
    public Boolean renderedByEstadoEnProceso(AccionReciente accionReciente) {
        try {
            if (JsfUtil.contextToBigInteger("grupoEstadoEnprocesoId").equals(accionReciente.getESTADOID())) {
                return Boolean.TRUE;
            }
            return Boolean.FALSE;
        } catch (Exception e) {
            JsfUtil.errorMessage(JsfUtil.nameOfMethod() + " " + e.getLocalizedMessage());
        }
        return Boolean.FALSE;
    }
// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Boolean change(AccionReciente accionReciente">
    @Override
    public Boolean changed(AccionReciente accionReciente) {

        try {
            /**
             *
             * Se usa un objeto JSON PARA COMPARAR
             *
             */
            Optional<AccionReciente> live = accionRecienteRepository.findByAccionRecienteId(accionReciente.getACCIONRECIENTEID());
            if (!live.isPresent()) {
//No se encontro el registro                
                return Boolean.TRUE;
            }
            String jsonAccionRecienteLive = live.get().toJSON();

            String jsonAccionReciente = accionReciente.toJSON();

            if (!jsonAccionReciente.equals(jsonAccionRecienteLive)) {
                //Otro usuario lo cambio mientras se estaba procesando
                return Boolean.TRUE;

            }
        } catch (Exception e) {
            JsfUtil.errorMessage(JsfUtil.nameOfMethod() + " " + e.getLocalizedMessage());
        }
        return Boolean.FALSE;

    }

    // </editor-fold>
}
