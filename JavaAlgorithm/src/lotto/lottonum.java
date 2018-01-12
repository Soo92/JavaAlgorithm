package lotto;

import java.util.*;

public class lottonum {
	private static Scanner sc;
	// �������� �޼ҵ�
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
	// �迭�� ���� ����ϴ� �޼ҵ�
	static void numPrint(int arr[],int bonus) {
		for (int i = 0; i < arr.length; i++) {
			if(i!=bonus)	System.out.print(arr[i] + " ");
			else	System.out.print(arr[i] + " ");
		}
		System.out.println();
	}
	// �迭 ���� ���� ���ϴ� �޼ҵ�
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
		int lotto[] = new int[6]; // �ζ� �ѹ��� �����ϴ� �迭
		int insertNum[] = new int[6]; // �����ϴ� �ζǹ�ȣ(���� �Է�/�ڵ�����)
		boolean reNum = false; // �ζ� ��÷ ��ȣ�� �ߺ��� üũ�ϴ� ����
		boolean reInsert = false; // ���� �Է��� �� �ߺ��� üũ�ϴ� ����
		int select = 0; // ���� �Է��� ������, �������� ��ȣ�� ������ �������� �����ϴ� ����
		int compareNum = 0; // �ζ� ��÷��ȣ�� ���� ��ȣ�� ���Ͽ� ���� ��ȣ�� � �ִ����� ��ȯ�Ѵ�.
		int money = 0; // ��÷��
		int price[] = {0,0,5000,50000,5000000,500000000,500000000};
		String rank[] = {"��","��","5��","4��","3��","2��","1��"};
		Random lottoNum = new Random();
		while (true) {
			System.out.println("�����Է� : 1, �ڵ����� : 2");
			sc = new Scanner(System.in);
			try {
				select = sc.nextInt();
			} catch (Exception e) {
			}
			if (select == 1 || select == 2)
				break;
			System.out.println("�Է°��� �߸��Ǿ����ϴ�.\n");
		}
		switch (select) {
		case 1: // �����Է��ϴ� ���
			do {
				int cnt = 0;
				while (cnt < insertNum.length) {
					try {
						System.out.println("1~45���� �� ����. (" + (cnt + 1) + "��°)");
						sc = new Scanner(System.in);
						insertNum[cnt] = sc.nextInt();
						if (insertNum[cnt] < 1 || insertNum[cnt] > 45) {
							System.out.println("���� ���� X");
							cnt--;
						}else {	for(int i=0;i<cnt;i++) {
							if(insertNum[i]==insertNum[cnt]) {
								System.out.println("�ߺ� �� ����.");
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
					System.out.println("�ߺ� �� ����. ���Է�.\n");
				}
			} while (reInsert);
			System.out.print("\nMy Number : ");
			numPrint(insertNum,-1); // �Է��� �ζ� ��ȣ ���
			break;
		case 2: // �ڵ����� ��ȣ �����ϴ� ���
			for (int i = 0; i < insertNum.length; i++) {
				insertNum[i] = lottoNum.nextInt(45) + 1; // 0~44���� ������ �߻����Ѽ� 1�� ����
				while (true) {
					boolean dupl = false;// �ߺ��� �˻��ϴ� ����
					for (int j = 0; j < i; j++) {
						if (insertNum[i] == insertNum[j]) {
							dupl = true;// �ߺ��� �߻��ϸ� true�� ��ȯ
						}
					}
					if (dupl == true) {
						insertNum[i] = lottoNum.nextInt(45) + 1;
					} else {
						break;// �ߺ����� ������ while���� ��������
					}
				}
			}
			bubbleSort(insertNum,1);
			System.out.print("\nMy Number : ");
			numPrint(insertNum,-1); // �ڵ� ������ �ζ� ��ȣ ���
			break;
		}
		/*
		 * �ߺ������ϴ� �ݺ��� reNum���� ���� �ݺ��Ѵ�. bubbleSort���� �ߺ��� �߻��ϸ� true�� ��ȯ�Ѵ�. �� ��ȯ���� reNum��
		 * �ް�, reNum���� true�� �ٽ� ������ �߻����Ѽ� �����Ѵ�.
		 */
		do {
			reNum = false;
			for (int i = 0; i < lotto.length; i++) {
				lotto[i] = lottoNum.nextInt(45) + 1; // 0~44���� ������ �߻����Ѽ� 1�� ����
			}
			reNum = bubbleSort(lotto,2); // ������ �ϴ� �ߺ����� �߻��ϸ� true���� ��ȯ
		} while (reNum);
		compareNum = compare(lotto, insertNum); // ���� ��ȣ�� ����� ��
		money = price[compareNum];
		// �ζǹ�ȣ ���
		System.out.print("Lotto Number : ");
		numPrint(lotto,lotto.length-1);
		System.out.println("\n" + rank[compareNum] + ": " + money + "��");
	}
}
