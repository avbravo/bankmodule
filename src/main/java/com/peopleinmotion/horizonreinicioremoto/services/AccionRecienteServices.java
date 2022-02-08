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
import java.util.Optional;

/**
 *
 * @author avbravo
 */
public interface AccionRecienteServices {
    public AccionReciente create(Agenda agenda, Banco banco, Cajero cajero, Accion accion, GrupoAccion grupoAccion, Estado estado, String autorizado);
    public Boolean renderedByEstadoSolicitado(AccionReciente accionReciente);
    public Boolean renderedByEstadoFinalizado(AccionReciente accionReciente);
    public Boolean renderedByEstadoEnProceso(AccionReciente accionReciente);
      
    public Boolean changed(AccionReciente accionReciente);
}
