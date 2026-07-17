package ar.edu.unahur.obj2.operaciones.analistas.criterios;

import ar.edu.unahur.obj2.operaciones.alertas.IAlerta;

public class AnalistaNiv1 implements ICriterioTraje {

    @Override
    public String nombreCriterio() {
        return "Analista Nivel 1";
    }

    @Override
    public Boolean puedeProcesar(IAlerta unaAlerta) {
        return true;
    }

    @Override
    public IAlerta enriquecer(IAlerta unaAlerta) {
        return unaAlerta;
    }

}
