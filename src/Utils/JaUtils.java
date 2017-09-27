package Utils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.util.Scanner;

/**
 * Created by Administrator on 2016/9/14.
 */
public class JaUtils {

    static Utils utils = new Utils();

    public static void main(String[] args) throws IOException {


//        filterEmtyObject("D:\\工作目录\\chufa出发！英雄基地\\RES\\修改\\2\\txt\\il\\jp\\Assembly-CSharp.dll.il_out");

        String ja_path = "E:\\2222\\shouren兽人探险队\\res\\修改\\测试1修改\\jp";
        String enFile = "C:\\Users\\appchina\\Desktop\\test\\update\\en";
        String zhFile = "C:\\Users\\appchina\\Desktop\\test\\update\\zh";
//        filterEmtyObject(ja_path);
//        filterIsnotJa("F:\\汉化\\gangtie钢铁之躯\\res\\提取\\il\\jp\\Assembly-CSharp.dll.il_out");
//        filterIsnotJa_jsonArray(ja_path);
//        jiaoYanJsonGeshi(ja_path);
//        jiaoyanfuhao(ja_path);
        jiaoHuanOriCz(ja_path);
//        jiaoHuanCzCz(enFile,zhFile);
//        jiaoHuanCzCz_array(enFile,zhFile);
//        jiaoHuanCzCz_dir(enFile,zhFile);
//        quJsonGeShi(ja_path);

    }

// 校验 cz和 ori 中的符号是不是一样的数目 jsonobject
    public static void jiaoyanfuhao(String path){
        JSONObject jo = new JSONObject(fileToString(path));
        JSONArray items = jo.getJSONArray("items");
        for (int i = 0; i < items.length(); i++) {
            JSONObject sJo = items.getJSONObject(i);
            String ori = sJo.getString("ori");
            String cz = sJo.getString("cz");
            if (ori.split("\'").length != cz.split("\'").length  ){
                System.out.println("ori--引号 : "+ori);
            }
            if (ori.split(",").length!=cz.split(",").length){
                System.out.println("ori--逗号 : "+ori);

            }
            if (ori.split(";").length!=cz.split(";").length){
                System.out.println("ori--分号 : "+ori);

            }
            if (ori.split("\\(").length!=cz.split("\\(").length){
                System.out.println("ori--左括号 : "+ori);

            }
            if (ori.split("\\)").length!=cz.split("\\)").length){
                System.out.println("ori--右括号 : "+ori);

            }
        }


    }


