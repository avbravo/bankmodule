/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.peopleinmotion.horizonreinicioremoto.entity;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 *
 * @author avbravo
 */
@Entity
@Table(name = "AGENDA")  
@NamedQueries({
    @NamedQuery(name = "Agenda.findAll", query = "SELECT a FROM Agenda a"),
    @NamedQuery(name = "Agenda.findByAgendaId", query = "SELECT a FROM Agenda a WHERE a.AGENDAID = :AGENDAID"),
    @NamedQuery(name = "Agenda.findByCodigoTransaccion", query = "SELECT a FROM Agenda a WHERE a.CODIGOTRANSACCION = :CODIGOTRANSACCION")
    
    })
public class Agenda implements Serializable {

    private static final long serialVersionUID = 1L;

  
    @Id
    @SequenceGenerator(name = "AGENDA_GEN", sequenceName = "AGENDA_SEQ",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "AGENDA_GEN")
    @NotNull
    @Column(name = "AGENDAID")
    private BigInteger AGENDAID;
     @NotNull
    @Column(name = "BANCOID")
    private BigInteger BANCOID;
     
    @NotNull
    @Column(name = "CAJERO")
    private String CAJERO;  
    @NotNull
    @Column(name = "CAJEROID")
    private BigInteger CAJEROID;
    @NotNull
    @Column(name = "ESTADOID")
    private BigInteger ESTADOID;
    @NotNull
    @Column(name = "ACCIONID")
    private BigInteger ACCIONID;
   
   
    @NotNull
    @Column(name = "FECHA")
   @Temporal(TemporalType.TIMESTAMP)
    private Date FECHA;    
        
    @NotNull
    @Column(name = "FECHAAGENDADA")
  @Temporal(TemporalType.TIMESTAMP)
    private Date FECHAAGENDADA; 
    
    @NotNull
    @Column(name = "FECHAEJECUCION")
   @Temporal(TemporalType.TIMESTAMP)
    private Date FECHAEJECUCION; 
    
    @NotNull
    @Column(name = "USUARIOIDSOLICITA")
    private BigInteger USUARIOIDSOLICITA;    
    
    @NotNull
    @Column(name = "USUARIOIDATIENDE")
    private BigInteger USUARIOIDATIENDE;    
        

    @NotNull
    @Column(name = "CODIGOTRANSACCION ")
    private String CODIGOTRANSACCION ;
    @NotNull
    @Column(name = "ACTIVO")
    private String ACTIVO;
        
    

    public Agenda() {
    }

    public BigInteger getBANCOID() {
        return BANCOID;
    }

    public void setBANCOID(BigInteger BANCOID) {
        this.BANCOID = BANCOID;
    }

    public String getCAJERO() {
        return CAJERO;
    }

    public void setCAJERO(String CAJERO) {
        this.CAJERO = CAJERO;
    }

    public Date getFECHAEJECUCION() {
        return FECHAEJECUCION;
    }

    public void setFECHAEJECUCION(Date FECHAEJECUCION) {
        this.FECHAEJECUCION = FECHAEJECUCION;
    }
    
    
    

    public BigInteger getCAJEROID() {
        return CAJEROID;
    }

    public void setCAJEROID(BigInteger CAJEROID) {
        this.CAJEROID = CAJEROID;
    }

    public BigInteger getESTADOID() {
        return ESTADOID;
    }

    public void setESTADOID(BigInteger ESTADOID) {
        this.ESTADOID = ESTADOID;
    }

    public BigInteger getACCIONID() {
        return ACCIONID;
    }

    public void setACCIONID(BigInteger ACCIONID) {
        this.ACCIONID = ACCIONID;
    }

    public Date getFECHAAGENDADA() {
        return FECHAAGENDADA;
    }

    public void setFECHAAGENDADA(Date FECHAAGENDADA) {
        this.FECHAAGENDADA = FECHAAGENDADA;
    }

    public BigInteger getUSUARIOIDSOLICITA() {
        return USUARIOIDSOLICITA;
    }

    public void setUSUARIOIDSOLICITA(BigInteger USUARIOIDSOLICITA) {
        this.USUARIOIDSOLICITA = USUARIOIDSOLICITA;
    }

    public BigInteger getUSUARIOIDATIENDE() {
        return USUARIOIDATIENDE;
    }

    public void setUSUARIOIDATIENDE(BigInteger USUARIOIDATIENDE) {
        this.USUARIOIDATIENDE = USUARIOIDATIENDE;
    }



