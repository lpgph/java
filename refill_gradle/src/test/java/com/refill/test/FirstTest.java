package com.refill.test;


import org.apache.commons.collections.list.SynchronizedList;

import java.util.ArrayList;
import java.util.List;

class Animal{
    public void move(){
        System.out.println("动物可以移动");
    }

    public String move(String str){
        return str;
    }
}

class Dog extends Animal{
    public void move(){
        short s1 = 1;
        s1 +=1;

        System.out.println("狗可以跑和走");
    }
}

//重写是子类对父类的允许访问的方法的实现过程进行重新编写, 返回值和形参都不能改变。即外壳不变，核心重写！
//重载重载(overloading) 是在一个类里面，方法名字相同，而参数不同。返回类型可以相同也可以不同。每个重载的方法（或者构造函数）都必须有一个独一无二的参数类型列表。
public class FirstTest {
    public static void main(String args[]) {
//        Animal a = new Animal(); // Animal 对象
//        Animal b = new Dog(); // Dog 对象
//
//        a.move();// 执行 Animal 类的方法
//
//        b.move();//父类执行子类 Dog 类的方法

//        Integer f1 = 100, f2 = 100, f3 = 150, f4 = 150;
//        //如果整型字面量的值在-128到127之间，那么不会new新的Integer对象，而是直接引用常量池中的Integer对象
//        System.out.println(f1 == f2);
//        //对象的比较还是使用equals
//        System.out.println(f3 == f4);
//
//        //length
//        String str = "aaaaaa";
//        System.out.println(str.length());
//        String [] as = {"1","2"};
//        ArrayList<String> a = new ArrayList<>();
//        a.add("111");
//        System.out.println(as.length);
//        System.out.println(a.size());


            String s1 = "Programming";
            //同时创建两个对象　一个静态区的字符串，一个堆上的对象
            String s2 = new String("Programming");
            String s3 = "Program";
            String s4 = "ming";
            String s5 = "Program" + "ming";
            String s6 = s3 + s4;
            //静态区字符串和对象比较 false
            System.out.println(s1 == s2);
            //true
            System.out.println(s1.equals(s2));
            //静态区字符串比较　true
            System.out.println(s1 == s5);
            //字符串和对象比较 false
            System.out.println(s1 == s6);
            //intern节省内存但增加运行时间
            //需要传入一个字符串对象（已存在于堆上）,检查常量池中是否存在该字符串对象，存在则返回引用，不存在则加入后返回引用
            //s6.intern()返回的是静态池中的常量 true
            System.out.println(s1 == s6.intern());
            //对象和静态池中的变量比较 false
            System.out.println(s2 == s2.intern());
            //true
            System.out.println(s1 == s2.intern());

            String a1 = "abcdefg";
            System.out.println(a1.substring(1));
            System.out.println(a1.charAt(0));
    }

    public static String reverse(String originStr) {
        if(originStr == null || originStr.length() <= 1)
            return originStr;
        return reverse(originStr.substring(1)) + originStr.charAt(0);
    }

    public int cheng(int i,int num){
        return i << num;
    }
    public int chu(int i,int num) {
        return i >> num;
    }

    public List addList2(ArrayList list){

//        Collections.so
        return list;
    }

}

