package dev.subashcodes.librarymangement.service;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Primary
@Component
public class HPComputer implements  Computer{
    @Override
    public String bootup() {
        return "Started HP Computer Successfully";
    }

    @Override
    public String shutdown() {
        return "Shutdown HP Computer";
    }
}
