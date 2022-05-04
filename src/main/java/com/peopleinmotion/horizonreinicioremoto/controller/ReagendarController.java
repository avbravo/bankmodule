/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.peopleinmotion.horizonreinicioremoto.controller;

import com.peopleinmotion.horizonreinicioremoto.domains.MessagesForm;
import com.peopleinmotion.horizonreinicioremoto.domains.TokenReader;
import com.peopleinmotion.horizonreinicioremoto.entity.Accion;
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
import com.peopleinmotion.horizonreinicioremoto.repository.AccionRepository;
import com.peopleinmotion.horizonreinicioremoto.repository.AgendaHistorialRepository;
import com.peopleinmotion.horizonreinicioremoto.repository.AgendaRepository;
import com.peopleinmotion.horizonreinicioremoto.repository.EstadoRepository;
import com.peopleinmotion.horizonreinicioremoto.repository.GrupoAccionRepository;
import com.peopleinmotion.horizonreinicioremoto.repository.TokenRepository;
import com.peopleinmotion.horizonreinicioremoto.services.AccionRecienteServices;
import com.peopleinmotion.horizonreinicioremoto.services.AgendaHistorialServices;
import com.peopleinmotion.horizonreinicioremoto.services.AgendaServices;
import com.peopleinmotion.horizonreinicioremoto.services.EmailServices;
import com.peopleinmotion.horizonreinicioremoto.services.NotificacionServices;
import com.peopleinmotion.horizonreinicioremoto.services.TokenServices;
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
public class ReagendarController implements Serializable, Page {

    // <editor-fold defaultstate="collapsed" desc="field ">
    private static final long serialVersionUID = 1L;
    private Cajero cajero = new Cajero();
    Usuario user = new Usuario();
    Banco bank = new Banco();
    AccionReciente accionReciente = new AccionReciente();
    AccionReciente accionRecienteOld = new AccionReciente();
    Accion accion = new Accion();
    Estado estado = new Estado();
    List<GrupoAccion> grupoAccionList = new ArrayList<>();
    Boolean haveAccionReciente = Boolean.FALSE;

    private Boolean showCommandButtonFinalizar = Boolean.FALSE;
    private Boolean showCommandButtonProcesando = Boolean.FALSE;
    private TokenReader tokenReader = new TokenReader();
    private Boolean tokenEnviado = Boolean.FALSE;
    private Boolean updateByOtherUser = Boolean.FALSE;
    private Boolean showCommandButtonReagendar = Boolean.FALSE;
    private Boolean showCommandButtonEncenderSubirPlantilla = Boolean.FALSE;
    private Boolean showCommandButtonCerrar = Boolean.FALSE;

    GrupoAccion grupoAccionEncenderSubirPlantilla = new GrupoAccion();
    List<Accion> accionList = new ArrayList<>();
    Accion selectOneMenuAccionValue = new Accion();
    private Date fechahoraBaja;

// </editor-fold>
// <editor-fold defaultstate="collapsed" desc="@Inject ">
    @Inject
    AccionRepository accionRepository;
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
    AgendaHistorialServices agendaHistorialServices;
    @Inject
    EstadoRepository estadoRepository;

    @Inject
    AgendaServices agendaServices;
    @Inject
    EmailServices emailServices;
    @Inject
    TokenRepository tokenRepository;
    @Inject
    TokenServices tokenServices;
    @Inject
    NotificacionServices notificacionServices;

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
    public ReagendarController() {
    }

