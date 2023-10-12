package org.example;

import Frontend.MainScreen;

public class Main {
    public static void main(String[] args) {

        try{
        MainScreen mainScreen = new MainScreen();
        mainScreen.setVisible(true);}catch (Exception e){
            System.out.println(e);
        }
    }

}