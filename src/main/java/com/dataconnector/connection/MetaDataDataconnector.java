/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dataconnector.connection;

import com.dataconnector.common.ConnectionHelper;
import com.dataconnector.constans.ProvidersSupportEnum;
import com.dataconnector.object.AtributosDTO;
import com.dataconnector.object.ForeringKey;

import com.dataconnector.object.METADATA_ENUM;
import com.dataconnector.object.METADATA_TIPO_ENUM;
import com.dataconnector.object.PrimaryKey;
import com.dataconnector.object.TipoIndiceEnum;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * {Insert class description here}
 *
 * @version $Revision: 1.1.1 (UTF-8)
 * @since build 7/03/2016
 * @author proveedor_hhurtado email: proveedor_hhurtad@ath.com.co
 */
public class MetaDataDataconnector {

    private final Connection connection;
    private String nameTypeDriver;
    private final String esquemaBD;
    private DatabaseMetaData metadata;
    private ProvidersSupportEnum typeDatabase;

    private final Map<String, Map<String, AtributosDTO>> metadataBD;

    public MetaDataDataconnector(String esquemaBD, Connection con, String nameTypeDriver, ProvidersSupportEnum typeDatabase) {

        this.connection = con;
        this.esquemaBD = esquemaBD;
        this.nameTypeDriver = nameTypeDriver;
        metadataBD = new HashMap<>();
        this.typeDatabase = typeDatabase;

    }

    public MetaDataDataconnector(String esquemaBD, Connection con) {
        this.connection = con;
        this.esquemaBD = esquemaBD;
        metadataBD = new HashMap<>();
    }

