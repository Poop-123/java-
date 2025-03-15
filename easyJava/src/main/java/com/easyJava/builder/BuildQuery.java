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
import java.util.ArrayList;
import java.util.List;

public class BuildQuery {
    private static final Logger logger= LoggerFactory.getLogger(BuildQuery.class);
    public static void execute(TableInfo tableInfo) throws IOException {
        File folder=new File(Constants.PATH_QUERY);
        if (!folder.exists()){
            folder.mkdirs();
        }
        File queryFile=new File(folder,tableInfo.getBeanName()+Constants.SUFFIX_BEAN_QUERY+".java");
        OutputStream out=null;
        OutputStreamWriter outputStreamWriter=null;
        BufferedWriter bufferedWriter=null;
        try{
            out= Files.newOutputStream(queryFile.toPath());
            outputStreamWriter=new OutputStreamWriter(out);
            bufferedWriter=new BufferedWriter(outputStreamWriter);
            bufferedWriter.write("package "+Constants.PACKAGE_QUERY+";");
            bufferedWriter.newLine();
            bufferedWriter.newLine();

            if(tableInfo.getHaveDate()||tableInfo.getHaveDateTime()){
                bufferedWriter.write("import java.util.Date;");
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

            bufferedWriter.newLine();
            BuildComment.createClassComment(bufferedWriter,tableInfo.getComment());
            bufferedWriter.write("public class "+tableInfo.getBeanName()+Constants.SUFFIX_BEAN_QUERY+"{");
            bufferedWriter.newLine();

            List<FieldInfo> extendList=new ArrayList<>();
            for(FieldInfo fieldInfo:tableInfo.getFieldList()){
                if(fieldInfo.getComment()!=null){
                    BuildComment.createFieldComment(bufferedWriter,fieldInfo.getComment());

                }
                bufferedWriter.write("\t private "+fieldInfo.getJavaType()+" "+fieldInfo.getPropertyName()+";");
                bufferedWriter.newLine();
                bufferedWriter.newLine();
                //String类型
                if(ArrayUtils.contains(Constants.SQL_STRING_TYPE,fieldInfo.getSqlType())){
                    bufferedWriter.write("\t private "+fieldInfo.getJavaType()+" "+fieldInfo.getPropertyName()+Constants.SUFFIX_BEAN_QUERY_FUZZY+";");
                    bufferedWriter.newLine();
                    bufferedWriter.newLine();

                    FieldInfo fuzzyField=new FieldInfo();
                    fuzzyField.setJavaType(fieldInfo.getJavaType());
                    fuzzyField.setPropertyName(fieldInfo.getPropertyName()+Constants.SUFFIX_BEAN_QUERY_FUZZY);
                    extendList.add(fuzzyField);
                }
                //Date&DateTime类型
                if(ArrayUtils.contains(Constants.SQL_DATE_TYPES,fieldInfo.getSqlType())||ArrayUtils.contains(Constants.SQL_DATE_TIME_TYPES,fieldInfo.getSqlType())){
                    bufferedWriter.write("\t private String"+" "+fieldInfo.getPropertyName()+Constants.SUFFIX_BEAN_QUERY_TIME_START+";");
                    bufferedWriter.newLine();
                    bufferedWriter.newLine();
                    bufferedWriter.write("\t private String"+" "+fieldInfo.getPropertyName()+Constants.SUFFIX_BEAN_QUERY_TIME_END+";");
                    bufferedWriter.newLine();
                    bufferedWriter.newLine();
                    FieldInfo startField=new FieldInfo();
                    startField.setJavaType(fieldInfo.getJavaType());
                    startField.setPropertyName(fieldInfo.getPropertyName()+Constants.SUFFIX_BEAN_QUERY_FUZZY);
                    extendList.add(startField);
                    FieldInfo endField=new FieldInfo();
                    endField.setJavaType(fieldInfo.getJavaType());
                    endField.setPropertyName(fieldInfo.getPropertyName()+Constants.SUFFIX_BEAN_QUERY_FUZZY);
                    extendList.add(endField);

                }
            }
            List<FieldInfo> fieldInfoList=tableInfo.getFieldList();
            fieldInfoList.addAll(extendList);
            for(FieldInfo fieldInfo:fieldInfoList){
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
