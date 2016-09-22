/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dataconnector.xml.object;

/**
 *{Insert class description here}
 * @version $Revision: 1.1.1  (UTF-8)
 * @since build 24/05/2016  
 * @author proveedor_hhurtado  email: proveedor_hhurtad@ath.com.co
 */
public class FieldSimpleConf {
    private String alias_field;
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    

    public String getAlias_field() {
        return alias_field;
    }

    public void setAlias_field(String alias_field) {
        this.alias_field = alias_field;
    }

    @Override
    public String toString() {
        return "FieldConf{" + "alias_field=" + alias_field + ", id=" + id + '}';
    }
    
    
    
}
