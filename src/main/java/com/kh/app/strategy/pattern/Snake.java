package com.kh.app.strategy.pattern;

public class Snake extends Pet {

	public Snake() {
		super();
	}

	public Snake(String name) {
		super(name);
	}

	@Override
	public String toString() {
		return "Snake [name=" + name + "]";
	}

}