    /**
     * 将格式化后的json文本,去格式化
     * @param path : json文件路径
     */
    public static void quJsonGeShi(String path){
        JSONArray ja = new JSONArray(fileToString(path));
        try {
            Utils.writeNewFile(path+"_n",ja.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //交换ori和cz 针对jsonobject
    public static void jiaoHuanOriCz(String dirs){
        File[] files = bianLiDir(dirs);
        for (File file:files) {
//            JSONObject paJo = new JSONObject(fileToString(file.getAbsolutePath()));
//            JSONArray items = paJo.getJSONArray("items");
            JSONArray items = new JSONArray(fileToString(file.getAbsolutePath()));

            for (int i = 0; i < items.length(); i++) {
                JSONObject jo = items.getJSONObject(i);
                String ori = jo.getString("ori");
                jo.put("cz",ori);
            }
            try {
                Utils.writeNewFile(file.getAbsolutePath() + "_n", items.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    //升级是交换两个json的cz
    public static void jiaoHuanCzCz(String enFile,String zhFile){
        JSONObject paJo = new JSONObject(fileToString(enFile));
        JSONArray items = paJo.getJSONArray("items");
        JSONObject enPaJo = new JSONObject(fileToString(zhFile));
        JSONArray enIitems = enPaJo.getJSONArray("items");
        for (int i = 0; i < items.length(); i++) {
            JSONObject jo = items.getJSONObject(i);
            String ori = jo.getString("ori");
            for (int j = 0; j < enIitems.length(); j++) {

                JSONObject enJo = enIitems.getJSONObject(j);
                String enOri = enJo.getString("ori");
                if (enOri.equals(ori)){
                    String cz = enJo.getString("cz");
                    jo.put("cz",cz);
                    break;
                }
            }

        }
        try {
            Utils.writeNewFile(enFile + "_n", paJo.toString());
            new File(enFile).delete();
            new File(enFile + "_n").renameTo(new File(enFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //升级是交换两个jsonArray 的  zh
    public static void jiaoHuanCzCz_array(String enFile,String zhFile){
        JSONArray items = new JSONArray(fileToString(enFile));

        JSONArray enIitems = new JSONArray(fileToString(zhFile));
        for (int i = 0; i < items.length(); i++) {
            JSONObject jo = items.getJSONObject(i);
            String ori = jo.getString("ori");
            for (int j = 0; j < enIitems.length(); j++) {

                JSONObject enJo = enIitems.getJSONObject(j);
                String enOri = enJo.getString("ori");
                if (enOri.equals(ori)){
                    String cz = enJo.getString("zh");
                    jo.put("zh",cz);
                    break;
                }
            }

        }
        try {
            Utils.writeNewFile(enFile + "_n", items.toString());
//            new File(enFile).delete();
//            new File(enFile + "_n").renameTo(new File(enFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //升级是交换两个json的zh
    public static void jiaoHuanzhzh(String enFile,String zhFile){
        System.out.println(enFile);
        JSONObject paJo = new JSONObject(fileToString(enFile));
        JSONArray items = paJo.getJSONArray("items");
        JSONObject zhPaJo = new JSONObject(fileToString(zhFile));
        JSONArray zhIitems = zhPaJo.getJSONArray("items");
        for (int i = 0; i < items.length(); i++) {
            JSONObject jo = items.getJSONObject(i);
            String ori = jo.getString("ori");
            for (int j = 0; j < zhIitems.length(); j++) {

                JSONObject zhJo = zhIitems.getJSONObject(j);
                String zhOri = zhJo.getString("ori");
                if (zhOri.equals(ori)){
                    String cz = zhJo.getString("cz");
                    jo.put("cz",cz);
                    break;
                }
            }

        }
        try {
            Utils.writeNewFile(enFile + "_n", paJo.toString());
//            new File(enFile).delete();
//            new File(enFile + "_n").renameTo(new File(enFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    // 升级文本,两个文件夹中的  文本 的json的cz
    public static void jiaoHuanCzCz_dir(String enDir,String zhDir){
        File zhDir_files = new File(zhDir);
        File enDir_files = new File(enDir);
        File[] zhfiles = zhDir_files.listFiles();
        File[] enfiles = enDir_files.listFiles();
        for (File enFile :
                enfiles) {
            for (File zhFile:
                    zhfiles) {
                // 这个是json array的升级
                jiaoHuanzhzh(enFile.getAbsolutePath(),zhFile.getAbsolutePath());
//                jiaoHuanCzCz(enFile.getAbsolutePath(),zhFile.getAbsolutePath());
            }
        }



    }
    
    
    
//    校验json格式
    public static void jiaoYanJsonGeshi(String dirs){
        File[] files = bianLiDir(dirs);
        for (File file:
             files) {
            try{
                JSONObject json = new JSONObject(fileToString(file.getAbsolutePath()));
            }catch (Exception e){
                try {
                    JSONArray json = new JSONArray(fileToString(file.getAbsolutePath()));
                }catch (Exception e1){
                    System.out.println(file.getAbsolutePath());
                    e1.printStackTrace();
                }

            }
        }


    }


    /**
     * 遍历文件夹并返回一个文件列表
     */

    public static File[] bianLiDir(String dir){
        File files = new File(dir);
        return files.listFiles();
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
