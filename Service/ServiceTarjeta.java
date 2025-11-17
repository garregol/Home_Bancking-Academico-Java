package Service;

import DAO.Clases.DAOTarjetas;
import DAO.Excepciones.DAOException;
import Entidades.Tarjeta;
import java.util.ArrayList;

public class ServiceTarjeta {
    private final DAOTarjetas daoTarjetas;

    public ServiceTarjeta() {
        this.daoTarjetas = new DAOTarjetas();
    }
    public void insertar(Tarjeta tarjeta) throws ServiceException {
        try {
            daoTarjetas.insertar(tarjeta);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }
    public void eliminar(int id) throws ServiceException {
        try {
            daoTarjetas.eliminar(id);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public void modificar(Tarjeta tarjeta) {
        try {
            if (tarjeta==null){
                throw new ServiceException("Tarjeta no puede ser nula");
            }
            daoTarjetas.modificar(tarjeta);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public Tarjeta consultarPorNumero(int numero) throws ServiceException {
        try {
            return daoTarjetas.buscarTarjeta(numero);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public ArrayList<Tarjeta> consultarTodas() throws ServiceException {
        try {
            return daoTarjetas.buscarTodos();
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public ArrayList<Tarjeta> consultarTodasPorIDusuario(int idusuario) throws ServiceException {
        try {
            return daoTarjetas.buscarTodosPorIDUsuario(idusuario);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }


}
