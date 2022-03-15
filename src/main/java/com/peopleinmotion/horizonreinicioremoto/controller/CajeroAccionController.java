/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.peopleinmotion.horizonreinicioremoto.controller;

import com.peopleinmotion.horizonreinicioremoto.entity.Accion;
import com.peopleinmotion.horizonreinicioremoto.entity.Banco;
import com.peopleinmotion.horizonreinicioremoto.entity.Cajero;
import com.peopleinmotion.horizonreinicioremoto.entity.GrupoAccion;
import com.peopleinmotion.horizonreinicioremoto.entity.Usuario;
import com.peopleinmotion.horizonreinicioremoto.interfaces.Page;
import com.peopleinmotion.horizonreinicioremoto.jmoordb.JmoordbContext;
import com.peopleinmotion.horizonreinicioremoto.utils.JsfUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import com.peopleinmotion.horizonreinicioremoto.repository.AccionRepository;
import com.peopleinmotion.horizonreinicioremoto.repository.CajeroRepository;
import com.peopleinmotion.horizonreinicioremoto.repository.GrupoAccionRepository;

/**
 *
 * @author avbravo
 */
@Named
@ViewScoped
public class CajeroAccionController implements Serializable , Page{

    // <editor-fold defaultstate="collapsed" desc="field ">
    private static final long serialVersionUID = 1L;
    private Cajero cajero = new Cajero();
    Usuario user = new Usuario();
    Banco bank = new Banco();
    GrupoAccion grupoAccion = new GrupoAccion();
    List<Accion> accionList = new ArrayList<>();
    Accion selectOneMenuAccionValue = new Accion();
// </editor-fold>
// <editor-fold defaultstate="collapsed" desc="@Inject ">

@Inject
    GrupoAccionRepository grupoAccionRepository;
    @Inject
    CajeroRepository cajeroRepository;
    @Inject
    AccionRepository accionRepository;
   
   
// </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="set/get() ">

    public GrupoAccion getGrupoAccion() {
        return grupoAccion;
    }

    public void setGrupoAccion(GrupoAccion grupoAccion) {
        this.grupoAccion = grupoAccion;
    }
    
    
    
    
    public List<Accion> getAccionList() {
        return accionList;
    }

    public void setAccionList(List<Accion> accionList) {
        this.accionList = accionList;
    }

    public Accion getSelectOneMenuAccionValue() {
        return selectOneMenuAccionValue;
    }

    public void setSelectOneMenuAccionValue(Accion selectOneMenuAccionValue) {
        this.selectOneMenuAccionValue = selectOneMenuAccionValue;
    }

   
    public void setBank(Banco bank) {
        this.bank = bank;
    }

    public Cajero getCajero() {
        return cajero;
    }

    public void setCajero(Cajero cajero) {
        this.cajero = cajero;
    }

    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }

    public Banco getBank() {
        return bank;
    }

// </editor-fold>
    /**
     * Creates a new instance of CajeroAccionController
     */
    public CajeroAccionController() {
    }

    @PostConstruct
    public void init() {
        try {
           
             if(JmoordbContext.get("user")==null){
                
            }else{
           
            user = (Usuario) JmoordbContext.get("user");
            bank = (Banco) JmoordbContext.get("banco");
            cajero = (Cajero) JmoordbContext.get("cajero");
            grupoAccion =(GrupoAccion)JmoordbContext.get("grupoAccion");
           
           
            
            /**
             * Buscare las acciones del grupo
             */
            
                // System.out.println(" Buscare las acciones del grupo........"+grupoAccion.getGRUPOACCION());
                 if(grupoAccion.getGRUPOACCION().toUpperCase().trim().equals("BAJAR PLANTILLA")){
                     // System.out.println(".... es bajar plantilla");
                      accionList = accionRepository.findByGrupoAccionIdAndPredeterminado(grupoAccion,"SI");
                 }else{
                     // System.out.println("no es bajar plantilla");
                        accionList = accionRepository.findByGrupoAccionId(grupoAccion);
                }
              
                
                if (accionList == null || accionList.isEmpty()) {
                  
                    JsfUtil.warningMessage("No acciones para el grupo seleccionado");
                } 
               

             }

        } catch (Exception e) {
   
            JsfUtil.errorMessage(JsfUtil.nameOfMethod() + " "+ e.getLocalizedMessage());
           

        }

    }

    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="String onAccionChange()">
    public String onSelectOneMenuAccionChange() {
        try {

            if (selectOneMenuAccionValue == null || selectOneMenuAccionValue.getACCION() == null) {
                //No se ha seleccionado departamemto
                JmoordbContext.put("accion", selectOneMenuAccionValue);
                JsfUtil.infoDialog("onAccionChangee()"," Nada Seleccionado");
            }else{
                JsfUtil.infoDialog("onAccionChangee() ", selectOneMenuAccionValue.getACCION());
                
                JmoordbContext.put("accion", selectOneMenuAccionValue);
            }

        } catch (Exception e) {
            JsfUtil.errorMessage(JsfUtil.nameOfMethod() + " " + e.getLocalizedMessage());
        }
        return "";
    }
// </editor-fold>

}
