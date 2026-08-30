package sv.edu.ues.occ.ingenieria.ppl115_2026.salud.galenosv.control;

import java.util.List;
import java.util.UUID;

/**
 *
 * @author antonio
 */
public interface DAOInterface<T> {

    public void crear(T registro) throws IllegalArgumentException, IllegalStateException;

    public void eliminar(UUID id) throws IllegalArgumentException, IllegalStateException;

    public T actualizar(T registro) throws IllegalArgumentException, IllegalStateException;
    
    public T buscar(UUID id) throws IllegalArgumentException, IllegalStateException;

    public List<T> findRange(int first, int max) throws IllegalArgumentException, IllegalStateException;

}
