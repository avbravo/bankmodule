/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.peopleinmotion.horizonreinicioremoto.events;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author avbravo
 */
public class EmailSender {

    private String header;
    private String messages;
    List<String> emailList = new ArrayList<>();

    public EmailSender() {
    }

    public EmailSender(String header, String messages, List<String> emailList) {
        this.header = header;
        this.messages = messages;
        this.emailList = emailList;
    }

    public String getMessages() {
        return messages;
    }

    public void setMessages(String messages) {
        this.messages = messages;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public List<String> getEmailList() {
        return emailList;
    }

    public void setEmailList(List<String> emailList) {
        this.emailList = emailList;
    }

    public static class Builder {

        private String header;
        private String messages;
        List<String> emailList = new ArrayList<>();

        public Builder header(String header) {
            this.header = header;
            return this;
        }

        public Builder messages(String messages) {
            this.messages = messages;
            return this;
        }

        public Builder emailList(List<String> emailList) {
            this.emailList = emailList;
            return this;
        }

        public EmailSender build() {
            return new EmailSender(header, messages, emailList);
        }

    }

}
