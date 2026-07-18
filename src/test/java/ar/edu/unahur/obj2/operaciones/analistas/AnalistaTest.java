package ar.edu.unahur.obj2.operaciones.analistas;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unahur.obj2.operaciones.alertas.Alerta;
import ar.edu.unahur.obj2.operaciones.alertas.IAlerta;
import ar.edu.unahur.obj2.operaciones.analistas.criterios.AnalistaNiv1;
import ar.edu.unahur.obj2.operaciones.analistas.criterios.DefensaHibrida;
import ar.edu.unahur.obj2.operaciones.analistas.criterios.EspEnAmenazasCrit;
import ar.edu.unahur.obj2.operaciones.analistas.criterios.EspEnInfraestructura;

public class AnalistaTest {
    private Analista miAnalista = new Analista("Matias");

    @BeforeEach
    void setUp() {
        miAnalista.cambiarCriterio(new AnalistaNiv1());
    }

    @Test
    void dadoUnAnalista_CuandoConsutlaSULegajoDevuelvaUnString() {
        assertEquals("Matias", miAnalista.getLegajo());
    }

    @Test
    void alRegistrarUnaAlertaLaMisaDeBestasEnElHistorialDelAnalista() {
        Alerta alerta = new Alerta("CVE-2024-123", "124.247.21.5", 250, 44);
        miAnalista.agregarAlerta(alerta);

        assertTrue(miAnalista.getHistorialAlertas().contains(alerta));
    }

    @Test
    void testEnriquecerAlerta() {
        Alerta alerta = new Alerta("CVE-2024-123", "124.247.21.5", 250, 44);

        IAlerta nuevaAlerta = miAnalista.enriquecerAlerta(alerta);

        assertEquals(nuevaAlerta.getServeridadTotal(), 250);
    }

    @Test
    void testEnriquecerAlerta2() {
        miAnalista.cambiarCriterio(new DefensaHibrida("192.168.0.1", "10.0.0.5", "124.246.21.5"));
        Alerta alerta = new Alerta("CVE-2024-123", "124.247.21.5", 250, 44);

        IAlerta nuevaAlerta = miAnalista.enriquecerAlerta(alerta);

        assertEquals(nuevaAlerta.getServeridadTotal(), 265);
    }

    @Test
    void elCriterioPredDelAnalistaEsAnalistaNiv1() {
        String nombre = miAnalista.getCriterioTriaje().nombreCriterio();

        assertEquals(nombre, "Analista Nivel 1");
    }

    @Test
    void unAnalistaJuniorPuedeProcesarCuaquierAlerta() {
        Alerta alerta = new Alerta("CVE-2024-123", "124.247.21.5", 250, 44);

        assertTrue(miAnalista.sePuedeProcesar(alerta));
    }

    @Test
    void unaAnalistaConEspEnAmenazasPuedeProcesarSiLaAlertaTieneSeveridadSuperiorA75() {
        miAnalista.cambiarCriterio(new EspEnAmenazasCrit());
        Alerta alerta = new Alerta("CVE-2024-123", "124.247.21.5", 250, 44);
        Alerta alerta2 = new Alerta("CVE-2024-123", "124.248.21.5", 11, 44);

        assertTrue(miAnalista.sePuedeProcesar(alerta));
        assertFalse(miAnalista.sePuedeProcesar(alerta2));
        assertEquals(miAnalista.getCriterioTriaje().nombreCriterio(), "Especialista en Amenazas Críticas");
    }

    @Test
    void unaAnalistaConEspEnInfraestructuraPuedeProcesarSiLaIPDelLaAlertaProvieneDeUnaListaIP() {
        miAnalista.cambiarCriterio(new EspEnInfraestructura("192.168.0.1", "10.0.0.5", "124.246.21.5"));
        Alerta alerta = new Alerta("CVE-2024-123", "124.246.21.5", 11, 44);
        Alerta alerta2 = new Alerta("CVE-2024-123", "124.248.21.5", 11, 44);

        assertTrue(miAnalista.sePuedeProcesar(alerta));
        assertFalse(miAnalista.sePuedeProcesar(alerta2));
        assertEquals(miAnalista.getCriterioTriaje().nombreCriterio(), "Especialista en Infraestructura");
    }

    @Test
    void unaAnalistaConDefHibridaPuedeProcesarLasAlertasQueCumplanLas2CriteriosAnteriores() {
        miAnalista.cambiarCriterio(new DefensaHibrida("192.168.0.1", "10.0.0.5", "124.246.21.5"));
        Alerta alerta = new Alerta("CVE-2024-123", "124.246.21.5", 250, 44);
        Alerta alerta2 = new Alerta("CVE-2024-123", "124.248.21.5", 11, 44);

        assertTrue(miAnalista.sePuedeProcesar(alerta));
        assertFalse(miAnalista.sePuedeProcesar(alerta2));
        assertEquals(miAnalista.getCriterioTriaje().nombreCriterio(), "Defensa Híbrida");
    }
}
