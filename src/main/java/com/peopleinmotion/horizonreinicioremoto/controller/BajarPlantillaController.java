/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.peopleinmotion.horizonreinicioremoto.controller;
// <editor-fold defaultstate="collapsed" desc="import ">

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
import com.peopleinmotion.horizonreinicioremoto.utils.JsfUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import com.peopleinmotion.horizonreinicioremoto.repository.AccionRepository;
import com.peopleinmotion.horizonreinicioremoto.repository.CajeroRepository;
import com.peopleinmotion.horizonreinicioremoto.repository.EmailConfigurationRepository;
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
import java.math.BigInteger;
import java.util.Date;
import lombok.Data;
import org.primefaces.PrimeFaces;
// </editor-fold>

/**
 *
 * @author avbravo
 */
@Named
@ViewScoped
@Data
public class BajarPlantillaController implements Serializable, Page {

    // <editor-fold defaultstate="collapsed" desc="field ">
    private static final long serialVersionUID = 1L;
    private Cajero cajero = new Cajero();
    Usuario user = new Usuario();
    Banco bank = new Banco();
    Accion accion = new Accion();
    AccionReciente accionReciente = new AccionReciente();
    GrupoAccion grupoAccion = new GrupoAccion();
    List<Accion> accionList = new ArrayList<>();
    Accion selectOneMenuAccionValue = new Accion();
    Estado estado = new Estado();
    private Boolean showButton = Boolean.FALSE;
    private Boolean haveAccionReciente = Boolean.TRUE;
    private String selectOneMenuCuandoValue = "ahora";
    private Date fechahoraBaja;
    private TokenReader tokenReader = new TokenReader();
    private Boolean tokenEnviado = Boolean.FALSE;
// </editor-fold>
// <editor-fold defaultstate="collapsed" desc="@Inject ">
    @Inject
    TokenRepository tokenRepository;

    @Inject
    EmailServices emailServices;
    @Inject
    EmailConfigurationRepository emailConfigurationRepository;

    @Inject
    AccionRecienteServices accionRecienteServices;
    @Inject
    AccionRecienteRepository accionRecienteRepository;
    @Inject
    GrupoAccionRepository grupoAccionRepository;
    @Inject
    CajeroRepository cajeroRepository;
    @Inject
    AccionRepository accionRepository;
    @Inject
    EstadoRepository estadoRepository;
    @Inject
    AgendaServices agendaServices;

    @Inject
    AgendaHistorialServices agendaHistorialServices;
    @Inject
    TokenServices tokenServices;
    @Inject
    NotificacionServices notificacionServices;
// </editor-fold>

    /**
     * Creates a new instance of CajeroAccionController
     */
    public BajarPlantillaController() {
    }

