package ar.edu.unahur.obj2.operaciones.analistas;

import java.util.ArrayList;
import java.util.List;

import ar.edu.unahur.obj2.operaciones.alertas.IAlerta;
import ar.edu.unahur.obj2.operaciones.analistas.criterios.AnalistaNiv1;
import ar.edu.unahur.obj2.operaciones.analistas.criterios.ICriterioTraje;

public class Analista {
    private String Legajo;
    private ICriterioTraje criterioTraje = new AnalistaNiv1();
    private List<IAlerta> historialAlertas = new ArrayList<>();

    public String getLegajo() {
        return Legajo;
    }

    public ICriterioTraje getCriterioTraje() {
        return criterioTraje;
    }

    public List<IAlerta> getHistorialAlertas() {
        return historialAlertas;
    }

    public void agregarAlerta(IAlerta unaAlerta) {
        this.historialAlertas.add(unaAlerta);
    }

    public Boolean sePuedeProcesar(IAlerta unaAlerta) {
        return criterioTraje.puedeProcesar(unaAlerta);
    }

    public IAlerta enriquecerAlerta(IAlerta unaAlerta) {
        return criterioTraje.enriquecer(unaAlerta);
    }
}
