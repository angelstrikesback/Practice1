package com.test.practice;

import java.util.Hashtable;

public class Roman {

    String romanValue;
    public static final Hashtable<String, Integer> romanDict = new Hashtable<String, Integer>();

    public Roman() {
        romanDict.put("I", 1);
        romanDict.put("V", 5);
        romanDict.put("X", 10);
        romanDict.put("L", 50);
        romanDict.put("C", 100);
        romanDict.put("D", 500);
        romanDict.put("M", 1000);

        romanValue = "";
    }

    public boolean validate(String input) {
        if (input.matches(".*[^IXCMVLD].*")) {
            System.out.println("Invalid Character Found");
            return false;
        }
//        if (!input.matches("[IXCMVLD]+")) {
//            System.out.println("Invalid Char");
//            return false;
//        }
        if (input.matches(".*[IXCM]{4}.*")) {
            System.out.println("Invalid - More than 4 repetitions");
            return false;
        }
        String[] arr = input.split("");
        for (int i=1; i<arr.length-1; i++) {
            int curr = romanDict.get(arr[i]);
            int next = romanDict.get(arr[i+1]);
            if ((curr < next) && ((next != 5 * curr && next != 10 * curr) || (curr == 5 || curr == 50 || curr == 500))){
                System.out.println("Invalid subtraction rule : Curr="+ curr + " : Next=" + next);
                return false;
            }
        }
        return true;
    }

    public int toArabic(String input) {

        String[] arr = input.split("");

        int sum = 0;
        int i=1;
        while (i<arr.length) {
//            System.out.println("Iterating " + i);
            if (i == arr.length - 1 || (romanDict.get(arr[i]) >= romanDict.get(arr[i+1]))) {
                sum += romanDict.get(arr[i]);
            } else if (romanDict.get(arr[i]) < romanDict.get(arr[i+1])) {
                sum = sum + (romanDict.get(arr[i+1]) - romanDict.get(arr[i]));
                i++;
            }
            i++;
        }

//        System.out.println("Integer Equivalent of " + input + " is " + sum);
        return sum;
    }

    public static void main(String[] args) {
        String input = "XLIIA";

        Roman r = new Roman();
        if (!r.validate(input)) {
            System.out.println("Invalid Input");
        } else {
            System.out.println("Integer Equivalent of " + input + " is " + r.toArabic(input));
        }
    }
}
