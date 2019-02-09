package com.mykcom.javapractice.main;


public class PracticeMain {

    private static String showTab(String str){
        return str.replace("\t", "\\t");
    }

    public static void main(String []args) {
//        String test = "\t\tHello W\torld";
        ReplaceTab obj = new ReplaceTab();
//
//        System.out.println(showTab(test));
//        System.out.println(showTab(obj.replaceHaedTabToSpace(test, 4)));

        String test2 = "    \t    Hello W\t    orld\n        Hel\tlo    Worl  d2";
        System.out.println("Original");
        System.out.println(showTab(test2));
        System.out.println("replaceSpacesToTab");
        System.out.println(showTab(obj.replaceSpacesToTab(test2, 4)));
        System.out.println("reaplceHeadSpacesToTab");
        System.out.println(showTab(obj.replaceHeadSpacesToTab(test2, 4)));

    }
}
