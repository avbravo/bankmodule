/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.peopleinmotion.horizonreinicioremoto.facade;

import com.peopleinmotion.horizonreinicioremoto.entity.Agenda;
import com.peopleinmotion.horizonreinicioremoto.utils.ConsoleUtil;
import com.peopleinmotion.horizonreinicioremoto.utils.JsfUtil;
import java.math.BigInteger;
import java.util.Date;
import java.util.Optional;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NamedQuery;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TemporalType;
import javax.transaction.Transactional;

/**
 *
 * @author avbravo
 */
@Stateless
public class AgendaFacade extends AbstractFacade<Agenda> {

    @PersistenceContext(unitName = "com.people-inmotion_horizonreinicioremotoejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AgendaFacade() {
        super(Agenda.class);
    }
    
    // <editor-fold defaultstate="collapsed" desc="Optional<Agenda> find(BigInteger id) ">

     public Optional<Agenda> find(BigInteger id) {
         try {
               Query query = em.createNamedQuery("Agenda.findByAgendaId");
        Agenda agenda = (Agenda)query.setParameter("AGENDAID", id).getSingleResult();
         return Optional.of(agenda);
         } catch (Exception e) {
              System.out.println(JsfUtil.nameOfMethod() + " "+e.getLocalizedMessage());
         }
         return Optional.empty();
      
    }
     // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Optional<Agenda> findByAgendaId(BigInteger AGENDAID) ">

     public Optional<Agenda> findByAgendaId(BigInteger AGENDAID) {
         try {
               Query query = em.createNamedQuery("Agenda.findByAgendaId");
        Agenda agenda = (Agenda)query.setParameter("AGENDAID", AGENDAID).getSingleResult();
         return Optional.of(agenda);
         } catch (Exception e) {
             // System.out.println("findByAgendaId() "+e.getLocalizedMessage());
         }
         return Optional.empty();
      
    }
     // </editor-fold>
     // <editor-fold defaultstate="collapsed" desc="Optional<Agenda> findByCodigoTransaccion(String  CODIGOTRANSACCION)">


     public Optional<Agenda> findByCodigoTransaccion(String  CODIGOTRANSACCION) {
         try {
               Query query = em.createNamedQuery("Agenda.findByCodigoTransaccion");
        Agenda agenda = (Agenda)query.setParameter("CODIGOTRANSACCION", CODIGOTRANSACCION).getSingleResult();
         return Optional.of(agenda);
         } catch (Exception e) {
             // System.out.println("findByCodigoTransaccion() "+e.getLocalizedMessage());
         }
         return Optional.empty();
      
    }
     // </editor-fold>
     
     // <editor-fold defaultstate="collapsed" desc="Long countByActivo(String ACTIVO)">    

       public int countByActivo(String ACTIVO) {
        Query query = em.createQuery("SELECT COUNT(a) FROM Agenda a WHERE a.ACTIVO = :ACTIVO");
        return ((Long) query.setParameter("ACTIVO", ACTIVO).getSingleResult()).intValue();
    }
       // </editor-fold>
       
       // <editor-fold defaultstate="collapsed" desc="Long countByEstadoIdAndActivo(BigInteger ESTADOID,String ACTIVO) ">    

       public int countByEstadoIdAndActivo(BigInteger ESTADOID,String ACTIVO) {
        Query query = em.createQuery("SELECT COUNT(a) FROM Agenda a WHERE a.ESTADOID = :ESTADOID AND a.ACTIVO = :ACTIVO");
        return ((Long) query.setParameter("ESTADOID", ESTADOID).setParameter("ACTIVO", ACTIVO).getSingleResult()).intValue();
    }
       // </editor-fold>
       // <editor-fold defaultstate="collapsed" desc="int countByBancoIdAndEstadoIdAndActivo(BigInteger BANCOID,BigInteger ESTADOID,String ACTIVO)">    

       public int countByBancoIdAndEstadoIdAndActivo(BigInteger BANCOID,BigInteger ESTADOID,String ACTIVO) {
           
        Query query = em.createQuery("SELECT COUNT(a) FROM Agenda a WHERE a.BANCOID = :BANCOID AND a.ESTADOID = :ESTADOID AND a.ACTIVO = :ACTIVO");
        return ((Long) query.setParameter("BANCOID", BANCOID).setParameter("ESTADOID", ESTADOID).setParameter("ACTIVO", ACTIVO).getSingleResult()).intValue();
        
  
    }
       // </editor-fold>
       // <editor-fold defaultstate="collapsed" desc="int countAgendamiento(BigInteger BANCOID,BigInteger CAJEROID,BigInteger ACCIONID, BigInteger ESTADOID,Date FECHAAGENDADA , String ACTIVO)">    
/**
 * Cuenta cuantos agendamientos hay con esa condicion
 * @param BANCOID
 * @param CAJEROID
 * @param ACCIONID
 * @param ESTADOID
 * @param FECHAAGENDADA
 * @param ACTIVO
 * @return 
 */
       public int countAgendamiento(BigInteger BANCOID,BigInteger CAJEROID,BigInteger ACCIONID, BigInteger ESTADOID,Date FECHAAGENDADA , String ACTIVO) {
           try {
//                Query query = em.createQuery("SELECT COUNT(a) FROM Agenda a WHERE a.BANCOID = :BANCOID AND a.CAJEROID = :CAJEROID AND a.ACCIONID = :ACCIONID AND a.FECHAAGENDADA = :FECHAAGENDADA  AND a.ESTADOID = :ESTADOID AND a.ACTIVO = :ACTIVO ");
//                Query query = em.createQuery("SELECT COUNT(a) FROM Agenda a WHERE a.BANCOID = :BANCOID AND a.CAJEROID = :CAJEROID AND a.ACCIONID = :ACCIONID AND a.ESTADOID = :ESTADOID AND a.ACTIVO = :ACTIVO AND a.FECHAAGENDADA = :FECHAAGENDADA" );
                Query query = em.createQuery("SELECT COUNT(a) FROM Agenda a WHERE a.BANCOID = :BANCOID AND a.CAJEROID = :CAJEROID AND a.ACCIONID = :ACCIONID AND a.ESTADOID = :ESTADOID AND a.ACTIVO = :ACTIVO AND a.FECHAAGENDADA BETWEEN :DESDE AND :HASTA" );

        
    
           query.setParameter("BANCOID", BANCOID);
          query.setParameter("CAJEROID",CAJEROID);
           query.setParameter("ACCIONID",ACCIONID);
           query.setParameter("ESTADOID",ESTADOID);
          query.setParameter("ACTIVO", ACTIVO);
          query.setParameter("DESDE", FECHAAGENDADA, TemporalType.TIMESTAMP);
          query.setParameter("HASTA", FECHAAGENDADA, TemporalType.TIMESTAMP);
       
                return ((Long) query.getSingleResult()).intValue();
           } catch (Exception e) {
               ConsoleUtil.error(JsfUtil.nameOfMethod() + " "+e.getLocalizedMessage());
           }
       return 0;
       
    }
       // </editor-fold>
}
