package DAO.Clases;

import DAO.Excepciones.DAOException;
import DAO.Interfaces.IDAOUsuario;
import Entidades.Usuario;

import java.sql.*;
import java.util.ArrayList;

public class DAOUsuario implements IDAOUsuario<Usuario> {
    private String DB_JDBC_DRIVER="org.h2.Driver";
    //private String DB_URL="jdbc:h2:~/USUARIO";
    private String DB_URL="jdbc:h2:file:C:\\Users\\thiga\\OneDrive - Universidad de Palermo\\BASES DE DATOS";
    private String DB_USER="sa";
    private String DB_PASSWORD="";


    @Override
    public void insertar(Usuario elemento) throws DAOException {

        Connection connection=null;
        PreparedStatement preparedStatement=null;
        try {
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            preparedStatement = connection.prepareStatement("INSERT INTO USUARIOS(nombre,apellido,username,password,es_admin) VALUES(?,?,?,?,?)");
            // "INSERT INTO Alumno VALUES(" +elemento.getId() + ",'" + elemento.getNombre() + "','"
            //preparedStatement.setInt(1, elemento.getId());
            preparedStatement.setString(1, elemento.getNombre());
            preparedStatement.setString(2, elemento.getApellido());
            preparedStatement.setString(3, elemento.getUsername());
            preparedStatement.setString(4, elemento.getPassword());
            preparedStatement.setBoolean(5, elemento.isEsAdmin());
            int resultado = preparedStatement.executeUpdate();
            System.out.println("Se agrego " + resultado);
        }
        catch (ClassNotFoundException | SQLException e)
        {
            throw new DAOException("No se pudo insertar");
        }

    }

    @Override
    public void eliminar(int id) throws DAOException {

        Connection connection=null;
        PreparedStatement preparedStatement=null;
        try {
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            preparedStatement = connection.prepareStatement("DELETE USUARIOS WHERE ID=(?)");
            // "INSERT INTO Alumno VALUES(" +elemento.getId() + ",'" + elemento.getNombre() + "','"
            preparedStatement.setInt(1, id);

            int resultado = preparedStatement.executeUpdate();
            System.out.println("Se elimino " + resultado);

            if(resultado == 0) {
                throw new DAOException("No se encontró el usuario con ID: " + id);
            }
        }
        catch (ClassNotFoundException | SQLException e)
        {
            throw new DAOException("No se pudo Eliminar");
        }
    }

    @Override
    public void modificar(Usuario elemento) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            preparedStatement = connection.prepareStatement(
                    "UPDATE USUARIOS SET NOMBRE = ?, APELLIDO = ?, USERNAME = ?, PASSWORD = ?, ES_ADMIN = ? WHERE ID = ?"
            );
            preparedStatement.setString(1, elemento.getNombre());
            preparedStatement.setString(2, elemento.getApellido());
            preparedStatement.setString(3, elemento.getUsername());
            preparedStatement.setString(4, elemento.getPassword());
            preparedStatement.setBoolean(5, elemento.isEsAdmin());
            preparedStatement.setInt(6, elemento.getId());

            int resultado = preparedStatement.executeUpdate();
            if (resultado == 0) {
                throw new DAOException("No se encontró el usuario con ID: " + elemento.getId());
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

    public Usuario buscar(String username, String password) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        Usuario usuario = null;

        try {
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            preparedStatement = connection.prepareStatement(
                    "SELECT * FROM USUARIOS WHERE USERNAME = ? AND PASSWORD = ?"
            );
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            rs = preparedStatement.executeQuery();

            if (rs.next()) {
                usuario = new Usuario();
                usuario.setId(rs.getInt("ID"));
                usuario.setApellido(rs.getString("APELLIDO"));
                usuario.setNombre(rs.getString("NOMBRE"));
                usuario.setUsername(rs.getString("USERNAME"));
                usuario.setPassword(rs.getString("PASSWORD"));
                usuario.setEsAdmin(rs.getBoolean("ES_ADMIN"));
            }

        } catch (ClassNotFoundException | SQLException e) {
            throw new DAOException("Error al buscar usuario: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                throw new DAOException("Error al cerrar conexión: " + e.getMessage());
            }
        }

        return usuario;
    }

    public Usuario buscarID(int ID) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        Usuario usuario = null;

        try {
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            preparedStatement = connection.prepareStatement(
                    "SELECT * FROM USUARIOS WHERE ID = ?"
            );
            preparedStatement.setInt(1,ID);
            rs = preparedStatement.executeQuery();

            if (rs.next()) {
                usuario = new Usuario();
                usuario.setId(rs.getInt("ID"));
                usuario.setApellido(rs.getString("APELLIDO"));
                usuario.setNombre(rs.getString("NOMBRE"));
                usuario.setUsername(rs.getString("USERNAME"));
                usuario.setPassword(rs.getString("PASSWORD"));
                usuario.setEsAdmin(rs.getBoolean("ES_ADMIN"));
            }

        } catch (ClassNotFoundException | SQLException e) {
            throw new DAOException("Error al buscar usuario: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                throw new DAOException("Error al cerrar conexión: " + e.getMessage());
            }
        }

        return usuario;
    }

    @Override
    public ArrayList<Usuario> buscarTodos() throws DAOException {
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        Usuario usuario = null;
        ArrayList<Usuario> usuarios =new ArrayList<>();
        try{
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            preparedStatement = connection.prepareStatement("SELECT * FROM USUARIOS");
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next())
            {
                usuario =new Usuario();
                usuario.setId(rs.getInt("ID"));
                usuario.setApellido(rs.getString("APELLIDO"));
                usuario.setNombre(rs.getString("NOMBRE"));
                usuario.setUsername(rs.getString("USERNAME"));
                usuario.setPassword(rs.getString("PASSWORD"));
                usuario.setEsAdmin(rs.getBoolean("ES_ADMIN"));
                usuarios.add(usuario);
            }
        }
        catch (ClassNotFoundException | SQLException e)
        {
            throw  new DAOException("Ocurrio un error en la base de datos");
        }
        return usuarios;
    }
}


