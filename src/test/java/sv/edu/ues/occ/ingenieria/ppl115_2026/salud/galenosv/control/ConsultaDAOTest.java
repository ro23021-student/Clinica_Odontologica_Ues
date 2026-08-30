package sv.edu.ues.occ.ingenieria.ppl115_2026.salud.galenosv.control;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.Mockito;
import static org.mockito.Mockito.when;
import sv.edu.ues.occ.ingenieria.ppl115_2026.salud.galenosv.entity.Consulta;

/**
 *
 * @author antonio
 */
public class ConsultaDAOTest {

    List<Consulta> LISTA_REGISTROS;

    public ConsultaDAOTest() {
        LISTA_REGISTROS = new ArrayList<>();
        LISTA_REGISTROS.add(new Consulta(UUID.randomUUID()));
        LISTA_REGISTROS.getFirst().setReferenciaExterna("RF-001");
        LISTA_REGISTROS.add(new Consulta(UUID.randomUUID()));
        LISTA_REGISTROS.get(1).setReferenciaExterna("RF-002");
        LISTA_REGISTROS.add(new Consulta(UUID.randomUUID()));
        LISTA_REGISTROS.getLast().setReferenciaExterna("RF-003");

    }

    @Test
    public void testFindRange() {
        System.out.println("findRange");
        int first = 0;
        int max = 100;
        int esperado = LISTA_REGISTROS.size();
        TypedQuery mockTQ = Mockito.mock(TypedQuery.class);
        Mockito.when(mockTQ.getResultList()).thenReturn(LISTA_REGISTROS);
        EntityManager mockEM = Mockito.mock(EntityManager.class);
        Mockito.when(mockEM.createNamedQuery("Consulta.findAll", Consulta.class)).thenReturn(mockTQ);
        ConsultaDAO cut = new ConsultaDAO();
        cut.em = mockEM;
        List<Consulta> resultado = cut.findRange(first, max);
        assertNotNull(resultado);
        assertEquals(esperado, resultado.size());
        //fail("The test case is a prototype.");
    }

    
}
