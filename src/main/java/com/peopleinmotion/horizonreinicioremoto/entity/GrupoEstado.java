/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.peopleinmotion.horizonreinicioremoto.entity;


import java.io.Serializable;
import java.math.BigInteger;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author avbravo
 */
@Entity
@Table(name = "GRUPOESTADO")   
@NamedQueries({
    @NamedQuery(name = "GrupoEstado.findAll", query = "SELECT g FROM GrupoEstado g"),
    @NamedQuery(name = "GrupoEstado.findByGrupoEstadoId", query = "SELECT g FROM GrupoEstado g WHERE g.GRUPOESTADOID = :GRUPOESTADOID"),
    @NamedQuery(name = "GrupoEstado.findByRazon", query = "SELECT g FROM GrupoEstado g WHERE g.GRUPOESTADO = :GRUPOESTADO"),
    @NamedQuery(name = "GrupoEstado.findByAtivo", query = "SELECT g FROM GrupoEstado g WHERE g.ACTIVO = :ACTIVO"),
    @NamedQuery(name = "GrupoEstado.findByOrden", query = "SELECT g FROM GrupoEstado g WHERE g.ORDEN = :ORDEN")
    })
public class GrupoEstado implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @SequenceGenerator(name = "GRUPOESTADO_GEN", sequenceName = "GRUPOESTADO_SEQ",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GRUPOESTADO_GEN")
    @NotNull
    @Column(name = "GRUPOESTADOID")
    private BigInteger GRUPOESTADOID;
    @Size(max = 100)
    @Column(name = "GRUPOESTADO")
    private String GRUPOESTADO;
    @Basic(optional = false)
    @NotNull     
    @Column(name = "ACTIVO")
    private String ACTIVO;
    @Column(name = "ORDEN")
    private BigInteger ORDEN;
    
     @Transient
     private BigInteger TOTAL;
    
    @OneToMany(fetch=FetchType.LAZY,cascade = CascadeType.ALL, mappedBy = "GRUPOESTADOID")
    private Collection<Estado> ESTADOCollection;
    


    public GrupoEstado() {
    }

    public GrupoEstado(BigInteger GRUPOESTADOID) {
        this.GRUPOESTADOID =GRUPOESTADOID;
    }

    public Collection<Estado> getESTADOCollection() {
        return ESTADOCollection;
    }

    public void setESTADOCollection(Collection<Estado> ESTADOCollection) {
        this.ESTADOCollection = ESTADOCollection;
    }

    public BigInteger getTOTAL() {
        return TOTAL;
    }

    public void setTOTAL(BigInteger TOTAL) {
        this.TOTAL = TOTAL;
    }

    
    
    
    
    public BigInteger getGRUPOESTADOID() {
        return GRUPOESTADOID;
    }

    public void setGRUPOESTADOID(BigInteger GRUPOESTADOID) {
        this.GRUPOESTADOID = GRUPOESTADOID;
    }

    public String getGRUPOESTADO() {
        return GRUPOESTADO;
    }

    public void setGRUPOESTADO(String GRUPOESTADO) {
        this.GRUPOESTADO = GRUPOESTADO;
    }

    public String getACTIVO() {
        return ACTIVO;
    }

    public void setACTIVO(String ACTIVO) {
        this.ACTIVO = ACTIVO;
    }

    public BigInteger getORDEN() {
        return ORDEN;
    }

    public void setORDEN(BigInteger ORDEN) {
        this.ORDEN = ORDEN;
    }

    

  
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (GRUPOESTADOID != null ? GRUPOESTADOID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GrupoEstado)) {
            return false;
        }
        GrupoEstado other = (GrupoEstado) object;
        if ((this.GRUPOESTADOID == null && other.GRUPOESTADOID != null) || (this.GRUPOESTADOID != null && !this.GRUPOESTADOID.equals(other.GRUPOESTADOID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "GrupoEstado{" + "GRUPOESTADOID=" + GRUPOESTADOID + ", GRUPOESTADO=" + GRUPOESTADO + ", ACTIVO=" + ACTIVO + ", ORDEN=" + ORDEN + ", TOTAL=" + TOTAL + ", ESTADOCollection=" + ESTADOCollection + '}';
    }

   
    
}
