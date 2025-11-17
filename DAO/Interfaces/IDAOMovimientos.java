package DAO.Interfaces;

import DAO.Excepciones.DAOException;

import java.util.ArrayList;

public interface IDAOMovimientos <T>{
    public void insertar(T elemento) throws DAOException;
    public T buscarID(int ID)throws DAOException;
    public ArrayList<T> buscarTodos() throws DAOException;
    public void transferir(int idOrigen, int idDestino, double monto) throws DAOException;
    ArrayList<T> buscarPorIdCuenta(int idCuenta) throws DAOException;
    ArrayList<T> buscarPorIdsCuentas(ArrayList<Integer> idsCuentas) throws DAOException;
    ArrayList<T> buscarPorIdUsuario(int idUsuario) throws DAOException;
}
