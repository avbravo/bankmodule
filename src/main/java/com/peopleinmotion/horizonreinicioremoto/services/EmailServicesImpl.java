/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.peopleinmotion.horizonreinicioremoto.services;

import com.peopleinmotion.horizonreinicioremoto.emails.EmailRecipients;
import com.peopleinmotion.horizonreinicioremoto.emails.JmoordbEmailSender;
import com.peopleinmotion.horizonreinicioremoto.entity.Accion;
import com.peopleinmotion.horizonreinicioremoto.entity.AccionReciente;
import com.peopleinmotion.horizonreinicioremoto.entity.Banco;
import com.peopleinmotion.horizonreinicioremoto.entity.Cajero;
import com.peopleinmotion.horizonreinicioremoto.entity.EmailConfiguration;
import com.peopleinmotion.horizonreinicioremoto.entity.Token;
import com.peopleinmotion.horizonreinicioremoto.entity.Usuario;
import com.peopleinmotion.horizonreinicioremoto.repository.BancoRepository;
import com.peopleinmotion.horizonreinicioremoto.repository.EmailConfigurationRepository;
import com.peopleinmotion.horizonreinicioremoto.repository.UsuarioRepository;
import com.peopleinmotion.horizonreinicioremoto.utils.DateUtil;
import com.peopleinmotion.horizonreinicioremoto.utils.JsfUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author avbravo
 */
@Stateless
public class EmailServicesImpl implements EmailServices {
   // <editor-fold defaultstate="collapsed" desc="@Inject ">

    @Inject
    BancoRepository bancoRepository;
    @Inject
    UsuarioRepository usuarioRepository;
    @Inject
    EmailConfigurationRepository emailConfigurationRepository;
// </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="fields ">
    JmoordbEmailSender jmoordbEmailSender = new JmoordbEmailSender();
// </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="String generateMessages(AccionReciente accionReciente, String header, Usuario usuario, Cajero cajero, Banco banco)">

    @Override
    public String generateMessages(AccionReciente accionReciente, String header, Usuario usuario, Cajero cajero, Banco banco) {
        String messages = "";
        try {

            String supervisadoMessage = "";

            messages = "\n  "
                    + "\n----------------------ACCION RECIENTE---------------------------------------"
                    + "\n"
                    + header
                    + "\n"
                    + accionReciente.getTITULO()
                    + "\nFecha : "
                    + DateUtil.showDate(accionReciente.getFECHAAGENDADA())
                    + " "
                    + DateUtil.showHour(accionReciente.getFECHAAGENDADA())
                    + "\nMensaje: "
                    + accionReciente.getMENSAJE()
                    + "\nCajero: "
                    + cajero.getCAJERO()
                    + "\nBanco: "
                    + banco.getBANCO()
                    + "\nEstado: "
                    + accionReciente.getESTADO()
                    + "\nAccion generada por: "
                    + usuario.getNOMBRE()
                    + "\n\n\b"
                    + "\nPor favor no responda este correo..."
                    + "\n-------------------------------------------------------------";

        } catch (Exception e) {
            JsfUtil.warningMessage("generateMessages() " + e.getLocalizedMessage());
        }
        return messages;

    }
// </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="String generateTokenMessages(AccionReciente accionReciente, String header, Usuario usuario, Cajero cajero, Banco banco)">

    @Override
    public String generateTokenMessages(Token token, String header, Usuario usuario) {
        String messages = "";
        try {

            String supervisadoMessage = "";

            messages = "\n  "
                    + "\n----------------------TOKEN GENERADO---------------------------------------"
                    + "\n"
                    + header
                    + "\n"
                    + "\nToken generado: "
                    + token.getTOKEN()
                    + "\nUtilicelo antes de : "
                    + DateUtil.showDate(token.getFECHAVENCIMIENTO())
                    + " "
                    + DateUtil.showHour(token.getFECHAVENCIMIENTO())                   
                    + "\n\n\b"
                    + "\nPor favor no responda este correo..."
                    + "\n-------------------------------------------------------------";

        } catch (Exception e) {
            JsfUtil.warningMessage("generateMessages() " + e.getLocalizedMessage());
        }
        return messages;

    }
// </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="String send(String message, List<String> emailList) ">

