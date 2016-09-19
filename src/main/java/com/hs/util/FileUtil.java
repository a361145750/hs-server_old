package com.hs.util;

import com.hs.common.model.BaseData;

import java.io.*;
import java.util.Properties;

/**
 * Created by work_tl on 2016/4/23.
 */
public class FileUtil {

    public static String DICT_PATH = "/jsp/common/dict/";

    public static String FILE_UPLOAD_PATH = "/fileupload/image/";

    static {
        Properties pop = new Properties();
        String path = FileUtil.class.getClassLoader().getResource("/fileUpload.properties").getPath();
        try ( FileInputStream in = new FileInputStream(new File(path))){
            pop.load(in);
            FILE_UPLOAD_PATH = pop.getProperty("imgFilePath");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getDictByName(String path,String name){
        if(!StringUtil.isNotEmpty(path) && !StringUtil.isNotEmpty(name)){
            String file = new StringBuffer(path).append(DICT_PATH).append(name).append(".json").toString();
            return readFile(file);
        }
        return "";
    }

    public static String readFile(String fileName){
        if(StringUtil.isNotEmpty(fileName)){
            File file = new File(fileName);
            if(file.exists()){
                try (FileInputStream fi = new FileInputStream(file);
                    InputStreamReader in = new InputStreamReader(fi,"UTF-8");
                    BufferedReader  reader = new BufferedReader(in) ){
                    StringBuilder sb = new StringBuilder();
                    String temp = reader.readLine();
                    while (temp!=null){
                        sb.append(temp).append("\r\n");
                        temp = reader.readLine();
                    }
                    return sb.toString();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return "";
    }

    public static void writeFile(String fileName, byte[] content){
        if(StringUtil.isNotEmpty(fileName) && content!=null){
            File file = new File(fileName,"UTF-8");
            if(!file.getParentFile().exists()){
                file.getParentFile().mkdirs();
            }
            try (FileOutputStream out = new FileOutputStream(file)){
                out.write(content);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void copyFile(String src,String dest){
        FileInputStream in=null;
        FileOutputStream out=null;
        try {
            in=new FileInputStream(src);
            File file=new File(dest);
            if(!file.getParentFile().exists())
                file.getParentFile().mkdirs();
            if(!file.exists())
                file.createNewFile();
            out=new FileOutputStream(file);
            int c;
            byte buffer[]=new byte[1024];
            while((c=in.read(buffer))!=-1){
                for(int i=0;i<c;i++)
                    out.write(buffer[i]);
            }
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            try {
                in.close();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static String creatAttchFileName(BaseData data, String fileName){
        String customId = data.getInputString("customId");
        String recordId = data.getInputString("recordId");
        String ext = fileName.substring(fileName.lastIndexOf("."));

        return  new StringBuilder(FILE_UPLOAD_PATH).append("/").append(customId).append("/").append(recordId).append("/").append(NumberSequenceUtil.getDateString()).append("/").append(NumberSequenceUtil.getTimeString()+NumberSequenceUtil.getRadomNum()+ext).toString();

    }

    public static void deleteAttchByCustom(BaseData data){
        String customId = data.getInputString("customId");
        String filePath = new StringBuilder(FILE_UPLOAD_PATH).append("/").append(customId).toString();
        File file = new File(filePath);
        if(!file.exists())
            file.delete();
    }

    public static void deleteAttchByRecord(BaseData data){
        String customId = data.getInputString("customId");
        String recordId = data.getInputString("recordId");
        String filePath = new StringBuilder(FILE_UPLOAD_PATH).append("/").append(customId).append("/").append(recordId).toString();
        File file = new File(filePath);
        if(!file.exists())
            file.delete();
    }

}
