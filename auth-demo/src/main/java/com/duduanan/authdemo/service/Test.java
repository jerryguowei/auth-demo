package com.duduanan.authdemo.service;

import java.io.Serializable;
import java.util.Arrays;

import org.springframework.util.SerializationUtils;

public class Test implements Serializable {
    
    private static final long serialVersionUID = 1L;
    private int result; 
    
    public Test(int result) {
        this.result = result;
    }
    
    public String toString() {
        return "the result is " + result;
    }

public static void main(String[] args) {
       byte [] bytes = SerializationUtils.serialize(new Test(15));      
       
       
       
       System.out.println(Arrays.toString(bytes));
       System.out.println(SerializationUtils.deserialize(bytes));
   }
}
