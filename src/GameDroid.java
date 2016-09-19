import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * Created by Administrator on 2016/9/12.
 */
public class GameDroid {


    public static void tiqu(String file_path) throws IOException {
        RandomAccessFile raf = new RandomAccessFile(new File(file_path), "r");
        raf.readInt();
        raf.readInt();

        while (true){
            byte[] key_value = new byte[4];
            raf.read(key_value);
            String key_str = new String(key_value);
            if (key_str.equals("TXTR")||key_str.equals("FONT")||key_str.equals("STRG")){

            }
        }
    }


    private static int yiwei(Integer integer, int tmp_bit) {
        for (int i = 0; i < tmp_bit; i++) {
            integer = integer*16*16;
        }
        return integer;
    }
}
