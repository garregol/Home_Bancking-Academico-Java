package Entidades;

public class Usuario {
    private int id;
    private String nombre;
    private String apellido;
    private String Username;
    private String Password;
    private boolean esAdmin;

    public Usuario(int id, String nombre, String apellido,String username,String password, boolean esAdmin) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.Username = username;
        this.Password = password;
        this.esAdmin = esAdmin;

    }

    public Usuario() {
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getUsername() {return Username;}

    public String getPassword() {return Password;}

    public boolean isEsAdmin() {return esAdmin;}

    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setUsername(String username) {Username = username;}

    public void setPassword(String password) {Password = password;}

    public void setEsAdmin(boolean esAdmin) {this.esAdmin = esAdmin;}
}
