/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.peopleinmotion.horizonreinicioremoto.entity;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import static java.util.stream.Collectors.toCollection;
import javax.persistence.Basic;
import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author avbravo
 */
@Entity
@Table(name = "USUARIO")
@NamedQueries({
    @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u"),
    @NamedQuery(name = "Usuario.findByUsuarioId", query = "SELECT u FROM Usuario u WHERE u.USUARIOID = :USUARIOID"),
    @NamedQuery(name = "Usuario.findByUsername", query = "SELECT u FROM Usuario u WHERE u.USERNAME = :USERNAME"),
    @NamedQuery(name = "Usuario.findByPassword", query = "SELECT u FROM Usuario u WHERE u.PASSWORD = :PASSWORD"),
    @NamedQuery(name = "Usuario.findByNombre", query = "SELECT u FROM Usuario u WHERE u.NOMBRE = :NOMBRE"),
    @NamedQuery(name = "Usuario.findByCedula", query = "SELECT u FROM Usuario u WHERE u.CEDULA = :CEDULA"),
    @NamedQuery(name = "Usuario.findByEmail", query = "SELECT u FROM Usuario u WHERE u.EMAIL = :EMAIL"),
    @NamedQuery(name = "Usuario.findByTelefono", query = "SELECT u FROM Usuario u WHERE u.TELEFONO = :TELEFONO"),
    @NamedQuery(name = "Usuario.findByCelular", query = "SELECT u FROM Usuario u WHERE u.CELULAR = :CELULAR"),
    @NamedQuery(name = "Usuario.findByActivo", query = "SELECT u FROM Usuario u WHERE u.ACTIVO = :ACTIVO"),
    @NamedQuery(name = "Usuario.findByOrden", query = "SELECT u FROM Usuario u WHERE u.ORDEN = :ORDEN"),
    @NamedQuery(name = "Usuario.findByNombreLike", query = "SELECT u FROM Usuario u WHERE lower(u.NOMBRE) like :NOMBRE"),
    @NamedQuery(name = "Usuario.contadorActivo", query = "SELECT COUNT(u) FROM Usuario u WHERE u.ACTIVO= :ACTIVO")})
@Cacheable(false)
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @SequenceGenerator(name = "USUARIO_GEN", sequenceName = "USUARIO_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USUARIO_GEN")
    @NotNull
    @Column(name = "USUARIOID")
    private BigInteger USUARIOID;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "USERNAME")
    private String USERNAME;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "PASSWORD")
    private String PASSWORD;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "NOMBRE")
    private String NOMBRE;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 35)
    @Column(name = "CEDULA")
    private String CEDULA;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid EMAIL")//if the field contains EMAIL address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "EMAIL")
    private String EMAIL;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 35)
    @Column(name = "TELEFONO")
    private String TELEFONO;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 35)
    @Column(name = "CELULAR")
    private String CELULAR;
    @Basic(optional = false)
    @NotNull

    @Column(name = "ACTIVO")
    private String ACTIVO;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ORDEN")
    private BigInteger ORDEN;

    @JoinColumn(name = "BANCOID", referencedColumnName = "BANCOID")
    @ManyToOne(optional = false)
    private Banco BANCOID;

    public Usuario() {
    }

    public Usuario(BigInteger USUARIOID) {
        this.USUARIOID = USUARIOID;
    }

    public BigInteger getUSUARIOID() {
        return USUARIOID;
    }

    public void setUSUARIOID(BigInteger USUARIOID) {
        this.USUARIOID = USUARIOID;
    }

    public String getUSERNAME() {
        return USERNAME;
    }

    public void setUSERNAME(String USERNAME) {
        this.USERNAME = USERNAME;
    }

    public String getPASSWORD() {
        return PASSWORD;
    }

    public void setPASSWORD(String PASSWORD) {
        this.PASSWORD = PASSWORD;
    }

    public String getNOMBRE() {
        return NOMBRE;
    }

    public void setNOMBRE(String NOMBRE) {
        this.NOMBRE = NOMBRE;
    }

    public String getCEDULA() {
        return CEDULA;
    }

    public void setCEDULA(String CEDULA) {
        this.CEDULA = CEDULA;
    }

    public String getEMAIL() {
        return EMAIL;
    }

    public void setEMAIL(String EMAIL) {
        this.EMAIL = EMAIL;
    }

    public String getTELEFONO() {
        return TELEFONO;
    }

    public void setTELEFONO(String TELEFONO) {
        this.TELEFONO = TELEFONO;
    }

    public String getCELULAR() {
        return CELULAR;
    }

    public void setCELULAR(String CELULAR) {
        this.CELULAR = CELULAR;
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

    public Banco getBANCOID() {
        return BANCOID;
    }

    public void setBANCOID(Banco BANCOID) {
        this.BANCOID = BANCOID;
    }

  
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (USUARIOID != null ? USUARIOID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.USUARIOID == null && other.USUARIOID != null) || (this.USUARIOID != null && !this.USUARIOID.equals(other.USUARIOID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Usuario{" + "USUARIOID=" + USUARIOID + ", USERNAME=" + USERNAME + ", PASSWORD=" + PASSWORD + ", NOMBRE=" + NOMBRE + ", CEDULA=" + CEDULA + ", EMAIL=" + EMAIL + ", TELEFONO=" + TELEFONO + ", CELULAR=" + CELULAR + ", ACTIVO=" + ACTIVO + ", ORDEN=" + ORDEN + ", BANCOID=" + BANCOID + '}';
    }

   

    public String toJSON() {
 
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        sb.append("\n  \"USUARIOID\":\"").append(USUARIOID).append("\"");
        sb.append("\n, \"USERNAME\":\"").append(USERNAME).append("\"");
        sb.append("\n, \"PASSWORD\":\"").append(PASSWORD).append("\"");
        sb.append("\n, \"NOMBRE\":\"").append(NOMBRE).append("\"");
        sb.append("\n, \"CEDULA\":\"").append(CEDULA).append("\"");
        sb.append("\n, \"EMAIL\":\"").append(EMAIL).append("\"");
        sb.append("\n, \"TELEFONO\":\"").append(TELEFONO).append("\"");
        sb.append("\n, \"CELULAR\":\"").append(CELULAR).append("\"");
        sb.append("\n, \"ACTIVO\":\"").append(ACTIVO).append("\"");
        sb.append("\n, \"ORDEN\":\"").append(ORDEN).append("\"");
        sb.append("\n, \"BANCOID\":").append(BANCOID.toJSON());
        
      
        //
        
        sb.append("\n}");
        return sb.toString();
    }
}
