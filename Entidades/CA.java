package Entidades;

public class CA extends Cuenta {




    public CA(int id, int idUsuario, String alias, int CBU, double saldo, String tipo) {
        super(id, idUsuario, alias, CBU, "caja de ahorro", saldo);
    }

    public CA() {
        super();
    }


}