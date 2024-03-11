package com.epam.rd.autotasks.validations;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ColorCodeValidation {
    public static boolean validateColorCode(String color) {
if (color == null){
    return false;
}else {
    boolean colorCheck = color.matches("(^#([a-fA-F0-9]{6}|[a-fA-F0-9]{3})$)");
    return colorCheck;
}
    }
}




