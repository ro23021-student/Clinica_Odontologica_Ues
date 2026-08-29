package sv.edu.ues.occ.ingenieria.ppl115_2026.salud.galenosv.control;

import jakarta.persistence.EntityManager;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author antonio
 */
public abstract class DefaultDAO<T> implements DAOInterface<T>{
   
       public abstract EntityManager getEntityManager();
    
    @Override
    public void crear(T registro) throws IllegalArgumentException, IllegalStateException {
    if(registro!=null){
          try{
           getEntityManager().persist(registro);
          }
          catch(Exception ex){
             Logger.getLogger(getClass().getName()).log(Level.SEVERE,ex.getMessage(),ex);
            throw new IllegalStateException("Error al crear el registro",ex);
          }
      }
      throw new IllegalArgumentException("El registro no puede ser nulo");
    }

    @Override
    public List<T> findRange(int first, int max) throws IllegalArgumentException, IllegalStateException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }


    
}
