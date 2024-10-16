package com.example.tap2024scn.models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.Statement;

public class ClienteDAO {
    private int id_cliente;
    private String nomcte;
    private String numtel;
    private String emailcte;

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public String getNomcte() {
        return nomcte;
    }

    public void setNomcte(String nomcte) {
        this.nomcte = nomcte;
    }

    public String getNumtel() {
        return numtel;
    }

    public void setNumtel(String numtel) {
        this.numtel = numtel;
    }

    public String getEmailcte() {
        return emailcte;

    }

    public void setEmailcte(String emailcte) {
        this.emailcte = emailcte;
    }

    public int insert() {
        int rowCont;
        String query = "INSERT INTO cliente(nomcte, numtel, emailcte)" +
    " VALUES('"+this.nomcte+"','"+this.numtel+"','"+this.emailcte+"')";
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
        String query = "UPDATE cliente SET nomcte = '"+this.nomcte+"',numtel = '"+this.numtel+"',emailcte = '"+this.emailcte+"' WHERE id_cliente ="+this.id_cliente;
        try {
            Statement stmt = Conexion.connection.createStatement();
            stmt.executeUpdate(query);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void delete(){
        String query = "DELETE FROM cliente WHERE id_cliente ="+this.id_cliente;
        try {
            Statement stmt = Conexion.connection.createStatement();
            stmt.executeUpdate(query);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    public ObservableList<ClienteDAO> selectAll(){
        ObservableList<ClienteDAO> listaC = FXCollections.observableArrayList();
        String query = "SELECT * FROM cliente";
        ClienteDAO objCte;
        try {
            Statement stmt = Conexion.connection.createStatement();
            ResultSet res = stmt.executeQuery(query);
            while (res.next()) {
                objCte = new ClienteDAO();
                objCte.id_cliente = res.getInt(1);
                objCte.nomcte = res.getString(2);
                objCte.numtel = res.getString(3);
                objCte.emailcte = res.getString(4);
                listaC.add(objCte);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return listaC;
    }
}