    // <editor-fold defaultstate="collapsed" desc="void init() ">
    @PostConstruct
    public void init() {
        try {

            tokenEnviado = Boolean.FALSE;
            if (JmoordbContext.get("user") == null) {

            } else {
                fechahoraBaja = DateUtil.fechaHoraActual();

                selectOneMenuCuandoValue = "";
                user = (Usuario) JmoordbContext.get("user");
                bank = (Banco) JmoordbContext.get("banco");
                cajero = (Cajero) JmoordbContext.get("cajero");
                grupoAccion = (GrupoAccion) JmoordbContext.get("grupoAccion");

                /**
                 * Buscare las acciones del grupo
                 */
                accionList = new ArrayList<>();

                if (grupoAccion.getGRUPOACCIONID().equals(JsfUtil.contextToBigInteger("grupoAccionBajarPlantillaId"))) {

                    accionList = accionRepository.findByGrupoAccionIdAndPredeterminado(grupoAccion, "SI");
                } else {
                    JsfUtil.warningMessage("El grupoAccion debe ser BAJAR PLANTILLA para realizar las operaciones");
                }
                if (accionList == null || accionList.isEmpty()) {

                    JsfUtil.warningMessage("No hay acciones para el grupo seleccionado");
                } else {
                    accion = accionList.get(0);
                }

                Optional<Estado> optional = estadoRepository.findByEstadoId(JsfUtil.contextToBigInteger("esatadoEnesperadeconfirmaci??ndelT??cnico"));
                if (!optional.isPresent()) {
                    JsfUtil.warningMessage("No se ha encontado el estado predeterminado para asignarlo a esta operacion.");
                } else {
                    estado = optional.get();
                    showButton = Boolean.TRUE;
                }
                findAccionReciente();
            }
        } catch (Exception e) {

            JsfUtil.errorMessage(JsfUtil.nameOfMethod() + " " + e.getLocalizedMessage());

        }

    }

    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="String onAccionChange()">
    public String onSelectOneMenuAccionChange() {
        try {

            if (selectOneMenuAccionValue == null || selectOneMenuAccionValue.getACCION() == null) {
                //No se ha seleccionado departamemto
                JmoordbContext.put("accion", selectOneMenuAccionValue);

            } else {

                JmoordbContext.put("accion", selectOneMenuAccionValue);
            }

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
            haveAccionReciente = Boolean.FALSE;
            Optional<AccionReciente> accionRecienteOptional = accionRecienteRepository.findByBancoIdAndCajeroIdUltimaAccionReciente(bank.getBANCOID(), cajero.getCAJEROID());
            if (accionRecienteOptional.isPresent()) {
                accionReciente = accionRecienteOptional.get();
                haveAccionReciente = Boolean.TRUE;

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

    // <editor-fold defaultstate="collapsed" desc="onCommandButtonSendToken() ">
    public String onCommandButtonSendToken() {
        try {
            /**
             * Valida que no se hay un agendamiento en la misma hora
             */

            sendToken();
        } catch (Exception e) {
            JsfUtil.errorMessage(JsfUtil.nameOfMethod() + " " + e.getLocalizedMessage());
        }

        return "";
    }
// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="String sendToken()">
    public String sendToken() {
        try {
            //Limpio el TokenReader
            TokenReader tokenReader = TokenReader.builder()
                    .number1("")
                    .number2("")
                    .number3("")
                    .number4("")
                    .build();
            tokenEnviado = Boolean.FALSE;

            if (selectOneMenuAccionValue == null) {
                JsfUtil.warningMessage("Seleccione la acci??n a ejecutar..");
                return "";
            }
            if (fechahoraBaja == null) {
                JsfUtil.warningMessage("Seleccione la fecha y hora");
                return "";
            }
            JmoordbContext.put("fechahoraBaja", fechahoraBaja);
            Token token = tokenServices.supplier();

            JmoordbContext.put("accion", selectOneMenuAccionValue);
            if (tokenRepository.create(token)) {

                //Envia el token sincrono y valida si fue o no enviado.
                if (!emailServices.sendTokenToEmailSincrono(token, user)) {
                    JsfUtil.errorMessage("No se logr?? enviar el token a su correo. Reintente la operaci??n");
                    tokenEnviado = Boolean.FALSE;

                } else {
                    JsfUtil.successMessage("El token fue enviado a su correo.");
                    tokenEnviado = Boolean.TRUE;

                    openDialogToken();
                }
                //Envia el token asincrono
//                emailServices.sendTokenToEmail(token, user);
//Abre el dialogo

            } else {
                JsfUtil.warningMessage("No se pudo generar el token. Repita la acci??n");
            }

        } catch (Exception e) {
            JsfUtil.errorMessage(JsfUtil.nameOfMethod() + " " + e.getLocalizedMessage());
        }
        return "";
    }
// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="String openDialogToken()">
    public String openDialogToken() {
        try {

            PrimeFaces.current().executeScript("PF('widgetVarTokenDialog').initPosition()");
            PrimeFaces.current().executeScript("PF('widgetVarTokenDialog').show()");

        } catch (Exception e) {
            JsfUtil.errorMessage(JsfUtil.nameOfMethod() + " " + e.getLocalizedMessage());
        }
        return "";
    }
// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="closeDialogEdit() ">
    public String closeDialogEdit() {
        try {

            PrimeFaces.current().executeScript("PF('widgetVarEditDialog').hide()");

        } catch (Exception e) {
            JsfUtil.errorMessage(JsfUtil.nameOfMethod() + " " + e.getLocalizedMessage());
        }
        return "";
    }
// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Boolean isProgramarEvento()">
    public Boolean isProgramarEvento() {
        try {

            if (selectOneMenuCuandoValue.trim().toLowerCase().equals("programar evento")) {

                return Boolean.TRUE;
            }

        } catch (Exception e) {
            JsfUtil.errorMessage(JsfUtil.nameOfMethod() + " " + e.getLocalizedMessage());
        }
        return Boolean.FALSE;
    }
// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="String onCommandButtonBajarPlantillaProgramarEvento()">
    /**
     * Guarda el evento y envia notificaciones
     *
     * @return
     */
    public String onCommandButtonBajarPlantillaProgramarEvento() {
        try {
            if (!tokenEnviado) {
                JsfUtil.warningMessage("Usted debe solicite primero un token");
                return "";
            }
            if (!validateToken()) {
                return "";
            }

            if (selectOneMenuAccionValue == null || selectOneMenuAccionValue.getACCIONID() == null) {
                JsfUtil.warningMessage("No selecciono la acci??n a ejecutar");
                return "";
            }

            JsfUtil.copyBeans(accion, selectOneMenuAccionValue);
            /**
             * Valida que no se hay un agendamiento en la misma hora
             */
            Integer count = agendaServices.countAgendamiento(cajero.getBANCOID().getBANCOID(), cajero.getCAJEROID(), accion.getACCIONID(), estado.getESTADOID(), fechahoraBaja, "SI");
            if (count > 0) {

                JsfUtil.warningMessage("Existe un registro agendado de ese cajero en esa fecha");

                return "";
            }

            if (accionList == null || accionList.isEmpty()) {
                JsfUtil.warningMessage("No acciones para el grupo seleccionado");
            } else {

                Date fechahoraBaja = (Date) JmoordbContext.get("fechahoraBaja");

                Optional<Agenda> agendaOptional = agendaServices.create(cajero, user, estado, accion, fechahoraBaja, fechahoraBaja);
                if (!agendaOptional.isPresent()) {
                    JsfUtil.warningMessage("No se encontro la agenda con ese codigo de transaccion");
                } else {
                    agendaHistorialServices.createHistorial(agendaOptional.get(), "BAJAR PLANTILLA", estado, user, "BA");

                    AccionReciente accionReciente = accionRecienteServices.create(agendaOptional.get(), bank, cajero, accion, grupoAccion, estado, "SI", "BA");
                    JmoordbContext.put("accionReciente", accionReciente);

                    notificacionServices.process(bank.getBANCOID(), "BANCO");
                    /**
                     * Envio de email
                     */
                    emailServices.sendEmailToTecnicos(accionReciente, accion, user, cajero, bank);
//       Boolean emailSend=    emailServices.sendEmailToTecnicos(accionReciente, accion, user, cajero, bank);
//                     if(emailSend ){
//                        // ConsoleUtil.info("Si envio el email");
//                    }else{
//                        ConsoleUtil.error("No envio el email");
//                    }
                    MessagesForm messagesForm = new MessagesForm.Builder()
                            .errorWindows(Boolean.FALSE)
                            .id(accionReciente.getCAJERO())
                            .header("Operaci??n exitosa")
                            .header2("La acci??n se realiz?? exitosamente")
                            .image("atm-green01.png")
                            .libary("images")
                            .titulo("Bajar plantilla Programar evento")
                            .mensaje("Se realiz?? exitosamente la baja de plantilla")
                            .returnTo("dashboard.xhtml")
                            .build();
                    JmoordbContext.put("messagesForm", messagesForm);

                    JmoordbContext.put("pageInView", "messagesform.xhtml");
                    return "messagesform.xhtml";
                }

            }

        } catch (Exception e) {
            JsfUtil.errorMessage(JsfUtil.nameOfMethod() + " " + e.getLocalizedMessage());
        }
        return "";
    }
// </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="String onCommandButtonBajarPlantillaSinToken()">

    /**
     * Guarda el evento y envia notificaciones
     *
     * @return
     */
    public String onCommandButtonBajarPlantillaSinToken() {
        try {
//            if (!tokenEnviado) {
//                JsfUtil.warningMessage("Usted debe solicite primero un token");
//                return "";
//            }
//            if (!validateToken()) {
//                return "";
//            }
            if (selectOneMenuAccionValue == null) {
                JsfUtil.warningMessage("Seleccione la acci??n a ejecutar..");
                return "";
            }
            if (fechahoraBaja == null) {
                JsfUtil.warningMessage("Seleccione la fecha y hora");
                return "";
            }
            JmoordbContext.put("fechahoraBaja", fechahoraBaja);

            JmoordbContext.put("accion", selectOneMenuAccionValue);

            if (selectOneMenuAccionValue == null || selectOneMenuAccionValue.getACCIONID() == null) {
                JsfUtil.warningMessage("No selecciono la acci??n a ejecutar");
                return "";
            }

            JsfUtil.copyBeans(accion, selectOneMenuAccionValue);
            /**
             * Valida que no se hay un agendamiento en la misma hora
             */
            Integer count = agendaServices.countAgendamiento(cajero.getBANCOID().getBANCOID(), cajero.getCAJEROID(), accion.getACCIONID(), estado.getESTADOID(), fechahoraBaja, "SI");
            if (count > 0) {

                JsfUtil.warningMessage("Existe un registro agendado de ese cajero en esa fecha");

                return "";
            }

            if (accionList == null || accionList.isEmpty()) {
                JsfUtil.warningMessage("No acciones para el grupo seleccionado");
            } else {

                Date fechahoraBaja = (Date) JmoordbContext.get("fechahoraBaja");

                Optional<Agenda> agendaOptional = agendaServices.create(cajero, user, estado, accion, fechahoraBaja, fechahoraBaja);
                if (!agendaOptional.isPresent()) {
                    JsfUtil.warningMessage("No se encontro la agenda con ese codigo de transaccion");
                } else {
                    agendaHistorialServices.createHistorial(agendaOptional.get(), "BAJAR PLANTILLA", estado, user, "BA");

                    AccionReciente accionReciente = accionRecienteServices.create(agendaOptional.get(), bank, cajero, accion, grupoAccion, estado, "SI", "BA");
                    JmoordbContext.put("accionReciente", accionReciente);

                    notificacionServices.process(bank.getBANCOID(), "BANCO");
                    /**
                     * Envio de email
                     */
                    emailServices.sendEmailToTecnicos(accionReciente, accion, user, cajero, bank);
//       Boolean emailSend=    emailServices.sendEmailToTecnicos(accionReciente, accion, user, cajero, bank);
//                     if(emailSend ){
//                        // ConsoleUtil.info("Si envio el email");
//                    }else{
//                        ConsoleUtil.error("No envio el email");
//                    }
                    MessagesForm messagesForm = new MessagesForm.Builder()
                            .errorWindows(Boolean.FALSE)
                            .id(accionReciente.getCAJERO())
                            .header("Operaci??n exitosa")
                            .header2("La acci??n se realiz?? exitosamente")
                            .image("atm-green01.png")
                            .libary("images")
                            .titulo("Bajar plantilla Programar evento")
                            .mensaje("Se realiz?? exitosamente la baja de plantilla")
                            .returnTo("dashboard.xhtml")
                            .build();
                    JmoordbContext.put("messagesForm", messagesForm);

                    JmoordbContext.put("pageInView", "messagesform.xhtml");
                    return "messagesform.xhtml";
                }

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
            JsfUtil.errorMessage(JsfUtil.nameOfMethod() + "  " + e.getLocalizedMessage());
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
            JsfUtil.errorMessage(JsfUtil.nameOfMethod() + e.getLocalizedMessage());
        }
        return "";
    }
// </editor-fold> 

}
