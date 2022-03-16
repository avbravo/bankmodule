/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.peopleinmotion.horizonreinicioremoto.services;

import com.peopleinmotion.horizonreinicioremoto.domains.TokenReader;
import com.peopleinmotion.horizonreinicioremoto.entity.Token;
import com.peopleinmotion.horizonreinicioremoto.entity.Usuario;
import com.peopleinmotion.horizonreinicioremoto.jmoordb.JmoordbContext;
import com.peopleinmotion.horizonreinicioremoto.repository.TokenRepository;
import com.peopleinmotion.horizonreinicioremoto.utils.DateUtil;
import com.peopleinmotion.horizonreinicioremoto.utils.JsfUtil;
import java.util.List;
import java.util.function.Supplier;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author avbravo
 */
@Stateless
public class TokenServicesImpl implements TokenServices {

    // <editor-fold defaultstate="collapsed" desc="@Inject ">
    @Inject
    TokenRepository tokenRepository;
// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="String marcarToken(String numero, TokenReader tokenReader) ">
    /**
     * Se usa para controlar los botones del dialogo de confirmación del token
     *
     * @param numero
     * @param tokenIngresado
     * @return
     */
    @Override
    public TokenReader marcarToken(String numero, TokenReader tokenReader) {
        try {

            if (numero.toLowerCase().equals("b")) {
                /**
                 * Borrar
                 */
                if (!tokenReader.getNumber4().equals("")) {
                    tokenReader.setNumber4("");
                } else {
                    if (!tokenReader.getNumber3().equals("")) {
                        tokenReader.setNumber3("");
                    } else {
                        if (!tokenReader.getNumber2().equals("")) {
                            tokenReader.setNumber2("");
                        } else {
                            if (!tokenReader.getNumber1().equals("")) {
                                tokenReader.setNumber1("");
                            }
                        }
                    }
                }
            } else {
                if (tokenReader.getNumber1().equals("")) {
                    tokenReader.setNumber1(numero);
                } else {
                    if (tokenReader.getNumber2().equals("")) {
                        tokenReader.setNumber2(numero);
                    } else {
                        if (tokenReader.getNumber3().equals("")) {
                            tokenReader.setNumber3(numero);
                        } else {
                            if (tokenReader.getNumber4().equals("")) {
                                tokenReader.setNumber4(numero);
                            }
                        }
                    }
                }
            }

        } catch (Exception e) {
            JsfUtil.errorMessage(JsfUtil.nameOfMethod() + " " + e.getLocalizedMessage());
        }
        return tokenReader;
    }

// </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="String marcarToken(String numero, String tokenIngresado) ">
    /**
     * Se usa para controlar los botones del dialogo de confirmación del token
     *
     * @param numero
     * @param tokenIngresado
     * @return
     */
    @Override
    public String marcarToken(String numero, String tokenIngresado) {
        try {
            String pos0 = tokenIngresado.substring(0, 1).trim();
            String pos1 = tokenIngresado.substring(1, 2).trim();
            String pos2 = tokenIngresado.substring(2, 3).trim();
            String pos3 = tokenIngresado.substring(3, 4).trim();
            if (numero.toLowerCase().equals("b")) {
                /**
                 * Borrar
                 */
                if (!pos3.equals("*")) {
                    pos3 = "*";
                } else {
                    if (!pos2.equals("*")) {
                        pos2 = "*";
                    } else {
                        if (!pos1.equals("*")) {
                            pos1 = "*";
                        } else {
                            if (!pos0.equals("*")) {
                                pos0 = "*";
                            }
                        }
                    }
                }
            } else {
                if (pos0.equals("*")) {
                    pos0 = numero;
                } else {
                    if (pos1.equals("*")) {
                        pos1 = numero;
                    } else {
                        if (pos2.equals("*")) {
                            pos2 = numero;
                        } else {
                            if (pos3.equals("*")) {
                                pos3 = numero;
                            }
                        }
                    }
                }
            }
            tokenIngresado = pos0 + pos1 + pos2 + pos3;

        } catch (Exception e) {
            JsfUtil.errorMessage(JsfUtil.nameOfMethod() + " " + e.getLocalizedMessage());
        }
        return tokenIngresado;
    }

// </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Boolean validateToken(Usuario usuario, String tokenIngresado) ">
    @Override
    public Boolean validateToken(Usuario usuario, String tokenIngresado) {
        try {
            List<Token> tokenList = tokenRepository.findByUsuarioIdTokenAndActivo(usuario.getUSUARIOID(), tokenIngresado, "SI");
            if (tokenList == null || tokenList.isEmpty()) {
                JsfUtil.warningMessage("El token no es válido .Verifique los datos...");
            } else {
                Token token = tokenList.get(0);
                if (token.getUSADO().equals("SI")) {
                    JsfUtil.warningMessage("Este token ya fue usado");
                } else {
                    if (token.getVENCIDO().equals("SI")) {
                        JsfUtil.warningMessage("Token ya venció");
                    } else {

                        if (DateUtil.fechaMayor(DateUtil.getFechaHoraActual(), token.getFECHAVENCIMIENTO())) {
                            JsfUtil.warningMessage("El plazo para usarlo ya se agotó. Genere otro token. Se colocará como vencido.");
                            token.setACTIVO("NO");
                            token.setUSADO("XX");
                            token.setVENCIDO("SI");
                            if (tokenRepository.update(token)) {
                                //   
                            } else {
                                // JsfUtil.warningMessage("No se pudo actualizar el token");
                            }
                        } else {

                            token.setACTIVO("NO");
                            token.setVENCIDO("XX");
                            token.setUSADO("SI");
                            if (tokenRepository.update(token)) {

                                return Boolean.TRUE;
                            } else {
                                JsfUtil.warningMessage("No se pudo actualizar el token");
                            }
                        }
                    }
                }
            }

        } catch (Exception e) {
            JsfUtil.errorMessage(JsfUtil.nameOfMethod() + " " + e.getLocalizedMessage());
        }
        return Boolean.FALSE;
    }
    // </editor-fold>

    @Override
    public Token supplier() {
        Token token = new Token();
        try {
            if (JmoordbContext.get("user") == null) {

            } else {

                Usuario user = (Usuario) JmoordbContext.get("user");
                token.setUSUARIOID(user.getUSUARIOID());
            }
            token.setACTIVO("SI");
            token.setCODIGOTRANSACCION(JsfUtil.getUUID());
            token.setFECHAGENERACION(DateUtil.fechaHoraActual());
            token.setFECHAVENCIMIENTO(DateUtil.sumarMinutosAFecha(token.getFECHAGENERACION(), 3));
            token.setTOKEN(JsfUtil.otp(4));
            token.setUSADO("NO");

            token.setVENCIDO("NO");
        } catch (Exception e) {
            JsfUtil.errorMessage(JsfUtil.nameOfMethod() + " " + e.getLocalizedMessage());
        }
        return token;
    }
}
