/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dataconnector.object;

/**
 *
 * @author jdquirogar
 */
public enum METADATA_ENUM {

    TABLE("TABLE"),
    COLUMN_NAME("COLUMN_NAME"),
    TYPE_NAME("TYPE_NAME"),
    PK_NAME("PK_NAME"),
    FK_NAME("FK_NAME"),
    FKCOLUMN_NAME("FKCOLUMN_NAME"),
    FKTABLE_NAME("FKTABLE_NAME"),
    PKTABLE_NAME("PKTABLE_NAME"),
    PKCOLUMN_NAME("PKCOLUMN_NAME"),
    
    TABLE_NAME("TABLE_NAME");
    
    private final String nombre;

    private METADATA_ENUM(String nombre) {
        this.nombre=nombre;
    }
    

  /*  public static String getMetadata(METADATA_ENUM metadata) {
        switch (metadata) {
            case TABLE:
                return "TABLE";
            case COLUMN_NAME:
                return "COLUMN_NAME";
            case TYPE_NAME:
                return "TYPE_NAME";
            case TABLE_NAME:
                return "TABLE_NAME";
            default:
                return null;
        }
    }*/

    public String getString() {
        return nombre;
    }
    

}
