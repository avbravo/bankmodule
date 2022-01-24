/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.peopleinmotion.horizonreinicioremoto.converter;

import com.peopleinmotion.horizonreinicioremoto.entity.Accion;
import com.peopleinmotion.horizonreinicioremoto.repository.AccionRepository;
import com.peopleinmotion.horizonreinicioremoto.utils.JsfUtil;
import java.math.BigDecimal;
import java.util.Optional;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author avbravo
 */
@Named
@RequestScoped
public class AccionConverter implements Converter {
@Inject
    AccionRepository accionRepository;

    
   
     @Override
    public Object getAsObject(FacesContext context, UIComponent component, String submittedValue) {
        Accion a = new Accion();
        if (accionRepository == null) {
            // System.out.println("Repository is null");
        }

        if (submittedValue == null || submittedValue.isEmpty()) {
            // System.out.println("submitted = null");
            return null;
        }

        try {
            Optional<Accion> optional = accionRepository.findByAccionId(JsfUtil.toBigInteger(Integer.parseInt(submittedValue)));
            if (optional.isPresent()) {
                a = optional.get();
            }
            return a;
        } catch (Exception e) {
            // System.out.println("====================");
            // System.out.println("---> getAsObject" +e.getLocalizedMessage());
            // System.out.println("====================");
            throw new ConverterException(new FacesMessage(submittedValue + " is not a valid selecction from Converter"), e);
        }
    }
    
    
     @Override
    public String getAsString(FacesContext context, UIComponent component, Object modelValue) {
        try {
           if (modelValue == null) {
            return "";
        }

        if (modelValue instanceof Accion) {
          return String.valueOf(((Accion) modelValue).getACCIONID());
        } else {
     
          throw new ConverterException(new FacesMessage(modelValue + " is not a valid from Converter"));
        }
      } catch (Exception e) {
           
            new FacesMessage("Error en converter Accion "+e.getLocalizedMessage());
      }

 return "";
    }

   
}
