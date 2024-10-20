package com.example.tap2024scn.models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.Statement;

public class CancionDAO
{
    private int idcancion;
    private String nombre;
    private double duracion;
    private String genero;
    private String fechalanzada;

    public int getIdcancion() {
        return idcancion;
    }

    public void setIdcancion(int idcancion) {
        this.idcancion = idcancion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getDuracion() {
        return duracion;
    }

    public void setDuracion(double duracion) {
        this.duracion = duracion;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getFechalanzada() {
        return fechalanzada;
    }

    public void setFechalanzada(String fechalanzada) {
        this.fechalanzada = fechalanzada;
    }

    public int insert() {
        int rowCont;
        String query = "INSERT INTO cancion(nombre, duracion, genero, fechalanzada)" +
                " VALUES('"+this.nombre+"','"+this.duracion+"','"+this.genero+"','"+this.fechalanzada+"')";
        try {
            Statement stmt = Conexion.connection.createStatement();
            rowCont = stmt.executeUpdate(query);

        }catch (Exception e) {
            rowCont = 0;
            e.printStackTrace();

        }
        return rowCont;
    }

    public void update(){
        String query = "UPDATE cancion SET nombre = '"+this.nombre+"',duracion = '"+this.duracion+"',genero = '"+this.genero+"' WHERE idcancion ="+this.idcancion;
        try {
            Statement stmt = Conexion.connection.createStatement();
            stmt.executeUpdate(query);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void delete(){
        String query = "DELETE FROM cancion WHERE idcancion ="+this.idcancion;
        try {
            Statement stmt = Conexion.connection.createStatement();
            stmt.executeUpdate(query);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    public ObservableList<CancionDAO> selectAll(){
        ObservableList<CancionDAO> listaC = FXCollections.observableArrayList();
        String query = "SELECT * FROM cancion";
        CancionDAO objCancion;
        try {
            Statement stmt = Conexion.connection.createStatement();
            ResultSet res = stmt.executeQuery(query);
            while (res.next()) {
                objCancion = new CancionDAO();
                objCancion.idcancion = res.getInt(1);
                objCancion.nombre = res.getString(2);
                objCancion.duracion = res.getDouble(3);
                objCancion.genero = res.getString(4);
                objCancion.fechalanzada = res.getString(5);
                listaC.add(objCancion);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return listaC;
    }
}
