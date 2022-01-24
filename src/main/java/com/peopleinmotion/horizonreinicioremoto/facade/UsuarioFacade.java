/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.peopleinmotion.horizonreinicioremoto.facade;

import com.peopleinmotion.horizonreinicioremoto.entity.Banco;
import com.peopleinmotion.horizonreinicioremoto.entity.Usuario;
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
public class UsuarioFacade extends AbstractFacade<Usuario> {

    @PersistenceContext(unitName = "com.people-inmotion_horizonreinicioremotoejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }
// <editor-fold defaultstate="collapsed" desc="Optional<Usuario> find(BigInteger id)  ">


    public Optional<Usuario> find(BigInteger id) {
        try {
            Query query = em.createNamedQuery("Usuario.findByUsuarioId");
            Usuario usuario = (Usuario) query.setParameter("USUARIOID", id).getSingleResult();
            return Optional.of(usuario);
        } catch (Exception e) {
             ConsoleUtil.error(JsfUtil.nameOfMethod() + " " + e.getLocalizedMessage());
        }
        return Optional.empty();

    }
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Optional<Usuario> findByUsuarioId(BigInteger USUARIOID) ">

    public Optional<Usuario> findByUsuarioId(BigInteger USUARIOID) {
        try {
            Query query = em.createNamedQuery("Usuario.findByUsuarioId");
            Usuario usuario = (Usuario) query.setParameter("USUARIOID", USUARIOID).getSingleResult();
            return Optional.of(usuario);
        } catch (Exception e) {
             ConsoleUtil.error(JsfUtil.nameOfMethod() + " " + e.getLocalizedMessage());
        }
        return Optional.empty();

    }
// </editor-fold>
    public List<Usuario> findByUsername(String username) {
        Query query = em.createNamedQuery("Usuario.findByUsername");
        return query.setParameter("USERNAME", username).getResultList();
    }

    public List<Usuario> findByNombre(String nombre) {
        Query query = em.createNamedQuery("Usuario.findByNombre");
        return query.setParameter("nombre", nombre).getResultList();
    }

    public List<Usuario> findByNombreLike(String value) {
        Query query = em.createNamedQuery("Usuario.findByNombreLike");
        value = "%" + value.trim() + "%";
        return query.setParameter("nombre", value).getResultList();
    }

    public Long contadorHabilitado(String value) {
        Query query = em.createNamedQuery("Usuario.contadorHabilitado");
        return (Long) query.setParameter("habilitado", value).getSingleResult();
    }

    // <editor-fold defaultstate="collapsed" desc="List<Usuario> findByBancoId(Banco BANCOID, String ACTIVO)">
    public List<Usuario> findByBancoIdAndActivo(Banco BANCOID, String ACTIVO ) {
        List<Usuario> list = new ArrayList<>();
        try {
            Query query = em.createQuery("SELECT u FROM Usuario u WHERE u.BANCOID = :BANCOID AND u.ACTIVO = :ACTIVO");

            list = query.setParameter("BANCOID", BANCOID).setParameter("ACTIVO",ACTIVO).getResultList();
        } catch (Exception ex) {
            JsfUtil.warningMessage("findByBancoId() " + ex.getLocalizedMessage());
            // System.out.println("findByBancoId() " + ex.getLocalizedMessage());
        }
        return list;
    }

// </editor-fold>
}
