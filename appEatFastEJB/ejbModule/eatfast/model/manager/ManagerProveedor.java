package eatfast.model.manager;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import eatfast.model.entities.TabInvProveedor;


/**
 * Session Bean implementation class ManagerProveedor
 */
@Stateless
@LocalBean
public class ManagerProveedor {
	@PersistenceContext(unitName = "restauranteDS")
	private EntityManager em;

    public ManagerProveedor() {
      
    }
    
    public void agregarproveedor(Integer idproveedor, String nombreproveedor, String direccionproveedor,
			String telefonoproveedor, String correoproveedor) throws Exception {
		if (idproveedor == null || idproveedor.equals(0))
			throw new Exception("Debe especificar la cedula.");
		TabInvProveedor p = new TabInvProveedor();
		p.setIdproveedor(idproveedor);
		p.setNombreproveedor(nombreproveedor);
		p.setTelefonoproveedor(telefonoproveedor);
		p.setDireccionproveedor(direccionproveedor);
		p.setCorreoproveedor(correoproveedor);
		
		em.persist(p);
	}

	public TabInvProveedor findproveedorByID(Integer idproveedor) throws Exception {
		TabInvProveedor p = em.find(TabInvProveedor.class, idproveedor);
		return p;
	}

	public void editarproveedor(Integer idproveedor, String nombreproveedor, String direccionproveedor,
			String telefonoproveedor, String correoproveedor) throws Exception {
		TabInvProveedor p = findproveedorByID(idproveedor);
		if (p == null)
			throw new Exception("No existe el proveedor especificado.");
		p.setNombreproveedor(nombreproveedor);		
		p.setDireccionproveedor(direccionproveedor);
		p.setTelefonoproveedor(telefonoproveedor);
		p.setCorreoproveedor(correoproveedor);
		em.merge(p);
	}

	public List<TabInvProveedor> findAllProveedor() {
		Query q;
		List<TabInvProveedor> listado;
		String sentenciaSQL;
		sentenciaSQL = "SELECT c FROM TabInvProveedor c ORDER BY c.nombreproveedorr";
		q = em.createQuery(sentenciaSQL);
		listado = q.getResultList();
		return listado;
	}

}

