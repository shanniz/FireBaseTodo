package com.example.shan.firebasetodo;


public class Task {

    public String todoTask;
    public String mDueDate;

    //MUST ADD 0 ARGUMENT CONSTRUCTOR
    public Task(){
        this.todoTask="";
        this.mDueDate="";
    }


    public Task(String todoTask, String mDueDate) {
        this.todoTask = todoTask;
        this.mDueDate = mDueDate;
    }


    public void setTodoTask(String todoTask) {
        this.todoTask = todoTask;
    }

    public void setDueDate(String dueDate) {
        mDueDate = dueDate;
    }

    //MUST DEFINE GETTER FUNCTIONS IN ORDER TO SERIALIZE OBJECT

    public String getTask(){
        return todoTask;
    }

    public String getDate(){
        return this.mDueDate;
    }
}
