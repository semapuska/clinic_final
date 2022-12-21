package com.company;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DbFunctionRepairman {
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

    public Boolean login_repairman(Connection connection, String user, String password) {
        Statement statement;
        ResultSet result = null;
        try {
            String query = String.format("SELECT * FROM person WHERE name = '%s' AND password = '%s' AND role = 'repairman'", user, password);
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

    public int login_repairman_id(Connection connection, String user, String password) {
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

    public void get_service_cars(Connection connection) {
        Statement statement;
        ResultSet result = null;
        ResultSet result2 = null;
        try {
            String query = String.format("SELECT * FROM service_cars_person WHERE repairman_id = 4");
            statement = connection.createStatement();
            result = statement.executeQuery(query);
            while (result.next()) {
                int cars_id = result.getInt("car_id");
                String query2 = String.format("SELECT * FROM cars WHERE id = %s", cars_id);
                statement = connection.createStatement();
                result2 = statement.executeQuery(query2);
                while (result2.next()) {
                    System.out.println("-----------------------");
                    System.out.println();
                    System.out.println(result2.getString("id") + " auto");
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
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void service_cars(Connection connection, int id, int person_id) {
        Statement statement;
        ResultSet result = null;
        try {
            String query = String.format("SELECT * FROM service_cars_person WHERE repairman_id = %s AND car_id = %s", 4, id);
            statement = connection.createStatement();
            result = statement.executeQuery(query);
            while (result.next()) {
                String query2 = String.format("UPDATE service_cars_person SET repairman_id = %s WHERE car_id = %s", person_id, id);
                statement = connection.createStatement();
                statement.executeUpdate(query2);

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

    public void get_service_cars(Connection connection, int id) {
        Statement statement;
        ResultSet result = null;
        ResultSet result2 = null;
        int count = 1;
        try {
            String query = String.format("SELECT * FROM service_cars_person WHERE repairman_id = %s", id);
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
}
