package DAO.Excepciones;

public class DAOException extends RuntimeException {
  public DAOException(String message) {
    super(message);
  }

  public DAOException() {
    super();
  }
}
