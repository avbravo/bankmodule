/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.peopleinmotion.horizonreinicioremoto.browser;

import com.peopleinmotion.horizonreinicioremoto.jmoordb.JmoordbContext;
import com.peopleinmotion.horizonreinicioremoto.utils.ConsoleUtil;
import com.peopleinmotion.horizonreinicioremoto.utils.JsfUtil;
import java.io.Serializable;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author avbravo
 */
@Named
@ViewScoped
public class NavigatorController implements Serializable{

    // <editor-fold defaultstate="collapsed" desc="fields ">
        private static final long serialVersionUID = 1L;
// </editor-fold>
    /**
     * Creates a new instance of NavigatorContoller
     */
    public NavigatorController() {
    }
    
    public String go(String url){
      
         JmoordbContext.put("pageInView", url);
        return url.trim();
    }
}
