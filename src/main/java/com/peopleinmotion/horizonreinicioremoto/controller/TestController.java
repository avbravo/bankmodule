/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.peopleinmotion.horizonreinicioremoto.controller;

import com.peopleinmotion.horizonreinicioremoto.entity.Accion;
import com.peopleinmotion.horizonreinicioremoto.entity.AccionReciente;
import com.peopleinmotion.horizonreinicioremoto.entity.Agenda;
import com.peopleinmotion.horizonreinicioremoto.entity.AgendaHistorial;
import com.peopleinmotion.horizonreinicioremoto.entity.Banco;
import com.peopleinmotion.horizonreinicioremoto.entity.Cajero;
import com.peopleinmotion.horizonreinicioremoto.entity.EmailConfiguration;
import com.peopleinmotion.horizonreinicioremoto.entity.Estado;
import com.peopleinmotion.horizonreinicioremoto.entity.GrupoAccion;
import com.peopleinmotion.horizonreinicioremoto.entity.GrupoEstado;
import com.peopleinmotion.horizonreinicioremoto.entity.Token;
import com.peopleinmotion.horizonreinicioremoto.entity.Usuario;

import com.peopleinmotion.horizonreinicioremoto.jmoordb.JmoordbContext;
import com.peopleinmotion.horizonreinicioremoto.repository.AccionRecienteRepository;
import com.peopleinmotion.horizonreinicioremoto.utils.JsfUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import com.peopleinmotion.horizonreinicioremoto.repository.AccionRepository;
import com.peopleinmotion.horizonreinicioremoto.repository.AgendaHistorialRepository;
import com.peopleinmotion.horizonreinicioremoto.repository.AgendaRepository;
import com.peopleinmotion.horizonreinicioremoto.repository.BancoRepository;
import com.peopleinmotion.horizonreinicioremoto.repository.CajeroRepository;
import com.peopleinmotion.horizonreinicioremoto.repository.EmailConfigurationRepository;
import com.peopleinmotion.horizonreinicioremoto.repository.EstadoRepository;

import com.peopleinmotion.horizonreinicioremoto.repository.GrupoAccionRepository;
import com.peopleinmotion.horizonreinicioremoto.repository.GrupoEstadoRepository;
import com.peopleinmotion.horizonreinicioremoto.repository.TokenRepository;
import com.peopleinmotion.horizonreinicioremoto.services.DashboardServices;
import com.peopleinmotion.horizonreinicioremoto.services.EmailServices;
import com.peopleinmotion.horizonreinicioremoto.utils.DateUtil;
import java.math.BigInteger;
import java.util.Date;
import java.util.Optional;

/**
 *
 * @author avbravo
 */
@Named
@ViewScoped
public class TestController implements Serializable {

    // <editor-fold defaultstate="collapsed" desc="field ">
    private static final long serialVersionUID = 1L;

    BigInteger totalSolicitado = new BigInteger("0");
    BigInteger totalFinalizado = new BigInteger("0");
    BigInteger totalEnProceso = new BigInteger("0");
    BigInteger totalNoSePuedeEjecutar = new BigInteger("0");
    Cajero cajero = new Cajero();
    Usuario user = new Usuario();
    Banco bank = new Banco();
    GrupoAccion grupoAccion = new GrupoAccion();
    List<Accion> accionList = new ArrayList<>();
    Accion selectOneMenuAccionValue = new Accion();
    Estado estado = new Estado();
    Agenda agenda = new Agenda();
    Accion accion = new Accion();
    EmailConfiguration emailConfiguration = new EmailConfiguration();
    List<GrupoEstado> grupoEstadoList = new ArrayList<>();

    List<Cajero> cajeroList = new ArrayList<>();

    private String myToken = "";
    private String inputTextCajeroSearch = "";
    private String inputTextDireccionCortaSearch = "";
// </editor-fold>
// <editor-fold defaultstate="collapsed" desc="@Inject ">

    @Inject
    TokenRepository tokenRepository;

    @Inject
    BancoRepository bancoRepository;
    @Inject
    DashboardServices dashboardServices;

