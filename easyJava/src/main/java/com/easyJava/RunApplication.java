package com.easyJava;

import com.easyJava.bean.TableInfo;
import com.easyJava.builder.BuilderTable;

import java.util.List;


public class RunApplication {
    public static void main(String[] args) {
        List<TableInfo> tableInfoList=BuilderTable.getTables();
    }
}