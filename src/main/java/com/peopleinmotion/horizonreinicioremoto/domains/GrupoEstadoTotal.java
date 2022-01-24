/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.peopleinmotion.horizonreinicioremoto.domains;

import com.peopleinmotion.horizonreinicioremoto.entity.GrupoEstado;

/**
 *
 * @author avbravo
 */
public class GrupoEstadoTotal {
    private GrupoEstado grupoEstado;
    private Integer total;

    public GrupoEstadoTotal() {
    }

    
    
    
    public GrupoEstadoTotal(GrupoEstado grupoEstado, Integer total) {
        this.grupoEstado = grupoEstado;
        this.total = total;
    }

    
    
    
    public GrupoEstado getGrupoEstado() {
        return grupoEstado;
    }

    public void setGrupoEstado(GrupoEstado grupoEstado) {
        this.grupoEstado = grupoEstado;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }
    
    
    
}