    @Override
    public String send(String message, List<String> emailList) {
        try {
            if (emailList == null || emailList.isEmpty() || emailList.size() == 0) {

                JsfUtil.warningMessage("No hay usuarios con los perfiles para enviarles notificicaciones.");
            }
        } catch (Exception e) {
            JsfUtil.warningMessage("send() " + e.getLocalizedMessage());
        }
        return "";
    }
    // </editor-fold>

// <editor-fold defaultstate="collapsed" desc="List<String> generateEmailList(List<Usuario> usuarioList) ">
    @Override
    public List<String> generateEmailList(List<Usuario> usuarioList) {
        List<String> emailList = new ArrayList<>();
        try {
            for (Usuario u : usuarioList) {
                if (u.getEMAIL() == null || u.getEMAIL().equals("")) {

                } else {
                    emailList.add(u.getEMAIL());
                }

            }
        } catch (Exception e) {
            JsfUtil.warningMessage("generateEmailList() " + e.getLocalizedMessage());
        }
        return emailList;
    }// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Future<String> sendEmailAsync(String emailreceptor, String titulo, String mensaje, String emailemisor, String passwordemisor)">
    @Override
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

    @Override
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

    // <editor-fold defaultstate="collapsed" desc="Boolean sendEmailToTecnicos(AccionReciente accionReciente, Accion accion, Usuario usuario, Cajero cajero, Banco banco)">    

    @Override
    public Boolean sendEmailToTecnicos(AccionReciente accionReciente, Accion accion, Usuario usuario, Cajero cajero, Banco banco) {
        try {
            //
            /*
             Enviar email a los tecnicos
             */
            /**
             * Localizo el codigo banco de control
             */
            Optional<Banco> bancoOptional = bancoRepository.findByEsControlAndActivo("SI", "SI");
            if (!bancoOptional.isPresent()) {
                JsfUtil.warningMessage("No existe el banco de control para ser usado para localizar tecnicos.");
                return Boolean.FALSE;
            }

            List<Usuario> usuarioList = usuarioRepository.findByBancoIdAndActivo(bancoOptional.get(), "SI");
            if (usuarioList == null || usuarioList.isEmpty()) {
                JsfUtil.warningMessage("No usuarios asignados al banco de control");
                return Boolean.FALSE;
            }

            List<String> emailList = generateEmailList(usuarioList);
            String message = generateMessages(accionReciente, accion.getACCION(), usuario, cajero, banco);

            //Busco el email
            Optional<EmailConfiguration> emailConfigurationOptional = emailConfigurationRepository.findByActivo("SI");
            if (!emailConfigurationOptional.isPresent()) {

                JsfUtil.warningMessage("No hay ninguna configuracion de email establecida.");
                return Boolean.FALSE;
            }
            /**
             * divide los emails
             */
            EmailRecipients emailRecipients = JsfUtil.divideDestinatary(emailList);

            Future<String> completableFutureCC
                    = sendEmailCccBccAsync(
                            emailRecipients.getTo(),
                            emailRecipients.getCc(),
                            emailRecipients.getBcc(),
                            accion.getACCION(),
                            message,
                            emailConfigurationOptional.get().getEMAIL(),
                            JsfUtil.desencriptar(emailConfigurationOptional.get().getPASSWORD()));
            return Boolean.TRUE;
        } catch (Exception e) {
            JsfUtil.warningMessage("sendEmailToTecnicos() " + e.getLocalizedMessage());
        }
        return Boolean.FALSE;
    }
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Boolean sendEmailToTecnicosHeader(AccionReciente accionReciente, String header, Usuario usuario, Cajero cajero, Banco banco)">    

