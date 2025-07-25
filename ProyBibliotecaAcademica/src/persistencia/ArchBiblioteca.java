package persistencia;

import java.io.*;
import java.util.List;

public class ArchBiblioteca {

    public static <T> void guardarLista(List<T> lista, String rutaArchivo) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(rutaArchivo))) {
            oos.writeObject(lista);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> List<T> cargarLista(String rutaArchivo) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(rutaArchivo))) {
            return (List<T>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return null;
        }
    }
}
