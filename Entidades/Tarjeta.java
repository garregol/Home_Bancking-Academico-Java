package Entidades;

public class Tarjeta {
    private int NumeroTarjeta;
    private String TipoTarjeta;
    private Double Saldo;
    private int idUsuario;

    public Tarjeta(int numeroTarjeta, String tipoTarjeta, Double saldo, int idUsuario) {
        this.NumeroTarjeta = numeroTarjeta;
        this.TipoTarjeta = tipoTarjeta;
        this.Saldo = saldo;
        this.idUsuario = idUsuario;
    }

    public Tarjeta() {
    }
    public int getNumeroTarjeta() {
        return NumeroTarjeta;
    }
    public void setNumeroTarjeta(int numeroTarjeta) {
        this.NumeroTarjeta = numeroTarjeta;
    }
    public String getTipoTarjeta() {
        return TipoTarjeta;
    }
    public void setTipoTarjeta(String tipoTarjeta) {
        this.TipoTarjeta = tipoTarjeta;
    }
    public Double getSaldo() {
        return Saldo;
    }
    public void setSaldo(Double saldo) {
        this.Saldo = saldo;
    }
    public int getIdUsuario() {
        return idUsuario;
    }
    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
}
