package ar.edu.unahur.obj2.operaciones.analistas;

import java.util.ArrayList;
import java.util.List;

import ar.edu.unahur.obj2.operaciones.alertas.IAlerta;
import ar.edu.unahur.obj2.operaciones.analistas.criterios.AnalistaNiv1;
import ar.edu.unahur.obj2.operaciones.analistas.criterios.ICriterioTraje;

public class Analista {
    private String legajo;
    private ICriterioTraje criterioTriaje = new AnalistaNiv1();
    private List<IAlerta> historialAlertas = new ArrayList<>();

    public Analista(String legajo) {
        this.legajo = legajo;
    }

    public String getLegajo() {
        return legajo;
    }

    public ICriterioTraje getCriterioTriaje() {
        return criterioTriaje;
    }

    public List<IAlerta> getHistorialAlertas() {
        return historialAlertas;
    }

    public void cambiarCriterio(ICriterioTraje nuevoCriterio) {
        this.criterioTriaje = nuevoCriterio;
    }

    public void agregarAlerta(IAlerta unaAlerta) {
        this.historialAlertas.add(unaAlerta);
    }

    public Boolean sePuedeProcesar(IAlerta unaAlerta) {
        return criterioTriaje.puedeProcesar(unaAlerta);
    }

    public IAlerta enriquecerAlerta(IAlerta unaAlerta) {
        return criterioTriaje.enriquecer(unaAlerta);
    }
}
