package dev.subashcodes.librarymangement.service;

import org.springframework.stereotype.Component;


// this annotatoin tells spring to crate a bean
@Component
public class AddService {


    //addition method
    public int addNum(int a, int b){
        return a  + b;
    }
}
