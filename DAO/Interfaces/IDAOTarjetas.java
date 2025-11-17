package DAO.Interfaces;

import DAO.Excepciones.DAOException;

import java.util.ArrayList;

public interface IDAOTarjetas<T>{
    public void insertar(T elemento) throws DAOException;
    public void eliminar(int id) throws DAOException;
    public void modificar(T elemento) throws DAOException;
    public T buscarTarjeta(int numeroTarjeta) throws DAOException;
    public ArrayList<T> buscarTodos() throws DAOException;
    public ArrayList<T> buscarTodosPorIDUsuario(int idusuario) throws DAOException;

}
