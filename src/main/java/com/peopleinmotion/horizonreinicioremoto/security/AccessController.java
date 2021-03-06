/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.peopleinmotion.horizonreinicioremoto.security;

import com.peopleinmotion.horizonreinicioremoto.domains.TokenReader;
import com.peopleinmotion.horizonreinicioremoto.entity.Historial;
import com.peopleinmotion.horizonreinicioremoto.entity.Token;
import com.peopleinmotion.horizonreinicioremoto.entity.Usuario;
import com.peopleinmotion.horizonreinicioremoto.interfaces.Page;

import com.peopleinmotion.horizonreinicioremoto.jmoordb.JmoordbContext;
import com.peopleinmotion.horizonreinicioremoto.repository.BancoRepository;
import com.peopleinmotion.horizonreinicioremoto.repository.HistorialRepository;
import com.peopleinmotion.horizonreinicioremoto.repository.TokenRepository;
import com.peopleinmotion.horizonreinicioremoto.services.AccessServices;
import com.peopleinmotion.horizonreinicioremoto.services.EmailServices;
import com.peopleinmotion.horizonreinicioremoto.services.TokenServices;
import com.peopleinmotion.horizonreinicioremoto.utils.BrowserUtil;
import com.peopleinmotion.horizonreinicioremoto.utils.ConsoleUtil;
import com.peopleinmotion.horizonreinicioremoto.utils.DateUtil;
import com.peopleinmotion.horizonreinicioremoto.utils.JsfUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import lombok.Data;

/**
 *
 * @author avbravo
 */
@Named
@SessionScoped
@Data
public class AccessController implements Serializable, Page {
// <editor-fold defaultstate="collapsed" desc="fields ">

    private static final long serialVersionUID = 1L;

    Usuario usuario = new Usuario();
    private Boolean loged = false;
    private String version = "";
    private String role = "";
    private String username = "";
    private String password = "";
    private Integer intentos = 0;
    private List<String> usernameList = new ArrayList<>();
    private Boolean tokenEnviado = Boolean.FALSE;
    private TokenReader tokenReader = new TokenReader();
    private String tokenIngresado = "";
//    Banco selectOneMenuBancoValue = new Banco();
//    private List<Banco> bancoList = new ArrayList<>();
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="inject() ">
    @Inject
    TokenServices tokenServices;
    @Inject
    TokenRepository tokenRepository;
    @Inject
    EmailServices emailServices;
    @Inject
    BancoRepository bancoRepository;

    @Inject
    AccessServices accessServices;

    @Inject
    HistorialRepository historialRepository;
    @Inject
    BrowserUtil browserUtil;
// </editor-fold>

    /**
     * Creates a new instance of AccessController
     */
    public AccessController() {
    }

