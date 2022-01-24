/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.peopleinmotion.horizonreinicioremoto.services;

import com.peopleinmotion.horizonreinicioremoto.entity.AccionReciente;
import com.peopleinmotion.horizonreinicioremoto.entity.Banco;
import com.peopleinmotion.horizonreinicioremoto.entity.Cajero;
import com.peopleinmotion.horizonreinicioremoto.entity.Estado;
import com.peopleinmotion.horizonreinicioremoto.entity.GrupoEstado;
import com.peopleinmotion.horizonreinicioremoto.jmoordb.JmoordbContext;
import com.peopleinmotion.horizonreinicioremoto.paginator.QuerySQL;
import com.peopleinmotion.horizonreinicioremoto.repository.AccionRecienteRepository;
import com.peopleinmotion.horizonreinicioremoto.repository.AgendaRepository;
import com.peopleinmotion.horizonreinicioremoto.repository.CajeroRepository;
import com.peopleinmotion.horizonreinicioremoto.repository.EstadoRepository;
import com.peopleinmotion.horizonreinicioremoto.repository.GrupoEstadoRepository;
import com.peopleinmotion.horizonreinicioremoto.utils.JsfUtil;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.Query;

/**
 *
 * @author avbravo
 */
@Stateless
public class DashboardServicesImpl implements DashboardServices {
    
    // <editor-fold defaultstate="collapsed" desc="@Inject">

    @Inject
    GrupoEstadoRepository grupoEstadoRepository;
    
    @Inject
    EstadoRepository estadoRepository;
    
    @Inject
    AccionRecienteRepository accionRecienteRepository;
    @Inject
    AgendaRepository agendaRepository;
    @Inject
    CajeroRepository cajeroRepository;
// </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="  List<GrupoEstado> calcularTotalGrupoEstado(Banco banco)">
    @Override
    public List<GrupoEstado> calcularTotalGrupoEstado(Banco banco) {
        List<GrupoEstado> grupoEstadoList = new ArrayList<>();
        try {
            
            grupoEstadoList = grupoEstadoRepository.findByActivo("SI");
            if (grupoEstadoList == null || grupoEstadoList.isEmpty()) {
                JsfUtil.warningMessage("No hay grupos de estados disponibles para realizar las operaciones");
            } else {
                Integer n = 0;
                for (GrupoEstado ge : grupoEstadoList) {
                    grupoEstadoList.get(n++).setTOTAL(JsfUtil.toBigInteger(0));
                }
                
                List<Estado> estadoList = estadoRepository.findByActivo("SI");
                if (estadoList == null || estadoList.isEmpty()) {
                    JsfUtil.warningMessage("No hay estados disponibles para realizar las operaciones");
                } else {
                    Integer count = 0;
                    for (Estado e : estadoList) {
                        // Cuenta los estados activos del banco
                        QuerySQL query= new QuerySQL.Builder()
                                .query("")
                                .count("SELECT COUNT(a) FROM AccionReciente a WHERE a.BANCOID = '"+banco.getBANCOID() +"' AND a.ESTADOID = '"+ e.getESTADOID()+"' AND a.ACTIVO = 'SI'")                               
                                .build();
                        count = accionRecienteRepository.count(query);
                        
                        
                        //count = agendaRepository.countByBancoIdAndEstadoIdAndActivo(banco.getBANCOID(), e.getESTADOID(), "SI");
                        /**
                         * Actualizo los totales del grupo
                         */
                        Integer c = 0;
                        for (GrupoEstado ge : grupoEstadoList) {
                            if (ge.getGRUPOESTADOID().equals(e.getGRUPOESTADOID().getGRUPOESTADOID())) {
                                BigInteger total = grupoEstadoList.get(c).getTOTAL().add(JsfUtil.toBigInteger(count));
                                grupoEstadoList.get(c).setTOTAL(total);
                            }
                            c++;
                        }
                    }
                }
            }
            
        } catch (Exception e) {
            JsfUtil.errorMessage(JsfUtil.nameOfMethod() + " "+ e.getLocalizedMessage());
        }
        return grupoEstadoList;
    }
// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="BigInteger totalSolicitado(List<GrupoEstado> grupoEstadoList)">
    @Override
    public BigInteger totalSolicitado(List<GrupoEstado> grupoEstadoList) {
        BigInteger total = new BigInteger("0");
        try {
            for (GrupoEstado ge : grupoEstadoList) {
              
                if (ge.getGRUPOESTADOID().equals(JsfUtil.contextToBigInteger("grupoEstadoSolicitadoId"))) {
                    total = ge.getTOTAL();
                    break;
                }

            }
            
        } catch (Exception e) {
          JsfUtil.errorMessage(JsfUtil.nameOfMethod() + " " + e.getLocalizedMessage());
        }
        return total;
    }
// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="BigInteger totalEnProceso(List<GrupoEstado> grupoEstadoList)  ">
    @Override
    public BigInteger totalEnProceso(List<GrupoEstado> grupoEstadoList) {
        BigInteger total = new BigInteger("0");
        try {
             for (GrupoEstado ge : grupoEstadoList) {
                if (ge.getGRUPOESTADOID().equals(JsfUtil.contextToBigInteger("grupoEstadoEnprocesoId"))) {
                    total = ge.getTOTAL();
                    break;
                }
            }
        } catch (Exception e) {
            JsfUtil.errorMessage(JsfUtil.nameOfMethod() + " " + e.getLocalizedMessage());
        }
        return total;
    }
// </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="BigInteger totalFinalizado(List<GrupoEstado> grupoEstadoList) ">

