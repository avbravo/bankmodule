/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.peopleinmotion.horizonreinicioremoto.entity;

import com.peopleinmotion.horizonreinicioremoto.paginator.QuerySQL;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
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
@Table(name = "HISTORIAL")
@NamedQueries({
    @NamedQuery(name = "Historial.findAll", query = "SELECT h FROM Historial h"),
    @NamedQuery(name = "Historial.findByHistorialId", query = "SELECT h FROM Historial h WHERE h.HISTORIALID = :HISTORIALID"),
    @NamedQuery(name = "Historial.findByTabla", query = "SELECT h FROM Historial h WHERE h.TABLA = :TABLA"),
    @NamedQuery(name = "Historial.findByModulo", query = "SELECT h FROM Historial h WHERE h.MODULO = :MODULO"),
    @NamedQuery(name = "Historial.findByEvento", query = "SELECT h FROM Historial h WHERE h.EVENTO = :EVENTO"),
    @NamedQuery(name = "Historial.findByUsuarioId", query = "SELECT h FROM Historial h WHERE h.USUARIOID = :USUARIOID"),
    @NamedQuery(name = "Historial.findByFecha", query = "SELECT h FROM Historial h WHERE h.FECHA= :FECHA")})
public class Historial implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @SequenceGenerator(name = "HISTORIAL_GEN", sequenceName = "HISTORIAL_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "HISTORIAL_GEN")
    @NotNull
    @Column(name = "HISTORIALID")
    private BigInteger HISTORIALID;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "TABLA")
    private String TABLA;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "MODULO")
    private String MODULO;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "EVENTO")
    private String EVENTO;
    @Lob
    @Column(name = "CONTENIDO")
    private String CONTENIDO;
    @Basic(optional = false)
    @NotNull
    @Column(name = "USUARIOID")
    private BigInteger USUARIOID;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date FECHA;

    public Historial() {
    }

    public Historial(String TABLA, String MODULO, String EVENTO, String CONTENIDO, BigInteger USUARIOID, Date FECHA) {
        this.TABLA = TABLA;
        this.MODULO = MODULO;
        this.EVENTO = EVENTO;
        this.CONTENIDO = CONTENIDO;
        this.USUARIOID = USUARIOID;
        this.FECHA = FECHA;
    }

    public Historial(BigInteger HISTORIALID) {
        this.HISTORIALID = HISTORIALID;
    }

    public BigInteger getHISTORIALID() {
        return HISTORIALID;
    }

    public void setHISTORIALID(BigInteger HISTORIALID) {
        this.HISTORIALID = HISTORIALID;
    }

    public String getTABLA() {
        return TABLA;
    }

    public void setTABLA(String TABLA) {
        this.TABLA = TABLA;
    }

    public String getMODULO() {
        return MODULO;
    }

    public void setMODULO(String MODULO) {
        this.MODULO = MODULO;
    }

    public String getEVENTO() {
        return EVENTO;
    }

    public void setEVENTO(String EVENTO) {
        this.EVENTO = EVENTO;
    }

    public String getCONTENIDO() {
        return CONTENIDO;
    }

    public void setCONTENIDO(String CONTENIDO) {
        this.CONTENIDO = CONTENIDO;
    }

    public BigInteger getUSUARIOID() {
        return USUARIOID;
    }

    public void setUSUARIOID(BigInteger USUARIOID) {
        this.USUARIOID = USUARIOID;
    }

    public Date getFECHA() {
        return FECHA;
    }

    public void setFECHA(Date FECHA) {
        this.FECHA = FECHA;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (HISTORIALID != null ? HISTORIALID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Historial)) {
            return false;
        }
        Historial other = (Historial) object;
        if ((this.HISTORIALID == null && other.HISTORIALID != null) || (this.HISTORIALID != null && !this.HISTORIALID.equals(other.HISTORIALID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Historial{HISTORIALID=").append(HISTORIALID);
        sb.append(", TABLA=").append(TABLA);
        sb.append(", MODULO=").append(MODULO);
        sb.append(", EVENTO=").append(EVENTO);
        sb.append(", CONTENIDO=").append(CONTENIDO);
        sb.append(", USUARIOID=").append(USUARIOID);
        sb.append(", FECHA=").append(FECHA);
        sb.append('}');
        return sb.toString();
    }
    
    

    public String toJSON() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        sb.append("\n  \"HISTORIALID\":\"").append(HISTORIALID).append("\"");
        sb.append("\n, \"TABLA\":\"").append(TABLA).append("\"");
        sb.append("\n, \"MODULO\":\"").append(MODULO).append("\"");
        sb.append("\n, \"EVENTO\":\"").append(EVENTO).append("\"");
        sb.append("\n, \"CONTENIDO\":\"").append(CONTENIDO).append("\"");
        sb.append("\n, \" USUARIOID\":\"").append(USUARIOID).append("\"");
        sb.append("\n, \"FECHA\":\"").append(FECHA).append("\"");
        sb.append("\n}");
        return sb.toString();
    }

 
    
    
    
    public static class Builder {

        private String TABLA;

        private String MODULO;

        private String EVENTO;

        private String CONTENIDO;

        private BigInteger USUARIOID;
        private Date FECHA;

        public Builder TABLA(String TABLA) {
            this.TABLA = TABLA;
            return this;
        }

        public Builder MODULO(String MODULO) {
            this.MODULO = MODULO;
            return this;
        }

        public Builder EVENTO(String EVENTO) {
            this.EVENTO = EVENTO;
            return this;
        }
        public Builder CONTENIDO(String CONTENIDO) {
            this.CONTENIDO = CONTENIDO;
            return this;
        }

        public Builder USUARIOID(BigInteger USUARIOID) {
            this.USUARIOID = USUARIOID;
            return this;
        }

        public Builder FECHA(Date FECHA) {
            this.FECHA = FECHA;
            return this;
        }

        public Historial build() {
            return new Historial(TABLA, MODULO, EVENTO, CONTENIDO, USUARIOID, FECHA);

        }

    }
}
