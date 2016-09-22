/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dataconnector.xml.object;

/**
 * {Insert class description here}
 *
 * @version $Revision: 1.1.1 (UTF-8)
 * @since build 24/05/2016
 * @author proveedor_hhurtado email: proveedor_hhurtad@ath.com.co
 */
public class DataConnectorMetadata {

    private JarInfoGenerate JarInfoGenerate_def;
    private String output_dir_jar;
    private String dir_lib;
    private String package_name_gen;
    private TablesConf Tables;
    private JdbcConnectionConf jdbc_connection;

    public DataConnectorMetadata() {

    }

    public TablesConf getTables() {
        return Tables;
    }

    public void setTables(TablesConf Tables) {
        this.Tables = Tables;
    }

    public JarInfoGenerate getJarInfoGenerate_def() {
        return JarInfoGenerate_def;
    }

    public void setJarInfoGenerate_def(JarInfoGenerate JarInfoGenerate_def) {
        this.JarInfoGenerate_def = JarInfoGenerate_def;
    }

    public String getOutput_dir_jar() {
        return output_dir_jar;
    }

    public void setOutput_dir_jar(String output_dir_jar) {
        this.output_dir_jar = output_dir_jar;
    }

    public String getDir_lib() {
        return dir_lib;
    }

    public void setDir_lib(String dir_lib) {
        this.dir_lib = dir_lib;
    }

    public String getPackage_name_gen() {
        return package_name_gen;
    }

    public void setPackage_name_gen(String package_name_gen) {
        this.package_name_gen = package_name_gen;
    }

    public JdbcConnectionConf getJdbc_connection() {
        return jdbc_connection;
    }

    public void setJdbc_connection(JdbcConnectionConf jdbc_connection) {
        this.jdbc_connection = jdbc_connection;
    }

    @Override
    public String toString() {
        return "DataConnectorMetadata{" + "JarInfoGenerate_def=" + JarInfoGenerate_def + ", output_dir_jar=" + output_dir_jar + ", dir_lib=" + dir_lib + ", package_name_gen=" + package_name_gen + ", Tables=" + Tables + ", jdbc_connection=" + jdbc_connection + '}';
    }

    
    
}
