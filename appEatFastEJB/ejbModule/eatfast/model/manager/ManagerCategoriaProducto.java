package eatfast.model.manager;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import eatfast.model.entities.TabInvCategoriaProducto;

/**
 * Session Bean implementation class ManagerCategoriaProducto
 */
@Stateless
@LocalBean
public class ManagerCategoriaProducto {

    @PersistenceContext(unitName = "restauranteDS")
    private EntityManager em;
    
    public ManagerCategoriaProducto() {
        // TODO Auto-generated constructor stub
    }
    
    public void agregarCategoriaProducto(String nombrecategoria, String descripcioncategoria) throws Exception{
    	TabInvCategoriaProducto pr=new TabInvCategoriaProducto();
    	pr.setNombrecategoria(nombrecategoria);
    	pr.setDescripcioncategoria(descripcioncategoria);
    	em.persist(pr);
    }
    public TabInvCategoriaProducto findProductoByID(int idcategoria) throws Exception{
    	TabInvCategoriaProducto pr = em.find(TabInvCategoriaProducto.class, idcategoria);
    	return pr;
    }
    public void editarCategoria(int idcategoria, String nombrecategoria, String descripcioncategoria)throws Exception{
    	TabInvCategoriaProducto pr = findProductoByID(idcategoria);
    	if(pr == null)
    		throw new Exception("No existe la categoria del producto especificada.");
    	pr.setNombrecategoria(nombrecategoria);
    	pr.setDescripcioncategoria(descripcioncategoria);
    	em.persist(pr);
    }
    public List<TabInvCategoriaProducto> findAllCategorias(){
    	Query q;
    	List<TabInvCategoriaProducto> listado;
    	String sentenciaSQL;
    	sentenciaSQL = "SELECT p FROM TabInvCategoriaProducto p ORDER BY p.nombrecategoria";
    	q = em.createQuery(sentenciaSQL);
    	listado=q.getResultList();
    	return listado;
    	
    }

}
