package models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
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

    public void insert() {
        String query = "INSERT INTO cliente(nomcte, numtel, emailcte)" +
    " VALUES('"+this.nomcte+"','"+this.numtel+"','"+this.emailcte+"')";
        try {
           Statement stmt = Conexion.connection.createStatement();
           stmt.executeUpdate(query);
        }catch (Exception e) {
            e.printStackTrace();
        }
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
                objCte.id_cliente = res.getInt("id_cliente");
                objCte.nomcte = res.getString("nomcte");
                objCte.numtel = res.getString("numtel");
                objCte.emailcte = res.getString("emailcte");
                listaC.add(objCte);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return listaC;
    }
}
