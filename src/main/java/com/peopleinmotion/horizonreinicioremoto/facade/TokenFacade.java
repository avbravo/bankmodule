/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.peopleinmotion.horizonreinicioremoto.facade;

import com.peopleinmotion.horizonreinicioremoto.entity.Token;
import com.peopleinmotion.horizonreinicioremoto.utils.ConsoleUtil;
import com.peopleinmotion.horizonreinicioremoto.utils.JsfUtil;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


/**
 *
 * @author avbravo
 */
@Stateless
public class TokenFacade extends AbstractFacade<Token> {

    @PersistenceContext(unitName = "com.people-inmotion_horizonreinicioremotoejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TokenFacade() {
        super(Token.class);
    }

    
      // <editor-fold defaultstate="collapsed" desc="Optional<Token> find(BigInteger id) ">

     public Optional<Token> find(BigInteger id) {
         try {
               Query query = em.createNamedQuery("Token.findByTokenId");
       Token token = (Token)query.setParameter("TOKENID", id).getSingleResult();
         return Optional.of(token);
         } catch (Exception e) {
              ConsoleUtil.error(JsfUtil.nameOfMethod() + " " + e.getLocalizedMessage());
         }
         return Optional.empty();
      
    }
     // </editor-fold>
      // <editor-fold defaultstate="collapsed" desc="Optional<Token> findByTokenId(BigInteger TOKENID) ">

     public Optional<Token> findByTokenId(BigInteger TOKENID) {
         try {
               Query query = em.createNamedQuery("Token.findByTokenId");
       Token token = (Token)query.setParameter("TOKENID", TOKENID).getSingleResult();
         return Optional.of(token);
         } catch (Exception e) {
             // System.out.println("findByTokenId() "+e.getLocalizedMessage());
         }
         return Optional.empty();
      
    }
     // </editor-fold>
   
    // <editor-fold defaultstate="collapsed" desc="Optional<Token> findByCodigoTransaccion(String  CODIGOTRANSACCION)">

    public Optional<Token> findByCodigoTransaccion(String CODIGOTRANSACCION) {
        try {
            Query query = em.createNamedQuery("Token.findByCodigoTransaccion");
            Token token = (Token) query.setParameter("CODIGOTRANSACCION", CODIGOTRANSACCION).getSingleResult();
            return Optional.of(token);
        } catch (Exception e) {
            // System.out.println("findByCodigoTransaccion() " + e.getLocalizedMessage());
        }
        return Optional.empty();

    }
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="List<Token> findByActivo(String ACTIVO)">

    public List<Token> findByActivo(String ACTIVO) {
        List<Token> list = new ArrayList<>();
        try {
            Query query = em.createNamedQuery("Token.findByActivo");
             list = query.setParameter("ACTIVO", ACTIVO).getResultList();
            
        } catch (Exception e) {
            // System.out.println("findByCodigoTransaccion() " + e.getLocalizedMessage());
        }
        return list;

    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="List<Token> findByUsuarioIdAndActivo(BigInteger USUARIOID, String ACTIVO)">
    public List<Token> findByUsuarioIdAndActivo(BigInteger USUARIOID, String ACTIVO) {
        List<Token> list = new ArrayList<>();
        try {

            Query query = em.createQuery("SELECT t FROM Token t WHERE t.USUARIOID = :USUARIOID AND t.ACTIVO = :ACTIVO ORDER BY t.TOKENID DESC");
            list = query.setParameter("USUARIOID", USUARIOID).setParameter("ACTIVO", ACTIVO).getResultList();
        } catch (Exception ex) {
                JsfUtil.errorMessage(JsfUtil.nameOfMethod() + " " + ex.getLocalizedMessage());
        }
        return list;
    }
// </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="List<Token> findByUsuarioIdTokenAndActivo(BigInteger USUARIOID, String TOKEN, String ACTIVO)">
    public List<Token> findByUsuarioIdTokenAndActivo(BigInteger USUARIOID, String TOKEN, String ACTIVO) {
        List<Token> list = new ArrayList<>();
        try {

            Query query = em.createQuery("SELECT t FROM Token t WHERE t.USUARIOID = :USUARIOID AND t.ACTIVO = :ACTIVO AND t.TOKEN = :TOKEN ORDER BY t.TOKENID DESC");
            list = query.setParameter("USUARIOID", USUARIOID).setParameter("ACTIVO", ACTIVO).setParameter("TOKEN", TOKEN).getResultList();
        } catch (Exception ex) {
                JsfUtil.errorMessage(JsfUtil.nameOfMethod() + " " + ex.getLocalizedMessage());
        }
        return list;
    }
// </editor-fold>

}
