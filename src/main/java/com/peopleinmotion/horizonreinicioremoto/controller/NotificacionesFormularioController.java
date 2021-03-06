/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.peopleinmotion.horizonreinicioremoto.controller;

import com.peopleinmotion.horizonreinicioremoto.domains.MessagesForm;
import com.peopleinmotion.horizonreinicioremoto.domains.TokenReader;
import com.peopleinmotion.horizonreinicioremoto.entity.AccionReciente;
import com.peopleinmotion.horizonreinicioremoto.entity.Agenda;
import com.peopleinmotion.horizonreinicioremoto.entity.Banco;
import com.peopleinmotion.horizonreinicioremoto.entity.Cajero;
import com.peopleinmotion.horizonreinicioremoto.entity.Estado;
import com.peopleinmotion.horizonreinicioremoto.entity.GrupoAccion;
import com.peopleinmotion.horizonreinicioremoto.entity.Token;
import com.peopleinmotion.horizonreinicioremoto.entity.Usuario;
import com.peopleinmotion.horizonreinicioremoto.interfaces.Page;
import com.peopleinmotion.horizonreinicioremoto.jmoordb.JmoordbContext;
import com.peopleinmotion.horizonreinicioremoto.repository.AccionRecienteRepository;
import com.peopleinmotion.horizonreinicioremoto.repository.AgendaHistorialRepository;
import com.peopleinmotion.horizonreinicioremoto.repository.AgendaRepository;
import com.peopleinmotion.horizonreinicioremoto.repository.EstadoRepository;
import com.peopleinmotion.horizonreinicioremoto.repository.GrupoAccionRepository;
import com.peopleinmotion.horizonreinicioremoto.repository.TokenRepository;
import com.peopleinmotion.horizonreinicioremoto.services.AccionRecienteServices;
import com.peopleinmotion.horizonreinicioremoto.services.AgendaHistorialServices;
import com.peopleinmotion.horizonreinicioremoto.services.EmailServices;
import com.peopleinmotion.horizonreinicioremoto.services.TokenServices;
import com.peopleinmotion.horizonreinicioremoto.utils.ConsoleUtil;
import com.peopleinmotion.horizonreinicioremoto.utils.DateUtil;
import com.peopleinmotion.horizonreinicioremoto.utils.JsfUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import lombok.Data;
import org.primefaces.PrimeFaces;

/**
 *
 * @author avbravo
 */
@Named
@ViewScoped
@Data
public class NotificacionesFormularioController implements Serializable, Page {

    // <editor-fold defaultstate="collapsed" desc="field ">
    private static final long serialVersionUID = 1L;
    private Cajero cajero = new Cajero();
    Usuario user = new Usuario();
    Banco bank = new Banco();
    AccionReciente accionReciente = new AccionReciente();
    AccionReciente accionRecienteOld = new AccionReciente();
    List<GrupoAccion> grupoAccionList = new ArrayList<>();
    Boolean haveAccionReciente = Boolean.FALSE;

    private Boolean showCommandButtonFinalizar = Boolean.FALSE;
    private Boolean showCommandButtonProcesando = Boolean.FALSE;
    private TokenReader tokenReader = new TokenReader();
    private Boolean tokenEnviado = Boolean.FALSE;
    private Boolean updateByOtherUser = Boolean.FALSE;
    private Boolean showCommandButtonReagendar = Boolean.FALSE;
// </editor-fold>

// <editor-fold defaultstate="collapsed" desc="@Inject ">
    @Inject
    GrupoAccionRepository grupoAccionRepository;
    @Inject
    AccionRecienteRepository accionRecienteRepository;
    @Inject
    AccionRecienteServices accionRecienteServices;
    @Inject
    AgendaRepository agendaRepository;
    @Inject
    AgendaHistorialRepository agendaHistorialRepository;
    @Inject
    EmailServices emailServices;
    @Inject
    AgendaHistorialServices agendaHistorialServices;
    @Inject
    EstadoRepository estadoRepository;

