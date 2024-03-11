package com.epam.rd.autotasks.words;

import java.util.Arrays;

public class StringUtil {
    public static int countEqualIgnoreCaseAndSpaces(String[] words, String sample) {

        if ( sample == null || words == null || words.length == 0|| sample.isBlank()){
            return 0;
        }else {
            String[] wordsCopy = new String[words.length];
            System.arraycopy(words,0,wordsCopy,0,words.length);
            sample = sample.strip();
            int counter = 0;
            for (int i = 0; i < wordsCopy.length; i++) {
                String temp = wordsCopy[i].strip();
                wordsCopy[i] = temp;
                StringBuilder builder = new StringBuilder();
                if (builder.append(wordsCopy[i]).toString().equalsIgnoreCase(sample)) {
                    counter++;
                }
            }
            return counter;
        }
    }

    public static String[] splitWords(String text) {
        if (text == null || text.isBlank()){
            return null;
        }
        text = text.strip();
        String s[] = text.split("[,.;: ?!]+\\s*");
        if(s.length > 0){
            String temp = s[0];
            if (temp.isBlank()){
                String [] arr = new String[s.length-1];
                System.arraycopy(s,1,arr,0,arr.length);
                return arr;
            }else {
                return s;
            }
        }else
            return null;
    }

    public static String convertPath(String path, boolean toWin) {
    if (path==null){
        return null;
    }

        boolean winCheck = path.matches("([C:\\\\]*[\\\\a-zA-Z0-9-_ .]*)");
        boolean unixCheck = path.matches("([~////]?[////a-zA-Z0-9-_ .]*)");
        String finishedPath = "";
        if (unixCheck==false && winCheck== false || path.isBlank()){
            return null;
        }
        if((winCheck == true && toWin == true) || (unixCheck == true && toWin ==false)){
            return path;
        }
        else if (toWin==false) {

          finishedPath = convertToUnix(path);

        } else if (toWin == true){

            finishedPath = convertToWin(path);
        }
            return finishedPath;
    }
    private static String convertToUnix(String path) {
        if (path.startsWith("C:\\User")){
            path = path.replace("C:\\User","~");
        } else if (path.startsWith("C:\\")) {
            path = path.replace("C:\\","/");
        }
        path = path.replace("\\", "/");
       return path;
    }
    private static String convertToWin(String path) {
        if (path.startsWith("~")){
            path = path.replace("~","C:\\User");
        } else if (path.startsWith("/")) {
            path = path.replaceFirst("/","C:\\\\");
        }
        path = path.replace("/", "\\");
        return path;
    }

    public static String joinWords(String[] words) {
        String cont = removeEmpty(words);
        return cont;
    }
    private static String removeEmpty(String[] array) {
        if(array == null || array.length==0){
            return null;
        }
        int count = 0;
        String cont = "";
        for (int i = 0; i < array.length; i++) {
            if(array[i].isBlank()){
                count++;
                if (count == array.length){
                    return null;
                }
                continue;
            }
            if (i == array.length){
                cont = cont + array[i];
            }else {
                cont = cont + array[i] + ", ";
            }
        }
        int length = cont.length()-1;
        char lastC = cont.charAt(length);
        if (lastC == ' '){
            cont = cont.substring(0,cont.length()-2);
        }
        return "["+cont+"]";
    }

    public static void main(String[] args) {
        System.out.println("Test 1: countEqualIgnoreCaseAndSpaces");
        String[] words = new String[]{" WordS    \t", "words", "w0rds", "WOR  DS", };
        String sample = "words   ";
        int countResult = countEqualIgnoreCaseAndSpaces(words, sample);
        System.out.println("Result: " + countResult);
        int expectedCount = 2;
        System.out.println("Must be: " + expectedCount);

        System.out.println("Test 2: splitWords");
        String text = "   ,, first, second!!!! third";
        String[] splitResult = splitWords(text);
        System.out.println("Result : " + Arrays.toString(splitResult));
        String[] expectedSplit = new String[]{"first", "second", "third"};
        System.out.println("Must be: " + Arrays.toString(expectedSplit));

        System.out.println("Test 3: convertPath");
        String unixPath = "/some/unix/path";
        String convertResult = convertPath(unixPath, true);
        System.out.println("Result: " + convertResult);
        String expectedWinPath = "C:\\some\\unix\\path";
        System.out.println("Must be: " + expectedWinPath);

        System.out.println("Test 4: joinWords");
        String[] toJoin = new String[]{"go", "with", "the", "", "FLOW"};
        String joinResult = joinWords(toJoin);
        System.out.println("Result: " + joinResult);
        String expectedJoin = "[go, with, the, FLOW]";
        System.out.println("Must be: " + expectedJoin);
    }
}