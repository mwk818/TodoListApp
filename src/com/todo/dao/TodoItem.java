package com.todo.dao;

import java.util.Date;
import java.text.SimpleDateFormat;

public class TodoItem {
    private String title;
    private String desc;
    private Date current_date;


    public TodoItem(String title, String desc){
        this.title=title;
        this.desc=desc;
        this.current_date=new Date();
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
    	String strDate = null;
    	SimpleDateFormat sdf = new SimpleDateFormat("E MMM dd HH:mm:ss z yyyy");
    	strDate = sdf.format(current_date);
        return strDate;
    }

    public void setCurrent_date(Date current_date) {
        this.current_date = current_date;
    }
    
    public String toSaveString() {
    	return title + "##" + desc + "##" + current_date + "\n";
    }
}
