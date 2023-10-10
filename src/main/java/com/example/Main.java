package com.example;

public class Main {
    public static void main(String[] args) {
        
        // Change offset character to test.
        Encoder encoder = new Encoder('F');
        encoder.printEncodeMap();
        // Change input string to test.
        String s = encoder.encodeString("Hello World! !@#$%^&*(){};:',./");
        System.out.println(s);
        s = encoder.decodeString(s);
        System.out.println(s);
    }
}