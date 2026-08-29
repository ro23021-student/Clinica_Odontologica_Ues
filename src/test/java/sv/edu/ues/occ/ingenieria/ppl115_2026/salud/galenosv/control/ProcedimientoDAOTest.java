/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package sv.edu.ues.occ.ingenieria.ppl115_2026.salud.galenosv.control;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.Mockito;
import sv.edu.ues.occ.ingenieria.ppl115_2026.salud.galenosv.entity.Procedimiento;

/**
 *
 * @author antonio
 */
public class ProcedimientoDAOTest {
    
     
   List<Procedimiento> LISTA_REGISTROS;

    
    public ProcedimientoDAOTest() {
           LISTA_REGISTROS = new ArrayList<>();
        LISTA_REGISTROS.add(new Procedimiento(UUID.randomUUID()));
        LISTA_REGISTROS.getFirst().setNombre("Mario");
        LISTA_REGISTROS.add(new Procedimiento(UUID.randomUUID()));
        LISTA_REGISTROS.get(1).setNombre("Jorge");
        LISTA_REGISTROS.add(new Procedimiento(UUID.randomUUID()));
        LISTA_REGISTROS.getLast().setNombre("Carlos");
        
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
        Mockito.when(mockEM.createNamedQuery("Procedimiento.findAll", Procedimiento.class)).thenReturn(mockTQ);
        ProcedimientoDAO cut = new ProcedimientoDAO();
        cut.em = mockEM;
        List<Procedimiento> resultado = cut.findRange(first, max);
        assertNotNull(resultado);
        assertEquals(esperado,resultado.size());
        //fail("The test case is a prototype.");
    }
    
}
