package eex1;

import java.util.Arrays;
import java.util.Scanner;

public class ex123 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		ex9[] e = new ex9[3];
		
		System.out.println("제품번호 수량 단가 부서 입력 : ");
		
		for (int i = 0; i < e.length; i++) {
		
		String line = sc.nextLine();
		String[] li = line.split(" ");
		int n = Integer.parseInt(li[0]);
		int c = Integer.parseInt(li[1]);
		int p = Integer.parseInt(li[2]);
		int d = Integer.parseInt(li[3]);
		
		e[i] = new ex9(n,c,p,d);
		
		
		System.out.println(e[i]);
		}
		int[] r = new int[3];
		for (int i = 0; i < e.length; i++) {
			
		r[i]=1;
		
		for (int j = 0; j < e.length; j++) {
			if(e[i].getsum()<e[j].getsum()) {
				r[i]++;
			}if(e[i].getsum()==e[j].getsum()) {
				continue;
			}
		}
		}
		System.out.println(Arrays.toString(r));
	}
}