    public void obtieneMetaDataBD() {
        try {
            //    this.connection = getConnection();

            metadata = connection.getMetaData();

            obtieneTablasEsquema();
            obtieneColumnasTablas();
            System.out.println("Total tablas MetaData: " + metadataBD.size());

            connection.close();

        } catch (SQLException ex) {
            Logger.getLogger(MetaDataDataconnector.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Map<String, Map<String, AtributosDTO>> obtenerMetaDataBD() throws SQLException {

        //    this.connection = getConnection();
        metadata = connection.getMetaData();

        obtieneTablasEsquema();
        obtieneColumnasTablas();
        System.out.println("Total tablas MetaData: " + metadataBD.size());

        return metadataBD;

    }

    private void obtieneTablasEsquema() {
        try {
            String[] types = {METADATA_ENUM.TABLE.getString()};
            ResultSet rs = metadata.getTables(esquemaBD, null, null, types);

            while (rs.next()) {
                String nombreTabla = rs.getString(METADATA_ENUM.TABLE_NAME.getString());
                // map.add(nombreTabla);
                metadataBD.put(nombreTabla, null);
            }
            System.out.println("Cantidad tablas esquema: " + metadataBD.size());
        } catch (SQLException ex) {
            // TODO: Logger
            ex.printStackTrace();
        }
    }

    private void obtieneColumnasTablas() {
        try {
            for (String tabla : metadataBD.keySet()) {
                Map mapAtributos = new HashMap();
                ResultSet rs = metadata.getColumns(esquemaBD, null, tabla, null);
                while (rs.next()) {
                    AtributosDTO atributo = new AtributosDTO();
                    String nombreAtributo = rs.getString(METADATA_ENUM.COLUMN_NAME.getString());
                    atributo.setNombreAtributo(nombreAtributo);
                    atributo.setTipoOriginal(rs.getString(METADATA_ENUM.TYPE_NAME.getString()));
                    atributo.setTipoMapeado(METADATA_TIPO_ENUM.getMetaDataTipoEnum(rs.getString(METADATA_ENUM.TYPE_NAME.getString())));

                    mapAtributos.put(nombreAtributo, atributo);
                    metadataBD.put(tabla, mapAtributos);
                }

                //Extracción de llave Primarias
                ResultSet rsTypeKey;

                rsTypeKey = metadata.getPrimaryKeys(esquemaBD, null, tabla);
                while (rsTypeKey.next()) {
                    PrimaryKey key = new PrimaryKey();
                    key.setPkName(rsTypeKey.getString(METADATA_ENUM.PK_NAME.getString()));

                    String nombreColumn = rsTypeKey.getString(METADATA_ENUM.COLUMN_NAME.getString());
                    key.setColumnName(nombreColumn);
                    key.setTableName(tabla);
                    //
                    Map<String, AtributosDTO> mapAtr = metadataBD.get(tabla);
                    AtributosDTO atributo = mapAtr.get(nombreColumn);
                    atributo.setLlavePrimaria(key);
                }
                //Extraccion de llaves foraneas

                //     ResultSet rsForeginKey = metadata.getExportedKeys(esquemaBD, null, tabla);
                rsTypeKey = metadata.getExportedKeys(esquemaBD, null, tabla);
                while (rsTypeKey.next()) {
                    ForeringKey fkKey = new ForeringKey();
                    fkKey.setFkname(rsTypeKey.getString(METADATA_ENUM.FK_NAME.getString()));
                    fkKey.setFktable(rsTypeKey.getString(METADATA_ENUM.FKTABLE_NAME.getString()));
                    //String nombreColumn = rsTypeKey.getString(METADATA_ENUM.FKCOLUMN_NAME.getString());
                    fkKey.setFkColumnName(rsTypeKey.getString(METADATA_ENUM.FKCOLUMN_NAME.getString()));
                    String nombreColumn = rsTypeKey.getString(METADATA_ENUM.PKCOLUMN_NAME.getString());
                    fkKey.setPkColumnName(rsTypeKey.getString(METADATA_ENUM.PKCOLUMN_NAME.getString()));
                    fkKey.setPkTableName(rsTypeKey.getString(METADATA_ENUM.PKTABLE_NAME.getString()));

                    Map<String, AtributosDTO> mapAtr = metadataBD.get(tabla);
                    AtributosDTO atributo = mapAtr.get(nombreColumn);
                    atributo.setForeringKey(fkKey);
                }
            }
        } catch (SQLException ex) {
            //TODO: Logger
            ex.printStackTrace();
        }
    }

    /*private IndicesDTO getIndice(String tabla, TipoIndiceEnum tipoLlave) throws SQLException {

     ResultSet rsTypeKey;
     if (tipoLlave.equals(TipoIndiceEnum.PRIMARY_KEY)) {
     rsTypeKey = metadata.getPrimaryKeys(esquemaBD, null, tabla);
     } else {
     rsTypeKey = metadata.getExportedKeys(esquemaBD, null, tabla);
     }
     IndicesDTO indice = new IndicesDTO();
     indice.setNombreIndice(rsTypeKey.getString(METADATA_ENUM.PK_NAME.getString()));
     indice.setIndice(tipoLLave);
     String nombreColumn = rsTypeKey.getString(METADATA_ENUM.COLUMN_NAME.getString());
     //
     Map<String, AtributosDTO> mapAtr = metadataBD.get(tabla);
     AtributosDTO atributo = mapAtr.get(nombreColumn);
     atributo.setIndicesTabla(indice);
     return indice;
     }*/
    private static void getNewInstanceDataConnector() {
        //TODO  Relizar lectura por archivo xml

       // metaDataDataconnector = new MetaDataDataconnector("employees", ConnectionHelper.getConnection(),"com.mysql.jdbc.Driver");
        //       metaDataDataconnector = new MetaDataDataconnector("DBUnico", ConnectionHelper.getConnectionSQLServer(),"com.microsoft.sqlserver.jdbc.SQLServerDriver");
    }

    public Map<String, Map<String, AtributosDTO>> getMetadataBD() {
        return metadataBD;
    }

    public String getNameTypeDriver() {
        return nameTypeDriver;
    }

    public ProvidersSupportEnum getTypeDatabase() {
        return typeDatabase;
    }

}
