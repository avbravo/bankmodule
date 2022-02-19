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
@Table(name = "GrupoUsuario")
@NamedQueries({
    @NamedQuery(name = "GrupoUsuario.findAll", query = "SELECT g FROM GrupoUsuario g"),
    @NamedQuery(name = "GrupoUsuario.findByGrupoUsuarioId", query = "SELECT g FROM GrupoUsuario g WHERE g.GRUPOUSUARIOID = :GRUPOUSUARIOID"),
    @NamedQuery(name = "GrupoUsuario.findByActivo", query = "SELECT g FROM GrupoUsuario g WHERE g.ACTIVO = :ACTIVO")
    })
@Cacheable(false)
public class GrupoUsuario implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
     @Id
    @SequenceGenerator(name = "GRUPOUSUARIO_GEN", sequenceName = "GRUPOUSUARIO_SEQ",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GRUPOUSUARIO_GEN")
    @NotNull
    @Column(name = "GRUPOUSUARIOID")
    private BigInteger GRUPOUSUARIOID;
    @Basic(optional = false)
    @NotNull
     
    @Column(name = "ACTIVO")
    private String ACTIVO;
    
    @JoinColumn(name = "GRUPOID", referencedColumnName = "GRUPOID")
    @ManyToOne(optional = false)
    private Grupo GRUPOID;
    @JoinColumn(name = "USUARIOID", referencedColumnName = "USUARIOID")
    @ManyToOne(optional = false)
    private Usuario USUARIOID;

    public GrupoUsuario() {
    }

    public GrupoUsuario(BigInteger GRUPOUSUARIOID) {
        this.GRUPOUSUARIOID = GRUPOUSUARIOID;
    }

    public BigInteger getGRUPOUSUARIOID() {
        return GRUPOUSUARIOID;
    }

    public void setGRUPOUSUARIOID(BigInteger GRUPOUSUARIOID) {
        this.GRUPOUSUARIOID = GRUPOUSUARIOID;
    }

    public String getACTIVO() {
        return ACTIVO;
    }

    public void setACTIVO(String ACTIVO) {
        this.ACTIVO = ACTIVO;
    }

    public Grupo getGRUPOID() {
        return GRUPOID;
    }

    public void setGRUPOID(Grupo GRUPOID) {
        this.GRUPOID = GRUPOID;
    }

    public Usuario getUSUARIOID() {
        return USUARIOID;
    }

    public void setUSUARIOID(Usuario USUARIOID) {
        this.USUARIOID = USUARIOID;
    }

    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (GRUPOUSUARIOID != null ? GRUPOUSUARIOID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GrupoUsuario)) {
            return false;
        }
        GrupoUsuario other = (GrupoUsuario) object;
        if ((this.GRUPOUSUARIOID == null && other.GRUPOUSUARIOID != null) || (this.GRUPOUSUARIOID != null && !this.GRUPOUSUARIOID.equals(other.GRUPOUSUARIOID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "GrupoUsuario{" + "GRUPOUSUARIOID=" +
                GRUPOUSUARIOID + ", ACTIVO=" + ACTIVO + ", "
                + "GRUPOID=" + GRUPOID + ", USUARIOID=" +
                USUARIOID + '}';
    }
 public String toJSON() {
  
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        sb.append("\"GRUPOUSUARIOID\":\"").append(GRUPOUSUARIOID);
        sb.append("\", \"GRUPOID\":\"").append(GRUPOID.toJSON());
        sb.append("\", \"USUARIOID\":\"").append(USUARIOID.toJSON());
     
        sb.append("\"}");
        return sb.toString();
    }
  
    
}
