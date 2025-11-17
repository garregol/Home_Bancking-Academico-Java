package DAO.Clases;

import DAO.Excepciones.DAOException;
import DAO.Interfaces.IDAOCuenta;
import Entidades.*;

import java.sql.*;
import java.util.ArrayList;

public class DAOCuenta implements IDAOCuenta<Cuenta> {
    private final String DB_JDBC_DRIVER = "org.h2.Driver";
    //private String DB_URL="jdbc:h2:~/USUARIO";
    private final String DB_URL = "jdbc:h2:file:C:\\Users\\thiga\\OneDrive - Universidad de Palermo\\BASES DE DATOS";
    private final String DB_USER = "sa";
    private final String DB_PASSWORD = "";


    @Override
    public void insertar(Cuenta elemento) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            String tipo = elemento.getTipo();
            double saldo = 0;
            if (elemento instanceof CA) {
                saldo = ((CA) elemento).getSaldo();
            } else if (elemento instanceof Cta_Cte) {
                saldo = ((Cta_Cte) elemento).getSaldo();
            } else if (elemento instanceof CA_dls) {
                saldo = ((CA_dls) elemento).getSaldo();
            }

            preparedStatement = connection.prepareStatement(
                    "INSERT INTO Cuentas(Usuario_ID,Tipo,Saldo,CBU,ALias) VALUES(?,?,?,?,?)"
            );
            preparedStatement.setInt(1, elemento.getIdUsuario());
            preparedStatement.setString(2, tipo);
            preparedStatement.setDouble(3, saldo);
            preparedStatement.setInt(4, elemento.getCBU());
            preparedStatement.setString(5, elemento.getAlias());

