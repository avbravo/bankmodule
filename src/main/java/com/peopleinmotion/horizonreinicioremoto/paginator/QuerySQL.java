/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.peopleinmotion.horizonreinicioremoto.paginator;

import com.peopleinmotion.horizonreinicioremoto.paginator.Paginator;
import lombok.Data;

/**
 *
 * @author avbravo
 */

public class QuerySQL {

    private String query;
    private String count;

    public QuerySQL() {
    }

    public QuerySQL(String query, String count) {
        this.query = query;
        this.count= count;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

   

    
    
    
  
    public static class Builder {

        private String query;
        private String count;

        public Builder query(String query) {
            this.query = query;
            return this;
        }

        public Builder count(String count) {
            this.count = count;
            return this;
        }

        public QuerySQL build() {
            return new QuerySQL(query, count);

        }
    }

}
