/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.peopleinmotion.horizonreinicioremoto.events;
// <editor-fold defaultstate="collapsed" desc="import">

import com.peopleinmotion.horizonreinicioremoto.emails.EmailRecipients;
import com.peopleinmotion.horizonreinicioremoto.entity.EmailConfiguration;
import com.peopleinmotion.horizonreinicioremoto.repository.EmailConfigurationRepository;
import com.peopleinmotion.horizonreinicioremoto.services.EmailConfigurationServices;
import com.peopleinmotion.horizonreinicioremoto.utils.JsfUtil;
import java.util.Optional;
import java.util.concurrent.Future;
import javax.ejb.Stateless;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
// </editor-fold>

/**
 *
 * @author avbravo
 */
@Stateless
public class EmailSenderListener {
    // <editor-fold defaultstate="collapsed" desc="@Inject">

    @Inject
    EmailConfigurationRepository emailConfigurationRepository;
    @Inject
    EmailConfigurationServices emailConfigurationServices;

    // </editor-fold>
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    // <editor-fold defaultstate="collapsed" desc="escuchaEvento(@Observes HistoryEvent historyevt)">
    public void escuchaEvento(@Observes EmailSenderEvent emailSenderevt) {
        try {

            EmailSender emailSender = emailSenderevt.getEmailSender();

            Optional<EmailConfiguration> optional = emailConfigurationRepository.findByActivo("SI");
            if (!optional.isPresent()) {

                JsfUtil.warningMessage("No hay ninguna configuracion de email establecida.");
                return;
            }

            EmailConfiguration emailConfiguration = optional.get();

//                     Agrega el de configuracion como retimente
            emailSender.getEmailList().add(emailConfiguration.getEMAIL());

            EmailRecipients emailRecipients = JsfUtil.divideDestinatary(emailSender.getEmailList());

            Future<String> completableFutureCC
                    = emailConfigurationServices.sendEmailCccBccAsync(
                            emailRecipients.getTo(),
                            emailRecipients.getCc(),
                            emailRecipients.getBcc(),
                            emailSender.getHeader(),
                            emailSender.getMessages(),
                            emailConfiguration.getEMAIL(),
                            JsfUtil.desencriptar(emailConfiguration.getPASSWORD()));

        } catch (Exception e) {
               JsfUtil.errorMessage(JsfUtil.nameOfMethod() + " " + e.getLocalizedMessage());

        }

    }
    // </editor-fold>

}
