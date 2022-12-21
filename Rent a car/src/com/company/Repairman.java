package com.company;

import java.sql.Connection;
import java.util.Scanner;

public class Repairman {
    //    jdbc:postgresql://localhost:5432/rentcar
    public static int user_id;

    // Авторизация директора
    public static void repairmanInputLgPw() {
        DbFunctionRepairman db = new DbFunctionRepairman();
        Connection connection = db.connect_to_db("rentcar", "anas", "anas");
        Scanner sc = new Scanner(System.in);

        System.out.print("Введите логин: ");
        String input_lg = sc.next();
        System.out.print("Введите пароль: ");
        String input_pw = sc.next();
        while (true) {
            if (db.login_repairman(connection, input_lg, input_pw)) {
                System.out.println("Механик, вы успешно вошли!");
                user_id = db.login_repairman_id(connection, input_lg, input_pw);
                repairmanActions();
                break;
            } else {
                System.out.print("Желаете выйти[1] или повторить[0]? ");
                int ex = sc.nextInt();
                if (ex == 1){
                    System.out.println("Спасибо :)");
                    Main.chooseUsers();
                } else if(ex == 0){
                    repairmanInputLgPw();
                } else {
                    System.out.println("Извините, но мы не нашли такой тип аккаунта!");
                    repairmanInputLgPw();
                }
            }
        }

    }

    public static void repairmanActions() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Меню:");
        System.out.println("(1)Показать список автомобилей");
        System.out.println("(2)Показать по названию брэнда автомобиля");
        System.out.println("(3)Показать автомобили в сервисе");
        System.out.println("(4)Добавить автомобиль");
        System.out.println("(5)Показать добавленных автомобилей");
        System.out.println("(0)Выход");
        do {
            System.out.print("Ваш выбор: ");
            String chooseAction = sc.nextLine();
            switch (chooseAction) {
                case "1":
                    System.out.println("Действие 1");
                    action1();
                    repairmanActions();
                    break;
                case "2":
                    System.out.println("Действие 2");
                    action2();
                    repairmanActions();
                    break;
                case "3":
                    System.out.println("Действие 3");
                    action3();
                    repairmanActions();
                    break;
                case "4":
                    System.out.println("Действие 4");
                    action4();
                    repairmanActions();
                    break;
                case "5":
                    System.out.println("Действие 5");
                    action5();
                    repairmanActions();
                    break;
//                case "6":
//                    System.out.println("Действие 6");
//                    action6();
//                    repairmanActions();
//                    break;
//                case "7":
//                    System.out.println("Действие 7");
//                    action7();
//                    repairmanActions();
//                    break;
                default:
                    System.out.println("Такого действия нет в программе!");
                    repairmanActions();
                    break;
//                case "n":
//                    try {
//                        chooseUsers();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
                case "0":
                    System.out.println("Выход");
                    Main.chooseUsers();
                    break;
            }
            break;
        } while (true);
    }

    public static void action1() {
        DbFunctionRepairman db = new DbFunctionRepairman();
        Connection connection = db.connect_to_db("rentcar", "anas", "anas");
        db.get_cars(connection);
    }

    public static void action2() {
        DbFunctionRepairman db = new DbFunctionRepairman();
        Connection connection = db.connect_to_db("rentcar", "anas", "anas");
        Scanner sc = new Scanner(System.in);
        System.out.print("Имя бренда: ");
        String brand_name = sc.nextLine();
        if (db.brand_check(connection, brand_name)) {
            db.get_brands(connection, brand_name);
        } else {
            System.out.println("Такой бренд не найден!");
            repairmanActions();
        }
    }

    public static void action3() {
        DbFunctionRepairman db = new DbFunctionRepairman();
        Connection connection = db.connect_to_db("rentcar", "anas", "anas");
        db.get_service_cars(connection);
    }

    public static void action4() {
        DbFunctionRepairman db = new DbFunctionRepairman();
        Connection connection = db.connect_to_db("rentcar", "anas", "anas");
        Scanner sc = new Scanner(System.in);
        System.out.println("Какую машину хотели бы взять из сервиса?");
        System.out.print("Номер авто: ");
        int number = sc.nextInt();
        db.service_cars(connection, number, user_id);
    }

    public static void action5() {
        DbFunctionRepairman db = new DbFunctionRepairman();
        Connection connection = db.connect_to_db("rentcar", "anas", "anas");
        db.get_service_cars(connection, user_id);
    }
}

