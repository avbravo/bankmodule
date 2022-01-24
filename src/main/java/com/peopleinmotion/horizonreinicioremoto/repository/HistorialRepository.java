/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.peopleinmotion.horizonreinicioremoto.repository;

import com.peopleinmotion.horizonreinicioremoto.entity.AccionReciente;
import com.peopleinmotion.horizonreinicioremoto.entity.Historial;
import com.peopleinmotion.horizonreinicioremoto.paginator.QuerySQL;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author avbravo
 */
public interface HistorialRepository {

    public List<Historial> findAll();

    public Optional<Historial> find(BigInteger id);

    public Boolean create(Historial historial);

    public Boolean update(Historial historial);

    public Boolean delete(Historial historial);

    /*
    Query
     */
//    public int queryCount(Query query);
//    public List<Historial> queryPagination(Query query,Integer pageNumber,Integer rowForPage) ;
//    public List<Historial> queryWithOutPagination(Query query); 
    public Boolean changed(Historial historial);


    

    public List<Historial> sql(QuerySQL querySQL);

    public List<Historial> pagination(QuerySQL querySQL, Integer pageNumber, Integer rowForPage);

    public int count(QuerySQL querySQL);

}
