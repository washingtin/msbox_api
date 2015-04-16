package com.msbox.api.task;

import com.msbox.api.common.constants.ActiveConstant;
import org.apache.commons.lang.StringUtils;

import java.io.*;

/**
 * Created by IntelliJ IDEA.
 * Description: ActiveMapUtil
 * Author: guost
 * Date: 2015-04-01 20:48
 */
public class ActiveMapUtil {

//    public static Map<String,Map<String,String>> buIdMap = new HashMap<String,Map<String,String>>();
//    public static Map<String,String> buCodeMap = new HashMap<String,String>();

    private static final String path = "conf/active.properties";
    private static String filePath = null;

    private void getPath(){
        filePath = Thread.currentThread().getContextClassLoader().getResource("/")+ path;
        filePath = filePath.substring(5);
        System.out.println("filePath=========" + filePath);
    }

    private void readFile(String path) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(new File(path))));
            String line = reader.readLine();
            while (line != null) {
                if (StringUtils.isNotEmpty(line)) {
                    String[] strs = line.split("\\|\\|");
                    System.out.println(strs[0] + "--" + strs[1] + "--" + strs[2]);
                    ActiveConstant.buCodeMap.put(strs[1],strs[2]);
                    ActiveConstant.buIdMap.put(strs[0],ActiveConstant.buCodeMap);
                }
                line = reader.readLine();
            }
        } catch (FileNotFoundException e) {
            e.getStackTrace();
        } catch (IOException e) {
            e.getStackTrace();
        }finally{
            if(reader != null){
                try {
                    reader.close();
                } catch (IOException e) {
                    System.out.println(" readFile()关闭io流reader：  path=" + path + "  错误信息  " + e.getStackTrace());
                }
            }
        }
    }

    public void init(){
        try{
            getPath();
            readFile(filePath);
        }catch(Throwable ex){
            ex.printStackTrace();
        }
    }

    public ActiveMapUtil(){

    }
}
