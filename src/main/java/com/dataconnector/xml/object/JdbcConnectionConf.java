/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dataconnector.xml.object;

/**
 *{Insert class description here}
 * @version $Revision: 1.1.1  (UTF-8)
 * @since build 3/06/2016  
 * @author proveedor_hhurtado  email: proveedor_hhurtad@ath.com.co
 */
public class JdbcConnectionConf {

    private String driverClass;
    private String URL;
    private String usuario;
    private String password;
    private String schemaBD;

    public String getDriverClass() {
        return driverClass;
    }

    public void setDriverClass(String driverClass) {
        this.driverClass = driverClass;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSchemaBD() {
        return schemaBD;
    }

    public void setSchemaBD(String schemaBD) {
        this.schemaBD = schemaBD;
    }

    
    
    @Override
    public String toString() {
        return "JdbcConnectionConf{" + "driverClass=" + driverClass + ", URL=" + URL + ", usuario=" + usuario + ", password=" + password + '}';
    }
    
    
}
