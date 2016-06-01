/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dataconnector.object;

/**
 *{Insert class description here}
 * @version $Revision: 1.1.1  (UTF-8)
 * @since build 8/03/2016  
 * @author proveedor_hhurtado  email: proveedor_hhurtad@ath.com.co
 */
public class PrimaryKey {
    
    private String tableName;
    private String columnName;
    private String pkName;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getPkName() {
        return pkName;
    }

    public void setPkName(String pkName) {
        this.pkName = pkName;
    }
    
    

}
