package Service;

import DAO.Clases.DAOUsuario;
import DAO.Excepciones.DAOException;
import Entidades.Usuario;

import java.util.ArrayList;

public class ServiceUsuario {
    private final DAOUsuario daoUsuario;

    public ServiceUsuario() {
        this.daoUsuario = new DAOUsuario();

    }

    public void insertar(Usuario usuario) throws ServiceException {
        try {
            daoUsuario.insertar(usuario);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public void eliminar(int id) throws ServiceException {
        try {
            daoUsuario.eliminar(id);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }

    }

    public void modificar(Usuario usuario) {
        try {
            daoUsuario.modificar(usuario);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public Usuario consultar(String username,String password) throws ServiceException {
        try {
            if (username == null || password == null) {
                throw new ServiceException("Username o password No pueden ser nulos");
            }
            if (username.isEmpty() || password.isEmpty()) {
                throw new ServiceException("Username o password No pueden estar vacios");
            }
            return daoUsuario.buscar(username, password);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }

    }

    public Usuario consultarID(int ID) throws ServiceException {
        try {
            if (ID <= 0) {
                throw new ServiceException("ID debe ser mayor que 0");
            }
            Usuario usuario = daoUsuario.buscarID(ID);
            if (usuario == null) {
                throw new ServiceException("Usuario no encontrado");
            }
            return usuario;
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }

    }

    public ArrayList<Usuario> consultarTodos() {
        try {
            return daoUsuario.buscarTodos();
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }
}