    @Override
    public Boolean sendEmailToTecnicosHeader(AccionReciente accionReciente, String header, Usuario usuario, Cajero cajero, Banco banco) {
        try {
            //
            /*
             Enviar email a los tecnicos
             */
            /**
             * Localizo el codigo banco de control
             */
            Optional<Banco> bancoOptional = bancoRepository.findByEsControlAndActivo("SI", "SI");
            if (!bancoOptional.isPresent()) {
                JsfUtil.warningMessage("No existe el banco de control para ser usado para localizar tecnicos.");
                return Boolean.FALSE;
            }

            List<Usuario> usuarioList = usuarioRepository.findByBancoIdAndActivo(bancoOptional.get(), "SI");
            if (usuarioList == null || usuarioList.isEmpty()) {
                JsfUtil.warningMessage("No usuarios asignados al banco de control");
                return Boolean.FALSE;
            }

            List<String> emailList = generateEmailList(usuarioList);
            String message = generateMessages(accionReciente, header, usuario, cajero, banco);

            //Busco el email
            Optional<EmailConfiguration> emailConfigurationOptional = emailConfigurationRepository.findByActivo("SI");
            if (!emailConfigurationOptional.isPresent()) {

                JsfUtil.warningMessage("No hay ninguna configuracion de email establecida.");
                return Boolean.FALSE;
            }
            /**
             * divide los emails
             */
            EmailRecipients emailRecipients = JsfUtil.divideDestinatary(emailList);

            Future<String> completableFutureCC
                    = sendEmailCccBccAsync(
                            emailRecipients.getTo(),
                            emailRecipients.getCc(),
                            emailRecipients.getBcc(),
                            header,
                            message,
                            emailConfigurationOptional.get().getEMAIL(),
                            JsfUtil.desencriptar(emailConfigurationOptional.get().getPASSWORD()));
            return Boolean.TRUE;
        } catch (Exception e) {
            JsfUtil.warningMessage("sendEmailToTecnicos() " + e.getLocalizedMessage());
        }
        return Boolean.FALSE;
    }
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Boolean sendTokenToEmail(Token token, Usuario usuario)">    

    @Override
    public Boolean sendTokenToEmail(Token token, Usuario usuario) {
        try {
            //
            /*
             Enviar email a los tecnicos
             */
           
            List<String> emailList = new ArrayList<>();
            emailList.add(usuario.getEMAIL());
            String message = generateTokenMessages(token, "Generacion Token", usuario);

            //Busco el email
            Optional<EmailConfiguration> emailConfigurationOptional = emailConfigurationRepository.findByActivo("SI");
            if (!emailConfigurationOptional.isPresent()) {

                JsfUtil.warningMessage("No hay ninguna configuracion de email establecida.");
                return Boolean.FALSE;
            }
            /**
             * divide los emails
             */
            EmailRecipients emailRecipients = JsfUtil.divideDestinatary(emailList);
            
            Future<String> completableFutureCC
                    = sendEmailCccBccAsync(
                            emailRecipients.getTo(),
                            emailRecipients.getCc(),
                            emailRecipients.getBcc(),
                            "Token",
                            message,
                            emailConfigurationOptional.get().getEMAIL(),
                            JsfUtil.desencriptar(emailConfigurationOptional.get().getPASSWORD()));
//            if(completableFutureCC.isDone()){
//                 System.out.println("test---> Tarea de envio completada...");
//            }else{
//                 System.out.println("test---> Tarea de envio no completada..,...");
//            }
            return Boolean.TRUE;
        } catch (Exception e) {
            JsfUtil.warningMessage(JsfUtil.nameOfMethod() + " "+ e.getLocalizedMessage());
        }
        return Boolean.FALSE;
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Boolean sendTokenToEmailSincrono(Token token, Usuario usuario)">

    @Override
    public Boolean sendTokenToEmailSincrono(Token token, Usuario usuario) {
        try {
            //
            /*
             Enviar el token al uuario
             */
          
            String message = generateTokenMessages(token, "Generacion Token", usuario);

            //Busco el email
            Optional<EmailConfiguration> emailConfigurationOptional = emailConfigurationRepository.findByActivo("SI");
            if (!emailConfigurationOptional.isPresent()) {

                JsfUtil.warningMessage("No hay ninguna configuracion de email establecida.");
                return Boolean.FALSE;
            }
            
           return     jmoordbEmailSender.sendOutlook(usuario.getEMAIL(), "Token", message, 
                    emailConfigurationOptional.get().getEMAIL(), 
                    JsfUtil.desencriptar(emailConfigurationOptional.get().getPASSWORD()),
                            Boolean.FALSE);
             

  
        } catch (Exception e) {
            JsfUtil.warningMessage(JsfUtil.nameOfMethod() + " " + e.getLocalizedMessage());
        }
        return Boolean.FALSE;
    }
    // </editor-fold>
    
    
    
   
}
