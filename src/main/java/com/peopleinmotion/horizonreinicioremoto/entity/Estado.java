/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.peopleinmotion.horizonreinicioremoto.entity;


import java.io.Serializable;
import java.math.BigInteger;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author avbravo
 */
@Entity
@Table(name = "ESTADO") 
@NamedQueries({
    @NamedQuery(name = "Estado.findAll", query = "SELECT e FROM Estado e"),
    @NamedQuery(name = "Estado.findByEstadoId", query = "SELECT e FROM Estado e WHERE e.ESTADOID = :ESTADOID"),
    @NamedQuery(name = "Estado.findByEstado", query = "SELECT e FROM Estado e WHERE e.ESTADO = :ESTADO"),
    @NamedQuery(name = "Estado.findByActivo", query = "SELECT e FROM Estado e WHERE e.ACTIVO = :ACTIVO"),
    @NamedQuery(name = "Estado.findByPredeterminado", query = "SELECT e FROM Estado e WHERE e.PREDETERMINADO = :PREDETERMINADO"),
    @NamedQuery(name = "Estado.findByOrden", query = "SELECT e FROM Estado e WHERE e.ORDEN = :ORDEN")
    })
public class Estado implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @SequenceGenerator(name = "ESTADO_GEN", sequenceName = "ESTADO_SEQ",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ESTADO_GEN")
    @NotNull
    @Column(name = "ESTADOID")
    private BigInteger ESTADOID;
    @Size(max = 100)
    @Column(name = "ESTADO")
    private String ESTADO;
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
    
    @JoinColumn(name = "GRUPOESTADOID", referencedColumnName = "GRUPOESTADOID")
    @ManyToOne(optional = false)
    private GrupoEstado GRUPOESTADOID;
    


    public Estado() {
    }

    public Estado(BigInteger ESTADOID) {
        this.ESTADOID = ESTADOID;
    }

    public String getPREDETERMINADO() {
        return PREDETERMINADO;
    }

    public void setPREDETERMINADO(String PREDETERMINADO) {
        this.PREDETERMINADO = PREDETERMINADO;
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

    public GrupoEstado getGRUPOESTADOID() {
        return GRUPOESTADOID;
    }

    public void setGRUPOESTADOID(GrupoEstado GRUPOESTADOID) {
        this.GRUPOESTADOID = GRUPOESTADOID;
    }

    
   
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ESTADOID != null ? ESTADOID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Estado)) {
            return false;
        }
        Estado other = (Estado) object;
        if ((this.ESTADOID == null && other.ESTADOID != null) || (this.ESTADOID != null && !this.ESTADOID.equals(other.ESTADOID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Estado{" + "ESTADOID=" + ESTADOID + ", ESTADO=" + ESTADO + ", ACTIVO=" + ACTIVO + ", PREDETERMINADO=" + PREDETERMINADO + ", ORDEN=" + ORDEN + ", GRUPOESTADOID=" + GRUPOESTADOID + '}';
    }

   
    
}
