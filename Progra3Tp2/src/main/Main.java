import models.domain.GestionRed;
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
