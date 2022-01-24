/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.peopleinmotion.horizonreinicioremoto.security;

import com.peopleinmotion.horizonreinicioremoto.utils.ConsoleUtil;
import java.io.Serializable;
import java.util.Date;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 *
 * @author avbravo
 */
public class SecuritySessionListener implements HttpSessionListener, Serializable {

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        
        
        ConsoleUtil.normal("********************************************************************");
        ConsoleUtil.normal("Session created : " + se.getSession().getId() + " at " + new Date());
        ConsoleUtil.normal("********************************************************************");
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        HttpSession session = se.getSession();
       ConsoleUtil.normal("********************************************************************");
        ConsoleUtil.normal("session destroyed :" + session.getId() + " Logging out user... at  " + new Date());
       ConsoleUtil.normal("********************************************************************");
    }
}
