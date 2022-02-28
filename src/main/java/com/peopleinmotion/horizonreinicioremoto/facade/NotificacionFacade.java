/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.peopleinmotion.horizonreinicioremoto.facade;

import com.peopleinmotion.horizonreinicioremoto.entity.Banco;
import com.peopleinmotion.horizonreinicioremoto.entity.Cajero;
import com.peopleinmotion.horizonreinicioremoto.entity.Notificacion;
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
public class NotificacionFacade extends AbstractFacade<Notificacion> {

    @PersistenceContext(unitName = "com.people-inmotion_horizonreinicioremotoejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public NotificacionFacade() {
        super(Notificacion.class);
    }

    // <editor-fold defaultstate="collapsed" desc="Optional<Notificacion> find(BigInteger id)">
    public Optional<Notificacion> find(BigInteger id) {
        try {
            Query query = em.createNamedQuery("Notificacion.findByNotificacionId");
            Notificacion grupo = (Notificacion) query.setParameter("NOTIFICACIONID", id).getSingleResult();
            return Optional.of(grupo);
        } catch (Exception e) {
            ConsoleUtil.error(JsfUtil.nameOfMethod() + " " + e.getLocalizedMessage());
        }
        return Optional.empty();

    }
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Optional<Notificacion> findByNotificacionId(BigInteger NOTIFICACIONID)">

    public Optional<Notificacion> findByNotificacionId(BigInteger NOTIFICACIONID) {
        try {
            Query query = em.createNamedQuery("NotificacionEstado.findByNotificacionId");
            Notificacion notificacion = (Notificacion) query.setParameter("NOTIFICACIONID", NOTIFICACIONID).getSingleResult();
            return Optional.of(notificacion);
        } catch (Exception e) {
            ConsoleUtil.error(JsfUtil.nameOfMethod() + " " + e.getLocalizedMessage());
        }
        return Optional.empty();

    }
    // </editor-fold>
    
    
    // <editor-fold defaultstate="collapsed" desc="Optional<Notificacion>  findByIDANDTIPOID(BigInteger ID, String TIPODID)">

    public Optional<Notificacion>  findByIDANDTIPOID(BigInteger ID, String TIPODID) {
        List<Cajero> list = new ArrayList<>();
        try {
            Query query = em.createQuery("SELECT n  FROM Notificacion n WHERE n.ID = :ID AND n.TIPODID = :TIPODID ");
            query.setParameter("ID", ID);
            query.setParameter("TIPODID", TIPODID);
           Notificacion notificacion = (Notificacion) query.getSingleResult();
           return Optional.of(notificacion);
        } catch (Exception e) {

          ConsoleUtil.error(JsfUtil.nameOfMethod() + " " + e.getLocalizedMessage());
        }
       return Optional.empty();
    }
// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="int countBancoIdAndActivo(BigInteger ID, String TIPODID)">
    /**
     * Cuenta los cajeros del banco y por activo
     *
     * @param BANCOID
     * @param ACTIVO
     * @return
     */
    public int countBancoIdAndActivo(BigInteger ID, String TIPODID) {

        try {

            Query query = em.createQuery("SELECT COUNT(n) FROM Notificacion n WHERE n.ID = :ID AND n.TIPODID = :TIPODID ");

            query.setParameter("ID", ID).setParameter("TIPODID", TIPODID).getResultList();
            return ((Long) query.getSingleResult()).intValue();

        } catch (Exception ex) {
            JsfUtil.errorMessage(JsfUtil.nameOfMethod() + " " + ex.getLocalizedMessage());
        }
        return 0;
    }
// </editor-fold>
}