    @Inject
    GrupoAccionRepository grupoAccionRepository;
    @Inject
    CajeroRepository cajeroRepository;
    @Inject
    AccionRepository accionRepository;
    @Inject
    AgendaRepository agendaRepository;
    @Inject
    AgendaHistorialRepository agendaHistorialRepository;
    @Inject
    EstadoRepository estadoRepository;
    @Inject
    EmailConfigurationRepository emailConfigurationRepository;

    @Inject
    AccionRecienteRepository accionRecienteRepository;
    @Inject
    GrupoEstadoRepository grupoEstadoRepository;
    @Inject
    EmailServices emailServices;
//    @Inject
//    AgendaFacade agendaFacade;

// </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="set/get() ">
    public String getInputTextCajeroSearch() {
        return inputTextCajeroSearch;
    }

    public void setInputTextCajeroSearch(String inputTextCajeroSearch) {
        this.inputTextCajeroSearch = inputTextCajeroSearch;
    }

    public String getInputTextDireccionCortaSearch() {
        return inputTextDireccionCortaSearch;
    }

    public void setInputTextDireccionCortaSearch(String inputTextDireccionCortaSearch) {
        this.inputTextDireccionCortaSearch = inputTextDireccionCortaSearch;
    }

    public String getMyToken() {
        return myToken;
    }

    public void setMyToken(String myToken) {
        this.myToken = myToken;
    }

    public BigInteger getTotalSolicitado() {
        return totalSolicitado;
    }

    public void setTotalSolicitado(BigInteger totalSolicitado) {
        this.totalSolicitado = totalSolicitado;
    }

    public BigInteger getTotalFinalizado() {
        return totalFinalizado;
    }

    public void setTotalFinalizado(BigInteger totalFinalizado) {
        this.totalFinalizado = totalFinalizado;
    }

    public BigInteger getTotalEnProceso() {
        return totalEnProceso;
    }

    public void setTotalEnProceso(BigInteger totalEnProceso) {
        this.totalEnProceso = totalEnProceso;
    }

    public BigInteger getTotalNoSePuedeEjecutar() {
        return totalNoSePuedeEjecutar;
    }

    public void setTotalNoSePuedeEjecutar(BigInteger totalNoSePuedeEjecutar) {
        this.totalNoSePuedeEjecutar = totalNoSePuedeEjecutar;
    }

    public List<GrupoEstado> getGrupoEstadoList() {
        return grupoEstadoList;
    }

    public void setGrupoEstadoList(List<GrupoEstado> grupoEstadoList) {
        this.grupoEstadoList = grupoEstadoList;
    }

    public List<Cajero> getCajeroList() {
        return cajeroList;
    }

    public void setCajeroList(List<Cajero> cajeroList) {
        this.cajeroList = cajeroList;
    }

    public Agenda getAgenda() {
        return agenda;
    }

    public void setAgenda(Agenda agenda) {
        this.agenda = agenda;
    }

    public GrupoAccion getGrupoAccion() {
        return grupoAccion;
    }

    public void setGrupoAccion(GrupoAccion grupoAccion) {
        this.grupoAccion = grupoAccion;
    }

    public List<Accion> getAccionList() {
        return accionList;
    }

    public void setAccionList(List<Accion> accionList) {
        this.accionList = accionList;
    }

    public Accion getSelectOneMenuAccionValue() {
        return selectOneMenuAccionValue;
    }

    public void setSelectOneMenuAccionValue(Accion selectOneMenuAccionValue) {
        this.selectOneMenuAccionValue = selectOneMenuAccionValue;
    }

    public void setBank(Banco bank) {
        this.bank = bank;
    }

    public Cajero getCajero() {
        return cajero;
    }

    public void setCajero(Cajero cajero) {
        this.cajero = cajero;
    }

    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }

    public Banco getBank() {
        return bank;
    }

