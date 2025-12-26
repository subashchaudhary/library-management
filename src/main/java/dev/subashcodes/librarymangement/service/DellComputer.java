package dev.subashcodes.librarymangement.service;

import org.springframework.stereotype.Component;


//by default the bean name will be class name with camelCase (dellComputer)


@Component(value = "dell")
public class DellComputer implements Computer{
    @Override
    public String bootup() {
        return "Stated Dell Computer Successuflly";
    }

    @Override
    public String shutdown() {

        return "ShutDown Dell Computer";
    }
}
