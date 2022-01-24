/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.peopleinmotion.horizonreinicioremoto.utils;
// <editor-fold defaultstate="collapsed" desc="import">  

import static com.diogonunes.jcolor.Ansi.colorize;
import com.diogonunes.jcolor.AnsiFormat;
import static com.diogonunes.jcolor.Attribute.BLUE_BACK;
import static com.diogonunes.jcolor.Attribute.BOLD;
import static com.diogonunes.jcolor.Attribute.BRIGHT_YELLOW_TEXT;
import static com.diogonunes.jcolor.Attribute.CYAN_TEXT;
import static com.diogonunes.jcolor.Attribute.GREEN_BACK;
import static com.diogonunes.jcolor.Attribute.GREEN_TEXT;
import static com.diogonunes.jcolor.Attribute.RED_BACK;
import static com.diogonunes.jcolor.Attribute.YELLOW_TEXT;
import com.peopleinmotion.horizonreinicioremoto.emails.EmailRecipients;
import com.peopleinmotion.horizonreinicioremoto.jmoordb.JmoordbContext;
import com.peopleinmotion.horizonreinicioremoto.utils.crypto.CryptoConverter;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.UUID;
import java.util.List;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.security.MessageDigest;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Random;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;
import javax.faces.component.UIInput;
import javax.faces.component.UISelectItem;
import javax.faces.context.ExternalContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import java.util.Enumeration;
import java.util.Properties;
import java.util.SplittableRandom;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import javax.faces.component.UIViewRoot;
import org.apache.commons.beanutils.BeanUtils;

// </editor-fold>
/**
 *
 * @authoravbravo
 */
public class JsfUtil implements Serializable {

    private static final Logger LOG = Logger.getLogger(JsfUtil.class.getName());
    private static final String EMAIL_REGEX = "^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$";

    // static Pattern object, since pattern is fixed
    private static Pattern pattern;

    // non-static Matcher object because it's created from the input String
    private static Matcher matcher;

    private static String opertativeSystem = System.getProperty("os.name").toLowerCase();
    private static Pattern patternPassword;
    private static Matcher matcherPassword;
    private static String PASSWORD_PATTERN = "((?=.*[a-z])(?=.*\\d)(?=.*[A-Z])(?=.*[@.#$%!-,_*?]).{8,40})";
    private static final long MEGABYTE = 1024L * 1024L;

// <editor-fold defaultstate="collapsed" desc="getSelectItems"> 
    public static SelectItem[] getSelectItems(List<?> entities, boolean selectOne) {
        int size = selectOne ? entities.size() + 1 : entities.size();
        SelectItem[] items = new SelectItem[size];
        int i = 0;
        if (selectOne) {
            items[0] = new SelectItem("", "­­­");
            i++;
        }
        for (Object x : entities) {
            items[i++] = new SelectItem(x, x.toString());
        }
        return items;
    }    // </editor-fold>
// <editor-fold defaultstate="collapsed" desc="logout"> 

    public String logout() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return "/index";
    }    // </editor-fold>
// <editor-fold defaultstate="collapsed" desc="errorMessage"> 

    public static void errorMessage(Exception ex, String defaultMsg) {
        String msg = ex.getLocalizedMessage();
        if (msg != null && msg.length() > 0) {
            errorMessage(msg);
        } else {
            errorMessage(defaultMsg);
        }
    }    // </editor-fold>
// <editor-fold defaultstate="collapsed" desc="errorMessages"> 

    public static void errorMessages(List<String> messages) {
        for (String message : messages) {
            errorMessage(message);

        }
    }    // </editor-fold>
// <editor-fold defaultstate="collapsed" desc="errorMessage(String msg)"> 

    public static void errorMessage(String msg) {
        FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                msg, msg);
        FacesContext.getCurrentInstance().addMessage(null, facesMsg);
        ConsoleUtil.error(msg);

        // LOG.warning(msg);
    }    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="testMessage"> 
    public static void testMessage(String msg) {
        FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                msg, msg);
        FacesContext.getCurrentInstance().addMessage(null, facesMsg);
        LOG.warning(msg);
    }    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="successMessage"> 
    public static void successMessage(String msg) {
        FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, msg,
                msg);
        FacesContext.getCurrentInstance().addMessage("successInfo", facesMsg);
    }    // </editor-fold>
// <editor-fold defaultstate="collapsed" desc="warningMessage"> 

    public static void warningMessage(String msg) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, msg, ""));
        LOG.warning(msg);
    }    // </editor-fold>
// <editor-fold defaultstate="collapsed" desc="warningMessage(String msg, Exception e)"> 

    public static void warningMessage(String msg, Exception e) {
        String msgExtra = "";
        if (e == null) {

        } else {
            msgExtra = e.getLocalizedMessage();
        }
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, msg + " " + msgExtra, ""));
        LOG.warning(msg);
    }    // </editor-fold>
// <editor-fold defaultstate="collapsed" desc="fatalMessage"> 

    public static void fatalMessage(String msg) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, msg, ""));
        LOG.warning(msg);
    }    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="getRequestParameter"> 
    public static String getRequestParameter(String key) {
        return FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get(key);
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="getObjectFromRequestParameter"> 
    public static Object getObjectFromRequestParameter(String requestParameterName,
            Converter converter, UIComponent component) {
        String theId = JsfUtil.getRequestParameter(requestParameterName);
        return converter.getAsObject(FacesContext.getCurrentInstance(), component, theId);
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="infoDialog"> 
    public static void infoDialog(String titulo, String texto) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, titulo,
                texto);
        //   PrimeFaces.current().dialog().showMessageDynamic(message);
    }

    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="warningDialog"> 
    public static void warningDialog(String titulo, String texto) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, titulo,
                texto);

//        PrimeFaces.current().dialog().showMessageDynamic(message);
    }    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="warningDialog(String titulo, String texto, Exception e)"> 

    public static void warningDialog(String titulo, String texto, Exception e) {
        String msgExtra = "";
        if (e == null) {

        } else {
            msgExtra = e.getLocalizedMessage();
        }
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, titulo,
                texto + " " + msgExtra);

//        PrimeFaces.current().dialog().showMessageDynamic(message);
    }    // </editor-fold>
// <editor-fold defaultstate="collapsed" desc="fatalDialog"> 

    public static void fatalDialog(String titulo, String texto) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, titulo,
                texto);
//        PrimeFaces.current().dialog().showMessageDynamic(message);
    }    // </editor-fold>
// <editor-fold defaultstate="collapsed" desc="errorDialog(String titulo, String texto)"> 

    public static void errorDialog(String titulo, String texto) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                titulo, texto);
//        PrimeFaces.current().dialog().showMessageDynamic(message);
    }    // </editor-fold>
// <editor-fold defaultstate="collapsed" desc="errorDialog(String titulo, String texto, Exception e)"> 

    public static void errorDialog(String titulo, String texto, Exception e) {
        String msgExtra = "";
        if (e == null) {

        } else {
            msgExtra = e.getLocalizedMessage();
        }
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                titulo, texto + " " + msgExtra);
//        PrimeFaces.current().dialog().showMessageDynamic(message);
    }    // </editor-fold>
// <editor-fold defaultstate="collapsed" desc="converterDate"> 

    public static java.sql.Date converterDate(java.util.Date fecha) {
        try {
            long lfecha = fecha.getTime();
            java.sql.Date dtfecha = new java.sql.Date(lfecha);
            return dtfecha;
        } catch (Exception e) {
            errorMessage("converterDate() " + e.getLocalizedMessage());
        }
        return null;
    }    // </editor-fold>
// <editor-fold defaultstate="collapsed" desc="isDummySelectItem"> 

    public static boolean isDummySelectItem(UIComponent component, String value) {
        for (UIComponent children : component.getChildren()) {
            if (children instanceof UISelectItem) {
                UISelectItem item = (UISelectItem) children;
                if (item.getItemValue() == null && item.getItemLabel().equals(value)) {
                    return true;
                }
                break;
            }
        }
        return false;
    }    // </editor-fold>

