/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dataconnector.common;

import com.dataconnector.commons.helper.DataConnectorHelper;
import com.dataconnector.compiler.CompilerDataConnectorException;
import com.dataconnector.compiler.Javac;
import com.dataconnector.generate.object.AtributosGenMetadataClass;
import com.dataconnector.object.AtributosDTO;
import com.dataconnector.object.METADATA_TIPO_ENUM;
import com.dataconnector.xml.object.DataConnectorMetadata;
import com.dataconnector.xml.object.FieldSimpleConf;
import com.dataconnector.xml.object.FieldsConf;
import com.dataconnector.xml.object.TableOverrideConf;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * {Insert class description here}
 *
 * @version $Revision: 1.1.1 (UTF-8)
 * @since build 3/06/2016
 * @author proveedor_hhurtado email: proveedor_hhurtad@ath.com.co
 */
public class DataConnGenerateClassMetadata {

    public File[] generateClassMetadata(Map<String, Map<String, AtributosDTO>> atributos, DataConnectorMetadata metadata) {
        File[] listFileClass = null;
        try {

            //Varaibles
            String packageVar = metadata.getPackage_name_gen();
            String dirOutputSCR = metadata.getOutput_dir_jar() + "//scr";
            String dirOutputTarget = metadata.getOutput_dir_jar() + "//target";

            String dirLibDataConnector = metadata.getDir_lib();

            StringBuilder classPath = new StringBuilder();
            Map root;
            List<AtributosGenMetadataClass> listaAtributos;
            List<File> listArchivoFuentes = new ArrayList();
            String nameTable;
            String fieldName;
            boolean isTableRename = false;

            //
            //Crea un directorio Temporal para descargar las platillas a procesar
            File dirTemp = File.createTempFile("dataconnector_freemaker", "");
            createDirectory(dirTemp);

            InputStream in = this.getClass().getResourceAsStream("/templates/TemplateClassMetadataDataConnector.txt");
            int dato = 0;
            File output = new File(dirTemp, "TemplateClassMetadataDataConnector.txt");
            try (OutputStream outFile = new FileOutputStream(output)) {
                while ((dato = in.read()) != -1) {
                    outFile.write(dato);
                }
            }
            //Se crea directorio para la complación de las fuentes
            File dirScr = new File(dirOutputSCR);
            createDirectory(dirScr);
            System.out.println("output: " + dirScr.getAbsolutePath());
            createDirectory(new File(dirOutputTarget));
            //Crea el directorio para los paquetes
            File dirPackage = new File(dirScr, packageVar.replaceAll("\\.", "/"));
            createDirectory(dirPackage);

            Configuration cfg = new Configuration(Configuration.VERSION_2_3_24);
            cfg.setDirectoryForTemplateLoading(dirTemp);
            cfg.setDefaultEncoding("UTF-8");
            cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
            cfg.setLogTemplateExceptions(false);
            //
            //Realiza la creacion de las classes acorde a la plantilla
            Set<Map.Entry<String, Map<String, AtributosDTO>>> listTables = atributos.entrySet();
            for (Map.Entry<String, Map<String, AtributosDTO>> entry : listTables) {
                root = new HashMap();
                nameTable = null;
                root.put("package", packageVar);

                Map<String, AtributosDTO> values = entry.getValue();
                listaAtributos = new ArrayList();
                 FieldsConf fieldsRename = null;
                if (metadata.getTables() != null) {
                    List<TableOverrideConf> listTableRename = metadata.getTables().getTable_override();
                     isTableRename = false;

                    for (TableOverrideConf tableRename : listTableRename) {

                        if (tableRename.getId().equals(entry.getKey())) {
                            nameTable = tableRename.getAlias_table();
                            fieldsRename = tableRename.getFields();

                            isTableRename = true;
                            root.put("table_rename", isTableRename);
                            root.put("table_original", entry.getKey());
                            break;
                        }
                    }
                }
                nameTable = (nameTable == null) ? entry.getKey().toUpperCase() : nameTable.toUpperCase();
                root.put("name_table", nameTable);

                //Adiciona los atributos a la lista
                for (Map.Entry<String, AtributosDTO> field : values.entrySet()) {

                    fieldName = validateNameField(field.getKey(), fieldsRename != null ? fieldsRename.getField() : null);
                    String type = field.getValue().getTipoMapeado() != null ? METADATA_TIPO_ENUM.getMetaDataTipo(field.getValue().getTipoMapeado()) : null;
                    if (type != null) {
                        AtributosGenMetadataClass at1 = new AtributosGenMetadataClass(fieldName, type,field.getKey());
                        listaAtributos.add(at1);
                    }
                }
                root.put("atributos", listaAtributos);

                /* Get the template (uses cache internally) */
                Template temp = cfg.getTemplate(output.getName());
                /* Merge data-model with template */
                String nameClassFile = packageVar.replaceAll("\\.", "/") + "//" + nameTable + ".java";
                File sourceFile = new File(dirScr, nameClassFile);

                Writer out = new OutputStreamWriter(new FileOutputStream(sourceFile));
                //Geenra el archivo
                temp.process(root, out);
                listArchivoFuentes.add(sourceFile);
            }

            //Analizando archivos contenidos en la carpeta lib para incluirlos en la path
            File dirLib = new File(dirLibDataConnector);
            File[] files = dirLib.listFiles();
            for (File file : files) {
                classPath.append(file.getAbsolutePath());
                //classPath.append(";");
            }
            //Se compilan las clases 
            Javac compiler = new Javac(classPath.toString(), dirOutputTarget);
            compiler.compile(listArchivoFuentes.toArray(new File[]{}));

            //Obtener listado de .class a empaquetar
            listFileClass = (new File(dirOutputTarget, packageVar.replaceAll("\\.", "/"))).listFiles();
            //   DataConnectorHelper.getInstance().generateJAR(metadata.getJarInfoGenerate_def(), listFileClass, packageVar);

        } catch (IOException | TemplateException ex) {
            Logger.getLogger(DataConnGenerateClassMetadata.class.getName()).log(Level.SEVERE, null, ex);
        } catch (CompilerDataConnectorException ex) {
            Logger.getLogger(DataConnGenerateClassMetadata.class.getName()).log(Level.SEVERE, null, ex);
        }

        return listFileClass;
    }

