package guanjunjingli;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

/**
 * Created by Administrator on 2016/8/16.
 */
public class Utils {

    public static void tiqu(File file) throws IOException {
        RandomAccessFile raf = new RandomAccessFile(file, "r");
        raf.readInt();
        raf.readInt();
        ArrayList<String> nameList = new ArrayList<>();
        ArrayList<Integer> sizeList = new ArrayList<>();
        for (int i = 0; i < 0x85; i++) {
            String tmp_str = "";
            byte[] tmp_byte = new byte[1] ;
            while ((tmp_byte[0]=raf.readByte())!=0){
                tmp_str+=new String(tmp_byte);
//                System.out.println(tmp_str);
            }
            nameList.add(tmp_str);
            int tmp_int = 0;
            int tmp_bit = 0;
            raf.readInt();

            while (tmp_bit<4){
                tmp_int+=yiwei(Integer.valueOf(raf.readByte()&0xff),tmp_bit);
                tmp_bit++;
//                System.out.println(tmp_str);
            }
//            byte [] bytes = new byte[tmp_bit];
//            raf.read(bytes);
            System.out.println(tmp_int);
            sizeList.add(tmp_int);
//            raf.readInt();
            if (raf.getFilePointer()==0x13fa){
                break;
            }
        }

        for (int i = 0; i < nameList.size(); i++) {
            System.out.println(nameList.get(1));
            File tmpFile = new File("D:\\工作目录\\guanjun冠军经理\\冠军经理17\\assets\\aa\\"+nameList.get(i));
            File tmpParentFile = new File(tmpFile.getParent());
            if (!tmpParentFile.exists()){
                tmpParentFile.mkdirs();
            }
            FileOutputStream fos = new FileOutputStream(tmpFile);
//            long fileSize = 0;
//            while (fileSize < sizeList.get(i)){
//                fos.write(raf.readByte());
//                fileSize++;
//            }
            System.out.println(sizeList.get(i));
            byte[] tmp_byte = new byte[sizeList.get(i)];
            raf.read(tmp_byte);
            fos.write(tmp_byte);
            fos.flush();
            fos.close();
        }
        raf.close();

    }

    private static int yiwei(Integer integer, int tmp_bit) {
        for (int i = 0; i < tmp_bit; i++) {
            integer = integer*16*16;
        }
        return integer;
    }
}
