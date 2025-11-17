package Entidades;

public class CA_dls extends Cuenta{


    public CA_dls(int id, int idUsuario, String alias, int CBU, double saldo, String tipo) {
        super(id, idUsuario, alias, CBU, "caja de ahorro en dolares", saldo);

    }

    public CA_dls() {
        super();
    }




}
