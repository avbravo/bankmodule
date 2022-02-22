/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.peopleinmotion.horizonreinicioremoto.entity;

import java.io.Serializable;
import java.math.BigInteger;
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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author avbravo
 */
@Entity
@Table(name = "Cajero")
@NamedQueries({
    @NamedQuery(name = "Cajero.findAll", query = "SELECT c FROM Cajero c"),
    @NamedQuery(name = "Cajero.findByCajeroId", query = "SELECT c FROM Cajero c WHERE c.CAJEROID = :CAJEROID"),
    @NamedQuery(name = "Cajero.findByCajero", query = "SELECT c FROM Cajero c WHERE c.CAJERO = :CAJERO"),
    @NamedQuery(name = "Cajero.findByActivo", query = "SELECT c FROM Cajero c WHERE c.ACTIVO = :ACTIVO"),
    @NamedQuery(name = "Cajero.findByDescripcion", query = "SELECT c FROM Cajero c WHERE c.DESCRIPCION = :DESCRIPCION"),
    @NamedQuery(name = "Cajero.findByDireccion", query = "SELECT c FROM Cajero c WHERE c.DIRECCION= :DIRECCION"),
    @NamedQuery(name = "Cajero.findByDireccioncorta", query = "SELECT c FROM Cajero c WHERE c.DIRECCIONCORTA = :DIRECCIONCORTA"),
    @NamedQuery(name = "Cajero.findByOrden", query = "SELECT c FROM Cajero c WHERE c.ORDEN = :ORDEN")

})
@Cacheable(false)
public class Cajero implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @SequenceGenerator(name = "CAJERO_GEN", sequenceName = "CAJERO_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CAJERO_GEN")
    @NotNull
    @Column(name = "CAJEROID")
    private BigInteger CAJEROID;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "CAJERO")
    private String CAJERO;
    @Basic(optional = false)
    @NotNull

    @Column(name = "ACTIVO")
    private String ACTIVO;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "DESCRIPCION")
    private String DESCRIPCION;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "DIRECCION")
    private String DIRECCION;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "DIRECCIONCORTA")
    private String DIRECCIONCORTA;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ORDEN")
    private BigInteger ORDEN;

    @JoinColumn(name = "BANCOID", referencedColumnName = "BANCOID")
    @ManyToOne(optional = false)
    private Banco BANCOID;

    public Cajero() {
    }

    public Cajero(BigInteger CAJEROID) {
        this.CAJEROID = CAJEROID;
    }

    public BigInteger getCAJEROID() {
        return CAJEROID;
    }

    public void setCAJEROID(BigInteger CAJEROID) {
        this.CAJEROID = CAJEROID;
    }

    public String getCAJERO() {
        return CAJERO;
    }

    public void setCAJERO(String CAJERO) {
        this.CAJERO = CAJERO;
    }

    public String getACTIVO() {
        return ACTIVO;
    }

    public void setACTIVO(String ACTIVO) {
        this.ACTIVO = ACTIVO;
    }

    public String getDESCRIPCION() {
        return DESCRIPCION;
    }

    public void setDESCRIPCION(String DESCRIPCION) {
        this.DESCRIPCION = DESCRIPCION;
    }

    public String getDIRECCION() {
        return DIRECCION;
    }

    public void setDIRECCION(String DIRECCION) {
        this.DIRECCION = DIRECCION;
    }

    public String getDIRECCIONCORTA() {
        return DIRECCIONCORTA;
    }

    public void setDIRECCIONCORTA(String DIRECCIONCORTA) {
        this.DIRECCIONCORTA = DIRECCIONCORTA;
    }

    public BigInteger getORDEN() {
        return ORDEN;
    }

    public void setORDEN(BigInteger ORDEN) {
        this.ORDEN = ORDEN;
    }

    public Banco getBANCOID() {
        return BANCOID;
    }

    public void setBANCOID(Banco BANCOID) {
        this.BANCOID = BANCOID;
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
        if (!(object instanceof Cajero)) {
            return false;
        }
        Cajero other = (Cajero) object;
        if ((this.CAJEROID == null && other.CAJEROID != null) || (this.CAJEROID != null && !this.CAJEROID.equals(other.CAJEROID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Cajero{" + "CAJEROID=" + CAJEROID + ", CAJERO=" + CAJERO + ", ACTIVO=" + ACTIVO + ", DESCRIPCION=" + DESCRIPCION + ", DIRECCION=" + DIRECCION + ", DIRECCIONCORTA=" + DIRECCIONCORTA + ", ORDEN=" + ORDEN + ", BANCOID=" + BANCOID + '}';
    }



    public String toJSON() {

        StringBuilder sb = new StringBuilder();
        sb.append("{");
        sb.append("\n  \"CAJEROID\":\"").append(CAJEROID).append("\"");
        sb.append("\n, \"CAJERO\":\"").append(CAJERO).append("\"");
        sb.append("\n, \"ACTIVO\":\"").append(ACTIVO).append("\"");
        sb.append("\n, \"DESCRIPCION\":\"").append(DESCRIPCION).append("\"");
        sb.append("\n, \"DIRECCION\":\"").append(DIRECCION).append("\"");
        sb.append("\n, \"DIRECCIONCORTA\":\"").append(DIRECCIONCORTA).append("\"");
        sb.append("\n, \"ORDEN\":\"").append(ORDEN).append("\"");
        sb.append("\n, \"BANCOID\":").append(BANCOID.toJSON());
        sb.append("\n}");
        return sb.toString();
    }

}
