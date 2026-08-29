package sv.edu.ues.occ.ingenieria.ppl115_2026.salud.galenosv.control;

import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import sv.edu.ues.occ.ingenieria.ppl115_2026.salud.galenosv.entity.ProcedimientoPasoExamen;

/**
 *
 * @author antonio
 */
@Stateless
@LocalBean
public class ProcedimientoPasoExamenDAO extends DefaultDAO<ProcedimientoPasoExamen>{
   
    
    @PersistenceContext(unitName="Galeno-PU")
    EntityManager em;
    
    @Override
    public EntityManager getEntityManager() {
     return em;
    }
    
    
     @Override
    public List<ProcedimientoPasoExamen> findRange(int first, int max) throws IllegalArgumentException, IllegalStateException {
        if (first >= 0 && max > 0) {
            try {
                TypedQuery<ProcedimientoPasoExamen> q = em.createNamedQuery("ProcedimientoPasoExamen.findAll", ProcedimientoPasoExamen.class);
                q.setFirstResult(first);
                q.setMaxResults(max);
                return q.getResultList();
            } catch (Exception ex) {
                Logger.getLogger(getClass().getName()).log(Level.SEVERE, ex.getMessage(), ex);
                throw new IllegalStateException("Error al consultar los registros", ex);
            }
        }
        throw new IllegalArgumentException("first debe ser >= 0 y max debe ser > 0");
    }
}
