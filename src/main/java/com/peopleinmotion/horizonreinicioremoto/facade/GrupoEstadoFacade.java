/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.peopleinmotion.horizonreinicioremoto.facade;

import com.peopleinmotion.horizonreinicioremoto.entity.GrupoEstado;
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
public class GrupoEstadoFacade extends AbstractFacade<GrupoEstado> {

    @PersistenceContext(unitName = "com.people-inmotion_horizonreinicioremotoejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public GrupoEstadoFacade() {
        super(GrupoEstado.class);
    }
    
    // <editor-fold defaultstate="collapsed" desc="Optional<GrupoEstado> find(BigInteger id)">

         public Optional<GrupoEstado> find(BigInteger id) {
         try {
               Query query = em.createNamedQuery("GrupoEstado.findByGrupoEstadoId");
        GrupoEstado grupoEstado = (GrupoEstado)query.setParameter("GRUPOESTADOID", id).getSingleResult();
         return Optional.of(grupoEstado);
         } catch (Exception e) {
              ConsoleUtil.error(JsfUtil.nameOfMethod() + " " + e.getLocalizedMessage());
         }
         return Optional.empty();
      
    }
         // </editor-fold>
         // <editor-fold defaultstate="collapsed" desc="Optional<GrupoEstado> findByGrupoEstadoId(BigInteger GRUPOESTADOID)">


         public Optional<GrupoEstado> findByGrupoEstadoId(BigInteger GRUPOESTADOID) {
         try {
               Query query = em.createNamedQuery("GrupoEstado.findByGrupoEstadoId");
        GrupoEstado grupoEstado = (GrupoEstado)query.setParameter("GRUPOESTADOID", GRUPOESTADOID).getSingleResult();
         return Optional.of(grupoEstado);
         } catch (Exception e) {
              ConsoleUtil.error(JsfUtil.nameOfMethod() + " " + e.getLocalizedMessage());
         }
         return Optional.empty();
      
    }
         // </editor-fold>
         
           // <editor-fold defaultstate="collapsed" desc="List<GrupoEstado> findByActivo(String ACTIVO) ">
public List<GrupoEstado> findByActivo(String ACTIVO) {
       List<GrupoEstado> list = new ArrayList<>();
         try {
               Query query = em.createNamedQuery("GrupoEstado.findByAtivo");
          list =query.setParameter("ACTIVO", ACTIVO).getResultList();

         } catch (Exception e) {
             // System.out.println("findByActivo() "+e.getLocalizedMessage());
         }
         return list;
      
    } 
// </editor-fold>

}
