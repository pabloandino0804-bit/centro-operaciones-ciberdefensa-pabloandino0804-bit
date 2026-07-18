package ar.edu.unahur.obj2.operaciones;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unahur.obj2.operaciones.alertas.Alerta;
import ar.edu.unahur.obj2.operaciones.alertas.IAlerta;
import ar.edu.unahur.obj2.operaciones.analistas.Analista;
import ar.edu.unahur.obj2.operaciones.excepciones.AnalistaSinCriterioException;
import ar.edu.unahur.obj2.operaciones.analistas.criterios.AnalistaNiv1;
import ar.edu.unahur.obj2.operaciones.analistas.criterios.EspEnInfraestructura;
import ar.edu.unahur.obj2.operaciones.analistas.criterios.EspEnAmenazasCrit;

public class MotorSIEMTest {
    private MotorSIEM motor = MotorSIEM.getInstance();
    private Analista miAnalista = new Analista("Matias");

    @BeforeEach
    void setUp() {
        motor.getAlertas().clear();
        miAnalista.cambiarCriterio(new AnalistaNiv1());
    }

    @Test
    void siElAnalistaNoTieneCriterioEntoncesLanzaraUnaExcepcion() {
        miAnalista.cambiarCriterio(null);

        assertThrows(AnalistaSinCriterioException.class, () -> motor.asignarTrabajo(miAnalista));
    }

    @Test
    void enCasoQueNoEncuentreUnoApropiadoDebeDarUnoDeEmergencia() {
        motor.recibirAlerta(new Alerta("CVE-2024-123", "124.248.21.5", 11, 44));
        miAnalista.cambiarCriterio(new EspEnAmenazasCrit());
        motor.asignarTrabajo(miAnalista);

        IAlerta alertaDada = miAnalista.getHistorialAlertas().get(0);

        assertEquals(alertaDada.getCodigo(), "CVE-2026-345");
    }

    @Test
    void alDarUnTrabajoAlAnalista_elMismoSeEliminaraDelMotor() {
        motor.recibirAlerta(new Alerta("CVE-2024-123", "192.168.0.1", 90, 44));
        miAnalista.cambiarCriterio(new EspEnInfraestructura("192.168.0.1", "10.0.0.5"));
        motor.asignarTrabajo(miAnalista);

        IAlerta alertaDada = miAnalista.getHistorialAlertas().get(0);

        assertEquals(motor.getAlertas().size(), 0);
        assertEquals(alertaDada.getCodigo(), "CVE-2024-123");
    }
}
