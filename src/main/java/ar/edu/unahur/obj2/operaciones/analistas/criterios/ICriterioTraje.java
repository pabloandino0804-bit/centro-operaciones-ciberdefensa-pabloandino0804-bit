package ar.edu.unahur.obj2.operaciones.analistas.criterios;

import ar.edu.unahur.obj2.operaciones.alertas.IAlerta;

public interface ICriterioTraje {

    String nombreCriterio();

    Boolean puedeProcesar(IAlerta unaAlerta);

    IAlerta enriquecer(IAlerta unaAlerta);
}
