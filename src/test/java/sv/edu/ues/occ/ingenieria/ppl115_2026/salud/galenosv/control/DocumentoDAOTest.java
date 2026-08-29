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
import sv.edu.ues.occ.ingenieria.ppl115_2026.salud.galenosv.entity.Documento;

/**
 *
 * @author antonio
 */
public class DocumentoDAOTest {
    
     List<Documento> LISTA_REGISTROS;

    
    public DocumentoDAOTest() {
           LISTA_REGISTROS = new ArrayList<>();
        LISTA_REGISTROS.add(new Documento(UUID.randomUUID()));
        LISTA_REGISTROS.getFirst().setValor("1212");
        LISTA_REGISTROS.add(new Documento(UUID.randomUUID()));
        LISTA_REGISTROS.get(1).setValor("2345");
        LISTA_REGISTROS.add(new Documento(UUID.randomUUID()));
        LISTA_REGISTROS.getLast().setValor("6722");
        
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
        Mockito.when(mockEM.createNamedQuery("Documento.findAll", Documento.class)).thenReturn(mockTQ);
        DocumentoDAO cut = new DocumentoDAO();
        cut.em = mockEM;
        List<Documento> resultado = cut.findRange(first, max);
        assertNotNull(resultado);
        assertEquals(esperado,resultado.size());
        //fail("The test case is a prototype.");
    }
    
}
