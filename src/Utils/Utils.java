package Utils;

import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2015/12/10.
 */
public class Utils {

    /**
     * 这个方法是将传进来的文件即path(文件的绝对路径)
     * 进行读取,读到StringBuffer并以String的格式返回
     *
     * @param path
     * @return
     * @throws FileNotFoundException
     */
    public static String fileToString(String path) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(path), "utf-8");
        StringBuffer sb = new StringBuffer();
        while (scanner.hasNextLine()) {
            sb.append(scanner.nextLine());
        }
        scanner.close();
        return sb.toString();
    }


    public static void makeCrcTable(){
        long[] crcTable_ = new long[0x100];
        for (int i = 0; i < 0x100; i++)
        {
            long num = i;
            for (int j = 0; j < 8; j++)
            {
                if ((num & 1L) == 0L)
                {
                    num = ((long) 0xedb88320L) ^ (num >> 1);
                }
                else
                {
                    num = num >> 1;
                }
            }
            crcTable_[i] = num;
        }
        for (int i = 0;i< 0x100;i++){
            System.out.print(crcTable_[i]+",");
        }

    }


    //计算so和level等产生的text中的文字数目
    public static void countJsonTextNumEn() throws IOException {
        //Json文件所在的文件夹
        String path = "C:\\Users\\Administrator\\Desktop\\新建文件夹";
        File file = new File(path);
        File[] files = file.listFiles();
        int count = 0;
        for (int i = 0; i < files.length; i++) {
            String absolutePath = files[i].getAbsolutePath();
            String jsonString = fileToString(absolutePath);
            JSONObject pJo = new JSONObject(jsonString);
            File out_file = new File(absolutePath+"_");
            FileWriter fw = new FileWriter(out_file);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(pJo.toString());
            bw.flush();
            bw.close();

//            JSONArray ja = pJo.getJSONArray("items");
//            for (int k = 0; k < ja.length(); k++) {
//                JSONObject sJo = ja.getJSONObject(k);
//                if (sJo.getString("cz").equals("")) {
//                    String ori = sJo.getString("ori");
//                    while (ori.contains(" ")) {
//                        ori = ori.substring(0, ori.lastIndexOf(" "));
//                        count++;
//                    }
//                    count++;
//                }
//            }
        }

        System.out.print("这" + files.length + "个文件共有英文单词" + count + "个");

    }


    //计算so和level等产生的text中的文字数目
    public static void countJsonTextNumZn() throws FileNotFoundException {
        //Json文件所在的文件夹
        String path = "C:\\Users\\Administrator.USER-20151019LW\\Desktop\\新建文件夹";
        File file = new File(path);
        File[] files = file.listFiles();
        int count = 0;
        for (int i = 0; i < files.length; i++) {
            String absolutePath = files[i].getAbsolutePath();
            String jsonString = fileToString(absolutePath);
            JSONObject pJo = new JSONObject(jsonString);
            JSONArray ja = pJo.getJSONArray("items");
            for (int k = 0; k < ja.length(); k++) {
                JSONObject sJo = ja.getJSONObject(k);
                String ori = sJo.getString("ori");
                ori = ori.replace("\n", "");
                //这里的空格要用文本中的空格，自己敲的没有用
                ori = ori.replace("　", "");
                count += ori.length();

            }
        }

        System.out.print("这" + files.length + "个文件共有中文单词" + count + "个");

    }


    //处理il的text或者刚开始就是json数组的text
    public static void countIlJsonTextNumEn() throws FileNotFoundException {
        String path = "F:\\workspace\\111.il_out";
        JSONArray ja = new JSONArray(fileToString(path));
        int count = 0;
        for (int i = 0; i < ja.length(); i++) {
            JSONObject jo = ja.getJSONObject(i);
            if (jo.getString("zh").equals("")) {
                String ori = jo.getString("ori");
                while (ori.contains(" ")) {
                    count++;
                    ori = ori.substring(0, ori.lastIndexOf(" "));
                }
                count++;
                System.out.printf(ori);
            }

        }
        System.out.print("这个文件共有英文单词" + count + "个");
    }


    //处理il的text或者刚开始就是json数组的text
    public static void countIlJsonTextNumZn() throws FileNotFoundException {
        String path = "F:\\workspace\\111.il_out";
        JSONArray ja = new JSONArray(fileToString(path));
        int count = 0;
        for (int i = 0; i < ja.length(); i++) {
            JSONObject jo = ja.getJSONObject(i);
            if (jo.getString("zh").equals("")) {
                String ori = jo.getString("ori");
                ori = ori.replace("\n", "");
                //这里的空格要用文本中的空格，自己敲的没有用
                ori = ori.replace("　", "");
                System.out.printf(ori);
                count += ori.length();

            }
        }
        System.out.print("这个文件共有中文单词" + count + "个");
    }


    public static void countXmlTextNumEn() throws ParserConfigurationException, IOException, SAXException {
        String path = "F:\\workspace\\51358cc068c79314780c64a094e5abe0_language_table_0_nabi.(1).txt";
        //这里是用dom解析xml文件
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        //这里直接传入文件路径就好了
        Document doc = db.parse(path);
        NodeList nList = doc.getElementsByTagName("item");
        int count = 0;
        for (int i = 0; i < nList.getLength(); i++) {
            Node item = nList.item(i);
            NodeList childNodes = item.getChildNodes();
            /**
             * 因为我们只要us这个子节点，所以就不遍历所有子节点了
             * 这里xml每一行的空格回车算一个节点，不算空格回车的话，
             * 我们的us节点应该在item（2）的位置的，算上空格回车的话
             * 我们的us节点就在item（5）了
             */
            Node usText = childNodes.item(5);
            String text = usText.getTextContent();
            System.out.println(text);
            while (text.contains(" ")) {
                count++;
                text = text.substring(0, text.lastIndexOf(" "));
            }
            count++;
        }
        System.out.printf("这个文件共有英文单词" + count + "个");

    }


    public static void countXmlTextNumZn() throws ParserConfigurationException, IOException, SAXException {
        String path = "F:\\workspace\\51358cc068c79314780c64a094e5abe0_language_table_0_nabi.(1).txt";
        //这里是用dom解析xml文件
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        //这里直接传入文件路径就好了
        Document doc = db.parse(path);
        NodeList nList = doc.getElementsByTagName("item");
        int count = 0;
        for (int i = 0; i < nList.getLength(); i++) {
            Node item = nList.item(i);
            NodeList childNodes = item.getChildNodes();
            /**
             * 因为我们只要us这个子节点，所以就不遍历所有子节点了
             * 这里xml每一行的空格回车算一个节点，不算空格回车的话，
             * 我们的us节点应该在item（2）的位置的，算上空格回车的话
             * 我们的us节点就在item（5）了
             */
            Node usText = childNodes.item(5);
            String text = usText.getTextContent();
            System.out.println(text);
            text = text.replace("\n", "");
            //这里的空格要用文本中的空格，自己敲的没有用
            text = text.replace("　", "");
            count += text.length();
        }
        System.out.printf("这个文件共有中文文单词" + count + "个");

    }

    //这个是处理"Line_110" = "Level";类似于这种的
    public static void countEquTextNumEn() throws IOException {
        String path = "C:\\Users\\Administrator.USER-20151019LW\\Desktop\\新建文件夹\\exec_strings_de.txt";
        FileInputStream fis = new FileInputStream(path);
        InputStreamReader isr = new InputStreamReader(fis, "utf-8");
        BufferedReader br = new BufferedReader(isr);
        String line = "";
        int count = 0;
        while ((line = br.readLine()) != null) {
            if (line.contains("=")) {
                line = line.substring(line.lastIndexOf("=") + 2);
                while (line.contains(" ")) {
                    line = line.substring(0, line.lastIndexOf(" "));
                    count++;
                }
                count++;
            }
        }

        System.out.printf("这个文件有英文单词" + count + "个");
    }


    public static void countEquTextNumZn() throws IOException {
        String path = "C:\\Users\\Administrator.USER-20151019LW\\Desktop\\新建文件夹\\exec_strings_de.txt";
        FileInputStream fis = new FileInputStream(path);
        InputStreamReader isr = new InputStreamReader(fis, "utf-8");
        BufferedReader br = new BufferedReader(isr);
        String line = "";
        int count = 0;
        while ((line = br.readLine()) != null) {
            if (line.contains("=")) {
                line = line.substring(line.lastIndexOf("=") + 2);
                line = line.substring(0, line.lastIndexOf(" "));
                count += line.length();
            }
        }

        System.out.printf("这个文件有中文单词" + count + "个");
    }


    //这个是一来都是文本的り,有,右,ゆめちゃんが一生懸命あきさんのために;;像这样
    public static void countTextNumEn() throws IOException {
        String path = "";//这里写文件所在的文件夹地址
        File file = new File(path);
        File[] files = file.listFiles();
        int count = 0;
        for (int i = 0; i < files.length; i++) {

            FileInputStream fis = new FileInputStream(files[i]);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            String line = "";
            while ((line = br.readLine()) != null) {
                while (line.contains(" ")) {
                    count++;
                    line = line.substring(0, line.lastIndexOf(" "));
                }
                count++;
            }
        }
        System.out.printf("这" + files.length + "个文件中共有" + count + "个单词");
    }

    public static void countTextNumZn() throws IOException {
        String path = "";//这里写文件所在的文件夹地址
        File file = new File(path);
        File[] files = file.listFiles();
        int count = 0;
        for (int i = 0; i < files.length; i++) {

            FileInputStream fis = new FileInputStream(files[i]);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            String line = "";
            while ((line = br.readLine()) != null) {
                count += line.length();
            }
        }
        System.out.printf("这" + files.length + "个文件中共有" + count + "个单词");
    }


    /***
     * 将提取出来的json文本中的ori中的文本拷贝到cz那列里
     * @param dir_path
     * @throws IOException
     */
    public static void copyOriToCz(String dir_path) throws IOException {
        File file = new File(dir_path);
        File[] files = file.listFiles();
        for (int i = 0; i < files.length; i++) {
            JSONArray ja = new JSONArray(fileToString(files[i].getAbsolutePath()));
            int len = ja.length();
            for (int j = 0; j < len; j++) {
                JSONObject sJo = ja.getJSONObject(j);
                String ori = sJo.getString("ori");
                sJo.put("cz",ori);

            }
            writeNewFile(files[i].getAbsolutePath() + "_n", formatJson(ja.toString()));

        }
    }


    /***
     * 将提取出来的json文本中的ori中的文本拷贝到cz那列里(要根据条件来弄)
     * @param dir_path
     * flag 是用来判断的条件
     * @throws IOException
     */
    public static void ifCopyOriToCz(String dir_path,String[] flag) throws IOException {
        File file = new File(dir_path);
        File[] files = file.listFiles();
        for (int i = 0; i < files.length; i++) {
            JSONArray ja = new JSONArray(fileToString(files[i].getAbsolutePath()));
            int len = ja.length();
            for (int j = 0; j < len; j++) {
                JSONObject sJo = ja.getJSONObject(j);
                String ori = sJo.getString("ori");
                for (String str : flag) {
                    if (ori.equals(str)) {

                        sJo.put("cz", ori);
                        break;
                    }

                }
            }
            writeNewFile(files[i].getAbsolutePath() + "_n", formatJson(ja.toString()));

        }
    }


    /**
     * 在字符窜中插入空格
     * @param dir_path:要修改的文件所在的文件加
     */
    public static void insertSpace(String dir_path) throws IOException {
        File file = new File(dir_path);
        File[] files = file.listFiles();
        for (int i = 0; i < files.length; i++) {
            JSONArray ja = new JSONArray(fileToString(files[i].getAbsolutePath()));
            int len = ja.length();
            for (int j = 0; j < len; j++) {
                JSONObject sJo = ja.getJSONObject(j);
                String cz = sJo.getString("cz");
                int k = 0;
                String tmp_cz = "";
                if (!cz.contains("\n")||true) {
                    for (char sub_str :
                            cz.toCharArray()) {
                        k++;
                        if (k > 16) {
                            k=0;
                            tmp_cz += sub_str + " ";
                        } else {
                            tmp_cz += sub_str;
                        }

                    }
                    sJo.put("cz", tmp_cz);
                }

            }
            writeNewFile(files[i].getAbsolutePath() + "_n", ja.toString());

        }
    }


    //提出英文文本时去掉肯定没有用的东西
    //如果出现Exception in thread "main" org.json.JSONException: Duplicate key "offset"
    //这个异常，说明那个文本的所有东西都没有用
    public static void filterJsonTextEn() throws IOException {
        String path = "C:\\Users\\Administrator\\Desktop\\记录\\en";
        File file = new File(path);
        File[] files = file.listFiles();
        for (int i = 0; i < files.length; i++) {
            System.out.println(files[i].getAbsolutePath());
            JSONObject jo = new JSONObject(fileToString(files[i].getAbsolutePath()));
            JSONArray ja = jo.getJSONArray("items");
            int len = ja.length();
            for (int j = 0; j < len; j++) {
                JSONObject sJo = ja.getJSONObject(j);
//                String cz = sJo.getString("cz");
//                if (cz.equals("")){
//                    ja.remove(j);
//                    len--;
//                    j--;
//                }
                String ori = sJo.getString("ori");
                if (ori.contains("_")||(ori.length()>10&&!ori.contains(" "))||ori.split(" ").length<=1||isChineseChar(ori)
                        /**********************其它游戏单加的弄玩就删***********************************/
                        ||ori.contains("Scrollbar")||ori.contains("Directional Li")||ori.contains("Sphere (")||ori.contains("Thruster (")
                        ||ori.contains("State ")||ori.contains("Sliding Area")||ori.contains("StageText")||ori.contains("Cube (")
                        ||ori.contains("Pole (")||ori.contains("Floor (")||ori.contains("Directional light")||ori.contains("MasterCube")
                        ||ori.contains("Bip0")||ori.contains("Timer")||ori.contains("Chap Txt")||ori.contains("Canvas (")
                        ||ori.contains("Audio ")||ori.contains("TAPE 0")||ori.contains("Layout")||ori.contains("Sparks")
                        ||ori.contains("LampA")||ori.contains("PipeRingA")||ori.contains("Note (")||ori.contains("Shelf (")
                        ||ori.contains("LampA")||ori.contains("Floor1x2")||ori.contains("Wheelchair")||ori.contains("PannelLight")
                        ||ori.contains("Prefab")||ori.contains("Audio ")||ori.contains("MainLight")||ori.contains("LoadingSystemPrfab")
                        ||ori.contains(".UI")||ori.contains("BarUnit")||ori.contains("UnityEngine")||ori.contains("BarUnit")
                        ||ori.contains("Combined Mesh")||ori.contains("LightSwitch")||ori.contains("Point Light")||ori.contains("BookB")
                        ||ori.contains("Outlet")||ori.contains("New Text")||ori.contains("Main Camera")||ori.contains("New Text")
                        ||ori.contains("LampPoint")||ori.contains("Reflection Probe")||ori.contains("HumanRDigit")||ori.contains("Your result")
                        ||ori.contains("Cube (1)")||ori.contains("BreakingCrate")||ori.contains("Teleport1")||ori.contains("Vent ")
                        ||ori.contains("Assembly-CSharp")||ori.contains("PlayMaker")||ori.contains(" 2D")||ori.contains("Story Text")
                        ||ori.contains("Version=")||ori.contains("Character Name")||ori.contains("Scroll View")||ori.contains("Fill Area")
                        ||ori.contains("Panel (")||ori.contains("Handle Slide")||ori.contains("Point light")||ori.contains("Cutscene Trigger")
                        ||ori.contains("Director Group")||ori.contains("Audio Track")||ori.contains("Game Object")||ori.contains("RoadBaseEmpty")
                        ||ori.contains("Plane (")||ori.contains("AirDuct")||ori.contains("Combo Butto")||ori.contains("Shot Track")
                        /**********************************************************************************************/
                        ||ori.contains("Image (")||ori.contains("Text (")||ori.contains("PlatformTD")||ori.contains("PanelPauseMenu")
                        ||ori.contains("Bip001")||ori.contains("BusStopListItem")||ori.contains("VLine")||ori.contains("TextLine")
                        ||ori.contains("Title 1")||ori.contains("BusStopPref")||ori.contains("GameObject")||ori.contains("TextLine")
                        ||ori.contains("Quad (")||ori.contains("Arrow (")||ori.contains("Button1 (")||ori.contains("EleButton")
                        ||ori.contains("Frame (")||ori.contains("GameMessage")||ori.contains("Anchor ")||ori.contains("Shaders/")
                        ||ori.contains("ScoreObj")||ori.contains("Quad ")||ori.contains("ScrollWheel")||ori.contains("UI")
                        ||ori.contains("Highlight")||ori.contains("MapButton")||ori.contains("Handle (")
                        ||ori.startsWith("1")||ori.startsWith("3")||ori.startsWith("5")||ori.startsWith("7")||ori.startsWith("9")
                        ||ori.startsWith("2")||ori.startsWith("4")||ori.startsWith("6")||ori.startsWith("8")||ori.startsWith("0")
                        /*********************************************************************************/

                        ||ori.split(" ").length<=1||ori.startsWith(" ")||ori.contains("AdColony")
                        ||ori.startsWith("a")||ori.startsWith("s")||ori.startsWith("d")||ori.startsWith("f")
                        ||ori.startsWith("g")||ori.startsWith("h")||ori.startsWith("j")||ori.startsWith("k")
                        ||ori.startsWith("l")||ori.startsWith("q")||ori.startsWith("w")||ori.startsWith("e")
                        ||ori.startsWith("r")||ori.startsWith("t")||ori.startsWith("y")||ori.startsWith("u")
                        ||ori.startsWith("z")||ori.startsWith("x")||ori.startsWith("c")||ori.startsWith("v")
                        ||ori.startsWith("i")||ori.startsWith("m")||ori.startsWith("n")||ori.startsWith("b")
                        ||ori.startsWith("o")||ori.startsWith("p")||ori.startsWith(" ")
//                        ||ori.contains("0")||ori.contains("1")||ori.contains("2")
//                        ||ori.contains("3")||ori.contains("4")||ori.contains("5")||ori.contains("6")
//                        ||ori.contains("7")||ori.contains("8")||ori.contains("9")
                        ){
                    /**
                     * 这里因为我们删掉了一个json对象，所以json数组的长度会减1
                     * 因此我们的总长度len要减1，而且删掉了一个元素后面的元素会往前移动一位
                     * 因此j要还是原来的值，所以j要减1；
                     */
                    ja.remove(j);
                    len--;
                    j--;
                }
            }
            writeNewFile(files[i].getAbsolutePath() + "_n", jsonFormat(jo.toString()));//jo.toString());

        }
    }

    //这个是过滤il文件的英文文本的
    public static void filterIlJsonTextEn() throws IOException {
        String path = "C:\\Users\\Administrator\\Desktop\\记录\\en";
        File file = new File(path);
        File[] files = file.listFiles();
        for (int i = 0; i < files.length; i++) {
            JSONArray ja = new JSONArray(fileToString(files[i].getAbsolutePath()));
            int len = ja.length();
            for (int j = 0; j < len; j++) {
                JSONObject sJo = ja.getJSONObject(j);
                String ori = sJo.getString("ori");
//                System.out.println(ori);
//                System.out.println(ori.contains("_"));
                if (ori.contains("_")||(ori.length()>10&&!ori.contains(" "))
                        /**********************其它游戏单加的弄玩就删***********************************/

                        /*********************************************************************************/
                        ||ori.contains("Disabled")||ori.contains("LeftLeg")||ori.contains("FadeIn")
                        ||ori.contains("ManaBar")||ori.contains("ManaCost")||ori.contains("LeaverOn")
                        ||ori.contains("ShopItem")||ori.contains("LeftLine")||ori.contains("LeaverOn")
                        ||ori.contains("Feedback")||ori.contains("WakeUp")||ori.contains("Brocoin")
                        ||ori.contains("Label")||ori.contains("Sprite")||ori.contains("Background")
                        ||ori.contains("Button")||ori.contains("Panel")||ori.contains("Icon")||ori.split(" ").length<=1
                        ||ori.contains("Color")||ori.contains("Scale")||ori.contains("Title")
                        ||ori.contains(".net")||ori.contains(".png")||ori.contains(".jpg")
                        ||ori.contains(".ogg")||ori.contains(".atlas")||ori.contains(".xml")
                        ||ori.contains(".tmx")||ori.contains(".atlas")||ori.contains(".xml")
                        ||ori.contains("UnityEngine.")||ori.contains(".dll")||(ori.length()>=9&&!ori.contains(" "))
                        ||ori.contains(")Z")||ori.contains(")V")||ori.contains(")L")||ori.contains(")I")
                        ||ori.contains(".tif")||ori.contains("Btn")||ori.contains("Gui")
                        ||ori.contains("(")||ori.contains("/")||ori.contains("bg")||ori.contains("OnC")
                        ||ori.contains("OnH") ||ori.contains("OnP") ||ori.contains("OnL")
                        ||ori.contains("ScrollWheel")||ori.contains("com.")||ori.contains(".com")
                        ||ori.contains("OnD")||ori.contains("OnS")||ori.contains("OnA")
                        ||ori.contains(".dat")||ori.contains("DummyID")||ori.contains("Player")
                        ||ori.contains("Value")||ori.contains("value")||ori.contains("initialized")
                        ||ori.contains("default")||ori.contains("platform")||ori.contains("Play")
                        ||ori.contains("client")||ori.contains("called")||ori.contains("User")
                        ||ori.contains("LoadMore")||ori.contains("LoadScores")||ori.contains("status:")
                        ||ori.contains("exception")||ori.contains("loading")||ori.contains("refresh")
                        ||ori.contains("services")||ori.contains("registered")||ori.contains("email")
                        ||ori.contains("ID")||ori.contains("Error")||ori.contains("log")
                        ||ori.contains("Found")||ori.contains("Proceeding")||ori.contains("User")
                        ||ori.contains("implementation")||ori.contains("NULL")||ori.contains("match:")
                        ||ori.contains("ConnectionRequest")||ori.contains("SendReliable")||ori.contains("StopAvertising")
                        ||ori.contains("from:")||ori.contains("Client")||ori.contains("Initialize")
                        ||ori.contains("PlayGames")||ori.contains("ReportProgress")||ori.contains("=")
                        ||ori.contains("image:")||ori.contains("callback")||ori.contains("UI")
                        ||ori.contains("URL")||ori.contains("DEBUG")||ori.contains("ERROR")
                        ||ori.contains("byte")||ori.contains("array")||ori.contains("offset")
                        ||ori.contains("length")||ori.contains("action")||ori.contains("Exception")
                        ||ori.contains("intent")||ori.contains("return")||ori.contains("Api")
                        ||ori.contains("API")||ori.contains("connect")||ori.contains("scope")
                        ||ori.contains("Java")||ori.contains("object")||ori.contains("method")
                        ||ori.contains("Verti")||ori.contains("Horiz")||ori.contains("Submit")
                        ||ori.contains("DataSource")||ori.contains("gpg")||ori.contains("payload")
                        ||ori.contains("advertising")||ori.contains("listener")||ori.contains("Default")
                        ||ori.contains("video")||ori.contains("msg")||ori.contains("isIdle")
                        ||ori.contains("clicked")||ori.contains("string")||ori.contains("Image")
                        ||ori.contains("DOWN")||ori.contains("TEMP")
                        ||ori.contains("Text")||ori.contains("Pressed")||ori.contains("SOFTWARE")
                        ||ori.contains("Normal")||ori.contains("Canvas")||ori.contains("Camera")
                        ||ori.startsWith("a")||ori.startsWith("s")||ori.startsWith("d")||ori.startsWith("f")
                        ||ori.startsWith("g")||ori.startsWith("h")||ori.startsWith("j")||ori.startsWith("k")
                        ||ori.startsWith("l")||ori.startsWith("q")||ori.startsWith("w")||ori.startsWith("e")
                        ||ori.startsWith("r")||ori.startsWith("t")||ori.startsWith("y")||ori.startsWith("u")
                        ||ori.startsWith("z")||ori.startsWith("x")||ori.startsWith("c")||ori.startsWith("v")
                        ||ori.startsWith("i")||ori.startsWith("m")||ori.startsWith("n")||ori.startsWith("b")
                        ||ori.startsWith("o")||ori.startsWith("p")||ori.startsWith(" ")
//                        ||ori.contains("0")||ori.contains("1")||ori.contains("2")
//                        ||ori.contains("3")||ori.contains("4")||ori.contains("5")||ori.contains("6")
//                        ||ori.contains("7")||ori.contains("8")||ori.contains("9")
                        ){
                    /**
                     * 这里因为我们删掉了一个json对象，所以json数组的长度会减1
                     * 因此我们的总长度len要减1，而且删掉了一个元素后面的元素会往前移动一位
                     * 因此j要还是原来的值，所以j要减1；
                     */
                    ja.remove(j);
                    len--;
                    j--;
                }
            }
            writeNewFile(files[i].getAbsolutePath() + "_n", formatJson(ja.toString()));

        }
    }

    //将数据写入新的文件中（像改变后的json文本等）
    private static void writeNewFile(String filePath, String jsonString) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(filePath));
        bw.write(jsonString);
        bw.flush();
        bw.close();
    }


    /**
     * 这个是如果文本在smali文件中，对肯定没有用的文本进行过滤
     * @return
     */
    public static void filterSmaliJsonTxt() throws IOException {
        String path = "D:\\工作目录\\daxue大学足球经理";
        File file = new File(path);
        for (File f : file.listFiles()){
            JSONArray mJa = new JSONArray(fileToString(f.getAbsolutePath()));
            int mLen = mJa.length();
            for (int i = 0; i < mLen; i++) {
                JSONObject mJo = mJa.getJSONObject(i);
                JSONArray sJa = mJo.getJSONArray("items");
                int sLen = sJa.length();
                for (int j = 0; j < sLen; j++) {
                    JSONObject sJo = sJa.getJSONObject(j);
                    String ori = sJo.getString("ori");
                    String uni = sJo.getString("unicode");
                    if (ori.contains("_")||(ori.length()>10&&!ori.contains(" "))
                            /**********************其它游戏单加的弄玩就删***********************************/
                            ||uni.contains("\\u")
                            /*********************************************************************************/
                            ||ori.contains("Disabled")||ori.contains("LeftLeg")||ori.contains("FadeIn")
                            ||ori.contains("ManaBar")||ori.contains("ManaCost")||ori.contains("LeaverOn")
                            ||ori.contains("ShopItem")||ori.contains("LeftLine")||ori.contains("LeaverOn")
                            ||ori.contains("Feedback")||ori.contains("WakeUp")||ori.contains("Brocoin")
                            ||ori.contains("Label")||ori.contains("Sprite")||ori.contains("Background")
                            ||ori.contains("Button")||ori.contains("Panel")||ori.contains("Icon")
                            ||ori.contains("Color")||ori.contains("Scale")||ori.contains("Title")
                            ||ori.contains(".net")||ori.contains(".png")||ori.contains(".jpg")
                            ||ori.contains(".ogg")||ori.contains(".atlas")||ori.contains(".xml")
                            ||ori.contains(".tmx")||ori.contains(".atlas")||ori.contains(".xml")
                            ||ori.contains("UnityEngine.")||ori.contains(".dll")||(ori.length()>=9&&!ori.contains(" "))
                            ||ori.contains(")Z")||ori.contains(")V")||ori.contains(")L")||ori.contains(")I")
                            ||ori.contains(".tif")||ori.contains("Btn")||ori.contains("Gui")
                            ||ori.contains("(")||ori.contains("/")||ori.contains("bg")||ori.contains("OnC")
                            ||ori.contains("OnH") ||ori.contains("OnP") ||ori.contains("OnL")
                            ||ori.contains("ScrollWheel")||ori.contains("com.")||ori.contains(".com")
                            ||ori.contains("OnD")||ori.contains("OnS")||ori.contains("OnA")
                            ||ori.contains(".dat")||ori.contains("DummyID")||ori.contains("Player")
                            ||ori.contains("Value")||ori.contains("value")||ori.contains("initialized")
                            ||ori.contains("default")||ori.contains("platform")||ori.contains("Play")
                            ||ori.contains("client")||ori.contains("called")||ori.contains("User")
                            ||ori.contains("LoadMore")||ori.contains("LoadScores")||ori.contains("status:")
                            ||ori.contains("exception")||ori.contains("loading")||ori.contains("refresh")
                            ||ori.contains("services")||ori.contains("registered")||ori.contains("email")
                            ||ori.contains("ID")||ori.contains("Error")||ori.contains("log")
                            ||ori.contains("Found")||ori.contains("Proceeding")||ori.contains("User")
                            ||ori.contains("implementation")||ori.contains("NULL")||ori.contains("match:")
                            ||ori.contains("ConnectionRequest")||ori.contains("SendReliable")||ori.contains("StopAvertising")
                            ||ori.contains("from:")||ori.contains("Client")||ori.contains("Initialize")
                            ||ori.contains("PlayGames")||ori.contains("ReportProgress")||ori.contains("=")
                            ||ori.contains("image:")||ori.contains("callback")||ori.contains("UI")
                            ||ori.contains("URL")||ori.contains("DEBUG")||ori.contains("ERROR")
                            ||ori.contains("byte")||ori.contains("array")||ori.contains("offset")
                            ||ori.contains("length")||ori.contains("action")||ori.contains("Exception")
                            ||ori.contains("intent")||ori.contains("return")||ori.contains("Api")
                            ||ori.contains("API")||ori.contains("connect")||ori.contains("scope")
                            ||ori.contains("Java")||ori.contains("object")||ori.contains("method")
                            ||ori.contains("Verti")||ori.contains("Horiz")||ori.contains("Submit")
                            ||ori.contains("DataSource")||ori.contains("gpg")||ori.contains("payload")
                            ||ori.contains("advertising")||ori.contains("listener")||ori.contains("Default")
                            ||ori.contains("video")||ori.contains("msg")||ori.contains("isIdle")
                            ||ori.contains("clicked")||ori.contains("string")||ori.contains("Image")
                            ||ori.contains("DOWN")||ori.contains("TEMP")
                            ||ori.contains("Text")||ori.contains("Pressed")||ori.contains("SOFTWARE")
                            ||ori.contains("Normal")||ori.contains("Canvas")||ori.contains("Camera")||(ori.split(" ").length<=1)
                            ||ori.startsWith("a")||ori.startsWith("s")||ori.startsWith("d")||ori.startsWith("f")
                            ||ori.startsWith("g")||ori.startsWith("h")||ori.startsWith("j")||ori.startsWith("k")
                            ||ori.startsWith("l")||ori.startsWith("q")||ori.startsWith("w")||ori.startsWith("e")
                            ||ori.startsWith("r")||ori.startsWith("t")||ori.startsWith("y")||ori.startsWith("u")
                            ||ori.startsWith("z")||ori.startsWith("x")||ori.startsWith("c")||ori.startsWith("v")
                            ||ori.startsWith("i")||ori.startsWith("m")||ori.startsWith("n")||ori.startsWith("b")
                            ||ori.startsWith("o")||ori.startsWith("p")||ori.startsWith(" ")){
//                            ||ori.contains("0")||ori.contains("1")||ori.contains("2")
//                            ||ori.contains("3")||ori.contains("4")||ori.contains("5")||ori.contains("6")
//                            ||ori.contains("7")||ori.contains("8")||ori.contains("9")){
                        /**
                         * 这里因为我们删掉了一个json对象，所以json数组的长度会减1
                         * 因此我们的总长度len要减1，而且删掉了一个元素后面的元素会往前移动一位
                         * 因此j要还是原来的值，所以j要减1；
                         */
                        sJa.remove(j);
                        sLen--;
                        j--;
                    }
                }
                if (sLen==0){
                    mJa.remove(i);
                    i--;
                    mLen--;
                }

            }
            writeNewFile(f.getAbsolutePath()+"_n",jsonFormatForSmali(mJa.toString()));
        }
    }

    /**
     * 这个是smali文件翻译好之后，将不需要翻译的文本全删掉
     */
    public static void filterSmaliJsonEndTxt() throws IOException {
        String path = "C:\\Users\\Administrator\\Desktop\\记录\\en";
        File file = new File(path);
        for (File f : file.listFiles()){
            JSONArray mJa = new JSONArray(fileToString(f.getAbsolutePath()));
            int mLen = mJa.length();
            for (int i = 0; i < mLen; i++) {
                JSONObject mJo = mJa.getJSONObject(i);
                JSONArray sJa = mJo.getJSONArray("items");
                int sLen = sJa.length();
                for (int j = 0; j < sLen; j++) {
                    JSONObject sJo = sJa.getJSONObject(j);
                    String zh = sJo.getString("zh");
                    if (zh.equals("")){
                        /**
                         * 这里因为我们删掉了一个json对象，所以json数组的长度会减1
                         * 因此我们的总长度len要减1，而且删掉了一个元素后面的元素会往前移动一位
                         * 因此j要还是原来的值，所以j要减1；
                         */
                        sJa.remove(j);
                        sLen--;
                        j--;
                    }
                }
                if (sLen==0){
                    mJa.remove(i);
                    i--;
                    mLen--;
                }

            }
            writeNewFile(f.getAbsolutePath()+"_n",jsonFormatForSmali(mJa.toString()));
        }
    }


    /**
     * 这个是对smali文本进行格式化
     * @return
     */
    private static String jsonFormatForSmali(String s){
        int len = s.length();
        char ch ;
        /**pkey是判断是不是第一个[这个符号, 是的话变为true
         * pkey2是判断是不是第一个{符号
         * pkey3是判断是不是path的，号
         * end判断是不是到结尾了
         */
        boolean pkey = false,pkey2 = false,pkey3 = false,end = false;
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < len; i++) {
            ch = s.charAt(i);
            sb.append(ch);
            if (ch == '{'&&!pkey2){
                sb.append('\n');
                sb.append('\t');
                sb.append('\t');
                pkey2 = true;
                continue;
            }
            if (ch == '['&&!pkey){
                sb.append('\n');
                sb.append('\t');
                pkey = true;
                continue;
            }
            if (ch ==':'&& s.charAt(i+1)=='['){
                sb.append('\n');
                sb.append('\t');
                sb.append('\t');
                continue;
            }
            if (ch=='['&&pkey){
                sb.append('\n');
                sb.append('\t');
                sb.append('\t');
                sb.append('\t');
                continue;
            }
            if (ch == ']'&&!end){
                sb.append('\n');
                sb.append('\t');
                continue;
            }
            if (ch == ']'&&end){
                break;
            }
            if (ch == '{'&&pkey2){
                sb.append('\n');
                sb.append('\t');
                sb.append('\t');
                sb.append('\t');
                sb.append('\t');
                continue;
            }
            if (ch == '"'&&s.charAt(i+1)=='}'){
                sb.append('\n');
                sb.append('\t');
                sb.append('\t');
                sb.append('\t');
                continue;
            }
            // 这个判断句尽量放在后边，在前面的话可能出问题
            if (ch == '}'&& s.charAt(i+1)==']'&&s.charAt(i-1)=='"'){
                sb.append('\n');
                sb.append('\t');
                sb.append('\t');
                continue;
            }
            if (ch == '}'&& s.charAt(i+1)==']'&&s.charAt(i-1)==']'){
                sb.append('\n');
                end = true;
                continue;
            }

            if (ch ==','&&pkey3&&s.charAt(i-2)=='"'){
                sb.append('\n');
                sb.append('\t');
                sb.append('\t');
                sb.append('\t');
                continue;
            }
            if (ch ==','&&pkey3&&s.charAt(i-2)==']'){
                sb.append('\n');
                sb.append('\t');
                pkey2 = false;
                pkey3 = false;
                continue;
            }
            if (ch ==','&&pkey3&&s.charAt(i+1)=='"'){
                sb.append('\n');
                sb.append('\t');
                sb.append('\t');
                sb.append('\t');
                sb.append('\t');
                continue;
            }
            if (ch ==','&&!pkey3){
                sb.append('\n');
                sb.append('\t');
                sb.append('\t');
                pkey3 = true;
                continue;
            }

        }
        return sb.toString();
    }


    private static String jsonFormat(String s) {
        StringBuffer sb = new StringBuffer();
        boolean pkey = false, akey = false;
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            sb.append(ch);
            if (i == 0) {
                sb.append('\n');
                sb.append("\t");
                continue;
            }

            if (ch == ',') {
                if (!pkey) {
                    sb.append('\n');
                    sb.append("\t");
                } else {
                    if (akey) {
                        sb.append('\n');
                        sb.append("\t");
                        sb.append("\t");
                        sb.append("\t");
                    } else {
                        sb.append('\n');
                        sb.append("\t");
                        sb.append("\t");
                    }
                }
                continue;
            }
            if (s.charAt(i) == '{') {
                sb.append('\n');
                sb.append("\t");
                sb.append("\t");
                sb.append("\t");
                akey = true;
                continue;
            }
            if (ch == '[') {
                sb.append('\n');
                sb.append("\t");
                sb.append("\t");
                continue;
            }
            if (i + 1 < s.length()) {
                if (s.charAt(i + 1) == ']') {
                    sb.append('\n');
                    sb.append("\t");
                    continue;
                }
                if (ch==']'){
                    sb.append('\n');
                    continue;
                }
                if (s.charAt(i + 1) == '}') {
                    akey = false;
                    sb.append('\n');
                    sb.append("\t");
                    sb.append("\t");
                    continue;
                }


                if (s.charAt(i + 1) == '[') {
                    sb.append('\n');
                    sb.append("\t");
                    pkey = true;
                    continue;
                }


            }

        }

        return sb.toString();

    }

    /**
     * 这个方法是在修改il文件后，之前的il_out的pos的值会和改后的il文件不对应
     * 我们将新的il文件生成的il_out中的pos替换旧的il_out中的值好打包的时候不会出错
     */
    public static void exchangeIlJsonPos() throws IOException {
        //这个是要替换pos的文件路径
        String old_path = "C:\\Users\\Administrator\\Desktop\\记录\\111.il_out2";
        //这个是新生成的il_out的路径
        String new_path = "C:\\Users\\Administrator\\Desktop\\记录\\111.il_out";
        JSONArray oldJa = new JSONArray(fileToString(old_path));
        JSONArray newJa = new JSONArray(fileToString(new_path));
        for (int i = 0; i < newJa.length(); i++) {
            JSONObject newJo = newJa.getJSONObject(i);
            for (int j = 0; j < oldJa.length(); j++) {
                JSONObject oldJo = oldJa.getJSONObject(j);
                //这里是交换行数的
//                if (newJo.getString("ori").equals(oldJo.getString("ori"))&&(newJo.getLong("line")!=oldJo.getLong("line"))){
//                    oldJo.put("line", newJo.getLong("line"));
//                    oldJo.put("ori", newJo.getString("ori")+"hehe");
//                    break;
//                }
                //这个是交换翻译的
                if (newJo.getString("ori").equals(oldJo.getString("ori"))){
                    oldJo.put("cz", newJo.getString("cz"));
                    break;
                }

                //这个去掉不该翻译的
                if (newJo.getString("ori").equals(oldJo.getString("ori"))){
                    if (newJo.getString("cz").equals("")){
                        oldJo.put("cz",newJo.getString("zh"));
                    }

                }

            }
        }
        for (int i = 0; i < oldJa.length(); i++) {
            JSONObject jo = oldJa.getJSONObject(i);
            String ori = jo.getString("ori");
            if (ori.contains("hehe")){
                System.out.println(jo.getLong("line"));
                jo.put("ori",ori.substring(0,ori.indexOf("hehe")));
            }
        }
        //这里的jsonFormat是针对JsonObject的，回公司的时候改下
        writeNewFile(old_path+"_new",jsonFormat2(oldJa.toString()));
    }



    /**
     * 将已翻译号的文本拷到未翻译的文本中jsonobject
     * @throws IOException
     */
    public static void exchangeLevelJsonPos() throws IOException {
        //这个是要替换pos的文件路径
        String old_path = "C:\\Users\\Administrator\\Desktop\\记录\\111.il_out2";
        //这个是以翻译好的文本
        String new_path = "C:\\Users\\Administrator\\Desktop\\记录\\111.il_out";
        JSONObject ojo = new JSONObject(old_path);
        JSONObject njo = new JSONObject(new_path);
        JSONArray oldJa = ojo.getJSONArray("items");
        JSONArray newJa = njo.getJSONArray("items");
        for (int i = 0; i < newJa.length(); i++) {
            JSONObject newJo = newJa.getJSONObject(i);
            for (int j = 0; j < oldJa.length(); j++) {
                JSONObject oldJo = oldJa.getJSONObject(j);
                if (newJo.getString("ori").equals(oldJo.getString("ori"))){
                    oldJo.put("cz", newJo.getString("cz"));
                }
            }
        }

        //这里的jsonFormat是针对JsonObject的，回公司的时候改下
        writeNewFile(old_path+"_new",formatJson(ojo.toString()));
    }


    /**
     * 因为il文件对位置也就是pos的顺序有要求，其他的好像都没有什么要求
     * 这个方法是对il文件排错时导致pos乱序了，将其排序好
     */
    public static void sortIlFile() throws IOException {
        String path = "C:\\Users\\Administrator\\Desktop\\lin临时图片\\sharedassets1.assets.txt";
        JSONArray ja = new JSONArray(fileToString(path));
        //这里nJa可能是以指针的形式指向ja所以不能这么赋值不然后面会出错的
//        JSONArray nJa = ja;
        JSONArray nJa = new JSONArray(fileToString(path));
        List<Long> lines = new ArrayList<Long>();
        int len = ja.length();
        for (int i = 0; i < len; i++) {
            JSONObject jo = ja.getJSONObject(i);
            lines.add(jo.getLong("line"));
//            ja.remove(i);
//            i--;
//            len --;
        }
        /**
         * 这里用了collections的sort方法详情在云笔记
         *  java List 排序 Collections.sort中
         */
        Collections.sort(lines, new Comparator<Long>() {
            @Override
            public int compare(Long o1, Long o2) {
                //这里如果return o2.compareTo(o1)就从大到小的排序了
                return o1.compareTo(o2);
            }
        });
//        System.out.println(lines);
        for (int i = 0; i < lines.size();i++) {
//            System.out.println(lines.get(i));
//            i = 3;
            Long lineNum = lines.get(i);
//            System.out.println(lineNum);
            for (int j = 0; j < nJa.length(); j++) {
                JSONObject nJo = nJa.getJSONObject(j);
                if (nJo.getLong("line")==lineNum){
                    System.out.println(i+"----"+lineNum);
                    ja.put(i,nJo);
                    break;
                }
            }
        }
        //这里回公司后换一下
        writeNewFile(path+"_new",jsonFormat2(ja.toString()));


    }



    public static void sortJsonObjectFile() throws IOException {
        String path = "C:\\Users\\Administrator\\Desktop\\lin临时图片\\sharedassets1.assets.txt";
        JSONObject jo = new JSONObject(fileToString(path));
        JSONArray ja = jo.getJSONArray("items");
        //这里nJa可能是以指针的形式指向ja所以不能这么赋值不然后面会出错的
//        JSONArray nJa = ja;
        JSONObject nJo = new JSONObject(fileToString(path));
        JSONArray nJa = nJo.getJSONArray("items");
        List<Long> lines = new ArrayList<Long>();
        int len = ja.length();
        for (int i = 0; i < len; i++) {
            JSONObject sjo = ja.getJSONObject(i);
            lines.add(sjo.getLong("pos"));
//            ja.remove(i);
//            i--;
//            len --;
        }
        /**
         * 这里用了collections的sort方法详情在云笔记
         *  java List 排序 Collections.sort中
         */
        Collections.sort(lines, new Comparator<Long>() {
            @Override
            public int compare(Long o1, Long o2) {
                //这里如果return o2.compareTo(o1)就从大到小的排序了
                return o1.compareTo(o2);
            }
        });
//        System.out.println(lines);
        for (int i = 0; i < lines.size();i++) {
//            System.out.println(lines.get(i));
//            i = 3;
            Long lineNum = lines.get(i);
//            System.out.println(lineNum);
            for (int j = 0; j < nJa.length(); j++) {
                JSONObject nsJo = nJa.getJSONObject(j);
                if (nsJo.getLong("pos")==lineNum){
                    System.out.println(i+"----"+lineNum);
                    ja.put(i,nsJo);
                    break;
                }
            }
        }
        //这里回公司后换一下
        writeNewFile(path+"_new",jsonFormat2(jo.toString()));


    }

    private static String jsonFormat2(String s) {
        StringBuffer sb = new StringBuffer();
        char pkey=0,akey = 0;
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (i>0){
                pkey = s.charAt(i-1);
            }
            if (i<s.length()-1){
                akey = s.charAt(i+1);
            }
            sb.append(ch);
            if (ch == '['){
                sb.append('\n');
                sb.append('\t');
                continue;
            }
            if (ch == '{'){
                sb.append('\n');
                sb.append('\t');
                sb.append('\t');
                continue;
            }
            if (ch == ','&& akey=='"'){
                sb.append('\n');
                sb.append('\t');
                sb.append('\t');
                continue;
            }
            if (akey=='}'||akey=='{'){
                sb.append('\n');
                sb.append('\t');
                continue;
            }
            if (akey==']'){
                sb.append('\n');
                continue;
            }

        }
        return sb.toString();
    }


    /**
     * 这个方法是针对il文件中有些文字只能改16进制，而il中的16进制是
     * 将32位数的高16位和低16位互相倒着放的，所以我们写了这个工具
     * 他两个交换的时候原来的顺序由高到低是4321调换后是2143；
     */
    public static void ilConvertUtf8ToHex(){
        String str = "我是哈哈哈哈";
        char[] chars = str.toCharArray();
        StringBuffer sb = new StringBuffer();
        for (char c:chars) {
            int[] ints = new int[4];
            //一个汉字由两个字节组成，一个字节等于8位二进制，一个16进制数等于4位二进制
            ints[0] = c & 0x000f;
            ints[1] = c >> 4 & 0x000f;
            ints[2] = c >> 8 & 0x000f;
            ints[3] = c >> 12 & 0x000f;
            //这里我们append一个0x就能弄成将utf-8的字符串转为16进制
            sb.append(Integer.toHexString(ints[1]).toUpperCase()).append(Integer.toHexString(ints[0]).toUpperCase()).append(" ");
            sb.append(Integer.toHexString(ints[3]).toUpperCase()).append(Integer.toHexString(ints[2]).toUpperCase()).append(" ");


        }
        System.out.println(sb.toString());
    }


    //这里的getTxtReader(String,String)方法应该是：
    private static BufferedReader getTxtReader(String path,String charSet) throws FileNotFoundException, UnsupportedEncodingException {
        File file = new File(path);
        InputStreamReader isr = new InputStreamReader(new FileInputStream(file),charSet);
        return new BufferedReader(isr);
    }

    static String SPACE = "\t";
    //这个是最优版的格式化json
    public static String formatJson(String json) {

        StringBuffer result = new StringBuffer();

        int length = json.length();
        int number = 0;
        char key = 0,nkey = 0,pkey =0;
        boolean isInline = false;
        //遍历输入字符串。
        for (int i = 0; i < length; i++) {
            //1、获取当前字符。
            key =json.charAt(i);
            if(i<length-1){
                nkey = json.charAt(i+1);
            }else{
                nkey = key;
            }
            if(i>0){
                pkey = json.charAt(i-1);
            }else{
                pkey = key;
            }
            // 1.1 如果key ” 冒号，标记变化；
            if(key == '"'){
                if(nkey == ':'||nkey=='}'||nkey==','||nkey==']') {
                    isInline = !isInline;
                }else if(pkey==':'||pkey==','||pkey=='{'||pkey=='['){
                    isInline =!isInline;
                }
            }

            if(isInline){
                result.append(key);
                continue;
            }


            //2、如果当前字符是前方括号、前花括号做如下处理：
            if (((key == '[') || (key == '{'))&&(!isInline)) {
                //（1）如果前面还有字符，并且字符为“：”，打印：换行和缩进字符字符串。
                if ((i - 1 > 0) && (json.charAt(i - 1) == ':')) {
                    result.append('\n');
                    result.append(indent(number));
                }

                //（2）打印：当前字符。
                result.append(key);

                //（3）前方括号、前花括号，的后面必须换行。打印：换行。
                result.append('\n');

                //（4）每出现一次前方括号、前花括号；缩进次数增加一次。打印：新行缩进。
                number++;
                result.append(indent(number));

                //（5）进行下一次循环。
                continue;
            }

            //3、如果当前字符是后方括号、后花括号做如下处理：
            if (((key == ']') || (key == '}'))&&(!isInline)) {
                //（1）后方括号、后花括号，的前面必须换行。打印：换行。
                result.append('\n');

                //（2）每出现一次后方括号、后花括号；缩进次数减少一次。打印：缩进。
                number--;
                result.append(indent(number));

                //（3）打印：当前字符。
                result.append(key);

                //（4）如果当前字符后面还有字符，并且字符不为“，”，打印：换行。
                if (((i + 1) < length) && (json.charAt(i + 1) != ',')) {
                    result.append('\n');
                }

                //（5）继续下一次循环。
                continue;
            }



            //4、如果当前字符是逗号。逗号后面换行，并缩进，不改变缩进次数。
            if ((key == ',')&&(!isInline)) {
                result.append(key);
                result.append('\n');
                result.append(indent(number));
                continue;
            }

            //5、打印：当前字符。
            result.append(key);
        }

        return result.toString();
    }

    public String formatJson(Object obj){
        StringBuffer sbuffer = new StringBuffer();
        if(obj instanceof JSONObject){
            indentObj(sbuffer,0,(JSONObject)obj,false);
        }else if(obj instanceof JSONArray){
            indentArr(sbuffer,0,(JSONArray)obj,false);
        }else{
            throw new RuntimeException("not json");
        }
        return sbuffer.toString();
    }

    private void indentObj(StringBuffer sbuffer,int ind,JSONObject jobj,boolean d){
        indSbuffer(sbuffer, ind, "{",d);
        Set<String> keys = jobj.keySet();
        boolean nd = true;
        int num = 0;
        for(String key: keys){
            Object val= jobj.get(key);
            if(++num==keys.size()){
                nd = false;
            }
            if(val instanceof JSONArray){
                indentArr(sbuffer,ind+1,(JSONArray)val,nd);
            }else if(val instanceof JSONObject){
                indentObj(sbuffer, ind + 1, (JSONObject) val,nd);
            }else {
                indSbuffer(sbuffer,ind+1,key+":"+val.toString(),nd);
            }
        }
        indSbuffer(sbuffer, ind, "}",d);
    }

    private void indentArr(StringBuffer sbuffer,int ind,JSONArray jarr,boolean d){
        indSbuffer(sbuffer,ind,"[",d);
        boolean nd = true;
        for(int i=0;i<jarr.length();i++){
            Object val = jarr.get(i);
            if(i == jarr.length()-1){
                nd = false;
            }
            if(val instanceof JSONArray){
                indentArr(sbuffer,ind+1,(JSONArray)val,nd);
            }else if(val instanceof JSONObject){
                indentObj(sbuffer, ind + 1, (JSONObject) val,nd);
            }else {
                indSbuffer(sbuffer,ind+1,val.toString(),nd);
            }
        }
        indSbuffer(sbuffer,ind,"]",d);
    }

    private void indSbuffer(StringBuffer sbuffer,int ind,String cont,boolean d){
        if(d){
            cont+=",";
        }
        for(int i=0;i<ind;i++){
            sbuffer.append(SPACE);
        }
        sbuffer.append(cont).append("\n");
    }

    /**
     * 返回指定次数的缩进字符串。每一次缩进三个空格，即SPACE。
     *
     * @param number 缩进次数。
     * @return 指定缩进次数的字符串。
     */
    private static String indent(int number) {
        StringBuffer result = new StringBuffer();
        for (int i = 0; i < number; i++) {
            result.append(SPACE);
        }
        return result.toString();
    }



    //判断是否包含中文
    public static boolean isChineseChar(String str){
        boolean temp = false;
        //中文区间"[\u4e00-\u9fa5]",
        Pattern p=Pattern.compile("[\u4e00-\u9fa5]");
        Matcher m=p.matcher(str);
        if(m.find()){
            temp =  true;
        }
        return temp;
    }

    public static boolean isnotAscii(String str){
        boolean tmp = false;
        for (char ch:str.toCharArray()){
            if ((byte)ch>0x7f||(byte)ch<0){
                System.out.println(str);
                return true;
            }
        }
        return tmp;
    }


}
