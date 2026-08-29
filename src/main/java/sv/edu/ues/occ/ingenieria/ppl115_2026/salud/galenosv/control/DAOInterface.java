 package sv.edu.ues.occ.ingenieria.ppl115_2026.salud.galenosv.control;

import java.util.List;

/**
 *
 * @author antonio
 */
public interface DAOInterface<T> {
     
    public void crear(T registro) throws IllegalArgumentException, IllegalStateException;
    
    public List<T> findRange(int first, int max) throws IllegalArgumentException, IllegalStateException;
    
}
