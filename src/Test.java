import Utils.Utils;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;


/**
 * Created by Administrator on 2015/12/10.
 */
public class Test {

    public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException {
//        Utils.countXmlTextNumEn();
//        Utils.countIlJsonTextNumZn();
//        Utils.countJsonTextNumEn();
//        Utils.countEquTextNumEn();

        //iljsonarray过滤
        Utils.filterIlJsonTextEn();
        //这个是二进制的jsonobject
//        Utils.filterJsonTextEn();
        //将提取出来的json文本中的ori中的文本拷贝到cz那列里
//        Utils.copyOriToCz("C:\\Users\\Administrator\\Desktop\\记录\\en");
        //在一句话中插入想要的字符
//        Utils.insertSpace("C:\\Users\\Administrator\\Desktop\\记录\\en");
        //根据条件将提取出来的json文本中的ori中的文本拷贝到cz那列里
//        Utils.ifCopyOriToCz("C:\\Users\\Administrator\\Desktop\\记录\\en",new String[]{"computers","stealth","skill","desc","hint","engineering","explosives",
//                "lockpicking","grappling","firearms","skillName","melee",
//                "skills","survivor","session"});

//        Utils.sortIlFile();
//        Utils.sortJsonObjectFile();
//        Utils.exchangeIlJsonPos();
        //这个是对smali文本资源过滤
//        Utils.filterSmaliJsonTxt();
//        Utils.txtToExcel("F:\\汉化\\shouji手机疑云\\Resource\\Data_out\\txt\\sharedassets0.assets_forum_posts.(75).txt",",");
        //去掉翻译好之后没有用的东西
//        Utils.filterSmaliJsonEndTxt();
        /**
         * 这个方法是针对il文件中有些文字只能改16进制，而il中的16进制是
         * 将32位数的高16位和低16位互相倒着放的，所以我们写了这个工具
         * 他两个交换的时候原来的顺序由高到低是4321调换后是2143；
         */
//        Utils.ilConvertUtf8ToHex();

    }
}
