/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.peopleinmotion.horizonreinicioremoto.services;

import com.peopleinmotion.horizonreinicioremoto.entity.AccionReciente;
import com.peopleinmotion.horizonreinicioremoto.entity.Banco;
import com.peopleinmotion.horizonreinicioremoto.entity.GrupoEstado;
import java.math.BigInteger;
import java.util.List;

/**
 *
 * @author avbravo
 */
public interface DashboardServices {
    public List<GrupoEstado> calcularTotalGrupoEstado(Banco banco);
    
    public BigInteger totalSolicitado(List<GrupoEstado> grupoEstadoList);
    public BigInteger totalEnProceso(List<GrupoEstado> grupoEstadoList);
    public BigInteger totalFinalizado(List<GrupoEstado> grupoEstadoList);
    public BigInteger totalNoSePuedeEjecutar(List<GrupoEstado> grupoEstadoList);
    
    public Boolean drawRowsAgendamiento(List<AccionReciente> accionRecienteList);
    
   public String onCommandButtonSelectAccionReciente(AccionReciente accionReciente, String formularioretorno);
   public String generarSiglas(AccionReciente accionReciente);
}
