package icesi.movil.login.model;

public class Usuario {

    private  String uid;
    private String nombre;
    private String edad;
    private String email;

    public Usuario() {

    }

    public Usuario(String uid, String nombre, String edad, String email) {
        this.uid = uid;
        this.nombre = nombre;
        this.edad = edad;
        this.email = email;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
