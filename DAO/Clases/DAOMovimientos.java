package DAO.Clases;

import DAO.Excepciones.DAOException;
import DAO.Interfaces.IDAOMovimientos;
import Entidades.Movimientos;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.sql.Timestamp;

public class DAOMovimientos implements IDAOMovimientos<Movimientos> {
    private final String DB_JDBC_DRIVER = "org.h2.Driver";
    //private String DB_URL="jdbc:h2:~/USUARIO";
    private final String DB_URL = "jdbc:h2:file:C:\\Users\\thiga\\OneDrive - Universidad de Palermo\\BASES DE DATOS";
    private final String DB_USER = "sa";
    private final String DB_PASSWORD = "";

    @Override
    public void insertar(Movimientos elemento) throws DAOException {
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        try {
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            preparedStatement= connection.prepareStatement("INSERT INTO MOVIMIENTOS(CUENTA_ID,FECHA,DESCRIPCION,TIPO,MONTO) VALUES(?,?,?,?,?)");
            //preparedStatement.setInt(1,elemento.getId());
            preparedStatement.setInt(1, elemento.getIdCuenta());
            Timestamp fechaSQL = new Timestamp(elemento.getFecha().getTime());
            preparedStatement.setTimestamp(2, fechaSQL);
            preparedStatement.setString(3, elemento.getDescripcion());
            preparedStatement.setString(4, elemento.getTipoMovimiento());
            preparedStatement.setDouble(5, elemento.getMonto());
            int resultado=preparedStatement.executeUpdate();
            System.out.println("Se agrego " + resultado);
        }
        catch (ClassNotFoundException | SQLException e)
        {
            throw  new DAOException("Error al insertar" + e.getMessage()+ e);
        }

    }

    @Override
    public Movimientos buscarID(int ID) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        Movimientos movimientos = null;

        try {
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            preparedStatement = connection.prepareStatement(
                    "SELECT * FROM MOVIMIENTOS WHERE ID = ?"
            );
            preparedStatement.setInt(1,ID);
            rs = preparedStatement.executeQuery();

            if (rs.next()) {
                movimientos =new Movimientos();
                movimientos.setId(rs.getInt("ID"));
                movimientos.setIdCuenta(rs.getInt("CUENTA_ID"));
                movimientos.setFecha(rs.getDate("FECHA"));
                movimientos.setTipoMovimiento(rs.getString("TIPO"));
                movimientos.setMonto(rs.getDouble("MONTO"));
                movimientos.setDescripcion(rs.getString("DESCRIPCION"));

            }

        } catch (ClassNotFoundException | SQLException e) {
            throw new DAOException("Error al buscar Movimiento: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                throw new DAOException("Error al cerrar conexión: " + e.getMessage());
            }
        }