// </editor-fold>
    /**
     * Creates a new instance of CajeroAccionController
     */
    public TestController() {
    }

    @PostConstruct
    public void init() {
        try {
            bancoRepository.findAll();
            user = (Usuario) JmoordbContext.get("user");
            bank = (Banco) JmoordbContext.get("banco");

        } catch (Exception e) {
            // System.out.println("init() " + e.getLocalizedMessage());
            JsfUtil.errorMessage("init() " + e.getLocalizedMessage());
            JsfUtil.errorDialog("init()", e.getLocalizedMessage());

        }

    }

    // <editor-fold defaultstate="collapsed" desc="onCommandButtonInitial() ">    
    public String onCommandButtonInitial() {
        try {
            /**
             * Remover esto
             */
            Optional<Cajero> cajeroOptional = cajeroRepository.findByCajeroId(JsfUtil.toBigInteger(1));
            // System.out.println("Ooptional " + cajeroOptional);
            if (!cajeroOptional.isPresent()) {
                JsfUtil.warningMessage("no existe cajero con el codigo 10");
            } else {
                cajero = cajeroOptional.get();
            }

            Optional<Accion> accionOptional = accionRepository.findByAccionId(JsfUtil.toBigInteger(2));
            if (!accionOptional.isPresent()) {
                JsfUtil.warningMessage("No existe cajero con el código 10");
            } else {
                accion = accionOptional.get();
            }
            Optional<GrupoAccion> grupoAccionOptional = grupoAccionRepository.findByGrupoAccionId(JsfUtil.toBigInteger(3));
            if (!grupoAccionOptional.isPresent()) {
                JsfUtil.warningMessage("No existe grupo acción con el código 10");
            } else {
                grupoAccion = grupoAccionOptional.get();
            }

            /**
             * EmailConfiguration
             */
            Optional<EmailConfiguration> emailConfigurationOptional = emailConfigurationRepository.findByEmailConfigurationId(JsfUtil.toBigInteger(1));
            if (!emailConfigurationOptional.isPresent()) {
                JsfUtil.warningMessage("No existe emailconfiguration con código 1");
            } else {
                emailConfiguration = emailConfigurationOptional.get();
            }

//            cajero = (Cajero) JmoordbContext.get("cajero");
//            grupoAccion = (GrupoAccion) JmoordbContext.get("grupoAccion");
            /**
             * Buscare las acciones del grupo
             */
//            accionList = new ArrayList<>();
//            if (grupoAccion.getGRUPOACCION().toUpperCase().trim().equals("ENCENDER (SUBIR PLANTILLA)")) {
//                accionList = accionRepository.findByGrupoAccionIdAndPredeterminado(grupoAccion, "SI");
//            } else {
//
//                JsfUtil.warningMessage("El grupoAccion debe ser ENCENDER (SUBIR PLANTILLA) para realizar las operaciones");
//            }
//
//            if (accionList == null || accionList.isEmpty()) {
//
//                JsfUtil.warningMessage("No acciones para el grupo seleccionado");
//            }
            Optional<Estado> optional = estadoRepository.findByPredeterminado("SI");
            if (!optional.isPresent()) {
                JsfUtil.warningMessage("No se ha encontado el estado predeterminado para asignarlo a esta operación.");
            } else {
                estado = optional.get();
            }

        } catch (Exception e) {
            // System.out.println("onCommandButtonInitial() " + e.getLocalizedMessage());
            JsfUtil.errorMessage("onCommandButtonInitial() " + e.getLocalizedMessage());
            JsfUtil.errorDialog("ionCommandButtonInitial()", e.getLocalizedMessage());
        }
        return "";
    }
// </editor-fold>

    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="String onAccionChange()">
    public String onSelectOneMenuAccionChange() {
        try {

            if (selectOneMenuAccionValue == null || selectOneMenuAccionValue.getACCION() == null) {
                //No se ha seleccionado departamemto
                JmoordbContext.put("accion", selectOneMenuAccionValue);
                JsfUtil.infoDialog("onAccionChangee()", " Nada Seleccionado");
            } else {
                JsfUtil.infoDialog("onAccionChangee() ", selectOneMenuAccionValue.getACCION());
                // System.out.println("onAccionChange " + selectOneMenuAccionValue.getACCION());
                JmoordbContext.put("accion", selectOneMenuAccionValue);
            }

        } catch (Exception e) {
            JsfUtil.errorMessage("onAccionChange() " + e.getLocalizedMessage());
        }
        return "";
    }
// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="onCommandButtonSubirPlantilla() ">
    /**
     * Guarda el evento y envia notificaciones
     *
     * @return
     */
    public String onCommandButtonCreateAgenda() {
        try {
//            Todo todo = new Todo();
//            todo.setDESCRIPCION("Aquiu");
//todo.setFECHA(DateUtil.fechaHoraActual());
//todoFacade.create(todo);
//            // System.out.println("gardo todo");

            Agenda agenda = new Agenda();
            agenda.setACTIVO("SI");
            agenda.setCODIGOTRANSACCION(JsfUtil.generateUniqueID());
            agenda.setCAJEROID(cajero.getCAJEROID());
            agenda.setESTADOID(estado.getESTADOID());
            agenda.setACCIONID(accion.getACCIONID());
            agenda.setFECHA(DateUtil.getFechaHoraActual());
            agenda.setFECHAAGENDADA(DateUtil.getFechaHoraActual());
            agenda.setUSUARIOIDATIENDE(JsfUtil.toBigInteger(0));
            agenda.setUSUARIOIDSOLICITA(user.getUSUARIOID());

            if (agendaRepository.create(agenda)) {
                // System.out.println("agemda creada voy a buscar el id");
                Optional<Agenda> agendaOptional = agendaRepository.findByCodigoTransaccion(agenda.getCODIGOTRANSACCION());
                if (!agendaOptional.isPresent()) {
                    JsfUtil.warningMessage("No se encontro la agenda con ese codigo de transaccion");
                } else {
                    // System.out.println("id encontrado " + agendaOptional.get().getAGENDAID());
                    JsfUtil.successMessage("id encontrado " + agendaOptional.get().getAGENDAID());

                }

                createHistorial(agendaOptional.get());
                AccionReciente accionReciente = createAccionReciente(agendaOptional.get());
                // Guardo el historial

//                String message = emailServices.generateMessages(accionReciente, grupoAccion.getGRUPOACCION(), user, cajero, bank);
            }

//            if (accionList == null || accionList.isEmpty()) {
//                JsfUtil.warningMessage("No acciones para el grupo seleccionado");
//            } else {
//                Agenda agenda = new Agenda();
////                agenda.setACCIONID(accionList.get(0).getACCIONID());
////                
////                // System.out.println("voy a otra....");
//                agenda.setACTIVO("SI");
////           
////                agenda.setCAJEROID(cajero.getCAJEROID());
////                agenda.setESTADOID(estado.getESTADOID());
//                agenda.setFECHA(DateUtil.getFechaHoraActual());
////                agenda.setFECHAAGENDADA(DateUtil.getFechaHoraActual());
////                agenda.setUSUARIOIDATIENDE(JsfUtil.toBigInteger(0));
////                agenda.setUSUARIOIDSOLICITA(user.getUSUARIOID());
//                // System.out.println("voy a guardar...... la agenda");
//                agendaRepository.create(agenda);
//                // System.out.println(" la guarde");
////                if(agendaRepository.create(agenda)){
////                    JsfUtil.successMessage("Se guardo adecuadamaente la agenda");
////                }else{
////                    JsfUtil.warningMessage("No se pudo guardar la agenda");
////                     new FacesMessage("No se pudo guardar......la agenda no se");
////                 
////                }
            JsfUtil.successMessage("Guardado....");

//            }
        } catch (Exception e) {
            JsfUtil.errorMessage("onCommandButtonSubirPlantilla()" + e.getLocalizedMessage());
        }
        return "";
    }
// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Boolean createHistorial(Agenda agenda) ">    
    private Boolean createHistorial(Agenda agenda) {
        try {
            AgendaHistorial agendaHistorial = new AgendaHistorial();
            agendaHistorial.setACCIONID(agenda.getACCIONID());
            agendaHistorial.setACTIVO("SI");
            agendaHistorial.setAGENDAID(agenda.getAGENDAID());
            agendaHistorial.setCAJEROID(agenda.getCAJEROID());
            agendaHistorial.setESTADOID(agenda.getESTADOID());
            agendaHistorial.setFECHA(agenda.getFECHA());
            agendaHistorial.setFECHAAGENDADA(agenda.getFECHAAGENDADA());
            agendaHistorial.setUSUARIOIDATIENDE(agenda.getUSUARIOIDATIENDE());
            agendaHistorial.setUSUARIOIDSOLICITA(agenda.getUSUARIOIDSOLICITA());
            if (agendaHistorialRepository.create(agendaHistorial)) {
                return Boolean.TRUE;
            }
            JsfUtil.warningMessage("No se guardo el historial de la agenda");

        } catch (Exception e) {
            JsfUtil.errorMessage("createHistorial()" + e.getLocalizedMessage());
        }
        return Boolean.FALSE;
    }
    // </editor-fold> 

    // <editor-fold defaultstate="collapsed" desc="Boolean createAccionReciente(Agenda agenda) ">    
    private AccionReciente createAccionReciente(Agenda agenda) {
        AccionReciente accionReciente = new AccionReciente();
        try {

            accionReciente.setACCIONID(agenda.getACCIONID());
            accionReciente.setACTIVO("SI");
            accionReciente.setAGENDAID(agenda.getAGENDAID());
            accionReciente.setBANCOID(bank.getBANCOID());
            accionReciente.setCAJEROID(cajero.getCAJEROID());
            accionReciente.setMENSAJE(accion.getACCION());
            accionReciente.setTITULO(grupoAccion.getGRUPOACCION());
            accionReciente.setFECHA(agenda.getFECHA());
            accionReciente.setFECHAAGENDADA(agenda.getFECHAAGENDADA());
            accionReciente.setVISTOBANCO("NO");
            accionReciente.setVISTOTECNICO("NO");
            if (accionRecienteRepository.create(accionReciente)) {
                //  return Boolean.TRUE;
            }
            JsfUtil.warningMessage("No se pudo guardar la acción reciente...");

        } catch (Exception e) {
            JsfUtil.errorMessage("createAccionReciente()" + e.getLocalizedMessage());
        }
        return accionReciente;
    }
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Boolean createAccionReciente(Agenda agenda) ">    

    private Boolean sendEmail(Agenda agenda) {
        try {
            AccionReciente accionReciente = new AccionReciente();
            accionReciente.setACCIONID(agenda.getACCIONID());
            accionReciente.setACTIVO("SI");
            accionReciente.setAGENDAID(agenda.getAGENDAID());
            accionReciente.setBANCOID(bank.getBANCOID());
            accionReciente.setCAJEROID(cajero.getCAJEROID());
            accionReciente.setMENSAJE(accion.getACCION());
            accionReciente.setTITULO(grupoAccion.getGRUPOACCION());
            accionReciente.setVISTOBANCO("NO");
            accionReciente.setVISTOTECNICO("NO");
            if (accionRecienteRepository.create(accionReciente)) {
                return Boolean.TRUE;
            }
            JsfUtil.warningMessage("No se pudo guardar la acción reciente...");

        } catch (Exception e) {
            JsfUtil.errorMessage("createAccionReciente()" + e.getLocalizedMessage());
        }
        return Boolean.FALSE;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="metodo() ">    
    public String onCommandButtonFindCajero() {
        try {

            cajeroList = cajeroRepository.findByBancoIdAndActivo(bank, "SI");
            if (cajeroList == null || cajeroList.isEmpty()) {
                // System.out.println(" no encontre para ese banco los cajeros");
                JsfUtil.warningMessage("No hay cajeros para el banco que esten activos");
            } else {
                JsfUtil.successMessage("Encontro cajeros del banco.,");
                for (Cajero c : cajeroList) {
                    // System.out.println("------> " + c.getCAJEROID() + "  " + c.getCAJERO() + " " + c.getDIRECCIONCORTA());
                }
            }
        } catch (Exception e) {
            JsfUtil.errorMessage("onCommandButtonSubirPlantilla()" + e.getLocalizedMessage());
        }
        return "";
    }
// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="onCommandButtonCreateCajero() ">    
    public String onCommandButtonCreateCajero() {
        try {
            Cajero cajero = new Cajero();
            cajero.setACTIVO("SI");
            cajero.setBANCOID(bank);
            cajero.setCAJERO(JsfUtil.generateUniqueID().substring(0, 4));
            cajero.setDESCRIPCION("Auto generado");
            cajero.setDIRECCION("Los santos");
            cajero.setDIRECCIONCORTA("La Villa");
            cajero.setORDEN(JsfUtil.toBigInteger(1));
            if (cajeroRepository.create(cajero)) {
                JsfUtil.successMessage("Se creo el nuevo cajero ");
            } else {
                JsfUtil.warningMessage("No se pudo crear el nuevo cajerp");
            }
            onCommandButtonFindCajero();

        } catch (Exception e) {
            JsfUtil.errorMessage("createCajero())" + e.getLocalizedMessage());
        }
        return "";
    }
// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="onCommandButttonCalcularTotales() ">    
    public String onCommandButttonCalcularTotales() {
        try {
            grupoEstadoList = dashboardServices.calcularTotalGrupoEstado(bank);

            totalSolicitado = dashboardServices.totalSolicitado(grupoEstadoList);
            totalFinalizado = dashboardServices.totalFinalizado(grupoEstadoList);
            totalEnProceso = dashboardServices.totalEnProceso(grupoEstadoList);
            totalNoSePuedeEjecutar = dashboardServices.totalNoSePuedeEjecutar(grupoEstadoList);
            // System.out.println("totalSolicitado " + totalSolicitado);
            // System.out.println("totalFinalizado " + totalFinalizado);
            // System.out.println("totalEnProceso " + totalEnProceso);
            // System.out.println("totalNoSePuedeEjecutar " + totalNoSePuedeEjecutar);
        } catch (Exception e) {
            JsfUtil.errorMessage("onCommandButttonCalcularTotales()" + e.getLocalizedMessage());
        }
        return "";
    }
// </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="onCommandButttonCalcularTotales() ">    

    public String onCommandButttonCalcularTotalesOld() {
        try {

            List<GrupoEstado> all = grupoEstadoRepository.findAll();
            if (all == null || all.isEmpty()) {
                // System.out.println("no encontro nada de grupo de estados........");
            } else {
                // System.out.println("si hay grupos de estado");
            }
            grupoEstadoList = grupoEstadoRepository.findByActivo("SI");
            if (grupoEstadoList == null || grupoEstadoList.isEmpty()) {
                JsfUtil.warningMessage("No hay grupos de estados disponibles para realizar las operaciones");
            } else {
                Integer n = 0;
                for (GrupoEstado ge : grupoEstadoList) {
                    grupoEstadoList.get(n++).setTOTAL(JsfUtil.toBigInteger(0));
                }

                List<Estado> estadoList = estadoRepository.findByActivo("SI");
                if (estadoList == null || estadoList.isEmpty()) {
                    JsfUtil.warningMessage("No hay estados disponibles para realizar las operaciones");
                } else {
                    Integer count = 0;
                    for (Estado e : estadoList) {
                        // Cuenta los estados activos del banco
                        count = agendaRepository.countByBancoIdAndEstadoIdAndActivo(bank.getBANCOID(), e.getESTADOID(), "SI");
                        /**
                         * Actualizo los totales del grupo
                         */
                        Integer c = 0;
                        for (GrupoEstado ge : grupoEstadoList) {
                            if (ge.getGRUPOESTADOID().equals(e.getGRUPOESTADOID().getGRUPOESTADOID())) {
                                BigInteger total = grupoEstadoList.get(c).getTOTAL().add(JsfUtil.toBigInteger(count));
                                grupoEstadoList.get(c).setTOTAL(total);
                            }
                            c++;
                        }
                    }
                }
            }

        } catch (Exception e) {
            JsfUtil.errorMessage("onCommandButttonCalcularTotales()" + e.getLocalizedMessage());
        }
        return "";
    }
// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="onCommandButtonSendToken() ">    
    public String onCommandButtonSendToken() {
        try {
            Token token = new Token();
            token.setACTIVO("SI");
            token.setCODIGOTRANSACCION(JsfUtil.getUUID());
            token.setFECHAGENERACION(DateUtil.fechaHoraActual());
            token.setFECHAVENCIMIENTO(DateUtil.sumarMinutosAFecha(token.getFECHAGENERACION(), 3));
            token.setTOKEN(JsfUtil.otp(4));
            token.setUSADO("NO");
            token.setUSUARIOID(user.getUSUARIOID());
            token.setVENCIDO("NO");
            if (tokenRepository.create(token)) {
                emailServices.sendTokenToEmail(token, user);
                JsfUtil.successMessage("Se creo el token");
            } else {
                JsfUtil.warningMessage("No se creo el token");
            }
        } catch (Exception e) {
            JsfUtil.errorMessage("onCommandButtonSendToken() " + e.getLocalizedMessage());
        }
        return "";
    }
// </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="String onCommandButtonValidateToken() ">    

    public String onCommandButtonValidateToken() {
        try {
            List<Token> tokenList = tokenRepository.findByUsuarioIdTokenAndActivo(user.getUSUARIOID(), myToken, "SI");
            if (tokenList == null || tokenList.isEmpty()) {
                JsfUtil.warningMessage("El token no es valido genere otro token");
            } else {
                Token token = tokenList.get(0);
                if (token.getUSADO().equals("SI")) {
                    JsfUtil.warningMessage("Este token ya fue usado");
                } else {
                    if (token.getVENCIDO().equals("SI")) {
                        JsfUtil.warningMessage("Token ya vencio");
                    } else {

                        if (DateUtil.fechaMayor(DateUtil.getFechaHoraActual(), token.getFECHAVENCIMIENTO())) {
                            JsfUtil.warningMessage("El plazo para usarlo ya se agoto. Genere otro token. Se colocara como vencido.");
                            token.setACTIVO("NO");
                            token.setUSADO("XX");
                            token.setVENCIDO("SI");
                            if (tokenRepository.update(token)) {
                                //   
                            } else {
                                // JsfUtil.warningMessage("No se pudo actualizar el token");
                            }
                        } else {
                            JsfUtil.successMessage("Validación correcta, se procede con la acción..");
                            token.setACTIVO("NO");
                            token.setVENCIDO("XX");
                            token.setUSADO("SI");
                            if (tokenRepository.update(token)) {
                                 
                            } else {
                                JsfUtil.warningMessage("No se pudo actualizar el token");
                            }
                        }
                    }
                }
            }

        } catch (Exception e) {
            JsfUtil.errorMessage("onCommandButtonValidateToken() " + e.getLocalizedMessage());
        }
        return "";
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="onCommandButtonCajeroSearch() ">    
    public String onCommandButtonCajeroSearch() {
        try {
            if (inputTextCajeroSearch == null || inputTextCajeroSearch.isEmpty() || inputTextCajeroSearch.equals("")) {
                JsfUtil.warningMessage("Ingrese el codigo del cajero para realizar la busqueda..");
                return "";
            }
            List<Cajero> list = cajeroRepository.findByCajeroIdBancoIdAndActivo(bank, inputTextCajeroSearch, "SI");
            if (list == null || list.isEmpty()) {
                JsfUtil.warningMessage("No se encontro cajero con codigo " + inputTextCajeroSearch);
            } else {
                JsfUtil.successMessage("Cajero encontrado");
            }
        } catch (Exception e) {
            JsfUtil.errorMessage("onCommandButtonCajeroSearch()" + e.getLocalizedMessage());
        }
        return "";
    }
// </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="onCommandButtonCajeroSearch() ">    

    public String onCommandButtonDireccionCortaSearch() {
        try {
            if (inputTextDireccionCortaSearch == null || inputTextDireccionCortaSearch.isEmpty() || inputTextDireccionCortaSearch.equals("")) {
                JsfUtil.warningMessage("Ingrese la direccion para realizar la busqueda");
                return "";
            }
            List<Cajero> list = cajeroRepository.findByDireccionCortaBancoIdAndActivo(bank, inputTextDireccionCortaSearch, "SI");
            if (list == null || list.isEmpty()) {
                JsfUtil.warningMessage("No se encontro cajero con direccion cortar " + inputTextDireccionCortaSearch);
            } else {
                JsfUtil.successMessage("Cajero encontrado");
            }
        } catch (Exception e) {
            JsfUtil.errorMessage("onCommandButtonCajeroSearch()" + e.getLocalizedMessage());
        }
        return "";
    }
// </editor-fold>

}
