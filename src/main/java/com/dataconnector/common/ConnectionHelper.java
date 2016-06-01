/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dataconnector.common;

import com.dataconnector.connection.MetaDataDataconnector;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 * {Insert class description here}
 *
 * @version $Revision: 1.1.1 (UTF-8)
 * @since build 17/03/2016
 * @author proveedor_hhurtado email: proveedor_hhurtad@ath.com.co
 */
public class ConnectionHelper {

    public static Connection getConnection() {
        Connection con = null;
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/employees", "root", "Welcome1");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            Logger.getLogger(MetaDataDataconnector.class.getName()).log(Level.SEVERE, null, ex);
        }
        return con;

    }

    public static Connection getConnectionOracle() {

        Connection con = null;
        try {
            Class.forName("oracle.jdbc.OracleDriver").newInstance();
            con = DriverManager.getConnection("jdbc:oracle:thin:@10.130.4.157:1528:ATHORA12", "UNICO_DESA", "Ath12345");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            Logger.getLogger(MetaDataDataconnector.class.getName()).log(Level.SEVERE, null, ex);
        }

        return con;
    }

    public static Connection getJNDIConnection() {

        String DATASOURCE_CONTEXT = "jdbcunicodb_1";
        Connection con=null;
        // result = null;
        try {
            Context initialContext = new InitialContext();
            if (initialContext == null) {
                System.out.println("Problemas con la obtencion del contexto JNDI ");
                //  log("JNDI problem. Cannot get InitialContext.");
            }
            DataSource datasource = (DataSource) initialContext.lookup(DATASOURCE_CONTEXT);

           
                con= datasource.getConnection();

                    System.out.println("Conexion realizada exictosamente!!!");
                

          
        } catch (NamingException | SQLException ex ) {
            Logger.getLogger(ConnectionHelper.class.getName()).log(Level.SEVERE, null, ex);
          
        }

        return con;
    }

    public static Connection getConnectionSQLServer() {
        Connection con = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
            //con = DriverManager.getConnection("jdbc:sqlserver://10.130.4.14:1440;databaseName=DBUnico", "consulta", "consulta");
            //con = DriverManager.getConnection("jdbc:sqlserver://10.137.0.11:1433;databaseName=DBUnico", "consulta", "consulta");
            con = DriverManager.getConnection("jdbc:sqlserver://10.130.4.101:1501;databaseName=DBUnico", "sa", "Pruebas123");
            //10.137.0.11:1433
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            Logger.getLogger(MetaDataDataconnector.class.getName()).log(Level.SEVERE, null, ex);
        }
        return con;
    }

    public static void main(String arg[]) {

        try {
            DatabaseMetaData pro = null;

            try (Connection con = getConnectionOracle()) {
                pro = con.getMetaData();
                System.out.println("Driver Oracle: " + pro.getDatabaseProductName());
            }

            try (Connection con = getConnection()) {
                pro = con.getMetaData();
                System.out.println("Driver Mysql: " + pro.getDatabaseProductName());
            }

            try (Connection con = getConnectionSQLServer()) {
                pro = con.getMetaData();
                System.out.println("Driver SQLServer:" + pro.getDatabaseProductName());
            }

        } catch (SQLException ex) {
            Logger.getLogger(ConnectionHelper.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
