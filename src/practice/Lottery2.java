package practice;

import java.util.Scanner;
import java.util.Set;
import java.util.HashSet;

public class Lottery2 {

	private static Set<Integer> randomNum(int max , int min , int x) {
		Set<Integer> a = new HashSet<Integer>();
			// 按順序放入隨機數
		while(true) {
			a.add((int) (Math.random() * (max - min + 1)) + min);
			// 驗證數量
			if(a.size()==x) {				
				break;
			}
		}
		return a;
	}

	public static void main(String[] args) {		
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("選號範圍(最大):");
		int max = scanner.nextInt();
		System.out.print("選號範圍(最小):");
		int min = scanner.nextInt();
		System.out.print("選號次數:");
		int times = scanner.nextInt();	
		//b=固定中獎的隨機數
		Set<Integer> b = randomNum(max , min , times);
		//a=之後計算會變動的隨機數
		Set<Integer> a;
		int counts = 0;
		
		System.out.println("頭獎:"+b);
		
		//頭獎
		while(true) {
			counts++;
			a = randomNum(max , min , times);
			//Set不用排序
			//Arrays.sort(a);
			//Arrays.sort(b);
			
			if(a.equals(b)) {
				System.out.println("試算全中(參考):總共需要"+counts+"次");
				break;
			}
		}
		
		System.out.print("購買次數(樣本數):");		
		int buy = scanner.nextInt();

		//buy會在運算途中--,所以設一個新變數維持最大值
		int origin = buy;		
		
		//中獎數量,從times(選號最大數量)開始往下找
		while(times>0) {
			//中獎的數量
			int same = 0;
			//中獎的次數
			int probability = 0;
			//buy每--一次等於重新買一張
			while(buy>0) {
				same = 0;
				a = randomNum(max , min , times);
				buy--;
				//查a、b內容物,從號碼min開始檢查到號碼max
				for(int x = min ; x<max ; x++) {
					if(a.contains(x) && b.contains(x)) {
						same++;
						if(same == times) {
							probability++;
						}						
					}
				}
			}
			System.out.println("中'" + times + "'個機率為:" + ((float)probability/(float)origin)*100 + "%");
			buy = origin;
			times--;			
		}
		scanner.close();
	}

}
