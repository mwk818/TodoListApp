package com.todo.dao;

import java.util.Date;
import java.text.SimpleDateFormat;

public class TodoItem {
    private String title;
    private String desc;
    private Date dcurrent_date;
    private String current_date;
    private String category;
    private String due_date;


    public TodoItem(String title, String desc, String category, String due_date){
        this.title=title;
        this.desc=desc;
        this.dcurrent_date=new Date();
    	SimpleDateFormat s = new SimpleDateFormat("yyyy/mm/dd HH:mm:ss");
        this.current_date= s.format(dcurrent_date);
        this.category=category;
        this.due_date=due_date;
    }
    
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getCurrent_date() {
        return current_date;
    }

    public void setCurrent_date(String current_date) {
        this.current_date = current_date;
    }
    
    public String getCategory() {
    	return category;
    }
    
    public void setCategory(String category) {
    	this.category = category;
    }
    
    public String getDue_date() {
    	return due_date;
    }
    
    public void setDue_date(String due_date) {
    	this.due_date = due_date;
    }
    
    public String toSaveString() {
    	return title + "##" + category + "##" + desc + "##" + due_date + "##" + current_date + "\n";
    }
}