            int resultado = preparedStatement.executeUpdate();
            System.out.println("Se agrego " + resultado);
        } catch (ClassNotFoundException | SQLException e) {
            throw new DAOException("Error al insertar"+ e.getMessage());
        } finally {
            try {
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                throw new DAOException("Error al cerrar conexión: " + e.getMessage());
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
            preparedStatement = connection.prepareStatement("DELETE FROM CUENTAS WHERE ID=(?)");
            // "INSERT INTO Alumno VALUES(" +elemento.getId() + ",'" + elemento.getNombre() + "','"
            preparedStatement.setInt(1, id);

            int resultado = preparedStatement.executeUpdate();
            System.out.println("Se elimino " + resultado);

            if (resultado == 0) {
                throw new DAOException("No se encontró el cuenta con ID: " + id);
            }
        } catch (ClassNotFoundException | SQLException e) {
            throw new DAOException("No se pudo Eliminar");
        }
    }

    @Override
    public void modificar(Cuenta elemento) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            preparedStatement = connection.prepareStatement(
                    "UPDATE CUENTAS SET CBU = ?, ALIAS = ? WHERE ID = ?"
            );
            preparedStatement.setInt(1, elemento.getCBU());
            preparedStatement.setString(2, elemento.getAlias());
            preparedStatement.setInt(3, elemento.getId());

            int resultado = preparedStatement.executeUpdate();
            if (resultado == 0) {
                throw new DAOException("No se encontró la Cuenta con ID: " + elemento.getId());
            }
            System.out.println("Se modificó " + resultado);
        } catch (ClassNotFoundException | SQLException e) {
            throw new DAOException("No se pudo modificar");
        } finally {
            try {
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                throw new DAOException("Error al cerrar conexión: " + e.getMessage());
            }
        }
    }


    @Override
    public Cuenta buscarID(int id) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        Cuenta cuenta = null;

        try {
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            preparedStatement = connection.prepareStatement("SELECT * FROM CUENTAS WHERE ID = ?");
            preparedStatement.setInt(1, id);
            rs = preparedStatement.executeQuery();

            if (rs.next()) {
                String tipo = rs.getString("TIPO");
                switch (tipo) {
                    case "caja de ahorro":
                        cuenta = new CA();
                        ((CA) cuenta).setSaldo(rs.getDouble("SALDO"));
                        ((CA) cuenta).setTipo(rs.getString("TIPO"));
                        break;
                    case "cuenta corriente":
                        cuenta = new Cta_Cte();
                        ((Cta_Cte) cuenta).setSaldo(rs.getDouble("SALDO"));
                        ((Cta_Cte) cuenta).setTipo(rs.getString("TIPO"));
                        break;
                    case "caja de ahorro DLS":
                        cuenta = new CA_dls();
                        ((CA_dls) cuenta).setSaldo(rs.getDouble("SALDO"));
                        ((CA_dls) cuenta).setTipo(rs.getString("TIPO"));
                        break;
                }
                cuenta.setId(rs.getInt("ID"));
                cuenta.setIdUsuario(rs.getInt("USUARIO_ID"));
                cuenta.setTipo(tipo);
                cuenta.setCBU(rs.getInt("CBU"));
                cuenta.setAlias(rs.getString("ALIAS"));
            }
        } catch (ClassNotFoundException | SQLException e) {
            throw new DAOException("Error al buscar cuenta: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                throw new DAOException("Error al cerrar conexión: " + e.getMessage());
            }
        }
        return cuenta;
    }

    @Override
    public Cuenta buscarIDUsuario(int IDusuario) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        Cuenta cuenta = null;

        try {
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            preparedStatement = connection.prepareStatement("SELECT * FROM CUENTAS WHERE USUARIO_ID = ?");
            preparedStatement.setInt(1, IDusuario);
            rs = preparedStatement.executeQuery();

            if (rs.next()) {
                String tipo = rs.getString("TIPO");
                switch (tipo) {
                    case "caja de ahorro":
                        cuenta = new CA();
                        ((CA) cuenta).setSaldo(rs.getDouble("SALDO"));
                        ((CA) cuenta).setTipo(rs.getString("TIPO"));
                        break;
                    case "cuenta corriente":
                        cuenta = new Cta_Cte();
                        ((Cta_Cte) cuenta).setSaldo(rs.getDouble("SALDO"));
                        ((Cta_Cte) cuenta).setTipo(rs.getString("TIPO"));
                        break;
                    case "caja de ahorro DLS":
                        cuenta = new CA_dls();
                        ((CA_dls) cuenta).setSaldo(rs.getDouble("SALDO"));
                        ((CA_dls) cuenta).setTipo(rs.getString("TIPO"));
                        break;
                }
                cuenta.setId(rs.getInt("ID"));
                cuenta.setIdUsuario(rs.getInt("USUARIO_ID"));
                cuenta.setTipo(tipo);
                cuenta.setCBU(rs.getInt("CBU"));
                cuenta.setAlias(rs.getString("ALIAS"));
            }
        } catch (ClassNotFoundException | SQLException e) {
            throw new DAOException("Error al buscar cuenta por ID de usuario: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                throw new DAOException("Error al cerrar conexión: " + e.getMessage());
            }
        }
        return cuenta;

    }

    @Override
    public Cuenta buscarAlias(String alias) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        Cuenta cuenta = null;

        try {
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            preparedStatement = connection.prepareStatement("SELECT * FROM CUENTAS WHERE ALIAS = ?");
            preparedStatement.setString(1, alias);
            rs = preparedStatement.executeQuery();

            if (rs.next()) {
                String tipo = rs.getString("TIPO");
                switch (tipo) {
                    case "caja de ahorro":
                        cuenta = new CA();
                        ((CA) cuenta).setSaldo(rs.getDouble("SALDO"));
                        ((CA) cuenta).setTipo(rs.getString("TIPO"));
                        break;
                    case "cuenta corriente":
                        cuenta = new Cta_Cte();
                        ((Cta_Cte) cuenta).setSaldo(rs.getDouble("SALDO"));
                        ((Cta_Cte) cuenta).setTipo(rs.getString("TIPO"));
                        break;
                    case "caja de ahorro DLS":
                        cuenta = new CA_dls();
                        ((CA_dls) cuenta).setSaldo(rs.getDouble("SALDO"));
                        ((CA_dls) cuenta).setTipo(rs.getString("TIPO"));
                        break;
                }
                cuenta.setId(rs.getInt("ID"));
                cuenta.setIdUsuario(rs.getInt("USUARIO_ID"));
                cuenta.setTipo(tipo);
                cuenta.setCBU(rs.getInt("CBU"));
                cuenta.setAlias(rs.getString("ALIAS"));
            }
        } catch (ClassNotFoundException | SQLException e) {
            throw new DAOException("Error al buscar cuenta por alias: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                throw new DAOException("Error al cerrar conexión: " + e.getMessage());
            }
        }
        return cuenta;
    }


    public ArrayList<Cuenta> buscarTodos() throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        ArrayList<Cuenta> cuentas = new ArrayList<>();
        try {
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            preparedStatement = connection.prepareStatement("SELECT * FROM CUENTAS");
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String tipo = rs.getString("TIPO");
                Cuenta cuenta = null;
                switch (tipo) {
                    case "caja de ahorro":
                        cuenta = new CA();
                        ((CA) cuenta).setSaldo(rs.getDouble("SALDO"));
                        ((CA) cuenta).setTipo(rs.getString("TIPO"));
                        break;
                    case "cuenta corriente":
                        cuenta = new Cta_Cte();
                        ((Cta_Cte) cuenta).setSaldo(rs.getDouble("SALDO"));
                        ((Cta_Cte) cuenta).setTipo(rs.getString("TIPO"));
                        break;
                    case "caja de ahorro DLS":
                        cuenta = new CA_dls();
                        ((CA_dls) cuenta).setSaldo(rs.getDouble("SALDO"));
                        ((CA_dls) cuenta).setTipo(rs.getString("TIPO"));
                        break;
                }
                if (cuenta != null) {
                    cuenta.setId(rs.getInt("ID"));
                    cuenta.setIdUsuario(rs.getInt("USUARIO_ID"));
                    cuenta.setTipo(tipo);
                    cuenta.setCBU(rs.getInt("CBU"));
                    cuenta.setAlias(rs.getString("ALIAS"));
                    cuentas.add(cuenta);
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            throw new DAOException("Ocurrió un error en la base de datos: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                throw new DAOException("Error al cerrar conexión: " + e.getMessage());
            }
        }
        return cuentas;
    }


    public ArrayList<Cuenta> buscarTodosID(int idUsuario) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        ArrayList<Cuenta> cuentas = new ArrayList<>();
        try {
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            preparedStatement = connection.prepareStatement("SELECT * FROM CUENTAS WHERE USUARIO_ID = ?");
            preparedStatement.setInt(1, idUsuario);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String tipo = rs.getString("TIPO");
                Cuenta cuenta = null;
                switch (tipo) {
                    case "caja de ahorro":
                        cuenta = new CA();
                        ((CA) cuenta).setSaldo(rs.getDouble("SALDO"));
                        ((CA) cuenta).setTipo(rs.getString("TIPO"));
                        break;
                    case "cuenta corriente":
                        cuenta = new Cta_Cte();
                        ((Cta_Cte) cuenta).setSaldo(rs.getDouble("SALDO"));
                        ((Cta_Cte) cuenta).setTipo(rs.getString("TIPO"));
                        break;
                    case "caja de ahorro DLS":
                        cuenta = new CA_dls();
                        ((CA_dls) cuenta).setSaldo(rs.getDouble("SALDO"));
                        ((CA_dls) cuenta).setTipo(rs.getString("TIPO"));
                        break;
                }
                if (cuenta != null) {
                    cuenta.setId(rs.getInt("ID"));
                    cuenta.setIdUsuario(rs.getInt("USUARIO_ID"));
                    cuenta.setTipo(tipo);
                    cuenta.setCBU(rs.getInt("CBU"));
                    cuenta.setAlias(rs.getString("ALIAS"));
                    cuentas.add(cuenta);
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            throw new DAOException("Ocurrió un error en la base de datos: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                throw new DAOException("Error al cerrar conexión: " + e.getMessage());
            }
        }
        return cuentas;
    }

}
