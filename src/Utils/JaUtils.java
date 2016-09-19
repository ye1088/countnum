package Utils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by Administrator on 2016/9/14.
 */
public class JaUtils {

    public static void main(String[] args) throws IOException {

//        String ja_path = "C:\\Users\\Administrator\\Desktop\\记录\\zh\\libApplicationMain.so(2,43c0a0,03adac)_.txt";
//        filterEmtyObject(ja_path);
        byte[] bytes = "axLd3dlod9@_IYjR[=Msx#&>1jJh2:]L".getBytes("UTF-8");
        for (byte b:bytes
             ) {
            System.out.print(b);
        }


    }

    /**
     * 删掉
     * @param ja_path
     */
    public static void filterEmtyObject(String ja_path) throws IOException {
        boolean jo_flag = true;
//        try {
            JSONObject json = new JSONObject(Utils.fileToString(ja_path));
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

//        }catch (Exception e){
//            JSONArray json = new JSONArray(ja_path);
//        }


    }
}
