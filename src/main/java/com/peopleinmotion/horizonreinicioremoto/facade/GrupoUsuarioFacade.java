/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.peopleinmotion.horizonreinicioremoto.facade;

import com.peopleinmotion.horizonreinicioremoto.entity.Grupo;
import com.peopleinmotion.horizonreinicioremoto.entity.GrupoUsuario;
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
public class GrupoUsuarioFacade extends AbstractFacade<GrupoUsuario> {

    @PersistenceContext(unitName = "com.people-inmotion_horizonreinicioremotoejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public GrupoUsuarioFacade() {
        super(GrupoUsuario.class);
    }
    // <editor-fold defaultstate="collapsed" desc="Optional<GrupoUsuario> find(BigInteger id)">


    public Optional<GrupoUsuario> find(BigInteger id) {
         try {
               Query query = em.createNamedQuery("GrupoUsuario.findByGrupoUsuarioId");
        GrupoUsuario grupoUsuario = (GrupoUsuario)query.setParameter("GRUPOUSUARIOID", id).getSingleResult();
         return Optional.of(grupoUsuario);
         } catch (Exception e) {
              ConsoleUtil.error(JsfUtil.nameOfMethod() + " " + e.getLocalizedMessage());
         }
         return Optional.empty();
      
    }// </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Optional<GrupoUsuario> findByGrupoUsuarioId(BigInteger GRUPOUSUARIOID)  ">
    public Optional<GrupoUsuario> findByGrupoUsuarioId(BigInteger GRUPOUSUARIOID) {
         try {
               Query query = em.createNamedQuery("GrupoUsuario.findByGrupoUsuarioId");
        GrupoUsuario grupoUsuario = (GrupoUsuario)query.setParameter("GRUPOUSUARIOID", GRUPOUSUARIOID).getSingleResult();
         return Optional.of(grupoUsuario);
         } catch (Exception e) {
              ConsoleUtil.error(JsfUtil.nameOfMethod() + " " + e.getLocalizedMessage());
         }
         return Optional.empty();
      
    }//editor-fold>
    
     // <editor-fold defaultstate="collapsed" desc="List<GrupoUsuario> findByUsuarioId(Usuario USUARIOID)">
    public List<GrupoUsuario> findByUsuarioId(Usuario USUARIOID) {
        List<GrupoUsuario> list = new ArrayList<>();
        try {

            Query query = em.createQuery("SELECT e FROM GrupoUsuario e WHERE e.USUARIOID = :USUARIOID");
            list = query.setParameter("USUARIOID", USUARIOID).getResultList();
        } catch (Exception ex) {
            System.out.println("findByUsuarioId() " + ex.getLocalizedMessage());
        }
        return list;
    }
// </editor-fold>
     // <editor-fold defaultstate="collapsed" desc="List<GrupoUsuario> findByUsuarioIdAndGrupoId(Usuario USUARIOID, Grupo GRUPOID)">
    public List<GrupoUsuario> findByUsuarioIdAndGrupoId(Usuario USUARIOID, Grupo GRUPOID) {
        List<GrupoUsuario> list = new ArrayList<>();
        try {

            Query query = em.createQuery("SELECT g FROM GrupoUsuario g WHERE g.USUARIOID = :USUARIOID AND g.GRUPOID = :GRUPOID");
            query.setParameter("USUARIOID", USUARIOID);
            list = query.setParameter("GRUPOID", GRUPOID).getResultList();
        } catch (Exception ex) {
            System.out.println("findByUsuarioIdAndGrupoId() " + ex.getLocalizedMessage());
        }
        return list;
    }
// </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="List<GrupoUsuario> findByGrupoId(Grupo GRUPOID)">
    public List<GrupoUsuario> findByGrupoId(Grupo GRUPOID) {
        List<GrupoUsuario> list = new ArrayList<>();
        try {

            Query query = em.createQuery("SELECT e FROM GrupoUsuario e WHERE e.GRUPOID = :GRUPOID");
            list = query.setParameter("GRUPOID", GRUPOID).getResultList();
        } catch (Exception ex) {
            System.out.println("findByGrupoId() " + ex.getLocalizedMessage());
        }
        return list;
    }
// </editor-fold>
    
}
