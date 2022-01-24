/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.peopleinmotion.horizonreinicioremoto.repository;

import com.peopleinmotion.horizonreinicioremoto.entity.AccionReciente;
import com.peopleinmotion.horizonreinicioremoto.entity.EmailConfiguration;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author avbravo
 */
public interface EmailConfigurationRepository {
    public List<EmailConfiguration> findAll(); 
    public Optional<EmailConfiguration> find(BigInteger id);
    public Optional<EmailConfiguration> findByEmailConfigurationId(BigInteger EMAILCONFIGURATIONID);
    public Optional<EmailConfiguration> findByActivo(String ACTIVO);
    public Boolean create(EmailConfiguration emailConfiguration);
    
}
