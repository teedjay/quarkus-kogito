package org.acme;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AutomatedTasks {

    public Person doStuff(Person p) {
        System.out.println("I'm logging a person : " + p.toString());
        return p;
    }

    public void newAge(Person p) {
        p.age++;
        System.out.println("I'm increasing the age by 1 year to : " + p.toString());
    }
    
}