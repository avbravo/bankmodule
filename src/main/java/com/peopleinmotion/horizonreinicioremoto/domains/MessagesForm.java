/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.peopleinmotion.horizonreinicioremoto.domains;

import com.peopleinmotion.horizonreinicioremoto.events.EmailSender;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;

/**
 *
 * @author avbravo
 */
@Data
public class MessagesForm implements Serializable{

    private String header;
    private String header2;
    private String titulo;
    private String mensaje;
    private String image;
    private String library;
    private String returnTo;
    private String id;
    private String data;
    private Boolean errorWindows;

    public MessagesForm() {
    }

    public MessagesForm(String header, String header2, String titulo, String mensaje, String image, String library, String returnTo, String id, String data, Boolean errorWindows) {
        this.header = header;
        this.header2 = header2;
        this.titulo = titulo;
        this.mensaje = mensaje;
        this.image = image;
        this.library = library;
        this.returnTo = returnTo;
        this.id = id;
        this.data = data;
        this.errorWindows = errorWindows;
    }




   
  

    
    
    
    public static class Builder {

        private String header;
        private String header2;
            private String image;
    private String library;

        private String titulo;
        private String mensaje;
        private String returnTo;
        private String id;
        private String data;
         private Boolean errorWindows;

        public Builder header(String header) {
            this.header = header;
            return this;
        }
        public Builder header2(String header2) {
            this.header2 = header2;
            return this;
        }
        public Builder image(String image) {
            this.image = image;
            return this;
        }
        public Builder libary(String library) {
            this.library = library;
            return this;
        }
        public Builder titulo(String titulo) {
            this.titulo = titulo;
            return this;
        }
        public Builder mensaje(String mensaje) {
            this.mensaje = mensaje;
            return this;
        }
        public Builder returnTo(String returnTo) {
            this.returnTo = returnTo;
            return this;
        }
        public Builder data(String data) {
            this.data = data;
            return this;
        }
        public Builder id(String id) {
            this.id = id;
            return this;
        }
        public Builder errorWindows(Boolean errorWindows) {
            this.errorWindows = errorWindows;
            return this;
        }
        
        

        public MessagesForm build() {
            return new MessagesForm(header, header2, titulo, mensaje, image, library, returnTo, id, data, errorWindows);
        }

    }

}