        return movimientos;
    }

    @Override
    public ArrayList<Movimientos> buscarTodos() throws DAOException {
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        Movimientos movimiento = null;
        ArrayList<Movimientos> movimientos =new ArrayList<>();
        try{
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            preparedStatement = connection.prepareStatement("SELECT * FROM MOVIMIENTOS");
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next())
            {

                movimiento =new Movimientos();
                movimiento.setId(rs.getInt("ID"));
                movimiento.setIdCuenta(rs.getInt("CUENTA_ID"));
                movimiento.setFecha(rs.getDate("FECHA"));
                movimiento.setTipoMovimiento(rs.getString("TIPO"));
                movimiento.setMonto(rs.getDouble("MONTO"));
                movimiento.setDescripcion(rs.getString("DESCRIPCION"));
                movimientos.add(movimiento);
            }
        }
        catch (ClassNotFoundException | SQLException e)
        {
            throw  new DAOException("Ocurrio un error en la base de datos");
        }
        return movimientos;
    }

    @Override
    public void transferir(int idOrigen, int idDestino, double monto) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            connection.setAutoCommit(false); // Iniciar transacción

            // Actualizar cuenta origen
            preparedStatement = connection.prepareStatement(
                    "UPDATE CUENTAS SET SALDO = SALDO - ? WHERE ID = ?"
            );
            preparedStatement.setDouble(1, monto);
            preparedStatement.setInt(2, idOrigen);
            preparedStatement.executeUpdate();

            // Actualizar cuenta destino
            preparedStatement = connection.prepareStatement(
                    "UPDATE CUENTAS SET SALDO = SALDO + ? WHERE ID = ?"
            );
            preparedStatement.setDouble(1, monto);
            preparedStatement.setInt(2, idDestino);
            preparedStatement.executeUpdate();

            // Registrar movimiento en la cuenta origen
            Movimientos movimientoOrigen = new Movimientos();
            movimientoOrigen.setIdCuenta(idOrigen);
            movimientoOrigen.setTipoMovimiento("ENVIADO");
            movimientoOrigen.setFecha(java.sql.Date.valueOf(LocalDateTime.now().toLocalDate()));
            movimientoOrigen.setMonto(monto);
            movimientoOrigen.setDescripcion("Transferencia a cuenta ID: " + idDestino);
            insertar(movimientoOrigen);

            // Registrar movimiento en la cuenta destino
            Movimientos movimientoDestino = new Movimientos();
            movimientoDestino.setIdCuenta(idDestino);
            movimientoDestino.setTipoMovimiento("RECIBIDO");
            LocalDateTime fechaHoraActual = LocalDateTime.now();
            movimientoDestino.setFecha(java.sql.Date.valueOf(fechaHoraActual.toLocalDate()));
            movimientoDestino.setMonto(monto);
            movimientoDestino.setDescripcion("Transferencia desde cuenta ID: " + idOrigen);
            insertar(movimientoDestino);

            connection.commit(); // Confirmar transacción
        } catch (ClassNotFoundException | SQLException e) {
            if (connection != null) {
                try {
                    connection.rollback(); // Revertir cambios en caso de error
                } catch (SQLException rollbackEx) {
                    throw new DAOException("Error al revertir transacción: " + rollbackEx.getMessage());
                }
            }
        }

    }

    public ArrayList<Movimientos> buscarPorIdCuenta(int idCuenta) throws DAOException {
        ArrayList<Movimientos> movimientos = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        try {
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            preparedStatement = connection.prepareStatement("SELECT * FROM MOVIMIENTOS WHERE CUENTA_ID = ?");
            preparedStatement.setInt(1, idCuenta);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Movimientos movimiento = new Movimientos();
                movimiento.setId(rs.getInt("ID"));
                movimiento.setIdCuenta(rs.getInt("CUENTA_ID"));
                movimiento.setFecha(rs.getDate("FECHA"));
                movimiento.setTipoMovimiento(rs.getString("TIPO"));
                movimiento.setMonto(rs.getDouble("MONTO"));
                movimiento.setDescripcion(rs.getString("DESCRIPCION"));
                movimientos.add(movimiento);
            }
        } catch (ClassNotFoundException | SQLException e) {
            throw new DAOException("Error al buscar movimientos por cuenta: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                throw new DAOException("Error al cerrar conexión: " + e.getMessage());
            }
        }
        return movimientos;
    }

    // Buscar movimientos por varios IDs de cuentas
    public ArrayList<Movimientos> buscarPorIdsCuentas(ArrayList<Integer> idsCuentas) throws DAOException {
        ArrayList<Movimientos> movimientos = new ArrayList<>();
        if (idsCuentas == null || idsCuentas.isEmpty()) return movimientos;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        try {
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            StringBuilder query = new StringBuilder("SELECT * FROM MOVIMIENTOS WHERE CUENTA_ID IN (");
            for (int i = 0; i < idsCuentas.size(); i++) {
                query.append("?");
                if (i < idsCuentas.size() - 1) query.append(",");
            }
            query.append(")");
            preparedStatement = connection.prepareStatement(query.toString());
            for (int i = 0; i < idsCuentas.size(); i++) {
                preparedStatement.setInt(i + 1, idsCuentas.get(i));
            }
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Movimientos movimiento = new Movimientos();
                movimiento.setId(rs.getInt("ID"));
                movimiento.setIdCuenta(rs.getInt("CUENTA_ID"));
                movimiento.setFecha(rs.getDate("FECHA"));
                movimiento.setTipoMovimiento(rs.getString("TIPO"));
                movimiento.setMonto(rs.getDouble("MONTO"));
                movimiento.setDescripcion(rs.getString("DESCRIPCION"));
                movimientos.add(movimiento);
            }
        } catch (ClassNotFoundException | SQLException e) {
            throw new DAOException("Error al buscar movimientos por cuentas: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                throw new DAOException("Error al cerrar conexión: " + e.getMessage());
            }
        }
        return movimientos;
    }

    // Buscar movimientos por ID de usuario (requiere que CUENTAS tenga ID_USUARIO)
    public ArrayList<Movimientos> buscarPorIdUsuario(int idUsuario) throws DAOException {
        ArrayList<Movimientos> movimientos = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        try {
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            String query = "SELECT m.* FROM MOVIMIENTOS m " +
                    "JOIN CUENTAS c ON m.CUENTA_ID = c.ID " +
                    "WHERE c.ID_USUARIO = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, idUsuario);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Movimientos movimiento = new Movimientos();
                movimiento.setId(rs.getInt("ID"));
                movimiento.setIdCuenta(rs.getInt("CUENTA_ID"));
                movimiento.setFecha(rs.getDate("FECHA"));
                movimiento.setTipoMovimiento(rs.getString("TIPO"));
                movimiento.setMonto(rs.getDouble("MONTO"));
                movimiento.setDescripcion(rs.getString("DESCRIPCION"));
                movimientos.add(movimiento);
            }
        } catch (ClassNotFoundException | SQLException e) {
            throw new DAOException("Error al buscar movimientos por usuario: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                throw new DAOException("Error al cerrar conexión: " + e.getMessage());
            }
        }
        return movimientos;
    }
}
