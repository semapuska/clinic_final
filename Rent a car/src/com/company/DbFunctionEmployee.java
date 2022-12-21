package com.company;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DbFunctionEmployee {
    public Connection connect_to_db(String dbname, String user, String pass) {
        Connection connection = null;
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/"+dbname, user, pass);
        } catch (Exception e) {
            System.out.println(e);
        }
        return connection;
    }

    public Boolean login_employee(Connection connection, String user, String password) {
        Statement statement;
        ResultSet result = null;
        try {
            String query = String.format("SELECT * FROM person WHERE name = '%s' AND password = '%s' AND role = 'employee'", user, password);
            statement = connection.createStatement();
            result = statement.executeQuery(query);
            while (result.next()) {
                return true;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    public int login_employee_id(Connection connection, String user, String password) {
        Statement statement;
        ResultSet result = null;
        try {
            String query = String.format("SELECT * FROM person WHERE name = '%s' AND password = '%s'", user, password);
            statement = connection.createStatement();
            result = statement.executeQuery(query);
            while (result.next()) {
                return result.getInt("id");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return 0;
    }

    public Boolean register_check(Connection connection, String user) {
        Statement statement;
        ResultSet result = null;
        Boolean flag = true;
        try {
            String query = String.format("SELECT * FROM person WHERE name ILIKE '%s'", user);
            statement = connection.createStatement();
            result = statement.executeQuery(query);
            while (result.next()) {
                flag = false;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return flag;
    }

    public void register_employee(Connection connection, String user, String password) {
        Statement statement;
        ResultSet result = null;
        try {
            String query = String.format("INSERT INTO person (name, password, role)" +
                    " VALUES ('%s', '%s', 'employee');", user, password);
            statement = connection.createStatement();
            statement.executeUpdate(query);
            System.out.println("Успешно зарегистрированы!");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    //    Action 1
    public void get_cars(Connection connection) {
        Statement statement;
        ResultSet result = null;
        try {
            String query = String.format("SELECT * FROM cars WHERE rent = false AND service = false");
            statement = connection.createStatement();
            result = statement.executeQuery(query);
            System.out.println("-----------------------");
            while (result.next()) {
                System.out.println();
                System.out.println(result.getString("id") + " auto");
                System.out.println("Name: " + result.getString("name"));
                System.out.println("Brand: " + result.getString("brand"));
                System.out.println("Price: " + result.getString("price"));
                System.out.println("Body Type: " + result.getString("body_type"));
                System.out.println("Auto/Manual: " + result.getString("auto_manual"));
                System.out.println("In rent: " + result.getString("rent"));
                System.out.println("In service: " + result.getString("service"));
                System.out.println("Status: " + result.getString("status") + ".");
                System.out.println();
                System.out.println("-----------------------");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

//    Action 2
    public Boolean brand_check(Connection connection, String brand_name) {
        Statement statement;
        ResultSet result = null;
        Boolean flag = false;
        try {
            String query = String.format("SELECT * FROM cars WHERE brand ILIKE '%s'", brand_name);
            statement = connection.createStatement();
            result = statement.executeQuery(query);
            while (result.next()) {
                flag = true;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return flag;
    }

    public void get_brands(Connection connection, String brand_name) {
        Statement statement;
        ResultSet result = null;
        try {
            String query = String.format("SELECT * FROM cars WHERE brand ILIKE '%s'", brand_name);
            statement = connection.createStatement();
            result = statement.executeQuery(query);
            System.out.println("-----------------------");
            while (result.next()) {
                System.out.println();
                System.out.println(result.getString("id") + " auto");
                System.out.println("Name: " + result.getString("name"));
                System.out.println("Brand: " + result.getString("brand"));
                System.out.println("Price: " + result.getString("price"));
                System.out.println("Body Type: " + result.getString("body_type"));
                System.out.println("Auto/Manual: " + result.getString("auto_manual"));
                System.out.println("In rent: " + result.getString("rent"));
                System.out.println("In service: " + result.getString("service"));
                System.out.println("Status: " + result.getString("status") + ".");
                System.out.println();
                System.out.println("-----------------------");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void  solt_cars(Connection connection, int id, int person_id) {
        Statement statement;
        ResultSet result = null;
        ResultSet result2 = null;
        try {
            String solt_name = null, solt_brand = null;
            int solt_price = 0;
            String query1 = String.format("SELECT * FROM cars WHERE id = %s", id);
            statement = connection.createStatement();
            result = statement.executeQuery(query1);

            while (result.next()) {
                String name = result.getString("name");
                solt_name = name;
                String brand = result.getString("brand");
                solt_brand = brand;
                int price = result.getInt("price");
                solt_price = price;
                String body_type = result.getString("body_type");
                String auto_manual = result.getString("auto_manual");
                Boolean rent = result.getBoolean("rent");
                Boolean service = result.getBoolean("service");
                String status = result.getString("status");

                String query = String.format("INSERT INTO solt_cars (name, price, body_type, auto_manual, brand, rent, service, status)" +
                        " VALUES ('%s', %s, '%s', '%s', '%s', %s, %s, '%s')", name, price, body_type, auto_manual, brand, rent, service, status);
                statement = connection.createStatement();
                statement.executeUpdate(query);
            }

            String query = String.format("DELETE FROM cars WHERE id = %s", id);
            statement = connection.createStatement();
            statement.executeUpdate(query);

            String query2 = String.format("SELECT * FROM solt_cars WHERE name = '%s' AND brand = '%s' AND price = %s",
                    solt_name, solt_brand, solt_price);
            statement = connection.createStatement();
            result2 = statement.executeQuery(query2);
            while (result2.next()) {
                int id1 = result2.getInt("id");

                String query3 = String.format("INSERT INTO solt_cars_person (car_id, person_id)" +
                        " VALUES (%s, %s)", id1, person_id);
                statement = connection.createStatement();
                statement.executeUpdate(query3);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void show_bought_cars(Connection connection, int id) {
        Statement statement;
        ResultSet result = null;
        ResultSet result2 = null;
        int count = 1;
        try {
            String query = String.format("SELECT * FROM solt_cars_person WHERE person_id = %s", id);
            statement = connection.createStatement();
            result = statement.executeQuery(query);
            while (result.next()) {
                int cars_id = result.getInt("car_id");
                String query2 = String.format("SELECT * FROM solt_cars WHERE id = %s", cars_id);
                statement = connection.createStatement();
                result2 = statement.executeQuery(query2);
                while (result2.next()) {
                    System.out.println("-----------------------");
                    System.out.println();
                    System.out.println(count + " auto");
                    System.out.println("Name: " + result2.getString("name"));
                    System.out.println("Brand: " + result2.getString("brand"));
                    System.out.println("Price: " + result2.getString("price"));
                    System.out.println("Body Type: " + result2.getString("body_type"));
                    System.out.println("Auto/Manual: " + result2.getString("auto_manual"));
                    System.out.println("In rent: " + result2.getString("rent"));
                    System.out.println("In service: " + result2.getString("service"));
                    System.out.println("Status: " + result2.getString("status") + ".");
                    System.out.println();
                    System.out.println("-----------------------");
                    count++;
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void rent_cars(Connection connection, int id, int person_id) {
        Statement statement;
        ResultSet result = null;
        ResultSet result2 = null;
        try {
            String query = String.format("SELECT * FROM cars WHERE id = %s", id);
            statement = connection.createStatement();
            result = statement.executeQuery(query);
            System.out.println("-----------------------");
            while (result.next()) {
                String name = result.getString("name");
                int price = result.getInt("price");
                System.out.println();
                System.out.println("Цена за " + name + " за " + price + "$ \n" +
                        "Сумма налога составила: 1% или " + (int)price/100 + "$\n" +
                        "Сумма комиссий продавцу за продажу составила 5% или " + (price*5)/100 + "$");
                System.out.println("Итого окончательная цена: " + (((int) price/100) + ((price*5)/100)) + "$");
                System.out.println();
                System.out.println("-----------------------");
                String query2 = String.format("UPDATE cars SET rent = %s WHERE id = %s", true, id);
                statement = connection.createStatement();
                statement.executeUpdate(query2);
            }

            String query2 = String.format("SELECT * FROM cars WHERE id = %s", id);
            statement = connection.createStatement();
            result2 = statement.executeQuery(query2);
            while (result2.next()) {
                int id1 = result2.getInt("id");

                String query3 = String.format("INSERT INTO rent_cars_person (car_id, person_id)" +
                        " VALUES (%s, %s)", id1, person_id);
                statement = connection.createStatement();
                statement.executeUpdate(query3);

                System.out.println("-----------------------");
                System.out.println();
                System.out.println("Успешно добавлено!");
                System.out.println();
                System.out.println("-----------------------");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void show_rent_cars(Connection connection, int id) {
        Statement statement;
        ResultSet result = null;
        ResultSet result2 = null;
        int count = 1;
        try {
            String query = String.format("SELECT * FROM rent_cars_person WHERE person_id = %s", id);
            statement = connection.createStatement();
            result = statement.executeQuery(query);
            while (result.next()) {
                int cars_id = result.getInt("car_id");
                String query2 = String.format("SELECT * FROM cars WHERE id = %s AND rent = true", cars_id);
                statement = connection.createStatement();
                result2 = statement.executeQuery(query2);
                while (result2.next()) {
                    System.out.println("-----------------------");
                    System.out.println();
                    System.out.println(count + " auto");
                    System.out.println("Name: " + result2.getString("name"));
                    System.out.println("Brand: " + result2.getString("brand"));
                    System.out.println("Price: " + result2.getString("price"));
                    System.out.println("Body Type: " + result2.getString("body_type"));
                    System.out.println("Auto/Manual: " + result2.getString("auto_manual"));
                    System.out.println("In rent: " + result2.getString("rent"));
                    System.out.println("In service: " + result2.getString("service"));
                    System.out.println("Status: " + result2.getString("status") + ".");
                    System.out.println();
                    System.out.println("-----------------------");
                    count++;
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void service_cars(Connection connection, int id, int person_id, String status) {
        Statement statement;
        ResultSet result = null;
        ResultSet result2 = null;
        try {
            String query = String.format("SELECT * FROM cars WHERE id = %s", id);
            statement = connection.createStatement();
            result = statement.executeQuery(query);
            while (result.next()) {
                String query2 = String.format("UPDATE cars SET service = %s WHERE id = %s", true, id);
                statement = connection.createStatement();
                statement.executeUpdate(query2);
            }

            String query2 = String.format("SELECT * FROM cars WHERE id = %s", id);
            statement = connection.createStatement();
            result2 = statement.executeQuery(query2);
            while (result2.next()) {
                int id1 = result2.getInt("id");
                String name = result2.getString("name");
                String brand = result2.getString("brand");
                int price = result2.getInt("price");
                String body_type = result2.getString("body_type");
                String auto_manual = result2.getString("auto_manual");
                Boolean rent = result2.getBoolean("rent");
                Boolean service = result2.getBoolean("service");

                String query3 = String.format("INSERT INTO service_cars (name, price, body_type, auto_manual, brand, rent, service, status)" +
                        " VALUES ('%s', %s, '%s', '%s', '%s', %s, %s, '%s')", name, price, body_type, auto_manual, brand, rent, service, status);
                statement = connection.createStatement();
                statement.executeUpdate(query3);

                String query4 = String.format("INSERT INTO service_cars_person (car_id, person_id, repairman_id)" +
                        " VALUES (%s, %s)", id1, person_id, 4);
                statement = connection.createStatement();
                statement.executeUpdate(query4);

                System.out.println("-----------------------");
                System.out.println();
                System.out.println("Успешно добавлено!");
                System.out.println();
                System.out.println("-----------------------");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
