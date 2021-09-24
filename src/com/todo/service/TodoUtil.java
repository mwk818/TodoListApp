package com.todo.service;

import java.util.*;

import com.todo.dao.TodoItem;
import com.todo.dao.TodoList;
import java.io.*;
import java.text.SimpleDateFormat;

public class TodoUtil {
	
	public static void createItem(TodoList list) {
		
		String title, desc;
		Scanner sc = new Scanner(System.in);
		
		System.out.print("\n"
				+ "========== Create an item Section\n"
				+ "Enter the title > ");
		
		title = sc.nextLine();
		if (list.isDuplicate(title)) {
			System.out.printf("Title can't be duplicated");
			return;
		}

		System.out.print("Enter the description > ");
		desc = sc.nextLine().trim();
		
		TodoItem t = new TodoItem(title, desc);
		list.addItem(t);
		System.out.println("Item has been added!\n");
	}

	public static void deleteItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("\n"
				+ "========== Delete The Item Section\n"
				+ "Enter the title of the item to remove > ");
		String title = sc.nextLine();
		
		for (TodoItem item : l.getList()) {
			if (title.equals(item.getTitle())) {
				l.deleteItem(item);
				break;
			}
		}
		System.out.println("Item has been deleted!\n");
	}


	public static void updateItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("\n"
				+ "========== Edit The Item Section\n"
				+ "Enter the title of the item you want to update > ");
		String title = sc.nextLine().trim();
		if (!l.isDuplicate(title)) {
			System.out.println("Title doesn't exist");
			return;
		}

		System.out.println("Enter the new title of the item");
		String new_title = sc.nextLine().trim();
		if (l.isDuplicate(new_title)) {
			System.out.println("Title can't be duplicate");
			return;
		}
	
		System.out.println("Enter the new description ");
		String new_description = sc.nextLine().trim();
		for (TodoItem item : l.getList()) {
			if (item.getTitle().equals(title)) {
				l.deleteItem(item);
				TodoItem t = new TodoItem(new_title, new_description);
				l.addItem(t);
				System.out.println("Item has been edited");
			}
		}
		System.out.println("The item has been updated!");
	}

	public static void listAll(TodoList l) throws Exception {
		for (TodoItem item : l.getList()) {
			System.out.println("<" + item.getTitle() + "> " + item.getDesc() + " - " + item.getCurrent_date());
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
			String title, desc, date;
			FileReader fr = new FileReader(filename);
			BufferedReader br = new BufferedReader(fr);
			String i = " ";
			while(i != null) { 
				i = br.readLine();
				StringTokenizer st = new StringTokenizer(i, "##");
				while(st.hasMoreTokens()) {
					title = st.nextToken();
					desc = st.nextToken();
					date = st.nextToken();
					TodoItem t = new TodoItem(title, desc);
					Date date1=new SimpleDateFormat("E MMM dd HH:mm:ss z yyyy").parse(date); 
					t.setCurrent_date(date1);
					l.addItem(t);
				}
			}
		} catch(Exception e) {System.out.print("todoList.txt doesn't exist");}
	}
}
