package ar.edu.unahur.obj2.operaciones.analistas.criterios;

import ar.edu.unahur.obj2.operaciones.alertas.IAlerta;
import ar.edu.unahur.obj2.operaciones.alertas.threatIntels.GeolocalizacionIP;
import ar.edu.unahur.obj2.operaciones.alertas.threatIntels.HistorialForense;

public class EspEnAmenazasCrit implements ICriterioTraje {

    @Override
    public String nombreCriterio() {
        return "Especialista en Amenazas Críticas";
    }

    @Override
    public Boolean puedeProcesar(IAlerta unaAlerta) {
        return unaAlerta.getServeridadBase() > 75;
    }

    @Override
    public IAlerta enriquecer(IAlerta unaAlerta) {

        return new HistorialForense(new GeolocalizacionIP(unaAlerta));
    }

}
