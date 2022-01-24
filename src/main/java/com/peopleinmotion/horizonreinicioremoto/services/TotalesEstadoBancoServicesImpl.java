/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.peopleinmotion.horizonreinicioremoto.services;

import com.peopleinmotion.horizonreinicioremoto.domains.TotalesEstadoBanco;
import com.peopleinmotion.horizonreinicioremoto.entity.Banco;
import com.peopleinmotion.horizonreinicioremoto.entity.GrupoEstado;
import com.peopleinmotion.horizonreinicioremoto.jmoordb.JmoordbContext;
import com.peopleinmotion.horizonreinicioremoto.repository.BancoRepository;
import com.peopleinmotion.horizonreinicioremoto.utils.JsfUtil;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author avbravo
 */
@Stateless
public class TotalesEstadoBancoServicesImpl implements TotalesEstadoBancoServices {
// <editor-fold defaultstate="collapsed" desc="fields) ">
    private BigInteger totalSolicitado = new BigInteger("0");
    private BigInteger totalFinalizado = new BigInteger("0");
    private BigInteger totalEnProceso = new BigInteger("0");
    private BigInteger totalNoSePuedeEjecutar = new BigInteger("0");
// </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="@Inject ">
    @Inject
    BancoRepository bancoRepository;
    @Inject
    DashboardServices dashboardServices;
// </editor-fold>

    /**
     * Creates a new instance of TotalesEstadoBancoServicesImpl
     */
    public TotalesEstadoBancoServicesImpl() {
    }

    // <editor-fold defaultstate="collapsed" desc="List<TotalesEstadoBanco> calcularTotales()">
    @Override
    public List<TotalesEstadoBanco> calcularTotales() {
        List<TotalesEstadoBanco> totalesEstadoBancoList = new ArrayList<>();
        try {

            List<Banco> bancoList = new ArrayList<>();
       
            bancoList = bancoRepository.findByEsControlAndActivoList("NO", "SI");
            if (bancoList == null || bancoList.isEmpty()) {
                JsfUtil.warningDialog("Mensaje", "No hay registros de bancos para procesar");
            } else {

                for (Banco banco : bancoList) {
                    List<GrupoEstado> grupoEstadoList = dashboardServices.calcularTotalGrupoEstado(banco);

                    totalSolicitado = dashboardServices.totalSolicitado(grupoEstadoList);
                    totalFinalizado = dashboardServices.totalFinalizado(grupoEstadoList);
                    totalEnProceso = dashboardServices.totalEnProceso(grupoEstadoList);
                    totalNoSePuedeEjecutar = dashboardServices.totalNoSePuedeEjecutar(grupoEstadoList);
                    TotalesEstadoBanco teb
                            = new TotalesEstadoBanco(banco, totalSolicitado, totalFinalizado, totalEnProceso, totalNoSePuedeEjecutar);
                    totalesEstadoBancoList.add(teb);
                }

            }

        } catch (Exception e) {
            JsfUtil.errorMessage(JsfUtil.nameOfMethod() + " " + e.getLocalizedMessage());
        }
        return totalesEstadoBancoList;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="TotalesEstadoBanco calcularTotalesDelBanco()">

    @Override
    public TotalesEstadoBanco calcularTotalesDelBanco() {
        TotalesEstadoBanco totalesEstadoBanco = new TotalesEstadoBanco();
        try{
            Banco banco = (Banco) JmoordbContext.get("banco");
             List<GrupoEstado> grupoEstadoList = dashboardServices.calcularTotalGrupoEstado(banco);

                    totalSolicitado = dashboardServices.totalSolicitado(grupoEstadoList);
                    totalFinalizado = dashboardServices.totalFinalizado(grupoEstadoList);
                    totalEnProceso = dashboardServices.totalEnProceso(grupoEstadoList);
                    totalNoSePuedeEjecutar = dashboardServices.totalNoSePuedeEjecutar(grupoEstadoList);
                    totalesEstadoBanco
                            = new TotalesEstadoBanco(banco, totalSolicitado, totalFinalizado, totalEnProceso, totalNoSePuedeEjecutar);
      } catch (Exception e) {
            JsfUtil.errorMessage(JsfUtil.nameOfMethod() + " " + e.getLocalizedMessage());
        }
        return totalesEstadoBanco;
    }
    // </editor-fold>
}
