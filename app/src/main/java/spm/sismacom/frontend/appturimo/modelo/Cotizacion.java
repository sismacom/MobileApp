package spm.sismacom.frontend.appturimo.modelo;

public class Cotizacion {

    private String checkin;
    private String checkout;
    private String destino;
    private String cantidadPersonas;
    private double totalHospedaje;

    public Cotizacion (String checkin, String checkout,String destino,String cantidadPersonas,double totalHospedaje ){
        this.checkin=checkin;
        this.checkout=checkout;
        this.cantidadPersonas=cantidadPersonas;
        this.destino=destino;
        this.totalHospedaje=totalHospedaje;

    }

    public String getCheckin() {
        return checkin;
    }

    public void setCheckin(String checkin) {
        this.checkin = checkin;
    }

    public String getCheckout() {
        return checkout;
    }

    public void setCheckout(String checkout) {
        this.checkout = checkout;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getCantidadPersonas() {
        return cantidadPersonas;
    }

    public void setCantidadPersonas(String cantidadPersonas) {
        this.cantidadPersonas = cantidadPersonas;
    }

    public double getTotalHospedaje() {
        return totalHospedaje;
    }

    public void setTotalHospedaje(double totalHospedaje) {
        this.totalHospedaje = totalHospedaje;
    }

    public String Mensaje(){

        String Mensaje;

        Mensaje= "Destino: "+this.destino+ "para "+this.cantidadPersonas+"\n"+
                    "desde "+this.checkin+" hasta "+this.checkout+" tiene un costo de:\n"+
                    this.totalHospedaje;

        return Mensaje;

    }


}
