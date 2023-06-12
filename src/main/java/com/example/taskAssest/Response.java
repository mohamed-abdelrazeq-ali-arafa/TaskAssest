package com.example.taskAssest;

import lombok.Data;

@Data
public class Response<t> {

    t data;
    String error;

    Boolean isSuccess;
    public  void  sucess(t data){
        this.data=data;
        isSuccess=true;
        this.error=null;

    }

    public  void  failure(String error){
        isSuccess=false;
        this.error=error;

    }

    public void make(String msg,int code,t data){

        if(code==400) {
            failure(msg);
        }
        else
        {
            sucess(data);
        }

    }


}
