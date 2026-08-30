package sv.edu.ues.occ.ingenieria.ppl115_2026.salud.galenosv.control;

import jakarta.persistence.EntityManager;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.Mock;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;
import sv.edu.ues.occ.ingenieria.ppl115_2026.salud.galenosv.entity.Consulta;

/**
 *
 * @author antonio
 */
@ExtendWith(MockitoExtension.class)
public class DefaultDAOTest {

    @Mock
    EntityManager em;

    // Subclase mínima SOLO para poder instanciar la clase abstracta en el test.
    private class DAODePrueba extends DefaultDAO<String> {

        @Override
        public EntityManager getEntityManager() {
            return em;
        }
    }

    // ===================== crear() =====================
    @Test
    void crear_llamaAPersistCuandoElRegistroNoEsNulo() {
        DAODePrueba dao = new DAODePrueba();

        dao.crear("un registro de prueba");

        verify(em, times(1)).persist("un registro de prueba");
    }

    @Test
    void crear_lanzaIllegalArgumentExceptionSiElRegistroEsNulo() {
        DAODePrueba dao = new DAODePrueba();

        assertThrows(IllegalArgumentException.class, () -> dao.crear(null));
        verify(em, never()).persist(any());
    }

    @Test
    void crear_convierteErrorDePersistenciaEnIllegalStateException() {
        DAODePrueba dao = new DAODePrueba();

        doThrow(new RuntimeException("Fallo simulado de BD")).when(em).persist(any());

        assertThrows(IllegalStateException.class, () -> dao.crear("otro registro"));
    }

    // ===================== actualizar() =====================
    @Test
    void actualizar_llamaAMergeYRetornaElRegistroActualizado() {
        DAODePrueba dao = new DAODePrueba();
        when(em.merge("registro actualizado")).thenReturn("registro actualizado");

        String resultado = dao.actualizar("registro actualizado");

        assertEquals("registro actualizado", resultado);
        verify(em, times(1)).merge("registro actualizado");
    }

    @Test
    void actualizar_lanzaIllegalArgumentExceptionSiElRegistroEsNulo() {
        DAODePrueba dao = new DAODePrueba();

        assertThrows(IllegalArgumentException.class, () -> dao.actualizar(null));
        verify(em, never()).merge(any());
    }

    @Test
    void actualizar_convierteErrorDePersistenciaEnIllegalStateException() {
        DAODePrueba dao = new DAODePrueba();
        when(em.merge(any())).thenThrow(new RuntimeException("Fallo simulado de BD"));

        assertThrows(IllegalStateException.class, () -> dao.actualizar("otro registro"));
    }

    // ===================== buscar() =====================
    @Test
    void buscar_retornaElRegistroCuandoExiste() {
        DAODePrueba dao = new DAODePrueba();
        UUID id = UUID.randomUUID();
        when(em.find(String.class, id)).thenReturn("registro encontrado");

        String resultado = dao.buscar(id);

        assertEquals("registro encontrado", resultado);
    }

    @Test
    void buscar_lanzaIllegalArgumentExceptionSiElIdEsNulo() {
        DAODePrueba dao = new DAODePrueba();

        assertThrows(IllegalArgumentException.class, () -> dao.buscar(null));
        verify(em, never()).find(any(), any());
    }

    @Test
    void buscar_convierteErrorDeConsultaEnIllegalStateException() {
        DAODePrueba dao = new DAODePrueba();
        UUID id = UUID.randomUUID();
        when(em.find(String.class, id)).thenThrow(new RuntimeException("Fallo simulado de BD"));

        assertThrows(IllegalStateException.class, () -> dao.buscar(id));
    }

    // ===================== eliminar(UUID) =====================
    @Test
    void eliminar_llamaARemoveCuandoElRegistroExiste() {
        DAODePrueba dao = new DAODePrueba();
        UUID id = UUID.randomUUID();
        when(em.find(String.class, id)).thenReturn("registro a borrar");

        dao.eliminar(id);

        verify(em, times(1)).remove("registro a borrar");
    }

    @Test
    void eliminar_lanzaIllegalArgumentExceptionSiElIdEsNulo() {
        DAODePrueba dao = new DAODePrueba();

        assertThrows(IllegalArgumentException.class, () -> dao.eliminar(null));
        verify(em, never()).find(any(), any());
        verify(em, never()).remove(any());
    }

    @Test
    void eliminar_lanzaIllegalArgumentExceptionSiNoExisteElRegistro() {
        DAODePrueba dao = new DAODePrueba();
        UUID id = UUID.randomUUID();
        when(em.find(String.class, id)).thenReturn(null);

        assertThrows(IllegalArgumentException.class, () -> dao.eliminar(id));
        verify(em, never()).remove(any());
    }

    @Test
    void eliminar_convierteErrorDePersistenciaEnIllegalStateException() {
        DAODePrueba dao = new DAODePrueba();
        UUID id = UUID.randomUUID();
        when(em.find(String.class, id)).thenReturn("registro a borrar");
        doThrow(new RuntimeException("Fallo simulado de BD")).when(em).remove("registro a borrar");

        assertThrows(IllegalStateException.class, () -> dao.eliminar(id));
    }

    // ===================== findRange() =====================
    @Test
    void findRange_lanzaUnsupportedOperationExceptionSiNoSeSobreescribe() {
        DAODePrueba dao = new DAODePrueba();

        assertThrows(UnsupportedOperationException.class, () -> dao.findRange(0, 10));
    }

    @Test
    void findRange_lanzaIllegalArgumentExceptionSiFirstEsNegativo() {
        ConsultaDAO dao = new ConsultaDAO();
        // Nota: no necesitas mockear nada aquí, porque la validación
        // ocurre ANTES de tocar el EntityManager.
        assertThrows(IllegalArgumentException.class, () -> dao.findRange(-1, 10));
    }

}
