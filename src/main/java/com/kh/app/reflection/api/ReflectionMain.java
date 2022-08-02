package com.kh.app.reflection.api;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * reflect 투영/반사하다
 * 
 *  - 클래스 객체를 통해서 클래스 정보를 열람. 객체를 생성. 메소드를 호출. 필드값 제어
 *
 */

public class ReflectionMain {

	public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException {
		ReflectionMain main = new ReflectionMain();
//		main.test1();
//		main.test2();
		main.test3();
		
	}

	/**
	 * 필드제어
	 * @throws ClassNotFoundException 
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws NoSuchFieldException 
	 */
	private void test3() throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, NoSuchFieldException {
		Class<Sample> clz = (Class<Sample>) Class.forName("com.kh.app.reflection.api.Sample");
		Sample sample = clz.getDeclaredConstructor(int.class, String.class).newInstance(123, "Wow");
		Field num = clz.getDeclaredField("num");
		System.out.println(num);
		
		num.setAccessible(true);  //private 필드도 접근가능하도록 설정
		
		Object value = num.get(sample);
		System.out.println(value);
		
		System.out.println(sample);
	}

	/**
	 * 메소드제어
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	private void test2() throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		Class clz = Sample.class;
		Method[] methods = clz.getDeclaredMethods();
		for(Method method : methods) {
			System.out.println(method);
		}
		
		//하나의 메소드 가져오기
		Sample sample = (Sample) clz.getDeclaredConstructor(null).newInstance(null);
		
		Method setNum = clz.getDeclaredMethod("setNum", int.class);
		Object returnValue = setNum.invoke(sample, 123);    //invoke(대상객체, 전달할 인자)
		System.out.println(returnValue);
		
		Method getNum = clz.getDeclaredMethod("getNum", null);
		returnValue = getNum.invoke(sample);
		System.out.println(returnValue);
		
		System.out.println(sample);
	}


	/**
	 * 객체 생성
	 * 
	 * 클래스 객체
	 * - 클래스당 하나씩 만들어지는 객체로 클래스의 모든 정보를 가지고 있다.
	 * - new 연산자 호출시에도 이 클래스 객체를 베이스로 객체가 생성된다.
	 * 
	 * @throws ClassNotFoundException 
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	private void test1() throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Sample s1 = new Sample();
		
		Class clz1 = Sample.class;
		Class clz2 = s1.getClass();
		Class clz3 = Class.forName("com.kh.app.reflection.api.Sample");
		
		System.out.println(clz1 == clz2);  //true
		System.out.println(clz1 == clz3);  //true

		//기본생성자
		Constructor<Sample> const1 = clz1.getDeclaredConstructor(null);
		Sample s2 = const1.newInstance(null);
		System.out.println(s2); //Sample [num=0, str=null]

		//
		Class[] parameterTypes = {int.class, String.class};
		Constructor<Sample> const2 = clz1.getDeclaredConstructor(parameterTypes);
//		Constructor<Sample> const2 = clz1.getDeclaredConstructor(int.class, String.class);
		Object[] initArgs = {100, "helloworld"};
		Sample s3 = const2.newInstance(initArgs);
//		Sample s3 = const2.newInstance(100, "helloworld");
		System.out.println(s3);  //Sample [num=100, str=helloworld]
	}
	
	

}
