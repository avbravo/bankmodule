/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.peopleinmotion.horizonreinicioremoto.domains;

import com.peopleinmotion.horizonreinicioremoto.entity.Banco;
import java.math.BigInteger;

/**
 *
 * @author avbravo
 */
public class TotalesEstadoBanco {

    private Banco Banco;
    private BigInteger totalSolicitado;
    private BigInteger totalFinalizado;
    private BigInteger totalEnProceso;
    private BigInteger totalNoSePuedeEjecutar;

    public TotalesEstadoBanco() {
    }

    public TotalesEstadoBanco(Banco Banco, BigInteger totalSolicitado, BigInteger totalFinalizado, BigInteger totalEnProceso, BigInteger totalNoSePuedeEjecutar) {
        this.Banco = Banco;
        this.totalSolicitado = totalSolicitado;
        this.totalFinalizado = totalFinalizado;
        this.totalEnProceso = totalEnProceso;
        this.totalNoSePuedeEjecutar = totalNoSePuedeEjecutar;
    }

    public Banco getBanco() {
        return Banco;
    }

    public void setBanco(Banco Banco) {
        this.Banco = Banco;
    }

    public BigInteger getTotalSolicitado() {
        return totalSolicitado;
    }

    public void setTotalSolicitado(BigInteger totalSolicitado) {
        this.totalSolicitado = totalSolicitado;
    }

    public BigInteger getTotalFinalizado() {
        return totalFinalizado;
    }

    public void setTotalFinalizado(BigInteger totalFinalizado) {
        this.totalFinalizado = totalFinalizado;
    }

    public BigInteger getTotalEnProceso() {
        return totalEnProceso;
    }

    public void setTotalEnProceso(BigInteger totalEnProceso) {
        this.totalEnProceso = totalEnProceso;
    }

    public BigInteger getTotalNoSePuedeEjecutar() {
        return totalNoSePuedeEjecutar;
    }

    public void setTotalNoSePuedeEjecutar(BigInteger totalNoSePuedeEjecutar) {
        this.totalNoSePuedeEjecutar = totalNoSePuedeEjecutar;
    }

    
    
    
    

}
