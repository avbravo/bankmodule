/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.peopleinmotion.horizonreinicioremoto.services;



import com.peopleinmotion.horizonreinicioremoto.emails.JmoordbEmailSender;
import java.io.Serializable;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import javax.ejb.Stateless;

/**
 *
 * @author avbravo
 */
@Stateless
public class EmailConfigurationServices implements Serializable {
    
      // <editor-fold defaultstate="collapsed" desc="fields ">
    JmoordbEmailSender jmoordbEmailSender= new  JmoordbEmailSender();
    Exception exception;
// </editor-fold>
 

    // <editor-fold defaultstate="collapsed" desc=" set/get)">
    public Exception getException() {
        return exception;
    }

    public void setException(Exception exception) {
        this.exception = exception;
    }

// </editor-fold>




 
   

   

    
    
    
     // <editor-fold defaultstate="collapsed" desc="Future<String> sendEmailAsync(String emailreceptor, String titulo, String mensaje, String emailemisor, String passwordemisor)">
    public Future<String> sendEmailAsync(String emailreceptor, String titulo, String mensaje, String emailemisor, String passwordemisor) throws InterruptedException {

        CompletableFuture<String> completableFuture
                = new CompletableFuture<>();

        Executors.newCachedThreadPool().submit(new Callable<Object>() {

            @Override
            public Object call() throws Exception {

               jmoordbEmailSender.sendOutlook(emailreceptor, titulo, mensaje, emailemisor, passwordemisor);

                completableFuture.complete("enviado");

                return null;
            }
        });

        return completableFuture;
    }// </editor-fold>
//    // <editor-fold defaultstate="collapsed" desc="Future<String> sendEmailCccBccAsync(String[] to, String[] cc, String[] bcc, String titulo, String mensaje, String emailemisor, String passwordemisor)">
//

    public Future<String> sendEmailCccBccAsync(String[] to, String[] cc, String[] bcc, String titulo, String mensaje, String emailemisor, String passwordemisor) throws InterruptedException {

        CompletableFuture<String> completableFuture
                = new CompletableFuture<>();

        Executors.newCachedThreadPool().submit(new Callable<Object>() {
            @Override
            public Object call() throws Exception {

               jmoordbEmailSender.sendOutlook(to, cc, bcc, titulo, mensaje, emailemisor, passwordemisor, false);
                completableFuture.complete("enviado");

                return null;
            }
        });

        return completableFuture;
    }// </editor-fold>

      
    
 
    
    
}
