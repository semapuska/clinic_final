package com.company;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DbFunction {
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

    public Boolean login_director(Connection connection, String user, String password) {
        Statement statement;
        ResultSet result = null;
        try {
            String query = String.format("SELECT * FROM person WHERE name = '%s' AND password = '%s' AND role = 'director'", user, password);
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

//    Action 1
    public void get_cars(Connection connection) {
        Statement statement;
        ResultSet result = null;
        try {
            String query = String.format("SELECT * FROM cars");
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
    public void get_mount_rent_cars(Connection connection) {
        Statement statement;
        ResultSet result = null;
        Boolean flag = true;
        try {
            String query = String.format("SELECT * FROM cars WHERE rent = true");
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
                flag = false;
            }
            if (flag) {
                System.out.println();
                System.out.println("Арендованных автомобилей нет!");
                System.out.println();
                System.out.println("-----------------------");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

//    Action 3
    public void get_mount_service_cars(Connection connection) {
        Statement statement;
        ResultSet result = null;
        Boolean flag = true;
        try {
            String query = String.format("SELECT * FROM cars WHERE service = true");
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
                flag = false;
            }
            if (flag) {
                System.out.println();
                System.out.println("Сервисных автомобилей нет!");
                System.out.println();
                System.out.println("-----------------------");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

//    Action 4
    public void get_the_most_expansive_car(Connection connection) {
        Statement statement;
        ResultSet result = null;
        Boolean flag = true;
        try {
            String query = String.format("SELECT * FROM cars WHERE price = (SELECT MAX(price) FROM cars)");
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
                flag = false;
            }
            if (flag) {
                System.out.println();
                System.out.println("Aвтомобилей нет!");
                System.out.println();
                System.out.println("-----------------------");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

//    Action 5
    public void get_the_cheapest_car(Connection connection) {
        Statement statement;
        ResultSet result = null;
        Boolean flag = true;
        try {
            String query = String.format("SELECT * FROM cars WHERE price = (SELECT MIN(price) FROM cars)");
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
                flag = false;
            }
            if (flag) {
                System.out.println();
                System.out.println("Aвтомобилей нет!");
                System.out.println();
                System.out.println("-----------------------");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public Boolean check_cars_id(Connection connection, int id) {
        Statement statement;
        ResultSet result = null;
        Boolean flag = false;
        try {
            String query = String.format("SELECT * FROM cars WHERE id = %s", id);
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

    public void delete_cars(Connection connection, int id) {
        Statement statement;
        ResultSet result = null;
        try {
            String query1 = String.format("SELECT * FROM cars WHERE id = %s", id);
            statement = connection.createStatement();
            result = statement.executeQuery(query1);

            while (result.next()) {
                String name = result.getString("name");
                String brand = result.getString("brand");
                int price = result.getInt("price");
                String body_type = result.getString("body_type");
                String auto_manual = result.getString("auto_manual");
                Boolean rent = result.getBoolean("rent");
                Boolean service = result.getBoolean("service");
                String status = result.getString("status");

                String query = String.format("INSERT INTO deleted_cars (name, price, body_type, auto_manual, brand, rent, service, status)" +
                        " VALUES ('%s', %s, '%s', '%s', '%s', %s, %s, '%s')", name, price, body_type, auto_manual, brand, rent, service, status);
                statement = connection.createStatement();
                statement.executeUpdate(query);
            }

            String query = String.format("DELETE FROM cars WHERE id = %s", id);
            statement = connection.createStatement();
            statement.executeUpdate(query);
            System.out.println("-----------------------");
            System.out.println();
            System.out.println("Успешно удалено!");
            System.out.println();
            System.out.println("-----------------------");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void get_deleted_cars(Connection connection) {
        Statement statement;
        ResultSet result = null;
        Boolean flag = true;
        try {
            String query = String.format("SELECT * FROM deleted_cars");
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
                flag = false;
            }
            if (flag) {
                System.out.println();
                System.out.println("Удаленных автомобилей нет!");
                System.out.println();
                System.out.println("-----------------------");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void insert_cars(Connection connection, String name, int price, String body_type, String auto_manual, String brand) {
        Statement statement;
        try {
            String query = String.format("INSERT INTO cars (name, price, body_type, auto_manual, brand)" +
                    " VALUES ('%s', %s, '%s', '%s', '%s');", name, price, body_type, auto_manual, brand);
            statement = connection.createStatement();
            statement.executeUpdate(query);
            System.out.println("Успешно добавлено!");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}