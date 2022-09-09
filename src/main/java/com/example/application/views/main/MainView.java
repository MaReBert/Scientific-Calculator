package com.example.application.views.main;

import com.example.application.processor.StringProcessor;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import org.springframework.beans.factory.annotation.Autowired;

@PageTitle("Main")
@Route(value = "main")
@RouteAlias(value = "")
public class MainView extends VerticalLayout {

    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;
    private Button button5;
    private Button button6;
    private Button button7;
    private Button button8;
    private Button button9;
    private Button button0;
    private Button buttonPlus;
    private Button buttonMinus;
    private Button buttonMulti;
    private Button buttonDevide;
    private Button buttonEqual;
    private Button buttonDelete;
    private TextArea textArea;
    private String totalInputString;
    private Button buttonParenthesisOpen;
    private Button buttonParenthesisClose;
    @Autowired
    private StringProcessor stringProcessor;
    private String result;
    private boolean resultIsActive;

    public MainView() {
        configureButtons();
        configureTextArea();
        add(getContent());

    }



    private void configureButtons() {
        button1 = new Button("1", click -> clickButton("1"));
        button2 = new Button("2", click -> clickButton("2"));
        button3 = new Button("3", click -> clickButton("3"));
        button4 = new Button("4", click -> clickButton("4"));
        button5 = new Button("5", click -> clickButton("5"));
        button6 = new Button("6", click -> clickButton("6"));
        button7 = new Button("7", click -> clickButton("7"));
        button8 = new Button("8", click -> clickButton("8"));
        button9 = new Button("9", click -> clickButton("9"));
        button0 = new Button("0", click -> clickButton("0"));
        buttonPlus = new Button("+", click -> clickButton("+"));
        buttonMinus = new Button("-", click -> clickButton("-"));
        buttonMulti = new Button("*", click -> clickButton("*"));
        buttonDevide = new Button("/", click -> clickButton("/"));
        buttonParenthesisOpen = new Button("(", click -> clickButton("("));
        buttonParenthesisClose = new Button(")", click -> clickButton(")"));

        buttonEqual = new Button("=", click -> getResult());

        buttonDelete = new Button("DEL", click-> deleteInput());

    }
    private void configureTextArea() {
        textArea = new TextArea();
        textArea.setWidth("25em");
        textArea.addValueChangeListener(e -> updateTextArea());
    }

    private VerticalLayout getContent(){
        VerticalLayout content = new VerticalLayout(textArea, getButtons());
        content.setSizeFull();
        content.setHorizontalComponentAlignment(Alignment.CENTER);

        content.addClassName("content");
        return content;
    }
    private VerticalLayout getButtons(){
        HorizontalLayout buttonRow1 = new HorizontalLayout(button1, button2, button3, buttonPlus, buttonParenthesisOpen);
        HorizontalLayout buttonRow2 = new HorizontalLayout(button4, button5, button6, buttonMinus, buttonParenthesisClose);
        HorizontalLayout buttonRow3 = new HorizontalLayout(button7, button8, button9, buttonMulti);
        HorizontalLayout buttonRow4 = new HorizontalLayout(buttonDelete, button0, buttonEqual, buttonDevide);

        return new VerticalLayout(buttonRow1, buttonRow2, buttonRow3, buttonRow4);
    }
    private void clickButton(String inputValue){
        if(resultIsActive){
            textArea.setValue("");
            resultIsActive = false;
        }
        if(totalInputString == null){
            totalInputString = inputValue;
        }else {
            totalInputString = totalInputString + inputValue;
        }
        textArea.setValue(totalInputString);
    }
    private void deleteInput(){
        totalInputString = null;
        textArea.setValue("");
    }
    private void updateTextArea(){
        textArea.setValue(totalInputString);
    }
    private void getResult(){
        result = stringProcessor.processInputString(totalInputString);
        resultIsActive = true;
        totalInputString = null;
        textArea.setValue(result);
    }

}
