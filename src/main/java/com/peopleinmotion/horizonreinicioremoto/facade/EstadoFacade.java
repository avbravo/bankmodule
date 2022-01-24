/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.peopleinmotion.horizonreinicioremoto.facade;

import com.peopleinmotion.horizonreinicioremoto.entity.Estado;
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
public class EstadoFacade extends AbstractFacade<Estado> {

    @PersistenceContext(unitName = "com.people-inmotion_horizonreinicioremotoejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EstadoFacade() {
        super(Estado.class);
    }

    // <editor-fold defaultstate="collapsed" desc="Optional<Estado> find(BigInteger id)">
    public Optional<Estado> find(BigInteger id) {
        try {
            Query query = em.createNamedQuery("Estado.findByEstadoId");
            Estado estado = (Estado) query.setParameter("ESTADOID", id).getSingleResult();
            return Optional.of(estado);
        } catch (Exception e) {
             ConsoleUtil.error(JsfUtil.nameOfMethod() + " " + e.getLocalizedMessage());
        }
        return Optional.empty();

    }
// </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Optional<Estado> findByEstadoId(BigInteger ACCIONID)">
    public Optional<Estado> findByEstadoId(BigInteger ESTADOID) {
        try {
            Query query = em.createNamedQuery("Estado.findByEstadoId");
            Estado estado = (Estado) query.setParameter("ESTADOID", ESTADOID).getSingleResult();
            return Optional.of(estado);
        } catch (Exception e) {
            // System.out.println("findByEstadoId() " + e.getLocalizedMessage());
        }
        return Optional.empty();

    }
// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="List<Estado> findByGrupoEstadoId(GrupoEstado GRUPOESTADOID)">
    public List<Estado> findByGrupoEstadoId(GrupoEstado GRUPOESTADOID) {
        List<Estado> list = new ArrayList<>();
        try {

            Query query = em.createQuery("SELECT e FROM Estado e WHERE e.GRUPOESTADOID = :GRUPOESTADOID");
            list = query.setParameter("GRUPOESTADOID", GRUPOESTADOID).getResultList();
        } catch (Exception ex) {
            // System.out.println("findByGrupoEstadoId() " + ex.getLocalizedMessage());
        }
        return list;
    }
// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Optional<Estado> findByPredeterminado(String PREDETERMINADO)">
    public Optional<Estado> findByPredeterminado(String PREDETERMINADO) {
        try {
            Query query = em.createNamedQuery("Estado.findByPredeterminado");
            Estado estado = (Estado) query.setParameter("PREDETERMINADO", PREDETERMINADO).getSingleResult();
            return Optional.of(estado);
        } catch (Exception e) {
            // System.out.println("findByPredeterminado() " + e.getLocalizedMessage());
        }
        return Optional.empty();

    }
// </editor-fold>          

    // <editor-fold defaultstate="collapsed" desc="List<Estado> findByActivo(String ACTIVO) ">
    public List<Estado> findByActivo(String ACTIVO) {
        List<Estado> list = new ArrayList<>();
        try {
            Query query = em.createNamedQuery("Estado.findByActivo");
            list = query.setParameter("ACTIVO", ACTIVO).getResultList();

        } catch (Exception e) {
            // System.out.println("findByActivo() " + e.getLocalizedMessage());
        }
        return list;

    }
// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Optional<Estado> findByPredeterminadoAndActivo(String PREDETERMINADO, String ACTIVO)">
    public Optional<Estado> findByPredeterminadoAndActivo(String PREDETERMINADO, String ACTIVO) {
        try {
            Query query = em.createQuery("SELECT e FROM Estado e WHERE e.PREDETERMINADO = :PREDETERMINADO AND e.ACTIVO = :ACTIVO");

            Estado estado = (Estado) query.setParameter("PREDETERMINADO", PREDETERMINADO).setParameter("ACTIVO", ACTIVO).getSingleResult();
            return Optional.of(estado);
        } catch (Exception e) {
            // System.out.println("findByPredeterminadoAndActivo() " + e.getLocalizedMessage());
        }
        return Optional.empty();

    }
    // </editor-fold>
}
