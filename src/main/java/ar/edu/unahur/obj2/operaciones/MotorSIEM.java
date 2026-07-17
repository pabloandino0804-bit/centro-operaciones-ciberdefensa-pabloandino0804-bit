package ar.edu.unahur.obj2.operaciones;

import java.util.ArrayList;
import java.util.List;

import ar.edu.unahur.obj2.operaciones.alertas.Alerta;
import ar.edu.unahur.obj2.operaciones.alertas.IAlerta;
import ar.edu.unahur.obj2.operaciones.analistas.Analista;

public class MotorSIEM {
    private List<IAlerta> colaAlertas = new ArrayList<>();
    private MotorSIEM instance = new MotorSIEM();

    public MotorSIEM() {
    }

    public MotorSIEM getInstance() {
        return instance;
    }

    public List<IAlerta> getAlertas() {
        return colaAlertas;
    }

    public void recibirAlerta(IAlerta alerta) {
        this.colaAlertas.add(alerta);
    }

    public void asignarTrabajo(Analista unAnalista) {
        if (unAnalista == null) {

        }

        IAlerta alertaAEntregar = colaAlertas.stream()
                .filter(a -> unAnalista.sePuedeProcesar(a))
                .findFirst().orElse(new Alerta("CVE-2026-345", "192.168.11.1", 45, 3));

        this.colaAlertas.remove(alertaAEntregar);

        alertaAEntregar = unAnalista.enriquecerAlerta(alertaAEntregar);

        unAnalista.agregarAlerta(alertaAEntregar);
    }
}