// <editor-fold defaultstate="collapsed" desc="getComponentMessages"> 
    public static String getComponentMessages(String clientComponent, String defaultMessage) {
        FacesContext fc = FacesContext.getCurrentInstance();
        UIComponent component = UIComponent.getCurrentComponent(fc).findComponent(clientComponent);
        if (component instanceof UIInput) {
            UIInput inputComponent = (UIInput) component;
            if (inputComponent.isValid()) {
                return defaultMessage;
            } else {
                Iterator<FacesMessage> iter = fc.getMessages(inputComponent.getClientId());
                if (iter.hasNext()) {
                    return iter.next().getDetail();
                }
            }
        }
        return "";
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="getRootCause"> 
    public static Throwable getRootCause(Throwable cause) {
        if (cause != null) {
            Throwable source = cause.getCause();
            if (source != null) {
                return getRootCause(source);
            } else {
                return cause;
            }
        }
        return null;
    }
    // </editor-fold>
// <editor-fold defaultstate="collapsed" desc="md5"> 

    public static String md5(String clear) throws Exception {
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] b = md.digest(clear.getBytes());
        int size = b.length;
        StringBuffer h = new StringBuffer(size);
        //algoritmo y arreglo md5
        for (int i = 0; i < size; i++) {
            int u = b[i] & 255;
            if (u < 16) {
                h.append("0" + Integer.toHexString(u));
            } else {
                h.append(Integer.toHexString(u));
            }
        }
        //clave encriptada
        return h.toString();
    }    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="getUUID"> 
    /**
     * genera id
     *
     * @return returna un randomUUID automatico
     */
    public static String getUUID() {

        UUID uuid = UUID.randomUUID();
        return uuid.toString();

    }// </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="getUUIDMinusculas()"> 

    /**
     * genera id
     *
     * @return returna un randomUUID automatico
     */
    public static String getUUIDMinusculas() {

        UUID uuid = UUID.randomUUID();
        return uuid.toString().toLowerCase();

    }// </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="getUUIDMinusculas()"> 

    /**
     * genera id
     *
     * @return returna un randomUUID automatico
     */
    public static String getUUIDMayusculas() {

        UUID uuid = UUID.randomUUID();
        return uuid.toString().toUpperCase();

    }// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="getExtension"> 
    /**
     * getExtension()
     *
     * @param texto
     * @return la extension de un nombre de archivo
     */
    public static String getExtension(String texto) {
        try {
            return texto.substring(texto.indexOf("."), texto.length());
        } catch (Exception e) {
            errorMessage(nameOfMethod() + " " + e.getLocalizedMessage());
        }
        return "";
    }
// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="copyFile">
/*
     copia un archivo generalmente cuando se usa el fileupload
     fileName: nombre del archivo a copiar
     in: Es el InputStream
     destination: ruta donde se guardara el archivo

     */
    public static Boolean copyFile(String fileName, InputStream in, String destination) {
        try {

            // write the inputStream to a FileOutputStream
            OutputStream out = new FileOutputStream(new File(destination + fileName));

            int read = 0;
            byte[] bytes = new byte[1024];

            while ((read = in.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }

            in.close();
            out.flush();
            out.close();

            return true;
        } catch (IOException e) {
            errorMessage(nameOfMethod() + " " + e.getLocalizedMessage());
        }
        return false;
    }
// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="getPathFotos">
    public static String getPathFotos() {
        try {

            String path = getPath() + "resources/fotos/";
            return path;
        } catch (Exception e) {

            errorMessage("getPathFotosPlagas() " + e.getLocalizedMessage());
        }
        return null;

    }
// </editor-fold>

// <editor-fold defaultstate="collapsed" desc="getPath"> 

    /*
     devuelve el path 
     */
    public static String getPath() {
        try {
            ServletContext ctx = (ServletContext) FacesContext.getCurrentInstance()
                    .getExternalContext().getContext();
            return ctx.getRealPath("/");

        } catch (Exception e) {

            errorMessage("getPath() " + e.getLocalizedMessage());
        }
        return null;

    }// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="getAddPathResources"> 
    /**
     *
     * @param folder forExample: getPathResources("/fotos/paises/");
     * @return getPath() + "resources"+folder
     *
     */
    public static String getAddPathResources(String folder) {

        try {

            String path = getPath() + "resources" + folder;
            return path;
        } catch (Exception e) {

            errorMessage("getAddPathResources() " + e.getLocalizedMessage());
        }
        return null;

    }// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="redondear">
    public static Double redondear(Double n, Integer decimales) {
        Double r = 0.0;
        try {
            switch (decimales) {
                case 0:
                    r = Math.floor(n);
                    break;
                case 1:
                    r = (double) Math.round(n * 10) / 10;

                    break;

                case 2:
                    r = (double) Math.round(n * 100) / 100;
                    break;
                case 3:
                    r = (double) Math.round(n * 1000) / 1000;
                    break;
                case 4:
                    r = (double) Math.round(n * 10000) / 10000;
                    break;
            }

            return r;
        } catch (Exception e) {
            errorMessage("redondear() " + e.getLocalizedMessage());
        }
        return r;
    }// </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="redondearEnteroSuperior(Double n)">

    public static Double redondearEnteroSuperior(Double n) {
        Double r = 0.0;
        try {

            return Math.ceil(n);
        } catch (Exception e) {
            errorMessage("redondearEnteroSuperior() " + e.getLocalizedMessage());
        }
        return r;
    }// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="redondearEnteroInferior(Double n)">
    public static Double redondearEnteroInferior(Double n) {
        Double r = 0.0;
        try {

            return Math.floor(n);
        } catch (Exception e) {
            errorMessage("redondearEnteroInferior() " + e.getLocalizedMessage());
        }
        return r;
    }// </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="redondearMasCercano(Double n)">

    public static Long redondearMasCercano(Double n) {
        Double r = 0.0;
        try {

            return Math.round(n);
        } catch (Exception e) {
            errorMessage("redondearEnteroInferior() " + e.getLocalizedMessage());
        }
        return r.longValue();
    }// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="generateUniqueID">
    /**
     * Genera una serie de caracteres aleatorios
     *
     * @return
     */
    public static String generateUniqueID() {
        String strValue = "";
        UUID idUnique = UUID.randomUUID();
        strValue = idUnique.toString();
        return strValue.toUpperCase();
    }// </editor-fold>
// <editor-fold defaultstate="collapsed" desc="round(Double value)"> 

    public static Double round(Double value) {

        if (value != null) {
            // System.out.println("value: " + value);
            // System.out.println("value to String: " + value.toString());
            String[] splitter = value.toString().split("\\.");
            // System.out.println("decimals: " + splitter[1].length());
            if (splitter[1].length() <= 2) {
                return value;
            } else {
                return (long) (value * 1e2) / 1e2;
            }

        } else {
            return 1d;
        }
    }// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="nombreDia"> 
    public static String nameOfDay(LocalDate date) {
        String nombre = "DOMINGO";
        try {
            DayOfWeek dia = date.getDayOfWeek();
            dia.name();
            switch (dia.name()) {
                case "SATURDAY":
                    nombre = "SABADO";
                    break;
                case "SUNDAY":
                    nombre = "DOMINGO";
                    break;
                case "MONDAY":
                    nombre = "LUNES";
                    break;
                case "TUESDAY":
                    nombre = "MARTES";
                    break;
                case "WEDNESDAY":
                    nombre = "MIERCOLES";
                    break;
                case "THURSDAY":
                    nombre = "JUEVES";
                    break;
                case "FRIDAY":
                    nombre = "VIERNES";
                    break;

            }

        } catch (Exception e) {
            errorMessage("nameOfDay() " + e.getLocalizedMessage());
        }
        return nombre;
    }
// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="letterToUpper"> 
    public String letterToUpper(String texto) {
        try {

            texto = texto.trim();
            int largo = texto.length();
            if (largo <= 0) {
                return texto;
            }
            String letra = texto.substring(0, 1);

            texto = letra.toUpperCase() + texto.substring(1);
        } catch (Exception ex) {
            // System.out.println("letterToUpper() " + ex.getLocalizedMessage());
        }
        return texto;
    }
// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="letterToLower"> 
    /**
     * ConvertirLetraMinuscula
     *
     * @param s_cadena
     * @param caracter
     * @return
     */
    public String letterToLower(String texto) {

        try {

            texto = texto.trim();
            int largo = texto.length();
            if (largo <= 0) {
                return texto;
            }
            String letra = texto.substring(0, 1);

            texto = letra.toLowerCase() + texto.substring(1);
        } catch (Exception ex) {
            // System.out.println("letterToLower() " + ex.getLocalizedMessage());
        }
        return texto;
    }// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="twoDigitString"> 
    private static String twoDigitString(int number) {

        if (number == 0) {
            return "00";
        }

        if (number / 10 == 0) {
            return "0" + number;
        }

        return String.valueOf(number);
    }// </editor-fold>

// <editor-fold defaultstate="collapsed" desc="esImpar"> 
    public static Boolean esImpar(int iNumero) {
        if (iNumero % 2 != 0) {
            return true;
        } else {
            return false;
        }
    }// </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="getIp">  

    /**
     * Devuelve el IP
     *
     * @return
     */
    public static String getIp() {
        String myip = "";
        try {
            HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            String ipAddress = request.getHeader("X-FORWARDED-FOR");
            if (ipAddress == null) {
                ipAddress = request.getRemoteAddr();

            }
            myip = ipAddress;
        } catch (Exception e) {
            errorMessage("getIp() " + e.getLocalizedMessage());
        }
        return myip;
    }// </editor-fold>

    /**
     * Encripta un texto en base a una llave
     *
     * @param textoencriptado
     * @param texto: myclavepersonal
     * @param key : mykey
     * @return
     *
     * // <editor-fold defaultstate="collapsed" desc="getSession">
     * /**
     * devuelve Un objeto Session correspondiente a la sesion local
     *
     * @return
     */
    public static HttpSession getSession() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpSession session = request.getSession();
        return session;
    }// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="getSession">  
    /**
     * devuelve Un objeto Session correspondiente a la sesion local
     *
     * @return
     */
