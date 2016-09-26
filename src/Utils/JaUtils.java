package Utils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.util.Scanner;

/**
 * Created by Administrator on 2016/9/14.
 */
public class JaUtils {

    public static void main(String[] args) throws IOException {


//        filterEmtyObject("D:\\工作目录\\chufa出发！英雄基地\\RES\\修改\\2\\txt\\il\\jp\\Assembly-CSharp.dll.il_out");

        String ja_path = "D:\\工作目录\\xingsheng兴盛帝国\\res\\提取的\\txt\\app\\il\\jp\\Assembly-CSharp.dll.il_out";
//        filterEmtyObject(ja_path);
//        filterIsnotJa("D:\\工作目录\\xingsheng兴盛帝国\\res\\提取的\\Resource\\txt\\bin\\Data\\Managed\\il\\jp\\Assembly-CSharp.dll.il_out");
        filterIsnotJa_jsonArray(ja_path);

    }

    /**
     * 将文件打开以字符串返回
     */
    public static String fileToString(String path)  {
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File(path),"utf-8");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        StringBuilder sb = new StringBuilder();

        while (scanner.hasNext()){
            sb.append(scanner.nextLine());
        }
        scanner.close();
        return sb.toString();
    }

    /**
     * 删掉没有翻译的元素
     * @param ja_path
     */
    public static void filterEmtyObject(String ja_path) throws IOException {
        boolean jo_flag = true;
        try {
            JSONObject json = new JSONObject(fileToString(ja_path));
            JSONArray items = json.getJSONArray("items");
            for (int i = 0; i < items.length(); i++) {
                JSONObject jo =  items.getJSONObject(i);
                String cz = jo.getString("cz");
                System.out.println("111  :"+cz.equals(""));
                if (cz.equals("")){
                    items.remove(i);
                    i--;
                }
            }
            BufferedWriter bw = new BufferedWriter(new FileWriter(ja_path+"_"));
            bw.write(json.toString());
            bw.flush();
            bw.close();

        }catch (Exception e){
            JSONArray json = new JSONArray(fileToString(ja_path));
            for (int i = 0; i < json.length(); i++) {
                JSONObject jo =  json.getJSONObject(i);
                String cz = jo.getString("zh");
                String ori = jo.getString("ori");
                if (cz.equals("")||ori.equals(cz)){
                    json.remove(i);
                    i--;
                }
            }
            BufferedWriter bw = new BufferedWriter(new FileWriter(ja_path+"_"));
            bw.write(json.toString());
            bw.flush();
            bw.close();
        }


    }

    /**
     * 过滤掉不是日文的元素
     * @param ja_path
     * @throws IOException
     */
    public static void filterIsnotJa(String ja_path) throws IOException{
        JSONArray json = new JSONArray(fileToString(ja_path));
        for (int i = 0; i < json.length(); i++) {
            JSONObject jo =  json.getJSONObject(i);
            String ori = jo.getString("ori");
            if (!whatLanguage.containJp(ori)){
                json.remove(i);
                i--;
            }
        }
        BufferedWriter bw = new BufferedWriter(new FileWriter(ja_path+"_"));
        bw.write(json.toString());
        bw.flush();
        bw.close();
    }

    /**
     * 过滤掉不是日文的元素  针对jasonarray的
     * @param ja_path
     * @throws IOException
     */
    public static void filterIsnotJa_jsonArray(String ja_path) throws IOException{
        JSONArray json = new JSONArray(fileToString(ja_path));
        for (int i = 0; i < json.length(); i++) {
            JSONObject jo =  json.getJSONObject(i);
            String ori = jo.getString("ori");
            if (!whatLanguage.containJp(ori)){
                json.remove(i);
                i--;
            }
        }
        BufferedWriter bw = new BufferedWriter(new FileWriter(ja_path+"_"));
        bw.write(json.toString());
        bw.flush();
        bw.close();
    }
}
