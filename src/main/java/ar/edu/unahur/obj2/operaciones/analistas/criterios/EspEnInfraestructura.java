package ar.edu.unahur.obj2.operaciones.analistas.criterios;

import java.util.Arrays;
import java.util.List;

import ar.edu.unahur.obj2.operaciones.alertas.IAlerta;
import ar.edu.unahur.obj2.operaciones.alertas.threatIntels.GeolocalizacionIP;
import ar.edu.unahur.obj2.operaciones.alertas.threatIntels.HistorialForense;

public class EspEnInfraestructura implements ICriterioTraje {
    private List<String> listaIPs;

    public EspEnInfraestructura(String... IPs) {
        this.listaIPs = Arrays.asList(IPs);
    }

    @Override
    public String nombreCriterio() {
        return "Especialista en Infraestructura";
    }

    @Override
    public Boolean puedeProcesar(IAlerta unaAlerta) {
        return listaIPs.contains(unaAlerta.getIP());
    }

    @Override
    public IAlerta enriquecer(IAlerta unaAlerta) {
        return new HistorialForense(new GeolocalizacionIP(unaAlerta));
    }
}
