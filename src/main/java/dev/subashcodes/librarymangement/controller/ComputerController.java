package dev.subashcodes.librarymangement.controller;


import dev.subashcodes.librarymangement.service.Computer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ComputerController {


    @Autowired
    //@Qualifier("dell") //asking bean based on the Qualifier of given bean name
    private Computer computer;

    @GetMapping("/computer/start")
    public String start(){
       return computer.bootup();
    }

    @GetMapping("/computer/stop")
    public String stop(){
       return computer.shutdown();
    }
}
