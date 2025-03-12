package com.easyJava.builder;

import com.easyJava.bean.Constants;
import com.easyJava.bean.FieldInfo;
import com.easyJava.bean.TableInfo;
import com.easyJava.utils.PropertiesUtils;
import com.easyJava.utils.StringUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BuilderTable {
    private static final Logger logger= LoggerFactory.getLogger(BuilderTable.class);
    private static Connection conn=null;
    private static final String SQL_SHOW_TABLE_STATUS="show table status";
    private static final String SQL_SHOW_TABLE_FIELDS="show full fields from %s";
    static {
        String driverName= PropertiesUtils.getString("db.driver.name");
        String url=PropertiesUtils.getString("db.url");
        String user=PropertiesUtils.getString("db.username");
        String password=PropertiesUtils.getString("db.password");
        try {
            Class.forName(driverName);
            conn= DriverManager.getConnection(url,user,password);
        } catch (Exception e) {
            logger.error("数据库连接失败",e);
        }
    }
    public static void  getTables(){
        PreparedStatement ps=null;
        ResultSet tableResult=null;
        List<TableInfo> tableInfoList=new ArrayList<>();

        try{
            ps=conn.prepareStatement(SQL_SHOW_TABLE_STATUS);
            tableResult=ps.executeQuery();
            while(tableResult.next()){
                String tableName=tableResult.getString("Name");
                String comment=tableResult.getString("Comment");
                String beanName=tableName;
                if(Constants.IGNORE_TABLE_PREFIX){
                    beanName=beanName.substring(beanName.indexOf("_")+1);
                }
                beanName=processField(beanName,true);
                TableInfo tableInfo=new TableInfo();
                tableInfo.setTableName(tableName);
                tableInfo.setBeanName(beanName);
                tableInfo.setComment(comment);
                tableInfo.setBeanParamName(beanName+Constants.SUFFIX_BEAN_PARAM);
                List<FieldInfo> fieldInfoList=readFieldInfo(tableInfo);
                tableInfo.setFieldList(fieldInfoList);
                tableInfoList.add(tableInfo);
                }
        }
        catch (Exception e){
            e.printStackTrace();
            logger.error("读取表失败");
        }
        finally {
            if (tableResult!=null){
                try {
                    tableResult.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if(ps!=null){
                try {
                    ps.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if(conn!=null){
                try {
                    conn.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }

        }

    }
    private static List<FieldInfo> readFieldInfo(TableInfo tableInfo) {
        PreparedStatement ps = null;
        ResultSet fieldResult = null;
        List<FieldInfo> fieldInfoList = new ArrayList<>();

        try {
            ps = conn.prepareStatement(String.format(SQL_SHOW_TABLE_FIELDS,tableInfo.getTableName()));
            fieldResult = ps.executeQuery();
            while (fieldResult.next()) {
                String field = fieldResult.getString("Field");
                String type = fieldResult.getString("Type");
                String extra=fieldResult.getString("Extra");
                String comment=fieldResult.getString("Comment");

                if(type.indexOf("(")>0){
                    type=type.substring(0,type.indexOf("("));
                }
                String propertyName=processField(field,false);
                FieldInfo fieldInfo=new FieldInfo();
                fieldInfoList.add(fieldInfo);
                fieldInfo.setFieldName(field);
                fieldInfo.setComment(comment);
                fieldInfo.setAutoIncrement("auto_increment".equalsIgnoreCase(extra)?true:false);
                fieldInfo.setSqlType(type);
                fieldInfo.setPropertyName(propertyName);
                fieldInfo.setJavaType(processJavaType(type));

                if(ArrayUtils.contains(Constants.SQL_DATE_TIME_TYPES,type)){
                    tableInfo.setHaveDateTime(true);
                }
                if(ArrayUtils.contains(Constants.SQL_DATE_TYPES,type)){
                    tableInfo.setHaveDate(true);
                }
                if(ArrayUtils.contains(Constants.SQL_DECIMAL_TYPE,type)){
                    tableInfo.setHaveBigDecimal(true);
                }
            }
        } catch (Exception e) {
            logger.error("读取表2失败");
        } finally {
            if (fieldResult != null) {
                try {
                    fieldResult.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }



        }
        return fieldInfoList;
    }
    private static String processField(String field,Boolean upperCaseFirstLetter){
        StringBuffer sb=new StringBuffer();
        String[] fields=field.split("_");
        sb.append(upperCaseFirstLetter? StringUtils.upperCaseFirstLetter(fields[0]):fields[0]);

        for(int i=1,len=fields.length;i<len;i++){
            sb.append(StringUtils.upperCaseFirstLetter(fields[i]));
        }
        return sb.toString();
    }
    private static String processJavaType(String type){
        if(ArrayUtils.contains(Constants.SQL_INTEGER_TYPE,type)){
            return "Integer";
        }
        else if(ArrayUtils.contains(Constants.SQL_LONG_TYPE,type)){
            return "Long";
        }
        else if(ArrayUtils.contains(Constants.SQL_STRING_TYPE,type)){
            return "String";
        }
        else if(ArrayUtils.contains(Constants.SQL_DATE_TIME_TYPES,type)){
            return "Date";
        }
        else if(ArrayUtils.contains(Constants.SQL_DECIMAL_TYPE,type)){
            return "BigDecimal";
        }
        else{
            throw new RuntimeException("无法识别的类型"+type);
        }

    }
}
