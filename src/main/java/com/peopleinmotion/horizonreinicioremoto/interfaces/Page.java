/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.peopleinmotion.horizonreinicioremoto.interfaces;

import com.peopleinmotion.horizonreinicioremoto.jmoordb.JmoordbContext;
import com.peopleinmotion.horizonreinicioremoto.utils.ConsoleUtil;
import com.peopleinmotion.horizonreinicioremoto.utils.DateUtil;
import com.peopleinmotion.horizonreinicioremoto.utils.JsfUtil;

/**
 *
 * @author avbravo
 */
public interface Page {

    // <editor-fold defaultstate="collapsed" desc=" String browserEventFromPage(String pageLogin)>
    default  public String browserEventFromPage(String pageLogin) {

        String pageInView = "";
        try {
          
            pageInView = (String) JmoordbContext.get("pageInView");
            
            if(pageInView == null){
                pageInView ="";
            }else{
                
                Boolean loged= Boolean.FALSE;
               if (JmoordbContext.get("user") != null) {
                   loged= Boolean.TRUE;
               }
                  pageInView = (pageInView == null ? (loged ? "" : pageLogin) : pageInView);
                  
                  

            }
          
           
            return pageInView;


        } catch (Exception e) {
            JsfUtil.errorMessage(JsfUtil.nameOfMethod() + " " + e.getLocalizedMessage());
             ConsoleUtil.error(JsfUtil.nameOfMethod() + " " + e.getLocalizedMessage());
        }
        return pageInView;

    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="String browserEventFromLogin(String toIndex) ">
   default public String browserEventFromLogin(String toIndex) {
        String pageInView = "";
        try {
          
            pageInView = (String) JmoordbContext.get("pageInView");   
            
             Boolean loged= Boolean.FALSE;
               if (JmoordbContext.get("user") != null) {
                   loged= Boolean.TRUE;
               }
            if(pageInView == null){
                pageInView ="";
            }else{
                  pageInView = (pageInView == null ? (loged ? toIndex : "") : pageInView);                   
            }                    
            return pageInView;

        } catch (Exception e) {
            JsfUtil.errorMessage(JsfUtil.nameOfMethod() + " " + e.getLocalizedMessage());
             ConsoleUtil.error(JsfUtil.nameOfMethod() + " " + e.getLocalizedMessage());
        }
        return pageInView;
    }
    // </editor-fold>
}
