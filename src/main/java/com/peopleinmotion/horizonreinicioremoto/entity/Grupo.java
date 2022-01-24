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
@Table(name = "GRUPO")
@NamedQueries({
    @NamedQuery(name = "Grupo.findAll", query = "SELECT g FROM Grupo g"),
    @NamedQuery(name = "Grupo.findByGrupoId", query = "SELECT g FROM Grupo g WHERE g.GRUPOID = :GRUPOID"),
    @NamedQuery(name = "Grupo.findByGrupo", query = "SELECT g FROM Grupo g WHERE g.GRUPO = :GRUPO"),
    @NamedQuery(name = "Grupo.findByActivo", query = "SELECT g FROM Grupo g WHERE g.ACTIVO = :ACTIVO"),
    @NamedQuery(name = "Grupo.findByDescripcion", query = "SELECT g FROM Grupo g WHERE g.DESCRIPCION = :DESCRIPCION"),
    @NamedQuery(name = "Grupo.findByOrden", query = "SELECT g FROM Grupo g WHERE g.ORDEN = :ORDEN")
    })
public class Grupo implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @SequenceGenerator(name = "GRUPO_GEN", sequenceName = "GRUPO_SEQ",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GRUPO_GEN")
    @NotNull
    @Column(name = "GRUPOID")
    private BigInteger GRUPOID;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "GRUPO")
    private String GRUPO;
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
    @Column(name = "ORDEN")
    private BigInteger ORDEN;
    
    @OneToMany(fetch=FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "GRUPOID")
    private Collection<GrupoUsuario> grupoUsuarioCollection;

    public Grupo() {
    }

    public Grupo(BigInteger GRUPOID) {
        this.GRUPOID = GRUPOID;
    }

    public BigInteger getGRUPOID() {
        return GRUPOID;
    }

    public void setGRUPOID(BigInteger GRUPOID) {
        this.GRUPOID = GRUPOID;
    }

    public String getGRUPO() {
        return GRUPO;
    }

    public void setGRUPO(String GRUPO) {
        this.GRUPO = GRUPO;
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

    public BigInteger getORDEN() {
        return ORDEN;
    }

    public void setORDEN(BigInteger ORDEN) {
        this.ORDEN = ORDEN;
    }

    public Collection<GrupoUsuario> getGrupoUsuarioCollection() {
        return grupoUsuarioCollection;
    }

    public void setGrupoUsuarioCollection(Collection<GrupoUsuario> grupoUsuarioCollection) {
        this.grupoUsuarioCollection = grupoUsuarioCollection;
    }

   
   

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (GRUPOID != null ? GRUPOID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Grupo)) {
            return false;
        }
        Grupo other = (Grupo) object;
        if ((this.GRUPOID == null && other.GRUPOID != null) || (this.GRUPOID != null && !this.GRUPOID.equals(other.GRUPOID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Grupo{" + "GRUPOID=" + GRUPOID + ", GRUPO=" + GRUPO + ", ACTIVO=" + ACTIVO + ", DESCRIPCION=" +
                DESCRIPCION + ", ORDEN=" + ORDEN + '}';
    }

    public String toJSON() {
        System.out.println(">>>> grupo.java toJSON()......");
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        sb.append("\"GRUPOID\":\"").append(GRUPOID);
        sb.append("\", \"GRUPO\":\"").append(GRUPO);
        sb.append("\", \"ACTIVO\":\"").append(ACTIVO);
        sb.append("\", \"DESCRIPCION\":\"").append(DESCRIPCION);
        sb.append("\", \"ORDEN\":\"").append(ORDEN);

        sb.append("\"}");
        return sb.toString();
    }
    
    
}
