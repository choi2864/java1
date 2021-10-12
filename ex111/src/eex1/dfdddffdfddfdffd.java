package eex1;

import java.util.Arrays;

public class dfdddffdfddfdffd {

	public static void main(String[] args) {
		
		int[] arr = new int[10];

		for(int i = 0; i < arr.length; i++){
			arr[i] = (int)(Math.random() * 100) + 1;
		}
		System.out.println(Arrays.toString(arr));
		printRank(arr); //석차구하기
}

	private static void printRank(int[] arr) {

				int[] rank = new int[arr.length];

				for(int i = 0; i < rank.length; i++){

					rank[i] = 1;
				}
				for(int i = 0; i < arr.length; i++){

					for(int j = 0; j < arr.length; j++){

						if(arr[i] < arr[j]){

							rank[i]++;
						}
					}
				}
				System.out.println(Arrays.toString(rank));
	}
}