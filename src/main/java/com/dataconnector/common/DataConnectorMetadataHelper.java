/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dataconnector.common;

import com.dataconnector.commons.xml.ProccessXMLDataConnector;
import com.dataconnector.commons.helper.DataConnectorHelper;
import com.dataconnector.connection.MetaDataDataconnector;
import com.dataconnector.constans.ProvidersSupportEnum;
import com.dataconnector.object.AtributosDTO;
import com.dataconnector.xml.object.DataConnectorMetadata;
import com.dataconnector.xml.object.JdbcConnectionConf;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * {Insert class description here}
 *
 * @version $Revision: 1.1.1 (UTF-8)
 * @since build 3/06/2016
 * @author proveedor_hhurtado email: proveedor_hhurtad@ath.com.co
 */
public class DataConnectorMetadataHelper {

    public static void generateMetadataDataConnector() {
        DataConnectorMetadata conf = new DataConnectorMetadata();
        ProccessXMLDataConnector prXmlDataConn = new ProccessXMLDataConnector();
        DataConnGenerateClassMetadata classMetadata = new DataConnGenerateClassMetadata();
        Map<String, Map<String, AtributosDTO>> mapMetadata = null;

        try {
            //Procesa El archivo XML
            System.out.println("Digite la path del archivo DataConnectorMetadata-conf.xml");
            BufferedReader parametros = new BufferedReader(new InputStreamReader(System.in));
            String path = parametros.readLine();
            System.out.println("Valor digitado: " + path);

            File f = new File(path);
            // System.out.println("Exsite el archivo: "+f.exists());
            //Cargar Info Metadata de la BD
            prXmlDataConn.readDocumentXMLDataconnector(f, conf);
            //Obtiene la conexion de la BD

            JdbcConnectionConf driverConf = conf.getJdbc_connection();
            Class.forName(driverConf.getDriverClass()).newInstance();
            MetaDataDataconnector metaDataDataconn;
            System.out.println("Obteniendo info de la metadata de la BD...");
            // Obtiene la metada de la BD
            try (Connection con = DriverManager.getConnection(driverConf.getURL(), driverConf.getUsuario(), driverConf.getPassword())) {
                // Obtiene la metada de la BD
                metaDataDataconn = new MetaDataDataconnector(driverConf.getSchemaBD(), con);
                mapMetadata = metaDataDataconn.obtenerMetaDataBD();
            }
            System.out.println("Generando fuentes metadata DataConnector...");
            if (mapMetadata != null&& !mapMetadata.isEmpty()) {
                File[] listClass = classMetadata.generateClassMetadata(mapMetadata, conf);
                //Se realiza la generación del jar.
                System.out.println("Empaquetando clases...");
                DataConnectorHelper.getInstance().generateJAR(new File(conf.getOutput_dir_jar()),conf.getJarInfoGenerate_def(), listClass, conf.getPackage_name_gen());
            } else {
                System.err.println("Problemas!!! Metadata Vacia...");
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            Logger.getLogger(MetaDataDataconnector.class.getName()).log(Level.SEVERE, "Problemas al obtener la conexion a la BD", ex);
        } catch (IOException ex) {
            Logger.getLogger(DataConnectorMetadataHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(final String arg[]) {

        generateMetadataDataConnector();
    }
}
