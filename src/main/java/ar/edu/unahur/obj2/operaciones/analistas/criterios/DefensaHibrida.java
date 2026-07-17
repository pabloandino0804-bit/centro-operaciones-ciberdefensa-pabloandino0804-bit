package ar.edu.unahur.obj2.operaciones.analistas.criterios;

import java.util.Arrays;
import java.util.List;

import ar.edu.unahur.obj2.operaciones.alertas.IAlerta;

public class DefensaHibrida implements ICriterioTraje {
    private List<String> listaIPs;

    public DefensaHibrida(String... IPs) {
        this.listaIPs = Arrays.asList(IPs);
    }

    @Override
    public String nombreCriterio() {
        return "Defensa Híbrida";
    }

    @Override
    public Boolean puedeProcesar(IAlerta unaAlerta) {
        return listaIPs.contains(unaAlerta.getIP()) && unaAlerta.getServeridadBase() > 75;
    }

    @Override
    public IAlerta enriquecer(IAlerta unaAlerta) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'enriquecer'");
    }

}