    // <editor-fold defaultstate="collapsed" desc="init()">
    @PostConstruct
    public void init() {
        loged = false;

        try {
            tokenIngresado = "";
            intentos = 0;

            /**
             * Lee las configuraciones iniciales
             */
            version = accessServices.loadConfigurationPropeties();

            /**
             * Banco
             */
//            QuerySQL querySQL = new QuerySQL.Builder()
//                    .query("SELECT b FROM Banco b WHERE b.ESCONTROL = 'NO' AND b.ACTIVO = 'SI' ORDER BY b.BANCO ASC")
//                    .count("SELECT COUNT(b) FROM Banco b WHERE b.ESCONTROL = 'NO' AND b.ACTIVO = 'SI'")
//                    .build();
//
//            bancoList = bancoRepository.sql(querySQL);
            JmoordbContext.put("countViewAction", 0);
        } catch (Exception e) {
            JsfUtil.errorMessage(JsfUtil.nameOfMethod() + " " + e.getLocalizedMessage());
            ConsoleUtil.error(JsfUtil.nameOfMethod() + " " + e.getLocalizedMessage());
        }

    }
// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="String String login()">
    public String login() {
        try {
tokenIngresado = "";
            if (username == null || username.equals("")) {

                JsfUtil.warningMessage("Ingrese el nombre del usuario");
                return "";
            }

            if (password == null || password.equals("")) {

                JsfUtil.warningMessage("Ingrese el password del usuario");
                return "";
            }

            setLoged(Boolean.FALSE);

            if (accessServices.validateCredentials(usuario, username, password)) {

                usuario = (Usuario) JmoordbContext.get("user");
                if (usuario.getMODULOBANCO().toUpperCase().equals("NO")) {
                    JsfUtil.warningMessage("No tiene permisos para usar este m??dulo ");
                    return "";
                }
               // setLoged(Boolean.TRUE);

                JmoordbContext.put("banco", usuario.getBANCOID());

                Historial historial = new Historial.Builder()
                        .EVENTO("Login")
                        .FECHA(DateUtil.fechaHoraActual())
                        .MODULO("BANCO")
                        .TABLA("USUARIO")
                        .CONTENIDO(usuario.toJSON())
                        .USUARIOID(usuario.getUSUARIOID())
                        .build();

                if (!historialRepository.create(historial)) {

                }

                JmoordbContext.put("countViewAction", 0);
//                JmoordbContext.put("pageInView", "dashboard.xhtml");
                JmoordbContext.put("pageInView", "logintoken.xhtml");
                intentos = 0;
                // return "dashboard.xhtml";
                //Envia al token al usuario
                sendToken();
                return "logintoken.xhtml";

            } else {
                intentos++;

                if (intentos > 2) {
                    JsfUtil.warningMessage("Usted ha intentado ingresar en m??s de tres ocasiones sin ??xito.");
                    accessServices.disableUser(username);
                    intentos = 0;
                    return "";
                }

            }

        } catch (Exception e) {
            JsfUtil.errorMessage(JsfUtil.nameOfMethod() + " " + e.getLocalizedMessage());
        }
        return "";
    }
// </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="String String loginOld()">
    public String loginOld() {
        try {

            if (username == null || username.equals("")) {

                JsfUtil.warningMessage("Ingrese el nombre del usuario");
                return "";
            }

            if (password == null || password.equals("")) {

                JsfUtil.warningMessage("Ingrese el password del usuario");
                return "";
            }

            setLoged(Boolean.FALSE);

            if (accessServices.validateCredentials(usuario, username, password)) {

                usuario = (Usuario) JmoordbContext.get("user");
                if (usuario.getMODULOBANCO().toUpperCase().equals("NO")) {
                    JsfUtil.warningMessage("No tiene permisos para usar este m??dulo ");
                    return "";
                }
                setLoged(Boolean.TRUE);

                JmoordbContext.put("banco", usuario.getBANCOID());

                Historial historial = new Historial.Builder()
                        .EVENTO("Login")
                        .FECHA(DateUtil.fechaHoraActual())
                        .MODULO("BANCO")
                        .TABLA("USUARIO")
                        .CONTENIDO(usuario.toJSON())
                        .USUARIOID(usuario.getUSUARIOID())
                        .build();

                if (!historialRepository.create(historial)) {

                }

                JmoordbContext.put("countViewAction", 0);
//                JmoordbContext.put("pageInView", "dashboard.xhtml");
                JmoordbContext.put("pageInView", "logintoken.xhtml");
                intentos = 0;
                // return "dashboard.xhtml";
                //Envia al token al usuario
                sendToken();
                return "logintoken.xhtml";

            } else {
                intentos++;

                if (intentos > 2) {
                    JsfUtil.warningMessage("Usted ha intentado ingresar en m??s de tres ocasiones sin ??xito.");
                    accessServices.disableUser(username);
                    intentos = 0;
                    return "";
                }

            }

        } catch (Exception e) {
            JsfUtil.errorMessage(JsfUtil.nameOfMethod() + " " + e.getLocalizedMessage());
        }
        return "";
    }
// </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="String loginConToken()">

    public String loginConToken() {
        try {

            if (username == null || username.equals("")) {

                JsfUtil.warningMessage("Ingrese el nombre del usuario");
                return "";
            }

            if (password == null || password.equals("")) {

                JsfUtil.warningMessage("Ingrese el password del usuario");
                return "";
            }
           
            if (validateToken()) {
                 setLoged(Boolean.TRUE);
                JmoordbContext.put("countViewAction", 0);
                JmoordbContext.put("pageInView", "dashboard.xhtml");
                intentos = 0;
                return "dashboard.xhtml";
            }

        } catch (Exception e) {
            JsfUtil.errorMessage(JsfUtil.nameOfMethod() + " " + e.getLocalizedMessage());
        }
        return "";
    }
// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="expired()">
    public String expired() {

        return "faces/login.xhtml";
    }// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="int getMaxInactiveInterval()">
    public int getMaxInactiveInterval() {
        int tiempo = ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest())
                .getSession().getMaxInactiveInterval();

