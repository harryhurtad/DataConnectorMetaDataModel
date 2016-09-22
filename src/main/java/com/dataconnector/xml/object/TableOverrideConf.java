/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dataconnector.xml.object;


import java.util.ArrayList;
import java.util.List;

/**
 *{Insert class description here}
 * @version $Revision: 1.1.1  (UTF-8)
 * @since build 24/05/2016  
 * @author proveedor_hhurtado  email: proveedor_hhurtad@ath.com.co
 */
public class TableOverrideConf {
        

  
    private String alias_table;
    private String id;
    private FieldsConf Fields;

    public TableOverrideConf() {
       
    }

    public String getAlias_table() {
        return alias_table;
    }

    public void setAlias_table(String alias_table) {
        this.alias_table = alias_table;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public FieldsConf getFields() {
        return Fields;
    }

    public void setFields(FieldsConf Fields) {
        this.Fields = Fields;
    }

    @Override
    public String toString() {
        return "TableOverrideConf{" + "alias_table=" + alias_table + ", id=" + id + ", Fields=" + Fields + '}';
    }

    
    
   
    
    
    
}
