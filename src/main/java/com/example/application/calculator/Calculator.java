package com.example.application.calculator;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
@Component
public class Calculator {

    public String getResult(ArrayList<String> localArrayList) {
        if(localArrayList.size() > 1) {
            if(findRightParenthesis(localArrayList) > 0){
                ArrayList<String> extractedList = extractParenthesis(findLeftParenthesis(findRightParenthesis(localArrayList), localArrayList), findRightParenthesis(localArrayList), localArrayList);
                int indexLeftParenthesis = findLeftParenthesis(findRightParenthesis(localArrayList), localArrayList);

                localArrayList = deleteParenthesisTerm(indexLeftParenthesis, extractedList.size(), localArrayList);
                localArrayList.set(indexLeftParenthesis, getResult(extractedList));

                getResult(localArrayList);
            }
            if(findPointOperators(localArrayList) != 0) {
                ArrayList<String> extractedList = new ArrayList<>();
                extractedList.add(localArrayList.get(findPointOperators(localArrayList) - 1));
                extractedList.add(localArrayList.get(findPointOperators(localArrayList)));
                extractedList.add(localArrayList.get(findPointOperators(localArrayList) + 1));

                double result = calculatePointTerm(extractedList);

                localArrayList.set((findPointOperators(localArrayList) - 1), String.valueOf(result));

                localArrayList.remove(localArrayList.get(findPointOperators(localArrayList)) + 1);
                localArrayList.remove(localArrayList.get(findPointOperators(localArrayList)));

                getResult(localArrayList);
            }
            if(findLineOperators(localArrayList) != 0) {
                ArrayList<String> extractedList = new ArrayList<>();
                extractedList.add(localArrayList.get(findLineOperators(localArrayList) - 1));
                extractedList.add(localArrayList.get(findLineOperators(localArrayList)));
                extractedList.add(localArrayList.get(findLineOperators(localArrayList) + 1));

                double result = calculateLineTerm(extractedList);
                int lineOperatorIndex = findLineOperators(localArrayList);
                localArrayList.set((lineOperatorIndex - 1), String.valueOf(result));

                localArrayList.remove((lineOperatorIndex) + 1);
                localArrayList.remove(lineOperatorIndex);

                getResult(localArrayList);
            }
        }
        return localArrayList.get(0);
    }

    private double calculatePointTerm(ArrayList<String> arrayList){
        if (arrayList.get(1).equals("*")){
            return multiply(arrayList.get(0), arrayList.get(2));
        }else {
            return divide(arrayList.get(0), arrayList.get(2));
        }
    }
    private double calculateLineTerm(ArrayList<String> arrayList){
        if (arrayList.get(1).equals("+")){
            return sum(arrayList.get(0), arrayList.get(2));
        }else {
            return subtract(arrayList.get(0), arrayList.get(2));
        }
    }
    private double multiply(String factor1, String factor2){

        return Double.parseDouble(factor1) * Double.parseDouble(factor2);
    }
    private double sum(String summand1, String summand2){

        return Double.parseDouble(summand1) + Double.parseDouble(summand2);
    }
    private double subtract(String minuend, String subtrahend){

        return Double.parseDouble(minuend) - Double.parseDouble(subtrahend);
    }
    private double divide(String dividend, String divisor){

        return (Double.parseDouble(dividend)) / Double.parseDouble(divisor);

    }
    private int findRightParenthesis(ArrayList<String> stringArrayList){
        for (String strings : stringArrayList){
            if (strings.equals(")")){
                return stringArrayList.indexOf(")");
            }
        }
        return 0;
    }
    private int findLeftParenthesis(int indexFirstParenthesis, ArrayList<String> stringArrayList){
        for(int i = indexFirstParenthesis; i > 0; i--){
            if(stringArrayList.get(i).equals("(")){
                return i;
            }
        }
        return 0;
    }
    private ArrayList<String> extractParenthesis(int indexLeft, int indexRight, ArrayList<String> localArrayList){
        ArrayList<String> extractedList = new ArrayList<>();
        for (int i = indexLeft + 1; i < indexRight; i++){
            extractedList.add(localArrayList.get(i));
        }
        return extractedList;

    }

    private ArrayList<String> deleteParenthesisTerm(int indexLeft, int arrayLength, ArrayList<String> stringArrayList){
        for (int i = indexLeft + 1; i <= arrayLength + 1; i++){
            stringArrayList.remove(1);
        }
        return stringArrayList;
    }
    private int findPointOperators(ArrayList<String> arrayList) {
        for (int i = 0; i < arrayList.size(); i++) {
            if(arrayList.get(i).equals("*") || arrayList.get(i).equals("/")){
               return i;
            }
        }
        return 0;
    }
    private int findLineOperators(ArrayList<String> arrayList) {
        for (int i = 0; i < arrayList.size(); i++) {
            if(arrayList.get(i).equals("+") || arrayList.get(i).equals("-")){
                return i;
            }
        }
        return 0;
    }

}
