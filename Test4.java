package javawork;

import java.util.Arrays;
import java.util.Scanner;

/**
 * �ͻ���
 * @author neuedu_yhl
 */
public class Test4 {
	
	// ����һ����������Ž�ɫ��Ϣ
	// ����
	static GamePlayer[] players = {};
	private static Scanner scanner;
	private static Scanner scanner2;
	private static Scanner scanner3;
	private static Scanner scanner1;

	
	public static void main(String[] args) {
		welcome();
	}
	

    // дһ������ �����ӭ����
	public static void welcome() {
		scanner1 = new Scanner(System.in);
		System.out.println("============================");
		System.out.println("==============��ӭ����ʵ����ѧ...==============");
		System.out.println("============================");
		System.out.println("1.����ѧ�� ...");
		System.out.println("2.�޸�ѧ��...");
		System.out.println("3.ɾ��ѧ��...");
		System.out.println("4.��ѯѧ��");
		System.out.println("5.��ӡѧ��");
		System.out.println("6.�γ̹���");
		System.out.println("7.ϵͳ�˳�");
		System.out.println("������������еĲ���:(1-7)");
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
			System.out.println("��������,����������");
			welcome();
			break;
		}
	}
	// ����ѧ������ϵͳ�ķ���
	private static void createNewGame() {
		scanner2 = new Scanner(System.in);
		System.out.println("ͨ��ѧ��ѧ�ţ���ѯ��ѧ��ѡ�����:");
		String name = scanner2.next();
		System.out.println("������ѧ������");
	    int race = scanner2.nextInt();
	    // ����ѧ��ϵͳ
	    GamePlayer gp = new GamePlayer();
	    gp.name = name;
	    gp.race = race;
	    players = Arrays.copyOf(players, players.length+1);
	    players[players.length-1] = gp;
	    // �ж�
	    if(players != null) {
	    	System.out.println("�����ɹ�");
	    	System.out.println("ѧ������:"+gp.name);
	    	System.out.println("ѧ��:"+gp.race);
	    	int power = gp.getPower();
	    	System.out.println("����:"+power);
	    }
	    welcome();
	}
	private static void gameRules() {
		System.out.println("�γ���Ϣ��������");
		 welcome();
	}
	private static void gameOver() {
		System.exit(0);
	}
	// �鿴����ѧ����Ϣ
	private static void printAllCharacters() {
		for(int i=0;i<players.length;i++) {
			System.out.println(players[i]);
		}
		welcome();
	}
	
	// 5.����ѧ�����鿴ѧ����Ϣ
	private static void queryCharacterByName() {
		scanner3 = new Scanner(System.in);
		System.out.println("������Ҫ��ѯ��ѧ����:");
		String name = scanner3.next();
		for(int i=0;i<players.length;i++) {
			if(players[i].name.equals(name)) {
				System.out.println(players[i]);
			}
		}
		welcome();
	}
	// 6.�޸�ѧ����Ϣ  ����ѧ������ȥ�޸�ѧ����Ϣ
	private static void updateCharacterInfoByName() {
		scanner = new Scanner(System.in);
		System.out.println("������Ҫ�޸ĵ�ѧ������:");
		String name = scanner.next();
		System.out.println("�������޸ĵ�ѧ���γ�:");
		int race = scanner.nextInt();
		for(int i=0;i<players.length;i++) {
			if(players[i].name.equals(name)) {
				// ���޸Ĳ���
				players[i].race = race;
				System.out.println("�޸ĳɹ�");
				System.out.println("�޸ĺ����Ϣ:"+players[i]);
			}
		}
		 welcome();
	}
	
	// 7.ɾ��ѧ����Ϣ
	private static void deleteCharacters() {
scanner2 = new Scanner(System.in);
		System.out.println("������Ҫɾ����ѧ���γ�:");
		String name = scanner2.next();
		for(int i=0;i<players.length;i++) {
			if(players[i].name.equals(name)) {
				// ɾ��
				players[i] = players[players.length-1];
				players = Arrays.copyOf(players, players.length-1);
			}
		}
		System.out.println("ɾ�������Ϣ:"+Arrays.toString(players));
		welcome();
	}
}