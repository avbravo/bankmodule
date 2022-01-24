/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.peopleinmotion.horizonreinicioremoto.facade;

import com.peopleinmotion.horizonreinicioremoto.entity.Banco;
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

public class BancoFacade extends AbstractFacade<Banco> {

    @PersistenceContext(unitName = "com.people-inmotion_horizonreinicioremotoejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public BancoFacade() {
        super(Banco.class);
    }
    // <editor-fold defaultstate="collapsed" desc="Optional<Banco> find(BigInteger id)">

    /**
     * Busca por la llave primaria se usa solo el nombre find() para simplificar
     * su uso
     *
     * @param BANCOID
     * @return
     */
    public Optional<Banco> find(BigInteger id) {
        try {
            Query query = em.createNamedQuery("Banco.findByBancoId");
            Banco banco = (Banco) query.setParameter("BANCOID", id).getSingleResult();
            return Optional.of(banco);
        } catch (Exception e) {
             ConsoleUtil.error(JsfUtil.nameOfMethod() + " " + e.getLocalizedMessage());
        }
        return Optional.empty();

    }// </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Optional<Banco> findById(BigInteger BANCOID)">

    public Optional<Banco> findById(BigInteger BANCOID) {
        try {
            Query query = em.createNamedQuery("Banco.findByBancoId");
            Banco banco = (Banco) query.setParameter("BANCOID", BANCOID).getSingleResult();
            return Optional.of(banco);
        } catch (Exception e) {
            // System.out.println("findByBancoId() "+e.getLocalizedMessage());
        }
        return Optional.empty();

    }// </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Optional<Banco> findByBancoId(BigInteger BANCOID)">

    public Optional<Banco> findByBancoId(BigInteger BANCOID) {
        try {
            Query query = em.createNamedQuery("Banco.findByBancoId");
            Banco banco = (Banco) query.setParameter("BANCOID", BANCOID).getSingleResult();
            return Optional.of(banco);
        } catch (Exception e) {
            // System.out.println("findByBancoId() "+e.getLocalizedMessage());
        }
        return Optional.empty();

    }// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Optional<Banco> findByEsControlActivo(String ESCONTROL, String ACTIVO) ">
    public Optional<Banco> findByEsControlAndActivo(String ESCONTROL, String ACTIVO) {
        try {
            Query query = em.createQuery("SELECT b FROM Banco b WHERE b.ESCONTROL = :ESCONTROL AND b.ACTIVO = :ACTIVO ORDER BY b.BANCO");

            Banco banco = (Banco) query.setParameter("ESCONTROL", ESCONTROL).setParameter("ACTIVO", ACTIVO).getSingleResult();
            return Optional.of(banco);
        } catch (Exception e) {
            // System.out.println("findByEsControlActivo() "+e.getLocalizedMessage());
        }
        return Optional.empty();

    }
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Optional<Banco> findByEsControlActivoList(String ESCONTROL, String ACTIVO) ">

    public List<Banco> findByEsControlAndActivoList(String ESCONTROL, String ACTIVO) {
        List<Banco> list = new ArrayList<>();
        try {
            Query query = em.createQuery("SELECT b FROM Banco b WHERE b.ESCONTROL = :ESCONTROL AND b.ACTIVO = :ACTIVO ORDER BY b.BANCO");
            list
                    = list = query.setParameter("ESCONTROL", ESCONTROL).setParameter("ACTIVO", ACTIVO).getResultList();

        } catch (Exception e) {
            // System.out.println(JsfUtil.nameOfMethod() + " "+e.getLocalizedMessage());
        }
        return list;

    }
    // </editor-fold>

    
    

}
