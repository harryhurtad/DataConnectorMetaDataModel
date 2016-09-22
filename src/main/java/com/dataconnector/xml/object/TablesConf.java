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
 * {Insert class description here}
 *
 * @version $Revision: 1.1.1 (UTF-8)
 * @since build 24/05/2016
 * @author proveedor_hhurtado email: proveedor_hhurtad@ath.com.co
 */
public class TablesConf {

    @CollectionInfoDataConnector(nameClassGeneric = "com.dataconnector.xml.object.TableOverrideConf")
    private List<TableOverrideConf> table_override;

    public TablesConf() {
        table_override = new ArrayList<>();
    }

    public List<TableOverrideConf> getTable_override() {
        return table_override;
    }

    public void setTable_override(List<TableOverrideConf> table_override) {
        this.table_override = table_override;
    }

    @Override
    public String toString() {
        return "TablesConf{" + "table_override=" + table_override + '}';
    }
    
    

}
