package ar.edu.unahur.obj2.operaciones.analistas.criterios;

import ar.edu.unahur.obj2.operaciones.alertas.IAlerta;

public class EspEnAmenazasCrit implements ICriterioTraje {

    @Override
    public String nombreCriterio() {
        return "Especialista en Amenazas Criticas";
    }

    @Override
    public Boolean puedeProcesar(IAlerta unaAlerta) {
        return unaAlerta.getServeridadBase() > 75;
    }

    @Override
    public IAlerta enriquecer(IAlerta unaAlerta) {
        return unaAlerta;
    }

}
