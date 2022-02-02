/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.peopleinmotion.horizonreinicioremoto.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Builder;

/**
 *
 * @author avbravo
 */
@Builder
@Entity
@Table(name = "ACCIONRECIENTE")
@NamedQueries({
    @NamedQuery(name = "AccionReciente.findAll", query = "SELECT a FROM AccionReciente a"),
    @NamedQuery(name = "AccionReciente.findByAccionRecienteId", query = "SELECT a FROM AccionReciente a WHERE a.ACCIONRECIENTEID = :ACCIONRECIENTEID"),
    @NamedQuery(name = "AccionReciente.findByAccionId", query = "SELECT a FROM AccionReciente a WHERE a.ACCIONID = :ACCIONID"),
    @NamedQuery(name = "AccionReciente.findByCajeroId", query = "SELECT a FROM AccionReciente a WHERE a.CAJEROID = :CAJEROID"),
    @NamedQuery(name = "AccionReciente.findByBancoId", query = "SELECT a FROM AccionReciente a WHERE a.BANCOID = :BANCOID ORDER BY a.AGENDAID DESC"),
    @NamedQuery(name = "AccionReciente.findByActivo", query = "SELECT a FROM AccionReciente a WHERE a.ACTIVO = :ACTIVO"),
    @NamedQuery(name = "AccionReciente.findByAgendaId", query = "SELECT a FROM AccionReciente a WHERE a.AGENDAID = :AGENDAID")
})
public class AccionReciente implements Serializable { 

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @SequenceGenerator(name = "ACCIONRECIENTE_GEN", sequenceName = "ACCIONRECIENTE_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ACCIONRECIENTE_GEN")
    @NotNull
    @Column(name = "ACCIONRECIENTEID")
    private BigInteger ACCIONRECIENTEID;
   @NotNull
    @Column(name = "ACCIONID")
    private BigInteger ACCIONID;
    @NotNull
    @Column(name = "ESTADOID")
    private BigInteger ESTADOID;
    @NotNull
    @Column(name = "ESTADO")
    private String ESTADO;
    
    
    @NotNull
    @Column(name = "BANCOID")
    private BigInteger BANCOID;
    @NotNull
    @Column(name = "CAJEROID")
    private BigInteger CAJEROID;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "CAJERO")
    private String CAJERO;
    @NotNull
    @Column(name = "AGENDAID")
    private BigInteger AGENDAID;

    @Size(max = 100)
    @Column(name = "TITULO")
    private String TITULO;
    @Size(max = 200)
    @Column(name = "MENSAJE")
    private String MENSAJE;

    @Basic(optional = false)
    @NotNull
    @Size(min = 2, max = 2)
    @Column(name = "ACTIVO")
    private String ACTIVO;
    @Basic(optional = false)
    @NotNull
    @Size(min = 2, max = 2)
    @Column(name = "VISTOBANCO")
    private String VISTOBANCO;
    @Basic(optional = false)
    @NotNull
    @Size(min = 2, max = 2)
    @Column(name = "VISTOTECNICO")
    private String VISTOTECNICO;
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

    public AccionReciente() {
    }

    public AccionReciente(BigInteger ACCIONRECIENTEID, BigInteger ACCIONID, BigInteger ESTADOID, String ESTADO, BigInteger BANCOID, BigInteger CAJEROID, String CAJERO, BigInteger AGENDAID, String TITULO, String MENSAJE, String ACTIVO, String VISTOBANCO, String VISTOTECNICO, Date FECHA, Date FECHAAGENDADA, Date FECHAEJECUCION) {
        this.ACCIONRECIENTEID = ACCIONRECIENTEID;
        this.ACCIONID = ACCIONID;
        this.ESTADOID = ESTADOID;
        this.ESTADO = ESTADO;
        this.BANCOID = BANCOID;
        this.CAJEROID = CAJEROID;
        this.CAJERO = CAJERO;
        this.AGENDAID = AGENDAID;
        this.TITULO = TITULO;
        this.MENSAJE = MENSAJE;
        this.ACTIVO = ACTIVO;
        this.VISTOBANCO = VISTOBANCO;
        this.VISTOTECNICO = VISTOTECNICO;
        this.FECHA = FECHA;
        this.FECHAAGENDADA = FECHAAGENDADA;
        this.FECHAEJECUCION = FECHAEJECUCION;
    }

    
    
    
    
    public Date getFECHAEJECUCION() {
        return FECHAEJECUCION;
    }

    public void setFECHAEJECUCION(Date FECHAEJECUCION) {
        this.FECHAEJECUCION = FECHAEJECUCION;
    }

    
    
    
    public AccionReciente(BigInteger ACCIONID) {
        this.ACCIONID = ACCIONID;
    }

    public BigInteger getESTADOID() {
        return ESTADOID;
    }

    public void setESTADOID(BigInteger ESTADOID) {
        this.ESTADOID = ESTADOID;
    }

    public String getESTADO() {
        return ESTADO;
    }

    public void setESTADO(String ESTADO) {
        this.ESTADO = ESTADO;
    }

    
    
    
    
    public String getCAJERO() {
        return CAJERO;
    }

    public void setCAJERO(String CAJERO) {
        this.CAJERO = CAJERO;
    }

    public BigInteger getACCIONRECIENTEID() {
        return ACCIONRECIENTEID;
    }

    public void setACCIONRECIENTEID(BigInteger ACCIONRECIENTEID) {
        this.ACCIONRECIENTEID = ACCIONRECIENTEID;
    }

    public BigInteger getACCIONID() {
        return ACCIONID;
    }

    public void setACCIONID(BigInteger ACCIONID) {
        this.ACCIONID = ACCIONID;
    }

    public BigInteger getBANCOID() {
        return BANCOID;
    }

    public void setBANCOID(BigInteger BANCOID) {
        this.BANCOID = BANCOID;
    }

    public BigInteger getCAJEROID() {
        return CAJEROID;
    }

    public void setCAJEROID(BigInteger CAJEROID) {
        this.CAJEROID = CAJEROID;
    }

    public BigInteger getAGENDAID() {
        return AGENDAID;
    }

    public void setAGENDAID(BigInteger AGENDAID) {
        this.AGENDAID = AGENDAID;
    }

    public String getTITULO() {
        return TITULO;
    }

    public void setTITULO(String TITULO) {
        this.TITULO = TITULO;
    }

    public String getMENSAJE() {
        return MENSAJE;
    }

    public void setMENSAJE(String MENSAJE) {
        this.MENSAJE = MENSAJE;
    }

    public String getACTIVO() {
        return ACTIVO;
    }

    public void setACTIVO(String ACTIVO) {
        this.ACTIVO = ACTIVO;
    }

    public String getVISTOBANCO() {
        return VISTOBANCO;
    }

    public void setVISTOBANCO(String VISTOBANCO) {
        this.VISTOBANCO = VISTOBANCO;
    }

    public String getVISTOTECNICO() {
        return VISTOTECNICO;
    }

    public void setVISTOTECNICO(String VISTOTECNICO) {
        this.VISTOTECNICO = VISTOTECNICO;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ACCIONID != null ? ACCIONID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AccionReciente)) {
            return false;
        }
        AccionReciente other = (AccionReciente) object;
        if ((this.ACCIONID == null && other.ACCIONID != null) || (this.ACCIONID != null && !this.ACCIONID.equals(other.ACCIONID))) {
            return false;
        }
        return true;
    }

