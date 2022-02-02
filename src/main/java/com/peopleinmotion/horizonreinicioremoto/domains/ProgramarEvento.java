/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.peopleinmotion.horizonreinicioremoto.domains;

import com.peopleinmotion.horizonreinicioremoto.entity.Accion;
import com.peopleinmotion.horizonreinicioremoto.entity.Cajero;
import java.util.Date;
import lombok.Builder;
import lombok.Data;

/**
 *
 * @author avbravo
 */
@Data
@Builder
public class ProgramarEvento {
 private Cajero cajero;
 private Accion accion;
 private Date   fechahora;

    public ProgramarEvento() {
    }

    public ProgramarEvento(Cajero cajero, Accion accion, Date fechahora) {
        this.cajero = cajero;
        this.accion = accion;
        this.fechahora = fechahora;
    }
 
 
 
}
