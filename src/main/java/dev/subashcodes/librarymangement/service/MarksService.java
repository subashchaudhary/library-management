package dev.subashcodes.librarymangement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MarksService {

    @Autowired
    AddService addService;

    public Integer calcuateMarks(int subj1MArks, int subject2Marks){


      return addService.addNum(subj1MArks, subject2Marks);


    }
}
