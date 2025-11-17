package Entidades;

public abstract class Cuenta {
    private int id;
    private int idUsuario;
    private String Alias;
    private int CBU;
    private String tipo;
    private double saldo;

    public Cuenta() {
    }

    public Cuenta(int id, int idUsuario, String alias, int CBU, String tipo,Double saldo) {
        this.id = id;
        this.idUsuario = idUsuario;
        Alias = alias;
        this.CBU = CBU;
        this.tipo = tipo;
        this.saldo = saldo;
    }

    public int getId() {
        return id;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public String getAlias() {
        return Alias;
    }

    public int getCBU() {
        return CBU;
    }

    public String getTipo() {
        return tipo;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public void setAlias(String alias) {
        Alias = alias;
    }

    public void setCBU(int CBU) {
        this.CBU = CBU;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    public double getSaldo() {
        return saldo;
    }
    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

}

