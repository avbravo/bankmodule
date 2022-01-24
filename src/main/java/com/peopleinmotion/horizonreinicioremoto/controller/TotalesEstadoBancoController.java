/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.peopleinmotion.horizonreinicioremoto.controller;

import com.peopleinmotion.horizonreinicioremoto.domains.TotalesEstadoBanco;
import com.peopleinmotion.horizonreinicioremoto.entity.Banco;
import com.peopleinmotion.horizonreinicioremoto.entity.Usuario;
import com.peopleinmotion.horizonreinicioremoto.interfaces.Page;
import com.peopleinmotion.horizonreinicioremoto.jmoordb.JmoordbContext;
import com.peopleinmotion.horizonreinicioremoto.paginator.Paginator;
import com.peopleinmotion.horizonreinicioremoto.services.TotalesEstadoBancoServices;
import com.peopleinmotion.horizonreinicioremoto.utils.ConsoleUtil;
import com.peopleinmotion.horizonreinicioremoto.utils.DateUtil;
import com.peopleinmotion.horizonreinicioremoto.utils.JsfUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import lombok.Data;

/**
 *
 * @author avbravo
 */
//@Named(value = "dashboardController")
@Named
@ViewScoped
@Data
public class TotalesEstadoBancoController implements Serializable, Page {

// <editor-fold defaultstate="collapsed" desc="field ">
    private static final long serialVersionUID = 1L;
    private Integer rowForPage = 5;

  

    Usuario user = new Usuario();
    Banco banco = new Banco();
 
    List<TotalesEstadoBanco> totalesEstadoBancoList = new ArrayList<>();
    List<TotalesEstadoBanco> totalesEstadoBancoSelectedList = new ArrayList<>();


// </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="paginator ">
    Paginator paginator = new Paginator();
    Paginator paginatorOld = new Paginator();
// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="injects() ">
    @Inject
    TotalesEstadoBancoServices totalesEstadoBancoServices;
   // </editor-fold>
  
// </editor-fold>
    /**
     * Creates a new instance of DashboadController
     */
    public TotalesEstadoBancoController() {
    }

    // <editor-fold defaultstate="collapsed" desc="init()">
    @PostConstruct
    public void init() {
        try {
           
            totalesEstadoBancoList = new ArrayList<>();
            if (JmoordbContext.get("user") == null) {

            } else {

                /**
                 * Leer de la sesion
                 */
                user = (Usuario) JmoordbContext.get("user");
                banco = (Banco) JmoordbContext.get("banco");

               calcularTotales();

            }
        } catch (Exception e) {
            JsfUtil.errorMessage(JsfUtil.nameOfMethod() + e.getLocalizedMessage());

        }

    }

    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="onCommandButttonCalcularTotales() ">    
    public String calcularTotales() {
        try {
            totalesEstadoBancoList = new ArrayList<>();
             totalesEstadoBancoList = totalesEstadoBancoServices.calcularTotales();
           
        } catch (Exception e) {
            JsfUtil.errorMessage(JsfUtil.nameOfMethod() + " " + e.getLocalizedMessage());
        }
        return "";
    }
// </editor-fold>
}
