/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.peopleinmotion.horizonreinicioremoto.services;

import com.peopleinmotion.horizonreinicioremoto.entity.Agenda;
import com.peopleinmotion.horizonreinicioremoto.entity.Usuario;

/**
 *
 * @author avbravo
 */
public interface AgendaHistorialServices {
public Boolean createHistorial(Agenda agenda, String EVENTOOCURRIDO, Usuario usuario);
}
