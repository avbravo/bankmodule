/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.peopleinmotion.horizonreinicioremoto.paginator;

/**
 *
 * @author avbravo
 */
public class Paginator {

    private String name;
    private String filter;
    private Integer page;
    private String title;
    private Integer numberOfPage;
    public Paginator() {
    }

    public Paginator(String name, String filter, Integer page, String title, Integer numberOfPage) {
        this.name = name;
        this.filter = filter;
        this.page = page;
        this.title = title;
        this.numberOfPage = numberOfPage;
    }

   
    
    

    public Integer getNumberOfPage() {
        return numberOfPage;
    }

    public void setNumberOfPage(Integer numberOfPage) {
        this.numberOfPage = numberOfPage;
    }

    
    
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

      
    public static class Builder {

    private String name;
    private String  filter;
    private Integer page;
    private String title;
        private Integer numberOfPage;
    
    
        public Builder name(String name) {
            this.name =name;
            return this;
        }
        
        public Builder filter(String  filter) {
            this.filter =filter;
            return this;
        }
        
public Builder title(String  title) {
            this.title =title;
            return this;
        }
public Builder page(Integer page) {
            this.page =page;
            return this;
        }
public Builder numberOfPage(Integer numberOfPagw) {
            this.numberOfPage =numberOfPage;
            return this;
        }


        public Paginator build() {
            return new Paginator(name, filter, page, title, numberOfPage);

        }
    }

}