    // <editor-fold defaultstate="collapsed" desc="init() ">
    @PostConstruct
    public void init() {
        try {
            showCommandButtonReagendar = Boolean.FALSE;
            updateByOtherUser = Boolean.FALSE;
            showCommandButtonEncenderSubirPlantilla = Boolean.FALSE;
            showCommandButtonCerrar = Boolean.FALSE;
            if (JmoordbContext.get("user") == null) {

            } else {
                haveAccionReciente = Boolean.FALSE;
                grupoAccionList = new ArrayList<>();
                user = (Usuario) JmoordbContext.get("user");
                bank = (Banco) JmoordbContext.get("banco");
                // findAccionReciente();
                accionReciente = (AccionReciente) JmoordbContext.get("accionRecienteDashboard");
                JsfUtil.copyBeans(accionRecienteOld, accionReciente);
                cajero = (Cajero) JmoordbContext.get("cajero");
                haveAccionReciente = Boolean.TRUE;
                if (!accionReciente.getGRUPOESTADOID().equals(JsfUtil.contextToBigInteger("grupoEstadoEnprocesoId"))) {
                    showCommandButtonReagendar = Boolean.TRUE;
                    showCommandButtonEncenderSubirPlantilla = Boolean.FALSE;

                }
                /**
                 * Verifica si la plantilla esta bajada
                 */
                if (accionReciente.getESTADOID().equals(JsfUtil.contextToBigInteger("estadoPlantillaDeshabilitada"))) {
                    showCommandButtonEncenderSubirPlantilla = Boolean.TRUE;
                    showCommandButtonReagendar = Boolean.FALSE;
                    //Carga el selectOneMenu
                    fillSelectOneMenuGrupoAccionEncenderSubirPlantilla();
                    accionList = new ArrayList<>();

                    if (grupoAccionEncenderSubirPlantilla.getGRUPOACCIONID().equals(JsfUtil.contextToBigInteger("grupoAccionEncenderSubirPlantillaId"))) {

                        accionList = accionRepository.findByGrupoAccionIdAndPredeterminado(grupoAccionEncenderSubirPlantilla, "SI");

                    } else {

                        JsfUtil.warningMessage("El grupoAccion debe ser Encender Subir Plantilla para realizar las operaciones");
                    }

                    if (accionList == null || accionList.isEmpty()) {

                        JsfUtil.warningMessage("No hay acciones para el grupo seleccionado");
                    } else {

                        accion = accionList.get(0);

                    }
                }
            }
            if (!showCommandButtonEncenderSubirPlantilla
                    && !showCommandButtonReagendar) {
                showCommandButtonCerrar = Boolean.TRUE;
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
            JsfUtil.warningMessage("No se identificó el grupo de acción para continuar esta operación");

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
    public String onCommandButtonReagendar() {
        try {
            if (DateUtil.igualDiaMesAñoHoraMinuto(accionReciente.getFECHAAGENDADA(), accionRecienteOld.getFECHAAGENDADA())) {
                JsfUtil.warningMessage("Indique otra fecha para proceder a realizar el cambio de agenda");
                return "";
            }

//            if (!tokenEnviado) {
//                JsfUtil.warningMessage("Usted debe solicite primero un token");
//                return "";
//            }
//            if (!validateToken()) {
//                return "";
//            }
            /**
             * Valida si fue cambiado por otro usuario
             */
            if (accionRecienteServices.changed(accionRecienteOld)) {
                MessagesForm messagesForm = new MessagesForm.Builder()
                        .errorWindows(Boolean.TRUE)
                        .id(accionReciente.getCAJERO())
                        .header("Operación incompleta")
                        .header2("La acción no fue completada")
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
                notificacionServices.process(bank.getBANCOID(), "BANCO");

                Optional<Agenda> agendaOptional = agendaRepository.findByAgendaId(accionReciente.getAGENDAID());
                if (!agendaOptional.isPresent()) {
                    JsfUtil.warningMessage("No se encontro registros de ese agendamiento");

                    return "";
                } else {
                    Agenda agenda = agendaOptional.get();
                    agenda.setFECHAAGENDADA(accionReciente.getFECHAAGENDADA());

                    if (agendaRepository.update(agenda)) {
                        agendaHistorialServices.createHistorial(agendaOptional.get(), "SE REAGENDÓ EL EVENTO", estado, user, "BA");

                        JmoordbContext.put("accionReciente", accionReciente);
                        emailServices.sendEmailToTecnicosHeader(accionReciente, "SE REAGENDÓ EL EVENTO", user, cajero, bank);

                        /*
                        *Mensajes éxitosos
                         */
                        MessagesForm messagesForm = new MessagesForm.Builder()
                                .errorWindows(Boolean.FALSE)
                                .id(accionReciente.getCAJERO())
                                .header("Operación exitosa")
                                .header2("La acción se realizó exitosamente")
                                .image("atm-green01.png")
                                .libary("images")
                                .titulo("Se reagendó el evento")
                                .mensaje("Se realizó con éxito el regeandeamiento")
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
//            if (!tokenEnviado) {
//                JsfUtil.warningMessage("Usted debe solicite primero un token");
//                return "";
//            }
//            if (!validateToken()) {
//                return "";
//            }
            /**
             * Valida si fue cambiado por otro usuario
             */

            if (accionRecienteServices.changed(accionRecienteOld)) {
                MessagesForm messagesForm = new MessagesForm.Builder()
                        .errorWindows(Boolean.TRUE)
                        .id(accionReciente.getCAJERO())
                        .header("Operación incompleta")
                        .header2("La acción no fue completada")
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
                notificacionServices.process(bank.getBANCOID(), "BANCO");
                Optional<Agenda> agendaOptional = agendaRepository.findByAgendaId(accionReciente.getAGENDAID());
                if (!agendaOptional.isPresent()) {
                    JsfUtil.warningMessage("No se encontro registros de ese agendamiento");

                    return "";
                } else {
                    Agenda agenda = agendaOptional.get();
                    agenda.setACTIVO("NO");

                    if (agendaRepository.update(agenda)) {
                        agendaHistorialServices.createHistorial(agendaOptional.get(), "SE CANCELÓ EL EVENTO", estado, user, "BA");

                        JmoordbContext.put("accionReciente", accionReciente);
                        emailServices.sendEmailToTecnicosHeader(accionReciente, "SE CANCELÓ EL EVENTO", user, cajero, bank);

                        /*
                        *Mensajes éxitosos
                         */
                        MessagesForm messagesForm = new MessagesForm.Builder()
                                .errorWindows(Boolean.FALSE)
                                .id(accionReciente.getCAJERO())
                                .header("Operación exitosa")
                                .header2("La acción se realizó exitosamente")
                                .image("atm-green01.png")
                                .libary("images")
                                .titulo("Cancelación de evento")
                                .mensaje("Se realizó exitosamente la cancelación del evento")
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
                notificacionServices.process(bank.getBANCOID(), "BANCO");
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
                        *Mensajes éxitosos
                         */
                        MessagesForm messagesForm = new MessagesForm.Builder()
                                .errorWindows(Boolean.FALSE)
                                .id(accionReciente.getCAJERO())
                                .header("Operación exitosa")
                                .header2("La acción se realizó exitosamente")
                                .image("atm-green01.png")
                                .libary("images")
                                .titulo("Reagendar acción")
                                .mensaje("Se realizó exitosamente el reagendamiento ")
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
            // ConsoleUtil.info("onCommandButtonSendToken(isReagendar ) "+isReagendar);
            this.showCommandButtonReagendar = isReagendar;
            if (isReagendar) {
                // ConsoleUtil.info("accionReciente.getFECHAAGENDADA() " +accionReciente.getFECHAAGENDADA());
                // ConsoleUtil.info("accionRecienteOld.getFECHAAGENDADA() " +accionRecienteOld.getFECHAAGENDADA());
                if (DateUtil.igualDiaMesAñoHoraMinuto(accionReciente.getFECHAAGENDADA(), accionRecienteOld.getFECHAAGENDADA())) {
                    JsfUtil.warningMessage("Indique otra fecha para proceder a realizar el cambio de agenda");
                    return "";
                }

            }
            sendToken(isReagendar);

        } catch (Exception e) {
            JsfUtil.errorMessage(JsfUtil.nameOfMethod() + e.getLocalizedMessage());

        }
        return "";
    }
// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="String sendToken()">
    public String sendToken(Boolean isReagendar) {
        try {

            tokenEnviado = Boolean.FALSE;
            Token token = tokenServices.supplier();

            if (tokenRepository.create(token)) {
                //Envia el token sincrono y valida si fue o no enviado.
                if (!emailServices.sendTokenToEmailSincrono(token, user)) {
                    JsfUtil.errorMessage("No se logró enviar el token a su correo. Reintente la operación");
                    tokenEnviado = Boolean.FALSE;

                } else {
                    JsfUtil.successMessage("El token fue enviado a su correo.");
                    tokenEnviado = Boolean.TRUE;

                    openDialogToken(isReagendar);
                }
                //Envia el token al usuario

            } else {
                JsfUtil.warningMessage("No se pudo generar el token. Repita la acción");
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
                PrimeFaces.current().executeScript("PF('widgetVarTokenDialogReagendar').initPosition()");
                PrimeFaces.current().executeScript("PF('widgetVarTokenDialogReagendar').show()");

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

    // <editor-fold defaultstate="collapsed" desc="fillSelectOneMenuGrupoAccionEncenderSubirPlantilla() ">
    public String fillSelectOneMenuGrupoAccionEncenderSubirPlantilla() {
        try {
            grupoAccionList = new ArrayList<>();
            Optional<GrupoAccion> optional = grupoAccionRepository.findByGrupoAccionId(JsfUtil.contextToBigInteger("grupoAccionEncenderSubirPlantillaId"));
            if (optional.isPresent()) {
                grupoAccionEncenderSubirPlantilla = optional.get();
            } else {
                JsfUtil.warningMessage("No se encontro el grupo de Accion para encender subir plantilla");
            }

        } catch (Exception e) {
            JsfUtil.errorMessage(JsfUtil.nameOfMethod() + " " + e.getLocalizedMessage());
        }
        return "";
    }
// </editor-fold>
// <editor-fold defaultstate="collapsed" desc="String onCommandButtonEncenderSubirPlantillasSinToken()">

    /**
     * Guarda el evento y envia notificaciones
     *
     * @return
     */
    public String onCommandButtonEncenderSubirPlantillaSinToken() {
        try {

            if (selectOneMenuAccionValue == null) {
                JsfUtil.warningMessage("Seleccione la acción a ejecutar..");
                return "";
            }
            fechahoraBaja = DateUtil.fechaHoraActual();
            if (fechahoraBaja == null) {
                JsfUtil.warningMessage("Seleccione la fecha y hora");
                return "";
            }
            JmoordbContext.put("fechahoraBaja", fechahoraBaja);

            JmoordbContext.put("accion", selectOneMenuAccionValue);
            if (selectOneMenuAccionValue == null || selectOneMenuAccionValue.getACCIONID() == null) {
                JsfUtil.warningMessage("No selecciono la acción a ejecutar");
                return "";
            }

            /**
             * Valida si fue cambiado por otro usuario
             */
            if (accionRecienteServices.changed(accionRecienteOld)) {
                MessagesForm messagesForm = new MessagesForm.Builder()
                        .errorWindows(Boolean.TRUE)
                        .id(accionReciente.getCAJERO())
                        .header("Operación incompleta")
                        .header2("La acción no fue completada")
                        .image("robot01.png")
                        .libary("images")
                        .titulo("Encender /Subir Plantilla")
                        .mensaje("Otro usuario modifico este registro mientras usted lo editaba. ")
                        .returnTo("buscarcajero.xhtml")
                        .build();
                JmoordbContext.put("messagesForm", messagesForm);
                JmoordbContext.put("pageInView", "messagesform.xhtml");
                return "messagesform.xhtml";
            }

            Optional<Estado> optional = estadoRepository.findByEstadoId(JsfUtil.contextToBigInteger("estadoSolicituddeHabilitacióndePlantillaEnviada"));
            if (!optional.isPresent()) {
                JsfUtil.warningMessage("No se ha encontado el estado predeterminado para asignarlo a esta operacion.");
            } else {
                estado = optional.get();

            }
            JsfUtil.copyBeans(accion, selectOneMenuAccionValue);
            /**
             * Valida que no se hay un agendamiento en la misma hora
             */
            Integer count = agendaServices.countAgendamiento(cajero.getBANCOID().getBANCOID(), cajero.getCAJEROID(), accion.getACCIONID(), estado.getESTADOID(), fechahoraBaja, "SI");
            if (count > 0) {
                // ConsoleUtil.info("Existe un registro agendado de ese cajero en esa fecha");
                JsfUtil.warningMessage("Existe un registro agendado de ese cajero en esa fecha");

                return "";
            }

            if (accionList == null || accionList.isEmpty()) {
                JsfUtil.warningMessage("No acciones para el grupo seleccionado");
            } else {

                /**
                 * Buscamos la accion para subir plantilla
                 */
                accionReciente.setFECHA(DateUtil.getFechaHoraActual());
                accionReciente.setFECHAAGENDADA(DateUtil.getFechaHoraActual());
                accionReciente.setESTADO(estado.getESTADO());
                accionReciente.setESTADOID(estado.getESTADOID());
                accionReciente.setGRUPOESTADOID(estado.getGRUPOESTADOID().getGRUPOESTADOID());
                accionReciente.setACCIONID(selectOneMenuAccionValue.getACCIONID());
                accionReciente.setTITULO(selectOneMenuAccionValue.getGRUPOACCIONID().getGRUPOACCION());

                if (accionRecienteRepository.update(accionReciente)) {
                    //Actualizar la agenda
                    notificacionServices.process(bank.getBANCOID(), "BANCO");

                    Optional<Agenda> agendaOptional = agendaRepository.findByAgendaId(accionReciente.getAGENDAID());
                    if (!agendaOptional.isPresent()) {
                        JsfUtil.warningMessage("No se encontro registros de ese agendamiento");

                        return "";
                    } else {
                        Agenda agenda = agendaOptional.get();
                        agenda.setESTADOID(estado.getESTADOID());
                        agenda.setFECHAAGENDADA(accionReciente.getFECHAAGENDADA());
                        agenda.setGRUPOESTADOID(estado.getGRUPOESTADOID().getGRUPOESTADOID());
                        agenda.setACCIONID(selectOneMenuAccionValue.getACCIONID());
                      
                        if (agendaRepository.update(agenda)) {
                            agendaHistorialServices.createHistorial(agendaOptional.get(), "ENCENDER/SUBIR PLANTILLA", estado, user, "BA");

                            JmoordbContext.put("accionReciente", accionReciente);
                            emailServices.sendEmailToTecnicosHeader(accionReciente, "ENCENDER/SUBIR PLANTILLA", user, cajero, bank);

                            /*
                        *Mensajes éxitosos
                             */
                            MessagesForm messagesForm = new MessagesForm.Builder()
                                    .errorWindows(Boolean.FALSE)
                                    .id(accionReciente.getCAJERO())
                                    .header("Operación exitosa")
                                    .header2("La acción se realizó exitosamente")
                                    .image("atm-green01.png")
                                    .libary("images")
                                    .titulo("Encender Subir Plantilla")
                                    //                            .mensaje("Se realizó exitosamente el registro de Encender Subir Plantilla")
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
            }
        } catch (Exception e) {
            JsfUtil.errorMessage(JsfUtil.nameOfMethod() + " " + e.getLocalizedMessage());
        }
        return "";
    }
// </editor-fold>
}
