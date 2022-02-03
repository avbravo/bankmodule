/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.peopleinmotion.horizonreinicioremoto.converter;

import com.peopleinmotion.horizonreinicioremoto.entity.Cajero;
import com.peopleinmotion.horizonreinicioremoto.repository.CajeroRepository;
import com.peopleinmotion.horizonreinicioremoto.utils.JsfUtil;
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
public class CajeroConverter implements Converter {
@Inject
    CajeroRepository cajeroRepository;

    
   
     @Override
    public Object getAsObject(FacesContext context, UIComponent component, String submittedValue) {
        Cajero a = new Cajero();
        if (cajeroRepository == null) {

        }

        if (submittedValue == null || submittedValue.isEmpty()) {
            // System.out.println("submitted = null");
            return null;
        }

        try {
            Optional<Cajero> optional = cajeroRepository.findByCajeroId(JsfUtil.toBigInteger(Integer.parseInt(submittedValue)));
            if (optional.isPresent()) {
                a = optional.get();
            }
            return a;
        } catch (Exception e) {

            throw new ConverterException(new FacesMessage(submittedValue + " is not a valid selecction from Converter"), e);
        }
    }
    
    
     @Override
    public String getAsString(FacesContext context, UIComponent component, Object modelValue) {
        try {
           if (modelValue == null) {
            return "";
        }

        if (modelValue instanceof Cajero) {
          return String.valueOf(((Cajero) modelValue).getCAJEROID());
        } else {

          throw new ConverterException(new FacesMessage(modelValue + " is not a valid from Converter"));
        }
      } catch (Exception e) {
   
            new FacesMessage("Error en converter Cajero "+e.getLocalizedMessage());
      }

 return "";
    }
}
