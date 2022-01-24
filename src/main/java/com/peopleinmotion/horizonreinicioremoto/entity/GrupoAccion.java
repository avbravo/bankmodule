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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author avbravo
 */
@Entity
@Table(name = "GRUPOACCION")   
@NamedQueries({
    @NamedQuery(name = "GrupoAccion.findAll", query = "SELECT g FROM GrupoAccion g"),
    @NamedQuery(name = "GrupoAccion.findByGrupoAcconId", query = "SELECT g FROM GrupoAccion g WHERE g.GRUPOACCIONID = :GRUPOACCIONID"),
    @NamedQuery(name = "GrupoAccion.findByRazon", query = "SELECT g FROM GrupoAccion g WHERE g.GRUPOACCION = :GRUPOACCION"),
    @NamedQuery(name = "GrupoAccion.findByHabilitado", query = "SELECT g FROM GrupoAccion g WHERE g.ACTIVO = :ACTIVO"),
    @NamedQuery(name = "GrupoAccion.findByOrden", query = "SELECT g FROM GrupoAccion g WHERE g.ORDEN = :ORDEN")
    })
public class GrupoAccion implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @SequenceGenerator(name = "GRUPOACCION_GEN", sequenceName = "GRUPOACCION_SEQ",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GRUPOACCION_GEN")
    @NotNull
    @Column(name = "GRUPOACCIONID")
    private BigInteger GRUPOACCIONID;
    @Size(max = 100)
    @Column(name = "GRUPOACCION")
    private String GRUPOACCION;
    @Basic(optional = false)
    @NotNull
     
    @Column(name = "ACTIVO")
    private String ACTIVO;
    @Column(name = "ORDEN")
    private BigInteger ORDEN;
    
    @OneToMany(fetch=FetchType.LAZY,cascade = CascadeType.ALL, mappedBy = "GRUPOACCIONID")
    private Collection<Accion> ACCIONCollection;
    
    public GrupoAccion() {
    }

    public GrupoAccion(BigInteger GRUPOACCIONID) {
        this.GRUPOACCIONID = GRUPOACCIONID;
    }

    public BigInteger getGRUPOACCIONID() {
        return GRUPOACCIONID;
    }

    public void setGRUPOACCIONID(BigInteger GRUPOACCIONID) {
        this.GRUPOACCIONID = GRUPOACCIONID;
    }

    public String getGRUPOACCION() {
        return GRUPOACCION;
    }

    public void setGRUPOACCION(String GRUPOACCION) {
        this.GRUPOACCION = GRUPOACCION;
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

    public Collection<Accion> getACCIONCollection() {
        return ACCIONCollection;
    }

    public void setACCIONCollection(Collection<Accion> ACCIONCollection) {
        this.ACCIONCollection = ACCIONCollection;
    }

    

   
    
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (GRUPOACCIONID != null ? GRUPOACCIONID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GrupoAccion)) {
            return false;
        }
        GrupoAccion other = (GrupoAccion) object;
        if ((this.GRUPOACCIONID == null && other.GRUPOACCIONID != null) || (this.GRUPOACCIONID != null && !this.GRUPOACCIONID.equals(other.GRUPOACCIONID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "GrupoAccion{" + "GRUPOACCIONID=" + GRUPOACCIONID + ", GRUPOACCION=" + GRUPOACCION + ", ACTIVO=" + ACTIVO + ", ORDEN=" + ORDEN + ", ACCIONCollection=" + ACCIONCollection + '}';
    }

    
    
}
