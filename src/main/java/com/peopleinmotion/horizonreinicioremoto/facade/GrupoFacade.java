/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.peopleinmotion.horizonreinicioremoto.facade;

import com.peopleinmotion.horizonreinicioremoto.entity.Grupo;
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
public class GrupoFacade extends AbstractFacade<Grupo> {

    @PersistenceContext(unitName = "com.people-inmotion_horizonreinicioremotoejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public GrupoFacade() {
        super(Grupo.class);
    }
    
      // <editor-fold defaultstate="collapsed" desc="Optional<Grupo> find(BigInteger id)">

         public Optional<Grupo> find(BigInteger id) {
         try {
               Query query = em.createNamedQuery("Grupo.findByGrupoId");
        Grupo grupo = (Grupo)query.setParameter("GRUPOID", id).getSingleResult();
         return Optional.of(grupo);
         } catch (Exception e) {
              ConsoleUtil.error(JsfUtil.nameOfMethod() + " " + e.getLocalizedMessage());
         }
         return Optional.empty();
      
    }
         // </editor-fold>
         // <editor-fold defaultstate="collapsed" desc="Optional<Grupo> findByGrupoId(BigInteger GRUPOID)">


         public Optional<Grupo> findByGrupoId(BigInteger GRUPOID) {
         try {
               Query query = em.createNamedQuery("GrupoEstado.findByGrupoId");
        Grupo grupo = (Grupo)query.setParameter("GRUPOID", GRUPOID).getSingleResult();
         return Optional.of(grupo);
         } catch (Exception e) {
              ConsoleUtil.error(JsfUtil.nameOfMethod() + " " + e.getLocalizedMessage());
         }
         return Optional.empty();
      
    }
         // </editor-fold>
}
