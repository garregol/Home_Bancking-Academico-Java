package Entidades;

import java.sql.Date;
import java.sql.Timestamp;

public class Movimientos {

    private int id;
    private int idCuenta;
    private Date fecha;
    private String tipoMovimiento; // "debito" o "credito"
    private double monto;
    private String descripcion;

    public Movimientos(int id, int idCuenta,String fecha, String tipoMovimiento, double monto, String descripcion) {
        this.id = id;
        this.idCuenta = idCuenta;
        this.tipoMovimiento = tipoMovimiento;
        this.monto = monto;
        this.descripcion = descripcion;

    }

    public Movimientos() {
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdCuenta() {
        return idCuenta;
    }

    public void setIdCuenta(int idCuenta) {
        this.idCuenta = idCuenta;
    }

    public Date getFecha() {
        return fecha;
    }
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getTipoMovimiento() {
        return tipoMovimiento;
    }

    public void setTipoMovimiento(String tipoMovimiento) {
        this.tipoMovimiento = tipoMovimiento;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
