import java.util.ArrayList;
import java.util.List;

import models.domain.GestionRed;
import models.domain.Localidad;
import models.domain.ServicioJson;
import presenter.RedFibraOpticaPresenter;
import view.VentanaPrincipal;
 
public class Main {
    public static void main(String[] args) {
       
        GestionRed modelo = new GestionRed();

        VentanaPrincipal vista = new VentanaPrincipal();
        
        RedFibraOpticaPresenter presenter = new RedFibraOpticaPresenter(vista, modelo);

        presenter.iniciar();
    }
}
