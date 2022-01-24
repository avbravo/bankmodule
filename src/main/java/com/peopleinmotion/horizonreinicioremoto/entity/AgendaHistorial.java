/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.peopleinmotion.horizonreinicioremoto.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author avbravo
 */
@Entity
@Table(name = "AGENDAHISTORIAL")   
@NamedQueries({ 
    @NamedQuery(name = "AgendaHistorial.findAll", query = "SELECT a FROM AgendaHistorial a"),
    @NamedQuery(name = "AgendaHistorial.findByAgendaHistorialId", query = "SELECT a FROM AgendaHistorial a WHERE a.AGENDAHISTORIALID = :AGENDAHISTORIALID"),
    @NamedQuery(name = "AgendaHistorial.findByAgendaId", query = "SELECT a FROM AgendaHistorial a WHERE a.AGENDAID = :AGENDAID"),
    @NamedQuery(name = "AgendaHistorial.findByCajeroId", query = "SELECT a FROM AgendaHistorial a WHERE a.CAJEROID = :CAJEROID"),
    @NamedQuery(name = "AgendaHistorial.findByActivo", query = "SELECT a FROM AgendaHistorial a WHERE a.ACTIVO = :ACTIVO"),
    @NamedQuery(name = "AgendaHistorial.findByEstadoId", query = "SELECT a FROM AgendaHistorial a WHERE a.ESTADOID = :ESTADOID"),
    @NamedQuery(name = "AgendaHistorial.findByAccionId", query = "SELECT a FROM AgendaHistorial a WHERE a.ACCIONID = :ACCIONID")    
    })
public class AgendaHistorial implements Serializable {

    private static final long serialVersionUID = 1L;
    
        @Id
    @SequenceGenerator(name = "AGENDAHISTORIAL_GEN", sequenceName = "AGENDAHISTORIAL_SEQ",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "AGENDAHISTORIAL_GEN")
    @NotNull
    @Column(name = "AGENDAHISTORIALID")
    private BigInteger AGENDAHISTORIALID;
        
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
        
    @Basic(optional = false)
    @NotNull     
    @Column(name = "ACTIVO")
    private String ACTIVO;
        
     @Basic(optional = false)
    @NotNull     
    @Column(name = "EVENTOOCURRIDO")
    private String EVENTOOCURRIDO;
     

          @NotNull
    @Column(name = "FECHAEVENTO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date FECHAEVENTO; 
          
        @Basic(optional = false) 
        @NotNull
    @Column(name = "USUARIOEVENTO")
    private BigInteger USUARIOEVENTO;   


    public AgendaHistorial() {
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

    public String getEVENTOOCURRIDO() {
        return EVENTOOCURRIDO;
    }

    public void setEVENTOOCURRIDO(String EVENTOOCURRIDO) {
        this.EVENTOOCURRIDO = EVENTOOCURRIDO;
    }

    

    public Date getFECHAEVENTO() {
        return FECHAEVENTO;
    }

    public void setFECHAEVENTO(Date FECHAEVENTO) {
        this.FECHAEVENTO = FECHAEVENTO;
    }

    public BigInteger getUSUARIOEVENTO() {
        return USUARIOEVENTO;
    }

    public void setUSUARIOEVENTO(BigInteger USUARIOEVENTO) {
        this.USUARIOEVENTO = USUARIOEVENTO;
    }
    
    
    
    
    
    public AgendaHistorial(BigInteger AGENDAHISTORIALID) {
        this.AGENDAHISTORIALID = AGENDAHISTORIALID;
    }

    public BigInteger getAGENDAHISTORIALID() {
        return AGENDAHISTORIALID;
    }

    public void setAGENDAHISTORIALID(BigInteger AGENDAHISTORIALID) {
        this.AGENDAHISTORIALID = AGENDAHISTORIALID;
    }

    
    
    
    public BigInteger getAGENDAID() {
        return AGENDAID;
    }

    public void setAGENDAID(BigInteger AGENDAID) {
        this.AGENDAID = AGENDAID;
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

    public Date getFECHA() {
        return FECHA;
    }

    public void setFECHA(Date FECHA) {
        this.FECHA = FECHA;
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

    public String getACTIVO() {
        return ACTIVO;
    }

    public void setACTIVO(String ACTIVO) {
        this.ACTIVO = ACTIVO;
    }

    
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (CAJEROID != null ? CAJEROID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AgendaHistorial)) {
            return false;
        }
        AgendaHistorial other = (AgendaHistorial) object;
        if ((this.CAJEROID == null && other.CAJEROID != null) || (this.CAJEROID != null && !this.CAJEROID.equals(other.CAJEROID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "AgendaHistorial{" + "AGENDAHISTORIALID=" + AGENDAHISTORIALID + ", AGENDAID=" + AGENDAID + ", BANCOID=" + BANCOID + ", CAJERO=" + CAJERO + ", CAJEROID=" + CAJEROID + ", ESTADOID=" + ESTADOID + ", ACCIONID=" + ACCIONID + ", FECHA=" + FECHA + ", FECHAAGENDADA=" + FECHAAGENDADA + ", FECHAEJECUCION=" + FECHAEJECUCION + ", USUARIOIDSOLICITA=" + USUARIOIDSOLICITA + ", USUARIOIDATIENDE=" + USUARIOIDATIENDE + ", ACTIVO=" + ACTIVO + ", EVENTOOCURRIDO=" + EVENTOOCURRIDO + ", FECHAEVENTO=" + FECHAEVENTO + ", USUARIOEVENTO=" + USUARIOEVENTO + '}';
    }

  
    
}
