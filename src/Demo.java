import Utils.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;

/**
 * Created by appchina on 2016/11/14.
 */
public class Demo {

    public static void main(String[] args) {
//        System.out.println(a(new byte[] { -105, -113, -111, -112, 101 }));
        try {
            chili_youjiu("F:\\汉化\\youjiu悠久的空岛\\res\\修改\\测试5\\libMyGame.so(1,10f8630,38bd4)_.txt");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void chili_youjiu(String path) throws Exception {


        JSONObject parent_jo = new JSONObject(Utils.fileToString(path));
        JSONArray items = parent_jo.getJSONArray("items");
        JSONObject out_pa_jo = new JSONObject();
        out_pa_jo.put("offset",17794608);
        JSONArray jsonArray = new JSONArray();
        //文本是不是空的
        boolean empty_flag = true;
        for (int i = 0; i < items.length(); i++) {
            JSONObject jo = items.getJSONObject(i);
            String fanyi = jo.getString("cz");
            int blen = jo.getInt("blen");
            String ori = jo.getString("ori");
            if (fanyi.equals("")) {
                continue;
            }
            if (isContainFuhao(fanyi)){
                if (!fanyi.substring(fanyi.lastIndexOf(",")).equals(ori.substring(ori.lastIndexOf(",")))){
                    System.out.println(fanyi);
                }else {
                    System.out.println(fanyi.substring(fanyi.lastIndexOf(",")));
                }
//                String zhengwen = fanyi.substring(0,fanyi.lastIndexOf(","));
//                String houzhui = fanyi.substring(fanyi.lastIndexOf(","));
//                zhengwen = bu_kong_ge(blen-houzhui.length()-zhengwen.getBytes("utf-8").length,zhengwen);
//                fanyi = zhengwen+houzhui;
//                System.out.println(fanyi+"  "+(fanyi.getBytes("utf-8").length==blen));
            }
//            if (fanyi.getBytes("utf-8").length!=blen){
//                fanyi = bu_kong_ge(blen-fanyi.getBytes("utf-8").length,fanyi);
//            }
            jo.put("cz",fanyi);
            jsonArray.put(jo);

        }
        out_pa_jo.put("items",jsonArray);
        Utils.writeNewFile(path+"_n",out_pa_jo.toString());
    }

    //字符长度不够 填补空格
    private static String bu_kong_ge( int i, String fanyi) throws IOException {
        int j = i/2;
        //补前面的空格用的
//        while (j>0){
//            fout.write(0x20);
//            j--;
//
//        }

        String kongge = "\n                                                                                                                                                  ";


            fanyi += kongge.substring(0,i);


        return fanyi;
//        System.out.println(j);
        //补前面的空格用的
//        j = i%2+i/2;
        //如果要直接在后面补空格的话,那么 while中的j要改成i

    }


    public static boolean isContainFuhao(String str){

        if (str.endsWith(",0")){
            return true;
        }else if (str.endsWith(",1")){
            return true;
        }else if (str.endsWith(",2")){
            return true;
        }else if (str.endsWith(",3")){
            return true;
        }else if (str.endsWith(",4")){
            return true;
        }else if (str.endsWith(",5")){
            return true;
        }else if (str.endsWith(",6")){
            return true;
        }else if (str.endsWith(",7")){
            return true;
        }else if (str.endsWith(",8")){
            return true;
        }else if (str.endsWith(",9")){
            return true;
        }

        return false;
    }

    public static String a(byte[] arg5) {
        String v0_2;
        int v1 = arg5.length;
        int v0;
        for(v0 = 0; v0 < v1 / 2; ++v0) {
            int v2 = arg5[v0 * 2 + 1];
            arg5[v0 * 2 + 1] = ((byte)(arg5[v0 * 2] ^ -1));
            arg5[v0 * 2] = ((byte)(v2 ^ -1));
        }

        try {
            v0_2 = new String(arg5, "gbk");
        }
        catch(UnsupportedEncodingException v0_1) {
            v0_1.printStackTrace();
            v0_2 = null;
        }

        return v0_2;
    }
}
