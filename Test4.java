package javawork;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 客户端
 * @author neuedu_yhl
 */
public class Test4 {
	
	// 创建一个容器来存放角色信息
	// 数组
	static GamePlayer[] players = {};
	private static Scanner scanner;
	private static Scanner scanner2;
	private static Scanner scanner3;
	private static Scanner scanner1;

	
	public static void main(String[] args) {
		welcome();
	}
	

    // 写一个方法 输出欢迎界面
	public static void welcome() {
		scanner1 = new Scanner(System.in);
		System.out.println("============================");
		System.out.println("==============欢迎来到实验中学...==============");
		System.out.println("============================");
		System.out.println("1.增加学生 ...");
		System.out.println("2.修改学生...");
		System.out.println("3.删除学生...");
		System.out.println("4.查询学生");
		System.out.println("5.打印学生");
		System.out.println("6.课程管理");
		System.out.println("7.系统退出");
		System.out.println("请输入您想进行的操作:(1-7)");
		int nextInt = scanner1.nextInt();
		switch (nextInt) {
		case 1:
			createNewGame();
			break;

		case 2:
			gameRules();
			welcome();
			break;
		case 3:
			gameOver();
			break;
		case 4:
			
			printAllCharacters();
			break;
		case 5:
			queryCharacterByName();
			break;
		case 6:
			updateCharacterInfoByName();
			break;
		case 7:
			deleteCharacters();
			break;
		default:
			System.out.println("输入有误,请重新输入");
			welcome();
			break;
		}
	}
	// 创建学生管理系统的方法
	private static void createNewGame() {
		scanner2 = new Scanner(System.in);
		System.out.println("通过学生学号，查询该学生选课情况:");
		String name = scanner2.next();
		System.out.println("请输入学生姓名");
	    int race = scanner2.nextInt();
	    // 构建学生系统
	    GamePlayer gp = new GamePlayer();
	    gp.name = name;
	    gp.race = race;
	    players = Arrays.copyOf(players, players.length+1);
	    players[players.length-1] = gp;
	    // 判断
	    if(players != null) {
	    	System.out.println("创建成功");
	    	System.out.println("学生名叫:"+gp.name);
	    	System.out.println("学号:"+gp.race);
	    	int power = gp.getPower();
	    	System.out.println("您的:"+power);
	    }
	    welcome();
	}
	private static void gameRules() {
		System.out.println("课程信息管理。。。");
		 welcome();
	}
	private static void gameOver() {
		System.exit(0);
	}
	// 查看所有学生信息
	private static void printAllCharacters() {
		for(int i=0;i<players.length;i++) {
			System.out.println(players[i]);
		}
		welcome();
	}
	
	// 5.根据学生名查看学生信息
	private static void queryCharacterByName() {
		scanner3 = new Scanner(System.in);
		System.out.println("请输入要查询的学生名:");
		String name = scanner3.next();
		for(int i=0;i<players.length;i++) {
			if(players[i].name.equals(name)) {
				System.out.println(players[i]);
			}
		}
		welcome();
	}
	// 6.修改学生信息  根据学生姓名去修改学生信息
	private static void updateCharacterInfoByName() {
		scanner = new Scanner(System.in);
		System.out.println("请输入要修改的学生姓名:");
		String name = scanner.next();
		System.out.println("请输入修改的学生课程:");
		int race = scanner.nextInt();
		for(int i=0;i<players.length;i++) {
			if(players[i].name.equals(name)) {
				// 做修改操作
				players[i].race = race;
				System.out.println("修改成功");
				System.out.println("修改后的信息:"+players[i]);
			}
		}
		 welcome();
	}
	
	// 7.删除学生信息
	private static void deleteCharacters() {
scanner2 = new Scanner(System.in);
		System.out.println("请输入要删除的学生课程:");
		String name = scanner2.next();
		for(int i=0;i<players.length;i++) {
			if(players[i].name.equals(name)) {
				// 删除
				players[i] = players[players.length-1];
				players = Arrays.copyOf(players, players.length-1);
			}
		}
		System.out.println("删除后的信息:"+Arrays.toString(players));
		welcome();
	}
}