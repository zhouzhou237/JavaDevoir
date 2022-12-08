import java.util.ArrayList;

import java.util.Optional;


import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;


//记得对每个按钮添加isStart

public class Game2Text extends javafx.application.Application {
    static boolean isStart = false;     //判断是否开始
    Button buttonItem,buttonProperties;        //查看装备，查看属性
    Button buttonAction,buttonStartround;         //控制英雄行动，控制开始
    Button buttonHeroChoose;        //英雄选择
    Button buttonEnter;     //确认按钮
    
    static TextArea gameArea;       //游戏区域
    static Player player = null;

    static ArrayList<String> heroList = new ArrayList<>();      //英雄列表
    static ArrayList<String> monsterList = new ArrayList<>();    //怪兽列表
    static ArrayList<String> allList = new ArrayList<>();       //总共列表

    //设置英雄
    static Hunter hunter = null;
    static Warrior warrior = null;
    static Mage mage = null;
    static Healer healer = null;

    //设置怪兽
    static Monster Monster1 = null;
    static Monster Monster2 = null;
    static Monster Monster3 = null;
    static Monster Monster4 = null;

    //设置武器
    static Bow bow = null;
    static Needle needle = null;
    static Sword sword = null;
    static Staff staff = null;

    public static void main(String[] args) {
		Application.launch(args);	// 启动独立的JavaFx程序
	}

