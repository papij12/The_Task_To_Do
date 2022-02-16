package com.tasktodo;
;


import androidx.room.*;

import java.io.Serializable;

//Defining table name
@Entity(tableName = "Task")
public class MainTask implements Serializable {

    //Create id column

 @PrimaryKey(autoGenerate = true)
 private  int ID;

 //create text column

 @ColumnInfo(name = "text")
 private String text;

 //Generate getter and setter


    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
