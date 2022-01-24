/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.peopleinmotion.horizonreinicioremoto.facade;

import com.peopleinmotion.horizonreinicioremoto.entity.Accion;
import com.peopleinmotion.horizonreinicioremoto.entity.GrupoAccion;
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
public class AccionFacade extends AbstractFacade<Accion> {

    @PersistenceContext(unitName = "com.people-inmotion_horizonreinicioremotoejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AccionFacade() {
        super(Accion.class);
    }

     // <editor-fold defaultstate="collapsed" desc="Optional<Accion> find(BigInteger id)">
public Optional<Accion> find(BigInteger id) {
         try {
               Query query = em.createNamedQuery("Accion.findByAccionId");
       Accion accion = (Accion)query.setParameter("ACCIONID", id).getSingleResult();
         return Optional.of(accion);
         } catch (Exception e) {
             System.out.println(JsfUtil.nameOfMethod() + " "+e.getLocalizedMessage());
         }
         return Optional.empty();
      
    } 
// </editor-fold>
     // <editor-fold defaultstate="collapsed" desc="Optional<Accion> findByAccionId(BigInteger ACCIONID)">
public Optional<Accion> findByAccionId(BigInteger ACCIONID) {
         try {
               Query query = em.createNamedQuery("Accion.findByAccionId");
       Accion accion = (Accion)query.setParameter("ACCIONID", ACCIONID).getSingleResult();
         return Optional.of(accion);
         } catch (Exception e) {
             // System.out.println("findByAccionId() "+e.getLocalizedMessage());
         }
         return Optional.empty();
      
    } 
// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="List<Accion> findByGrupoAccionId(GrupoAccion GRUPOACCIONID)">
     public List<Accion> findByGrupoAccionId(GrupoAccion GRUPOACCIONID) {
        List<Accion> list = new ArrayList<>();
        try {

            Query query = em.createQuery("SELECT a FROM Accion a WHERE a.GRUPOACCIONID = :GRUPOACCIONID");
            list = query.setParameter("GRUPOACCIONID", GRUPOACCIONID).getResultList();
        } catch (Exception ex) {
            
               JsfUtil.errorMessage(JsfUtil.nameOfMethod() + " " + ex.getLocalizedMessage());
        }
        return list;
    }
// </editor-fold>
   
    
   // <editor-fold defaultstate="collapsed" desc="List<Accion> findByGrupoAccionIdAndPredeterminado(GrupoAccion GRUPOACCIONID, String PREDETERMINADO )">
     public List<Accion> findByGrupoAccionIdAndPredeterminado(GrupoAccion GRUPOACCIONID, String PREDETERMINADO ) {
        List<Accion> list = new ArrayList<>();
        try {

            Query query = em.createQuery("SELECT a FROM Accion a WHERE a.GRUPOACCIONID = :GRUPOACCIONID AND  a.PREDETERMINADO = :PREDETERMINADO ");
   query.setParameter("GRUPOACCIONID", GRUPOACCIONID);
            list = query.setParameter("PREDETERMINADO", PREDETERMINADO ).getResultList();
        } catch (Exception ex) {
            // System.out.println("findByGrupoAccionIdAndPredeterminado() " + ex.getLocalizedMessage());
        }
        return list;
    }
// </editor-fold>
     
// <editor-fold defaultstate="collapsed" desc="findByAccionIdAndActiv(BigInteger ACCIONID, String ACTIVO) ">
public List<Accion> findByAccionIdAndActivo(BigInteger ACCIONID, String ACTIVO) {
            List<Accion> list = new ArrayList<>();
        try {
            Query query = em.createQuery("SELECT a FROM Accion a WHERE a.ACCIONID = :ACCIONID and a.ACTIVO = :ACTIVO");
             query.setParameter("ACCIONID", ACCIONID);
           list = query.setParameter("ACTIVO", ACTIVO).getResultList();
        } catch (Exception ex) {

            // System.out.println("findByAccionId() " + ex.getLocalizedMessage());

        }
        return list;
    }
// </editor-fold>

    
    }
