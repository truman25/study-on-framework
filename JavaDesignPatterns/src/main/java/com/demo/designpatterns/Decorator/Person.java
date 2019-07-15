package com.demo.designpatterns.Decorator;

public class Person implements Component {

	private String name;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Person(String name){
        this.name = name;
    }

    @Override
    public void show() {
        System.out.println("×°°çµÄ" + name);
    }
}
