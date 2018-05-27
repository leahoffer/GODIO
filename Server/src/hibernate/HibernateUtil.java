package hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import entity.*;
 
public class HibernateUtil
{
    private static final SessionFactory sessionFactory;

    static
    {
        try
        {
       	     AnnotationConfiguration config = new AnnotationConfiguration();
       	     config.addAnnotatedClass(PedidoEntity.class);
       	     config.addAnnotatedClass(DetallePedidoEntity.class);
             config.addAnnotatedClass(ClienteEntity.class);
             config.addAnnotatedClass(CuentaCorrienteEntity.class);
             config.addAnnotatedClass(MovimientoCCEntity.class);
             config.addAnnotatedClass(CondicionEntity.class);
             config.addAnnotatedClass(FacturaEntity.class);
             config.addAnnotatedClass(ItemFacturaEntity.class);
             config.addAnnotatedClass(ProductoEntity.class);
             config.addAnnotatedClass(UbicacionEntity.class);
             config.addAnnotatedClass(UbicacionId.class);
             config.addAnnotatedClass(BonificacionEntity.class);
             config.addAnnotatedClass(DescuentoEntity.class);
             config.addAnnotatedClass(LoteEntity.class);
             config.addAnnotatedClass(ItemRemitoEntity.class);
             config.addAnnotatedClass(MovimientoReservaEntity.class);
             config.addAnnotatedClass(MovimientoStockEntity.class);
             config.addAnnotatedClass(OrdenPedidoEntity.class);
             config.addAnnotatedClass(ReservaEntity.class);
            
             sessionFactory = config.buildSessionFactory();
        }
        catch (Throwable ex)
        {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
 
    public static SessionFactory getSessionFactory()
    {
        return sessionFactory;
    }
}
