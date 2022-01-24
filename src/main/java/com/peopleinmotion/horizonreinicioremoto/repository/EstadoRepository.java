/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.peopleinmotion.horizonreinicioremoto.repository;

import com.peopleinmotion.horizonreinicioremoto.entity.Estado;
import com.peopleinmotion.horizonreinicioremoto.entity.GrupoEstado;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author avbravo
 */
public interface EstadoRepository {

    public List<Estado> findAll();

    public Optional<Estado> find(BigInteger id);
    public Optional<Estado> findByEstadoId(BigInteger ESTADOID);

    public List<Estado> findByGrupoEstadoId(GrupoEstado GRUPOESTADOID);

    public Optional<Estado> findByPredeterminado(String PREDETERMINADO);
    public Optional<Estado> findByPredeterminadoAndActivo(String PREDETERMINADO, String ACTIVO);

    public Boolean create(Estado estado);
    
    public List<Estado> findByActivo(String ACTIVO);

}
