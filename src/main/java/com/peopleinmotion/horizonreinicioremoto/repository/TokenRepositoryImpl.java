/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.peopleinmotion.horizonreinicioremoto.repository;

import com.peopleinmotion.horizonreinicioremoto.entity.Token;
import com.peopleinmotion.horizonreinicioremoto.facade.TokenFacade;
import com.peopleinmotion.horizonreinicioremoto.utils.JsfUtil;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author avbravo
 */
@Stateless
public class TokenRepositoryImpl implements TokenRepository {

    @Inject
    TokenFacade tokenFacade;

    @Override
    public Boolean create(Token token) {
        try {
            tokenFacade.create(token);
         return Boolean.TRUE;
        } catch (Exception e) {
            JsfUtil.warningMessage("create() " + e.getLocalizedMessage());
            // System.out.println("create() " + e.getLocalizedMessage());
        }
       return Boolean.FALSE;
    }

    
      @Override
    public Boolean update(Token token) {
       try {
            tokenFacade.edit(token);
            return Boolean.TRUE;
        } catch (Exception e) {
            JsfUtil.warningMessage("update() " + e.getLocalizedMessage());
            // System.out.println("update() " + e.getLocalizedMessage());
        }
        return Boolean.FALSE;
    }
    
    @Override
    public List<Token> findAll() {
        return tokenFacade.findAll();
    }

    @Override
    public Optional<Token> findByTokenId(BigInteger TOKENID) {
        return tokenFacade.findByTokenId(TOKENID);
    }

    @Override
    public Optional<Token> findByCodigoTransaccion(String CODIGOTRANSACCION) {
        return tokenFacade.findByCodigoTransaccion(CODIGOTRANSACCION);
    }

    @Override
    public List<Token> findByUsuarioIdAndActivo(BigInteger USUARIOID, String ACTIVO) {
        return tokenFacade.findByUsuarioIdAndActivo(USUARIOID, ACTIVO);
    }

    @Override
    public List<Token> findByUsuarioIdTokenAndActivo(BigInteger USUARIOID, String TOKEN, String ACTIVO) {
        return tokenFacade.findByUsuarioIdTokenAndActivo(USUARIOID, TOKEN, ACTIVO);
    }

    @Override
    public List<Token> findByActivo(String ACTIVO) {
      return tokenFacade.findByActivo(ACTIVO);
    }

    @Override
    public Optional<Token> find(BigInteger id) {
        return tokenFacade.find(id);
    }

  

}
