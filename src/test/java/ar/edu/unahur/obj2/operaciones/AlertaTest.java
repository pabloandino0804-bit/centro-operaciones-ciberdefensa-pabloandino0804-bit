package ar.edu.unahur.obj2.operaciones;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import ar.edu.unahur.obj2.operaciones.alertas.Alerta;
import ar.edu.unahur.obj2.operaciones.alertas.IAlerta;
import ar.edu.unahur.obj2.operaciones.alertas.threatIntels.AnalisisMalwareSandBox;
import ar.edu.unahur.obj2.operaciones.alertas.threatIntels.GeolocalizacionIP;
import ar.edu.unahur.obj2.operaciones.alertas.threatIntels.HistorialForense;
import ar.edu.unahur.obj2.operaciones.excepciones.AlertaInvalidaException;
import ar.edu.unahur.obj2.operaciones.excepciones.EnreiquecimientoIncompatibleException;

public class AlertaTest {
    private Alerta alerta = new Alerta("CVE-2024-123", "124.247.21.5", 170, 44);

    @Test
    void unaAlertaSeCreaConSusValoresCorrectamente() {
        assertEquals("124.247.21.5", alerta.getIP());
        assertEquals("CVE-2024-123", alerta.getCodigo());
        assertEquals(170, alerta.getServeridadBase());
        assertEquals(170, alerta.getServeridadTotal());
        assertEquals(44, alerta.getTDeteccion());
    }

    @Test
    void unaAlertEsCriticaSiSuSeveridadSuperaA90() {
        assertTrue(alerta.esCriticaDefecto());
    }

    @Test
    void unaAlertaDebeTenerSuSeveridadBaseSinSerModificado() {
        IAlerta nuevaAlerta = new AnalisisMalwareSandBox(alerta);

        assertEquals("124.247.21.5", nuevaAlerta.getIP());
        assertEquals("CVE-2024-123", nuevaAlerta.getCodigo());
        assertEquals(170, nuevaAlerta.getServeridadBase());
        assertEquals(44, nuevaAlerta.getTDeteccion());
    }

    // Alertas Enriquecidas(Threat Intels)
    @Test
    void unaAlertaConAnalisisSuma35ALaSeveridadTotal() {
        IAlerta nuevaAlerta = new AnalisisMalwareSandBox(alerta);

        assertEquals(205, nuevaAlerta.getServeridadTotal());
    }

    @Test
    void unaAlertaConHistorialForenseSuma15ALaServeridadTotal() {
        IAlerta nuevaAlerta = new HistorialForense(alerta);

        assertEquals(185, nuevaAlerta.getServeridadTotal());
    }

    @Test
    void unaAlertaConGeolocalizacionSuma5ALaServeridadTotal() {
        IAlerta nuevaAlerta = new GeolocalizacionIP(alerta);

        assertEquals(175, nuevaAlerta.getServeridadTotal());
    }

    @Test
    void enAnalisisDeMalwareSiViolaAlertaNoCumpleEntoncesLanzaUnaExcepcion() {
        assertThrows(EnreiquecimientoIncompatibleException.class,
                () -> new AnalisisMalwareSandBox(new Alerta("arg-13563", "127.0.0.1", 245, 2025)));
    }

    @Test
    void cuandoUnaAlertaEsInvalida_LanzaUnaExcepcion() {
        assertThrows(AlertaInvalidaException.class,
                () -> new AnalisisMalwareSandBox(new Alerta("", "", -246, 2025)));
    }
}
