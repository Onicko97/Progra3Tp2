package view;

public interface IParametros {
	String getCostoPorKm();
    String getPorcentajeRecargo();
    String getCostoFijo();

    
    void mostrarError(String mensaje);
    void mostrarExito(String mensaje);

}
