package com.easyJava.bean;

import com.easyJava.utils.PropertiesUtils;

import java.util.Properties;

public class Constants {
    public static boolean IGNORE_TABLE_PREFIX;
    public static String SUFFIX_BEAN_PARAM;
    public static String PATH_BASE;
    public static String PATH_JAVA="java";
    public static String PATH_RESOURCES;
    public static String PACKAGE_BASE;
    public static String PATH_PO;
    public static String PACKAGE_PO;
    public static String AUTHOR_COMMENT;

    static{
        AUTHOR_COMMENT=PropertiesUtils.getString("author.comment");
        PATH_JAVA="java";
        PATH_RESOURCES="resources";
        IGNORE_TABLE_PREFIX= Boolean.valueOf(PropertiesUtils.getString("ignore.table.prefix"));
        SUFFIX_BEAN_PARAM=PropertiesUtils.getString("suffix.bean.param");


        PATH_BASE=PropertiesUtils.getString("path.base");
        PATH_BASE=PATH_BASE+"/"+PATH_JAVA+"/"+PropertiesUtils.getString("package.base");
        PATH_BASE=PATH_BASE.replace(".","/");

        PATH_PO=PATH_BASE+"/"+PropertiesUtils.getString("package.po").replace(".","/");


        PACKAGE_BASE=PropertiesUtils.getString("package.base");
        PACKAGE_PO=PACKAGE_BASE+"."+PropertiesUtils.getString("package.po");




    }
    public final static String[] SQL_DATE_TIME_TYPES=new String[]{"datetime","timestamp"};
    public final static String[] SQL_DATE_TYPES=new String[]{"date"};
    public final static String[] SQL_DECIMAL_TYPE=new String[]{"decimal","double","float"};
    public final static String[] SQL_STRING_TYPE=new String[]{"char","varchar","text","mediumtext","longtext"};
    public final static String[] SQL_INTEGER_TYPE=new String[]{"int","tinyint","int unsigned","tinyint unsigned"};
    public final static String[] SQL_LONG_TYPE=new String[]{"bigint"};
//   public static void main(String[] args){
//       System.out.println(PATH_BASE);
//       System.out.println(PATH_PO);
//   }

}