    @Override
    public void start(Stage primaryStage) throws Exception {

        //设置游戏主题区域(画面上方)
        BorderPane rootPane = new BorderPane();     //边界布局面板
        rootPane.setPadding(new Insets(10)); // 边缘内侧空白距离
		gameArea = new TextArea();
		gameArea.setEditable(false);	// 不可修改
		gameArea.setPrefSize(800, 350); // 游戏画面大小
		gameArea.setStyle("-fx-background-color:red;-fx-text-fill:blue;");
		gameArea.setWrapText(true);	// 自动换行
        Font font = new Font("Cambria", 40);
        gameArea.setFont(font);
        gameArea.appendText("\n\t Welcome to the Mini RPG Lite 3000");
        gameArea.appendText("\n\t ");
        gameArea.appendText("\n\t ");
        gameArea.appendText("\n\t Press ENTER to start game!");
        rootPane.setTop(gameArea);


        //设置左侧按钮(控制按钮)
        GridPane controlPane = new GridPane();
        controlPane.setAlignment(Pos.CENTER);
        controlPane.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
		controlPane.setHgap(5.5);
		controlPane.setVgap(5.5);

        //设置左侧按钮的位置参数
        int helpRowNumNum = 4;
        int buttonWidth = 100;       //按钮的宽
        int buttonHeight = 80;      //按钮的高

        //ENTER 键设置
        buttonEnter = new Button("ENTER");
        buttonEnter.setPrefSize(buttonWidth, buttonHeight);
        controlPane.add(buttonEnter, helpRowNumNum+1, 1);

        //Hero_Choose 键设置
        buttonHeroChoose = new Button("HERO_CHOOSE");
        buttonHeroChoose.setPrefSize(buttonWidth, buttonHeight);
        controlPane.add(buttonHeroChoose, helpRowNumNum+2,1);


        rootPane.setLeft(controlPane);      //确认控制面板在rootPane的左边


        //设置右侧按钮（操作按钮）
        GridPane operationPane = new GridPane();
        operationPane.setAlignment(Pos.CENTER);
        operationPane.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
		operationPane.setHgap(5.5);
		operationPane.setVgap(5.5);

        //设置右侧按钮的位置参数
        int helpRowNumNum2 = 14;
        int buttonWidth2 = 160;
        int buttonHeight2 = 50;

        //装备按钮
        buttonItem = new Button("show item");
        buttonItem.setPrefSize(buttonWidth2, buttonHeight2);
        operationPane.add(buttonItem,helpRowNumNum2+0,1);

        //属性按钮
        buttonProperties = new Button("show properties");
        buttonProperties.setPrefSize(buttonWidth2, buttonHeight2);
        operationPane.add(buttonProperties,helpRowNumNum2+3,1);

        //行动按钮
        buttonAction = new Button("ACTION");
        buttonAction.setPrefSize(buttonWidth2, buttonHeight2);
        operationPane.add(buttonAction, helpRowNumNum2+0,3);

        //开始轮次按钮
        buttonStartround = new Button("START GAME");
        buttonStartround.setPrefSize(buttonWidth2, buttonHeight2);
        operationPane.add(buttonStartround,helpRowNumNum2+3,3);

        rootPane.setCenter(operationPane);      //确认操作面板在rootPane的右边


        //点击确认开始游戏
        buttonEnter.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                StartGame();        //开始游戏
            }
        });


        //点击进入英雄选择界面
        buttonHeroChoose.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(isStart) {
                    System.out.println("Hero choose");       //检查器
                    StringBuilder herochooBuilder1 = new StringBuilder();
                    herochooBuilder1.append("\n\n How many heros do you want to choose? (1 to 4) ");
                    gameArea.appendText(herochooBuilder1+"");
                    TextInputalwaysDialog herochooseDialog = new TextInputalwaysDialog();

                    StringBuilder herochooBuilder11 = new StringBuilder();
                    herochooBuilder11.append("\n" + herochooseDialog.getText() + "\n");
                    gameArea.appendText(herochooBuilder11 + "");

                    int HCD1 = Integer.parseInt(herochooseDialog.getText());
                    if (HCD1 == 1) {
                        System.out.println("You choose 1 hero.");
                        monsterList.add("Monster1");
                        allList.add("Monster1");
                        //第一个英雄
                        heroclass1();
                        TextInputalwaysDialog heroclass1Dialog = new TextInputalwaysDialog();
                        StringBuilder heroclass1Builder = new StringBuilder();
                        int HClass1 = Integer.parseInt(heroclass1Dialog.getText());
                        
                        if (HClass1 == 1) {
                            heroclass1Builder.append("\n You have chosen Warrior");
                            heroList.add("Warrior");
                            allList.add("Warrior");
                        }else if (HClass1 == 2) {
                            heroclass1Builder.append("\n You have chosen Hunter");
                            heroList.add("Hunter");
                            allList.add("Hunter");
                        }else if (HClass1 == 3) {
                            heroclass1Builder.append("\n You have chosen Mage");
                            heroList.add("Mage");
                            allList.add("Mage");
                        }else if (HClass1 == 4) {
                            heroclass1Builder.append("\n You have chosen Healer");
                            heroList.add("Healer");
                            allList.add("Healer");
                        }else {
                            heroclass1Builder.append("\n ERROR");
                        }
                        gameArea.appendText(heroclass1Builder+"");

                    }else if (HCD1 == 2) {
                        System.out.println("You choose to have 2 heros.");
                        monsterList.add("Monster1");
                        monsterList.add("Monster2");
                        allList.add("Monster1");
                        allList.add("Monster2");

                        //第一个英雄
                        heroclass1();
                        TextInputalwaysDialog heroclass1Dialog = new TextInputalwaysDialog();
                        StringBuilder heroclass1Builder = new StringBuilder();
                        int HClass1 = Integer.parseInt(heroclass1Dialog.getText());
                        
                        if (HClass1 == 1) {
                            heroclass1Builder.append("\n You have chosen Warrior");
                            heroList.add("Warrior");
                            allList.add("Warrior");
                        }else if (HClass1 == 2) {
                            heroclass1Builder.append("\n You have chosen Hunter");
                            heroList.add("Hunter");
                            allList.add("Hunter");
                        }else if (HClass1 == 3) {
                            heroclass1Builder.append("\n You have chosen Mage");
                            heroList.add("Mage");
                            allList.add("Mage");
                        }else if (HClass1 == 4) {
                            heroclass1Builder.append("\n You have chosen Healer");
                            heroList.add("Healer");
                            allList.add("Healer");
                        }else {
                            heroclass1Builder.append("\n ERROR");
                        }
                        gameArea.appendText(heroclass1Builder+"");

                        //第二个英雄
                        heroclass2();
                        TextInputalwaysDialog heroclass2Dialog = new TextInputalwaysDialog();
                        StringBuilder heroclass2Builder = new StringBuilder();
                        int HClass2 = Integer.parseInt(heroclass2Dialog.getText());
                        
                        if (HClass2 == 1) {
                            heroclass2Builder.append("\n You have chosen Warrior");
                            heroList.add("Warrior");
                            allList.add("Warrior");
                        }else if (HClass2 == 2) {
                            heroclass2Builder.append("\n You have chosen Hunter");
                            heroList.add("Hunter");
                            allList.add("Hunter");
                        }else if (HClass2 == 3) {
                            heroclass2Builder.append("\n You have chosen Mage");
                            heroList.add("Mage");
                            allList.add("Mage");
                        }else if (HClass2 == 4) {
                            heroclass2Builder.append("\n You have chosen Healer");
                            heroList.add("Healer");
                            allList.add("Healer");
                        }else {
                            heroclass2Builder.append("\n ERROR");
                        }
                        gameArea.appendText(heroclass2Builder+"");                        

                    }else if (HCD1 == 3) {
                        System.out.println("You choose to have 3 heros.");
                        monsterList.add("Monster1");
                        monsterList.add("Monster2");
                        monsterList.add("Monster3");
                        allList.add("Monster1");
                        allList.add("Monster2");
                        allList.add("Monster3");

                        //第一个英雄
                        heroclass1();
                        TextInputalwaysDialog heroclass1Dialog = new TextInputalwaysDialog();
                        StringBuilder heroclass1Builder = new StringBuilder();
                        int HClass1 = Integer.parseInt(heroclass1Dialog.getText());
                        
                        if (HClass1 == 1) {
                            heroclass1Builder.append("\n You have chosen Warrior");
                            heroList.add("Warrior");
                            allList.add("Warrior");
                        }else if (HClass1 == 2) {
                            heroclass1Builder.append("\n You have chosen Hunter");
                            heroList.add("Hunter");
                            allList.add("Hunter");
                        }else if (HClass1 == 3) {
                            heroclass1Builder.append("\n You have chosen Mage");
                            heroList.add("Mage");
                            allList.add("Mage");
                        }else if (HClass1 == 4) {
                            heroclass1Builder.append("\n You have chosen Healer");
                            heroList.add("Healer");
                            allList.add("Healer");
                        }else {
                            heroclass1Builder.append("\n ERROR");
                        }
                        gameArea.appendText(heroclass1Builder+"");

                        //第二个英雄
                        heroclass2();
                        TextInputalwaysDialog heroclass2Dialog = new TextInputalwaysDialog();
                        StringBuilder heroclass2Builder = new StringBuilder();
                        int HClass2 = Integer.parseInt(heroclass2Dialog.getText());
                        
                        if (HClass2 == 1) {
                            heroclass2Builder.append("\n You have chosen Warrior");
                            heroList.add("Warrior");
                            allList.add("Warrior");
                        }else if (HClass2 == 2) {
                            heroclass2Builder.append("\n You have chosen Hunter");
                            heroList.add("Hunter");
                            allList.add("Hunter");
                        }else if (HClass2 == 3) {
                            heroclass2Builder.append("\n You have chosen Mage");
                            heroList.add("Mage");
                            allList.add("Mage");
                        }else if (HClass2 == 4) {
                            heroclass2Builder.append("\n You have chosen Healer");
                            heroList.add("Healer");
                            allList.add("Healer");
                        }else {
                            heroclass2Builder.append("\n ERROR");
                        }
                        gameArea.appendText(heroclass2Builder+"");  

                        //第三个英雄
                        heroclass1();
                        TextInputalwaysDialog heroclass3Dialog = new TextInputalwaysDialog();
                        StringBuilder heroclass3Builder = new StringBuilder();
                        int HClass3 = Integer.parseInt(heroclass3Dialog.getText());
                        
                        if (HClass3 == 1) {
                            heroclass3Builder.append("\n You have chosen Warrior");
                            heroList.add("Warrior");
                            allList.add("Warrior");
                        }else if (HClass3 == 2) {
                            heroclass3Builder.append("\n You have chosen Hunter");
                            heroList.add("Hunter");
                            allList.add("Hunter");
                        }else if (HClass3 == 3) {
                            heroclass3Builder.append("\n You have chosen Mage");
                            heroList.add("Mage");
                            allList.add("Mage");
                        }else if (HClass3 == 4) {
                            heroclass3Builder.append("\n You have chosen Healer");
                            heroList.add("Healer");
                            allList.add("Healer");
                        }else {
                            heroclass3Builder.append("\n ERROR");
                        }
                        gameArea.appendText(heroclass3Builder+"");                        

                    }else if (HCD1 == 4) {
                        System.out.println("You choose to have 4 heros.");
                        monsterList.add("Monster1");
                        monsterList.add("Monster2");
                        monsterList.add("Monster3");
                        monsterList.add("Monster4");
                        allList.add("Monster1");
                        allList.add("Monster2");
                        allList.add("Monster3");
                        allList.add("Monster4");

                        //第一个英雄
                        heroclass1();
                        TextInputalwaysDialog heroclass1Dialog = new TextInputalwaysDialog();
                        StringBuilder heroclass1Builder = new StringBuilder();
                        int HClass1 = Integer.parseInt(heroclass1Dialog.getText());
                        
                        if (HClass1 == 1) {
                            heroclass1Builder.append("\n You have chosen Warrior");
                            heroList.add("Warrior");
                            allList.add("Warrior");
                        }else if (HClass1 == 2) {
                            heroclass1Builder.append("\n You have chosen Hunter");
                            heroList.add("Hunter");
                            allList.add("Hunter");
                        }else if (HClass1 == 3) {
                            heroclass1Builder.append("\n You have chosen Mage");
                            heroList.add("Mage");
                            allList.add("Mage");
                        }else if (HClass1 == 4) {
                            heroclass1Builder.append("\n You have chosen Healer");
                            heroList.add("Healer");
                            allList.add("Healer");
                        }else {
                            heroclass1Builder.append("\n ERROR");
                        }
                        gameArea.appendText(heroclass1Builder+"");

                        //第二个英雄
                        heroclass2();
                        TextInputalwaysDialog heroclass2Dialog = new TextInputalwaysDialog();
                        StringBuilder heroclass2Builder = new StringBuilder();
                        int HClass2 = Integer.parseInt(heroclass2Dialog.getText());
                        
                        if (HClass2 == 1) {
                            heroclass2Builder.append("\n You have chosen Warrior");
                            heroList.add("Warrior");
                            allList.add("Warrior");
                        }else if (HClass2 == 2) {
                            heroclass2Builder.append("\n You have chosen Hunter");
                            heroList.add("Hunter");
                            allList.add("Hunter");
                        }else if (HClass2 == 3) {
                            heroclass2Builder.append("\n You have chosen Mage");
                            heroList.add("Mage");
                            allList.add("Mage");
                        }else if (HClass2 == 4) {
                            heroclass2Builder.append("\n You have chosen Healer");
                            heroList.add("Healer");
                            allList.add("Healer");
                        }else {
                            heroclass2Builder.append("\n ERROR");
                        }
                        gameArea.appendText(heroclass2Builder+"");  
                        
                        //第三个英雄
                        heroclass1();
                        TextInputalwaysDialog heroclass3Dialog = new TextInputalwaysDialog();
                        StringBuilder heroclass3Builder = new StringBuilder();
                        int HClass3 = Integer.parseInt(heroclass3Dialog.getText());
                        
                        if (HClass3 == 1) {
                            heroclass3Builder.append("\n You have chosen Warrior");
                            heroList.add("Warrior");
                            allList.add("Warrior");
                        }else if (HClass3 == 2) {
                            heroclass3Builder.append("\n You have chosen Hunter");
                            heroList.add("Hunter");
                            allList.add("Hunter");
                        }else if (HClass3 == 3) {
                            heroclass3Builder.append("\n You have chosen Mage");
                            heroList.add("Mage");
                            allList.add("Mage");
                        }else if (HClass3 == 4) {
                            heroclass3Builder.append("\n You have chosen Healer");
                            heroList.add("Healer");
                            allList.add("Healer");
                        }else {
                            heroclass3Builder.append("\n ERROR");
                        }
                        gameArea.appendText(heroclass3Builder+"");

                        //第四个英雄
                        heroclass2();
                        TextInputalwaysDialog heroclass4Dialog = new TextInputalwaysDialog();
                        StringBuilder heroclass4Builder = new StringBuilder();
                        int HClass4 = Integer.parseInt(heroclass4Dialog.getText());
                        
                        if (HClass4 == 1) {
                            heroclass4Builder.append("\n You have chosen Warrior");
                            heroList.add("Warrior");
                            allList.add("Warrior");
                        }else if (HClass4 == 2) {
                            heroclass4Builder.append("\n You have chosen Hunter");
                            heroList.add("Hunter");
                            allList.add("Hunter");
                        }else if (HClass4 == 3) {
                            heroclass4Builder.append("\n You have chosen Mage");
                            heroList.add("Mage");
                            allList.add("Mage");
                        }else if (HClass4 == 4) {
                            heroclass4Builder.append("\n You have chosen Healer");
                            heroList.add("Healer");
                            allList.add("Healer");
                        }else {
                            heroclass4Builder.append("\n ERROR");
                        }
                        gameArea.appendText(heroclass4Builder+"");  

                    }else {
                        System.out.println("ERROR");
                    }
                
                }else {
                    System.out.println("ERROR");
                    primaryStage.close();
                }
                
            }
        });

        //点击进入轮次
        buttonStartround.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                
                //创建4个怪兽
                Monster1 = new Monster("Monster1");
                Monster2 = new Monster("Monster2");
                Monster3 = new Monster("Monster3");
                Monster4 = new Monster("Monster4");

                //判断英雄是否穿戴护甲
                Boolean Hunter_armor = false;
                Boolean Warrior_armor = false;
                Boolean Mage_armor = false;
                Boolean Healer_armor = false;

                //创造第一回合的list
                ArrayList<String> RoundOneAllList = new ArrayList<>();
                RoundOneAllList = allList;
                ArrayList<String> RoundOneMonsterList = new ArrayList<>();
                RoundOneMonsterList = monsterList;
                ArrayList<String> RoundOneHeroList = new ArrayList<>();
                RoundOneHeroList = heroList;

                //第一回合
                String round_one_start = "";
                gameArea.setText("PLAY NOW!");
                round_one_start += "\n";
                round_one_start += "There are 5 rounds at all, the last round you will encounter the boss. \n";
                round_one_start += "For the first four round, you will meet the monsters. \n";
                round_one_start += "The number of monsters is depends on the number of heros that you choose. \n";
                round_one_start += "For example, if you choose 2 heros, you will meet 2 monsters each round. \n";
                round_one_start += "Here is your first round. \n";
                round_one_start += "-------------------- FIRST ROUND ---------------------------- \n";

                gameArea.appendText(round_one_start);
                
                while(RoundOneAllList.size() > 0) {
                    if(RoundOneHeroList != null && RoundOneMonsterList != null){
                        int randomIndex = (int)(Math.random()*RoundOneAllList.size());
                        String element_RoundOneAllList = RoundOneAllList.get(randomIndex);
                        Boolean checkway = RoundOneHeroList.contains(element_RoundOneAllList);
                        String round_one1 = "";
                        if (checkway == true){
                            int indexRoundOneMonster = (int)(Math.random()*RoundOneMonsterList.size());
                            String element_RoundOnemonsterList = RoundOneMonsterList.get(indexRoundOneMonster);
                            round_one1 += "\n Here is your hero's round : " + element_RoundOneAllList + "\n";
                            round_one1 += "\n You have 4 options : \n";
                            round_one1 += "\n 1) attack  2) defense  3) use weapon  4) use option 5) use food \n";
                            gameArea.appendText(round_one1);
                            TextInputalwaysDialog heroActionDialog = new TextInputalwaysDialog();
                            int HA = Integer.parseInt(heroActionDialog.getText());
                            gameArea.appendText("\n You choose the option :" + HA);
                            if (HA == 1) {
                                String round_one_HA = "";
                                System.out.println( element_RoundOneAllList + " attack");
                                round_one_HA += "\n " + element_RoundOneAllList + "attack" + element_RoundOnemonsterList + "\n";
                                if (element_RoundOneAllList == "Hunter"){
                                    System.out.println("Hunter attack");
                                    round_one_HA += "\n and deal " + hunter.getAttack() +"damage";
                                    if (element_RoundOnemonsterList == "Monster1"){
                                        round_one_HA += "\n " + element_RoundOnemonsterList + " loose " + hunter.getAttack() + "hp.";
                                        Monster1.looseHp(hunter.getAttack());
                                        if (Monster1.getHp() <= 0){
                                            Monster1.die();
                                            RoundOneAllList.remove("Monster1");
                                            RoundOneMonsterList.remove("Monster1");
                                        }
                                    }else if (element_RoundOnemonsterList == "Monster2"){
                                        round_one_HA += "\n " +element_RoundOnemonsterList + " loose " + hunter.getAttack() + "hp.";
                                        Monster2.looseHp(hunter.getAttack());
                                        if (Monster2.getHp() < 0){
                                            Monster2.die();
                                            RoundOneAllList.remove("Monster2");
                                            RoundOneMonsterList.remove("Monster2");
                                        }                                    
                                    }else if (element_RoundOnemonsterList == "Monster3"){
                                        round_one_HA += "\n " +element_RoundOnemonsterList + " loose " + hunter.getAttack() + "hp.";
                                        Monster3.looseHp(hunter.getAttack());
                                        if (Monster3.getHp() < 0){
                                            Monster3.die();
                                            RoundOneAllList.remove("Monster3");
                                            RoundOneMonsterList.remove("Monster3");
                                        }                                        
                                    }else if (element_RoundOnemonsterList == "Monster4"){
                                        round_one_HA += "\n " +element_RoundOnemonsterList + " loose " + hunter.getAttack() + "hp.";
                                        Monster4.looseHp(hunter.getAttack());
                                        if (Monster4.getHp() < 0){
                                            Monster4.die();
                                            RoundOneAllList.remove("Monster4");
                                            RoundOneMonsterList.remove("Monster4");
                                        }                                        
                                    }else {
                                        System.out.println("round_one_HA");
                                    }
                                    gameArea.appendText(round_one_HA);

                                }else if(element_RoundOneAllList == "Warrior"){
                                    System.out.println("Warrior attack");
                                    round_one_HA += "and deal " + warrior.getAttack() +" damage ";
                                    if (element_RoundOnemonsterList =="Monster1"){
                                        round_one_HA += "\n " +element_RoundOnemonsterList + " loose " + warrior.getAttack() + "hp.";
                                        Monster1.looseHp(warrior.getAttack());
                                        if (Monster1.getHp() < 0){
                                            Monster1.die();
                                            RoundOneAllList.remove("Monster1");
                                            RoundOneMonsterList.remove("Monster1");
                                        }
                                    }else if (element_RoundOnemonsterList == "Monster2"){
                                        round_one_HA += "\n " +element_RoundOnemonsterList + " loose " + warrior.getAttack() + "hp.";
                                        Monster2.looseHp(warrior.getAttack());
                                        if (Monster2.getHp() < 0){
                                            Monster2.die();
                                            RoundOneAllList.remove("Monster2");
                                            RoundOneMonsterList.remove("Monster2");
                                        }                                    
                                    }else if (element_RoundOnemonsterList == "Monster3"){
                                        round_one_HA += "\n " +element_RoundOnemonsterList + " loose " + warrior.getAttack() + "hp.";
                                        Monster3.looseHp(warrior.getAttack());
                                        if (Monster3.getHp() < 0){
                                            Monster3.die();
                                            RoundOneAllList.remove("Monster3");
                                            RoundOneMonsterList.remove("Monster3");
                                        }                                        
                                    }else if (element_RoundOnemonsterList == "Monster4"){
                                        round_one_HA += "\n " +element_RoundOnemonsterList + " loose " + warrior.getAttack() + "hp.";
                                        Monster4.looseHp(warrior.getAttack());
                                        if (Monster4.getHp() < 0){
                                            Monster4.die();
                                            RoundOneAllList.remove("Monster4");
                                            RoundOneMonsterList.remove("Monster4");
                                        }                                        
                                    }
                                    gameArea.appendText(round_one_HA);

                                }else if (element_RoundOneAllList == "Mage"){
                                    System.out.println("Mage attack");
                                    round_one_HA += "\n and deal " + mage.getAttack() +"damage";
                                    if (element_RoundOnemonsterList =="Monster1"){
                                        round_one_HA += "\n " +element_RoundOnemonsterList + " loose " + mage.getAttack() + "hp.";
                                        Monster1.looseHp(mage.getAttack());
                                        if (Monster1.getHp() < 0){
                                            Monster1.die();
                                            RoundOneAllList.remove("Monster1");
                                            RoundOneMonsterList.remove("Monster1");
                                        }
                                    }else if (element_RoundOnemonsterList == "Monster2"){
                                        round_one_HA += "\n " +element_RoundOnemonsterList + " loose " + mage.getAttack() + "hp.";
                                        Monster2.looseHp(mage.getAttack());
                                        if (Monster2.getHp() < 0){
                                            Monster2.die();
                                            RoundOneAllList.remove("Monster2");
                                            RoundOneMonsterList.remove("Monster2");
                                        }                                    
                                    }else if (element_RoundOnemonsterList == "Monster3"){
                                        round_one_HA += "\n " +element_RoundOnemonsterList + " loose " + mage.getAttack() + "hp.";
                                        Monster3.looseHp(mage.getAttack());
                                        if (Monster3.getHp() < 0){
                                            Monster3.die();
                                            RoundOneAllList.remove("Monster3");
                                            RoundOneMonsterList.remove("Monster3");
                                        }                                        
                                    }else if (element_RoundOnemonsterList == "Monster4"){
                                        round_one_HA += "\n " +element_RoundOnemonsterList + " loose " + mage.getAttack() + "hp.";
                                        Monster4.looseHp(mage.getAttack());
                                        if (Monster4.getHp() < 0){
                                            Monster4.die();
                                            RoundOneAllList.remove("Monster4");
                                            RoundOneMonsterList.remove("Monster4");
                                        }                                        
                                    }    
                                    gameArea.appendText(round_one_HA);

                                }else if (element_RoundOneAllList == "Healer"){
                                    System.out.println("Healer attack");
                                    round_one_HA += "\n and deal " + healer.getAttack() +"damage";
                                    if (element_RoundOnemonsterList =="Monster1"){
                                        round_one_HA +="\n " + element_RoundOnemonsterList + " loose " + healer.getAttack() + "hp.";
                                        Monster1.looseHp(healer.getAttack());
                                        if (Monster1.getHp() < 0){
                                            Monster1.die();
                                            RoundOneAllList.remove("Monster1");
                                            RoundOneMonsterList.remove("Monster1");
                                        }
                                    }else if (element_RoundOnemonsterList == "Monster2"){
                                        round_one_HA += "\n " +element_RoundOnemonsterList + " loose " + healer.getAttack() + "hp.";
                                        Monster2.looseHp(healer.getAttack());
                                        if (Monster2.getHp() < 0){
                                            Monster2.die();
                                            RoundOneAllList.remove("Monster2");
                                            RoundOneMonsterList.remove("Monster2");
                                        }                                    
                                    }else if (element_RoundOnemonsterList == "Monster3"){
                                        round_one_HA += "\n " +element_RoundOnemonsterList + " loose " + healer.getAttack() + "hp.";
                                        Monster3.looseHp(healer.getAttack());
                                        if (Monster3.getHp() < 0){
                                            Monster3.die();
                                            RoundOneAllList.remove("Monster3");
                                            RoundOneMonsterList.remove("Monster3");
                                        }                                        
                                    }else if (element_RoundOnemonsterList == "Monster4"){
                                        round_one_HA += "\n " +element_RoundOnemonsterList + " loose " + healer.getAttack() + "hp.";
                                        Monster4.looseHp(healer.getAttack());
                                        if (Monster4.getHp() < 0){
                                            Monster4.die();
                                            RoundOneAllList.remove("Monster4");
                                            RoundOneMonsterList.remove("Monster4");
                                        }                                        
                                    }
                                    gameArea.appendText(round_one_HA);  
                                                                       
                                }
                                
                            } else if (HA == 2){
                                String round_one_option2 = "";
                                round_one_option2 += "\n " +element_RoundOneAllList + " use the armor";
                                if (element_RoundOneAllList == "Hunter"){
                                    Hunter_armor = true;
                                }else if (element_RoundOneAllList == "Warrior"){
                                    Warrior_armor = true;
                                }else if (element_RoundOneAllList == "Mage"){
                                    Mage_armor = true;
                                }else if (element_RoundOneAllList == "Healer"){
                                    Healer_armor = true;
                                }
                                gameArea.appendText(round_one_option2);

                            }else if (HA == 3){
                                String round_one_option3 = "";
                                if (element_RoundOneAllList == "Hunter"){
                                    System.out.println("Hunter use the Bow");
                                    int indexMonster_Hunter = (int)(Math.random()*RoundOneMonsterList.size());
                                    String element_MonsterList_Hunter = RoundOneMonsterList.get(indexMonster_Hunter);
                                    round_one_option3 += "\n Hunter use the Bow to attack one monster \n";
                                    round_one_option3 += "\n and deal" + bow.getDamage() + "to" + element_MonsterList_Hunter;
                                    bow.looseAmount(1);
                                    if (element_MonsterList_Hunter == "Monster1"){
                                        Monster1.looseHp(bow.getDamage());
                                        round_one_option3 += "\n Monster1 loose " + bow.getDamage() + "hp";
                                    }else if (element_MonsterList_Hunter == "Monster2"){
                                        Monster2.looseHp(bow.getDamage());
                                        round_one_option3 += "\n Monster2 loose " + bow.getDamage() + "hp";
                                    }else if (element_MonsterList_Hunter == "Monster3"){
                                        Monster3.looseHp(bow.getDamage());
                                        round_one_option3 += "\n Monster3 loose " + bow.getDamage() + "hp";
                                    }else if (element_MonsterList_Hunter == "Monster4"){
                                        Monster4.looseHp(bow.getDamage());
                                        round_one_option3 += "\n Monster4 loose " + bow.getDamage() + "hp";
                                    }
                                    gameArea.appendText(round_one_option3);

                                }else if (element_RoundOneAllList == "Warrior"){
                                    System.out.println("Warrior use the sword");
                                    int indexMonster_Hunter = (int)(Math.random()*RoundOneMonsterList.size());
                                    String element_MonsterList_Hunter = RoundOneMonsterList.get(indexMonster_Hunter);
                                    round_one_option3 += "\n Warrior use the Sword to attack one monster \n";
                                    round_one_option3 += "\n and deal" + sword.getDamage() + "to" + element_MonsterList_Hunter;
                                    if (element_MonsterList_Hunter == "Monster1"){
                                        Monster1.looseHp(sword.getDamage());
                                        round_one_option3 += "\n Monster1 loose " + sword.getDamage() + "hp";
                                    }else if (element_MonsterList_Hunter == "Monster2"){
                                        Monster2.looseHp(sword.getDamage());
                                        round_one_option3 += "\n Monster2 loose " + sword.getDamage() + "hp";
                                    }else if (element_MonsterList_Hunter == "Monster3"){
                                        Monster3.looseHp(sword.getDamage());
                                        round_one_option3 += "\n Monster3 loose " + sword.getDamage() + "hp";
                                    }else if (element_MonsterList_Hunter == "Monster4"){
                                        Monster4.looseHp(sword.getDamage());
                                        round_one_option3 += "\n Monster4 loose " + sword.getDamage() + "hp";
                                    } 
                                    gameArea.appendText(round_one_option3);

                                }else if (element_RoundOneAllList == "Mage"){
                                    System.out.println("Mage use the Staff");
                                    round_one_option3 += "\n Mage use the Staff to attack all the monster";
                                    for (int i = 0; i<RoundOneMonsterList.size();i++){
                                        if (RoundOneMonsterList.get(i) == "Monster1"){
                                            Monster1.looseHp(staff.getDamage());
                                            round_one_option3 += "\n Monster1 loose " + staff.getDamage() + "hp";
                                        }else if (RoundOneMonsterList.get(i) == "Monster2"){
                                            Monster2.looseHp(staff.getDamage());
                                            round_one_option3 += "\n Monster2 loose " + staff.getDamage() + "hp";
                                        }else if (RoundOneMonsterList.get(i) == "Monster3"){
                                            Monster3.looseHp(staff.getDamage());
                                            round_one_option3 += "\n Monster3 loose " + staff.getDamage() + "hp";
                                        }else if (RoundOneMonsterList.get(i) == "Monster4"){
                                            Monster4.looseHp(staff.getDamage());
                                            round_one_option3 += "\n Monster4 loose " + staff.getDamage() + "hp";
                                        } 
                                    }
                                    gameArea.appendText(round_one_option3);

                                }else if (element_RoundOneAllList == "Healer"){
                                    System.out.println("Healer use the Needle");
                                    round_one_option3 += "\n You can choose one hero to heal";
                                    gameArea.appendText(round_one_option3);
                                    TextInputalwaysDialog ChooseHeroHeal = new TextInputalwaysDialog();
                                    String CH = "";
                                    CH = CH + ChooseHeroHeal;
                                    String healingheroString = "";
                                    if (CH == "Hunter"){
                                        healingheroString += "\n Hunter is healed";
                                        hunter.gainHp(needle.getHealing());
                                    }else if (CH == "Warrior"){
                                        healingheroString += "\n Warrior is healed";
                                        warrior.gainHp(needle.getHealing());
                                    }else if (CH == "Mage"){
                                        healingheroString += "\n Mage is healed";
                                        mage.gainHp(needle.getHealing());                                    
                                    }else if (CH == "Healer"){
                                        healingheroString += "\n Healer is healed";
                                        healer.gainHp(needle.getHealing());                                    
                                    }

                                    gameArea.appendText(healingheroString);
                                }
                            }else if (HA == 4){
                                
                                if (element_RoundOneAllList == "Hunter"){
                                    if(hunter.getOption() != null){
                                        String round_one_option4 = "";
                                        round_one_option4 += "\n Hunter use the option";
                                        gameArea.appendText(round_one_option4);
                                        hunter.gainAttack(5);
                                    }else{
                                        String round_one_option4 = "";
                                        round_one_option4 += "\n Hunter dont have option";
                                        gameArea.appendText(round_one_option4);
                                    }
                                }else if (element_RoundOneAllList == "Warrior"){
                                    if(warrior.getOption() != null){
                                        String round_one_option4 = "";
                                        round_one_option4 += "\n Warrior use the option";
                                        gameArea.appendText(round_one_option4);
                                        warrior.gainAttack(5);
                                    }else{
                                        String round_one_option4 = "";
                                        round_one_option4 += "\n Warrior dont have option";
                                        gameArea.appendText(round_one_option4);
                                    }                                
                                }else if (element_RoundOneAllList == "Mage"){
                                    if(mage.getOption() != null){
                                        String round_one_option4 = "";
                                        round_one_option4 += "\n Mage use the option";
                                        gameArea.appendText(round_one_option4);
                                        mage.gainAttack(5);
                                    }else{
                                        String round_one_option4 = "";
                                        round_one_option4 += "\n Mage dont have option";
                                        gameArea.appendText(round_one_option4);
                                    } 
                                }else if (element_RoundOneAllList == "Healer"){
                                    if(healer.getOption() != null){
                                        String round_one_option4 = "";
                                        round_one_option4 += "\n Warrior use the option";
                                        gameArea.appendText(round_one_option4);
                                        healer.gainAttack(5);
                                    }else{
                                        String round_one_option4 = "";
                                        round_one_option4 += "\n Warrior dont have option";
                                        gameArea.appendText(round_one_option4);
                                    } 
                                }
                                

                            }else if(HA == 5){
                                String round_one_option5 = "";
                                if (element_RoundOneAllList == "Hunter"){
                                    if(hunter.getFood() != null){
                                        round_one_option5 += "\n Hunter use the food";
                                        hunter.gainHp(15);
                                    }else{
                                        round_one_option5 += "\n Hunter dont have food";
                                    }
                                }else if (element_RoundOneAllList == "Warrior"){
                                    if(warrior.getFood() != null){
                                        round_one_option5 += "\n Warrior use the food";
                                        warrior.gainHp(15);
                                    }else{
                                        round_one_option5 += "\n Warrior dont have food";
                                    }                                
                                }else if (element_RoundOneAllList == "Mage"){
                                    if(mage.getFood() != null){
                                        round_one_option5 += "\n Mage use the food";
                                        mage.gainHp(15);
                                    }else{
                                        round_one_option5 += "\n Mage dont have food";
                                    } 
                                }else if (element_RoundOneAllList == "Healer"){
                                    if(healer.getFood() != null){
                                        round_one_option5 += "\n Warrior use the food";
                                        healer.gainHp(15);
                                    }else{
                                        round_one_option5 += "\n Warrior dont have food";
                                    } 
                                }
                                gameArea.appendText(round_one_option5);                            
                            }else {
                                System.out.println("ERREUR2");
                            }
                        }else {
                            System.out.println("Monster attack");
                            int indexHero = (int)(Math.random()*RoundOneHeroList.size());
                            String element_RoundOneHeroList = RoundOneHeroList.get(indexHero);
                            String monsterattack1 = "";
                            monsterattack1 += "\n" + element_RoundOneAllList + " attack " + element_RoundOneHeroList;
                            gameArea.appendText(monsterattack1);
                            if(element_RoundOneHeroList == "Hunter"){
                                if(Hunter_armor == false){
                                    StringBuilder monsterattack2 = new StringBuilder();
                                    monsterattack2.append("\n and deal 20 damage to Hunter") ;
                                    hunter.looseHp(20);
                                    gameArea.appendText(monsterattack2 + "");
                                }else{
                                    StringBuilder monsterattack3 = new StringBuilder();
                                    monsterattack3.append("\n and deal " + (20-hunter.getArmor()) + " damage to Hunter") ;
                                    hunter.looseHp(20-hunter.getArmor());
                                    gameArea.appendText(monsterattack3 + "");
                                }
                            }else if (element_RoundOneHeroList == "Warrior") {
                                if(Warrior_armor == false){
                                    StringBuilder monsterattack4 = new StringBuilder();
                                    monsterattack4.append("\n and deal 20 damage to Warrior") ;
                                    warrior.looseHp(20);
                                    gameArea.appendText(monsterattack4 + "");
                                }else{
                                    StringBuilder monsterattack5 = new StringBuilder();
                                    monsterattack5.append("\n and deal " + (20-warrior.getArmor()) + " damage to Warrior") ;
                                    warrior.looseHp(20-warrior.getArmor());
                                    gameArea.appendText(monsterattack5 + "");
                                }                          
                            }else if (element_RoundOneHeroList == "Mage") {
                                if(Mage_armor == false){
                                    StringBuilder monsterattack6 = new StringBuilder();
                                    monsterattack6.append("\n and deal 20 damage to Mage") ;
                                    mage.looseHp(20);
                                    gameArea.appendText(monsterattack6 + "");
                                }else{
                                    StringBuilder monsterattack7 = new StringBuilder();
                                    monsterattack7.append("\n and deal " + (20-mage.getArmor()) + " damage to Mage") ;
                                    mage.looseHp(20-mage.getArmor());
                                    gameArea.appendText(monsterattack7 + "");
                                }                         
                            }else if (element_RoundOneHeroList == "Healer") {
                                if(Healer_armor == false){
                                    StringBuilder monsterattack8 = new StringBuilder();
                                    monsterattack8.append("\n and deal 20 damage to Healer") ;
                                    healer.looseHp(20);
                                    gameArea.appendText(monsterattack8 + "");
                                }else{
                                    StringBuilder monsterattack9 = new StringBuilder();
                                    monsterattack9.append("\n and deal " + (20-healer.getArmor()) + " damage to Healer") ;
                                    healer.looseHp(20-healer.getArmor());
                                    gameArea.appendText(monsterattack9 + "");
                                }
                            }
                        }
                    }else if (RoundOneHeroList == null){
                        String GameOver = "";
                        GameOver += "\n Game Over!";
                        gameArea.appendText(GameOver);
                        isStart = false;
                        break;
                        
                    }else if (RoundOneMonsterList.size() == 0){
                        String FirstRoundWin = "";
                        FirstRoundWin += "\n You win the first round";
                        gameArea.appendText(FirstRoundWin);
                        break;
                        
                    }
                }
            }
        });

        //点击查看属性
        buttonProperties.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("View Properties");      //检查器
                String showproString = "";
                
                //设置每个英雄属性
                hunter = new Hunter("Hunter");
                warrior = new Warrior("Warrior");
                mage = new Mage("Mage");
                healer = new Healer("Healer");


                for (int i = 0; i<heroList.size(); i++) {
                    if (heroList.get(i) == "Hunter") {
                        showproString += "\n";
                        showproString += hunter.showProper();
                        showproString += "\n";
                    }else if (heroList.get(i) == "Warrior") {
                        showproString += "\n";
                        showproString += warrior.showProper();
                        showproString += "\n";
                    }else if (heroList.get(i) == "Mage") {
                        showproString += "\n";
                        showproString += mage.showProper();
                        showproString += "\n";                        
                    }else if (heroList.get(i) == "Healer") {
                        showproString += "\n";
                        showproString += healer.showProper();
                        showproString += "\n";                        
                    }
                }
                gameArea.appendText(showproString);
            }
        });


        //点击查看物品
        buttonItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("View Item");        //检查器
                String showItem = "";
                
                //设置每个英雄属性
                hunter = new Hunter("Hunter");
                warrior = new Warrior("Warrior");
                mage = new Mage("Mage");
                healer = new Healer("Healer");

                for (int i = 0; i<heroList.size(); i++) {
                    if (heroList.get(i) == "Hunter") {
                        showItem += "\n";
                        showItem += hunter.showItem();
                        showItem += "\n";
                    }else if (heroList.get(i) == "Warrior") {
                        showItem += "\n";
                        showItem += warrior.showItem();
                        showItem += "\n";
                    }else if (heroList.get(i) == "Mage") {
                        showItem += "\n";
                        showItem += mage.showItem();
                        showItem += "\n";                        
                    }else if (heroList.get(i) == "Healer") {
                        showItem += "\n";
                        showItem += healer.showItem();
                        showItem += "\n";                        
                    }
                }
                gameArea.appendText(showItem);
                
            }        
        });


        //英雄行动
        buttonAction.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                System.out.println("hero action");

            }
        });


        //设置画面展示
		Scene scene = new Scene(rootPane, 800, 550); // 游戏机整体大小
		primaryStage.setTitle("Mini RPG Lite 3000");
		primaryStage.setScene(scene);
		primaryStage.show();

    }

    //-----------------------------------控制事件----------------------------------//
        //游戏开始
        public static void StartGame(){
            if(isStart){
                return;
            }
            
            Font startnameFont = new Font("Cambria",15);     //字体设置
            gameArea.setFont(startnameFont);
            gameArea.setText("\n\n\t\t\t    Start Game!");
            gameArea.setStyle("-fx-background-color:red;-fx-text-fill:green;");

            TextInputDialog playername = new TextInputDialog("Player");
            playername.setTitle("What is your name?");
            playername.setHeaderText(null);
            playername.setContentText("What is your name?");
            Optional<String> result1 = playername.showAndWait();
            String name = "";
            if (result1.isPresent()) { 
                name = result1.get();        //获取玩家名字
            }else{
                return;
            }
            
            player = new Player(name);
            isStart = true;     //开始游戏
            String player_name = "You name is : " + player.getName() + "\n";
            gameArea.setText(player_name);
        }



        //设置文本输入框，之后将一直调用为了判定选择
        class TextInputalwaysDialog {
            private TextField textBox;
            private TextArea textArea;

            public TextInputalwaysDialog() {
                textBox = new TextField();
                BorderPane root = new BorderPane();
                root.setCenter(textBox);

                textArea = new TextArea(); 
                textArea.setEditable(false);
                textArea.setPrefSize(300, 80);
                textArea.setWrapText(true);
                Font textAreaFont = new Font("Cambria", 15);
                textArea.setFont(textAreaFont);
                textArea.appendText("\n\t After you input the text , you need to close the stage"); 
                root.setTop(textArea);

                Scene scene = new Scene(root, 300, 250);

                Stage stage = new Stage();
                stage.setTitle("Input Box");
                stage.setScene(scene);
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.showAndWait();
            }
            public String getText() {
                return textBox.getText();
            }
        }

        //第一个英雄类型选择
        public static void heroclass1() { 
            String heroclass1Builder = "";
            heroclass1Builder += "\n First Class:";
            heroclass1Builder +="\n 1) Warrior";
            heroclass1Builder +="\n 2) Hunter";
            heroclass1Builder +="\n 3) Mage";
            heroclass1Builder +="\n 4) Healer";
            gameArea.appendText(heroclass1Builder);
        }

        //第二个英雄类型选择
        public static void heroclass2() { 
            String heroclass2Builder = "";
            heroclass2Builder += "\n Second Class:";
            heroclass2Builder +="\n 1) Warrior";
            heroclass2Builder +="\n 2) Hunter";
            heroclass2Builder +="\n 3) Mage";
            heroclass2Builder +="\n 4) Healer";
            gameArea.appendText(heroclass2Builder);
        }

        //第三个英雄类型选择
        public static void heroclass3() { 
            String heroclass3Builder = "";
            heroclass3Builder += "\n Third Class:";
            heroclass3Builder +="\n 1) Warrior";
            heroclass3Builder +="\n 2) Hunter";
            heroclass3Builder +="\n 3) Mage";
            heroclass3Builder +="\n 4) Healer";
            gameArea.appendText(heroclass3Builder);
        }

        //第四个英雄类型选择
        public static void heroclass4() { 
            String heroclass4Builder = "";
            heroclass4Builder += "\n Fourth Class:";
            heroclass4Builder +="\n 1) Warrior";
            heroclass4Builder +="\n 2) Hunter";
            heroclass4Builder +="\n 3) Mage";
            heroclass4Builder +="\n 4) Healer";
            gameArea.appendText(heroclass4Builder);
        }        
}




        