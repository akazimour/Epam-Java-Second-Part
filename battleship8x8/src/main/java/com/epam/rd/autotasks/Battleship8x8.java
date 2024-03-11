package com.epam.rd.autotasks;

import java.util.ArrayList;
import java.util.List;

public class Battleship8x8 {
    private final long ships;
    private long shots = 0L;

    public Battleship8x8(final long ships) {
        this.ships = ships;
    }

    public boolean shoot(String shot) {
        char[] charArray = shot.toCharArray();
        char first = charArray[0];
        long createdShot = 0b10000000_00000000_00000000_00000000_00000000_00000000_00000000_00000000L;
        switch (first) {
            case 'A':
                break;
            case 'B':
                createdShot >>>= 1;
                break;
            case 'C':
                createdShot >>>= 2;
                break;
            case 'D':
                createdShot >>>= 3;
                break;
            case 'E':
                createdShot >>>= 4;
                break;
            case 'F':
                createdShot >>>= 5;
                break;
            case 'G':
                createdShot >>>= 6;
                break;
            case 'H':
                createdShot >>>= 7;
                break;
        }
        createdShot >>>= (8 * (shot.charAt(1) - 1));
        shots |= createdShot;
        if (ships == (ships | createdShot)) {
            return true;
        }
        return false;
//        int rows = Integer.parseInt(String.valueOf(charArray[1]));
//        String r = String.valueOf(rows);
//        String c = String.valueOf(col);
//        String actualShot = r + c;
//        shots = Long.parseLong(actualShot);
//
//        String binaryString = Long.toBinaryString(this.ships);
//        String[] results = binaryString.split("(?<=\\G.{" + 8 + "})");
//        for (int i = 0; i < results.length; i++) {
//            char[] chars = results[i].toCharArray();
//            for (int j = 0; j < chars.length; j++) {
//                switch (chars[j]) {
//                    case '1':
//                        chars[j] = '☐';
//                        break;
//                    case '0':
//                        chars[j] = '.';
//                        break;
//                }
//            }
//            results[i] = String.valueOf(chars);
//        }
//        System.arraycopy(results,0,marked,0,marked.length);

//        if (results[rows].charAt(col)=='1'){
//            StringBuilder builder = new StringBuilder(results[rows]);
//            builder.setCharAt(col,'☒');
//            results[rows]=builder.toString();

//            return true;
//        }else if(results[rows].charAt(col)=='☒') {
//            return false;
//        }else if(results[rows].charAt(col)=='.') {
//            StringBuilder builder = new StringBuilder(results[rows]);
//            builder.setCharAt(col,'×');
//            results[rows]=builder.toString();
//            return false;
//        }else
//
//            return false;
    }

    public String state() {
        String binaryString = Long.toBinaryString(ships);
        String strShots = Long.toBinaryString(shots);
        StringBuilder bufZeros = new StringBuilder();
        if (strShots.length() < 64) {
            for (int i = 0; i < 64 - strShots.length(); i++)
                bufZeros.append("0");
            strShots = bufZeros.append(strShots).toString();
            bufZeros.delete(0, bufZeros.length());
        }
        if (binaryString.length() < 64) {
            for (int i = 0; i < 64 - binaryString.length(); i++)
                bufZeros.append("0");
            binaryString = bufZeros.append(binaryString).toString();
            bufZeros.delete(0, bufZeros.length());
        }
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < strShots.length(); i++) {
            if (i%8 == 0)
                result.append("\n");
            if (binaryString.charAt(i) == '0' && strShots.charAt(i) == '0')
                result.append(".");
            if (binaryString.charAt(i) == '0' && strShots.charAt(i) == '1')
                result.append("×");
            if (binaryString.charAt(i) == '1' && strShots.charAt(i) == '0')
                result.append("☐");
            if (binaryString.charAt(i) == '1' && strShots.charAt(i) == '1')
                result.append("☒");
        }
        return result.toString();
    }
//        String[] results = binaryString.split("(?<=\\G.{" + 8 + "})");
//                for (int i = 0; i < results.length; i++) {
//            char[] chars = results[i].toCharArray();
//            for (int j = 0; j < chars.length; j++) {
//                switch (chars[j]) {
//                    case '1':
//                        chars[j] = '☐';
//                        break;
//                    case '0':
//                        chars[j] = '.';
//                        break;
//                }
//            }
//            results[i] = String.valueOf(chars);
//        }
//        String shotString = Long.toString(shots);
//                if (shotString.length()==1){
//                    String toShot = "A"+shotString;
//                    int i = Integer.parseInt(shotString);
//                    if (shoot(toShot)==true){
//                        StringBuilder builder = new StringBuilder(results[0]);
//                        builder.setCharAt(i,'☒');
//                        results[0]=builder.toString();
//                        shots=0L;
//                    } else if (shoot(toShot)==false && results[0].charAt(i)=='.') {
//                        StringBuilder builder = new StringBuilder(results[0]);
//                        builder.setCharAt(i,'×');
//                        results[0]=builder.toString();
//                        shots=0L;
//                    }
//                }else {
//                    char col = '0';
//                    char c = shotString.charAt(0);
//                    switch (c) {
//                        case '1':
//                            col = 'B';
//                            break;
//                        case '2':
//                            col = 'C';
//                            break;
//                        case '3':
//                            col = 'D';
//                            break;
//                        case '4':
//                            col = 'E';
//                            break;
//                        case '5':
//                            col = 'F';
//                            break;
//                        case '6':
//                            col = 'G';
//                            break;
//                        case '7':
//                            col = 'H';
//                            break;
//                    }
//                    char c1 = shotString.charAt(1);
//                    String shooting = String.valueOf(col) + String.valueOf(c1);
//
//                    int fIndex = Integer.parseInt(String.valueOf(c));
//                    int sIndex = Integer.parseInt(String.valueOf(c1));
//
//                    if (shoot(shooting)==true){
//                        StringBuilder builder = new StringBuilder(results[fIndex]);
//                        builder.setCharAt(sIndex,'☒');
//                        results[0]=builder.toString();
//                        shots=0L;
//                    }else if (shoot(shooting)==false && results[fIndex].charAt(sIndex)=='.') {
//                        StringBuilder builder = new StringBuilder(results[0]);
//                        builder.setCharAt(sIndex,'×');
//                        results[0]=builder.toString();
//                        shots=0L;
//                    }
//                }
//
//
//        String joined = String.join("\n", results);
//        return joined;
//    }
    }

