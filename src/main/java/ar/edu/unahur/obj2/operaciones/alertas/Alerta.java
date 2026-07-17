package ar.edu.unahur.obj2.operaciones.alertas;

public class Alerta implements IAlerta {
    private String codigoID;
    private String IPOrigen;
    private Integer serveridadBase;
    private Integer tiempoDeteccion;

    public Alerta(String codigo, String ip, Integer serveridad, Integer tiempoDeteccion) {
        this.codigoID = codigo;
        this.IPOrigen = ip;
        this.serveridadBase = serveridad;
        this.tiempoDeteccion = tiempoDeteccion;
    }

    @Override
    public String getCodigo() {
        return codigoID;
    }

    @Override
    public String getIP() {
        return IPOrigen;
    }

    @Override
    public Integer getServeridadBase() {
        return serveridadBase;
    }

    @Override
    public Integer getServeridadTotal() {
        return serveridadBase;
    }

    @Override
    public Integer getTDeteccion() {
        return tiempoDeteccion;
    }

    @Override
    public Boolean esCriticaDefecto() {
        return serveridadBase > 90;
    }
}
