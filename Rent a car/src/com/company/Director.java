package com.company;

import java.sql.Connection;
import java.util.Scanner;

public class Director {
//    jdbc:postgresql://localhost:5432/rentcar

    // Авторизация директора
    public static void directorInputLgPw() {
        DbFunction db = new DbFunction();
        Connection connection = db.connect_to_db("rentcar", "anas", "anas");
        Scanner sc = new Scanner(System.in);

        System.out.print("Введите логин: ");
        String input_lg = sc.next();
        System.out.print("Введите пароль: ");
        String input_pw = sc.next();
        while (true) {
            if (db.login_director(connection, input_lg, input_pw)) {
                System.out.println("Директор, вы успешно вошли!");
                directorActions();
                break;
            } else {
                System.out.print("Желаете выйти[1] или повторить[0]? ");
                int ex = sc.nextInt();
                if (ex == 1){
                    System.out.println("Спасибо :)");
                    Main.chooseUsers();
                } else if(ex == 0){
                    directorInputLgPw();
                } else {
                    System.out.println("Извините, но мы не нашли такой тип аккаунта!");
                    directorInputLgPw();
                }
            }
        }
    }

//     Действия директора
    public static void directorActions() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Меню:");
        System.out.println("(1)Показать список автомобилей");
        System.out.println("(2)Показать количество всех арендованных автомобилей");
        System.out.println("(3)Показать количество всех сервисных автомобилей");
        System.out.println("(4)Показать самый дорогой автомобиль");
        System.out.println("(5)Показать самый дешевый автомобиль");
        System.out.println("(6)Добавить новый автомобиль");
        System.out.println("(7)Удалить автомобиль");
        System.out.println("(8)Показать удаленных автомобилей");
        System.out.println("(0)Выход");
        do {
            System.out.print("Ваш выбор: ");
            String chooseAction = sc.nextLine();
            switch (chooseAction) {
                case "1":
                    System.out.println("Действие 1");
                    action1();
                    directorActions();
                    break;
                case "2":
                    System.out.println("Действие 2");
                    action2();
                    directorActions();
                    break;
                case "3":
                    System.out.println("Действие 3");
                    action3();
                    directorActions();
                    break;
                case "4":
                    System.out.println("Действие 4");
                    action4();
                    directorActions();
                    break;
                case "5":
                    System.out.println("Действие 5");
                    action5();
                    directorActions();
                    break;
                case "6":
                    System.out.println("Действие 6");
                    action6();
                    directorActions();
                    break;
                case "7":
                    System.out.println("Действие 7");
                    action7();
                    directorActions();
                    break;
                case "8":
                    System.out.println("Действие 7");
                    action8();
                    directorActions();
                    break;
                default:
                    System.out.println("Такого действия нет в программе!");
                    directorActions();
                    break;
//                case "n":
//                    try {
//                        chooseUsers();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
                case "0":
                    System.out.println("Выход");
                    break;
            }
            break;
        } while (true);
    }

    public static void action1() {
        DbFunction db = new DbFunction();
        Connection connection = db.connect_to_db("rentcar", "anas", "anas");
        db.get_cars(connection);
    }

    public static void action2() {
        DbFunction db = new DbFunction();
        Connection connection = db.connect_to_db("rentcar", "anas", "anas");
        db.get_mount_rent_cars(connection);
    }

    public static void action3() {
        DbFunction db = new DbFunction();
        Connection connection = db.connect_to_db("rentcar", "anas", "anas");
        db.get_mount_service_cars(connection);
    }

    public static void action4() {
        DbFunction db = new DbFunction();
        Connection connection = db.connect_to_db("rentcar", "anas", "anas");
        db.get_the_most_expansive_car(connection);
    }

    public static void action5() {
        DbFunction db = new DbFunction();
        Connection connection = db.connect_to_db("rentcar", "anas", "anas");
        db.get_the_cheapest_car(connection);
    }

    public static void action6() {
        DbFunction db = new DbFunction();
        Connection connection = db.connect_to_db("rentcar", "anas", "anas");
        Scanner sc = new Scanner(System.in);
        System.out.print("Имя автомобиля: ");
        String name = sc.nextLine();
        System.out.print("Цена: ");
        int price = sc.nextInt();
        System.out.print("Марка: ");
        String brand = sc.next();
        System.out.print("Тип Кузова (Пикап, Внедорожник, ...): ");
        String body_type = sc.next();
        System.out.print("Автомать или Ручной (auto/manual): ");
        String auto_manual = sc.next();
        db.insert_cars(connection, name, price, body_type, auto_manual, brand);
    }

    public static void action7() {
        DbFunction db = new DbFunction();
        Connection connection = db.connect_to_db("rentcar", "anas", "anas");
        Scanner sc = new Scanner(System.in);
        System.out.println("Какую машину хотели бы удалить?");
        System.out.print("Номер авто: ");
        int number = sc.nextInt();
        Boolean car_id = db.check_cars_id(connection, number);
        if (car_id) {
            System.out.println("Passed!");
            db.delete_cars(connection, number);
        } else {
            System.out.println("Такой автомобиль не найден!");
            System.out.println("Хотите продолжить да(1)/нет(0): ");
            int yes = sc.nextInt();
            if (yes == 1) {
                action7();
            } else {
                directorActions();
            }
        }
    }

    public static void action8() {
        DbFunction db = new DbFunction();
        Connection connection = db.connect_to_db("rentcar", "anas", "anas");
        db.get_deleted_cars(connection);
    }
}
