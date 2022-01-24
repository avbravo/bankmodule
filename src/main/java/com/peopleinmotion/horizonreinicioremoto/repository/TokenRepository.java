/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.peopleinmotion.horizonreinicioremoto.repository;

import com.peopleinmotion.horizonreinicioremoto.entity.Token;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author avbravo
 */
public interface TokenRepository {

    public Boolean create(Token token);
    public Boolean update(Token token);

    public List<Token> findAll();

    public Optional<Token> find(BigInteger id);
    public Optional<Token> findByTokenId(BigInteger TOKENID);

    public Optional<Token> findByCodigoTransaccion(String CODIGOTRANSACCION);
    public List<Token> findByUsuarioIdAndActivo(BigInteger USUARIOID, String ACTIVO);
 public   List<Token> findByUsuarioIdTokenAndActivo(BigInteger USUARIOID, String TOKEN, String ACTIVO);
  public List<Token> findByActivo(String ACTIVO);

}
