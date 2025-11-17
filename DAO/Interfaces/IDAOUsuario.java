package DAO.Interfaces;

import DAO.Excepciones.DAOException;

import java.util.ArrayList;

public interface IDAOUsuario<T> {
    public void insertar(T elemento) throws DAOException;
    public void eliminar(int id) throws DAOException;
    public void modificar(T elemento) throws DAOException;
    public T buscar(String username,String password) throws DAOException;
    public T buscarID(int ID) throws DAOException;
    public ArrayList<T> buscarTodos() throws DAOException;
}
