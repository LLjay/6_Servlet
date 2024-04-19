package com.jay.total;

import java.util.ArrayList;

public class Test {
	public static void main(String[] args) {
		ArrayList<Member> list = new ArrayList();
		list.add(new Member("전은지", 30));
		list.add(new Member("이지수", 45));
//		list.add(new Member("윤현지", 23));
//		list.add(new Member("서지안", 53));
//		list.add(new Member("이해솔", 14));
//		list.add(new Member("서지안", 53));
//		list.add(new Member("이해솔", 14));
//		list.add(new Member("전은지", 30));
		// 5 -> (5+1)/2 = 3
		// 6 -> (6+1)/2 = 3
		
		int num = 0;
		
		for(int i = 0; i < (list.size() + 1) / 2; i++) {
			System.out.println(list.get(num).getName()); 
			System.out.println(list.get(num).getAge());
			System.out.println();
			if ((num != list.size() - 1) && (num < list.size())) {
				System.out.println(list.get(num+1).getName()); 
				System.out.println(list.get(num+1).getAge());
			}
			num += 2;
		}
	}
}
