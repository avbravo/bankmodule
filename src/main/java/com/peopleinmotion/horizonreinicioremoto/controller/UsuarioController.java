/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.peopleinmotion.horizonreinicioremoto.controller;

import com.peopleinmotion.horizonreinicioremoto.entity.Usuario;
import com.peopleinmotion.horizonreinicioremoto.facade.UsuarioFacade;
import com.peopleinmotion.horizonreinicioremoto.interfaces.Page;
import com.peopleinmotion.horizonreinicioremoto.jmoordb.JmoordbContext;
import com.peopleinmotion.horizonreinicioremoto.utils.ConsoleUtil;
import com.peopleinmotion.horizonreinicioremoto.utils.DateUtil;
import com.peopleinmotion.horizonreinicioremoto.utils.JsfUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
//import javax.faces.bean.SessionScoped;

import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author avbravo
 */
//@ManagedBean(value = "usuarioController")
//@ViewScoped
//@SessionScoped
@Named
@SessionScoped
public class UsuarioController implements Serializable, Page{
    private static final long serialVersionUID = 1L;

    // <editor-fold defaultstate="collapsed" desc="field ">
    private Usuario usuario = new Usuario();
    private List<Usuario> usuarioList = new ArrayList<>();
// </editor-fold>
    
    @Inject
    UsuarioFacade usuarioFacade;
    
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Usuario> getUsuarioList() {
        return usuarioList;
    }

    // <editor-fold defaultstate="collapsed" desc="set/get ">
    public void setUsuarioList(List<Usuario> usuarioList) {    
        this.usuarioList = usuarioList;
    }

// </editor-fold>
    /**
     * Creates a new instance of UsuarioController
     */
    public UsuarioController() {
    }
    
    @PostConstruct
    public void init(){
        try {

        
           ConsoleUtil.redBackground(JsfUtil.nameOfClass() + "." +JsfUtil.nameOfMethod() + " at "+DateUtil.fechaHoraActual());
            /**
             * Voy a agregar uno nuevo
             */
            // System.out.println("voy a guardador");
//            Usuario usuario = new Usuario("prueba", "Uusario prueba","12", "Tecnico");

       //     usuarioFacade.create(usuario);
            // System.out.println(" voy a llamar el find All");
            
            
            
            
   JsfUtil.successMessage("init "+ "llamando findAll()");
            usuarioList= usuarioFacade.findAll();
        } catch (Exception e) {
            JsfUtil.errorMessage(JsfUtil.nameOfMethod() + " " + e.getLocalizedMessage());
            // System.out.println("init.... "+e.getLocalizedMessage());
        }
        
    }
    
    public String save(){
        try{
        JsfUtil.successMessage("save()"+"Voy a guardar");
        usuarioFacade.create(usuario);
        } catch (Exception e) {
            JsfUtil.errorMessage(JsfUtil.nameOfMethod()+ " "+ e.getLocalizedMessage());
            // System.out.println("init.... "+e.getLocalizedMessage());
        }
        return "";
    }
    
    
}
