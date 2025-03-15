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
import java.util.Map;

public class BuildMapper {
    private static final Logger logger= LoggerFactory.getLogger(BuildMapper.class);
    public static void execute(TableInfo tableInfo) throws IOException {
        File folder=new File(Constants.PATH_MAPPER);
        if (!folder.exists()){
            folder.mkdirs();
        }
        File mapperFile=new File(folder,tableInfo.getBeanName()+Constants.SUFFIX_MAPPER+".java");
        OutputStream out=null;
        OutputStreamWriter outputStreamWriter=null;
        BufferedWriter bufferedWriter=null;
        try{
            out= Files.newOutputStream(mapperFile.toPath());
            outputStreamWriter=new OutputStreamWriter(out);
            bufferedWriter=new BufferedWriter(outputStreamWriter);
            bufferedWriter.write("package "+Constants.PACKAGE_MAPPER+";");
            bufferedWriter.newLine();
            bufferedWriter.newLine();

            bufferedWriter.write("import org.apache.ibatis.annotations.Param;");
            bufferedWriter.newLine();

            bufferedWriter.newLine();
            BuildComment.createClassComment(bufferedWriter,tableInfo.getComment());
            bufferedWriter.write("public interface "+tableInfo.getBeanName()+Constants.SUFFIX_MAPPER+"<T,P> extends BaseMapper{");
            bufferedWriter.newLine();

            Map<String,List<FieldInfo>> keyIndexMap=tableInfo.getKeyIndexMap();

            for(Map.Entry<String,List<FieldInfo>> entry: keyIndexMap.entrySet()){
                List<FieldInfo> keyFieldInfoList=entry.getValue();

                Integer index=0;
                StringBuilder methodName=new StringBuilder();
                StringBuilder methodParms=new StringBuilder();
                for(FieldInfo fieldInfo:keyFieldInfoList){
                    index++;
                    methodParms.append("@Param(\""+fieldInfo.getPropertyName()+"\") "+fieldInfo.getJavaType()+" "+fieldInfo.getPropertyName());
                    methodName.append(StringUtils.upperCaseFirstLetter(fieldInfo.getPropertyName()));
                    if(index<keyFieldInfoList.size()){
                        methodName.append("And");
                        methodParms.append(", ");
                    }
                }
                BuildComment.createFieldComment(bufferedWriter,"根据"+methodName+"查询");
                bufferedWriter.write("\t T selectBy"+methodName+"("+methodParms+");");


                bufferedWriter.newLine();
                bufferedWriter.newLine();

                BuildComment.createFieldComment(bufferedWriter,"根据"+methodName+"更新");
                bufferedWriter.write("\t Integer updateBy"+methodName+"(@Param(\"bean\") T t, "+methodParms+");");
                bufferedWriter.newLine();
                bufferedWriter.newLine();

                BuildComment.createFieldComment(bufferedWriter,"根据"+methodName+"删除");
                bufferedWriter.write("\t Integer deleteBy"+methodName+"("+methodParms+");");
                bufferedWriter.newLine();
                bufferedWriter.newLine();


            }



            bufferedWriter.write("}");


            bufferedWriter.flush();
        }
        catch (Exception e){

            logger.error("创建Mapper失败");

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
