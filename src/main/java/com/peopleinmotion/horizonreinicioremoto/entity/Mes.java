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
@Table(name = "MES")
@NamedQueries({
    @NamedQuery(name = "Mes.findAll", query = "SELECT m FROM Mes m"),
    @NamedQuery(name = "Mes.findByMesId", query = "SELECT m FROM Mes m WHERE m.MESID = :MESID"),
    @NamedQuery(name = "Mes.findByMes", query = "SELECT m FROM Mes m WHERE m.MES = :MES"),
    @NamedQuery(name = "Mes.findByActivo", query = "SELECT m FROM Mes m WHERE m.ACTIVO = :ACTIVO"),
    @NamedQuery(name = "Mes.findByOrden", query = "SELECT m FROM Mes m WHERE m.ORDEN = :ORDEN")
    })
public class Mes implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @SequenceGenerator(name = "MES_GEN", sequenceName = "MES_SEQ",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MES_GEN")
    @Column(name = "MESID") 
    private BigInteger MESID;
    @Size(max = 100)
    @Column(name = "MES")
    private String MES;
    @Basic(optional = false)
    @NotNull
     
    @Column(name = "ACTIVO")
    private String ACTIVO;
    @Column(name = "ORDEN")
    private BigInteger ORDEN;
    
    public Mes() {
    }

    public Mes(BigInteger MESID) {
        this.MESID = MESID;
    }

    public BigInteger getMESID() {
        return MESID;
    }

    public void setMESID(BigInteger MESID) {
        this.MESID = MESID;
    }

    public String getMES() {
        return MES;
    }

    public void setMES(String MES) {
        this.MES = MES;
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
        hash += (MESID != null ? MESID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Mes)) {
            return false;
        }
        Mes other = (Mes) object;
        if ((this.MESID == null && other.MESID != null) || (this.MESID != null && !this.MESID.equals(other.MESID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Mes{" + "MESID=" + MESID + ", MES=" + MES + ", ACTIVO=" + ACTIVO + ", ORDEN=" + ORDEN + '}';
    }


}
