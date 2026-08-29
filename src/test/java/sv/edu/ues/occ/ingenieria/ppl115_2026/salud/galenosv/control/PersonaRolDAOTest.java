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
import sv.edu.ues.occ.ingenieria.ppl115_2026.salud.galenosv.entity.PersonaRol;

/**
 *
 * @author antonio
 */
public class PersonaRolDAOTest {
    
   List<PersonaRol> LISTA_REGISTROS;

    
    public PersonaRolDAOTest() {
           LISTA_REGISTROS = new ArrayList<>();
        LISTA_REGISTROS.add(new PersonaRol(UUID.randomUUID()));
        LISTA_REGISTROS.getFirst().setFechaCreacion(new Date());
        LISTA_REGISTROS.add(new PersonaRol(UUID.randomUUID()));
        LISTA_REGISTROS.get(1).setFechaCreacion(new Date());
        LISTA_REGISTROS.add(new PersonaRol(UUID.randomUUID()));
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
        Mockito.when(mockEM.createNamedQuery("PersonaRol.findAll", PersonaRol.class)).thenReturn(mockTQ);
        PersonaRolDAO cut = new PersonaRolDAO();
        cut.em = mockEM;
        List<PersonaRol> resultado = cut.findRange(first, max);
        assertNotNull(resultado);
        assertEquals(esperado,resultado.size());
        //fail("The test case is a prototype.");
    }
    
}
