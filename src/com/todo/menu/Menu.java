package com.todo.menu;
public class Menu {

    public static void displaymenu()
    {
    	System.out.println();
        System.out.println("<Commands for ToDoList>");
        System.out.println("add            Adds a new item");
        System.out.println("del            Deletes an existing item");
        System.out.println("edit           Updates an item"); 
        System.out.println("ls             Lists all items");
        System.out.println("ls_name_asc    Sorts the list by name");
        System.out.println("ls_name_desc   Sorts the list by name");
        System.out.println("ls_date        Sorts the list by date");
        System.out.println("help           Shows the list of commands");
        System.out.println("exit           Exit command");
        System.out.println();
    }
    public static void prompt() {
    	System.out.print("\nEnter a command > ");
    }
}
