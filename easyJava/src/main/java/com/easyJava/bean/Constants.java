package com.easyJava.bean;

import com.easyJava.utils.PropertiesUtils;

import java.util.Properties;

public class Constants {
    //忽略表前缀
    public static boolean IGNORE_TABLE_PREFIX;
    //添加bean参数后缀
    public static String SUFFIX_BEAN_QUERY;
    public static String SUFFIX_BEAN_QUERY_FUZZY;
    public static String SUFFIX_BEAN_QUERY_TIME_START;
    public static String SUFFIX_BEAN_QUERY_TIME_END;

    //需要忽略的属性
    public static String IGNORE_BEAN_2JSON_FIELD;
    public static String IGNORE_BEAN_2JSON_EXPRESSION;
    public static String IGNORE_BEAN_2JSON_CLASS;
    //日期序列化
    public static String SERIALIZATION_BEAN_DATE_EXPRESSION;
    public static String SERIALIZATION_BEAN_DATE_CLASS;


    //日期反序列化
    public static String DESERIALIZATION_BEAN_DATE_EXPRESSION;
    public static String DESERIALIZATION_BEAN_DATE_CLASS;
    //路径
    public static String PATH_BASE;
    public static String PATH_JAVA="java";
    public static String PATH_RESOURCES;
    public static String PATH_PO;
    public static String PATH_UTILS;
    public static String PATH_ENUMS;
    public static String PATH_QUERY;

    //包
    public static String PACKAGE_BASE;
    public static String PACKAGE_PO;
    public static String PACKAGE_UTILS;
    public static String PACKAGE_ENUMS;
    public static String PACKAGE_QUERY;
    //注释——作者
    public static String AUTHOR_COMMENT;


    static{
        AUTHOR_COMMENT=PropertiesUtils.getString("author.comment");

        IGNORE_TABLE_PREFIX= Boolean.valueOf(PropertiesUtils.getString("ignore.table.prefix"));
        SUFFIX_BEAN_QUERY=PropertiesUtils.getString("suffix.bean.query");
        SUFFIX_BEAN_QUERY_FUZZY=PropertiesUtils.getString("suffix.bean.query.fuzzy");
        SUFFIX_BEAN_QUERY_TIME_START=PropertiesUtils.getString("suffix.bean.query.time.start");
        SUFFIX_BEAN_QUERY_TIME_END=PropertiesUtils.getString("suffix.bean.query.time.end");

        IGNORE_BEAN_2JSON_CLASS=PropertiesUtils.getString("ignore.bean.2json.class");
        IGNORE_BEAN_2JSON_EXPRESSION=PropertiesUtils.getString("ignore.bean.2json.expression");
        IGNORE_BEAN_2JSON_FIELD=PropertiesUtils.getString("ignore.bean.2json.field");

        SERIALIZATION_BEAN_DATE_EXPRESSION=PropertiesUtils.getString("serialization.bean.date.expression");
        SERIALIZATION_BEAN_DATE_CLASS=PropertiesUtils.getString("serialization.bean.date.class");

        DESERIALIZATION_BEAN_DATE_EXPRESSION=PropertiesUtils.getString("deserialization.bean.date.expression");
        DESERIALIZATION_BEAN_DATE_CLASS=PropertiesUtils.getString("deserialization.bean.date.class");

        PATH_JAVA="java";
        PATH_RESOURCES="resources";


        PATH_BASE=PropertiesUtils.getString("path.base");
        PATH_BASE=PATH_BASE+PATH_JAVA;

        PACKAGE_BASE=PropertiesUtils.getString("package.base");
        PACKAGE_PO=PACKAGE_BASE+"."+PropertiesUtils.getString("package.po");
        PACKAGE_UTILS=PACKAGE_BASE+"."+PropertiesUtils.getString("package.utils");
        PACKAGE_ENUMS=PACKAGE_BASE+"."+PropertiesUtils.getString("package.enums");
        PACKAGE_QUERY=PACKAGE_BASE+"."+PropertiesUtils.getString("package.query");


        PATH_PO=PATH_BASE+"/"+PACKAGE_PO.replace(".","/");
        PATH_UTILS=PATH_BASE+"/"+PACKAGE_UTILS.replace(".","/");
        PATH_ENUMS=PATH_BASE+"/"+PACKAGE_ENUMS.replace(".","/");
        PATH_QUERY=PATH_BASE+"/"+PACKAGE_QUERY.replace(".","/");



    }
    public final static String[] SQL_DATE_TIME_TYPES=new String[]{"datetime","timestamp"};
    public final static String[] SQL_DATE_TYPES=new String[]{"date"};
    public final static String[] SQL_DECIMAL_TYPE=new String[]{"decimal","double","float"};
    public final static String[] SQL_STRING_TYPE=new String[]{"char","varchar","text","mediumtext","longtext"};
    public final static String[] SQL_INTEGER_TYPE=new String[]{"int","tinyint","int unsigned","tinyint unsigned"};
    public final static String[] SQL_LONG_TYPE=new String[]{"bigint"};
    public final static String[] FIELD_IN_IGNORE=IGNORE_BEAN_2JSON_FIELD.split(",");
   public static void main(String[] args){
       System.out.println(PACKAGE_QUERY);
   }

}
