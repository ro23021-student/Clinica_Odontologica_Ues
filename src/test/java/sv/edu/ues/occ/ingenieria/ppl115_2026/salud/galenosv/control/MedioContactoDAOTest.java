/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package sv.edu.ues.occ.ingenieria.ppl115_2026.salud.galenosv.control;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.Mockito;
import sv.edu.ues.occ.ingenieria.ppl115_2026.salud.galenosv.entity.MedioContacto;

/**
 *
 * @author antonio
 */
public class MedioContactoDAOTest {
    
     List<MedioContacto> LISTA_REGISTROS;

    
    public MedioContactoDAOTest() {
           LISTA_REGISTROS = new ArrayList<>();
        LISTA_REGISTROS.add(new MedioContacto(UUID.randomUUID()));
        LISTA_REGISTROS.getFirst().setFechaCreacion(new Date());
        LISTA_REGISTROS.add(new MedioContacto(UUID.randomUUID()));
        LISTA_REGISTROS.get(1).setFechaCreacion(new Date());
        LISTA_REGISTROS.add(new MedioContacto(UUID.randomUUID()));
        LISTA_REGISTROS.getLast().setFechaCreacion(new Date());
        
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
        Mockito.when(mockEM.createNamedQuery("MedioContacto.findAll", MedioContacto.class)).thenReturn(mockTQ);
        MedioContactoDAO cut = new MedioContactoDAO();
        cut.em = mockEM;
        List<MedioContacto> resultado = cut.findRange(first, max);
        assertNotNull(resultado);
        assertEquals(esperado,resultado.size());
        //fail("The test case is a prototype.");
    }
    
}
