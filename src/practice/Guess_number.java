package practice;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Guess_number {

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		
		//切換迴圈do or not
		Boolean switchbot = true;
		do{
			System.out.println("-----------------------------");
			System.out.println("遊戲開始!");
			
			//設定次數
			int limit = 0;
			do {
				System.out.print("請設定猜數字次數限制:");
				try {
					limit = scanner.nextInt();
					switchbot = true;
				} catch (InputMismatchException e) {
					System.out.printf("輸入錯誤!!必須輸入數字!!\n", scanner.next());
					switchbot = false;
				}
				if(limit==0) {switchbot=false;}
			} while (switchbot == false);
			int max = 0;
			int min = 0 ;
			int b;
			//設定範圍，int b控制選數字的迴圈
			do {b = 0;			
				do {
					System.out.print("請設定答案最大範圍:");
					try {
						max = scanner.nextInt();
						switchbot = true;
					} catch (Exception InputMismatchException) {
						System.out.printf("輸入錯誤!!必須輸入數字!!\n", scanner.next());
						switchbot = false;
					}
				} while (switchbot == false);
				
				//設定範圍
				
				do {
					System.out.print("請設定答案最小範圍:");
					try {
						min = scanner.nextInt();
						switchbot = true;						
					} catch (Exception InputMismatchException) {
						System.out.printf("輸入錯誤!!必須輸入數字!!\n", scanner.next());
						switchbot = false;
					}if((min>=max && max!=0) || ((max-min)<limit)) {
						System.out.println("範圍輸入錯誤,請重新設定範圍");b=0;
					}else {
						b++;
					}
				} while (switchbot == false);
			} while (b == 0);
			//答案隨機
			int ans = (int) (Math.random() * (max - min + 1) + min);
			System.out.println("-----------------------------");
			System.out.println("猜數字遊戲開始!");
			
			//開始輸入答案的迴圈
			int num;
			do{				
				System.out.println("請輸入" + min + "~" + max + "任意數字:");
				//判斷輸入的答案是int
				num = 0;
				switchbot = false;
				while (switchbot == false && limit>0) {
					try {
						num = scanner.nextInt();
						switchbot = true;
					} catch (Exception InputMismatchException) {
						System.out.printf("輸入錯誤!!必須輸入數字!!\n", scanner.next());
						System.out.println("請輸入" + min + "~" + max + "任意數字:");
						switchbot = false;
					}
				}if(num>min && num<max) {
					limit--;					
					//控制遊戲是否重來的變數
					String choice; 
					//輸入<答案
					if (num < ans && limit > 0) {
						System.out.println("大一點!剩餘" + limit + "次機會");
						min = num;
						switchbot = false;
						
					//輸入<答案
					} else if (num > ans && limit > 0) {
						System.out.println("小一點!剩餘" + limit + "次機會");
						max = num;
						switchbot = false;
					
					//輸入=答案
					} else if (num == ans) {
						System.out.println("恭喜!正確答案!");
						do {
							System.out.println("請選擇輸入'A.重新開始遊戲','B.離開遊戲'");
							choice = scanner.next().toUpperCase();
	
							switch (choice) {
							case "A":
								switchbot = false;
								break;
							case "B":
								switchbot = true;
								break;
							default:
								System.out.println("輸入錯誤!");								
							}
						}while(!(choice.equals("A") || choice.equals("B")));
					
					//次數用完未達對
					} else if (num != ans && limit == 0) {
						System.out.println("Game Over!");
						System.out.println("正確答案為:" + ans);
						do {
							System.out.println("-----------------------------");
							System.out.println("請選擇輸入'A.重新開始遊戲','B.離開遊戲'");
							choice = scanner.next().toUpperCase();
	
							switch (choice) {
							case "A":
								switchbot = false;
								break;
							case "B":
								switchbot = true;
								break;
							default:
								System.out.println("輸入錯誤!");								
							}
						}while(!(choice.equals("A") || choice.equals("B")));					
					} 
				}else {
					//輸入範圍外的數字
					switchbot = false;
					System.out.println("輸入錯誤!剩餘" + limit + "次機會");
				}
			}while(switchbot == false && num!=ans && limit!=0);
			
		}while(switchbot == false);
		System.out.println("END!");
		scanner.close();
	}
}