/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dataconnector.xml.object;


import com.dataconnector.commons.anotations.CollectionInfoDataConnector;
import java.util.ArrayList;
import java.util.List;

/**
 *{Insert class description here}
 * @version $Revision: 1.1.1  (UTF-8)
 * @since build 24/05/2016  
 * @author proveedor_hhurtado  email: proveedor_hhurtad@ath.com.co
 */
public class FieldsConf {
   @CollectionInfoDataConnector(nameClassGeneric = "com.dataconnector.xml.object.FieldSimpleConf")
    private final List<FieldSimpleConf> Field;

    public FieldsConf() {
        Field=new ArrayList<>();
    }

    public List<FieldSimpleConf> getField() {
        return Field;
    }

    @Override
    public String toString() {
        return "FieldsConf{" + "Field=" + Field + '}';
    }

   
   
}
