package com.refill.test;

import com.refill.base.BasicTest;
import org.junit.Test;

import java.util.*;

public class SortTest extends BasicTest {
    public class Student implements Comparable<Student> {
        private String name;        // 姓名
        private int age;            // 年龄

        public Student(String name, int age) {
            this.name = name;
            this.age = age;
        }

        @Override
        public String toString() {
            return "Student [name=" + name + ", age=" + age + "]";
        }

        @Override
        public int compareTo(Student o) {
            return this.age - o.age; // 比较年龄(年龄的升序)
        }
    }

    public class Student2 {
        private String name;        // 姓名
        private int age;            // 年龄

        public Student2(String name, int age) {
            this.name = name;
            this.age = age;
        }


        /**
         * 获取学生姓名
         */
        public String getName() {
            return name;
        }

        /**
         * 获取学生年龄
         */
        public int getAge() {
            return age;
        }


        @Override
        public String toString() {
            return "Student [name=" + name + ", age=" + age + "]";
        }

    }

    @Test
    public void run1() {
        Set<Student> set = new TreeSet<>();     // Java 7的钻石语法(构造器后面的尖括号中不需要写类型)
        set.add(new Student("Hao LUO", 33));
        set.add(new Student("XJ WANG", 32));
        set.add(new Student("Bruce LEE", 60));
        set.add(new Student("Bob YANG", 22));

        for (Student stu : set) {
            System.out.println(stu);
        }
    }

    @Test
    public void run2() {
        List<Student2> list = new ArrayList<>();     // Java 7的钻石语法(构造器后面的尖括号中不需要写类型)
        list.add(new Student2("Hao LUO", 33));
        list.add(new Student2("XJ WANG", 32));
        list.add(new Student2("Bruce LEE", 60));
        list.add(new Student2("Bob YANG", 22));

        // 通过sort方法的第二个参数传入一个Comparator接口对象
        // 相当于是传入一个比较对象大小的算法到sort方法中
        // 由于Java中没有函数指针、仿函数、委托这样的概念
        // 因此要将一个算法传入一个方法中唯一的选择就是通过接口回调
        Collections.sort(list, new Comparator<Student2>() {

            @Override
            public int compare(Student2 o1, Student2 o2) {
                return o1.getName().compareTo(o2.getName());    // 比较学生姓名
            }
        });

        for (Student2 stu : list) {
            System.out.println(stu);
        }
    }
}