        return tiempo;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="String inicializa()">
    public String inicializa() {

        username = "";

        loged = false;

        return "";
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="String showPageInView()">
    public String showPageInView() {
        try {
            String pageInView = (String) JmoordbContext.get("pageInView");
            return pageInView;
        } catch (Exception e) {
            JsfUtil.errorMessage(JsfUtil.nameOfMethod() + " " + e.getLocalizedMessage());
            ConsoleUtil.error(JsfUtil.nameOfMethod() + " " + e.getLocalizedMessage());
        }
        return "";

    }
// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="String browserEvent()">
    public String browserEvent(String from) {
        String pageInView = "";
        try {
            pageInView = (String) JmoordbContext.get("pageInView");
            if (pageInView == null) {
                pageInView = "";
            } else {
                pageInView = (pageInView == null ? (loged ? "dashboard.xhtml" : "") : pageInView);
            }
            return pageInView;

        } catch (Exception e) {
            JsfUtil.errorMessage(JsfUtil.nameOfMethod() + " " + e.getLocalizedMessage());
            ConsoleUtil.error(JsfUtil.nameOfMethod() + " " + e.getLocalizedMessage());
        }
        return pageInView;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="String logout()">
    public String logout() {
        String path = "";
        if (JmoordbContext.get("applicativePath") == null || String.valueOf(JmoordbContext.get("applicativePath")).equals("")) {

        } else {
            path = (String) JmoordbContext.get("applicativePath");
        }

        JmoordbContext.put("pageInView", "/faces/login.xhtml");

        return logout(path + "/faces/login.xhtml?faces-redirect=true");
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="String logout(String path)">
    public String logout(String path) {
        Boolean loggedIn = false;
        try {

            //Guarda el registro del acceso
            String ip = JsfUtil.getIp() == null ? "" : JsfUtil.getIp();

            HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
            if (session != null) {
                session.invalidate();
            }
            String url = (path);
            FacesContext fc = FacesContext.getCurrentInstance();
            ExternalContext ec = fc.getExternalContext();
            ec.redirect(url);
            return path;
        } catch (Exception e) {
            JsfUtil.errorMessage(JsfUtil.nameOfMethod() + " " + e.getLocalizedMessage());
        }
        return path;
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

            if (username == null || username.equals("")) {
                JsfUtil.warningMessage("Ingrese el nombre de usuario..");
                return "";
            }
            if (password == null || password.equals("")) {
                JsfUtil.warningMessage("Ingrese su contrase??a");
                return "";
            }

            Token token = tokenServices.supplier();

            if (tokenRepository.create(token)) {

                //Envia el token sincrono y valida si fue o no enviado.
                if (!emailServices.sendTokenToEmailSincrono(token, usuario)) {
                    JsfUtil.errorMessage("No se logr?? enviar el token a su correo. Reintente la operaci??n");
                    tokenEnviado = Boolean.FALSE;

                } else {
                    JsfUtil.successMessage("El token fue enviado a su correo.");
                    tokenEnviado = Boolean.TRUE;

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

    // <editor-fold defaultstate="collapsed" desc="Boolean validateToken() ">    
    public Boolean validateToken() {
        try {
           // String tokenIngresado = tokenReader.getNumber1().trim() + tokenReader.getNumber2().trim() + tokenReader.getNumber3().trim() + tokenReader.getNumber4().trim();
            return tokenServices.validateToken(usuario, tokenIngresado);
        } catch (Exception e) {
            JsfUtil.errorMessage(JsfUtil.nameOfMethod() + "  " + e.getLocalizedMessage());
        }
        return Boolean.FALSE;
    }
    // </editor-fold>
        // <editor-fold defaultstate="collapsed" desc="String anterior()">   
    public String anterior(){
          return "login.xhtml";
    }
  // </editor-fold>
}
