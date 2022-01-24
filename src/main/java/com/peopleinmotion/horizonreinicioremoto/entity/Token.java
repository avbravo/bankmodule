/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.peopleinmotion.horizonreinicioremoto.entity;

import java.io.Serializable;
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
import lombok.Builder;

/**
 *
 * @author avbravo
 */
@Entity
@Table(name = "TOKEN") 
@NamedQueries({
    @NamedQuery(name = "Token.findAll", query = "SELECT t FROM Token t"),
    @NamedQuery(name = "Token.findByTokenId", query = "SELECT t FROM Token t WHERE t.TOKENID = :TOKENID"),
    @NamedQuery(name = "Token.findByUsuarioId", query = "SELECT t FROM Token t WHERE t.USUARIOID = :USUARIOID"),
     @NamedQuery(name = "Token.findByCodigoTransaccion", query = "SELECT  t FROM Token t WHERE t.CODIGOTRANSACCION = :CODIGOTRANSACCION"),
    @NamedQuery(name = "Token.findByVencido", query = "SELECT t FROM Token t WHERE t.VENCIDO = :VENCIDO"),
    @NamedQuery(name = "Token.findByUsado", query = "SELECT t FROM Token t WHERE t.USADO = :USADO"),
    @NamedQuery(name = "Token.findByActivo", query = "SELECT t FROM Token t WHERE t.ACTIVO = :ACTIVO")
    
})

public class Token implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @SequenceGenerator(name = "TOKEN_GEN", sequenceName = "TOKEN_SEQ",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TOKEN_GEN")
    @NotNull
    @Column(name = "TOKENID")
    private BigInteger TOKENID;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 4)
    @Column(name = "TOKEN")
    private String TOKEN;
    @Basic(optional = false)
    @NotNull
     
    @Column(name = "ACTIVO")
    private String ACTIVO;
    @Basic(optional = false)
    @NotNull
    @Column(name = "USADO")
    private String USADO;
     @NotNull
    @Column(name = "CODIGOTRANSACCION ")
    private String CODIGOTRANSACCION ;
    @NotNull
    @Column(name = "VENCIDO")
    private String VENCIDO;
    @NotNull
    @Column(name = "USUARIOID")
    private BigInteger USUARIOID;
   
   @NotNull
    @Column(name = "FECHAGENERACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date FECHAGENERACION;
   
   @NotNull
    @Column(name = "FECHAVENCIMIENTO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date FECHAVENCIMIENTO;
   
   

    public Token() {
    }

    public Token(BigInteger TOKENID) {
        this.TOKENID = TOKENID;
    }

    public BigInteger getTOKENID() {
        return TOKENID;
    }

    public void setTOKENID(BigInteger TOKENID) {
        this.TOKENID = TOKENID;
    }

    public String getTOKEN() {
        return TOKEN;
    }

    public void setTOKEN(String TOKEN) {
        this.TOKEN = TOKEN;
    }

    public String getACTIVO() {
        return ACTIVO;
    }

    public void setACTIVO(String ACTIVO) {
        this.ACTIVO = ACTIVO;
    }

    public String getUSADO() {
        return USADO;
    }

    public void setUSADO(String USADO) {
        this.USADO = USADO;
    }

    public String getVENCIDO() {
        return VENCIDO;
    }

    public void setVENCIDO(String VENCIDO) {
        this.VENCIDO = VENCIDO;
    }

    public BigInteger getUSUARIOID() {
        return USUARIOID;
    }

    public void setUSUARIOID(BigInteger USUARIOID) {
        this.USUARIOID = USUARIOID;
    }

    
    
    public Date getFECHAGENERACION() {
        return FECHAGENERACION;
    }

    public void setFECHAGENERACION(Date FECHAGENERACION) {
        this.FECHAGENERACION = FECHAGENERACION;
    }

    public Date getFECHAVENCIMIENTO() {
        return FECHAVENCIMIENTO;
    }

    public void setFECHAVENCIMIENTO(Date FECHAVENCIMIENTO) {
        this.FECHAVENCIMIENTO = FECHAVENCIMIENTO;
    }

    public String getCODIGOTRANSACCION() {
        return CODIGOTRANSACCION;
    }

    public void setCODIGOTRANSACCION(String CODIGOTRANSACCION) {
        this.CODIGOTRANSACCION = CODIGOTRANSACCION;
    }

    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (TOKENID != null ? TOKENID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Token)) {
            return false;
        }
        Token other = (Token) object;
        if ((this.TOKENID == null && other.TOKENID != null) || (this.TOKENID != null && !this.TOKENID.equals(other.TOKENID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Token{" + "TOKENID=" + TOKENID + ", TOKEN=" + TOKEN + ", ACTIVO=" + ACTIVO + ", USADO=" + USADO + ", CODIGOTRANSACCION=" + CODIGOTRANSACCION + ", VENCIDO=" + VENCIDO + ", USUARIOID=" + USUARIOID + ", FECHAGENERACION=" + FECHAGENERACION + ", FECHAVENCIMIENTO=" + FECHAVENCIMIENTO + '}';
    }

   

}
