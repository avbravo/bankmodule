/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.peopleinmotion.horizonreinicioremoto.services;

import com.peopleinmotion.horizonreinicioremoto.entity.Accion;
import com.peopleinmotion.horizonreinicioremoto.entity.AccionReciente;
import com.peopleinmotion.horizonreinicioremoto.entity.Banco;
import com.peopleinmotion.horizonreinicioremoto.entity.Cajero;
import com.peopleinmotion.horizonreinicioremoto.entity.Token;
import com.peopleinmotion.horizonreinicioremoto.entity.Usuario;
import java.util.List;
import java.util.concurrent.Future;

/**
 *
 * @author avbravo
 */
public interface EmailServices {

    public String generateMessages(AccionReciente accionReciente, String header, Usuario usuario, Cajero cajero, Banco banco);   
    public String generateTokenMessages(Token token, String header, Usuario usuario);   
    public String send(String message, List<String> email);
    public List<String> generateEmailList(List<Usuario> usuarioList);    
    public Future<String> sendEmailCccBccAsync(String[] to, String[] cc, String[] bcc, String titulo, String mensaje, String emailemisor, String passwordemisor) throws InterruptedException;
    public Future<String> sendEmailAsync(String emailreceptor, String titulo, String mensaje, String emailemisor, String passwordemisor) throws InterruptedException ;
    public Boolean sendEmailToTecnicos(AccionReciente accionReciente, Accion accion, Usuario usuario, Cajero cajero, Banco banco);
    Boolean sendEmailToTecnicosHeader(AccionReciente accionReciente, String header, Usuario usuario, Cajero cajero, Banco banco);
    public Boolean sendTokenToEmail(Token token, Usuario usuario);
    public Boolean sendTokenToEmailSincrono(Token token, Usuario usuario);
    
}
