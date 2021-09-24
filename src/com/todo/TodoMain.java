package com.todo;

import java.util.Scanner;

import com.todo.dao.TodoList;
import com.todo.menu.Menu;
import com.todo.service.TodoUtil;

public class TodoMain {
	
	public static void start() throws Exception {
	
		Scanner sc = new Scanner(System.in);
		TodoList l = new TodoList();
		boolean isList = false;
		boolean quit = false;
		TodoUtil.loadList(l, "todolist.txt");
		Menu.displaymenu();
		do {
			Menu.prompt();
			isList = false;
			String choice = sc.next();
			System.out.println();
			switch (choice) {

			case "add":
				TodoUtil.createItem(l);
				break;
			
			case "del":
				TodoUtil.deleteItem(l);
				break;
				
			case "edit":
				TodoUtil.updateItem(l);
				break;
				
			case "ls":
				System.out.println("[Listing all items]");
				TodoUtil.listAll(l);
				break;

			case "ls_name_asc":
				System.out.println("[Listing items in ascending order of each name]");
				l.sortByName();
				isList = true;
				break;

			case "ls_name_desc":
				System.out.println("[Listing items in descending order of each name]");
				l.sortByName();
				l.reverseList();
				isList = true;
				break;
				
			case "ls_date":
				System.out.println("[Listing items in order of each date]");
				l.sortByDate();
				isList = true;
				break;

			case "exit":
				quit = true;
				System.out.println("Bye bye~!!");
				break;
			
			case "help":
				Menu.displaymenu();
				break;

			default:
				System.out.println("Please enter the right command!!");
				break;
			}
			
			if(isList) l.listAll();
		} while (!quit);
	}
}
