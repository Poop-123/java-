package com.easyJava.builder;

import com.easyJava.bean.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class BuildBase {
    private static Logger logger= LoggerFactory.getLogger(BuildBase.class);
    public static void execute() throws IOException {
        List<String> headInfoList=new ArrayList<>();
        headInfoList.add("package "+Constants.PACKAGE_ENUMS+";");
        build(headInfoList,"DateTimePatternEnum",Constants.PATH_ENUMS);
        headInfoList.clear();
        headInfoList.add("package "+Constants.PACKAGE_UTILS+";");
        build(headInfoList,"DateUtils", Constants.PATH_UTILS);

    }
    public static void build(List<String> headInfoList,String fileName, String outPutPath) throws IOException {
        File folder=new File(outPutPath);
        if(!folder.exists()){
            folder.mkdirs();
        }
        File javaFile=new File(outPutPath,fileName+".java");
        OutputStream outputStream=null;
        OutputStreamWriter outputStreamWriter=null;
        BufferedWriter bufferedWriter=null;

        InputStream inputStream=null;
        InputStreamReader inputStreamReader=null;
        BufferedReader bufferedReader=null;
        try{
            outputStream=new FileOutputStream(javaFile);
            outputStreamWriter=new OutputStreamWriter(outputStream);
            bufferedWriter=new BufferedWriter(outputStreamWriter);

            String templatePath=BuildBase.class.getClassLoader().getResource("template/"+fileName+".txt").getPath();
            inputStream=new FileInputStream(templatePath);
            inputStreamReader=new InputStreamReader(inputStream);
            bufferedReader=new BufferedReader(inputStreamReader);
            for(String headInfo:headInfoList){
                bufferedWriter.write(headInfo);

            }
            bufferedWriter.newLine();
            bufferedWriter.newLine();
            String lineInfo=null;
            while((lineInfo=bufferedReader.readLine())!=null){
                bufferedWriter.write(lineInfo);
                bufferedWriter.newLine();
            }
            bufferedWriter.newLine();
            bufferedWriter.flush();
        }
        catch (Exception e){
            logger.error("生成基础类失败：{}",fileName);
            e.printStackTrace();
        }
        finally {
            if(bufferedWriter!=null){
                bufferedReader.close();
            }
            if(inputStreamReader!=null){
                inputStreamReader.close();
            }
            if(inputStream!=null){
                inputStream.close();
            }
            if(bufferedWriter!=null){
                bufferedWriter.close();
            }
            if(outputStreamWriter!=null){
                outputStreamWriter.close();
            }
            if(outputStream!=null){
                outputStream.close();
            }
        }
    }

}
