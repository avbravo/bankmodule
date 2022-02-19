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
import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "EMAILCONFIGURATION") 
@NamedQueries({
    @NamedQuery(name = "EmailConfiguration.findAll", query = "SELECT e FROM EmailConfiguration e"),
    @NamedQuery(name = "EmailConfiguration.findByEmailConfigurationId", query = "SELECT e FROM EmailConfiguration e WHERE e.EMAILCONFIGURATIONID = :EMAILCONFIGURATIONID"),
    @NamedQuery(name = "EmailConfiguration.findByEmail", query = "SELECT e FROM EmailConfiguration e WHERE e.EMAIL = :EMAIL"),
    @NamedQuery(name = "EmailConfiguration.findByActivo", query = "SELECT e FROM EmailConfiguration e WHERE e.ACTIVO = :ACTIVO"),
    @NamedQuery(name = "EmailConfiguration.findByOrden", query = "SELECT e FROM EmailConfiguration e WHERE e.ORDEN = :ORDEN")
    })
@Cacheable(false)
public class EmailConfiguration implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @SequenceGenerator(name = "EMAILCONFIGURATION_GEN", sequenceName = "EMAILCONFIGURATION_SEQ",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EMAILCONFIGURATION_GEN")
    @NotNull
    @Column(name = "EMAILCONFIGURATIONID")
    private BigInteger EMAILCONFIGURATIONID;
    @Size(max = 150)
    @Column(name = "EMAIL")
    private String EMAIL;
    
    @Size(max = 150)
    @Column(name = "PASSWORD")
    private String PASSWORD;
    
    
      @Size(max = 100)
    @Column(name = "MAILSMTPHOST")
    private String MAILSMTPHOST;
      
    @Size(max = 15)
    @Column(name = "MAILSMTPAUTH")
    private String  MAILSMTPAUTH;
    
    @Size(max = 15)
    @Column(name = "MAILSMTPPORT")
    private String  MAILSMTPPORT;
    
    @Size(max = 15)
    @Column(name = "MAILSMTPSTARTTLSENABLE")
    private String  MAILSMTPSTARTTLSENABLE;
    
  
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 2, max = 2)
    @Column(name = "ACTIVO")      
    private String ACTIVO;
    
    
    @Column(name = "ORDEN")
    private BigInteger ORDEN;
    
   
    public EmailConfiguration() {
    }

    public BigInteger getEMAILCONFIGURATIONID() {
        return EMAILCONFIGURATIONID;
    }

    public void setEMAILCONFIGURATIONID(BigInteger EMAILCONFIGURATIONID) {
        this.EMAILCONFIGURATIONID = EMAILCONFIGURATIONID;
    }

    public String getEMAIL() {
        return EMAIL;
    }

    public void setEMAIL(String EMAIL) {
        this.EMAIL = EMAIL;
    }

    public String getPASSWORD() {
        return PASSWORD;
    }

    public void setPASSWORD(String PASSWORD) {
        this.PASSWORD = PASSWORD;
    }

    public String getMAILSMTPHOST() {
        return MAILSMTPHOST;
    }

    public void setMAILSMTPHOST(String MAILSMTPHOST) {
        this.MAILSMTPHOST = MAILSMTPHOST;
    }

    public String getMAILSMTPAUTH() {
        return MAILSMTPAUTH;
    }

    public void setMAILSMTPAUTH(String MAILSMTPAUTH) {
        this.MAILSMTPAUTH = MAILSMTPAUTH;
    }

    public String getMAILSMTPPORT() {
        return MAILSMTPPORT;
    }

    public void setMAILSMTPPORT(String MAILSMTPPORT) {
        this.MAILSMTPPORT = MAILSMTPPORT;
    }

    public String getMAILSMTPSTARTTLSENABLE() {
        return MAILSMTPSTARTTLSENABLE;
    }

    public void setMAILSMTPSTARTTLSENABLE(String MAILSMTPSTARTTLSENABLE) {
        this.MAILSMTPSTARTTLSENABLE = MAILSMTPSTARTTLSENABLE;
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
        hash += (EMAILCONFIGURATIONID != null ? EMAILCONFIGURATIONID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EmailConfiguration)) {
            return false;
        }
        EmailConfiguration other = (EmailConfiguration) object;
        if ((this.EMAILCONFIGURATIONID == null && other.EMAILCONFIGURATIONID != null) || (this.EMAILCONFIGURATIONID != null && !this.EMAILCONFIGURATIONID.equals(other.EMAILCONFIGURATIONID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "EmailConfiguration{" + "EMAILCONFIGURATIONID=" + EMAILCONFIGURATIONID + ", EMAIL=" + EMAIL + ", PASSWORD=" + PASSWORD + ", MAILSMTPHOST=" + MAILSMTPHOST + ", MAILSMTPAUTH=" + MAILSMTPAUTH + ", MAILSMTPPORT=" + MAILSMTPPORT + ", MAILSMTPSTARTTLSENABLE=" + MAILSMTPSTARTTLSENABLE + ", ACTIVO=" + ACTIVO + ", ORDEN=" + ORDEN + '}';
    }

 
    
}
