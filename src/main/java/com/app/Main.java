package com.app;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.swing.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Locale;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Для выхода напишите exit.");
        while (true) {

            System.out.println("Введите дату. Для выбора самой актуальной даты оставьте поле пустым.");
            String time = scanner.nextLine();
            if (time.equals("exit")) {
                break;
            }
            System.out.println("Введите код валюты");
            String money = scanner.nextLine().toUpperCase(Locale.ROOT);

            if (money.equals("EXIT")) {
                break;
            }

            if (money.length() != 3) {
                System.out.println("Недопустимый код валюты");
                continue;
            }
            Parser x = new Parser(time, money);
            System.out.println(x.getValue());
        }
    }
}
