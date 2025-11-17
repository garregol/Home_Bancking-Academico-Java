package Entidades;

public class Cta_Cte extends Cuenta{




    public Cta_Cte(int id, int idUsuario, String alias, int CBU, double saldo, String tipo) {
        super(id, idUsuario, alias, CBU, "cuenta corriente",saldo);

    }

    public Cta_Cte() {
        super();
    }




}
