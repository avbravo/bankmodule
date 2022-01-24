/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.peopleinmotion.horizonreinicioremoto.domains;

import lombok.Builder;
import lombok.Data;

/**
 *
 * @author avbravo
 */
@Data
@Builder
public class TokenReader {
 private String number1;
 private String number2;
 private String number3;
 private String number4;

    public TokenReader() {
    }

    public TokenReader(String number1, String number2, String number3, String number4) {
        this.number1 = number1;
        this.number2 = number2;
        this.number3 = number3;
        this.number4 = number4;
    }
 
}
