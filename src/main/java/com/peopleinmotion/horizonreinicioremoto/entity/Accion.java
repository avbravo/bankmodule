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
import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
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
@Table(name = "ACCION")  

@NamedQueries({ 
    @NamedQuery(name = "Accion.findAll", query = "SELECT a FROM Accion a"),
    @NamedQuery(name = "Accion.findByAccionId", query = "SELECT a FROM Accion a WHERE a.ACCIONID = :ACCIONID"),
    @NamedQuery(name = "Accion.findByAccion", query = "SELECT a FROM Accion a WHERE a.ACCION = :ACCION"),
    @NamedQuery(name = "Accion.findByActivo", query = "SELECT a FROM Accion a WHERE a.ACTIVO = :ACTIVO"),
    @NamedQuery(name = "Accion.findByOrden", query = "SELECT a FROM Accion a WHERE a.ORDEN = :ORDEN")
    })
@Cacheable(false)
public class Accion implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @SequenceGenerator(name = "ACCION_GEN", sequenceName = "ACCION_SEQ" ,allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ACCION_GEN")
    @NotNull
    @Column(name = "ACCIONID")
    private BigInteger ACCIONID;
    @Size(max = 100)
    @Column(name = "ACCION")
    private String ACCION;
    @Basic(optional = false)
    @NotNull
    @Size(min = 2, max = 2)
    @Column(name = "ACTIVO")      
    private String ACTIVO;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 2, max = 2)
    @Column(name = "PREDETERMINADO")      
    private String PREDETERMINADO;
    
    
    @Column(name = "ORDEN")
    private BigInteger ORDEN;
    
     @JoinColumn(name = "GRUPOACCIONID", referencedColumnName = "GRUPOACCIONID")
    @ManyToOne(optional = false)
    private GrupoAccion GRUPOACCIONID;

    public Accion() {
    }

    public Accion(BigInteger ACCIONID) {
        this.ACCIONID = ACCIONID;
    }

    public BigInteger getACCIONID() {
        return ACCIONID;
    }

    public void setACCIONID(BigInteger ACCIONID) {
        this.ACCIONID = ACCIONID;
    }

    public String getACCION() {
        return ACCION;
    }

    public void setACCION(String ACCION) {
        this.ACCION = ACCION;
    }

    public String getACTIVO() {
        return ACTIVO;
    }

    public void setACTIVO(String ACTIVO) {
        this.ACTIVO = ACTIVO;
    }

    public String getPREDETERMINADO() {
        return PREDETERMINADO;
    }

    public void setPREDETERMINADO(String PREDETERMINADO) {
        this.PREDETERMINADO = PREDETERMINADO;
    }

    public BigInteger getORDEN() {
        return ORDEN;
    }

    public void setORDEN(BigInteger ORDEN) {
        this.ORDEN = ORDEN;
    }

    public GrupoAccion getGRUPOACCIONID() {
        return GRUPOACCIONID;
    }

    public void setGRUPOACCIONID(GrupoAccion GRUPOACCIONID) {
        this.GRUPOACCIONID = GRUPOACCIONID;
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
        if (!(object instanceof Accion)) {
            return false;
        }
        Accion other = (Accion) object;
        if ((this.ACCIONID == null && other.ACCIONID != null) || (this.ACCIONID != null && !this.ACCIONID.equals(other.ACCIONID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Accion{" + "ACCIONID=" + ACCIONID + ", ACCION=" + ACCION 
                + ", ACTIVO=" + ACTIVO + ", PREDETERMINADO=" + PREDETERMINADO + ", ORDEN=" + ORDEN + ", GRUPOACCIONID=" + GRUPOACCIONID + '}';
    }

    
    
    
    
      public String toJSON() {
    
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        sb.append("\n  \"ACCIONID\":\"").append(ACCIONID).append("\"");
        sb.append("\n, \"ACCION\":\"").append(ACCION).append("\"");
        sb.append("\n, \"ACTIVO\":\"").append(ACTIVO).append("\"");
        sb.append("\n, \"PREDETERMINADO\":\"").append(PREDETERMINADO).append("\"");
        sb.append("\n, \"ORDEN\":\"").append(ORDEN).append("\"");
         sb.append("\n, \"GRUPOACCIONID\":").append(GRUPOACCIONID.toJSON());
        sb.append("\n}");
        return sb.toString();
    }
    
}
