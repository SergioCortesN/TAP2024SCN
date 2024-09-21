package models;

public class ClienteDAO {
    private int id_cliente;
    private String nomcte;
    private String telcte;
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

    public String getTelcte() {
        return telcte;
    }

    public void setTelcte(String telcte) {
        this.telcte = telcte;
    }

    public String getEmailcte() {
        return emailcte;
    }

    public void setEmailcte(String emailcte) {
        this.emailcte = emailcte;
    }
}
