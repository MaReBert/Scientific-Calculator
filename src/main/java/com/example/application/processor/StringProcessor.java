package com.example.application.processor;

import com.example.application.calculator.Calculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class StringProcessor {

    @Autowired
    private Calculator calculator;

    public ArrayList<String> processInputString(String inputString){

        String[] stringArray = splitString(inputString);

        if(!validateInput(stringArray)){
            stringArray = new String[1];
            stringArray[0] = "Syntax Error";
        }
        List<String> stringArrayList = new ArrayList<>(Arrays.asList(stringArray));

        return (ArrayList<String>) stringArrayList;
    }
    private boolean validateInput(String[] stringArray){

        return validateRepeatOpperators(stringArray) && validateRepeatParenthesis(stringArray) && validateOnlyOperators(stringArray);

    }

    private boolean validateOnlyOperators(String[] stringArray) {
        for(String s : stringArray){
            try {
                Double.parseDouble(s);
                return true;
            } catch(NumberFormatException e){

            }
        }
        return false;
    }

    private boolean validateRepeatParenthesis(String[] stringArray){
        int rightParenthesis = 0;
        int leftParenthesis = 0;

        for (String s : stringArray) {
            if (s.equals("(")) {
                leftParenthesis++;
            }
            if (s.equals(")")) {
                rightParenthesis++;
            }
        }
        return rightParenthesis == leftParenthesis;
    }

    private boolean validateRepeatOpperators(String[] stringArray){
        boolean counter = true;
        for (String s : stringArray) {
            if (s.equals("+") ||
                    s.equals("-") ||
                    s.equals("*") ||
                    s.equals("/")) {

                if (counter) {
                    return false;
                } else {
                    counter = true;
                }
            } else {
                counter = false;
            }
        }
        return true;
    }

    private String[] splitString(String inputString){

        return inputString.split("((?<=[)([-]+*/])|(?=[)([-]+*/]+))");
    }
}
