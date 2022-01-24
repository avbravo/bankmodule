/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.peopleinmotion.horizonreinicioremoto.repository;

import com.peopleinmotion.horizonreinicioremoto.entity.Banco;
import com.peopleinmotion.horizonreinicioremoto.entity.Cajero;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author avbravo
 */
public interface CajeroRepository {

    public List<Cajero> findAll();

    public Optional<Cajero> find(BigInteger id);
    public Optional<Cajero> findByCajeroId(BigInteger CAJEROID);

    public List<Cajero> findByCajeroIdAndActivo(BigInteger CAJEROID, String ACTIVO);

    public List<Cajero> findByBancoId(Banco BANCOID);
    public List<Cajero> findByBancoIdAndActivo(Banco BANCOID, String ACTIVO);

    public Boolean create(Cajero cajero);
    public List<Cajero> findByCajeroIdBancoIdAndActivo(Banco BANCOID, String CAJERO, String ACTIVO);
    public List<Cajero> findByDireccionCortaBancoIdAndActivo(Banco BANCOID, String DIRECCIONCORTA, String ACTIVO);

}
