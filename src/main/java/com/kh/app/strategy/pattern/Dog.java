package com.kh.app.strategy.pattern;

public class Dog extends Pet {

	public Dog() {
		super();
	}

	public Dog(String name) {
		super(name);
	}

	@Override
	public String toString() {
		return "Dog [name=" + name + "]";
	}

}
