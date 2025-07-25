package observador;

import java.util.ArrayList;
import java.util.List;

public class Notificador {
    private List<Observador> observadores = new ArrayList<>();

    public void agregarObservador(Observador o) {
        observadores.add(o);
    }

    public void eliminarObservador(Observador o) {
        observadores.remove(o);
    }

    public void notificar(String mensaje) {
        for (Observador o : observadores) {
            o.actualizar(mensaje);
        }
    }
}