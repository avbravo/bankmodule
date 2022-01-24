/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.peopleinmotion.horizonreinicioremoto.converter;

import com.peopleinmotion.horizonreinicioremoto.entity.Estado;
import com.peopleinmotion.horizonreinicioremoto.facade.EstadoFacade;
import java.math.BigDecimal;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;

/**
 *
 * @author avbravo
 */
public class EstadoConverter implements Converter {
@Inject
EstadoFacade estadoAccionFacade;

    public Object getAsObject(FacesContext facesContext, UIComponent component, String string) {
        if (string == null || string.length() == 0) {
            return null;
        }
        BigDecimal id = new BigDecimal(string);
       
        return estadoAccionFacade.find(id);
    }

    public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
        if (object == null) {
            return null;
        }
        if (object instanceof Estado) {
            Estado o = (Estado) object;
            return o.getESTADOID()== null ? "" : o.getESTADOID().toString();
        } else {
            throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: com.avbravo.myjpa.entity.Estado");
        }
    }
    
}
