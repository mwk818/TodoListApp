package com.todo.service;

import java.util.*;

import com.todo.dao.TodoItem;
import com.todo.dao.TodoList;
import java.io.*;
import java.text.SimpleDateFormat;

public class TodoUtil {
	
	public static void createItem(TodoList list) {
		
		String title, desc, category, due_date;
		Scanner sc = new Scanner(System.in);
		
		System.out.print("\n"
				+ "========== Create an item Section\n"
				+ "Enter the title > ");
		
		title = sc.nextLine().trim();
		if (list.isDuplicate(title)) {
			System.out.printf("Title can't be duplicated");
			return;
		}

		System.out.print("Enter the category > ");
		category = sc.nextLine().trim();
		
		System.out.print("Enter the description > ");
		desc = sc.nextLine().trim();
		
		System.out.print("Enter the due date > ");
		due_date = sc.nextLine().trim();
		
		TodoItem t = new TodoItem(title, desc, category, due_date);
		list.addItem(t);
		System.out.println("Item has been added!\n");
	}

	public static void deleteItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("\n"
				+ "========== Delete The Item Section\n"
				+ "Enter the number of the item to remove > ");
		int number = sc.nextInt();
		
		int i = 0;
		
		for (TodoItem item : l.getList()) {
			i++;
			if (number == i) {
				System.out.println(i + ". <" + item.getTitle() + ">  - " + item.getCategory() + " - " + item.getDesc() + " - " + item.getDue_date() + " - " + item.getCurrent_date());
				System.out.print("Are you sure you are goin to delete this item? (y/n) > ");
				String yn = sc.next().trim();
				
				sc.nextLine();
				
				if (yn.equals("y")) {
					l.deleteItem(item);
					System.out.print("Item has been deleted!\n");
					break;
				}
				else if (yn.equals("n"))
					break;
			}
		}
	}


	public static void updateItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("\n"
				+ "========== Edit The Item Section\n"
				+ "Enter the number of the item you want to update > ");
		int number = sc.nextInt();
//		if (!l.isDuplicate(title)) {
//			System.out.println("Title doesn't exist");
//			return;
//		}
		
		int i = 0;
		
		for (TodoItem item : l.getList()) {
			i++;
			if (number == i)
				System.out.println(i + ". <" + item.getTitle() + ">  - " + item.getCategory() + " - " + item.getDesc() + " - " + item.getDue_date() + " - " + item.getCurrent_date());
		}
		
		sc.nextLine();
		
		System.out.print("Enter the new title of the item > ");
		String new_title = sc.nextLine().trim();
		if (l.isDuplicate(new_title)) {
			System.out.println("Title can't be duplicated");
			return;
		}
		
		System.out.print("Enter the new category > ");
		String new_category = sc.nextLine().trim();
	
		System.out.print("Enter the new description > ");
		String new_description = sc.nextLine().trim();
		
		System.out.print("Enter the new due_date > ");
		String new_due_date = sc.nextLine().trim();
		
		i = 0;
		
		for (TodoItem item : l.getList()) {
			i++;
			if (number == i) {
				l.deleteItem(item);
				TodoItem t = new TodoItem(new_title, new_description, new_category, new_due_date);
				l.addItem(t);
				System.out.println("Item has been edited");
			}
		}
	}

	public static void listAll(TodoList l) throws Exception {
		int i = 1; 
		for (TodoItem item : l.getList()) {
			System.out.println(i + ". <" + item.getTitle() + ">  - " + item.getCategory() + " - " + item.getDesc() + " - " + item.getDue_date() + " - " + item.getCurrent_date());
			i++;
		}
		saveList(l, "todolist.txt");
	}
	public static void saveList(TodoList l, String filename) throws Exception {
			FileWriter fw = new FileWriter(filename);
			fw.flush();
			for (TodoItem item : l.getList()) {
				fw.write(item.toSaveString());
			}
			fw.close();
	}
	public static void loadList(TodoList l, String filename) {
		try {
			String number, title, desc, date, category, due_date;
			FileReader fr = new FileReader(filename);
			BufferedReader br = new BufferedReader(fr);
			String i = " ";
			while(i != null) { 
				i = br.readLine();
				StringTokenizer st = new StringTokenizer(i, "##");
				while(st.hasMoreTokens()) {
					title = st.nextToken();
					category = st.nextToken();
					desc = st.nextToken();
					due_date = st.nextToken();
					date = st.nextToken();
					TodoItem t = new TodoItem(title, desc, category, due_date); 
					t.setCurrent_date(date);
					l.addItem(t);
				}
			}
			br.close();
		} catch(Exception e) {System.out.print("todolist.txt doesn't exist");}
	}
	
	public static int show_num_items(TodoList l) {
		int count = 0;
		for (TodoItem item : l.getList())
			count++;
		return count;
	}
	
	public static void find_keyword(TodoList l, String keyword) {
		int i = 0;
		int count = 0;
		String title_desc = null;
		for (TodoItem item : l.getList()) {
			i++;
			title_desc = item.getTitle() + " " + item.getDesc();
			for (int index = 0, kindex = 0; index < title_desc.length() && kindex < keyword.length(); index++) {
				char ichar = title_desc.charAt(index);
				char kchar = keyword.charAt(kindex);
				if (ichar == kchar) {
					if (kindex == keyword.length()-1) {
						System.out.println(i + ". <" + item.getTitle() + ">  - " + item.getCategory() + " - " + item.getDesc() + " - " + item.getDue_date() + " - " + item.getCurrent_date());
						count++;
						break;
					}
					kindex++;
				}
				else if (kchar > 0)
					kindex = 0;
			}
		}
		System.out.print("[" + count + " of items were found!!]");
	}
	
	public static void find_ckeyword(TodoList l, String keyword) {
		int i = 0;
		int count = 0;
		for (TodoItem item : l.getList()) {
			i++;
			for (int index = 0, kindex = 0; index < item.getCategory().length() && kindex < keyword.length(); index++) {
				char ichar = item.getCategory().charAt(index);
				char kchar = keyword.charAt(kindex);
				if (ichar == kchar) {
					if (kindex == keyword.length()-1) {
						System.out.println(i + ". <" + item.getTitle() + ">  - " + item.getCategory() + " - " + item.getDesc() + " - " + item.getDue_date() + " - " + item.getCurrent_date());
						count++;
						break;
					}
					kindex++;
				}
				else if (kchar > 0)
					kindex = 0;
			}
		}
		System.out.print("[" + count + " of items were found!!]");
	}
	
	public static void list_cate(TodoList l) {
		String[] cate = {};
		boolean duplic = false;
		for (TodoItem item : l.getList()) {
			for (int i = 0; i < show_num_items(l); i++) {
				if (cate == null) {
					cate[i] = item.getCategory();
					break;
				}
				else if (cate[i] == item.getCategory()) {
					duplic = true;
					break;
				}
				else if (cate[i] != item.getCategory()) {
					continue;
				}
			}
		}
	}
}
