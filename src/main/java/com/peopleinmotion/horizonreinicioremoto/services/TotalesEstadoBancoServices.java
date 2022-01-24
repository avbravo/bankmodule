/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.peopleinmotion.horizonreinicioremoto.services;

import com.peopleinmotion.horizonreinicioremoto.domains.TotalesEstadoBanco;
import java.util.List;

/**
 *
 * @author avbravo
 */
public interface TotalesEstadoBancoServices {
    public List<TotalesEstadoBanco> calcularTotales();
    public TotalesEstadoBanco calcularTotalesDelBanco();
    
}
