package DAO.Clases;

import DAO.Excepciones.DAOException;
import DAO.Interfaces.IDAOTarjetas;
import Entidades.Tarjeta;

import java.sql.*;
import java.util.ArrayList;

public class DAOTarjetas implements IDAOTarjetas<Tarjeta> {
    private final String DB_JDBC_DRIVER = "org.h2.Driver";
    //private String DB_URL="jdbc:h2:~/USUARIO";
    private final String DB_URL = "jdbc:h2:file:C:\\Users\\thiga\\OneDrive - Universidad de Palermo\\BASES DE DATOS";
    private final String DB_USER = "sa";
    private final String DB_PASSWORD = "";

    @Override
    public void insertar(Tarjeta elemento) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            preparedStatement = connection.prepareStatement("INSERT INTO TARJETA(NUMERO_TARJETA,SALDO,TIPO,ID_USUARIO) VALUES(?,?,?,?)");
            preparedStatement.setInt(1, elemento.getNumeroTarjeta());
            preparedStatement.setDouble(2, elemento.getSaldo());
            preparedStatement.setString(3, elemento.getTipoTarjeta());
            preparedStatement.setInt(4, elemento.getIdUsuario());

            int resultado = preparedStatement.executeUpdate();
            System.out.println("Se agrego " + resultado);
        } catch (ClassNotFoundException | SQLException e) {
            throw new DAOException("Error al insertar" + e);
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    @Override
    public void eliminar(int id) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            preparedStatement = connection.prepareStatement("DELETE FROM TARJETA WHERE NUMERO_TARJETA=(?)");
            preparedStatement.setInt(1, id);
            int resultado = preparedStatement.executeUpdate();
            System.out.println("Se elimino " + resultado);
        } catch (ClassNotFoundException | SQLException e) {
            throw new DAOException("Error al eliminar");
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    @Override
    public void modificar(Tarjeta elemento) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            preparedStatement = connection.prepareStatement("UPDATE TARJETA SET TIPO=?, ID_USUARIO=?, SALDO=? WHERE NUMERO_TARJETA=?");
            preparedStatement.setString(1, elemento.getTipoTarjeta());
            preparedStatement.setInt(2, elemento.getIdUsuario());
            preparedStatement.setDouble(3, elemento.getSaldo());
            preparedStatement.setInt(4, elemento.getNumeroTarjeta());
            int resultado = preparedStatement.executeUpdate();
            System.out.println("Se modifico " + resultado);
        } catch (ClassNotFoundException | SQLException e) {
            throw new DAOException("Error al modificar");
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }


    @Override
    public Tarjeta buscarTarjeta(int numeroTarjeta) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Tarjeta tarjeta = null;
        try {
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            preparedStatement = connection.prepareStatement("SELECT * FROM TARJETA WHERE NUMERO_TARJETA=?");
            preparedStatement.setInt(1, numeroTarjeta);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                tarjeta = new Tarjeta();
                tarjeta.setNumeroTarjeta(rs.getInt("NUMERO_TARJETA"));
                tarjeta.setTipoTarjeta(rs.getString("TIPO"));
                tarjeta.setIdUsuario(rs.getInt("ID_USUARIO"));
                tarjeta.setSaldo(rs.getDouble("SALDO"));
            }
        } catch (ClassNotFoundException | SQLException e) {
            throw new DAOException("Ocurrio un error en la base de datos");
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return tarjeta;
    }

    @Override
    public ArrayList<Tarjeta> buscarTodos() throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Tarjeta tarjeta = null;
        ArrayList<Tarjeta> tarjetas = new ArrayList<>();
        try {
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            preparedStatement = connection.prepareStatement("SELECT * FROM TARJETA");
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                tarjeta = new Tarjeta();
                tarjeta.setNumeroTarjeta(rs.getInt("NUMERO_TARJETA"));
                tarjeta.setTipoTarjeta(rs.getString("TIPO"));
                tarjeta.setIdUsuario(rs.getInt("ID_USUARIO"));
                tarjeta.setSaldo(rs.getDouble("SALDO"));
                tarjetas.add(tarjeta);
            }
        } catch (ClassNotFoundException | SQLException e) {
            throw new DAOException("Ocurrio un error en la base de datos"+ e.getMessage());
        }
        return tarjetas;
    }

    @Override
    public ArrayList<Tarjeta> buscarTodosPorIDUsuario(int Idusuario) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Tarjeta tarjeta = null;
        ArrayList<Tarjeta> tarjetas = new ArrayList<>();
        try {
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            preparedStatement = connection.prepareStatement("SELECT * FROM TARJETA WHERE ID_USUARIO=?");
            preparedStatement.setInt(1, Idusuario);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                tarjeta = new Tarjeta();
                tarjeta.setNumeroTarjeta(rs.getInt("NUMERO_TARJETA"));
                tarjeta.setTipoTarjeta(rs.getString("TIPO"));
                tarjeta.setIdUsuario(rs.getInt("ID_USUARIO"));
                tarjeta.setSaldo(rs.getDouble("SALDO"));
                tarjetas.add(tarjeta);
            }
        } catch (ClassNotFoundException | SQLException e) {
            throw new DAOException("Ocurrio un error en la base de datos"+ e.getMessage());
        }
        return tarjetas;
    }
}


