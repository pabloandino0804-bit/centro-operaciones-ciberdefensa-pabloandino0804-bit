package ar.edu.unahur.obj2.operaciones.alertas.threatIntels;

import ar.edu.unahur.obj2.operaciones.alertas.AlertaDeco;
import ar.edu.unahur.obj2.operaciones.alertas.IAlerta;

public class GeolocalizacionIP extends AlertaDeco {

    public GeolocalizacionIP(IAlerta unaAlerta) {
        super(unaAlerta);
    }

    @Override
    public Integer getServeridadTotal() {
        return unaAlerta.getServeridadBase() + 5;
    }

    @Override
    protected Boolean esIncompatible(IAlerta unaAlerta) {
        return false;
    }

}
