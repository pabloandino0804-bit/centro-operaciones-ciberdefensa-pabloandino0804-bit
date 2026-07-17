package ar.edu.unahur.obj2.operaciones.alertas;

import ar.edu.unahur.obj2.operaciones.excepciones.EnreiquecimientoIncompatibleException;
import ar.edu.unahur.obj2.operaciones.excepciones.AlertaInvalidaException;

public abstract class AlertaDeco implements IAlerta {
    protected final IAlerta unaAlerta;

    public AlertaDeco(IAlerta unaAlerta) {
        if (unaAlerta.getCodigo() == "" || unaAlerta.getIP() == "" || unaAlerta.getServeridadBase() < 0) {
            throw new AlertaInvalidaException("La alerta dada es invlida");
        }
        if (esIncompatible(unaAlerta)) {
            throw new EnreiquecimientoIncompatibleException("La alerta es incompatible");
        }
        this.unaAlerta = unaAlerta;
    }

    @Override
    public String getCodigo() {
        return unaAlerta.getCodigo();
    }

    @Override
    public String getIP() {
        return unaAlerta.getIP();
    }

    @Override
    public Integer getServeridadBase() {
        return unaAlerta.getServeridadBase();
    }

    @Override
    public Integer getTDeteccion() {
        return unaAlerta.getTDeteccion();
    }

    @Override
    public Boolean esCriticaDefecto() {
        return unaAlerta.esCriticaDefecto();
    }

    protected abstract Boolean esIncompatible(IAlerta unaAlerta);
}
