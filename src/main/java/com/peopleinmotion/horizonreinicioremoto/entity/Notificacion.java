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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;

/**
 *
 * @author avbravo
 */
@Entity
@Table(name = "NOTIFICACION")
@NamedQueries({
    @NamedQuery(name = "Notificacion.findAll", query = "SELECT n FROM Notificacion n"),
    @NamedQuery(name = "Notificacion.findByNotificacionId", query = "SELECT n FROM Notificacion n WHERE n.NOTIFICACIONID = :NOTIFICACIONID"),
    @NamedQuery(name = "Notificacion.findById", query = "SELECT n FROM Notificacion n WHERE n.ID = :ID"),
    @NamedQuery(name = "Notificacion.findByTipoId", query = "SELECT n FROM Notificacion n WHERE n.TIPODID = :TIPODID")
    
})
@Data
@Cacheable(false)
public class Notificacion implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @SequenceGenerator(name = "NOTIFICACION_GEN", sequenceName = "NOTIFICACION_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "NOTIFICACION_GEN")
    @NotNull
    @Column(name = "NOTIFICACIONID")
    private BigInteger NOTIFICACIONID;
    @NotNull
    @Column(name = "ID")
    private BigInteger ID;
    
    @NotNull
    @Size(max = 100)
    @Column(name = "TIPODID")
    private String TIPODID;
    
    
    @Basic(optional = false)
    @NotNull
    @Size(max = 250)
    @Column(name = "TRANSACCION")
    private String TRANSACCION;
   
    public Notificacion() {
    }

    public Notificacion(BigInteger NOTIFICACIONID, BigInteger ID, String TIPODID, String TRANSACCION) {
        this.NOTIFICACIONID = NOTIFICACIONID;
        this.ID = ID;
        this.TIPODID = TIPODID;
        this.TRANSACCION = TRANSACCION;
    }

   
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (NOTIFICACIONID != null ? NOTIFICACIONID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Notificacion)) {
            return false;
        }
        Notificacion other = (Notificacion) object;
        if ((this.NOTIFICACIONID == null && other.NOTIFICACIONID != null) || (this.NOTIFICACIONID != null && !this.NOTIFICACIONID.equals(other.NOTIFICACIONID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Notificacion{" + "NOTIFICACIONID=" + NOTIFICACIONID + ", ID=" + ID + ", TIPODID=" + TIPODID + ", TRANSACCION=" + TRANSACCION + '}';
    }



    public String toJSON() {

        StringBuilder sb = new StringBuilder();
        sb.append("{");
        sb.append("\n  \"NOTIFICACIONID\":\"").append(NOTIFICACIONID).append("\"");
        sb.append("\n, \"ID\":\"").append(ID).append("\"");
        sb.append("\n, \"TIPODID\":\"").append(TIPODID).append("\"");
        sb.append("\n, \"TRANSACCION\":\"").append(TRANSACCION).append("\"");
        sb.append("\n}");
        return sb.toString();
    }

}
