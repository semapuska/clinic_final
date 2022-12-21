package com.company;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Пожалуйста, авторизуйтесь");
        chooseUsers();
    }

    // Выбор Аккаунта
    public static void chooseUsers() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Выберите вашу специальность :");
        System.out.println("(1) Директор");
        System.out.println("(2) Механик");
        System.out.println("(3) Покупатель");
        System.out.print("Ваш выбор: ");
        String choose = sc.nextLine();
        switch (choose.toLowerCase()) {
            case "director":
            case "1":
                System.out.println("Добро пожаловать Директор");
                System.out.println("Введите логин и пароль");
                Director.directorInputLgPw();
                break;
            case "worker":
            case "2":
                System.out.println("Добро пожаловать Механик");
                System.out.println("Введите логин и пароль");
                Repairman.repairmanInputLgPw();
                break;
            case "employee":
            case "3":
                System.out.println("Добро пожаловать Покупатель!");
                System.out.println("Введите логин и пароль");
                Employee.employeeInputLgPw();
                break;
            default:
                System.out.println("Извините, но мы не нашли такой тип аккаунта!");
                System.out.print("Желаете выйти[1] или повторить[0]? ");
                int ex = sc.nextInt();
                if (ex == 0){
                    chooseUsers();
                }
                else if(ex == 1){
                    System.out.println("Спасибо :)");
                    System.exit(0);
                }
        }
    }
}