    @Override
    public BigInteger totalFinalizado(List<GrupoEstado> grupoEstadoList) {
        BigInteger total = new BigInteger("0");
        try {
             for (GrupoEstado ge : grupoEstadoList) {
                if (ge.getGRUPOESTADOID().equals(JsfUtil.contextToBigInteger("grupoEstadoFinalizadoId"))) {
                    total = ge.getTOTAL();
                    break;
                }
            }
        } catch (Exception e) {
         JsfUtil.errorMessage(JsfUtil.nameOfMethod() + " " + e.getLocalizedMessage());
        }
        return total;
    }
// </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="BigInteger totalNoSePuedeEjecutar(List<GrupoEstado> list)">

    @Override
    public BigInteger totalNoSePuedeEjecutar(List<GrupoEstado> grupoEstadoList) {
        BigInteger total = new BigInteger("0");
        try {
             for (GrupoEstado ge : grupoEstadoList) {
                if (ge.getGRUPOESTADOID().equals(JsfUtil.contextToBigInteger("grupoEstadoNoSepuedejecutarId"))) {
                    total = ge.getTOTAL();
                    break;
                }
            }
        } catch (Exception e) {
        JsfUtil.errorMessage(JsfUtil.nameOfMethod() + " " + e.getLocalizedMessage());
        }
        return total;
    }
    // </editor-fold>

   
    // <editor-fold defaultstate="collapsed" desc=Boolean drawRowsAgendamiento(List<AccionReciente> accionRecienteList) ">

    @Override
    public Boolean drawRowsAgendamiento(List<AccionReciente> accionRecienteList) {
        try {
            Integer index = (Integer) JmoordbContext.get("index");

            if (accionRecienteList == null || accionRecienteList.isEmpty() || accionRecienteList.size() == 0) {
                return Boolean.FALSE;

            }

            switch (index) {
                case 0:
                case 4:
                case 8:
                case 12:
                case 16:
                case 20:
                case 24:
                case 28:
                case 32:
                case 36:
                case 40:
                case 44:
                case 48:
                case 52:

                    return Boolean.TRUE;
            }
        } catch (Exception e) {
            JsfUtil.errorMessage(JsfUtil.nameOfMethod()+ " "+ e.getLocalizedMessage());
        }
        return Boolean.FALSE;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="String onCommandButtonSelectAccionReciente(AccionReciente accionReciente, String formularioretorno)">

    @Override
    public String onCommandButtonSelectAccionReciente(AccionReciente accionReciente, String formularioretorno) {
        try {
            JmoordbContext.put("accionRecienteDashboard", accionReciente);
            JmoordbContext.put("formularioRetorno", formularioretorno);
            Optional<Cajero> cajeroOptional = cajeroRepository.findByCajeroId(accionReciente.getCAJEROID());

            if (!cajeroOptional.isPresent()) {

            } else {
                Cajero cajero = cajeroOptional.get();
                JmoordbContext.put("cajero", cajero);
            }
        } catch (Exception e) {
            JsfUtil.errorMessage(JsfUtil.nameOfMethod()+ " " + e.getLocalizedMessage());
        }
        return "";
    }
    // </editor-fold>

    
    // <editor-fold defaultstate="collapsed" desc="String generarSiglas(AccionReciente accionReciente)">

    @Override
    public String generarSiglas(AccionReciente accionReciente) {
        String siglas="";
        try {
             if (accionReciente.getESTADOID().equals(JsfUtil.contextToBigInteger("estadoEnEsperaDeEjecucionId"))) {
                                siglas = "S";
                            } else {
                                if (accionReciente.getESTADOID().equals(JsfUtil.contextToBigInteger("estadoProcesandoId"))) {
                                    siglas = "P";
                                } else {
                                    if (accionReciente.getESTADOID().equals(JsfUtil.contextToBigInteger("estadoFinalizadoId"))) {
                                        siglas = "F";
                                    } else {
                                        if (accionReciente.getESTADOID().equals(JsfUtil.contextToBigInteger("estadoAcciónNoSePuedeEjecutarId"))) {
                                            siglas = "N";
                                        } else {
                                            if (accionReciente.getESTADOID().equals(JsfUtil.contextToBigInteger("estadoRobotNoDisponibleId"))) {
                                                siglas = "R";
                                            } else {

                                            }
                                        }
                                    }
                                }
                            }
        } catch (Exception e) {
               JsfUtil.errorMessage(JsfUtil.nameOfMethod()+ " " + e.getLocalizedMessage());
        }
        return siglas;
    }
    // </editor-fold>
}
