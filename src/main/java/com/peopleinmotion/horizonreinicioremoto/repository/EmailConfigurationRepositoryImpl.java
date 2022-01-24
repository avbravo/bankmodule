/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.peopleinmotion.horizonreinicioremoto.repository;

import com.peopleinmotion.horizonreinicioremoto.entity.EmailConfiguration;
import com.peopleinmotion.horizonreinicioremoto.facade.EmailConfigurationFacade;
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
public class EmailConfigurationRepositoryImpl implements EmailConfigurationRepository {

    @Inject
    EmailConfigurationFacade emailConfigurationFacade;

    @Override
    public List<EmailConfiguration> findAll() {
        return emailConfigurationFacade.findAll();
    }

 

   

    @Override
    public Boolean create(EmailConfiguration emailConfiguration) {
        try {
           emailConfigurationFacade.create(emailConfiguration);
            return true;
        } catch (Exception e) {
            // System.out.println("create() " + e.getLocalizedMessage());
        }
        return false;
    }

  

    @Override
    public Optional<EmailConfiguration> findByEmailConfigurationId(BigInteger EMAILCONFIGURATIONID) {
            return emailConfigurationFacade.findByEmailConfigurationId(EMAILCONFIGURATIONID);
    }

    @Override
    public Optional<EmailConfiguration> findByActivo(String ACTIVO) {
     return emailConfigurationFacade.findByActivo(ACTIVO);
    }

    @Override
    public Optional<EmailConfiguration> find(BigInteger id) {
      return emailConfigurationFacade.find(id);
    }

    

}