//    @Override
//    public String toString() {
//        return " Accion[ ACCIONID=" + ACCIONID + " ]";
//    }

    @Override
    public String toString() {
        return "AccionReciente{" + "ACCIONRECIENTEID=" + ACCIONRECIENTEID + ", ACCIONID=" + ACCIONID + ", ESTADOID=" + ESTADOID + ", ESTADO=" + ESTADO + ", BANCOID=" + BANCOID + ", CAJEROID=" + CAJEROID + ", CAJERO=" + CAJERO + ", AGENDAID=" + AGENDAID + ", TITULO=" + TITULO + ", MENSAJE=" + MENSAJE + ", ACTIVO=" + ACTIVO + ", VISTOBANCO=" + VISTOBANCO + ", VISTOTECNICO=" + VISTOTECNICO + ", FECHA=" + FECHA + ", FECHAAGENDADA=" + FECHAAGENDADA + ", FECHAEJECUCION=" + FECHAEJECUCION + '}';
    }
  
    public String toJSON() {
        return "{" + "\"ACCIONRECIENTEID\":\"" + ACCIONRECIENTEID + "\", \" ACCIONID\":\"" + ACCIONID + "\", \"ESTADOID\":\"" + ESTADOID + "\", \" ESTADO\":\"" + ESTADO + "\", \"BANCOID\":\"" + BANCOID + "\", \" CAJEROID=" + CAJEROID + "\", \"CAJERO\":\"" + CAJERO + "\", \" AGENDAID\":\"" + AGENDAID + "\", \" TITULO\":\"" + TITULO + "\", \" MENSAJE\":\"" + MENSAJE + "\", \" ACTIVO\":\"" + ACTIVO + ", VISTOBANCO\":\"" + VISTOBANCO + "\", \"VISTOTECNICO\":\"" + VISTOTECNICO + "\", \" FECHA\":\"" + FECHA + "\", \" FECHAAGENDADA\":\"" + FECHAAGENDADA + "\", \"FECHAEJECUCION=\":\"" + FECHAEJECUCION + "\"}";
    }

  
   

  

    
    
}
