/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dataconnector.object;

/**
 * {Insert class description here}
 *
 * @version $Revision: 1.1.1 (UTF-8)
 * @since build 7/03/2016
 * @author proveedor_hhurtado email: proveedor_hhurtad@ath.com.co
 */
public enum METADATA_TIPO_ENUM {

    INTEGER,
    VARCHAR,
    BOOLEAN,
    BIGINT,
    DATETIME,
    MONEY,
    CHAR,
    FLOAT,
    SYSNAME,
    DECIMAL,
    UNIQUE_IDENTIFIER,
    TEXT,
    IMAGE,
    BINARY,
    VARBINARY,
    NUMERIC;

    public static String getMetaDataTipo(METADATA_TIPO_ENUM tipo) {
        switch (tipo) {
            case BOOLEAN:
                return "java.lang.Boolean";
            case VARCHAR:
                return "java.lang.String";
            case INTEGER:
                return "java.lang.Integer";
            case BIGINT:
                return "java.math.BigInteger";
            case DATETIME:
                return "java.util.Date";
            case MONEY:
                return "java.lang.Double";
            case CHAR:
                return "java.lang.String";
            case FLOAT:
                return "java.lang.Float";
            case SYSNAME:
                return "java.lang.String";
            case DECIMAL:
                return "java.math.BigDecimal";
            case UNIQUE_IDENTIFIER:
                return "java.util.UUID";
            case TEXT:
                return "java.lang.String";
            case IMAGE:
                return "java.lang.Byte";
            case BINARY:
                return "java.lang.Byte";
            case VARBINARY:
                return "java.lang.Byte";
            case NUMERIC:
                return "java.math.BigDecimal";
            default:
                return null;
        }
    }

    public static METADATA_TIPO_ENUM getMetaDataTipoEnum(String tipo) {
     String tipoTmp=tipo.toLowerCase();   
        if (tipoTmp.equals("varchar") || tipoTmp.contains("varchar")) {
            return VARCHAR;
        } else if (tipoTmp.equals("integer") || tipoTmp.equals("int") || tipoTmp.contains("integer") || tipoTmp.contains("int")) {
            return INTEGER;
        } else if (tipoTmp.equals("bit") || tipoTmp.contains("bit")) {
            return BOOLEAN;
        } else if (tipoTmp.equals("bigint") || tipoTmp.contains("bigint")) {
            return BIGINT;

        } else if (tipoTmp.equals("datetime") || tipoTmp.contains("datetime")|| tipoTmp.contains("date")) {
            return DATETIME;
        } else if (tipoTmp.equals("money") || tipoTmp.contains("money")) {
            return MONEY;
        } else if (tipoTmp.equals("char") || tipoTmp.contains("char")) {
            return CHAR;
        } else if (tipoTmp.equals("float") || tipoTmp.contains("float")) {
            return FLOAT;
        } else if (tipoTmp.equals("sysname") || tipoTmp.contains("sysname")) {
            return SYSNAME;
        } else if (tipoTmp.equals("decimal") || tipoTmp.contains("decimal")) {
            return DECIMAL;
        } else if (tipoTmp.equals("uniqueidentifier") || tipoTmp.contains("uniqueidentifier")) {
            return UNIQUE_IDENTIFIER;
        } else if (tipoTmp.equals("text") || tipoTmp.contains("text")) {
            return TEXT;
        } else if (tipoTmp.equals("image") || tipoTmp.contains("image")) {
            return IMAGE;
        } else if (tipoTmp.equals("binary") || tipoTmp.contains("binary")) {
            return BINARY;
        } else if (tipoTmp.equals("varbinary") || tipoTmp.contains("varbinary")) {
            return VARBINARY;
        } else if (tipoTmp.equals("numeric") || tipoTmp.contains("numeric")) {
            return NUMERIC;
        } else {
            System.out.println(tipo);
            return null;
        }

    }
}
