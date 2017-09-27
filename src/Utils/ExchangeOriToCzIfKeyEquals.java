package Utils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by appchina on 2017/1/4.
 */
public class ExchangeOriToCzIfKeyEquals {

    public static void exchangeOriToCzIfKeyEquals(String srcPath, String dstPath){
        try {
            JSONObject src_pa_jo = new JSONObject(Utils.fileToString(srcPath));

            JSONObject dst_pa_jo = new JSONObject(Utils.fileToString(dstPath));

            JSONArray dst_items = dst_pa_jo.getJSONArray("items");
            JSONArray src_items = src_pa_jo.getJSONArray("items");

            boolean isHasKey = false;

            for (int i = 0; i < dst_items.length(); ) {
                JSONObject dst_son_jo = dst_items.getJSONObject(i);
                String dst_key = dst_son_jo.getString("ori");
                for (int j = 0; j < src_items.length(); j++) {
                    JSONObject src_son_jo = src_items.getJSONObject(j);
                    String src_key = src_son_jo.getString("ori");
                    if (src_key.equals(dst_key)){
                        JSONObject dst_string_jo = dst_items.getJSONObject(i + 1);

                        JSONObject src_string = src_items.getJSONObject(j + 1);
                        dst_string_jo.put("cz",src_string.getString("ori"));

                        i += 2;
                        isHasKey = true;
                        break;
                    }else {

                        continue;
                    }
                }

                if (!isHasKey){
                    i++;
                }else {
                    isHasKey = false;
                }
            }

            Utils.writeNewFile(dstPath+"_out",dst_pa_jo.toString());


        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        String srcPath = "F:\\汉化\\ling零骑士\\零骑士\\assets\\e2918e79144ca8e4db3b2d9b1ec7fad2_.txt";
        String dstPath = "F:\\汉化\\ling零骑士\\零骑士\\assets\\55ff9e086bee91a4b954482c076304c6_.txt";

        exchangeOriToCzIfKeyEquals(srcPath,dstPath);

    }
}
