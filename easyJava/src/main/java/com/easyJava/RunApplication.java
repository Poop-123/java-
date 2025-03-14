package com.easyJava;

import com.easyJava.bean.TableInfo;
import com.easyJava.builder.BuildBase;
import com.easyJava.builder.BuildPo;
import com.easyJava.builder.BuilderTable;

import java.io.IOException;
import java.util.List;


public class RunApplication {
    public static void main(String[] args) throws IOException {
        //创建表
        List<TableInfo> tableInfoList=BuilderTable.getTables();
        //
        BuildBase.execute();
        for(TableInfo tableInfo:tableInfoList){
            BuildPo.execute(tableInfo);
        }
    }
}