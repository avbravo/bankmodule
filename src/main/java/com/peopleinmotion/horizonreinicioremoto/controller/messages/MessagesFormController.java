/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.peopleinmotion.horizonreinicioremoto.controller.messages;

import com.peopleinmotion.horizonreinicioremoto.domains.MessagesForm;
import com.peopleinmotion.horizonreinicioremoto.entity.Banco;
import com.peopleinmotion.horizonreinicioremoto.entity.Usuario;
import com.peopleinmotion.horizonreinicioremoto.interfaces.Page;
import com.peopleinmotion.horizonreinicioremoto.jmoordb.JmoordbContext;
import com.peopleinmotion.horizonreinicioremoto.utils.JsfUtil;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import com.peopleinmotion.horizonreinicioremoto.utils.DateUtil;
import java.util.Date;
import lombok.Data;

/**
 *
 * @author avbravo
 */
@Named
@ViewScoped
@Data
public class MessagesFormController implements Serializable , Page{

    // <editor-fold defaultstate="collapsed" desc="field ">
    private static final long serialVersionUID = 1L;
    
    Usuario user = new Usuario();
    Banco bank = new Banco();
        
    MessagesForm messagesForm = new MessagesForm();
   
// </editor-fold>
// <editor-fold defaultstate="collapsed" desc="@Inject ">
    

// </editor-fold>

    /**
     * Creates a new instance of CajeroAccionController
     */
    public MessagesFormController() {
    }

    // <editor-fold defaultstate="collapsed" desc="void init() ">
    @PostConstruct
    public void init() {
        try {
            if(JmoordbContext.get("user")==null){
                
            }else{
            user = (Usuario) JmoordbContext.get("user");
            bank = (Banco) JmoordbContext.get("banco");
           
            if(JmoordbContext.get("messagesForm")== null){
              
                JsfUtil.warningMessage("No se asigno el mensaje para visualizarlo");
            }else{
              
                messagesForm =(MessagesForm)JmoordbContext.get("messagesForm");
              
            }
           
            /**
             * Para el token
             */
           

            /**
             * Buscare las acciones del grupo
             */
           

          
            }

        } catch (Exception e) {
            
            JsfUtil.errorMessage(JsfUtil.nameOfMethod() + " " + e.getLocalizedMessage());

        }

    }

   
    // <editor-fold defaultstate="collapsed" desc="String showDate(Date date) ">

    public String showDate(Date date) {
        return DateUtil.showDate(date);
    }
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="String showHour(Date date) ">

    public String showHour(Date date) {
        return DateUtil.showHour(date);
    }
    // </editor-fold>

   // <editor-fold defaultstate="collapsed" desc="String onCommandButtonReturn() ">
    /**
     * Guarda el evento y envia notificaciones
     *
     * @return
     */
    public String onCommandButtonReturn() {
        try {
                 JmoordbContext.put("pageInView", messagesForm.getReturnTo());
             return messagesForm.getReturnTo();
        } catch (Exception e) {
            JsfUtil.errorMessage(JsfUtil.nameOfMethod()+ " " + e.getLocalizedMessage());
        }
        return "";
    }
// </editor-fold>


}
