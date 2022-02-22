/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.peopleinmotion.horizonreinicioremoto.controller;

// <editor-fold defaultstate="collapsed" desc="import ">

import com.peopleinmotion.horizonreinicioremoto.entity.Banco;
import com.peopleinmotion.horizonreinicioremoto.entity.Cajero;
import com.peopleinmotion.horizonreinicioremoto.entity.Usuario;
import com.peopleinmotion.horizonreinicioremoto.interfaces.Page;
import com.peopleinmotion.horizonreinicioremoto.jmoordb.JmoordbContext;
import com.peopleinmotion.horizonreinicioremoto.paginator.Paginator;
import com.peopleinmotion.horizonreinicioremoto.repository.AccionRecienteRepository;
import com.peopleinmotion.horizonreinicioremoto.repository.AgendaHistorialRepository;
import com.peopleinmotion.horizonreinicioremoto.repository.AgendaRepository;
import com.peopleinmotion.horizonreinicioremoto.repository.BancoRepository;
import com.peopleinmotion.horizonreinicioremoto.repository.CajeroRepository;
import com.peopleinmotion.horizonreinicioremoto.services.AgendaHistorialServices;
import com.peopleinmotion.horizonreinicioremoto.services.DashboardServices;
import com.peopleinmotion.horizonreinicioremoto.utils.ConsoleUtil;
import com.peopleinmotion.horizonreinicioremoto.utils.DateUtil;
import com.peopleinmotion.horizonreinicioremoto.utils.JsfUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import lombok.Data;
import org.primefaces.PrimeFaces;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;
// </editor-fold>
/**
 *
 * @author avbravo
 */
@Named
@ViewScoped
@Data
public class BuscarCajeroController implements Serializable, Page {

// <editor-fold defaultstate="collapsed" desc="field ">
    private static final long serialVersionUID = 1L;

    private Integer rowForPage = 15;
    private Cajero cajeroSelected = new Cajero();

    List<Cajero> cajeroList = new ArrayList<>();
    List<Cajero> cajeroSelectedList = new ArrayList<>();

    Usuario user = new Usuario();
    Banco banco = new Banco();
       private LazyDataModel<Cajero> lazyDataModelCajero;

// </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="paginator ">
    Paginator paginator = new Paginator();
    Paginator paginatorOld = new Paginator();
// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="injects() ">
    @Inject
    AgendaRepository agendaRepository;
    @Inject
    AgendaHistorialRepository agendaHistorialRepository;

    @Inject
    AgendaHistorialServices agendaHistorialServices;
    @Inject
    BancoRepository bancoRepository;
    @Inject
    CajeroRepository cajeroRepository;
    @Inject
    DashboardServices dashboardServices;
    @Inject
    AccionRecienteRepository accionRecienteRepository;
// </editor-fold>

    /**
     * Creates a new instance of DashboadController
     */
    public BuscarCajeroController() {
    }

    // <editor-fold defaultstate="collapsed" desc="init()">
    @PostConstruct
    public void init() {
        try {
            

 
            if (JmoordbContext.get("user") == null) {

            } else {

                /**
                 * Leer de la sesion
                 */
                user = (Usuario) JmoordbContext.get("user");
                banco = (Banco) JmoordbContext.get("banco");

                cajeroList = cajeroRepository.findByBancoIdAndActivo(banco,"SI");
if(JsfUtil.contextToInteger("rowForPage") != null){
                    rowForPage=JsfUtil.contextToInteger("rowForPage");
                }

            }
            
            
            this.lazyDataModelCajero = new LazyDataModel<Cajero>() {
                @Override
                public List<Cajero> load(int offset, int pageSize, Map<String, SortMeta> sortBy, Map<String, FilterMeta> filterBy) {

                    
                         QuerySQL querySQL = new QuerySQL.Builder()
                        .query("SELECT b FROM Banco b WHERE b.ESCONTROL = 'NO' AND b.ACTIVO = 'SI' ")
                        .count("SELECT COUNT(b) FROM Banco b WHERE b.ESCONTROL = 'NO' AND b.ACTIVO = 'SI'")
                        .build();
                        
                System.out.println("Voy a contar cuantos hay");

                Integer count = bancoRepository.count(querySQL);
                    Integer totalRecords = areaServices.countJsonQuery(JmoordbDocument.bsonToJson(paginator.getQuery()));

                    List<Paginator> list = processLazyDataModel(paginator, paginatorOld, offset, rowPage.get(), totalRecords, sortBy);
                    paginator = list.get(0);
                    paginatorOld = list.get(1);
                    paginator.setNumberOfPage(numberOfPages(totalRecords, rowPage.get()));

                    List<Cajero> result = areaServices.jsonQuery(
                            JmoordbDocument.documentToJson(paginator.getQuery()),
                            JmoordbDocument.documentToJson(paginator.getSort()),
                            paginator.getPage(),
                            rowPage.get());

                    lazyDataModelCajero.setRowCount(totalRecords);
                    PrimeFaces.current().executeScript("setDataTableWithPageStart()");
                    return result;
                }

            };
            
        } catch (Exception e) {
            JsfUtil.errorMessage(JsfUtil.nameOfMethod() + e.getLocalizedMessage());

        }

    }

    // </editor-fold>
// <editor-fold defaultstate="collapsed" desc="onCommandButtonSelectCajero ">
    public String onCommandButtonSelectCajero(Cajero cajero) {
        try {
            JmoordbContext.put("cajero", cajero);

            JsfUtil.infoDialog("Selecciono el cajero ", cajero.getCAJEROID().toString());
        } catch (Exception e) {
            JsfUtil.errorMessage(JsfUtil.nameOfMethod() + " " + e.getLocalizedMessage());
        }
        JmoordbContext.put("pageInView", "cajeroencontrado.xhtml");
        return "cajeroencontrado.xhtml";
    }
// </editor-fold>

}
