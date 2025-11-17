package Service;

import DAO.Clases.DAOCuenta;
import DAO.Excepciones.DAOException;
import Entidades.CA;
import Entidades.CA_dls;
import Entidades.Cta_Cte;
import Entidades.Cuenta;

import java.util.ArrayList;

public class ServiceCuenta {
    private final DAOCuenta daoCuenta;

    public ServiceCuenta() {
        this.daoCuenta = new DAOCuenta();

    }

    public void insertar(Cuenta cuenta) throws ServiceException {
        try {
            daoCuenta.insertar(cuenta);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ServiceException("Error al crear la cuenta: " + ex.getMessage());
        }
    }

    public void eliminar(int id) throws ServiceException {
        try {
            Cuenta cuenta= daoCuenta.buscarID(id);
            if (cuenta == null) {
                throw new ServiceException("Cuenta no encontrada");
            }
            if(cuenta.getSaldo()!=0){
                if(cuenta.getSaldo()>0){
                    throw new ServiceException("No se puede eliminar una cuenta con saldo positivo");
                }
                if(cuenta.getSaldo()<0){
                    throw new ServiceException("No se puede eliminar una cuenta con saldo negativo");
                }
            }
            else{
                daoCuenta.eliminar(id);
            }
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }

    }


    public void modificar(Cuenta cuenta) {
        try {
            daoCuenta.modificar(cuenta);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public Cuenta consultar(String username,String password) throws ServiceException {
        return null;
    }

    public Cuenta consultarID(int ID) throws ServiceException {
        try {
            Cuenta cuenta = daoCuenta.buscarID(ID);
            if (cuenta == null) {
                throw new ServiceException("Cuenta no encontrada");
            }
            String tipo = cuenta.getTipo();
            if (tipo.equals("caja de ahorro")) {
                return new CA(cuenta.getId(), cuenta.getIdUsuario(), cuenta.getAlias(), cuenta.getCBU(), ((CA) cuenta).getSaldo(), tipo);
            } else if (tipo.equals("cuenta corriente")) {
                return new Cta_Cte(cuenta.getId(), cuenta.getIdUsuario(), cuenta.getAlias(), cuenta.getCBU(), ((Cta_Cte) cuenta).getSaldo(), tipo);
            } else if (tipo.equals("caja de ahorro DLS")) {
                return new CA_dls(cuenta.getId(), cuenta.getIdUsuario(), cuenta.getAlias(), cuenta.getCBU(), ((CA_dls) cuenta).getSaldo(), tipo);
            } else {
                throw new ServiceException("Tipo de cuenta desconocido");
            }
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public Cuenta consultarIDUsuario(int IDusuario) throws ServiceException {
        try {
            Cuenta cuenta = daoCuenta.buscarIDUsuario(IDusuario);
            if (cuenta == null) {
                throw new ServiceException("Cuenta no encontrada");
            }
            String tipo = cuenta.getTipo();
            if (tipo.equals("caja de ahorro")) {
                return new CA(cuenta.getId(), cuenta.getIdUsuario(), cuenta.getAlias(), cuenta.getCBU(), ((CA) cuenta).getSaldo(), tipo);
            } else if (tipo.equals("cuenta corriente")) {
                return new Cta_Cte(cuenta.getId(), cuenta.getIdUsuario(), cuenta.getAlias(), cuenta.getCBU(), ((Cta_Cte) cuenta).getSaldo(), tipo);
            } else if (tipo.equals("caja de ahorro DLS")) {
                return new CA_dls(cuenta.getId(), cuenta.getIdUsuario(), cuenta.getAlias(), cuenta.getCBU(), ((CA_dls) cuenta).getSaldo(), tipo);
            } else {
                throw new ServiceException("Tipo de cuenta desconocido");
            }
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public Cuenta consultarAlias(String alias) throws ServiceException {
        try {
            if (alias == null || alias.isEmpty()) {
                throw new ServiceException("Alias no puede ser nulo o vacío");
            }
            Cuenta cuenta = daoCuenta.buscarAlias(alias);
            if (cuenta == null) {
                throw new ServiceException("Cuenta no encontrada con el alias proporcionado");
            }
            return cuenta;
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public ArrayList<Cuenta> consultarTodos() {
        try {
            return daoCuenta.buscarTodos();
        } catch (DAOException e) {
            throw new ServiceException("Ocurrió un error al consultar todas las cuentas", e);
        }
    }

    public ArrayList<Cuenta> consultarTodosID(int idusuario) {
        try {
            return daoCuenta.buscarTodosID(idusuario);
        } catch (DAOException e) {
            throw new ServiceException("Ocurrió un error al consultar todas las cuentas", e);
        }
    }




}