    /**
     *
     * @param drecotry
     * @throws IOException
     */
    private void createDirectory(File drecotry) throws IOException {

        if (drecotry.exists()) {
            delete(drecotry);
        }

        if (!drecotry.exists() && !(drecotry.mkdirs())) {
            throw new IOException("Could not create temp directory: " + drecotry.getAbsolutePath());
        }

    }

    /**
     * Elimina los archivos y carpetas generadas
     *
     * @param file
     * @throws IOException
     */
    private void delete(File file)
            throws IOException {

        if (file.isDirectory()) {

            //directory is empty, then delete it
            if (file.list().length == 0) {

                file.delete();
                System.out.println("Directory is deleted : "
                        + file.getAbsolutePath());

            } else {

                //list all the directory contents
                String files[] = file.list();

                for (String temp : files) {
                    //construct the file structure
                    File fileDelete = new File(file, temp);

                    //recursive delete
                    delete(fileDelete);
                }

                //check the directory again, if empty then delete it
                if (file.list().length == 0) {
                    file.delete();
                    System.out.println("Directory is deleted : "
                            + file.getAbsolutePath());
                }
            }

        } else {
            //if file, then delete it
            file.delete();
            System.out.println("File is deleted : " + file.getAbsolutePath());
        }
    }

    private String validateNameField(String field, List<FieldSimpleConf> listFieldToRename) {
        String valueReanme = null;
        if (listFieldToRename != null) {
            for (FieldSimpleConf fieldReanme : listFieldToRename) {
                if (fieldReanme.getId().equals(field)) {
                    valueReanme = fieldReanme.getAlias_field();
                    break;
                }

            }
        }
        return valueReanme == null ? field : valueReanme;

    }
}
