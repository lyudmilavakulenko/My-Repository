import java.lang.reflect.Array;
import  java.util.*;

public class Lesson4_Collection {

    public static void main(String[] args) {
        String[] brend = {"Intel", "Dell", "Apple", "IBM", "LG", "Dell"};
        List<String> brendList = Arrays.asList(brend);
        System.out.printf("%s ", brendList);
        System.out.println();
        Set<String> set = new HashSet<String>(brendList);
        System.out.printf("%s ", set);
        set.add("intel");
        set.add("Dell");
        set.add("       Apple");
        set.add("google");
        for (String el : set) {
            System.out.printf("%s \uD83D\uDEA9 ", el);  // для красоты
        }
        System.out.println("\nКоличество элементов: " + set.size());﻿
    }
}
