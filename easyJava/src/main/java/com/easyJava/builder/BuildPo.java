package com.easyJava.builder;

import com.easyJava.bean.Constants;
import com.easyJava.bean.FieldInfo;
import com.easyJava.bean.TableInfo;
import com.easyJava.utils.DateUtils;
import com.easyJava.utils.StringUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.file.Files;
import java.util.List;

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
                bufferedWriter.write(Constants.SERIALIZATION_BEAN_DATE_CLASS);
                bufferedWriter.newLine();
                bufferedWriter.write(Constants.DESERIALIZATION_BEAN_DATE_CLASS);
                bufferedWriter.newLine();
                bufferedWriter.write("import com.easyJava.enums.DateTimePatternEnum;");
                bufferedWriter.newLine();
                bufferedWriter.write("import com.easyJava.utils.DateUtils;");
                bufferedWriter.newLine();
            }
            if(tableInfo.getHaveBigDecimal()){
                bufferedWriter.write("import java.math.BigDecimal;");
                bufferedWriter.newLine();
            }
            for(FieldInfo fieldInfo:tableInfo.getFieldList()){
                if(ArrayUtils.contains(Constants.FIELD_IN_IGNORE,fieldInfo.getPropertyName())){
                    bufferedWriter.write(Constants.IGNORE_BEAN_2JSON_CLASS);
                    bufferedWriter.newLine();
                    break;
                }
            }
            bufferedWriter.newLine();
            BuildComment.createClassComment(bufferedWriter,tableInfo.getComment());
            bufferedWriter.write("public class "+tableInfo.getBeanName()+" implements Serializable{");
            bufferedWriter.newLine();


            for(FieldInfo fieldInfo:tableInfo.getFieldList()){
                if(fieldInfo.getComment()!=null){
                    BuildComment.createFieldComment(bufferedWriter,fieldInfo.getComment());

                }
                String type=fieldInfo.getSqlType();
                if(ArrayUtils.contains(Constants.SQL_DATE_TYPES,type)||ArrayUtils.contains(Constants.SQL_DATE_TIME_TYPES,type)){
                    bufferedWriter.write("\t "+String.format(Constants.SERIALIZATION_BEAN_DATE_EXPRESSION, DateUtils.YYYY_MM_DD_HH_MM_SS));
                    bufferedWriter.newLine();
                    bufferedWriter.write("\t "+String.format(Constants.DESERIALIZATION_BEAN_DATE_EXPRESSION, DateUtils.YYYY_MM_DD_HH_MM_SS));
                    bufferedWriter.newLine();
                }
                if(ArrayUtils.contains(Constants.FIELD_IN_IGNORE,fieldInfo.getPropertyName())){
                    bufferedWriter.write("\t "+Constants.IGNORE_BEAN_2JSON_EXPRESSION);
                    bufferedWriter.newLine();
                }
                bufferedWriter.write("\t private "+fieldInfo.getJavaType()+" "+fieldInfo.getPropertyName()+";");
                bufferedWriter.newLine();
                bufferedWriter.newLine();
            }
            for(FieldInfo fieldInfo:tableInfo.getFieldList()){
                String tempField= StringUtils.upperCaseFirstLetter(fieldInfo.getPropertyName());
                String setField="set"+tempField;
                String getField="get"+tempField;
                String propertyName=fieldInfo.getPropertyName();
                String javaType=fieldInfo.getJavaType();
                bufferedWriter.write("\tpublic void "+setField+"("+javaType+" "+propertyName+"){");
                bufferedWriter.newLine();
                bufferedWriter.write("\t\t"+"this."+propertyName+"="+propertyName+";");
                bufferedWriter.newLine();
                bufferedWriter.write("\t}");
                bufferedWriter.newLine();


                bufferedWriter.write("\tpublic "+javaType+" "+getField+"(){");
                bufferedWriter.newLine();
                bufferedWriter.write("\t\treturn "+"this."+propertyName+";");
                bufferedWriter.newLine();
                bufferedWriter.write("\t}");
                bufferedWriter.newLine();

            }
            bufferedWriter.write("\t@Override");
            bufferedWriter.newLine();
            bufferedWriter.write("\tpublic String toString(){");
            bufferedWriter.newLine();
            bufferedWriter.write("\t\treturn ");
            List<FieldInfo> list=tableInfo.getFieldList();
            int l=list.size();
            for(int i=0;i<l;i++){

                FieldInfo fieldInfo=tableInfo.getFieldList().get(i);
                if(ArrayUtils.contains(Constants.SQL_DATE_TIME_TYPES,fieldInfo.getSqlType())){
                    bufferedWriter.write("\""+fieldInfo.getComment()+":\"+" +
                            "("+fieldInfo.getPropertyName()+"==null?\"空\":"+
                            "DateUtils.format("+fieldInfo.getPropertyName()+","+" DateTimePatternEnum.YYYY_MM_DD_HH_MM_SS.getPattern())"
                            +")");

                }
                else if(ArrayUtils.contains(Constants.SQL_DATE_TYPES,fieldInfo.getSqlType())){
                    bufferedWriter.write("\""+fieldInfo.getComment()+":\"+" +
                            "("+fieldInfo.getPropertyName()+"==null?\"空\":"+
                            "DateUtils.format("+fieldInfo.getPropertyName()+","+" DateTimePatternEnum.YYYY_MM_DD.getPattern())"
                            +")");

                }
                else{
                    bufferedWriter.write("\""+fieldInfo.getComment()+":\"+" +
                            "("+fieldInfo.getPropertyName()+"==null?\"空\":"+fieldInfo.getPropertyName()+")");

                }
               if(i<l-1){
                    bufferedWriter.write("+\",\"+");
                }


            }
            bufferedWriter.write(";");
            bufferedWriter.newLine();
            bufferedWriter.write("\t}");
            bufferedWriter.newLine();
            bufferedWriter.write("}");
            bufferedWriter.flush();
        }
        catch (Exception e){

            logger.error("创建po失败");

        }
        finally {
            if(bufferedWriter!=null){
                bufferedWriter.close();
            }
          if(outputStreamWriter!=null){
              outputStreamWriter.close();
          }
          if(out!=null){
              out.close();
          }

        }

    }
}
