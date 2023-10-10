package com.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import  java.lang.Math;

public class Encoder {

    private Map<Character, Character> encodeMap;
    private Map<Character, Character> decodeMap;
    private static List<Character> referenceTable;
    private char offsetChar;

    // Create reference table as in problem statement.
    static
    {
        referenceTable = new ArrayList<>();
        for(char c = 'A'; c <= 'Z'; c++) referenceTable.add(c);
        for(char c = '0'; c <= '9'; c++) referenceTable.add(c);
        for(char c = '('; c <= '/'; c++) referenceTable.add(c);
    }

    // Main Encoder constructor. Saves input offset character and creates two Maps (HashMaps) of the
    // original reference table w.r.t offset reference table (determined by said offset character) and vice-versa accordingly.
    public Encoder(char offsetChar){
        this.offsetChar = offsetChar;
        int offsetByValue = referenceTable.indexOf(offsetChar);
        if (offsetByValue == -1) offsetByValue = 0; // If input offset character not in reference table, default to 0 (i.e., no offset).

        int referenceTableSize = referenceTable.size();

        encodeMap = new HashMap<>();
        for(int i = 0; i < referenceTableSize; i++){
            int offsetIndex = Math.floorMod((i - offsetByValue), referenceTableSize);
            encodeMap.put(referenceTable.get(i), referenceTable.get(offsetIndex));
        }

        decodeMap = new HashMap<>();
        for (char key : encodeMap.keySet()){
            decodeMap.put(encodeMap.get(key), key);
        }
    }

    // Self-explainatory.
    void printEncodeMap(){
        System.out.println(encodeMap);
    }

    void printDecodeMap(){
        System.out.println(decodeMap);
    }


    // Encodes an input string and adds the instanced Encoder's offsetChar to beginning of encoded string.
    public String encodeString(String string){
        string = string.toUpperCase();

        String encodedString = "" + offsetChar;
        for(Character c: string.toCharArray()){
            if (encodeMap.containsKey(c)) encodedString += encodeMap.get(c);
            else encodedString += c;
        }
        return encodedString;
    }

    // Decodes an encodedString. If beginning character of encoded string does not match the instanced Encoder's offsetChar, method returns the original input encodedString.
    public String decodeString(String encodedString){
        encodedString.toUpperCase();

        if (encodedString.charAt(0) != offsetChar){
            System.out.println("ERROR: First character of string does not match Encoder's offset character. Returned string not decoded.");
            return encodedString;
        }
        else{
            String decodedString = "";
            for(char c: encodedString.substring(1).toCharArray()){
                if (decodeMap.containsKey(c)) decodedString += decodeMap.get(c);
                else decodedString += c;
            }
            return decodedString;
        }
    }
}
