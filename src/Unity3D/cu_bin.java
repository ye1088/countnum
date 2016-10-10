package Unity3D;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * Created by Administrator on 2016/9/26.
 */
public class cu_bin {

    public static void main(String[] args) throws Exception {
//        String path = "D:\\工作目录\\suda苏打地牢\\res\\修改\\2\\libApplicationMain.so(2,43c0a0,03adac)_.txt";

//        packMain(path);
        pack(args[0]);
//        pack(path);
    }


    /**
     * 文件批量打包
     * 传入的路径为文件夹
     * @param path
     */
    public static void packMain(String path) throws Exception {
        File dir = new File(path);
        File[] files = dir.listFiles();
        for (File file:files
                ) {
            if (file.isDirectory()){
                packMain(file.getAbsolutePath());
                continue;
            }
            if (!file.getName().contains("_.txt")){
                continue;
            }
            pack(file.getAbsolutePath());
        }
    }


    /**
     * 单个文件打包
     * @param path
     * @throws Exception
     */
    public static void pack(String path) throws Exception {
        File data_file = new File(path.split("_.txt")[0]);

        if (!data_file.exists()){
            throw new Exception("ERROR: data文件不存在："+path.split("_.txt")[0]);
        }
        System.out.println(path);
        File out_file = new File(path.split("_.txt")[0]+"_out");
        RandomAccessFile fout = new RandomAccessFile(out_file,"rw");

        RandomAccessFile finp = new RandomAccessFile(data_file,"r");
        byte[] b = new byte[4096];
        int len =-1;
        while ((len =(finp.read(b)))!=-1){
            fout.write(b,0,len);

        }
        finp.close();

        try {
            JSONObject parent_jo = new JSONObject(Utils.JaUtils.fileToString(path));
            JSONArray items = parent_jo.getJSONArray("items");

            //文本是不是空的
            boolean empty_flag = true;
            for (int i = 0; i < items.length(); i++) {
                JSONObject jo = items.getJSONObject(i);
                String fanyi =jo.getString("cz");
                if (fanyi.equals("")){
                    continue;
                }

                empty_flag = false;
                byte[] fanyi_byte = fanyi.getBytes("utf-8");
                int enLen = jo.getInt("blen");
                long pos = jo.getLong("pos");
//                System.out.println("fanyi:"+fanyi_byte.length+" enlen:"+enLen);
                if (fanyi_byte.length>enLen){
                    finp.close();
                    pack_fail(data_file);
                    System.out.println("ERROR：字符串长了"+(fanyi_byte.length-enLen)+"在："+pos+"\n"+fanyi);
                    System.out.println(out_file.exists());
                    fout.close();
                    out_file.delete();
                    break;
//                    throw new Exception("ERROR：字符串太长在："+pos+"\n"+fanyi);
                }

                fout.seek(pos);
//            fout.write(fanyi_byte);
                if (fanyi_byte.length<=enLen){
                    bu_kong_ge(fout,enLen-fanyi_byte.length,fanyi_byte);
                }



            }

            fout.close();
            if (empty_flag){
                out_file.delete();
                data_file.delete();
                (new File(path)).delete();

            }
        }catch (JSONException e){
            System.out.println("Json格式有问题");
            out_file.delete();


        }catch (NumberFormatException e){
            System.out.println("Json格式有问题");
            out_file.delete();

        }catch (Exception e){
            System.out.println("未知异常");
            out_file.delete();

        }finally {
            finp.close();
            fout.close();
            pack_fail(data_file);
            System.out.println("beizhixingle");
        }

    }

    //处理打包失败的文本
    public static void pack_fail(File data_file) throws IOException {
//        String parent_path = data_file.getParent();
//        File parent_file = new File(parent_path);
//        String pparent = parent_file.getParent();
//        File fail_dir = new File(pparent + "\\fail");
//        if (!fail_dir.exists()){
//            fail_dir.mkdir();
//        }
//        Files.copy(data_file.toPath(),(new File(pparent + "\\fail\\"+data_file.getName())).toPath());
//        File txt_file = new File(data_file.getAbsolutePath()+"_.txt");
//        Files.copy(txt_file.toPath(),(new File(pparent + "\\fail\\"+txt_file.getName())).toPath());
//        data_file.delete();
//        txt_file.delete();
    }


    //字符长度不够 填补空格
    private static void bu_kong_ge(RandomAccessFile fout, int i, byte[] fanyi_byte) throws IOException {
        int j = i/2;
        while (j>0){
            fout.write(0x20);
            j--;

        }
        fout.write(fanyi_byte);
//        System.out.println(j);
        j = i%2+i/2;
        while (j>0){
//            System.out.println("后面的空格");
            fout.write(0x20);
            j--;
        }
    }

}
