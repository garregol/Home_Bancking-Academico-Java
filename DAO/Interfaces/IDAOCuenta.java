package DAO.Interfaces;

import DAO.Excepciones.DAOException;

import java.util.ArrayList;

public interface IDAOCuenta<T> {
    public void insertar(T elemento) throws DAOException;
    public void eliminar(int id) throws DAOException;
    public void modificar(T elemento) throws DAOException;
    public T buscarID(int ID)throws DAOException;
    public T buscarIDUsuario(int IDusuario)throws DAOException;
    public T buscarAlias(String alias) throws DAOException;
    public ArrayList<T> buscarTodos() throws DAOException;
    public ArrayList<T> buscarTodosID(int idusuario) throws DAOException;
}
