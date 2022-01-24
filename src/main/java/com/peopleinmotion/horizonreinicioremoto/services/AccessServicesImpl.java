/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.peopleinmotion.horizonreinicioremoto.services;

import com.peopleinmotion.horizonreinicioremoto.entity.Banco;
import com.peopleinmotion.horizonreinicioremoto.entity.Usuario;
import com.peopleinmotion.horizonreinicioremoto.jmoordb.JmoordbContext;
import com.peopleinmotion.horizonreinicioremoto.repository.BancoRepository;
import com.peopleinmotion.horizonreinicioremoto.repository.UsuarioRepository;
import com.peopleinmotion.horizonreinicioremoto.utils.ConsoleUtil;
import com.peopleinmotion.horizonreinicioremoto.utils.JsfUtil;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author avbravo
 */
@Stateless
public class AccessServicesImpl implements AccessServices {
// <editor-fold defaultstate="collapsed" desc="inject() ">

    @Inject
    BancoRepository bancoRepository;
    @Inject
    UsuarioRepository usuarioRepository;
    @Inject
    AccessServices accessServices;

// </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="String loadConfigurationPropeties()">

    @Override
    public String loadConfigurationPropeties() {
        String version = "";
        try {
            InputStream inputStream = getClass()
                    .getClassLoader().getResourceAsStream("configuration.properties");
            Properties prop = new Properties();

            if (inputStream != null) {

                prop.load(inputStream);
                version = prop.getProperty("version");
                /**
                 * rowForPage
                 */
                JsfUtil.propertiesIntegerToContext(prop, "rowForPage");
                /**
                 * Asigna las properties al contexto
                 */
                JsfUtil.propertiesBigIntegerToContext(prop, "grupoEstadoSolicitadoId");
                JsfUtil.propertiesBigIntegerToContext(prop, "grupoEstadoEnprocesoId");
                JsfUtil.propertiesBigIntegerToContext(prop, "grupoEstadoFinalizadoId");
                JsfUtil.propertiesBigIntegerToContext(prop, "grupoEstadoNoSepuedejecutarId");
                /**
                 * GrupoAccion
                 */
                JsfUtil.propertiesBigIntegerToContext(prop, "grupoAccionBajarPlantillaId");
                JsfUtil.propertiesBigIntegerToContext(prop, "grupoAccionReinicioRemotoId");
                JsfUtil.propertiesBigIntegerToContext(prop, "grupoAccionEncenderSubirPlantillaId");

                /**
                 * Estado
                 *
                 */
                JsfUtil.propertiesBigIntegerToContext(prop, "estadoEnEsperaDeEjecucionId");
                JsfUtil.propertiesBigIntegerToContext(prop, "estadoProcesandoId");
                JsfUtil.propertiesBigIntegerToContext(prop, "estadoFinalizadoId");
                JsfUtil.propertiesStringToContext(prop, "prefijo");
                JsfUtil.propertiesStringToContext(prop, "applicativePath");
            } else {
                JsfUtil.errorMessage("No se puede cargar el archivo configuration.properties");
            }
        } catch (Exception e) {
            JsfUtil.errorMessage(JsfUtil.nameOfMethod() + " " + e.getLocalizedMessage());
             ConsoleUtil.error(JsfUtil.nameOfMethod() + " " + e.getLocalizedMessage());
        }
        return version;
    }
    // </editor-fold>
// <editor-fold defaultstate="collapsed" desc="Boolean validateCredentials(Usuario usuario, String username, String password, Banco banco) ">

    @Override
    public Boolean validateCredentials(Usuario usuario, String username, String password, Banco banco) {
        try {
            if (banco == null || banco.getBANCOID() == null) {
                JsfUtil.warningMessage("Seleccione un banco");
                return Boolean.FALSE;
            }

            List<Usuario> list = usuarioRepository.findByUsername(username);
            if (list == null || list.isEmpty()) {

                JsfUtil.warningMessage("Username no esta registrado");
                return Boolean.FALSE;
            }
            Usuario u = list.get(0);

            if (!JsfUtil.desencriptar(u.getPASSWORD()).equals(password)) {
                JsfUtil.successMessage("El password no valido");
                return Boolean.FALSE;
            }

            usuario = u;
            /**
             * Guardar el context
             */
            JmoordbContext.put("user", usuario);
            JmoordbContext.put("banco", banco);


            return Boolean.TRUE;
        } catch (Exception e) {
            JsfUtil.errorMessage(JsfUtil.nameOfMethod() + " " + e.getLocalizedMessage());
             ConsoleUtil.error(JsfUtil.nameOfMethod() + " " + e.getLocalizedMessage());
        }
        return Boolean.FALSE;
    }
    // </editor-fold>
// <editor-fold defaultstate="collapsed" desc="Boolean validateCredentials(Usuario usuario, String username, String password) ">
    @Override
    public Boolean validateCredentials(Usuario usuario, String username, String password) {
      try {
            

            List<Usuario> list = usuarioRepository.findByUsername(username);
            if (list == null || list.isEmpty()) {

                JsfUtil.warningMessage("Username no esta registrado");
                return Boolean.FALSE;
            }
            Usuario u = list.get(0);

            if (!JsfUtil.desencriptar(u.getPASSWORD()).equals(password)) {
                JsfUtil.successMessage("El password no valido");
                return Boolean.FALSE;
            }

            usuario = u;
            /**
             * Guardar el context
             */
            JmoordbContext.put("user", usuario);
            
            
            return Boolean.TRUE;
        } catch (Exception e) {
            JsfUtil.errorMessage(JsfUtil.nameOfMethod() + " " + e.getLocalizedMessage());
             ConsoleUtil.error(JsfUtil.nameOfMethod() + " " + e.getLocalizedMessage());
        }
        return Boolean.FALSE;
    }
    // </editor-fold>
}
