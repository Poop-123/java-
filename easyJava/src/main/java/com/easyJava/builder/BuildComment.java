package com.easyJava.builder;

import com.easyJava.bean.Constants;
import com.easyJava.utils.DateUtils;
import com.easyJava.utils.PropertiesUtils;

import java.io.BufferedWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BuildComment {
    public static void createClassComment(BufferedWriter bufferedWriter,String classComment) throws IOException {
        bufferedWriter.write("/**");
        bufferedWriter.newLine();
        bufferedWriter.write("  * @Description:"+classComment);
        bufferedWriter.newLine();
        bufferedWriter.write("  * @Author:"+ Constants.AUTHOR_COMMENT);
        bufferedWriter.newLine();
        bufferedWriter.write("  * @Date:"+ DateUtils.format(new Date(),DateUtils._YYYYMMDD));
        bufferedWriter.newLine();
        bufferedWriter.write("  */");
        bufferedWriter.newLine();
    }
    public static void createFieldComment(BufferedWriter bufferedWriter,String fieldComment) throws IOException {
        bufferedWriter.write("\t/**");
        bufferedWriter.newLine();
        bufferedWriter.write("\t * "+fieldComment);
        bufferedWriter.newLine();
        bufferedWriter.write("\t */");
        bufferedWriter.newLine();

    }
    public static void createMethodComment(BufferedWriter bufferedWriter,String methodComment) throws IOException {
        bufferedWriter.write("\t/**");
        bufferedWriter.newLine();
        bufferedWriter.write("\t * "+methodComment);
        bufferedWriter.newLine();
        bufferedWriter.write("\t */");
        bufferedWriter.newLine();
    }
}
