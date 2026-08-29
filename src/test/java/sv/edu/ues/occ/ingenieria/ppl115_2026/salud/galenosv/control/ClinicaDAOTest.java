package sv.edu.ues.occ.ingenieria.ppl115_2026.salud.galenosv.control;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.Mockito;
import sv.edu.ues.occ.ingenieria.ppl115_2026.salud.galenosv.entity.Clinica;

/**
 *
 * @author antonio
 */
public class ClinicaDAOTest {
    
        List<Clinica> LISTA_REGISTROS;

    
    public ClinicaDAOTest() {
           LISTA_REGISTROS = new ArrayList<>();
        LISTA_REGISTROS.add(new Clinica(UUID.randomUUID()));
        LISTA_REGISTROS.getFirst().setNombre("CHEPE");
        LISTA_REGISTROS.add(new Clinica(UUID.randomUUID()));
        LISTA_REGISTROS.get(1).setNombre("MARIA");
        LISTA_REGISTROS.add(new Clinica(UUID.randomUUID()));
        LISTA_REGISTROS.getLast().setNombre("JORGE");
        
    }

  @Test
    public void testFindRange() {
        System.out.println("findRange");
        int first = 0;
        int max = 100;
        int esperado=LISTA_REGISTROS.size();
        TypedQuery mockTQ = Mockito.mock(TypedQuery.class);
        Mockito.when(mockTQ.getResultList()).thenReturn(LISTA_REGISTROS);
        EntityManager mockEM = Mockito.mock(EntityManager.class);
        Mockito.when(mockEM.createNamedQuery("Clinica.findAll", Clinica.class)).thenReturn(mockTQ);
        ClinicaDAO cut = new ClinicaDAO();
        cut.em = mockEM;
        List<Clinica> resultado = cut.findRange(first, max);
        assertNotNull(resultado);
        assertEquals(esperado,resultado.size());
        //fail("The test case is a prototype.");
    }

    
}
