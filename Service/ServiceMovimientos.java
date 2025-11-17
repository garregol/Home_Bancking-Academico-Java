package Service;
import DAO.Clases.DAOMovimientos;
import DAO.Excepciones.DAOException;
import Entidades.Movimientos;
import java.util.ArrayList;
import java.util.Date;

public class ServiceMovimientos {
    private final DAOMovimientos daoMovimientos;

    public ServiceMovimientos() {
        this.daoMovimientos = new DAOMovimientos();
    }
    public void insertar(Movimientos movimiento) throws ServiceException {
        try {
            daoMovimientos.insertar(movimiento);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }
    public Movimientos consultar(int id) throws ServiceException {
        try {
            if (id <= 0) {
                throw new ServiceException("ID debe ser mayor que 0");
            }
            return daoMovimientos.buscarID(id);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }
    public ArrayList<Movimientos> consultarTodos() throws ServiceException {
        try {
            return daoMovimientos.buscarTodos();
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public boolean Transferencia(int idCuentaOrigen, int idCuentaDestino, double monto, String tipoOrigen, String tipoDestino,Double SaldoOrigen) throws ServiceException {
        try {
            if (idCuentaOrigen <= 0 || idCuentaDestino <= 0) {
                throw new ServiceException("ID de cuenta debe ser mayor que 0");
            }

            if (monto <= 0) {
                throw new ServiceException("Monto debe ser mayor que 0");
            }

            if (tipoOrigen == null || tipoDestino == null) {
                throw new ServiceException("Tipo de cuenta no puede ser nulo");
            }
            if (tipoOrigen.isEmpty() || tipoDestino.isEmpty()) {
                throw new ServiceException("Tipo de cuenta no puede estar vacío");
            }

            if (!tipoOrigen.equals(tipoDestino)) {
                throw new ServiceException("No se puede transferir entre cuentas de distinto tipo");
            }

            if (SaldoOrigen < monto) {
                throw new ServiceException("Saldo insuficiente en la cuenta de origen");
            }

            daoMovimientos.transferir(idCuentaOrigen, idCuentaDestino, monto);
            return true;

        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }

    }
    public ArrayList<Movimientos> consultarPorIdUsuario(int idUsuario) throws ServiceException {
        try {
            if (idUsuario <= 0) {
                throw new ServiceException("ID de usuario debe ser mayor que 0");
            }
            return daoMovimientos.buscarPorIdUsuario(idUsuario);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public ArrayList<Movimientos> consultarPorIdCuenta(int idCuenta) throws ServiceException {
        try {
            if (idCuenta <= 0) {
                throw new ServiceException("ID de cuenta debe ser mayor que 0");
            }
            return daoMovimientos.buscarPorIdCuenta(idCuenta);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }


}