//    public static Boolean addParametersUserNameToSession(String username) {
//        Boolean add = false;
//        try {
//            HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
//            HttpSession session = request.getSession();
//            session.setAttribute("username", username);
//            add = true;
//        } catch (Exception e) {
//            errorMessage("addParametersUserNameToSession() " + e.getLocalizedMessage());
//        }
//        
//        return add;
//    }// </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="getBrowserName()">  
    public static String getBrowserName() {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        String userAgent = externalContext.getRequestHeaderMap().get("User-Agent");

        if (userAgent.contains("MSIE")) {
            return "Internet Explorer";
        }
        if (userAgent.contains("Firefox")) {
            return "Firefox";
        }
        if (userAgent.contains("Chrome")) {
            return "Chrome";
        }
        if (userAgent.contains("Opera")) {
            return "Opera";
        }
        if (userAgent.contains("Safari")) {
            return "Safari";
        }
        return "Unknown";
    }// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="getListaNombresPaises"> 
    public static List<String> getListaNombresPaises() {
        List<String> listPaises = new ArrayList<>();
        try {

            listPaises.add("Afganistán");
            listPaises.add("Albania");
            listPaises.add("Alemania");
            listPaises.add("Algeria");
            listPaises.add("Andorra");
            listPaises.add("Angola");
            listPaises.add("Anguila");
            listPaises.add("Antártida");
            listPaises.add("Antigua y Barbuda");
            listPaises.add("Antillas Neerlandesas");
            listPaises.add("Arabia Saudita");
            listPaises.add("Argentina");
            listPaises.add("Armenia");
            listPaises.add("Aruba");
            listPaises.add("Australia");
            listPaises.add("Austria");
            listPaises.add("Azerbayán");
            listPaises.add("Bélgica");
            listPaises.add("Bahamas");
            listPaises.add("Bahrein");
            listPaises.add("Bangladesh");
            listPaises.add("Barbados");
            listPaises.add("Belice");
            listPaises.add("Benín");
            listPaises.add("Bhután");
            listPaises.add("Bielorrusia");
            listPaises.add("Birmania");
            listPaises.add("Bolivia");
            listPaises.add("Bosnia y Herzegovina");
            listPaises.add("Botsuana");
            listPaises.add("Brasil");
            listPaises.add("Brunéi");
            listPaises.add("Bulgaria");
            listPaises.add("Burkina Faso");
            listPaises.add("Burundi");
            listPaises.add("Cabo Verde");
            listPaises.add("Camboya");
            listPaises.add("Camerún");
            listPaises.add("Canadá");
            listPaises.add("Chad");
            listPaises.add("Chile");
            listPaises.add("China");
            listPaises.add("Chipre");
            listPaises.add("Ciudad del Vaticano");
            listPaises.add("Colombia");
            listPaises.add("Comoras");
            listPaises.add("Congo");
            listPaises.add("Corea del Norte");
            listPaises.add("Corea del Sur");
            listPaises.add("Costa de Marfil");
            listPaises.add("Costa Rica");
            listPaises.add("Croacia");
            listPaises.add("Cuba");
            listPaises.add("Dinamarca");
            listPaises.add("Dominica");
            listPaises.add("Ecuador");
            listPaises.add("Egipto");
            listPaises.add("El Salvador");
            listPaises.add("Emiratos Árabes Unidos");
            listPaises.add("Eritrea");
            listPaises.add("Eslovaquia");
            listPaises.add("Eslovenia");
            listPaises.add("España");
            listPaises.add("Estados Unidos de América");
            listPaises.add("Estonia");
            listPaises.add("Etiopía");
            listPaises.add("Filipinas");
            listPaises.add("Finlandia");
            listPaises.add("Fiyi");
            listPaises.add("Francia");
            listPaises.add("Gabón");
            listPaises.add("Gambia");
            listPaises.add("Georgia");
            listPaises.add("Ghana");
            listPaises.add("Gibraltar");
            listPaises.add("Granada");
            listPaises.add("Grecia");
            listPaises.add("Groenlandia");
            listPaises.add("Guadalupe");
            listPaises.add("Guam");
            listPaises.add("Guatemala");
            listPaises.add("Guayana Francesa");
            listPaises.add("Guernsey");
            listPaises.add("Guinea");
            listPaises.add("Guinea Ecuatorial");
            listPaises.add("Guinea-Bissau");
            listPaises.add("Guyana");
            listPaises.add("Haití");
            listPaises.add("Honduras");
            listPaises.add("Hong kong");
            listPaises.add("Hungría");
            listPaises.add("India");
            listPaises.add("Indonesia");
            listPaises.add("Irán");
            listPaises.add("Irak");
            listPaises.add("Irlanda");
            listPaises.add("Isla Bouvet");
            listPaises.add("Isla de Man");
            listPaises.add("Isla de Navidad");
            listPaises.add("Isla Norfolk");
            listPaises.add("Islandia");
            listPaises.add("Islas Bermudas");
            listPaises.add("Islas Caimán");
            listPaises.add("Islas Cocos (Keeling)");
            listPaises.add("Islas Cook");
            listPaises.add("Islas de Åland");
            listPaises.add("Islas Feroe");
            listPaises.add("Islas Georgias del Sur y Sandwich del Sur");
            listPaises.add("Islas Heard y McDonald");
            listPaises.add("Islas Maldivas");
            listPaises.add("Islas Malvinas");
            listPaises.add("Islas Marianas del Norte");
            listPaises.add("Islas Marshall");
            listPaises.add("Islas Pitcairn");
            listPaises.add("Islas Salomón");
            listPaises.add("Islas Turcas y Caicos");
            listPaises.add("Islas Ultramarinas Menores de Estados Unidos");
            listPaises.add("Islas Vírgenes Británicas");
            listPaises.add("Islas Vírgenes de los Estados Unidos");
            listPaises.add("Israel");
            listPaises.add("Italia");
            listPaises.add("Jamaica");
            listPaises.add("Japón");
            listPaises.add("Jersey");
            listPaises.add("Jordania");
            listPaises.add("Kazajistán");
            listPaises.add("Kenia");
            listPaises.add("Kirgizstán");
            listPaises.add("Kiribati");
            listPaises.add("Kuwait");
            listPaises.add("Líbano");
            listPaises.add("Laos");
            listPaises.add("Lesoto");
            listPaises.add("Letonia");
            listPaises.add("Liberia");
            listPaises.add("Libia");
            listPaises.add("Liechtenstein");
            listPaises.add("Lituania");
            listPaises.add("Luxemburgo");
            listPaises.add("México");
            listPaises.add("Mónaco");
            listPaises.add("Macao");
            listPaises.add("Macedônia");
            listPaises.add("Madagascar");
            listPaises.add("Malasia");
            listPaises.add("Malawi");
            listPaises.add("Mali");
            listPaises.add("Malta");
            listPaises.add("Marruecos");
            listPaises.add("Martinica");
            listPaises.add("Mauricio");
            listPaises.add("Mauritania");
            listPaises.add("Mayotte");
            listPaises.add("Micronesia");
            listPaises.add("Moldavia");
            listPaises.add("Mongolia");
            listPaises.add("Montenegro");
            listPaises.add("Montserrat");
            listPaises.add("Mozambique");
            listPaises.add("Namibia");
            listPaises.add("Nauru");
            listPaises.add("Nepal");
            listPaises.add("Nicaragua");
            listPaises.add("Niger");
            listPaises.add("Nigeria");
            listPaises.add("Niue");
            listPaises.add("Noruega");
            listPaises.add("Nueva Caledonia");
            listPaises.add("Nueva Zelanda");
            listPaises.add("Omán");
            listPaises.add("Países Bajos");
            listPaises.add("Pakistán");
            listPaises.add("Palau");
            listPaises.add("Palestina");
            listPaises.add("Panamá");
            listPaises.add("Papúa Nueva Guinea");
            listPaises.add("Paraguay");
            listPaises.add("Perú");
            listPaises.add("Polinesia Francesa");
            listPaises.add("Polonia");
            listPaises.add("Portugal");
            listPaises.add("Puerto Rico");
            listPaises.add("Qatar");
            listPaises.add("Reino Unido");
            listPaises.add("República Centroafricana");
            listPaises.add("República Checa");
            listPaises.add("República Dominicana");
            listPaises.add("Reunión");
            listPaises.add("Ruanda");
            listPaises.add("Rumanía");
            listPaises.add("Rusia");
            listPaises.add("Sahara Occidental");
            listPaises.add("Samoa");
            listPaises.add("Samoa Americana");
            listPaises.add("San Bartolomé");
            listPaises.add("San Cristóbal y Nieves");
            listPaises.add("San Marino");
            listPaises.add("San Martín (Francia)");
            listPaises.add("San Pedro y Miquelón");
            listPaises.add("San Vicente y las Granadinas");
            listPaises.add("Santa Elena");
            listPaises.add("Santa Lucía");
            listPaises.add("Santo Tomé y Príncipe");
            listPaises.add("Senegal");
            listPaises.add("Serbia");
            listPaises.add("Seychelles");
            listPaises.add("Sierra Leona");
            listPaises.add("Singapur");
            listPaises.add("Siria");
            listPaises.add("Somalia");
            listPaises.add("Sri lanka");
            listPaises.add("Sudáfrica");
            listPaises.add("Sudán");
            listPaises.add("Suecia");
            listPaises.add("Suiza");
            listPaises.add("Surinám");
            listPaises.add("Svalbard y Jan Mayen");
            listPaises.add("Swazilandia");
            listPaises.add("Tadjikistán");
            listPaises.add("Tailandia");
            listPaises.add("Taiwán");
            listPaises.add("Tanzania");
            listPaises.add("Territorio Británico del Océano Índico");
            listPaises.add("Territorios Australes y Antárticas Franceses");
            listPaises.add("Timor Oriental");
            listPaises.add("Togo");
            listPaises.add("Tokelau");
            listPaises.add("Tonga");
            listPaises.add("Trinidad y Tobago");
            listPaises.add("Tunez");
            listPaises.add("Turkmenistán");
            listPaises.add("Turquía");
            listPaises.add("Tuvalu");
            listPaises.add("Ucrania");
            listPaises.add("Uganda");
            listPaises.add("Uruguay");
            listPaises.add("Uzbekistán");
            listPaises.add("Vanuatu");
            listPaises.add("Venezuela");
            listPaises.add("Vietnam");
            listPaises.add("Wallis y Futuna");
            listPaises.add("Yemen");
            listPaises.add("Yibuti");
            listPaises.add("Zambia");
            listPaises.add("Zimbabue");
        } catch (Exception e) {
        }
        return listPaises;
    }

    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="generarCodigoDigitos">
    /**
     *
     * @param n
     * @param digitos
     * @return
     */
    public static String generarCodigoDigitos(Integer n, Integer digitos) {
        String t = String.valueOf(n);
        try {
            if (t.length() >= digitos) {
                return t;
            } else {
                Integer dif = digitos - t.length();
                String c = "";
                for (int i = 0; i < dif; i++) {
                    c += "0";
                }
                t = c.trim() + t.trim();
            }

        } catch (Exception e) {
            errorMessage("generarCodigoDigitos() " + e.getLocalizedMessage());
        }

        return t.trim();
    }

    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="generarCodigoDigitos"> 
    public static Integer getEntero(Double n) {
        Integer value = 0;
        try {
            String str = String.valueOf(n);
            value = Integer.parseInt(str.substring(0, str.indexOf('.')));

        } catch (Exception e) {
            errorMessage("getEntero() " + e.getLocalizedMessage());
        }

        return value;
    }

    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="getDecimal"> 
    public static Integer getDecimal(Double n) {
        Integer value = 0;
        try {
            String str = String.valueOf(n);
            value = Integer.parseInt(str.substring(str.indexOf('.') + 1));

        } catch (Exception e) {
            errorMessage("getDecimal() " + e.getLocalizedMessage());
        }

        return value;
    }

    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="getRandomNumber"> 
    public static Integer getRandomNumber(int min, int max) {
        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }

        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="isVacio(String texto)()"> 
    /**
     * return true si es null empty equals("")
     *
     * @param texto
     * @return
     */
    public static Boolean isVacio(String texto) {
        texto = texto.trim();
        return texto == null || texto.equals("") || texto.isEmpty();
    }// </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="isVacio(Integer texto)"> 

    /**
     * return true si es null empty equals("")
     *
     * @param texto
     * @return
     */
    public static Boolean isVacio(Integer texto) {
        return texto == null || texto.equals("");
    }// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="isVacio(Integer texto)"> 
    /**
     * return true si es null empty equals("")
     *
     * @param texto
     * @return
     */
    public static Boolean isVacio(Double texto) {
        return texto == null || texto.equals("");
    }// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Boolean isNegativo(Double texto)"> 
    /**
     * return true si es null empty equals("")
     *
     * @param texto
     * @return
     */
    public static Boolean isNegativo(Double numero) {
        return numero == null || numero.equals("") || numero < 0;
    }// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Boolean isNegativo(Double texto)"> 
    /**
     * return true si es null empty equals("")
     *
     * @param texto
     * @return
     */
    public static Boolean isNegativo(Integer numero) {
        return numero == null || numero.equals("") || numero < 0;
    }// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="primercaracter()">
    public static String primerCaracter(String texto) {
        try {
            if (texto.length() > 0) {
                return texto.trim().substring(0, 1).toUpperCase();
            }

        } catch (Exception e) {

            errorMessage(nameOfMethod() + " " + e.getLocalizedMessage());
        }
        return texto;
    }

    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="emailValidate(String email)">
    public static Boolean emailValidate(String email) {
        pattern = Pattern.compile(EMAIL_REGEX, Pattern.CASE_INSENSITIVE);
        matcher = pattern.matcher(email);
        return matcher.matches();
    }// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="nameOfClassAndMethod()">
    public static String nameOfClassAndMethod() {
        final StackTraceElement e = Thread.currentThread().getStackTrace()[2];
        final String s = e.getClassName();
        return s.substring(s.lastIndexOf('.') + 1, s.length()) + "." + e.getMethodName();
    }// </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="nameOfClass()">

    public static String nameOfClass() {
        final StackTraceElement e = Thread.currentThread().getStackTrace()[2];
        final String s = e.getClassName();
        return s.substring(s.lastIndexOf('.') + 1, s.length());
    }    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="nameOfMethod(">
    public static String nameOfMethod() {
        final StackTraceElement e = Thread.currentThread().getStackTrace()[2];
        final String s = e.getClassName();
        return e.getMethodName();
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc=" String textoDespuesUltimoPunto(String texto)">
    /**
     * obtiene el texto despues del ultimo puento
     *
     * @param texto (com.avbravo.entity.Rol)
     * @return Rol
     */
    public static String textoDespuesUltimoPunto(String texto) {
        String result = "";
        // TODO code application logic here
        try {

            Integer pos = texto.lastIndexOf(".");

            result = texto.substring(pos + 1, texto.length());

        } catch (Exception e) {
        }
        return result;

    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="boolean isValidEmail(String email()">
    public static Boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."
                + "[a-zA-Z0-9_+&*-]+)*@"
                + "(?:[a-zA-Z0-9-]+\\.)+[a-z"
                + "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null) {
            return false;
        }
        return pat.matcher(email).matches();
    }  // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Object copyBeans(Object destino, Object fuente)">
    /**
     * Copia el contenido de un bean en otro
     *
     * @param destino
     * @param fuente
     * @return
     */
    /**
     * Agrega una lista a un TreeMap con duplicados
     *
     * @param destino
     * @param fuente
     * @return Creado por Israel Deago /Modificado para Genericos
     * @email: isde115@gmail.com Ejemplo de uso TreeMap<Integer, List<Integer>>
     * treeMap = new TreeMap<>(); treeMap.put(55, new ArrayList<>());
     * JsfUtil.addListToMapWithDuplicated(treeMap, 55, 55);
     * JsfUtil.addListToMapWithDuplicated(treeMap, 55, 438);
     * JsfUtil.addListToMapWithDuplicated(treeMap, 55, 900);
     * JsfUtil.addListToMapWithDuplicated(treeMap, 55, 1025);
     * JsfUtil.addListToMapWithDuplicated(treeMap, 55, 3020); treeMap.put(20,
     * new ArrayList<>()); JsfUtil.addListToMapWithDuplicated(treeMap, 20,
     * 3020); JsfUtil.addListToMapWithDuplicated(treeMap, 20, 500);
     * JsfUtil.addListToMapWithDuplicated(treeMap, 20, 1000); //
     * System.out.println("Lista " + treeMap);
     *
     * Salida Lista {20=[3020, 500, 1000], 55=[55, 438, 900, 1025, 3020]}
     */
    public static <T, U> void addListToMapWithDuplicated(TreeMap treeMap, U mapKey, T valor) {
        List<T> lista = (List<T>) treeMap.get(mapKey);

        // Se crea si esta vacia
        if (lista == null) {
            lista = new ArrayList<>();
            lista.add(valor);
            treeMap.put(mapKey, lista);
        } else {

            lista.add(valor);
        }
    }// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="totalCaracteresVaciosAlfinalCadena() ">
    /**
     * Cuenta la cantidad de espacios al final de una cadena
     *
     * @param texto
     * @return
     */
    public static Integer totalEspaciosAlfinalCadena(String texto) {
        Integer count = 0;
        for (int x = texto.length() - 1; x > 0; x--) {
            // System.out.println("Inverso " + x + ": " + texto.charAt(x));
            char c = texto.charAt(x);

            if (texto.charAt(x) == ' ') {
                count++;
            }
        }
        return count;

    }

    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc=" updateJSFComponent(String jsfcomponent)">
    /**
     * Actualiza un componente JSF en un formulario .xhtml
     *
     * @param texto
     * @return
     */
    public static String updateJSFComponent(String jsfcomponent) {
//        PrimeFaces.current().ajax().update(jsfcomponent);
        return "";

    }

    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc=" Integer decenaDeUnEntero(Integer  n)">
    public static Integer decenaDeUnEntero(Integer n) {
        Integer decena = 0;
        try {
            if (n == null || n.equals("")) {
                return 0;
            }
            String x = String.valueOf(n);
            if (x.length() == 0) {
                return 0;
            }
            decena = Integer.parseInt(x.substring(0, x.length() - 1));
        } catch (Exception e) {
            // System.out.println("decenaDeUnEntero() " + e.getLocalizedMessage());
        }

        return decena;
    }    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="String closeDialog(String widgetVarDialog)">
    public static String closeDialog(String widgetVarDialog) {
        try {
//            PrimeFaces current = PrimeFaces.current();

            String dialog = "PF('" + widgetVarDialog + "').hide();";

//            current.executeScript(dialog);
        } catch (Exception e) {
            errorDialog("closeDialog()", e.getLocalizedMessage());
        }
        return "";
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="isWindows()">
    /*
    Implementado desde el ejemplo de Mkyong
    https://mkyong.com/java/how-to-detect-os-in-java-systemgetpropertyosname/
     */
    public static boolean isWindows() {

        return (opertativeSystem.indexOf("win") >= 0);

    }

    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="isMac()">
    public static boolean isMac() {

        return (opertativeSystem.indexOf("mac") >= 0);

    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="boolean isLinux()">
    public static boolean isLinux() {

        return (opertativeSystem.indexOf("nix") >= 0 || opertativeSystem.indexOf("nux") >= 0 || opertativeSystem.indexOf("aix") > 0);

    }
// </editor-fold>
// <editor-fold defaultstate="collapsed" desc="boolean isSolaris()">

    public static boolean isSolaris() {

        return (opertativeSystem.indexOf("sunos") >= 0);

    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="String conversorTfhka(String d, String tipo)">
    /**
     *
     * @param d
     * @param tipo
     * @return convierte un string para impresora fiscal
     */
    public static String conversorTfhka(String d, String tipo) {
        try {
            Integer tam = d.length();
            String r = "";
            switch (tipo) {

                case "preciounitario":

                    if (tam != 8) {
                        for (int i = 0; i < 8 - tam; i++) {
                            r += "0";
                        }
                        d = r + d;
                    }

                    break;
                case "cantidad":

                    if (tam != 5) {
                        for (int i = 0; i < 5 - tam; i++) {
                            r += "0";
                        }
                        d = r + d;
                    }

                    break;
            }

        } catch (Exception e) {
            JsfUtil.warningMessage("conversor() " + e.getLocalizedMessage().toString());

        }
        return d;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="String fileSeparator()">
    //https://docs.oracle.com/javase/tutorial/essential/environment/sysprop.html
    public static String fileSeparator() {
        return System.getProperty("file.separator");

    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="String javaClassPath()">
    public static String javaClassPath() {
        return System.getProperty("java.class.path");

    }
    // </editor-fold>

    public static String javaHome() {
        return System.getProperty("java.home");

    }

    public static String javaVendor() {
        return System.getProperty("java.vendor");

    }

    public static String javaVendorUrl() {
        return System.getProperty("java.vendor.url");

    }

    public static String javaVersion() {
        return System.getProperty("java.version");

    }

    public static String lineSeparator() {
        return System.getProperty("line.separator");

    }

    public static String osArch() {
        return System.getProperty("os.arch");

    }

    public static String osName() {
        return System.getProperty("os.name");

    }

    public static String osVersion() {
        return System.getProperty("os.version");

    }

    public static String pathSeparator() {
        return System.getProperty("path.separator");

    }

    public static String userDir() {
        return System.getProperty("user.dir");

    }

    public static String userHome() {
        return System.getProperty("user.home");

    }

    public static String userName() {
        return System.getProperty("user.name");

    }

    // <editor-fold defaultstate="collapsed" desc="Boolean unzip(String fileZip, String directoryDestine)">
    public static Boolean unzip(String fileZip, String directoryDestine) {
        try {
            File destDir = new File(directoryDestine);
            byte[] buffer = new byte[1024];
            ZipInputStream zis = new ZipInputStream(new FileInputStream(fileZip));
            ZipEntry zipEntry = zis.getNextEntry();
            while (zipEntry != null) {
                File newFile = newFileForUnzip(destDir, zipEntry);
                FileOutputStream fos = new FileOutputStream(newFile);
                int len;
                while ((len = zis.read(buffer)) > 0) {
                    fos.write(buffer, 0, len);
                }
                fos.close();
                zipEntry = zis.getNextEntry();
            }
            zis.closeEntry();
            zis.close();
            return true;
        } catch (Exception e) {
            errorDialog("unzip()", e.getLocalizedMessage());
        }
        return false;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="File newFileForUnzip(File destinationDir, ZipEntry zipEntry))">
    public static File newFileForUnzip(File destinationDir, ZipEntry zipEntry) throws IOException {
        File destFile = new File(destinationDir, zipEntry.getName());

        String destDirPath = destinationDir.getCanonicalPath();
        String destFilePath = destFile.getCanonicalPath();

        if (!destFilePath.startsWith(destDirPath + File.separator)) {
            throw new IOException("Entry is outside of the target dir: " + zipEntry.getName());
        }

        return destFile;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc=" Boolean runCommand(String command)">
    /**
     *
     * @param command
     * @return ejecuta comandos del sistema operativo
     */
    public static Boolean runCommand(String command) {
        try {

            Runtime.getRuntime().exec(command);

            return true;
        } catch (Exception e) {
            errorDialog("runCommand()", e.getLocalizedMessage());
        }
        return false;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Boolean mkdir(String directoryPath)">
    /**
     * Crea directorio especificado en el path
     *
     * @param directoryPath
     * @return
     */
    public static Boolean mkdir(String directoryPath) {
        try {
            File directorio = new File(directoryPath);
            if (!directorio.exists()) {
                //Crear el directorio
                if (directorio.mkdirs()) {
                    return true;
//
                } else {
                    return false;
                }
            }

        } catch (Exception e) {
            errorDialog("mkdir()", e.getLocalizedMessage());
        }
        return false;
    }
// </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Boolean existDirectory(String directoryPath)">

    /**
     * Crea directorio especificado en el path
     *
     * @param directoryPath
     * @return
     */
    public static Boolean existDirectory(String directoryPath) {
        try {
            File directorio = new File(directoryPath);
            if (directorio.exists()) {
                return true;

            }

        } catch (Exception e) {
            errorDialog("existDirectory()", e.getLocalizedMessage());
        }
        return false;
    }
// </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="String nameOfFileInPath(String filenamePath)">

    /**
     *
     * @param filenamePath
     * @return el nombre del archivo que esta en un path
     */
    public static String nameOfFileInPath(String filenamePath) {
        String name = "";
        try {
            name = filenamePath.substring(filenamePath.lastIndexOf(System.getProperty("file.separator")) + 1,
                    filenamePath.lastIndexOf('.'));
        } catch (Exception e) {
            errorDialog("nameOfFileInPath()", e.getLocalizedMessage());
        }
        return name;
    }

    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="String pathOfFile(String filenamePath)" >
    /**
     *
     * @param filenamePath
     * @return el path de un archivo
     */
    public static String pathOfFile(String filenamePath) {
        String path = "";
        try {
            path = filenamePath.substring(0, filenamePath.lastIndexOf(System.getProperty("file.separator")));
        } catch (Exception e) {
            errorDialog("pathOfFile()", e.getLocalizedMessage());
        }
        return path;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="String extensionOfFileInPath(String filenamePath)">
    /**
     *
     * @param filenamePath
     * @return devuelve la extension de un archivo en un path
     */
    public static String extensionOfFileInPath(String filenamePath) {
        String extension = "";
        try {
            extension = filenamePath.substring(filenamePath.lastIndexOf('.') + 1, filenamePath.length());
        } catch (Exception e) {
        }
        return extension;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Boolean zipDirectory(String sourceDirectory, String targetDirectoryAndNamezip)">
    /**
     *
     * @param sourceDirectory
     * @param targetDirectoryAndNamezip
     * @return Comprime una carpeta
     */
    public static Boolean zipDirectory(String sourceDirectory, String targetDirectoryAndNamezip) {
        try {

            String sourceFile = sourceDirectory;

            FileOutputStream fos = new FileOutputStream(targetDirectoryAndNamezip);
            ZipOutputStream zipOut = new ZipOutputStream(fos);
            File fileToZip = new File(sourceFile);

            zipFile(fileToZip, fileToZip.getName(), zipOut);
            zipOut.close();
            fos.close();
            return true;
        } catch (Exception e) {
            errorDialog("zipDirectory()", e.getLocalizedMessage());
        }
        return false;
    }

    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="void zipFile(File fileToZip, String fileName, ZipOutputStream zipOut) ">
    /**
     * fuente https://www.baeldung.com/java-compress-and-uncompress
     *
     * @param fileToZip
     * @param fileName
     * @param zipOut
     * @throws IOException
     */
    private static void zipFile(File fileToZip, String fileName, ZipOutputStream zipOut) {
        try {
            if (fileToZip.isHidden()) {
                return;
            }
            if (fileToZip.isDirectory()) {
                if (fileName.endsWith("/")) {
                    zipOut.putNextEntry(new ZipEntry(fileName));
                    zipOut.closeEntry();
                } else {
                    zipOut.putNextEntry(new ZipEntry(fileName + "/"));
                    zipOut.closeEntry();
                }
                File[] children = fileToZip.listFiles();
                for (File childFile : children) {
                    zipFile(childFile, fileName + "/" + childFile.getName(), zipOut);
                }
                return;
            }
            FileInputStream fis = new FileInputStream(fileToZip);
            ZipEntry zipEntry = new ZipEntry(fileName);
            zipOut.putNextEntry(zipEntry);
            byte[] bytes = new byte[1024];
            int length;
            while ((length = fis.read(bytes)) >= 0) {
                zipOut.write(bytes, 0, length);
            }
            fis.close();
        } catch (Exception e) {
            errorDialog("zipFile()", e.getLocalizedMessage());
        }

    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="unzipFileToDirectory(String fileZipWithPath, String pathToUnzip)">
    /**
     * Fuente
     * https://examples.javacodegeeks.com/core-java/util/zip/extract-zip-file-with-subdirectories/
     *
     * @param fileZipWithPath
     * @param pathToUnzip
     * @return
     */
    public static Boolean unzipFileToDirectory(String fileZipWithPath, String pathToUnzip) {
        try {

            String filename = fileZipWithPath;

            File srcFile = new File(filename);

            // create a directory with the same name to which the contents will be extracted
            String zipPath = filename.substring(0, filename.length() - 4);
            File temp = new File(zipPath);
            temp.mkdir();

            ZipFile zipFile = null;

            try {

                zipFile = new ZipFile(srcFile);

                // get an enumeration of the ZIP file entries
                Enumeration<? extends ZipEntry> e = zipFile.entries();

                while (e.hasMoreElements()) {

                    ZipEntry entry = e.nextElement();

                    File destinationPath = new File(zipPath, entry.getName());

                    //create parent directories
                    destinationPath.getParentFile().mkdirs();

                    // if the entry is a file extract it
                    if (entry.isDirectory()) {
                        continue;
                    } else {

                        // System.out.println("Extracting file: " + destinationPath);
                        BufferedInputStream bis = new BufferedInputStream(zipFile.getInputStream(entry));

                        int b;
                        byte buffer[] = new byte[1024];

                        FileOutputStream fos = new FileOutputStream(destinationPath);

                        BufferedOutputStream bos = new BufferedOutputStream(fos, 1024);

                        while ((b = bis.read(buffer, 0, 1024)) != -1) {
                            bos.write(buffer, 0, b);
                        }

                        bos.close();
                        bis.close();

                    }

                }
                return true;
            } catch (IOException ioe) {
                // System.out.println("Error opening zip file" + ioe);
            } finally {
                try {
                    if (zipFile != null) {
                        zipFile.close();
                    }
                } catch (IOException ioe) {
                    // System.out.println("Error while closing zip file" + ioe);
                }
            }

        } catch (Exception e) {
            errorDialog("unzipFileToDirectory()", e.getLocalizedMessage());
        }
        return false;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Long curretnTimeMillis()">
    public static Long curretnTimeMillis() {
        return System.currentTimeMillis();
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Boolean appendTextToFile(String filePath, String text) ">
    /**
     *
     * @return
     */
    public static Boolean appendTextToFile(String filePath, String text) {
        try {

            Charset utf8 = StandardCharsets.UTF_8;
            List<String> list = Arrays.asList(text);

            try {

                Files.write(Paths.get(filePath), list, utf8,
                        StandardOpenOption.CREATE, StandardOpenOption.APPEND);
                return true;
            } catch (IOException x) {
                System.err.format("IOException: %s%n", x);
                errorDialog("pathOfFile()", x.getLocalizedMessage());
            }

        } catch (Exception e) {
            errorDialog("pathOfFile()", e.getLocalizedMessage());
        }
        return false;
    }

// </editor-fold>    
    // <editor-fold defaultstate="collapsed" desc="Boolean appendTextToFileJSonFormat(String filePath, String text) ">
    /**
     *
     * @return
     */
    public static Boolean appendTextToFileJSonFormat(String filePath, String text) {
        try {

            Charset utf8 = StandardCharsets.UTF_8;
            List<String> list = Arrays.asList(text);

            try {

                Files.write(Paths.get(filePath), list, utf8,
                        StandardOpenOption.CREATE, StandardOpenOption.APPEND);
                return true;
            } catch (IOException x) {
                System.err.format("IOException: %s%n", x);
                errorDialog("pathOfFile()", x.getLocalizedMessage());
            }

        } catch (Exception e) {
            errorDialog("pathOfFile()", e.getLocalizedMessage());
        }
        return false;
    }

// </editor-fold>    
    // <editor-fold defaultstate="collapsed" desc="Boolean appendTextToLogFile(String filePath, String nameOfClass, String nameOfMethod, String text, Boolean isError)">
    /**
     *
     * @return
     */
// </editor-fold>    
    // <editor-fold defaultstate="collapsed" desc="Boolean appendTextToLogErrorFile(String filePath, String nameOfClass, String nameOfMethod, String text, Exception exception, Boolean generateDailyFile) {">
    /**
     *
     * @return
     */
    // </editor-fold>    
    // <editor-fold defaultstate="collapsed" desc="Boolean appendTextToLogErrorFile(String filePath, String nameOfClass, String nameOfMethod, String text, Exception exception, Boolean generateDailyFile) {">
    /**
     *
     * @return
     */
    // </editor-fold>    
    // <editor-fold defaultstate="collapsed" desc="Boolean appendTextToLogJson(String filePath, String titulo, String text)">
    /**
     *
     * @return
     */
    // </editor-fold>    
    // <editor-fold defaultstate="collapsed" desc="Boolean existFile(String filePath) ">
    public static Boolean existFile(String filePath) {
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                return false;
                // file.createNewFile();
            }
            return true;
        } catch (Exception e) {
            errorDialog("existFile()", e.getLocalizedMessage());
        }
        return false;

    }
    // </editor-fold>   

    // <editor-fold defaultstate="collapsed" desc="deleteDirectory(File path)">
    public static void deleteDirectory(File path) {

        try {
            if (path.exists()) {
                File[] files = path.listFiles();
                for (int i = 0; i < files.length; i++) {
                    if (files[i].isDirectory()) {
                        deleteDirectory(files[i]);
                    } else {
                        files[i].delete();
                    }
                }
            }
        } catch (Exception e) {
            errorDialog("deleteDirectory()", e.getLocalizedMessage());
        }

    }

    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Boolean deleteDirectorio(String ruta) ">
    public Boolean deleteDirectorio(String ruta) {
        try {
            File file = new File(ruta);
            if (!file.exists()) {
                //existe
                return false;
            } else {
                deleteDirectory(file);
                return true;
            }
        } catch (Exception ex) {
            errorDialog("deleteDirectory()", ex.getLocalizedMessage());
        }
        return false;
    }

    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="String convertirLetraMayuscula(String texto)">
    public static String convertirLetraMayuscula(String texto) {

        try {

            texto = texto.trim();
            int largo = texto.length();
            if (largo <= 0) {
                return texto;
            }
            String letra = texto.substring(0, 1);

            texto = letra.toUpperCase() + texto.substring(1);
        } catch (Exception ex) {
            errorDialog("convertirLetraMayuscula()", ex.getLocalizedMessage());
        }
        return texto;
    }
// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="String convertirLetraMinuscula(String texto)">
    /**
     * ConvertirLetraMinuscula
     *
     * @param s_cadena
     * @param caracter
     * @return
     */
    public static String convertirLetraMinuscula(String texto) {

        try {

            texto = texto.trim();
            int largo = texto.length();
            if (largo <= 0) {
                return texto;
            }
            String letra = texto.substring(0, 1);

            texto = letra.toLowerCase() + texto.substring(1);
        } catch (Exception ex) {
            errorDialog("convertirLetraMinuscula()", ex.getLocalizedMessage());
        }
        return texto;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="agregarTextoAlFinalArchivo(String rutaArchivo, String texto)">
    public static void agregarTextoAlFinalArchivo(String rutaArchivo, String texto) {
        try {
            RandomAccessFile miRAFile;
            // Abrimos el fichero de acceso aleatorio
            miRAFile = new RandomAccessFile(rutaArchivo, "rw");
            // Nos vamos al final del fichero
            miRAFile.seek(miRAFile.length());
            // Incorporamos la cadena al fichero     
            miRAFile.writeBytes(texto);
            // Cerramos el fichero
            miRAFile.close();
        } catch (Exception ex) {
            errorDialog("agregarTextoAlFinalArchivo()", ex.getLocalizedMessage());
        }
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="actualizaTextoArchivo(String rutaArchivo, String search, String reemplazo)">
    /*
     * Actualiza el archivo una cadena con la que le especifiquemos ejemplo
     * ActualizarTextoArchivo("/home/avbravo/Documentos/etiquetas.properties",
     * "nombre", "name"); Actualiza en el archivo nombre por name
     */
    public static Boolean actualizaTextoArchivo(String rutaArchivo, String search, String reemplazo) {
        try {

            File file = new File(rutaArchivo);
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = "", oldtext = "";

            while ((line = reader.readLine()) != null) {

                oldtext += line + "\r\n";

            }
            reader.close();

            if (oldtext.indexOf(search) != -1) {
                String newtext = oldtext.replaceAll(search, reemplazo);

                FileWriter writer = new FileWriter(rutaArchivo);
                writer.write(newtext);
                writer.close();

                return true;
            }

        } catch (Exception ex) {
            errorDialog("actualizaTextoArchivo()", ex.getLocalizedMessage());
        }
        return false;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Boolean encontrarTextoArchivo(String rutaArchivo, String search) ">
    public static Boolean encontrarTextoArchivo(String rutaArchivo, String search) {
        try {

            File file = new File(rutaArchivo);
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            boolean encontrado = false;
            while ((line = reader.readLine()) != null) {
                if (line.indexOf(search) != -1) {
                    encontrado = true;
                }
            }
            return encontrado;
        } catch (Exception ex) {
            errorDialog("encontrarTextoArchivo()", ex.getLocalizedMessage());
        }
        return false;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Boolean insertarTextoArchivo(String rutaArchivo, String search, String textoInsertar, boolean antes)">

    /*
     * Inserta texto en el archivo antes o despues de la linea donde se
     * encuentre la cadena search el parametro antes = true : indica que se
     * insertara antes antes = false : indica que se insertara despues
     * InsertarTextoArchivo("/home/avbravo/Documentos/etiquetas.properties",
     * "name", "email=\"@ww\"", false)
     */
    public static Boolean insertarTextoArchivo(String rutaArchivo, String search, String textoInsertar, Boolean antes) {
        try {

            File file = new File(rutaArchivo);
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = "", oldtext = "";
            boolean encontrado = false;
            while ((line = reader.readLine()) != null) {
                if (line.indexOf(search) != -1) {
                    if (antes) {
                        //insertarlo antes
                        oldtext += textoInsertar + "\r\n" + line + "\r\n";
                    } else {
                        //insertar despues
                        oldtext += line + "\r\n" + textoInsertar + "\r\n";
                    }

                    encontrado = true;

                } else {
                    oldtext += line + "\r\n";
                }

            }
            reader.close();

            if (encontrado) {
                FileWriter writer = new FileWriter(rutaArchivo);
                writer.write(oldtext);
                writer.close();

                return true;
            }

        } catch (Exception ex) {
            // System.out.println("insertarTextoArchivo()" + ex.getLocalizedMessage());
            // errorDialog("insertarTextoArchivo()", ex.getLocalizedMessage());
        }
        return false;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="String otp(int otplentgh)">
    /**
     * Generador de otp estilo de codigos que usan en banca en linea es un
     * codigo aleatorio numerico
     *
     * @param Optlentgh largo de digital que devolvera el opt
     * @return otp Ejemplo generateOtp(4) Puede generar: 7596 (4 digitos
     * aleatorios)
     */
    public static String otp(int otplentgh) {
        SplittableRandom sr = new SplittableRandom();
        StringBuilder sb = new StringBuilder();
        try {

            for (int i = 0; i < otplentgh; i++) {
                sb.append(sr.nextInt(0, 10));
            }
        } catch (Exception e) {
        }
        return sb.toString();
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc=" metodo()">
    /**
     * Agrega contenido al archivo en la ruta local
     *
     * @param fileName nombre del archivo sin el path
     * @param text texto a a agregar
     * @return
     */
    public static String fileAddTextToLocal(String fileName, String text) {
        try {
//            String fileName="filename.txt";

            File myObj = new File(fileName);
            if (myObj.createNewFile()) {
                // System.out.println("File created: " + myObj.getName());
            } else {
                // System.out.println("File already exists.");
            }

            // String str = "World"+DateUtil.fechaActual();
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true));
            writer.append("\n");
            writer.append(text);

            writer.close();
            //   Util.infoDialog("save", "Exitoso");
        } catch (Exception e) {
            //   errorServices.errorMessage(Util.nameOfClass(), Util.nameOfMethod(), e.getLocalizedMessage(), e);
            //   Util.errorDialog("Error)(", e.getLocalizedMessage());

        }
        return "";
    }
// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="PasswordInfo passwordInformacion(String password)">
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Boolean validatePasswordContainsUpperLowerNumber( String password) ">
    /**
     * Valida un password que contenga minimo 8 caracteres, letra mayusculas y
     * minusculas
     *
     * @param password
     * @return
     */
    public static Boolean validatePasswordContainsUpperLowerNumber(String password) {
        patternPassword = Pattern.compile(PASSWORD_PATTERN);

        matcherPassword = patternPassword.matcher(password);
        return matcherPassword.matches();

    }
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Boolean validatePasswordContainsUpperLowerNumber( String password, String pattern)">

    /**
     * Valida el password en base al patron
     *
     * @param password
     * @param pattern
     * @return
     */
    public static Boolean validatePasswordContainsUpperLowerNumber(String password, String pattern) {
        patternPassword = Pattern.compile(pattern);

        matcherPassword = patternPassword.matcher(password);
        return matcherPassword.matches();

    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="pathOfMicroprofileConfig()">
    /**
     * se usa con Microprofile Config para ejempp
     * #-------------------------------------------------------------- #--Path
     * Images #-- si pathBaseLinuxAddUserHome (solo para Linux
     * JsfUtil.isLinux()) #-- si es true se agrega JsfUtil.userHome() al path
     * (/asistencia/imagenes => /home/miuser/asistencia/imagenes) #-- si es
     * false = se usa el path completo de pathLinux (/opt/asistencia/imagenes)
     * pathBaseLinuxAddUserHome=true pathLinux=/asistencia/imagenes/
     * pathWindows=C:\\asistencia\\imagenes\\
     *
     * @param addUserHome
     * @param pahtLinux
     * @param pathWindows
     * @return
     */
    public static String pathOfMicroprofileConfig(Boolean addUserHome, String pahtLinux, String pathWindows) {
        return isLinux() ? (addUserHome ? userHome() + pahtLinux : pahtLinux) : pathWindows;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Boolean divideDestinatary(List<User> list)()">
    /**
     * Divide de una lista los emails
     *
     * @param list
     * @return
     */
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="void printEmailRecipients(EmailRecipients emailRecipients) ">
    /**
     * iprime el contenido de emails recipients
     *
     * @param emailRecipients
     */
// </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="long secondsToNanoseconds(Double seconds)">
    /**
     * Convierte segundos (Double ) a nanosegundos
     *
     * @param seconds
     * @return
     */
    public static long secondsToNanoseconds(Double seconds) {
        long result = 0;
        try {

            String doubleAsString = String.valueOf(seconds);
            int indexOfDecimal = doubleAsString.indexOf(".");
            Double in = Double.parseDouble(doubleAsString.substring(0, indexOfDecimal));

            Integer len = doubleAsString.substring(indexOfDecimal).length();
            Double dec = Double.parseDouble(doubleAsString.substring(indexOfDecimal));
            long r2 = 0;
            switch (len) {
                case 1:
                    dec = dec * 100;
                    r2 = dec.longValue() * 1_00_000_000L;
                    break;
                case 2:
                    dec = dec * 10;
                    r2 = dec.longValue() * 1_00_000_000L;
                    break;
                case 3:
                    dec = dec * 100;
                    r2 = dec.longValue() * 1_0_000_000L;
                    break;
            }

            long r1 = in.longValue() * 1_000_000_000L;

            result = r1 + r2;

        } catch (Exception e) {
            // System.out.println("error " + e.getLocalizedMessage());
        }
        return result;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Ram ramMemory()">
    /**
     * Muestra la memoria RAM libre
     *
     * @return
     */
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="long bytesToMegabytes(long bytes)">
    public static long bytesToMegabytes(long bytes) {
        return bytes / MEGABYTE;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Long integerToLong(Integer i)">
    public static Long integerToLong(Integer i) {
        Long l = Long.valueOf(i.longValue());
        return l;
    } // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="BigInteger toBigInteger(Integer number)>  
    public static BigInteger toBigInteger(Integer number) {
        return BigInteger.valueOf(number);
    }
// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc=" int longToInteger(Long n)">    
    public static int longToInteger(Long n) {
        return n.intValue();
    }
// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="BigInteger contextToBigInteger(String key)">
    /**
     * Busca en el context el key y el value lo devuelve como BigInteger
     *
     * @param key
     * @return
     */
    public static BigInteger contextToBigInteger(String key) {
        BigInteger i = new BigInteger("0");
        try {

            if (JmoordbContext.get(key) == null) {
                warningMessage("Procesando " + key + " key es null");
            } else {
                String val = String.valueOf(JmoordbContext.get(key));
                i = new BigInteger(val);
            }

        } catch (Exception e) {
            errorMessage(nameOfMethod() + " " + e.getLocalizedMessage());
        }
        return i;
    }

// </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="BigInteger contextToInteger(String key)">
    /**
     * Busca en el context el key y el value lo devuelve como BigInteger
     *
     * @param key
     * @return
     */
    public static Integer contextToInteger(String key) {
        Integer i = 0;
        try {

            if (JmoordbContext.get(key) == null) {
                warningMessage("Procesando " + key + " key es null");
            } else {
                String val = String.valueOf(JmoordbContext.get(key));
                i = Integer.parseInt(val);
            }

        } catch (Exception e) {
            errorMessage(nameOfMethod() + " " + e.getLocalizedMessage());
        }
        return i;
    }

// </editor-fold>
    // <editor-fold defaultstate="collapsed" desc=" propertiesBigIntegerToContext(Properties properties, String key)">
    /**
     * Lee una propiedad y la asigna al context
     *
     * @param properties
     * @param key
     */
    public static void propertiesBigIntegerToContext(Properties properties, String key) {
        try {
            if (properties.getProperty(key) == null) {
                JsfUtil.warningMessage("No existe la propiedad" + key);
                JmoordbContext.put(key, 0);
            } else {
                Integer value = Integer.parseInt(properties.getProperty(key));
                JmoordbContext.put(key, value);
            }
        } catch (Exception e) {
            System.out.println(JsfUtil.nameOfMethod() + " "+e.getLocalizedMessage());
        }
    }
// </editor-fold>
    // <editor-fold defaultstate="collapsed" desc=" propertiesStringToContext(Properties properties, String key)">

    /**
     * Lee una propiedad y la asigna al context
     *
     * @param properties
     * @param key
     */
    public static void propertiesStringToContext(Properties properties, String key) {
        try {
            if (properties.getProperty(key) == null) {
                JsfUtil.warningMessage("no existe la propiedad" + key);
                JmoordbContext.put(key, 0);
            } else {
                String value = String.valueOf(properties.getProperty(key));
                JmoordbContext.put(key, value);
            }
        } catch (Exception e) {
        }
    }
// </editor-fold>
    // <editor-fold defaultstate="collapsed" desc=" propertiesBigIntegerToContext(Properties properties, String key)">

    /**
     * Lee una propiedad y la asigna al context
     *
     * @param properties
     * @param key
     */
    public static void propertiesIntegerToContext(Properties properties, String key) {
        try {
            if (properties.getProperty(key) == null) {
                JsfUtil.warningMessage("no existe la propiedad" + key);
                JmoordbContext.put(key, 0);
            } else {
                Integer value = Integer.parseInt(properties.getProperty(key));
                JmoordbContext.put(key, value);
            }
        } catch (Exception e) {
        }
    }
// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Boolean divideDestinatary(List<User> list)()">
    /**
     * Divide de una lista los emails
     *
     * @param list
     * @return
     */
    public static EmailRecipients divideDestinatary(List<String> list) {
        EmailRecipients emailRecipients = new EmailRecipients();
        try {
            ////Divide para las copias y bcc,cc
            emailRecipients.divide(list);

        } catch (Exception e) {
            // System.out.println("divideDestinatary() " + e.getLocalizedMessage());
        }
        return emailRecipients;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="desencritpar">  
    /**
     * Encripta un texto en base a una llave
     *
     * @param textoencriptado
     * @param texto: myclavepersonal
     * @param key : mykey
     * @return
     */
    public static String desencriptar(String textoencriptado) {
        try {
            CryptoConverter cryptoConverter = new CryptoConverter();

            return cryptoConverter.convertToEntityAttribute(textoencriptado);
        } catch (Exception e) {
            errorMessage("desencriptar() " + e.getLocalizedMessage());
        }
        return "";
    }// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="encriptar">  
    /**
     * Encripta un texto en base a una llave
     *
     * @param texto: myclavepersonal
     * @param key : mykey
     * @return
     */
    public static String encriptar(String texto) {

        try {
            CryptoConverter cryptoConverter = new CryptoConverter();

            return cryptoConverter.convertToDatabaseColumn(texto);
        } catch (Exception e) {
            errorMessage("encriptar() " + e.getLocalizedMessage());
        }
        return "";
    }// </editor-fold>

// <editor-fold defaultstate="collapsed" desc=UIComponent findComponentById(String componentId) ">
    public static UIComponent findComponentById(String componentId) {
        FacesContext context = FacesContext.getCurrentInstance();
        UIViewRoot root = context.getViewRoot();
        return root.findComponent(componentId);
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Boolean tieneEspaciosBlanco(String texto)">
    public static Boolean tieneEspaciosBlanco(String texto) {
        try {

            texto = texto.trim();

            Pattern pattern = Pattern.compile("\\s");
            Matcher matcher = pattern.matcher(texto);
            boolean found = matcher.find();
            return found;
        } catch (Exception e) {
            errorMessage("tieneEspaciosBlanco() " + e.getLocalizedMessage());
        }
        return Boolean.FALSE;
    }
// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Object copyBeans(Object destino, Object fuente)">
    /**
     * Copia el contenido de un bean en otro
     *
     * @param destino
     * @param fuente
     * @return
     */
    public static Object copyBeans(Object destino, Object fuente) {
        try {
            BeanUtils.copyProperties(destino, fuente);
        } catch (Exception e) {
        }

        return destino;
    }// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Integer numberOfPages(Integer rows,Integer rowForPage)">
    public static Integer numberOfPages(Integer rows, Integer rowForPage) {
        Integer numberOfPage = 1;
        try {

            if (rows > 0) {
                numberOfPage = rows / rowForPage;
                if ((rows % rowForPage) > 0) {
                    numberOfPage++;
                }
            }
        } catch (Exception e) {
            System.out.println(
                    "------------------------------------------------------------------------------------------------");
            System.out.println(
                    "Class:" + JsfUtil.nameOfClass() + " Metodo:" + JsfUtil.nameOfMethod());
            System.out.println("Error " + e.getLocalizedMessage());
            System.out.println(
                    "------------------------------------------------------------------------------------------------");
            JsfUtil.errorMessage(nameOfMethod() + " " + e.getLocalizedMessage());
        }
        return numberOfPage;
    }
    // </editor-fold>

//    public static String goUrlValidate(String path) {
//        String newPage = path;
//        try {
////            if (JmoordbContext.get("prefijo") == null || String.valueOf(JmoordbContext.get("prefijo")).equals("")) {
//// No encontro el prefijo
//                if (path.contains("/faces/")) {
//
//                    newPage = path.replace("/faces/", "");
//
//                } else {
//                    newPage = path;
//                }
//
//        } catch (Exception e) {
//        }
//        return newPage;
//    }
}
