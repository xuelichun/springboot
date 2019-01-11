package com.bonc.test1217;


import com.alibaba.fastjson.JSON;
import com.sun.tools.doclets.formats.html.SourceToHTMLConverter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Stream;

/**
 * @Author : xuelc
 * @Description:
 * @Date : Created in 10:56 2018/12/17.
 * @Modified By:
 */
public class Lambda {
    public static void main(String[] args) {
        List<String> list = new ArrayList();
        list.add("aa");
        list.add("bb");
        list.add("cc");
        list.add("dd");
        list.add("ee");
        list.add("ff");
        list.add("gg");

//        for(String str:list){
//            System.out.println(str);
//        }
         /*//使用 lambda 表达式以及函数操作(functional operation)
         list.forEach((str)-> System.out.println(str));*/
        /*//在 Java 8 中使用双冒号操作符(double colon operator)
        list.forEach(System.out::println);*/



        String[] players = {"Rafael Nadal", "Novak Djokovic",
                "Stanislas Wawrinka", "David Ferrer",
                "Roger Federer", "Andy Murray",
                "Tomas Berdych", "Juan Martin Del Potro",
                "Richard Gasquet", "John Isner"};
//        Arrays.sort(players, new Comparator<String>() {
//            @Override
//            public int compare(String o1, String o2) {
//                return o1.compareTo(o2);
//            }
//        });


        /*Comparator<String> sortName=(String o1, String o2)->(o1.compareTo(o2));
        Arrays.sort(players,sortName);
        */


        /*Arrays.sort(players,(String o1, String o2)->(o1.compareTo(o2)));
//todo 输出

        System.out.println(JSON.toJSONString(Arrays.asList(players)));
*/
        List<Person> javaProgrammers = new ArrayList<Person>() {
            {
                add(new Person("Elsdon", "Jaycob", "Java programmer", "male", 43, 2000));
                add(new Person("Tamsen", "Brittany", "Java programmer", "female", 23, 1500));
                add(new Person("Floyd", "Donny", "Java programmer", "male", 33, 1800));
                add(new Person("Sindy", "Jonie", "Java programmer", "female", 32, 1600));
                add(new Person("Vere", "Hervey", "Java programmer", "male", 22, 1200));
                add(new Person("Maude", "Jaimie", "Java programmer", "female", 27, 1900));
                add(new Person("Shawn", "Randall", "Java programmer", "male", 30, 2300));
                add(new Person("Jayden", "Corrina", "Java programmer", "female", 35, 1700));
                add(new Person("Palmer", "Dene", "Java programmer", "male", 33, 2000));
                add(new Person("Addison", "Pam", "Java programmer", "female", 34, 1300));
            }
        };

        List<Person> phpProgrammers = new ArrayList<Person>() {
            {
                add(new Person("Jarrod", "Pace", "PHP programmer", "male", 34, 1550));
                add(new Person("Clarette", "Cicely", "PHP programmer", "female", 23, 1200));
                add(new Person("Victor", "Channing", "PHP programmer", "male", 32, 1600));
                add(new Person("Tori", "Sheryl", "PHP programmer", "female", 21, 1000));
                add(new Person("Osborne", "Shad", "PHP programmer", "male", 32, 1100));
                add(new Person("Rosalind", "Layla", "PHP programmer", "female", 25, 1300));
                add(new Person("Fraser", "Hewie", "PHP programmer", "male", 36, 1100));
                add(new Person("Quinn", "Tamara", "PHP programmer", "female", 21, 1000));
                add(new Person("Alvin", "Lance", "PHP programmer", "male", 38, 1600));
                add(new Person("Evonne", "Shari", "PHP programmer", "female", 40, 1800));
            }
        };

        //System.out.println("所有程序员的姓名:");
        /*javaProgrammers.forEach((p) -> System.out.printf("%s %s",p.getFirstName(), p.getLastName()));
        phpProgrammers.forEach((p) -> System.out.printf("%s %s; ", p.getFirstName(), p.getLastName()));
*/




        /*System.out.println("给程序员加薪 5% :");
        Consumer<Person> giveRaise = e -> e.setSalary(e.getSalary() / 100 * 5 + e.getSalary());

        javaProgrammers.forEach(giveRaise);
        phpProgrammers.forEach(giveRaise);

        javaProgrammers.forEach((j)-> System.out.println(j.getSalary()));
*/
        //phpProgrammers.stream().filter((p) -> (p.getSalary() > 1440)).forEach((p) -> System.out.printf("%s %s; ", p.getFirstName(), p.getLastName()));

        // 1. Individual values
        Stream stream = Stream.of("a", "b", "c");
        System.out.println();
// 2. Arrays
        String [] strArray = new String[] {"a", "b", "c"};
        stream = Stream.of(strArray);
        stream = Arrays.stream(strArray);
// 3. Collections
        List<String> lista = Arrays.asList(strArray);
        stream = list.stream();
    }



}
