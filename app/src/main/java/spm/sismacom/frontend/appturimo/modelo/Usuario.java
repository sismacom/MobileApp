package spm.sismacom.frontend.appturimo.modelo;

public class Usuario {
    private String nombreUsuario;
    private String passUsuario;
    private String tipoUsuario;

    public Usuario() {
    }

    public Usuario(String nombreUsuario, String passUsuario, String tipoUsuario) {
        this.nombreUsuario = nombreUsuario;
        this.passUsuario = passUsuario;
        this.tipoUsuario = tipoUsuario;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getPassUsuario() {
        return passUsuario;
    }

    public void setPassUsuario(String passUsuario) {
        this.passUsuario = passUsuario;
    }

    @Override
    public String toString() {
        return nombreUsuario;
    }
}