    @Inject
    TokenRepository tokenRepository;
    @Inject
    TokenServices tokenServices;

// </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Boolean getShowCommandButtonProcesando() ">
    public Boolean getShowCommandButtonProcesando() {
        showCommandButtonProcesando = Boolean.FALSE;
        try {

            if (accionReciente.getESTADOID().equals(JsfUtil.contextToBigInteger("estadoEnEsperaDeEjecucionId"))) {
                showCommandButtonProcesando = Boolean.TRUE;
            } else {
                showCommandButtonProcesando = Boolean.FALSE;
            }

        } catch (Exception e) {
            JsfUtil.errorMessage(JsfUtil.nameOfMethod() + " " + e.getLocalizedMessage());
        }
        return showCommandButtonProcesando;
    }

    // </editor-fold> 
    // <editor-fold defaultstate="collapsed" desc="Boolean getShowCommandButtonFinalizar() ">
    public Boolean getShowCommandButtonFinalizar() {
        showCommandButtonFinalizar = Boolean.FALSE;
        try {
            if (accionReciente.getESTADOID().equals(JsfUtil.contextToBigInteger("estadoProcesandoId"))) {
                showCommandButtonFinalizar = Boolean.TRUE;

            } else {
                showCommandButtonFinalizar = Boolean.FALSE;
            }

        } catch (Exception e) {
            JsfUtil.errorMessage(JsfUtil.nameOfMethod() + " " + e.getLocalizedMessage());
        }
        return showCommandButtonFinalizar;
    }

// </editor-fold>
    /**
     * Creates a new instance of CajeroAccionController
     */
    public NotificacionesFormularioController() {
    }

