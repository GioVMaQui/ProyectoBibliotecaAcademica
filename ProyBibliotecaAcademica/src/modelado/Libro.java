package modelado;

import java.io.Serializable;

public class Libro implements Serializable {
    private String titulo;
    private String autor;
    private String codigo;
    private EstadoLibro estado;

    public Libro(String titulo, String autor, String codigo) {
        this.titulo = titulo;
        this.autor = autor;
        this.codigo = codigo;
        this.estado = EstadoLibro.DISPONIBLE;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public String getCodigo() {
        return codigo;
    }

    public EstadoLibro getEstado() {
        return estado;
    }

    public boolean isDisponible() {
        return estado == EstadoLibro.DISPONIBLE;
    }

    public void setDisponible(boolean disponible) {
        this.estado = disponible ? EstadoLibro.DISPONIBLE : EstadoLibro.PRESTADO;
    }

    @Override
    public String toString() {
        return "Libro{codigo='" + codigo + "', titulo='" + titulo + "', autor='" + autor + "', estado=" + estado + "}";
    }
}