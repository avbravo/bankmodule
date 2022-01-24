/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.peopleinmotion.horizonreinicioremoto.facade;

import com.peopleinmotion.horizonreinicioremoto.entity.EmailConfiguration;
import com.peopleinmotion.horizonreinicioremoto.utils.ConsoleUtil;
import com.peopleinmotion.horizonreinicioremoto.utils.JsfUtil;
import java.math.BigInteger;
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
public class EmailConfigurationFacade extends AbstractFacade<EmailConfiguration> {

    @PersistenceContext(unitName = "com.people-inmotion_horizonreinicioremotoejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EmailConfigurationFacade() {
        super(EmailConfiguration.class);
    }
    
    
    // <editor-fold defaultstate="collapsed" desc="Optional<EmailConfiguration> find(BigInteger id) ">

      public Optional<EmailConfiguration> find(BigInteger id) {
         try {
               Query query = em.createNamedQuery("EmailConfiguration.findByEmailConfigurationId");
        EmailConfiguration emailConfiguration = (EmailConfiguration)query.setParameter("EMAILCONFIGURATIONID",id).getSingleResult();
         return Optional.of(emailConfiguration);
         } catch (Exception e) {
              ConsoleUtil.error(JsfUtil.nameOfMethod() + " " + e.getLocalizedMessage());
         }
         return Optional.empty();
      
    }
      // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Optional<EmailConfiguration> findByEmailConfigurationId(BigInteger EMAILCONFIGURATIONID) ">

      public Optional<EmailConfiguration> findByEmailConfigurationId(BigInteger EMAILCONFIGURATIONID) {
         try {
               Query query = em.createNamedQuery("EmailConfiguration.findByEmailConfigurationId");
        EmailConfiguration emailConfiguration = (EmailConfiguration)query.setParameter("EMAILCONFIGURATIONID", EMAILCONFIGURATIONID).getSingleResult();
         return Optional.of(emailConfiguration);
         } catch (Exception e) {
             // System.out.println("findByEmailConfigurationId() "+e.getLocalizedMessage());
         }
         return Optional.empty();
      
    }
      // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Optional<EmailConfiguration> findByActivo(String ACTIVO)">

      public Optional<EmailConfiguration> findByActivo(String ACTIVO) {
         try {
               Query query = em.createNamedQuery("EmailConfiguration.findByActivo");
        EmailConfiguration emailConfiguration = (EmailConfiguration)query.setParameter("ACTIVO", ACTIVO).getSingleResult();
         return Optional.of(emailConfiguration);
         } catch (Exception e) {
             // System.out.println("findByActivo() "+e.getLocalizedMessage());
         }
         return Optional.empty();
      
    }
      // </editor-fold>
}
