package lotto;

import java.util.*;

public class lottonum {
	private static Scanner sc;
	// 버블정렬 메소드
	static boolean bubbleSort(int arr[],int bonus) {
		int temp = 0;
		for (int j = 0; j < arr.length - bonus; j++) {
			for (int i = 0; i < arr.length - bonus; i++) {
				if (arr[i] > arr[i + 1]) {
					temp = arr[i];
					arr[i] = arr[i + 1];
					arr[i + 1] = temp;
				}
				for(int k=0;k<arr.length-2;k++) {
					if(arr[k] == arr[arr.length-1])
						return true;
				}
				if (arr[i] == arr[i + 1]) {
					return true;
				}
			}
		}
		return false;
	}
	// 배열의 값을 출력하는 메소드
	static void numPrint(int arr[],int bonus) {
		for (int i = 0; i < arr.length; i++) {
			if(i!=bonus)	System.out.print(arr[i] + " ");
			else	System.out.print(arr[i] + " ");
		}
		System.out.println();
	}
	// 배열 안의 값을 비교하는 메소드
	static int compare(int arr[], int arr2[]) {
		int count = 0;
		for (int i = 0; i < arr.length-1; i++) {
			for (int j = 0; j < arr2.length-1; j++) {
				if (arr[i] == arr2[j])
					count++;
			}
		}
		if(count==5) 
			for (int j = 0; j < arr2.length-1; j++) {
				if (arr[5] != arr2[j])
					count--;
			}
		return count;
	}
	
	public static void main(String[] args) {
		int lotto[] = new int[6]; // 로또 넘버를 저장하는 배열
		int insertNum[] = new int[6]; // 구입하는 로또번호(직접 입력/자동생성)
		boolean reNum = false; // 로또 당첨 번호의 중복을 체크하는 변수
		boolean reInsert = false; // 직접 입력할 때 중복을 체크하는 변수
		int select = 0; // 직접 입력할 것인지, 랜덤으로 번호를 추출할 것인지를 선택하는 변수
		int compareNum = 0; // 로또 당첨번호와 나의 번호를 비교하여 같은 번호가 몇개 있는지를 반환한다.
		int money = 0; // 당첨금
		int price[] = {0,0,5000,50000,5000000,500000000,500000000};
		String rank[] = {"꽝","꽝","5등","4등","3등","2등","1등"};
		Random lottoNum = new Random();
		while (true) {
			System.out.println("직접입력 : 1, 자동생성 : 2");
			sc = new Scanner(System.in);
			try {
				select = sc.nextInt();
			} catch (Exception e) {
			}
			if (select == 1 || select == 2)
				break;
			System.out.println("입력값이 잘못되었습니다.\n");
		}
		switch (select) {
		case 1: // 직접입력하는 경우
			do {
				int cnt = 0;
				while (cnt < insertNum.length) {
					try {
						System.out.println("1~45까지 중 선택. (" + (cnt + 1) + "번째)");
						sc = new Scanner(System.in);
						insertNum[cnt] = sc.nextInt();
						if (insertNum[cnt] < 1 || insertNum[cnt] > 45) {
							System.out.println("정상 범위 X");
							cnt--;
						}else {	for(int i=0;i<cnt;i++) {
							if(insertNum[i]==insertNum[cnt]) {
								System.out.println("중복 값 존재.");
								cnt--;
							}
						}}
						cnt++;
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}
				}
				reInsert = bubbleSort(insertNum,1);
				cnt = 0;
				if (reInsert == true) {
					System.out.println("중복 값 존재. 재입력.\n");
				}
			} while (reInsert);
			System.out.print("\nMy Number : ");
			numPrint(insertNum,-1); // 입력한 로또 번호 출력
			break;
		case 2: // 자동으로 번호 생성하는 경우
			for (int i = 0; i < insertNum.length; i++) {
				insertNum[i] = lottoNum.nextInt(45) + 1; // 0~44까지 난수를 발생시켜서 1을 더함
				while (true) {
					boolean dupl = false;// 중복을 검사하는 변수
					for (int j = 0; j < i; j++) {
						if (insertNum[i] == insertNum[j]) {
							dupl = true;// 중복이 발생하면 true로 변환
						}
					}
					if (dupl == true) {
						insertNum[i] = lottoNum.nextInt(45) + 1;
					} else {
						break;// 중복값이 없으면 while문을 빠져나감
					}
				}
			}
			bubbleSort(insertNum,1);
			System.out.print("\nMy Number : ");
			numPrint(insertNum,-1); // 자동 추출한 로또 번호 출력
			break;
		}
		/*
		 * 중복방지하는 반복문 reNum값에 따라 반복한다. bubbleSort에서 중복이 발생하면 true를 반환한다. 그 반환값을 reNum에
		 * 받고, reNum값이 true면 다시 난수를 발생시켜서 저장한다.
		 */
		do {
			reNum = false;
			for (int i = 0; i < lotto.length; i++) {
				lotto[i] = lottoNum.nextInt(45) + 1; // 0~44까지 난수를 발생시켜서 1을 더함
			}
			reNum = bubbleSort(lotto,2); // 정렬을 하다 중복값이 발생하면 true값을 반환
		} while (reNum);
		compareNum = compare(lotto, insertNum); // 같은 번호가 몇개인지 비교
		money = price[compareNum];
		// 로또번호 출력
		System.out.print("Lotto Number : ");
		numPrint(lotto,lotto.length-1);
		System.out.println("\n" + rank[compareNum] + ": " + money + "원");
	}
}
