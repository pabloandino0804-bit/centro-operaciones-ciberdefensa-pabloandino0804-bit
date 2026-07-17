package ar.edu.unahur.obj2.operaciones.alertas;

public interface IAlerta {
    String getCodigo();

    String getIP();

    Integer getServeridadBase();

    Integer getServeridadTotal();

    Integer getTDeteccion();

    Boolean esCriticaDefecto();
}
