/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.peopleinmotion.horizonreinicioremoto.planificador;

import com.peopleinmotion.horizonreinicioremoto.entity.Token;
import com.peopleinmotion.horizonreinicioremoto.repository.TokenRepository;
import com.peopleinmotion.horizonreinicioremoto.utils.DateUtil;
import com.peopleinmotion.horizonreinicioremoto.utils.JsfUtil;
import java.util.List;
import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Provider;


/**
 *
 * @author avbravo
 */
@Stateless
public class Planificador {

    

    // <editor-fold defaultstate="collapsed" desc="@Inject">
    @Inject
   TokenRepository tokenRepository;
// </editor-fold>
  
//  @Schedule( minute = "*/36", hour = "21", persistent = false)
    /**
     *Verifica al final del dia los Tokens que no se han usado y los desabilita.
     */
  @Schedule( minute = "55", hour = "23", persistent = false)
//@Schedule(hour = "*", minute = "*/5", persistent = false)

    public void downToken() {
        try {
            // System.out.println("-----------------------------------------------");
            // System.out.println(" <<<Planificador>>>");
            // System.out.println("Lanzado "+DateUtil.getFechaHoraActual());
            // System.out.println("------------------------------------------------");
           List<Token> list = tokenRepository.findByActivo("SI");
           
           if(list == null  || list.isEmpty()){
               // No hay tokens pemdientes
           }
           else{
               for(Token t:list){
                  if (DateUtil.fechaMayor(DateUtil.getFechaHoraActual(), t.getFECHAVENCIMIENTO())) {
                      t.setACTIVO("NO");
                      t.setVENCIDO("SI");
                      t.setUSADO("XX");
                      tokenRepository.update(t);
                      
                      
                  }
               }
               
           }
        } catch (Exception e) {
                JsfUtil.errorMessage(JsfUtil.nameOfMethod() + " " + e.getLocalizedMessage());
        }
      // System.out.println("Planificador finalizado.....");
    }

}
