/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dataconnector.object;

/**
 *{Insert class description here}
 * @version $Revision: 1.1.1  (UTF-8)
 * @since build 7/03/2016  
 * @author proveedor_hhurtado  email: proveedor_hhurtad@ath.com.co
 */
public class AtributosDTO {

    private String nombreAtributo;
    private METADATA_TIPO_ENUM tipoMapeado;
    private String tipoOriginal;
    private PrimaryKey llavePrimaria;
    private ForeringKey foreringKey;

    public String getNombreAtributo() {
        return nombreAtributo;
    }

    public void setNombreAtributo(String nombreAtributo) {
        this.nombreAtributo = nombreAtributo;
    }

    public METADATA_TIPO_ENUM getTipoMapeado() {
        return tipoMapeado;
    }

    public void setTipoMapeado(METADATA_TIPO_ENUM tipoMapeado) {
        this.tipoMapeado = tipoMapeado;
    }

    public String getTipoOriginal() {
        return tipoOriginal;
    }

    public void setTipoOriginal(String tipoOriginal) {
        this.tipoOriginal = tipoOriginal;
    }

    public PrimaryKey getLlavePrimaria() {
        return llavePrimaria;
    }

    public void setLlavePrimaria(PrimaryKey llavePrimaria) {
        this.llavePrimaria = llavePrimaria;
    }

    public ForeringKey getForeringKey() {
        return foreringKey;
    }

    public void setForeringKey(ForeringKey foreringKey) {
        this.foreringKey = foreringKey;
    }

   
    
}
