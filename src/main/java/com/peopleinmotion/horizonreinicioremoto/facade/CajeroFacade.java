/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.peopleinmotion.horizonreinicioremoto.facade;

import com.peopleinmotion.horizonreinicioremoto.entity.Banco;
import com.peopleinmotion.horizonreinicioremoto.entity.Cajero;
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
public class CajeroFacade extends AbstractFacade<Cajero> {

    @PersistenceContext(unitName = "com.people-inmotion_horizonreinicioremotoejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CajeroFacade() {
        super(Cajero.class);
    }
    // <editor-fold defaultstate="collapsed" desc="Optional<Cajero> find(BigInteger id)">

    public Optional<Cajero> find(BigInteger id) {
        try {
            Query query = em.createNamedQuery("Cajero.findByCajeroId");
            Cajero cajero = (Cajero) query.setParameter("CAJEROID", id).getSingleResult();
            return Optional.of(cajero);
        } catch (Exception e) {
            ConsoleUtil.error(JsfUtil.nameOfMethod() + " " + e.getLocalizedMessage());
        }
        return Optional.empty();

    }
// </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Optional<Cajero> findByCajeroId(BigInteger CAJEROID)">

    public Optional<Cajero> findByCajeroId(BigInteger CAJEROID) {
        try {
            Query query = em.createNamedQuery("Cajero.findByCajeroId");
            Cajero cajero = (Cajero) query.setParameter("CAJEROID", CAJEROID).getSingleResult();
            return Optional.of(cajero);
        } catch (Exception e) {
            // System.out.println("findByCajeroId() "+e.getLocalizedMessage());
        }
        return Optional.empty();

    }
// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="List<Cajero> findByBancoId(Banco BANCOID)">
    public List<Cajero> findByBancoId(Banco BANCOID) {
        List<Cajero> list = new ArrayList<>();
        try {
            Query query = em.createQuery("SELECT c FROM Cajero c WHERE c.BANCOID = :BANCOID");

            list = query.setParameter("BANCOID", BANCOID).getResultList();
        } catch (Exception ex) {
            // System.out.println("findByBancoId() " + ex.getLocalizedMessage());
        }
        return list;
    }
// </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="List<Cajero> findByBancoIdAndActivo(Banco BANCOID, String ACTIVO)">

    public List<Cajero> findByBancoIdAndActivo(Banco BANCOID, String ACTIVO) {
        List<Cajero> list = new ArrayList<>();
        try {

            Query query = em.createQuery("SELECT c FROM Cajero c WHERE c.BANCOID = :BANCOID AND c.ACTIVO = :ACTIVO ORDER BY c.CAJERO");

            list = query.setParameter("BANCOID", BANCOID).setParameter("ACTIVO", ACTIVO).getResultList();

        } catch (Exception ex) {
            JsfUtil.errorMessage(JsfUtil.nameOfMethod() + " " + ex.getLocalizedMessage());
        }
        return list;
    }
// </editor-fold>

// <editor-fold defaultstate="collapsed" desc="findByCajeroIdAndActiv(BigInteger CAJEROID, String ACTIVO) ">
    public List<Cajero> findByCajeroIdAndActivo(BigInteger CAJEROID, String ACTIVO) {
        List<Cajero> list = new ArrayList<>();
        try {
            Query query = em.createQuery("SELECT c FROM Cajero c WHERE c.CAJEROID = :CAJEROID and c.ACTIVO = :ACTIVO");
            query.setParameter("CAJEROID", CAJEROID);
            list = query.setParameter("ACTIVO", ACTIVO).getResultList();
        } catch (Exception ex) {

            // System.out.println("findByCajeroId() " + ex.getLocalizedMessage());
        }
        return list;
    }
// </editor-fold>
// <editor-fold defaultstate="collapsed" desc="List<Cajero> findByCajeroIdBancoIdAndActivo(Banco BANCOID, String CAJERO, String ACTIVO)">

    public List<Cajero> findByCajeroIdBancoIdAndActivo(Banco BANCOID, String CAJERO, String ACTIVO) {
        List<Cajero> list = new ArrayList<>();
        try {
            Query query = em.createQuery("SELECT c FROM Cajero c WHERE  c.BANCOID = :BANCOID AND c.CAJERO = :CAJERO AND c.ACTIVO = :ACTIVO");
            query.setParameter("CAJERO", CAJERO);
            query.setParameter("BANCOID", BANCOID);
            list = query.setParameter("ACTIVO", ACTIVO).getResultList();
        } catch (Exception ex) {

            // System.out.println("findByCajeroId() " + ex.getLocalizedMessage());
        }
        return list;
    }
// </editor-fold>
// <editor-fold defaultstate="collapsed" desc="List<Cajero> findByDireccionCortaBancoIdAndActivo(Banco BANCOID, String DIRECCIONCORTA, String ACTIVO)">

    public List<Cajero> findByDireccionCortaBancoIdAndActivo(Banco BANCOID, String DIRECCIONCORTA, String ACTIVO) {
        List<Cajero> list = new ArrayList<>();
        try {
            DIRECCIONCORTA = "%" + DIRECCIONCORTA.trim().toUpperCase() + "%";
            Query query = em.createQuery("SELECT c FROM Cajero c WHERE  c.BANCOID = :BANCOID AND c.DIRECCIONCORTA LIKE :DIRECCIONCORTA AND c.ACTIVO = :ACTIVO");
            query.setParameter("DIRECCIONCORTA", DIRECCIONCORTA);
            query.setParameter("BANCOID", BANCOID);
            list = query.setParameter("ACTIVO", ACTIVO).getResultList();
        } catch (Exception ex) {

            // System.out.println("findByDireccionCortaBancoIdAndActivo() " + ex.getLocalizedMessage());
        }
        return list;
    }
// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="int countBancoIdAndActivo(Banco BANCOID, String ACTIVO)">
    /**
     * Cuenta los cajeros del banco y por activo
     * @param BANCOID
     * @param ACTIVO
     * @return 
     */
    public int countBancoIdAndActivo(Banco BANCOID, String ACTIVO) {
        List<Cajero> list = new ArrayList<>();
        try {

            Query query = em.createQuery("SELECT COUNT(c) FROM Cajero c WHERE c.BANCOID = :BANCOID AND c.ACTIVO = :ACTIVO ");

            query.setParameter("BANCOID", BANCOID).setParameter("ACTIVO", ACTIVO).getResultList();
            return ((Long) query.getSingleResult()).intValue();

        } catch (Exception ex) {
            JsfUtil.errorMessage(JsfUtil.nameOfMethod() + " " + ex.getLocalizedMessage());
        }
        return 0;
    }
// </editor-fold>
    
     // <editor-fold defaultstate="collapsed" desc="List<Cajero> findByBancoIdAndActivoPaginacion(Banco BANCOID, String ACTIVO)">

    public List<Cajero> findBancoIdAndActivoPaginacion(Banco BANCOID, String ACTIVO, Integer pageNumber, Integer rowForPage) {
        List<Cajero> list = new ArrayList<>();
        try {

            Query query = em.createQuery("SELECT c FROM Cajero c WHERE c.BANCOID = :BANCOID AND c.ACTIVO = :ACTIVO ORDER BY c.CAJERO");

             query.setParameter("BANCOID", BANCOID).setParameter("ACTIVO", ACTIVO);
  query.setFirstResult(pageNumber).setMaxResults(rowForPage);
  list = query.getResultList();
        } catch (Exception ex) {
            JsfUtil.errorMessage(JsfUtil.nameOfMethod() + " " + ex.getLocalizedMessage());
        }
        return list;
    }
// </editor-fold>
}
