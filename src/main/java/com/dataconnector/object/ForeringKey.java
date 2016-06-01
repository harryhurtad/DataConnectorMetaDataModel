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
public class ForeringKey {

    private String fkname;
    private String fktable;
    private String fkColumnName;
    private String pkTableName;
    private String pkColumnName;

    public String getFkname() {
        return fkname;
    }

    public void setFkname(String fkname) {
        this.fkname = fkname;
    }

    public String getFktable() {
        return fktable;
    }

    public void setFktable(String fktable) {
        this.fktable = fktable;
    }

    public String getFkColumnName() {
        return fkColumnName;
    }

    public void setFkColumnName(String fkColumnName) {
        this.fkColumnName = fkColumnName;
    }

    public String getPkTableName() {
        return pkTableName;
    }

    public void setPkTableName(String pkTableName) {
        this.pkTableName = pkTableName;
    }

    public String getPkColumnName() {
        return pkColumnName;
    }

    public void setPkColumnName(String pkColumnName) {
        this.pkColumnName = pkColumnName;
    }
    
    
}