    // <editor-fold defaultstate="collapsed" desc="init() ">
    @PostConstruct
    public void init() {
        try {

            updateByOtherUser = Boolean.FALSE;
            if (JmoordbContext.get("user") == null) {

            } else {
                haveAccionReciente = Boolean.FALSE;
                grupoAccionList = new ArrayList<>();
                user = (Usuario) JmoordbContext.get("user");
                bank = (Banco) JmoordbContext.get("banco");
                accionReciente = (AccionReciente) JmoordbContext.get("accionRecienteDashboard");
                JsfUtil.copyBeans(accionRecienteOld, accionReciente);
                cajero = (Cajero) JmoordbContext.get("cajero");
                haveAccionReciente = Boolean.TRUE;
            }

        } catch (Exception e) {
            JsfUtil.errorMessage(JsfUtil.nameOfMethod() + " " + e.getLocalizedMessage());

        }

    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="fillSelectOneMenuGrupoAccion() ">
    public String fillSelectOneMenuGrupoAccion() {
        try {
            grupoAccionList = grupoAccionRepository.findAll();
        } catch (Exception e) {
            JsfUtil.errorMessage(JsfUtil.nameOfMethod() + " " + e.getLocalizedMessage());
        }
        return "";
    }
// </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="String onCommandButtonGrupoAccion(GrupoAccion grupoAccion) ">

    /**
     * Se ejecuta cuando se selecciona un grupo de accion
     *
     * @param grupoAccion
     * @return
     */
    public String onCommandButtonGrupoAccion(GrupoAccion grupoAccion) {
        try {
            JmoordbContext.put("grupoAccion", grupoAccion);

            if (grupoAccion.getGRUPOACCIONID().equals(JsfUtil.contextToBigInteger("grupoAccionEncenderSubirPlantillaId"))) {
                JmoordbContext.put("pageInView", "subirplantilla.xhtml");

                return "subirplantilla.xhtml";
            }
            if (grupoAccion.getGRUPOACCIONID().equals(JsfUtil.contextToBigInteger("grupoAccionReinicioRemotoId"))) {
                JmoordbContext.put("pageInView", "reinicioremoto.xhtml");
                return "reinicioremoto.xhtml";
            }
            if (grupoAccion.getGRUPOACCIONID().equals(JsfUtil.contextToBigInteger("grupoAccionBajarPlantillaId"))) {
                JmoordbContext.put("pageInView", "bajarplantilla.xhtml");
                return "bajarplantilla.xhtml";
            }
            JsfUtil.warningMessage("No se identific?? el grupo de acci??n para continuar esta operaci??n");

        } catch (Exception e) {
            JsfUtil.errorMessage(JsfUtil.nameOfMethod() + " " + e.getLocalizedMessage());
        }
        return "";
    }
// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="findAccionReciente()">
    /**
     * Busca la ultima accion reciente del cajero
     *
     * @return
     */
    private String findAccionReciente() {
        try {
            Optional<AccionReciente> accionRecienteOptional = accionRecienteRepository.findByBancoIdAndCajeroIdUltimaAccionReciente(bank.getBANCOID(), cajero.getCAJEROID());
            if (accionRecienteOptional.isPresent()) {
                accionReciente = accionRecienteOptional.get();
                haveAccionReciente = Boolean.TRUE;
                PrimeFaces.current().ajax().update("form:growl");

            } else {

                PrimeFaces.current().ajax().update("form:growl");

            }
        } catch (Exception e) {
            JsfUtil.errorMessage(JsfUtil.nameOfMethod() + " " + e.getLocalizedMessage());
            PrimeFaces.current().ajax().update("form:growl");

        }
        return "";
    }
// </editor-fold>
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

    // <editor-fold defaultstate="collapsed" desc="Boolean renderedByEstadoSolicitado()">
    public Boolean renderedByEstadoSolicitado() {
        return accionRecienteServices.renderedByEstadoSolicitado(accionReciente);

    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="String onCommandButtonReagendar()">
    public String onCommandButtonEjecutar() {

        try {

//            if (!tokenEnviado) {
//                JsfUtil.warningMessage("Usted debe solicite primero un token");
//                return "";
//            }
//            if (!validateToken()) {
//                return "";
//            }

//            /**
//             * Valida si fue cambiado por otro usuario
//             */
  if (accionReciente.getAUTORIZADO().trim().toUpperCase().equals("PE")) {
                JsfUtil.warningMessage("Seleccione un estado diferente para proceder a realizar la acci??n.");
                return "";
            }
            if (accionRecienteServices.changed(accionRecienteOld)) {
                MessagesForm messagesForm = new MessagesForm.Builder()
                        .errorWindows(Boolean.TRUE)
                        .id(accionReciente.getCAJERO())
                        .header("Operaci??n incompleta")
                        .header2("La acci??n no fue completada")
                        .image("robot01.png")
                        .libary("images")
                        .titulo("Cambio de estado a procesando")
                        .mensaje("Otro usuario modifico este registro mientras usted lo editaba. ")
                        .returnTo("buscarcajero.xhtml")
                        .build();
                JmoordbContext.put("messagesForm", messagesForm);
                JmoordbContext.put("pageInView", "messagesform.xhtml");
                return "messagesform.xhtml";
            }
//
//            Estado estado = new Estado();
//            Optional<Estado> optional = estadoRepository.findByEstadoId(JsfUtil.contextToBigInteger("estadoProcesandoId"));
//            if (!optional.isPresent()) {
//                JsfUtil.warningMessage("No se ha encontado el estado predeterminado para asignalor a esta operacion.");
//            } else {
//                estado = optional.get();
//            }
            Estado estado = new Estado();
            Optional<Estado> optional = estadoRepository.findByEstadoId(accionReciente.getESTADOID());
            if (!optional.isPresent()) {

                JsfUtil.warningMessage("No se ha encontado el estado predeterminado para asignalor a esta operacion.");
            } else {
                estado = optional.get();

            }
            accionReciente.setFECHA(DateUtil.getFechaHoraActual());
            if (accionRecienteRepository.update(accionReciente)) {
//                //Actualizar la agenda
//
                Optional<Agenda> agendaOptional = agendaRepository.findByAgendaId(accionReciente.getAGENDAID());
                if (!agendaOptional.isPresent()) {
                    JsfUtil.warningMessage("No se encontr?? registros de ese agendamiento");

                    return "";
                } else {
                    Agenda agenda = agendaOptional.get();
                    agenda.setFECHAAGENDADA(accionReciente.getFECHAAGENDADA());

                    if (agendaRepository.update(agenda)) {
                        String texto = "PENDIENTE";
                                switch(accionReciente.getAUTORIZADO()){
                                    case "SI":
                                        texto="AUTORIZADO";
                                        break;
                                    case "NO":
                                        texto="RECHAZADO";
                                        break;
                                    case "PE":
                                            texto="PEMDIENTE";
                                            break;
                                }
                                
                        agendaHistorialServices.createHistorial(agendaOptional.get(), "SE CAMBI?? LA AUTORIZACI??N A " +texto, estado, user, "BA");

                        JmoordbContext.put("accionReciente", accionReciente);
                        emailServices.sendEmailToTecnicosHeader(accionReciente, "SE CAMBI?? LA AUTORIZACI??N", user, cajero, bank);

                        /*
                        *Mensajes ??xitosos
                         */
                        MessagesForm messagesForm = new MessagesForm.Builder()
                                .errorWindows(Boolean.FALSE)
                                .id(accionReciente.getCAJERO())
                                .header("Operaci??n exitosa")
                                .header2("La acci??n se realiz?? exitosamente")
                                .image("atm-green01.png")
                                .libary("images")
                                .titulo("Se cambi?? el estado de autorizado")
                                .mensaje("Se realiz?? con ??xito el cambio de autorizado a " + showAutorizadoName(accionReciente.getAUTORIZADO()))
                                .returnTo("dashboard.xhtml")
                                .build();
                        JmoordbContext.put("messagesForm", messagesForm);
                        JmoordbContext.put("pageInView", "messagesform.xhtml");
                        return "messagesform.xhtml";
                    } else {
                        JsfUtil.warningMessage("No se puede actualizar la agenda...");
                        return "";
                    }

                }
            } else {

                JsfUtil.warningMessage("No se pudo actualizar la agenda reciente");
            }
        } catch (Exception e) {
            JsfUtil.errorMessage(JsfUtil.nameOfMethod() + e.getLocalizedMessage());
        }
        return "";
    }

// </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="String onCommandButtonReagendar()">
    public String onCommandButtonEjecutarOld() {

        try {

            if (!tokenEnviado) {
                JsfUtil.warningMessage("Usted debe solicite primero un token");
                return "";
            }
            if (!validateToken()) {
                return "";
            }

            /**
             * Valida si fue cambiado por otro usuario
             */
            if (accionRecienteServices.changed(accionRecienteOld)) {
                MessagesForm messagesForm = new MessagesForm.Builder()
                        .errorWindows(Boolean.TRUE)
                        .id(accionReciente.getCAJERO())
                        .header("Operaci??n incompleta")
                        .header2("La acci??n no fue completada")
                        .image("robot01.png")
                        .libary("images")
                        .titulo("Cambio de estado a procesando")
                        .mensaje("Otro usuario modifico este registro mientras usted lo editaba. ")
                        .returnTo("buscarcajero.xhtml")
                        .build();
                JmoordbContext.put("messagesForm", messagesForm);
                JmoordbContext.put("pageInView", "messagesform.xhtml");
                return "messagesform.xhtml";
            }

            Estado estado = new Estado();
            Optional<Estado> optional = estadoRepository.findByEstadoId(JsfUtil.contextToBigInteger("estadoProcesandoId"));
            if (!optional.isPresent()) {

                JsfUtil.warningMessage("No se ha encontado el estado predeterminado para asignalor a esta operacion.");
            } else {
                estado = optional.get();
            }
            accionReciente.setFECHA(DateUtil.getFechaHoraActual());
            if (accionRecienteRepository.update(accionReciente)) {
                //Actualizar la agenda

                Optional<Agenda> agendaOptional = agendaRepository.findByAgendaId(accionReciente.getAGENDAID());
                if (!agendaOptional.isPresent()) {
                    JsfUtil.warningMessage("No se encontro registros de ese agendamiento");

                    return "";
                } else {
                    Agenda agenda = agendaOptional.get();
                    agenda.setFECHAAGENDADA(accionReciente.getFECHAAGENDADA());

                    if (agendaRepository.update(agenda)) {
                        agendaHistorialServices.createHistorial(agendaOptional.get(), "SE CAMBI?? LA AUTORIZACI??N", estado, user, "BA");

                        JmoordbContext.put("accionReciente", accionReciente);
                        emailServices.sendEmailToTecnicosHeader(accionReciente, "SE CAMBI?? LA AUTORIZACI??N", user, cajero, bank);

                        /*
                        *Mensajes ??xitosos
                         */
                        MessagesForm messagesForm = new MessagesForm.Builder()
                                .errorWindows(Boolean.FALSE)
                                .id(accionReciente.getCAJERO())
                                .header("Operaci??n exitosa")
                                .header2("La acci??n se realiz?? exitosamente")
                                .image("atm-green01.png")
                                .libary("images")
                                .titulo("Se cambi?? el estado de autorizado")
                                .mensaje("Se realiz?? con ??xito el cambio de autorizado a " + showAutorizadoName(accionReciente.getAUTORIZADO()))
                                .returnTo("dashboard.xhtml")
                                .build();
                        JmoordbContext.put("messagesForm", messagesForm);
                        JmoordbContext.put("pageInView", "messagesform.xhtml");
                        return "messagesform.xhtml";
                    } else {
                        JsfUtil.warningMessage("No se puede actualizar la agenda...");
                        return "";
                    }

                }
            } else {

                JsfUtil.warningMessage("No se pudo actualizar la agenda reciente");
            }
        } catch (Exception e) {
            JsfUtil.errorMessage(JsfUtil.nameOfMethod() + e.getLocalizedMessage());
        }
        return "";
    }

// </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="String onCommandButtonCancelar()">
    public String onCommandButtonCancelar() {

        try {
            //PrimeFaces.current().executeScript("PF('bancoDialog').hide()");
            if (!tokenEnviado) {
                JsfUtil.warningMessage("Usted debe solicite primero un token");
                return "";
            }
            if (!validateToken()) {
                return "";
            }
            /**
             * Valida si fue cambiado por otro usuario
             */

            if (accionRecienteServices.changed(accionRecienteOld)) {
                MessagesForm messagesForm = new MessagesForm.Builder()
                        .errorWindows(Boolean.TRUE)
                        .id(accionReciente.getCAJERO())
                        .header("Operaci??n incompleta")
                        .header2("La acci??n no fue completada")
                        .image("robot01.png")
                        .libary("images")
                        .titulo("Cambio de estado a finalizado")
                        .mensaje("Otro usuario modifico este registro mientras usted lo editaba. ")
                        .returnTo("buscarcajero.xhtml")
                        .build();
                JmoordbContext.put("messagesForm", messagesForm);
                JmoordbContext.put("pageInView", "messagesform.xhtml");
                return "messagesform.xhtml";
            }

            Estado estado = new Estado();
            Optional<Estado> optional = estadoRepository.findByEstadoId(JsfUtil.contextToBigInteger("estadoFinalizadoId"));
            if (!optional.isPresent()) {

                JsfUtil.warningMessage("No se ha encontado el estado predeterminado para asignalor a esta operacion.");
            } else {
                estado = optional.get();

            }

            accionReciente.setACTIVO("NO");
            accionReciente.setFECHA(DateUtil.getFechaHoraActual());
            if (accionRecienteRepository.update(accionReciente)) {
                //Actualizar la agenda

                Optional<Agenda> agendaOptional = agendaRepository.findByAgendaId(accionReciente.getAGENDAID());
                if (!agendaOptional.isPresent()) {
                    JsfUtil.warningMessage("No se encontro registros de ese agendamiento");

                    return "";
                } else {
                    Agenda agenda = agendaOptional.get();
                    agenda.setACTIVO("NO");
                    agenda.setFECHA(DateUtil.getFechaHoraActual());
                    if (agendaRepository.update(agenda)) {
                        agendaHistorialServices.createHistorial(agendaOptional.get(), "SE CANCEL?? EL EVENTO", estado, user, "BA");

                        JmoordbContext.put("accionReciente", accionReciente);
                        emailServices.sendEmailToTecnicosHeader(accionReciente, "SE CANCEL?? EL EVENTO", user, cajero, bank);

                        /*
                        *Mensajes ??xitosos
                         */
                        MessagesForm messagesForm = new MessagesForm.Builder()
                                .errorWindows(Boolean.FALSE)
                                .id(accionReciente.getCAJERO())
                                .header("Operaci??n exitosa")
                                .header2("La acci??n se realiz?? exitosamente")
                                .image("atm-green01.png")
                                .libary("images")
                                .titulo("Cancelaci??n de evento")
                                .mensaje("Se realiz?? exitosamente la cancelaci??n del evento")
                                .returnTo("dashboard.xhtml")
                                .build();
                        JmoordbContext.put("messagesForm", messagesForm);
                        JmoordbContext.put("pageInView", "messagesform.xhtml");
                        return "messagesform.xhtml";
                    } else {
                        JsfUtil.warningMessage("No se puede actualizar la agenda...");
                        return "";
                    }

                }
            } else {

                JsfUtil.warningMessage("No se pudo actualizar la agenda reciente");
            }
        } catch (Exception e) {
            JsfUtil.errorMessage(JsfUtil.nameOfMethod() + e.getLocalizedMessage());
        }
        return "";
    }

// </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="reagendarAccion() ">
    public String reagendarAccion() {
        try {
            accionReciente.setFECHA(DateUtil.getFechaHoraActual());
            Estado estado = new Estado();
            Optional<Estado> optional = estadoRepository.findByEstadoId(accionReciente.getESTADOID());
            if (!optional.isPresent()) {

                JsfUtil.warningMessage("No se ha encontado el estado predeterminado para asignalor a esta operacion.");
            } else {
                estado = optional.get();

            }
            if (accionRecienteRepository.update(accionReciente)) {
                //Actualizar la agenda
                Optional<Agenda> agendaOptional = agendaRepository.findByAgendaId(accionReciente.getAGENDAID());
                if (!agendaOptional.isPresent()) {
                    JsfUtil.warningMessage("No se encontro registros de ese agendamiento");
                    return "";
                } else {
                    Agenda agenda = agendaOptional.get();
                    agenda.setFECHAAGENDADA(accionReciente.getFECHAAGENDADA());

                    if (agendaRepository.update(agenda)) {
                        agendaHistorialServices.createHistorial(agendaOptional.get(), "REAGENDAR ACCION", estado, user, "BA");

                        JmoordbContext.put("accionReciente", accionReciente);
                        emailServices.sendEmailToTecnicosHeader(accionReciente, "REAGENDAR ACCION", user, cajero, bank);
                        /*
                        *Mensajes ??xitosos
                         */
                        MessagesForm messagesForm = new MessagesForm.Builder()
                                .errorWindows(Boolean.FALSE)
                                .id(accionReciente.getCAJERO())
                                .header("Operaci??n exitosa")
                                .header2("La acci??n se realiz?? exitosamente")
                                .image("atm-green01.png")
                                .libary("images")
                                .titulo("Reagendar acci??n")
                                .mensaje("Se realiz?? exitosamente el reagendamiento ")
                                .returnTo("dashboard.xhtml")
                                .build();
                        JmoordbContext.put("messagesForm", messagesForm);
                        JmoordbContext.put("pageInView", "messagesform.xhtml");
                        return "messagesform.xhtml";
                    } else {
                        JsfUtil.warningMessage("No se puede actualizar la agenda...");
                        return "";
                    }

                }
            } else {
                JsfUtil.warningMessage("No se pudo actualizar la agenda reciente");
            }
        } catch (Exception e) {
            JsfUtil.errorMessage(JsfUtil.nameOfMethod() + " " + e.getLocalizedMessage());
        }
        return "";
    }

// </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="regresar() ">    
    public String regresar() {
        try {
            if (JmoordbContext.get("formularioRetorno") == null) {
                JsfUtil.warningMessage("No se especifico la pagina de retorno");
                return "";
            }
            String retorno = (String) JmoordbContext.get("formularioRetorno");
            JmoordbContext.put("pageInView", retorno);
            return retorno;
        } catch (Exception e) {
            JsfUtil.errorMessage(JsfUtil.nameOfMethod() + " " + e.getLocalizedMessage());
        }
        return "";
    }
// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="onCommandButtonSendToken()">
    public String onCommandButtonSendToken(Boolean isReagendar) {

        try {

            this.showCommandButtonReagendar = isReagendar;
            if (accionReciente.getAUTORIZADO().trim().toUpperCase().equals("PE")) {
                JsfUtil.warningMessage("Seleccione un estado diferente para proceder a realizar la acci??n.");
                return "";
            }
            sendToken(isReagendar);

        } catch (Exception e) {
            JsfUtil.errorMessage(JsfUtil.nameOfMethod() + e.getLocalizedMessage());

        }
        return "";
    }
// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="String sendToken(Boolean isReagendar) ">
    public String sendToken(Boolean isReagendar) {
        try {

            tokenEnviado = Boolean.FALSE;
            Token token = tokenServices.supplier();

            if (tokenRepository.create(token)) {
                //Envia el token sincrono y valida si fue o no enviado.
                if (!emailServices.sendTokenToEmailSincrono(token, user)) {
                    JsfUtil.errorMessage("No se logr?? enviar el token a su correo. Reintente la operaci??n");
                    tokenEnviado = Boolean.FALSE;

                } else {
                    JsfUtil.successMessage("El token fue enviado a su correo.");
                    tokenEnviado = Boolean.TRUE;

                    openDialogToken(isReagendar);
                }
                //Envia el token al usuario

            } else {
                JsfUtil.warningMessage("No se pudo generar el token. Repita la acci??n");
            }

        } catch (Exception e) {
            JsfUtil.errorMessage(JsfUtil.nameOfMethod() + " " + e.getLocalizedMessage());
        }
        return "";
    }
// </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Boolean validateToken() ">    

    public Boolean validateToken() {
        try {
            String tokenIngresado = tokenReader.getNumber1().trim() + tokenReader.getNumber2().trim() + tokenReader.getNumber3().trim() + tokenReader.getNumber4().trim();
            return tokenServices.validateToken(user, tokenIngresado);
        } catch (Exception e) {
            JsfUtil.errorMessage(JsfUtil.nameOfMethod() + " " + e.getLocalizedMessage());
        }
        return Boolean.FALSE;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="String remoteCommand()">
    public String remoteCommand() {
        return "";
    }
// </editor-fold>            
    // <editor-fold defaultstate="collapsed" desc="String marcarNumero() ">

    /**
     * Se usa para marcar el numero del tokem
     *
     * @param numero
     * @return
     */
    public String marcarNumero(String numero) {

        try {

            tokenReader = tokenServices.marcarToken(numero, tokenReader);

        } catch (Exception e) {
            JsfUtil.errorMessage(JsfUtil.nameOfMethod() + " " + e.getLocalizedMessage());
        }
        return "";
    }
// </editor-fold> 

    // <editor-fold defaultstate="collapsed" desc="String openDialogToken()">
    public String openDialogToken(Boolean isReagendar) {
        try {
            if (isReagendar) {
                PrimeFaces.current().executeScript("PF('widgetVarTokenDialogEjecutar').initPosition()");
                PrimeFaces.current().executeScript("PF('widgetVarTokenDialogEjecutar').show()");

            } else {
                PrimeFaces.current().executeScript("PF('widgetVarTokenDialog').initPosition()");
                PrimeFaces.current().executeScript("PF('widgetVarTokenDialog').show()");

            }

        } catch (Exception e) {
            JsfUtil.errorMessage(JsfUtil.nameOfMethod() + " " + e.getLocalizedMessage());
        }
        return "";
    }
// </editor-fold>

// <editor-fold defaultstate="collapsed" desc="method ">
    public String showAutorizadoName(String autorizado) {
        String text = "";
        autorizado = autorizado.toUpperCase();
        try {
            switch (autorizado) {
                case "PE":
                    text = "Pendiente";
                    break;
                case "SI":
                    text = "Autorizado";
                    break;
                case "NO":
                    text = "Denegado";
                    break;

            }
        } catch (Exception e) {
            JsfUtil.errorMessage(JsfUtil.nameOfMethod() + " " + e.getLocalizedMessage());
        }
        return text;
    }
// </editor-fold>
}
