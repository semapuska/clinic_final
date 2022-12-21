package com.company;

import java.sql.Connection;
import java.util.Scanner;

public class Employee {
    //    jdbc:postgresql://localhost:5432/rentcar
    public static int user_id;

    // Авторизация директора
    public static void employeeInputLgPw() {
        DbFunctionEmployee db = new DbFunctionEmployee();
        Connection connection = db.connect_to_db("rentcar", "anas", "anas");
        Scanner sc = new Scanner(System.in);

        do {
            System.out.println("Хотите зарегистрироваться(1) или войти(0)?");
            System.out.print("Ваш выбор: ");
            String chooseAction = sc.nextLine();
            switch (chooseAction) {
                case "1":
                    System.out.print("Введите логин: ");
                    String input_lg = sc.next();
                    System.out.print("Введите пароль: ");
                    String input_pw = sc.next();
                    if (db.register_check(connection, input_lg)) {
                        db.register_employee(connection, input_lg, input_pw);
                        user_id = db.login_employee_id(connection, input_lg, input_pw);
                        employeeActions();
                    } else {
                        System.out.println("Такой логин существует!");
                        employeeInputLgPw();
                    }
                    break;
                case "0":
                    System.out.print("Введите логин: ");
                    String input_lg0 = sc.next();
                    System.out.print("Введите пароль: ");
                    String input_pw0 = sc.next();

                    while (true) {
                        if (db.login_employee(connection, input_lg0, input_pw0)) {
                            System.out.println("Покупатель, вы успешно вошли!");
                            user_id = db.login_employee_id(connection, input_lg0, input_pw0);
                            employeeActions();
                            break;
                        } else {
                            System.out.print("Желаете выйти[1] или повторить[0]? ");
                            int ex = sc.nextInt();
                            if (ex == 1){
                                System.out.println("Спасибо :)");
                                Main.chooseUsers();
                            } else if(ex == 0){
                                employeeInputLgPw();
                            } else {
                                System.out.println("Извините, но мы не нашли такой тип аккаунта!");
                                employeeInputLgPw();
                            }
                        }
                    }
                    break;
                default:
                    System.out.println("Такого действия нет в программе!");
                    employeeInputLgPw();
                    break;
            }
            break;
        } while (true);

    }

    public static void employeeActions() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Меню:");
        System.out.println("(1)Показать список автомобилей");
        System.out.println("(2)Показать по названию брэнда автомобиля");
        System.out.println("(3)Купить автомобиль");
        System.out.println("(4)Показать купленные автомобили");
        System.out.println("(5)Арендовать машину");
        System.out.println("(6)Показать арендованных машин");
        System.out.println("(7)Добавить автомобиль в сервис");
        System.out.println("(0)Выход");
        do {
            System.out.print("Ваш выбор: ");
            String chooseAction = sc.nextLine();
            switch (chooseAction) {
                case "1":
                    System.out.println("Действие 1");
                    action1();
                    employeeActions();
                    break;
                case "2":
                    System.out.println("Действие 2");
                    action2();
                    employeeActions();
                    break;
                case "3":
                    System.out.println("Действие 3");
                    action3();
                    employeeActions();
                    break;
                case "4":
                    System.out.println("Действие 4");
                    action4();
                    employeeActions();
                    break;
                case "5":
                    System.out.println("Действие 5");
                    action5();
                    employeeActions();
                    break;
                case "6":
                    System.out.println("Действие 6");
                    action6();
                    employeeActions();
                    break;
                case "7":
                    System.out.println("Действие 7");
                    action7();
                    employeeActions();
                    break;
//                case "8":
//                    System.out.println("Действие 7");
//                    action8();
//                    directorActions();
//                    break;
                default:
                    System.out.println("Такого действия нет в программе!");
                    employeeActions();
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
        DbFunctionEmployee db = new DbFunctionEmployee();
        Connection connection = db.connect_to_db("rentcar", "anas", "anas");
        db.get_cars(connection);
    }

    public static void action2() {
        DbFunctionEmployee db = new DbFunctionEmployee();
        Connection connection = db.connect_to_db("rentcar", "anas", "anas");
        Scanner sc = new Scanner(System.in);
        System.out.print("Имя бренда: ");
        String brand_name = sc.nextLine();
        if (db.brand_check(connection, brand_name)) {
            db.get_brands(connection, brand_name);
        } else {
            System.out.println("Такой бренд не найден!");
            employeeActions();
        }
    }

    public static void action3() {
        DbFunctionEmployee db = new DbFunctionEmployee();
        Connection connection = db.connect_to_db("rentcar", "anas", "anas");
        Scanner sc = new Scanner(System.in);
        System.out.println("Какую машину хотели бы купить?");
        System.out.print("Номер авто: ");
        int number = sc.nextInt();
        db.solt_cars(connection, number, user_id);
    }

    public static void action4() {
        DbFunctionEmployee db = new DbFunctionEmployee();
        Connection connection = db.connect_to_db("rentcar", "anas", "anas");
        db.show_bought_cars(connection, user_id);
    }

    public static void action5() {
        DbFunctionEmployee db = new DbFunctionEmployee();
        Connection connection = db.connect_to_db("rentcar", "anas", "anas");
        Scanner sc = new Scanner(System.in);
        System.out.println("Какую машину хотели бы арендовать?");
        System.out.print("Номер авто: ");
        int number = sc.nextInt();
        db.rent_cars(connection, number, user_id);
    }

    public static void action6() {
        DbFunctionEmployee db = new DbFunctionEmployee();
        Connection connection = db.connect_to_db("rentcar", "anas", "anas");
        db.show_rent_cars(connection, user_id);
    }

    public static void action7() {
        DbFunctionEmployee db = new DbFunctionEmployee();
        Connection connection = db.connect_to_db("rentcar", "anas", "anas");
        Scanner sc = new Scanner(System.in);
        System.out.println("Какую машину хотели бы добавить в сервис?");
        System.out.print("Проблема автомобиля: ");
        String status = sc.nextLine();
        System.out.print("Номер авто: ");
        int number = sc.nextInt();
        db.service_cars(connection, number, user_id, status);
    }
}