    public BigInteger getAGENDAID() {
        return AGENDAID;
    }

    public void setAGENDAID(BigInteger AGENDAID) {
        this.AGENDAID = AGENDAID;
    }

    public String getCODIGOTRANSACCION() {
        return CODIGOTRANSACCION;
    }

    public void setCODIGOTRANSACCION(String CODIGOTRANSACCION) {
        this.CODIGOTRANSACCION = CODIGOTRANSACCION;
    }
    
    
    
    
//    public Agenda(BigInteger AGENDAID) {
//        this.AGENDAID= AGENDAID;
//    }
//
//    public BigInteger getAGENDAID() {
//        return AGENDAID;
//    }
//
//    public void setAGENDAID(BigInteger AGENDAID) {
//        this.AGENDAID = AGENDAID;
//    }

//    public BigInteger getCAJEROID() {
//        return CAJEROID;
//    }
//
//    public void setCAJEROID(BigInteger CAJEROID) {
//        this.CAJEROID = CAJEROID;
//    }
//
//    public BigInteger getESTADOID() {
//        return ESTADOID;
//    }
//
//    public void setESTADOID(BigInteger ESTADOID) {
//        this.ESTADOID = ESTADOID;
//    }
//
//    public BigInteger getACCIONID() {
//        return ACCIONID;
//    }
//
//    public void setACCIONID(BigInteger ACCIONID) {
//        this.ACCIONID = ACCIONID;
//    }

    public Date getFECHA() {
        return FECHA;
    }

    public void setFECHA(Date FECHA) {
        this.FECHA = FECHA;
    }

//    public Date getFECHAAGENDADA() {
//        return FECHAAGENDADA;
//    }
//
//    public void setFECHAAGENDADA(Date FECHAAGENDADA) {
//        this.FECHAAGENDADA = FECHAAGENDADA;
//    }
//
//    public BigInteger getUSUARIOIDSOLICITA() {
//        return USUARIOIDSOLICITA;
//    }
//
//    public void setUSUARIOIDSOLICITA(BigInteger USUARIOIDSOLICITA) {
//        this.USUARIOIDSOLICITA = USUARIOIDSOLICITA;
//    }
//
//    public BigInteger getUSUARIOIDATIENDE() {
//        return USUARIOIDATIENDE;
//    }
//
//    public void setUSUARIOIDATIENDE(BigInteger USUARIOIDATIENDE) {
//        this.USUARIOIDATIENDE = USUARIOIDATIENDE;
//    }

    public String getACTIVO() {
        return ACTIVO;
    }

    public void setACTIVO(String ACTIVO) {
        this.ACTIVO = ACTIVO;
    }

    
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (AGENDAID != null ? AGENDAID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Agenda)) {
            return false;
        }
        Agenda other = (Agenda) object;
        if ((this.AGENDAID == null && other.AGENDAID != null) || (this.AGENDAID != null && !this.AGENDAID.equals(other.AGENDAID))) {
            return false;
        }
        return true;
    }

    
    
//    @Override
//    public String toString() {
//        return " Cajero[ CAJEROID=" + CAJEROID + " ]";
//    }
//    

//    @Override
//    public String toString() {
//        return "Agenda{" + "AGENDAID=" + AGENDAID + ", CAJEROID=" + CAJEROID + ", ESTADOID=" + ESTADOID + ", ACCIONID=" + ACCIONID + ", FECHA=" + FECHA + ", FECHAAGENDADA=" + FECHAAGENDADA + ", USUARIOIDSOLICITA=" + USUARIOIDSOLICITA + ", USUARIOIDATIENDE=" + USUARIOIDATIENDE + ", ACTIVO=" + ACTIVO + '}';
//    }

    @Override
    public String toString() {
        return "Agenda{" + "AGENDAID=" + AGENDAID + ", BANCOID=" + BANCOID + ", CAJERO=" + CAJERO + ", CAJEROID=" + CAJEROID + ", ESTADOID=" + ESTADOID + ", ACCIONID=" + ACCIONID + ", FECHA=" + FECHA + ", FECHAAGENDADA=" + FECHAAGENDADA + ", FECHAEJECUCION=" + FECHAEJECUCION + ", USUARIOIDSOLICITA=" + USUARIOIDSOLICITA + ", USUARIOIDATIENDE=" + USUARIOIDATIENDE + ", CODIGOTRANSACCION=" + CODIGOTRANSACCION + ", ACTIVO=" + ACTIVO + '}';
    }
}
 