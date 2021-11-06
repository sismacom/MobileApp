package spm.sismacom.frontend.appturimo.modelo;

public class DestinoTuristico {
    private String nombreDestino;
    private double valor;

    public DestinoTuristico() {
    }

    public DestinoTuristico(String nombreDestino, double valor) {
        this.nombreDestino = nombreDestino;
        this.valor = valor;
    }

    public String getNombreDestino() {
        return nombreDestino;
    }

    public void setNombreDestino(String nombreDestino) {
        this.nombreDestino = nombreDestino;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return nombreDestino;
    }
}
