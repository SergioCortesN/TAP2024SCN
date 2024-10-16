package com.example.tap2024scn.models;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {
    static private String DB = "musica";
    static private String user = "boss";
    static private String pwd = "123";
    static private String host = "localhost";
    static private String port = "3306";
    public static Connection connection;

    public static void CrearConexion() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://"+host+":"+port+"/"+DB,user,pwd);
            System.out.println("Conexion exitosa a la base de datos");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}