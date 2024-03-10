package org.example;

import java.io.*;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Main {
    public static void main(String[] args) {

        String data = "Попов Алексей Николаевич 00.00.1990 89555555554 М";
        String data2 = "Борунов Алексей Николаевич 00.00.1990 89555555554 М";
        String data3 = "123123 Алексей Николаевич 00.00.1990 89555555554 М";
        String data4 = "Борунов 123123123 Николаевич 00.00.1990 89555555554 М";
        String data5 = "Борунов Алексей 123123123 00.00.1990 89555555554 М";
        String data6 = "Борунов Алексей Николаевич йц.00.1990 89555555554 М";
        String data7 = "Борунов Алексей Николаевич 00.йц.1990 89555555554 М";
        String data8 = "Борунов Алексей Николаевич 00.00.йцу 89555555554 М";
        String data9 = "Борунов Алексей Николаевич 00.00.1990 йцу М";
        String data10 = "Борунов Алексей Николаевич 00.00.1990 89555555554 Щ";

        validationCheck();

    }
    public static void validationCheck() {
        String[] informData = new String[]{"Фамилия", "Имя", "Отчество", "Дата рождения день", "Дата рождения месяц",
                "Дата рождения год", "Номер телефона", "Пол"};
        System.out.println("Введите данные в формате \nФамилия Имя Отчество дата рождения " +
                "00.00.0000 номер телефона 11 символов пол М/Ж");
        Scanner scanner = new Scanner(System.in);
        String userData = scanner.nextLine();
        System.out.println("Вы ввели");
        System.out.println(userData);
        String[] paterCheck = new String[]{"\\D+", "\\D+", "\\D+", "\\d{2}", "\\d{2}", "\\d{4}", "\\d{11}",
                "(м|М|Ж|ж)"};
        String[] splitUserData = userData.split(" |\\.");
        if (splitUserData.length < 8) {
            throw new RuntimeException("Вы указали не все данные");
        }
        for (int i = 0; i < splitUserData.length; i++) {
            Pattern patternFor = Pattern.compile(paterCheck[i]);
            Matcher matcherFor = patternFor.matcher(splitUserData[i]);
            boolean boolResFor = matcherFor.matches();
            if (!boolResFor == true) {
                throw new RuntimeException("Вы указали некорктные данные -> " + informData[i]);
            }
        }
        try (FileWriter fileWriter = new FileWriter(splitUserData[0]+".txt",true) ){
            fileWriter.write("\r\n" + userData);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}