package com.easyJava.builder;

import com.easyJava.bean.Constants;
import com.easyJava.bean.FieldInfo;
import com.easyJava.bean.TableInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.file.Files;

public class BuildPo {
    private static final Logger logger= LoggerFactory.getLogger(BuildPo.class);
    public static void execute(TableInfo tableInfo) throws IOException {
        File folder=new File(Constants.PATH_PO);
        if (!folder.exists()){
            folder.mkdirs();
        }
        File poFile=new File(folder,tableInfo.getBeanName()+".java");
        OutputStream out=null;
        OutputStreamWriter outputStreamWriter=null;
        BufferedWriter bufferedWriter=null;
        try{
            out= Files.newOutputStream(poFile.toPath());
            outputStreamWriter=new OutputStreamWriter(out);
            bufferedWriter=new BufferedWriter(outputStreamWriter);
            bufferedWriter.write("package "+Constants.PACKAGE_PO+";");
            bufferedWriter.newLine();
            bufferedWriter.newLine();
            bufferedWriter.write("import java.io.Serializable;");
            bufferedWriter.newLine();
            if(tableInfo.getHaveDate()||tableInfo.getHaveDateTime()){
                bufferedWriter.write("import java.util.Date;");
                bufferedWriter.newLine();
            }
            if(tableInfo.getHaveBigDecimal()){
                bufferedWriter.write("import java.math.BigDecimal;");
                bufferedWriter.newLine();
            }
            bufferedWriter.newLine();
            BuildComment.createClassComment(bufferedWriter,tableInfo.getComment());
            bufferedWriter.write("public class "+tableInfo.getBeanName()+" implements Serializable{");
            bufferedWriter.newLine();
            for(FieldInfo fieldInfo:tableInfo.getFieldList()){
               // if(fieldInfo.getComment()!=null){
                    BuildComment.createFieldComment(bufferedWriter,fieldInfo.getComment());

               // }
                bufferedWriter.write("\t private "+fieldInfo.getJavaType()+" "+fieldInfo.getPropertyName()+";");
                bufferedWriter.newLine();
                bufferedWriter.newLine();
            }
            bufferedWriter.write("}");
            bufferedWriter.flush();
        }
        catch (Exception e){
            logger.error("创建po失败");

        }
        finally {
            bufferedWriter.close();
            outputStreamWriter.close();
            out.close();
        }

    }
}
