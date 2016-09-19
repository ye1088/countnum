package Utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2016/9/9.
 */
public class CodeChange {



/*

 *  把中文字符串转换为十六进制Unicode编码字符串

 */

    public static String stringToUnicode(String s) {
        String str = "";
        for (int i = 0; i < s.length(); i++) {
            int ch = (int) s.charAt(i);
            if (ch > 255)
                str += "\\u" + Integer.toHexString(ch);
            else
                str += s.charAt(i);
        }
        return str;
    }



/*

 *  把十六进制Unicode编码字符串转换为中文字符串

 */

    public static String unicodeToString(String str) {

        Pattern pattern = Pattern.compile("(\\\\u(\\p{XDigit}{4}))");

        Matcher matcher = pattern.matcher(str);

        char ch;

        while (matcher.find()) {

            ch = (char) Integer.parseInt(matcher.group(2), 16);

            str = str.replace(matcher.group(1), ch + "");

        }

        return str;

    }



    public static void main(String[] args) throws IOException {



        // 直接以Unicode字符串的方式初始化字符串时，会自动

//        String s1 = "\\配\\置\\成\\功\\，\\重\\启\\后\\生\\效";
//
//        System.out.println("s1: " + s1);



        //转换汉字为Unicode码

        String s2 = "\n" +
                "AI = AI\n" +
                "AdjustTheDifficulty = 调节游戏的难度\n" +
                "Afghanistan = 阿富汗\n" +
                "Air = 空中\n" +
                "Airfield = 机场\n" +
                "Airship = 飞艇\n" +
                "Amphibian = 两栖战车\n" +
                "Annihilation = 歼灭\n" +
                "AnnihilationGoalETC = 打败所有的敌军\n" +
                "AppDescriptionTropicalStormfrontCleanETC = 前线风暴(2014版)是一款极具挑战性和趣味性的即时战略游戏。在浩瀚的海洋和岛礁中，建造自己的军队和装备，与敌军展开陆海空三军的较量。在不同的游戏环节中，可以建造主战坦克，装甲车，战略轰炸机，武装直升机，核潜艇，巡洋舰，导弹舰，航母等数十种战斗准备。这款游戏除了单人挑战模式，还有局域网对战和网络对战模式，其中单人挑战模式有25个任务。玩法丰富，精彩不断，准备战斗吧！\n" +
                "AppDescriptionTropicalStormfrontETC = 前线风暴(2014版)是一款极具挑战性和趣味性的即时战略游戏。在浩瀚的海洋和岛礁中，建造自己的军队和装备，与敌军展开陆海空三军的较量。在不同的游戏环节中，可以建造主战坦克，装甲车，战略轰炸机，武装直升机，核潜艇，巡洋舰，导弹舰，航母等数十种战斗准备。这款游戏除了单人挑战模式，还有局域网对战和网络对战模式，其中单人挑战模式有25个任务。玩法丰富，精彩不断，准备战斗吧！\n" +
                "AppOverviewTropicalStormfrontCleanETC = 前线风暴(2014版)是一款极具挑战性和趣味性的即时战略游戏。在浩瀚的海洋和岛礁中，建造自己的军队和装备，与敌军展开陆海空三军的较量。在不同的游戏环节中，可以建造主战坦克，装甲车，战略轰炸机，武装直升机，核潜艇，巡洋舰，导弹舰，航母等数十种战斗准备。这款游戏除了单人挑战模式，还有局域网对战和网络对战模式，其中单人挑战模式有25个任务。玩法丰富，精彩不断，准备战斗吧！\n" +
                "AppOverviewTropicalStormfrontETC = 前线风暴(2014版)是一款极具挑战性和趣味性的即时战略游戏。在浩瀚的海洋和岛礁中，建造自己的军队和装备，与敌军展开陆海空三军的较量。在不同的游戏环节中，可以建造主战坦克，装甲车，战略轰炸机，武装直升机，核潜艇，巡洋舰，导弹舰，航母等数十种战斗准备。这款游戏除了单人挑战模式，还有局域网对战和网络对战模式，其中单人挑战模式有25个任务。玩法丰富，精彩不断，准备战斗吧！\n" +
                "Artillery = 大炮\n" +
                "Auto = 自动\n" +
                "Back = 返回\n" +
                "Base = 基地\n" +
                "BaseStation = 基地\n" +
                "BasesCapturedLost = 占领的设施数\n" +
                "BattleTank = 主战坦克\n" +
                "BlueGold = 蓝色/金黄\n" +
                "BlueLeader = 蓝队队长\n" +
                "BlueWhite = 蓝色/白色\n" +
                "Brazil = 巴西\n" +
                "Bridge = 桥\n" +
                "Buildings = 建筑物\n" +
                "Bullets = 子弹\n" +
                "BuyTheFullVersion = 激活购买完整版本\n" +
                "BuyTheFullVersionETC = 免费版本部分功能不全，激活购买完整版本，可以增加以下功能：\\n\\\n" +
                "\t\t-任务模式可以开通所有任务\\n\\\n" +
                "\t\t-开通对战模式，选择人机对战\\n\\\n" +
                "\t\t-开通网络模式，多人在线对战\\n\\\n" +
                "\t\t-更强的AI和更丰富的设置\\n\\\n" +
                "\t\t\\n\\\n" +
                "\t\tLemeGame\n" +
                "Campaign = 任务模式\n" +
                "CampaignFailureETC = 任务没有达成！再试一次...\n" +
                "CampaignHighScore = 最高得分\n" +
                "CampaignMissionX = 任务 {0}\n" +
                "CampaignOverviewETC = 指挥一场正义与邪恶的战斗.\n" +
                "CampaignReset = 重新开始任务\n" +
                "CampaignResetETC = 您确认要从头开始新的任务吗？\n" +
                "CampaignStoreEndedTropicalStormfrontV01ETC = 您已经成功地完成所有的任务！这证明您的策略是正确且有效的。您已经打败所有的敌军。 奖励:{0}\\n\\\n" +
                "\t\t\\n\\\n" +
                "\t\t感谢您！我们会在将来的任务中考虑您。请等候我们的消息。\\n\\\n" +
                "\t\t\\n\\\n" +
                "\t\t恭喜！\n" +
                "CampaignStoryBeginTropicalStormfrontCleanV01ETC = 世界总是不安宁，愤怒，仇恨与贪婪肆虐。借助强大的经济和军事实力，强者愈强，弱者愈弱，谁将主宰浩瀚的海洋与岛屿，现在谁也说不清楚，唯有不断征服，才能有自己的未来！\n" +
                "CampaignStoryBeginTropicalStormfrontV01ETC = 世界总是不安宁，愤怒，仇恨与贪婪肆虐。借助强大的经济和军事实力，强者愈强，弱者愈弱，谁将主宰浩瀚的海洋与岛屿，现在谁也说不清楚，唯有不断征服，才能有自己的未来！\n" +
                "CampaignSuccessETC = 您已经成功地完成这项任务！ 奖励:{0}\n" +
                "Cancel = 取消\n" +
                "Cannonball = 加农炮\n" +
                "CaptureTheFlag = 夺旗\n" +
                "CaptureTheFlagGoalETC = 夺旗已经有 {0} 秒钟。\n" +
                "Carrier = 运输船\n" +
                "Casual = 休闲\n" +
                "China = 中国\n" +
                "Chinnook = Chinnook\n" +
                "Cliff = Cliff\n" +
                "CommandoUnitX = 战斗单元:{0}\n" +
                "ConnectionMessage = 连接消息\n" +
                "Continue = 继续\n" +
                "ContinueGame = 继续游戏\n" +
                "ContinueGameETC = 您想继续当前的游戏吗？\n" +
                "CountNotConnectETC = 无法连接到游戏。可能原因是：\\n\\\n" +
                "\t\t\\n\\\n" +
                "\t\t1 游戏人满了\\n\\\n" +
                "\t\t2 服务器已关闭\\n\\\n" +
                "\t\t3 网络连接有问题\n" +
                "Create = 创建\n" +
                "Credits = 关于\n" +
                "Cruiser = 巡洋舰\n" +
                "CurrentScore = 当前得分\n" +
                "DamageInflicedSustained = 造成的伤害\n" +
                "Default = 默认\n" +
                "Defeat = 打败\n" +
                "Delete = 删除\n" +
                "DeleteGame = 删除游戏\n" +
                "DeleteGameETC = 您确认要删除当前的游戏吗？\n" +
                "DestroyedBuildings = 被损毁的建筑\n" +
                "Destroyer = 损毁人\n" +
                "DifficultyX = 难度: {0}\n" +
                "Disabled = 无效\n" +
                "DisconnectedETC = 游戏断开。可能原因是：\\n\\\n" +
                "\t\t\\n\\\n" +
                "\t\t1 服务器已关闭\\n\\\n" +
                "\t\t2 网络连接有问题\n" +
                "DisplayAI = 显示AI\n" +
                "DoYouReallyWantToQuitETC = 您确认要退出当前任务吗？相关信息会自动保存。\n" +
                "DoYouReallyWantToRestartETC = 您确认要重新开始游戏吗？当前的游戏信息将丢失。\n" +
                "Down = 向下\n" +
                "Egypt = 埃及\n" +
                "Enabled = 有效\n" +
                "Enemy = 敌人\n" +
                "England = 英格兰\n" +
                "Escort = \\ 护航舰\n" +
                "EscortGoalTeamEnemyETC = 不要让{0} 辆卡车达到目标区域（蓝色），或者摧毁掉所有的敌人。\n" +
                "EscortGoalTeamTruckETC = 保护  {0} 辆卡车到达目标区域（蓝色）或者摧毁掉所有敌人。\n" +
                "ExistingGame = 当前的游戏\n" +
                "ExistingGameCampaignXETC = 已经存在一个游戏任务{0}。如果您决定继续当前的游戏任务将被删除。\n" +
                "ExistingGameETC = 有一个游戏任务正在进行。按 [继续] 可以继续玩，或者按[删除]来创建一个新游戏任务。\n" +
                "ExistingMultiplayerGameETC = 有一个多人模式的游戏存在。您想继续游戏吗？\n" +
                "ExistingMultiplayerJoinOrHostETC = 您想重新加入游戏吗？或者创建一个多人模式的游戏？\n" +
                "Exit = 退出\n" +
                "ExternalStorage = SD卡\n" +
                "ExternalStorageMissingETC = 无法找到SD卡，请确保手机上插入SD卡，或者将手机与PC断开以保证SD能正常使用。\n" +
                "Extreme = 极难\n" +
                "Farmland = 农田\n" +
                "FighterPlane = 战斗机\n" +
                "FlagBattle = 权力之战\n" +
                "Flora = Flora\n" +
                "Forums = 论坛\n" +
                "France = 法国\n" +
                "FullScreen = 全屏\n" +
                "FurtherInformationETC = 欢迎访问乐米互动的网址以及了解最近的新闻。也可以到我们的论坛中参与讨论。\n" +
                "GameWillBeHostedAsXGame = 创建的游戏服务器：“游戏: {0}”\n" +
                "GamesPlayed = 已玩次数\n" +
                "GamesWon = 获胜次数\n" +
                "General = 一般\n" +
                "Germany = 德国\n" +
                "Ground = 地面\n" +
                "Group = 编队\n" +
                "Gunboat = 炮艇\n" +
                "Hard = 困难\n" +
                "Helicopter = 武装直升机\n" +
                "HighScore = 最高分数\n" +
                "Host = 建游戏服务器\n" +
                "HostAGame = 创建一个游戏服务器\n" +
                "HoursPlayed = 已玩时间（小时)\n" +
                "Hovercraft = 水翼船\n" +
                "Human = 人\n" +
                "Humvee = 悍马\n" +
                "IncomeX = 奖励:{0}\n" +
                "India = 印度\n" +
                "InformationAIETC = AI 是人工智能的意思，这个技术在你为游戏设置不同难度的时候，可以使获得的奖励也不一样。难度越大，奖励越多！\n" +
                "#  \n" +
                "InformationControlsETC = \n" +
                "InformationFunctionsETC = \\ \n" +
                "InformationHostingETC = 请注意移动战斗单元可以通过左侧的控制面板进行修理，飞行单元可以在控制面板上设置为自动巡航。\n" +
                "InformationListMoveablesDesertStormfrontETC = \\ \n" +
                "InformationListMoveablesTropicalStormfrontETC = \\ \n" +
                "InformationListStructuresDesertStormfrontETC = \\ \n" +
                "InformationListStructuresTropicalStormfrontETC = \\ \n" +
                "InformationMultiplayerETC = 最好在WiFi环境下玩多人模式，如果运营商提供流媒体支持，也可以在3G环境下运行。在Internet环境下，你还可以自己创建服务器，提供给多人联网对战。创建这种服务器需要调整网络端口，有时候跟你的无线路由器的设置也有关系。\n" +
                "InformationUnitsTropicalStormfrontETC = 基地中可以建造新的战斗单元，根据您的成绩，每隔{0}秒可以获得一些奖励。值得说明的是，只有主战坦克才能够接管敌军的基地。\n" +
                "InformationXPPointsETC = \\ \n" +
                "InitializingMap = 正在初始化地图\n" +
                "InternetGame = 网络游戏\n" +
                "Iran = 伊朗\n" +
                "Iraq = 伊拉克\n" +
                "Island = 岛屿\n" +
                "Italy = 意大利\n" +
                "Japan = 日本\n" +
                "Join = 加入\n" +
                "JoinOrHost = 加入或创建\n" +
                "JoiningGame = 正在加入游戏...\n" +
                "KeywordsTropicalStormfrontETC = 战争，实时，策略，游戏，战斗，即时策略，防御，战役，坦克，捕获，生存，捕获旗帜，海洋，陆地，空中，多人在线，局域网，互联网\n" +
                "LANGameETC = 局域网游戏\n" +
                "LANGameOnlyETC = 为了能在Inernet上玩这款游戏，请注意：\\n\\\n" +
                "\t\t\\n\\\n" +
                "\t\t(A) 在路由器上开启UPnP\\n\\\n" +
                "\t\t(B) 调整路由器的端口到 {0} \\n\\\n" +
                "\t\t(C) 直接连接到互联网\n" +
                "LanguageDefault = 语言：默认\n" +
                "Later = 稍后\n" +
                "Left = 向左\n" +
                "Loading = 正在加载\n" +
                "Manual = 游戏教程\n" +
                "Map = 地图\n" +
                "Mechanic = 机械\n" +
                "Menu = 菜单\n" +
                "MiscFeaturesTropicalStormfrontCleanETC = \\ \n" +
                "MiscFeaturesTropicalStormfrontETC = \\ \n" +
                "MiscNotesTropicalStormfrontETC = 重要提示：该游戏要求屏幕最低分辨率为800*480。请确认试用版使用没有问题后再确认是否激活购买完整版本。\\n\\\n" +
                "\t\t\\n\\\n" +
                "\t\t\\n\\\n" +
                "\t\tLemeGame\\n\\\n" +
                "\t\t\\n\\\n" +
                "\t\thttp://www.lemegame.com\n" +
                "MiscPromoTropicalStormfrontETC = 前线风暴(2014版)，实时对战，精彩无限！\n" +
                "Missile = 导弹\n" +
                "MissileTank = 导弹坦克\n" +
                "Mission001DescriptionTropicalStormfrontV01CleanETC = 敌人已经在附近建立了一个基地，在没构成威胁前，想办法把它毁掉并接管它。\n" +
                "Mission001DescriptionTropicalStormfrontV01ETC = 某国已经在瓜达卢佩岛建立了一个前线基地，你需要尽快拿下这个基地。\n" +
                "Mission002DescriptionTropicalStormfrontV01CleanETC = 敌人在你附近出现，你需要建造海军力量，随时与其展开战斗。\n" +
                "Mission002DescriptionTropicalStormfrontV01ETC = 某国在巴尔米拉环礁附近出现，你需要建造一支海军力量，对其发起攻击。\n" +
                "Mission003DescriptionTropicalStormfrontV01CleanETC = 敌人已经修理好基地，正在造一批空中作战力量，你必须带领一支海军中队，干掉它!\n" +
                "Mission003DescriptionTropicalStormfrontV01ETC = 某国及其盟友已经修理好约翰斯顿环礁的基地，正在建造空军。你需要领导一支海军中队，消灭他们。\n" +
                "Mission004DescriptionTropicalStormfrontV01CleanETC = 你已经在敌人的炮火下，请坚持住，直到援军到达。\n" +
                "Mission004DescriptionTropicalStormfrontV01ETC = 你的盟友在福克兰群岛受到敌人的攻击，情况非常危急，他们必须坚持住直到援军到来。\n" +
                "Mission005DescriptionTropicalStormfrontV01CleanETC = 在敌人的攻击下，你幸存下来，造些飞机反攻吧。\n" +
                "Mission005DescriptionTropicalStormfrontV01ETC = 在对战中幸存下来后，跟你的盟友一起，发起反攻吧！\n" +
                "Mission006DescriptionTropicalStormfrontV01CleanETC = 派出你的飞行中队，尽快拿下附近岛屿的敌军基地。\n" +
                "Mission006DescriptionTropicalStormfrontV01ETC = 这场战争没那么简单，准备好，迎接新的战斗！\n" +
                "Mission007DescriptionTropicalStormfrontV01CleanETC = 你必须进入敌人的领土，夺取其基地。\n" +
                "Mission007DescriptionTropicalStormfrontV01ETC = 皮特克恩岛战役，必须将敌人赶跑。\n" +
                "Mission008DescriptionTropicalStormfrontV01CleanETC = 获胜之后，又将面临新的挑战。\n" +
                "Mission008DescriptionTropicalStormfrontV01ETC = 在皮特克恩岛获胜后，又在新喀里多尼亚遇到了敌军。\n" +
                "Mission009DescriptionTropicalStormfrontV01CleanETC = 你必须保护好自己的领土，不让敌人侵犯，祝你好运！\n" +
                "Mission009DescriptionTropicalStormfrontV01ETC = 保卫夏威夷，阻止其他敌人的进犯。\n" +
                "Mission010DescriptionTropicalStormfrontV01CleanETC = 幸存下来后，你还必须征战，以获得更多的领土。\n" +
                "Mission010DescriptionTropicalStormfrontV01ETC = 收复了夏威夷后，必须继续收复中途岛。\n" +
                "Mission011DescriptionTropicalStormfrontV01CleanETC = 你必须从敌人那里拿到这个岛屿的控制权，才能继续新的任务。\n" +
                "Mission011DescriptionTropicalStormfrontV01ETC = 两支部队正在试图夺取南鸟岛。\n" +
                "Mission012DescriptionTropicalStormfrontV01CleanETC = 你需要把这个岛屿抢到手。\n" +
                "Mission012DescriptionTropicalStormfrontV01ETC = 两支军队为了争夺这个地区，只有通过战争来解决。\n" +
                "Mission013DescriptionTropicalStormfrontV01CleanETC = 在你获得该地区的控制权后，敌人正试图卷土重来。\n" +
                "Mission013DescriptionTropicalStormfrontV01ETC = 在某国北部地区，两支部队正在为谁获得该地区的控制权而较量。\n" +
                "Mission014DescriptionTropicalStormfrontV01CleanETC = 从多个方向保卫好你的岛链，直到援军到达。\n" +
                "Mission014DescriptionTropicalStormfrontV01ETC = 在北马里亚纳群岛，敌军与盟军双方展开了一场新的战斗。\n" +
                "Mission015DescriptionTropicalStormfrontV01CleanETC = 你正在被攻击，你前方的一些基地也被占领了，是否要撤退？\n" +
                "Mission015DescriptionTropicalStormfrontV01ETC = 在大东群岛附近，一场战斗已经开始。\n" +
                "Mission016DescriptionTropicalStormfrontV01CleanETC = 你将去争夺一个重要岛屿的控制权。\n" +
                "Mission016DescriptionTropicalStormfrontV01ETC = 你需要和敌军争夺父岛的控制权。\n" +
                "Mission017DescriptionTropicalStormfrontV01CleanETC = 敌人试图卷土重来，小心为妙！\n" +
                "Mission017DescriptionTropicalStormfrontV01ETC = 敌军联合起来试图重新占领皮特克恩岛。\n" +
                "Mission018DescriptionTropicalStormfrontV01CleanETC = 敌人正在朝你挺进，小心哦！\n" +
                "Mission018DescriptionTropicalStormfrontV01ETC = 资源消耗殆尽，面对敌军，你一定要坚持住！\n" +
                "Mission019DescriptionTropicalStormfrontV01CleanETC = 你将面临一场大规模的战斗，千万别输掉了！\n" +
                "Mission019DescriptionTropicalStormfrontV01ETC = 这是中途岛的一场战役，准备开战吧。\n" +
                "Mission020DescriptionTropicalStormfrontV01CleanETC = 冲突仍然在继续，你必须征服你的敌人，让其投降！\n" +
                "Mission020DescriptionTropicalStormfrontV01ETC = 一场战争即将结束，无论哪方率先占领首都，都将赢得胜利。\n" +
                "Mission021DescriptionTropicalStormfrontV01CleanETC = 从敌军夺取备受争议的领土。\n" +
                "Mission021DescriptionTropicalStormfrontV01ETC = 抢夺琉球岛。\n" +
                "Mission022DescriptionTropicalStormfrontV01CleanETC = 你的一个中队将攻击敌人，让他在家门口无地自容！\n" +
                "Mission022DescriptionTropicalStormfrontV01ETC = 进军九州，这里有敌方盟军把守，看上去你有实力战胜他们。\n" +
                "Mission023DescriptionTropicalStormfrontV01CleanETC = 面对敌人的进犯，你必须坚持并幸存下来，看你的了！\n" +
                "Mission023DescriptionTropicalStormfrontV01ETC = 这是一场防御战，看你能否变被动为主动，加油！\n" +
                "Mission024DescriptionTropicalStormfrontV01CleanETC = 你的敌人已经疲惫不堪，看起来很快就会被击败了，全力出击！\n" +
                "Mission024DescriptionTropicalStormfrontV01ETC = 为了营救盟友，你必须战胜另外两支部队，他们会很快投降吗？\n" +
                "Mission025DescriptionTropicalStormfrontV01CleanETC = 发动海军力量，把敌人的领地夺取过来。\n" +
                "Mission025DescriptionTropicalStormfrontV01ETC = 为了自由和平，我们必须联合起来击败侵略者，尽早结束这场战争！\n" +
                "MissionObjective = 任务目标\n" +
                "MissionX = 任务 {0}:\n" +
                "MissionXCompleted = 任务 {0} 已完成.\n" +
                "MissionXLocked = 任务 {0} 已锁定.\n" +
                "MissionXNotAvailableLiteETC = 任务 {0} 在Lite版中无法开启.\n" +
                "MoneyX = 余额: {0}\n" +
                "Mosque = 清真寺\n" +
                "Mountain = 山地\n" +
                "MouseRTSModeETC = \\ \n" +
                "Move = 移动\n" +
                "Multiplayer = 多人对战\n" +
                "MultiplayerFailureETC = 您已经被水平更高的对手击败\n" +
                "MultiplayerPort = 多人对战网络端口: {0}\n" +
                "MultiplayerSuccessETC = 你已经成功打败敌人。\n" +
                "MusicOff = 音效: 关闭\n" +
                "MusicPercentX = 音效 FX: {0}%\n" +
                "NA = N/A\n" +
                "NationX = 选择国家:{0}\n" +
                "Network = 网络\n" +
                "NewGame = 新游戏\n" +
                "Next = 下一步\n" +
                "NoGamesFoundETC = 游戏扫描中...\n" +
                "None = 无\n" +
                "Normal = 正常\n" +
                "OK = 确定\n" +
                "Oasis = Oasis\n" +
                "OilField = 油田\n" +
                "Options = 选项\n" +
                "OrangeBlack = 橙色/黑色\n" +
                "OrangeBlackStriped = 橙色/黑色条纹\n" +
                "OrangeWhite = 橙色/白色\n" +
                "OrangeWhiteStriped = 橙色/白色条纹\n" +
                "Patrol = 巡逻\n" +
                "Play = 开始\n" +
                "PlayTutorial = 游戏教程\n" +
                "PlayTutorialETC = 是否开始游戏教程？\n" +
                "Player = 玩家\n" +
                "PlayersX = 玩家: {0}\\ \n" +
                "Purchase = 购买\n" +
                "Quit = 退出\n" +
                "QuitGame = 退出游戏\n" +
                "Rally = 集合\n" +
                "RandomCaptureFlagXY = 随机: 夺旗战斗 {0}x{0}\n" +
                "RandomCaptureFlagXYMem = 随机: 夺旗战斗 {0}x{0}(有足够内存?)\n" +
                "RandomDefenseXY = 随机: 防御 {0}x{0}\n" +
                "RandomDefenseXYMem = 随机: 防御 {0}x{0}(有足够内存?)\n" +
                "RandomSupremacyXY = 随机: 权力之战 {0}x{0}\n" +
                "RandomSupremacyXYMem = 随机: 权力之战 {0}x{0}(有足够内存?)\n" +
                "Randomize = 随机选择\n" +
                "Ravine = Ravine\n" +
                "RealTimeStrategy = 即时战略\n" +
                "Rebels = 反叛者\n" +
                "ReconnectMultiplayerGameETC = 你想重新连接或创建游戏吗？\n" +
                "RedBlack = 红色/黑色\n" +
                "RedGold = 红色/金色\n" +
                "RedLeader = 红队领队\n" +
                "RenderDetails = 渲染细节\n" +
                "Repair = 修理\n" +
                "Reset = 重置\n" +
                "ResetTheCampaignETC = 重置任务(无法撤销）\n" +
                "Restart = 重新开始\n" +
                "RestartGame = 重新开始游戏\n" +
                "Resume = 继续\n" +
                "Right = 向右\n" +
                "Russia = 俄国\n" +
                "SaudiArabia = 沙特阿拉伯\n" +
                "ScanningGames = 正在扫描游戏...\n" +
                "ScrollKeyXisY = 滚动键 {0}: {1}\n" +
                "ScrollSpeed = 滚动速度: {0}px/s\n" +
                "Seagulls = 海鸥\n" +
                "SelectNation = 选择国家\n" +
                "Shell = Shell\n" +
                "Shipyard = 船坞\n" +
                "Skirmish = 战役模式\n" +
                "SkirmishFailureETC = 你已经被击败！\n" +
                "SkirmishSuccessETC = 你已经完成目标任务！\n" +
                "SoundFXOff = 音效 FX: 关\n" +
                "SoundFXPercentX = 音效 FX: {0}%\n" +
                "Sparrows = 麻雀战法\n" +
                "Split = 分离\n" +
                "Squad = 中队\n" +
                "Start = 开始\n" +
                "Statistics = 统计信息\n" +
                "Stop = 停止\n" +
                "Submarine = 潜艇\n" +
                "Supremacy = 权力之争\n" +
                "SupremacyGoalETC = 打败所有的对手，拿下 {0} 个建造单位.\n" +
                "Survival = 幸存\n" +
                "SurvivalGoalAttacker1ETC = 打败 {0}.\n" +
                "SurvivalGoalAttacker1ExtraETC = 打败 {0} ，并给他们最多留下 {1} 个基地.\n" +
                "SurvivalGoalAttacker2ETC = 打败 {0} 和 {1}.\n" +
                "SurvivalGoalAttacker2ExtraETC = 打败 {0} 和 {1}，并给他们最多留下 {2} 个基地.\n" +
                "SurvivalGoalAttacker3ETC = 打败 {0}, {1} 和 {2}.\n" +
                "SurvivalGoalAttacker3ExtraETC = 打败 {0}，{1} 和 {2}，并给他们最多留下 {3} 个基地.\n" +
                "SurvivalGoalAttacker4ETC = 打败 {0},，{1}， {2} 和 {3}.\n" +
                "SurvivalGoalAttacker4ExtraETC = 打败 {0},，{1}， {2} 和 {3}，并给他们最多留下 {4} 个基地.\n" +
                "SurvivalGoalETC = 活着\n" +
                "SurvivalGoalExtra1ETC = 至少保卫好一个基地\n" +
                "SurvivalGoalExtraXETC = 至少保卫好 {0} 个基地\n" +
                "TeamA = A 队\n" +
                "TeamB = B 队\n" +
                "TeamC = C 队\n" +
                "TeamD = D 队\n" +
                "TeamsX = 队伍: {0}\n" +
                "TestingFailureETC = 噢，你在测试场景中失败了。\n" +
                "TestingSuccessETC = 你已经成功完成测试场景\n" +
                "TimeLimitETC = 时间限制是 {0} 分钟.\n" +
                "TimeLimitX = 时间限制: {0}\n" +
                "Torpedo = Torpedo\n" +
                "TouchScrolling = 触屏滚动\n" +
                "Transport = 运输\n" +
                "TropicalStormfront = 前线风暴（2014版）\n" +
                "TropicalStormfrontLite = 前线风暴（试用版）\n" +
                "Truck = 卡车\n" +
                "Tutorial = 游戏教程\n" +
                "Tutorial00TropicalStormfrontETC = 欢迎使用游戏教程，我们将尽力为您讲解清楚怎么玩这款实时战略游戏 :-）\n" +
                "Tutorial01TropicalStormfrontETC = 前线风暴(2014版)是一款即时战略游戏. 您接下来的目标是要完成下面文本框中的任务.\n" +
                "Tutorial02TropicalStormfrontETC = 为了玩得更流畅，游戏需要初始化地图资源。请稍等一下...\n" +
                "Tutorial03TropicalStormfrontETC = 上面这个数字表示可以购买战斗单元的剩余金额。每隔80秒，会自动根据您的战斗结果，给您一些奖励。\n" +
                "Tutorial04TropicalStormfrontETC = 使用面这排按钮可以组队。按下这个按钮，然后点击地图拖动手指，选取要组队的战斗单元。\n" +
                "Tutorial05TropicalStormfrontETC = 绿色箭头指向的建筑是您自己的。它是您的基地，可以在这里造地面战斗单元。\n" +
                "Tutorial06TropicalStormfrontETC = 下面这个基地表示您的敌人。\n" +
                "Tutorial07TropicalStormfrontETC = 为了能攻击敌人，让我们先来造一辆主战坦克吧。请注意只有主战坦克才能占领敌人的基地哦。\\n\\\n" +
                "\t\t\\n\\\n" +
                "\t\t任务：点击自己的基地，然后选择下面的按钮造一辆坦克。\n" +
                "Tutorial08TropicalStormfrontETC = 非常好！请点击X，关闭基地控制面板。\\n\\\n" +
                "\t\t\\n\\\n" +
                "\t\t任务：关闭基地控制面板。\n" +
                "Tutorial09BTropicalStormfrontETC = 让我们看看怎么改变游戏视图，请用你的手指滑动地图。\n" +
                "Tutorial09TropicalStormfrontETC = 在造坦克的时候，我们练习一下如何改变游戏视图。您可以用手挪动地图。\\n\\\n" +
                "\t\t\\n\\\n" +
                "\t\t任务：挪动一下地图。\n" +
                "Tutorial10BTropicalStormfrontETC = 很棒！下面是一个运输船。我们将用这艘运输船把造好的坦克输送到敌人的岛上。\\n\\\n" +
                "\t\t\\n\\\n" +
                "\t\t注意：点击坦克，把它移动到目标位置。\n" +
                "Tutorial10TropicalStormfrontETC = 不错！下面是一个运输船。我们将用这艘运输船把造好的坦克输送到敌人的岛上。\n" +
                "Tutorial11TropicalStormfrontETC = 好的，您的坦克应该造好了。让我们把它开到运输船上去。\\n\\\n" +
                "\t\t\\n\\\n" +
                "\t\t任务：点击基地，然后选中黄色的坦克。\n" +
                "Tutorial12TropicalStormfrontETC = 让我们把坦克开到运输船上去。\\n\\\n" +
                "\t\t\\n\\\n" +
                "\t\t任务：点一下运输船，我们把坦克放进运输船。\n" +
                "Tutorial13TropicalStormfrontETC = 很好！坦克接下来会开进运输船...\n" +
                "Tutorial14TropicalStormfrontETC = 我们把运输船向东移动到敌人所在的岛上。\\n\\\n" +
                "\t\t\\n\\\n" +
                "\t\t任务：点击运输船，向东方向，点击敌人的海岸线。\n" +
                "Tutorial15TropicalStormfrontETC = 对了！我们稍等一下，直到运输船到达敌人的海岸上。\n" +
                "Tutorial16TropicalStormfrontETC = 我们来用坦克攻击并占领敌人的基地吧。\\n\\\n" +
                "\t\t\\n\\\n" +
                "\t\t任务：点击一下运输船，然后选中您的坦克。\n" +
                "Tutorial17TropicalStormfrontETC = 请命令坦克攻击敌人的基地。\\n\\\n" +
                "\t\t\\n\\\n" +
                "\t\t任务：点击敌人的基地，把坦克移动到那儿去。\n" +
                "Tutorial18TropicalStormfrontETC = 很棒！我们歇一会儿。我们来看您的坦克是怎样摧毁敌人基地的吧...\n" +
                "Tutorial19TropicalStormfrontETC = 真不错！也不是很难对吧？您已经学会了怎么摧毁敌人的方法了:)\n" +
                "Tutorial20TropicalStormfrontETC = 请注意，用攻击敌人基地一样的方法，您也可以攻击敌人的移动战斗单元。\\n\\\n" +
                "\t\t\\n\\\n" +
                "\t\t注意哦，保护好您的核心单元，核心单元被摧毁，您就输了哦！\n" +
                "Tutorial21TropicalStormfrontETC = [巡逻]  可以使战斗单元在两点之间来回巡逻。[修理] 可以将受损的战斗单元送回基地修理好。双击移动中的战斗单元可以使其停止移动。[自动] 可以让飞机自动巡弋.\n" +
                "Tutorial22TropicalStormfrontETC = 请参考游戏教程，或者访问其他游戏论坛，更多了解游戏的相关信息以及使用技巧。\n" +
                "TutorialFailureETC = 真遗憾，游戏教程学得不够好！再来一遍?\n" +
                "TutorialSuccessETC = 游戏介绍完了，祝您玩得开心！\n" +
                "USA = 美国\n" +
                "Under = 下面\n" +
                "UnitSpeedsNormal = 移动速度：正常\n" +
                "UnitSpeedsX = 移动速度: {0}x\n" +
                "UnitsDestroyedLost = 击毁敌军数量\n" +
                "Up = 向上\n" +
                "Upper = 上面\n" +
                "Victory = 胜利！\n" +
                "ViewDefault = 视图：默认\n" +
                "ViewExplorationAndFog = 地图：探索+迷雾\n" +
                "ViewExplorationOnly = 地图：探索\n" +
                "ViewFogOnly = 地图：迷雾\n" +
                "ViewNoExplorationFog = 地图：无探索无迷雾\n" +
                "Volcano = 火山\n" +
                "WaitingForPlayers = 等待其他玩家...\n" +
                "Warehouse = 仓库\n" +
                "Water = 水\n" +
                "Website = 网站\n" +
                "WhatsNew = 更新内容\n" +
                "4x4 = 4x4\n" +
                "AppDescriptionDesertStormfrontCleanETC = 沙漠风暴是一款及时性战略游戏(RTS)，战役地点是在荒凉的沙漠上。你要在炎热的沙漠上与敌人争夺能源和石油。你所能够部署的单位有运输车，坦克，大炮，技工，直升机，飞机，舰船和潜艇以及其他的单位。沙漠风暴可支持多人在线局域网对战。\\n\\n游戏里有30种自定义战役，任务包括需要在沙漠的主要城市上指挥布置你的单位来进行作战、抢夺石油和重要的战略军事机构。\\n\\n沙漠风暴中除了30种自定义战役之外，还设有一个随机地图生成器，随机地图任务包括消灭敌人力量、获得敌方战旗、抵御敌方进攻的装甲部队、护航任务、海战及空战，当然还有坦克战役。游戏会实时统计和显示玩家分数。\n" +
                "AppDescriptionDesertStormfrontETC = 沙漠风暴是一款及时性战略游戏(RTS)，战役地点在中东。你要在炎热的沙漠上与敌人争夺能源和石油。你所能够部署的单位有运输车，坦克，大炮，技工，直升机，飞机，舰船和潜艇以及其他的单位。沙漠风暴可支持多人在线局域网对战。\\n\\n游戏里有30种自定义战役，任务包括需要在中东的主要城市上指挥西部联盟来进行作战、抢夺石油和重要的战略军事机构。其中参与的国家有美国、英国、法国、意大利、伊拉克、伊朗、沙特阿拉伯、埃及、阿富汗、外加一个反派组织。\\n\\n沙漠风暴中除了30种自定义战役之外，还设有一个随机地图生成器，随机地图任务包括消灭敌人力量、获得敌方战旗、抵御敌方进攻的装甲部队、护航任务、海战及空战，当然还有坦克战役。游戏会实时统计和显示玩家分数。\n" +
                "AppOverviewDesertStormfrontCleanETC = 沙漠风暴是一款及时性战略游戏(RTS)，战役地点是在荒凉的沙漠上。你要在炎热的沙漠上与敌人争夺能源和石油。你所能够部署的单位有运输车，坦克，大炮，技工，直升机，飞机，舰船和潜艇以及其他的单位。沙漠风暴可支持多人在线局域网对战。\n" +
                "AppOverviewDesertStormfrontETC = 战役地点在中东。你要在炎热的沙漠上与敌人争夺能源和石油。你所能够部署的单位有运输车，坦克，大炮，技工，直升机，飞机，舰船和潜艇以及其他的单位。沙漠风暴可支持多人在线局域网对战。\n" +
                "BeachFlora = 海滩上的植物\n" +
                "CampaignStoreEndedDesertStormfrontV01ETC = 你已经成功完成了所有任务\\! 你的战略非常成功，你击败了所有的敌人，获得奖励点数 \\: {0}\\n\\n非常感谢\\! 我们也将考虑把未来的任务交给你，也请你为了各自的未来与我们进行合作。\\n\\n恭喜您\\!\\n最高指挥官。\n" +
                "CampaignStoryBeginDesertStormfrontV01CleanETC = 沙漠地区是个不安定地带，某方的一点动机都可能会引发战争，但石油绝对是大家的共同目标，丰富的石油是非常抢手的，石油是支撑现代文明不可或缺的资源之一，没有了石油，国家甚至会分崩离析。\\n\\n这是战争前的黎明，沙漠的石油拥有国开始无视那些水资源供给国的要求，停止向这几个国家供给石油了。沙漠国家渴望获得权力，所以他们联合起来开始建立了一个武器库可以随时随地攻击任何地方。\\n\\n为了阻止他们，水资源供给国也联合起来制定了一个计划，目标是占领沙漠中的主要城市、油田和战略军事机构。一旦控制这些关键地点，水资源供给国联盟将会和沙漠的石油拥有国进行政府间的谈判，答应会把土地归还给沙漠的人民，但是沙漠的石油拥有国不会这么容易就屈服的...\\n\n" +
                "CampaignStoryBeginDesertStormfrontV01ETC = 中东地区是个不安定地带，某方的一点动机都可能会引发战争，但近几年来中东地区的丰富石油资源都是各国想要争抢的目标， 丰富的石油是非常抢手的，石油是支撑现代文明不可或缺的资源之一，没有了石油，国家甚至会分崩离析。\\n\\n这是战争前的黎明，伊朗已经开始无视西方国家的要求，停止向几个国家供给石油了。为了能够拥有权力，所以伊朗建立了一个核武器库，美国和法国的机器爆炸事件都可以和伊朗政府扯上联系。\\n\\n为了阻止他们，美国和法国联合起来制定了一个计划，目标是占领伊朗的主要城市、油田和战略军事机构，包括周边地区。一旦控制这些关键地点，西方国家联盟将会与伊朗政府谈判，并把土地归还给伊朗人民。但他们会发现，中东国家不会轻易屈服的...\\n\n" +
                "DesertStormfrontLite = 沙漠风暴精简版\n" +
                "DesertStormfront = 沙漠风暴\n" +
                "InformationUnitsDesertStormfrontETC = 游戏特色，17种移动单位还有4种基础单位。基础单位允许你建造新的单位。油田会提供每秒{0}的固定收入。运输车是唯一能够接管油田的单位。\n" +
                "KeywordsDesertStormfrontETC = 战争，及时性战略，游戏，战斗，RTS，防御，战役，坦克、捕获、生存、夺旗，海，陆，空，沙漠，多人、局域网、互联网\n" +
                "MiscFeaturesDesertStormfrontCleanETC = - 及时性战略(RTS)沙漠战役\\n- 单人游戏和多人(局域网、互联网)\\n- 30种任务\\n- 随机地图生成器\\n- 16种移动单位包括\\: 运输车，坦克，大炮，4x4，技工，卡车，飞机，直升机，炮艇，潜艇，巡洋舰和运输器\\n- 1种特殊单位\\:超级气垫船\\n- 4种基础单位\\:基地、造船厂、机场、油田\\n- 团队游戏 (包含于多人游戏)\\n- 实时统计和显示玩家得分\\n- 战争谜团/调查谜团\\n- 与电脑对战也有4种不同的挑战难度\\n- 宏大的音乐及音效\\n\n" +
                "MiscFeaturesDesertStormfrontETC = - 及时性战略(RTS)中东战役\\n- 及时性战略\\n- 30种任务\\n- 随机地图生成器\\n- 16种移动单位包括\\: 运输车，坦克，大炮，4x4，技工，卡车，飞机，直升机，炮艇，潜艇，巡洋舰和运输器\\n- 1种特殊单位\\: 超级气垫船\\n- 4种基础单位\\: 基地、造船厂、机场、油田\\n- 国家\\: 美国，英国，法国，意大利，伊拉克，伊朗，沙特阿拉伯，埃及，阿富汗，外加一个反派组织。\\n- 团队游戏 (包含于多人游戏)\\n- 实时统计和显示玩家得分\\n- 战争谜团/调查谜团\\n- 与电脑对战也有4种不同的挑战难度\\n- 宏大的音乐及音效\\n\n" +
                "MiscNotesDesertStormfrontETC = 注意\\: 游戏需要一个800x480像素或更高的屏幕分辨率。虽然游戏仍然运行在一个较低的分辨率，而不是所有的图形用户界面元素都可以被正确地渲染。经过测试，游戏在30+帧/秒的情况下就可以运行，但可能在某些设施上运行会比较缓慢，先使用精简版进行游戏，核实正确的功能的功能后在购买正版游戏。如果运行游戏遇到什么问题，可以尝试(1)重新启动(i.e. 完全关闭)或者(2)结束re-install.\\n\\n精简版游戏包括4场战役可以供您试玩，无时间限制，不包含任何间谍软件，恶意软件或第三方软件。再次声明，请先用精简版进行游戏，如果您有任何问题或不满意您的购买，请随时通过电子邮件与我们联系。我们会无条件进行退款，包括您的\\#信息、订单等\\n\\n如果您喜欢这种热带环境下的战略游戏还请关注一下战略游戏热带风暴\\:)\\n\\n感谢您对本游戏的支持以及购买\\!\\nnoblemaster\\n\\nTwitter\\: http\\://twitter.com/noblemaster\n" +
                "MiscPromoDesertStormfrontETC = 沙漠风暴，及时性沙漠战役\\!\n" +
                "Mission001DescriptionDesertStormfrontV01CleanETC = 部署你的部队和你的第一个敌人进行战斗，去占领他们的基地。\n" +
                "Mission001DescriptionDesertStormfrontV01ETC = 在科威特附近部署进队，阿巴丹(伊朗)将成为美国的第一个目标。\n" +
                "Mission002DescriptionDesertStormfrontV01CleanETC = 这里发生了反派军暴乱，请铲除所有反派军\\! 增援部队正在赶来的路上。\n" +
                "Mission002DescriptionDesertStormfrontV01ETC = 这里发生了反派军暴乱，请铲除所有反派军\\! 法国增援部队正在赶来的路上。\n" +
                "Mission003DescriptionDesertStormfrontV01CleanETC = 由于桥梁被摧毁，转移到下个过渡点失败，你必须要找到一个方法来保证运输卡车的安全。\n" +
                "Mission003DescriptionDesertStormfrontV01ETC = 由于桥梁被摧毁，转移到下个过渡点【设拉子（伊朗）】失败，你必须要找到一个方法来保证运输卡车的安全。\n" +
                "Mission004DescriptionDesertStormfrontV01CleanETC = 核压缩设施已经建成，但重要的是，我们停止了所有该设施的生产。\n" +
                "Mission004DescriptionDesertStormfrontV01ETC = 核压缩设施已在阿巴丹外建成，但重要的是，我们停止了所有该设施的生产。\n" +
                "Mission005DescriptionDesertStormfrontV01CleanETC = 你发动了你的第一次进攻。祝你好运\\!\n" +
                "Mission005DescriptionDesertStormfrontV01ETC = 阿富汗和伊拉克已加入伊朗的联盟。英国攻击的第一目标，阿兹祖巴耶（伊拉克）。\n" +
                "Mission006DescriptionDesertStormfrontV01CleanETC = 你发起了一次进攻，但你很快发现自己有点应付不过来了，现在只能坚持到增援部队的到来。\n" +
                "Mission006DescriptionDesertStormfrontV01ETC = 法国进攻阿巴斯港（伊朗），但很快发现自己有点应付不过来了，现在法国必须坚持到增援部队的到来。\n" +
                "Mission007DescriptionDesertStormfrontV01CleanETC = 继续你的征程，你发现自己被埋伏了，不得不坚守在一座小城镇里。\n" +
                "Mission007DescriptionDesertStormfrontV01ETC = 继续从阿巴丹到设拉子的征程，美国发现自己被埋伏了，不得不坚守在一座小城镇里。\n" +
                "Mission008DescriptionDesertStormfrontV01CleanETC = 我们想要前进的更远就必须要占领一个关键油田。\n" +
                "Mission008DescriptionDesertStormfrontV01ETC = 我们想要前进的更远就必须要占领设拉子以外的一个关键油田。\n" +
                "Mission009DescriptionDesertStormfrontV01CleanETC = 你必须要赶紧让运输卡车进入城镇，并在敌人增援部队赶来之前建立基地。\n" +
                "Mission009DescriptionDesertStormfrontV01ETC = 美国已经抵达设拉子，但必须要赶紧让运输卡车进入城镇，并在敌人增援部队赶来之前建立基地。\n" +
                "Mission010DescriptionDesertStormfrontV01CleanETC = 你的敌人企图抢回被占领的土地，在援军到达之前你必须要坚持住。\n" +
                "Mission010DescriptionDesertStormfrontV01ETC = 伊朗想借助伊拉克的力量抢回设拉子，在援军到达之前你必须要坚持住。\n" +
                "Mission011DescriptionDesertStormfrontV01CleanETC = 你攻击敌人三次，并取得了胜利\\!\n" +
                "Mission011DescriptionDesertStormfrontV01ETC = 美国、法国、英国在巴士拉(伊拉克)进行了三次攻击。\n" +
                "Mission012DescriptionDesertStormfrontV01CleanETC = 海战\\: 你是被一艘巨大的敌人舰艇攻击。摧毁所有敌人的海军部队\n" +
                "Mission012DescriptionDesertStormfrontV01ETC = 沙特阿拉伯加入了与伊拉克和伊朗的联盟。一艘沙特阿拉伯的舰艇攻击了法国、英国和美国在波斯湾的基地。\n" +
                "Mission013DescriptionDesertStormfrontV01CleanETC = 前进，你需要占领一座城市来满足你的供给需求。\n" +
                "Mission013DescriptionDesertStormfrontV01ETC = 前进，美国攻击巴格达（伊拉克）。占领这座城市。\n" +
                "Mission014DescriptionDesertStormfrontV01CleanETC = 你要攻击一个炼油设施，但该地点是被严密保护起来的。\n" +
                "Mission014DescriptionDesertStormfrontV01ETC = 美国要攻击伊朗炼油设施，但伊拉克帮助伊朗在这加强了他们的防御。\n" +
                "Mission015DescriptionDesertStormfrontV01CleanETC = 你为了另一座重要的城市在敌人的领土上与敌人作战，一切都很顺利\\!\n" +
                "Mission015DescriptionDesertStormfrontV01ETC = 英国将攻击吉达港城（沙特阿拉伯），与沙特阿拉伯开战。\n" +
                "Mission016DescriptionDesertStormfrontV01CleanETC = 你需要占领更多的领土。\n" +
                "Mission016DescriptionDesertStormfrontV01ETC = 英国进攻麦加（沙特阿拉伯）。\n" +
                "Mission017DescriptionDesertStormfrontV01CleanETC = 你正在对一个大城市发动大规模攻击。尽可能争取下这座城市！\\!\n" +
                "Mission017DescriptionDesertStormfrontV01ETC = 意大利加入战争并攻击麦地那（沙特阿拉伯）。法国加入战斗。\n" +
                "Mission018DescriptionDesertStormfrontV01CleanETC = 已检测到一个核压缩设施，需要赶紧行动。\n" +
                "Mission018DescriptionDesertStormfrontV01ETC = 库姆（伊朗）的核压缩设施被美国攻击了。\n" +
                "Mission019DescriptionDesertStormfrontV01CleanETC = 你收到敌人的攻击了，要坚持到援军到达。\n" +
                "Mission019DescriptionDesertStormfrontV01ETC = 埃及和沙特阿拉伯试图从英国手中夺回吉达港。\n" +
                "Mission020DescriptionDesertStormfrontV01CleanETC = 你正在把重要的物资送往前线。在任何情况下，前进的同时都要确保运输卡车的安全。\n" +
                "Mission020DescriptionDesertStormfrontV01ETC = 法国对法拉（阿富汗）发动攻击。\n" +
                "Mission021DescriptionDesertStormfrontV01CleanETC = 你的敌人试图夺回他的几座油田，挡住这次进攻！\\!\n" +
                "Mission021DescriptionDesertStormfrontV01ETC = 伊朗试图从美国手中夺回几座油田。\n" +
                "Mission022DescriptionDesertStormfrontV01CleanETC = 你瞄准的是海岸上的一座油田。\n" +
                "Mission022DescriptionDesertStormfrontV01ETC = 美国目标是伊朗海岸附近的一座油田。\n" +
                "Mission023DescriptionDesertStormfrontV01CleanETC = 你正在发动一场针对敌方高防御阵地的进攻。尽你最大的能力吧\\!\n" +
                "Mission023DescriptionDesertStormfrontV01ETC = 意大利和法国联手攻击开罗（埃及）。\n" +
                "Mission024DescriptionDesertStormfrontV01CleanETC = 包围敌人进行围攻\\!\n" +
                "Mission024DescriptionDesertStormfrontV01ETC = 占领开罗后，埃及投降。利雅得（沙特阿拉伯）被意大利、法国和埃及攻击。\n" +
                "Mission025DescriptionDesertStormfrontV01CleanETC = 投入部队，强行进入并占领敌人的一个中央据点。\n" +
                "Mission025DescriptionDesertStormfrontV01ETC = 沙特阿拉伯已投降。法国、英国和美国攻击喀布尔（阿富汗）。\n" +
                "Mission026DescriptionDesertStormfrontV01CleanETC = 你的敌人试图摧毁自己的首都，防止军事情报的泄露。不要让一辆运输卡车到达目标区域\\!\n" +
                "Mission026DescriptionDesertStormfrontV01ETC = 阿富汗试图摧毁自己的首都，以防止军事情报的泄露。不要让一辆运输卡车到达目标区域\\!\n" +
                "Mission027DescriptionDesertStormfrontV01CleanETC = 你瞄准的是一个重要的机场。摧毁所有敌人\n" +
                "Mission027DescriptionDesertStormfrontV01ETC = 抵抗失败，阿富汗投降。美国目标是一个重要机场，地点在克尔曼沙赫（伊朗）。\n" +
                "Mission028DescriptionDesertStormfrontV01CleanETC = 你在低于一次大规模的攻击。护送运输卡车里的外交官到机场。\n" +
                "Mission028DescriptionDesertStormfrontV01ETC = 美国在巴格达抵御来自伊拉克和伊朗的大规模攻击。护送卡车里的外交官到机场。\n" +
                "Mission029DescriptionDesertStormfrontV01CleanETC = 你正在移动到敌人最后一个主要的城市。袭击并占领了城镇的郊区。\n" +
                "Mission029DescriptionDesertStormfrontV01ETC = 伊拉克投降。美国移动到德黑兰（伊朗）。美国必须进攻并占领城镇的郊区。\n" +
                "Mission030DescriptionDesertStormfrontV01CleanETC = 出发去征服城镇的其余部分。去战胜敌人并获得最终胜利吧\\!\n" +
                "Mission030DescriptionDesertStormfrontV01ETC = 美国必须征服德黑兰的其他部分。伊朗绝望与当地叛军结盟。去战胜敌人并获得最终胜利吧\\!\n" +
                "Tutorial00DesertStormfrontETC = 欢迎来到本教程！让我们为你讲解游戏的基本玩法吧；-）\n" +
                "Tutorial01DesertStormfrontETC = 沙漠风暴是一款及时性战略游戏，你的目标是完成下方黑色文本框内的任务。\\n\\n点击[确定]开始任务。\n" +
                "Tutorial02DesertStormfrontETC = 游戏需要初始化地图，以提供流畅的游戏。请准备...\n" +
                "Tutorial03DesertStormfrontETC = 上面的数字显示的是你当前所拥有的金钱数。你所拥有的油田每80秒会为你产生一次金钱收入。\n" +
                "Tutorial04DesertStormfrontETC = 下面的按钮允许对单位进行分组，点击分组按钮选择一个选区把要分组的单位框起来。\n" +
                "Tutorial05DesertStormfrontETC = 标有绿色箭头的设施是你的基地，能够生产其他单位。\n" +
                "Tutorial06DesertStormfrontETC = 这里显示的是敌人的基地。\n" +
                "Tutorial07DesertStormfrontETC = 这是一个中立的油田，油田是产生收入的唯一的设施。\n" +
                "Tutorial08DesertStormfrontETC = 让我们先造一辆运输车然后对油田进行采集，一定要注意，运输车是唯一能够接管油田的单位。\\n\\n任务\\: 点击你的基地然后选择运输车蓝色按钮来造一辆运输车。\n" +
                "Tutorial09DesertStormfrontETC = 干得漂亮\\!你可以点击[x]键来关闭基地面板。\\n\\n任务\\:关闭基地面板。\n" +
                "Tutorial10BDesertStormfrontETC = 让我们在等待运输车造好之前，先来练习如何改变视图吧。你可以通过指针来改变地图上的可见部分。\\n\\n任务\\:按下A S D W键盘键或鼠标移到屏幕的边缘。鼠标和键盘的设置可以在屏幕上的[选项]进行调整。\n" +
                "Tutorial10DesertStormfrontETC = 让我们在等待运输车造好之前，先来练习如何改变视图吧。你可以通过指针来改变地图上的可见部分。\\n\\n任务\\:拖动地图。\n" +
                "Tutorial11BDesertStormfrontETC = 不错\\!下面有艘你可以部署的运输船，我们可以用它来载运输车来渡过水域到达油田。\\n\\n注\\:鼠标左键选择单位，鼠标右键使单位移动(或停止)。\n" +
                "Tutorial11DesertStormfrontETC = 干的不错\\!我们可以用箭头所指的运输船来载运输车渡过这片水域到达油田。\n" +
                "Tutorial12DesertStormfrontETC = 你的运输车已经建造完成，让我们把它移动到运输船上去吧。\\n\\n任务\\:点击基地并选择坦克的黄色按钮。\n" +
                "Tutorial13DesertStormfrontETC = 让我们把运输车移动到运输船上去吧。\\n\\n任务\\:点击运输船并移动至运输车的位置。\n" +
                "Tutorial14DesertStormfrontETC = 很好\\!让我们等待运输车载上运输船...\n" +
                "Tutorial15DesertStormfrontETC = 让我们把运输船移动到油田的附近。\\n\\n任务\\:点击你的运输船并将其移动至敌方海滩附近。\n" +
                "Tutorial16DesertStormfrontETC = 是的，就是这儿\\! 让我们等运输船到达目标海滩吧...\n" +
                "Tutorial17DesertStormfrontETC = 让你的运输车对油田进行接管。\\n\\n任务\\: 点击运输船和选择运输车。\n" +
                "Tutorial18DesertStormfrontETC = 让你的运输车去接管油田。\\n\\n任务\\: 点击油田并将你的坦克移动过去。\n" +
                "Tutorial19DesertStormfrontETC = 好\\! 现在就让我们耐心等待你的运输车接管油田了...\n" +
                "Tutorial20DesertStormfrontETC = 干得漂亮\\!这应该不难吧，你已经成功完成了教程并接管了油田。\n" +
                "Tutorial21DesertStormfrontETC = 和接管油田的方式大同小异，你也可以攻击敌人的部队和军事机构。\\n\\n但要小心，如果攻击的目标选择失误的话，你很有可能全军覆没，最终输掉这场战役哟。\n" +
                "Tutorial22DesertStormfrontETC = 其他单位的移动方式有：[巡逻]，可以在两个位置之间进行巡视。[修理]，可以让单位自行修理并回到原来的生产位置。[停止]让单位停止移动。\\n\\n空中单位还可以设置为[自动]模式，可以让它们自行移动。使用[召集]按钮来设置单位集合地点。\n" +
                "Tutorial23DesertStormfrontETC = 想了解更多的手谈汉化游戏资讯，可以关注我们的贴吧及微博，来了解关于游戏的策略以及更多信息，祝你们游戏愉快。\n" +
                "UnitGroupKey = 单位 [集团] Key\\: {0}\n" +
                "UnitPatrolKey = 单位 [巡逻] Key\\: {0}\n" +
                "UnitRepairKey = 单位 [修理] Key\\: {0}\n" +
                "UnitStopKey = 单位 [停止] Key\\: {0}\n" +
                "UnitUnGroupKey = 单位 [分离] Key (un-Group)\\: {0}\n" +
                "\n";

        s2 = CodeChange.stringToUnicode(s2);

        File file = new File("aa.txt");
        System.out.println("s2: " + s2);
        FileOutputStream fout = new FileOutputStream(file);
        fout.write(s2.getBytes());
        System.out.println("s2: " + s2);



        //转换Unicode码为汉字
//        String aaa ="\u4ec0\u4e48\u662f\u5b89\u5168\u63a7\u4ef6\uff1f###\u5b89\u5168\u63a7\u4ef6\u53ef\u4ee5\u4fdd\u8bc1\u7528\u6237\u7684\u5bc6\u7801\u4e0d\u88ab\u7a83\u53d6\uff0c\u4ece\u800c\u4fdd\u8bc1\u8d44\u91d1\u5b89\u5168";
//        String s3 = CodeChange.unicodeToString(aaa);
//
//        System.out.println("s3: " + s3);

    }





}
