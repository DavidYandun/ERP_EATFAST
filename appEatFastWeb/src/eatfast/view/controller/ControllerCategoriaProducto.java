package eatfast.view.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import eatfast.model.entities.TabInvCategoriaProducto;
import eatfast.model.manager.ManagerCategoriaProducto;
import eatfast.view.util.JSFUtil;


@ManagedBean
@SessionScoped
public class ControllerCategoriaProducto {
		private int idcategoria;
		private String nombrecategoria;
		private String descripcioncategoria;
		private List<TabInvCategoriaProducto> lista;
		
		@EJB
		ManagerCategoriaProducto managerCategoriaProducto;
		
		@PostConstruct
		public void iniciar() {
			lista = managerCategoriaProducto.findAllCategorias();
			nombrecategoria = "";
			descripcioncategoria = "";
		}
		public void agregarCategoriaProducto() {
			try {
				managerCategoriaProducto.agregarCategoriaProducto(nombrecategoria, descripcioncategoria);
				lista = managerCategoriaProducto.findAllCategorias();
				JSFUtil.crearMensajeInfo("Categoria de producto creada exitosmente.");				
			}catch (Exception e) {
				JSFUtil.crearMensajeError(e.getMessage());
				e.printStackTrace();
			}
			nombrecategoria = "";
			descripcioncategoria = "";
		}
		public void cargarCategoriaProducto(TabInvCategoriaProducto categoriaProducto) {
			idcategoria =categoriaProducto.getIdcategoria();
			nombrecategoria = categoriaProducto.getNombrecategoria();
			descripcioncategoria =categoriaProducto.getDescripcioncategoria();
		}
		public void editarCategoriaProducto() {
			try {
				managerCategoriaProducto.editarCategoria(idcategoria, nombrecategoria, descripcioncategoria);
				lista = managerCategoriaProducto.findAllCategorias();
				JSFUtil.crearMensajeInfo("Categoria producto editado correctamente.");				
			}catch (Exception e) {
				JSFUtil.crearMensajeError(e.getMessage());
				e.printStackTrace();
			}
			nombrecategoria = "";
			descripcioncategoria = "";
		}
		
		
		public int getIdcategoria() {
			return idcategoria;
		}
		public void setIdcategoria(int idcategoria) {
			this.idcategoria = idcategoria;
		}
		public String getNombrecategoria() {
			return nombrecategoria;
		}
		public void setNombrecategoria(String nombrecategoria) {
			this.nombrecategoria = nombrecategoria;
		}
		public String getDescripcioncategoria() {
			return descripcioncategoria;
		}
		public void setDescripcioncategoria(String descripcioncategoria) {
			this.descripcioncategoria = descripcioncategoria;
		}
		public List<TabInvCategoriaProducto> getLista() {
			return lista;
		}
		public void setLista(List<TabInvCategoriaProducto> lista) {
			this.lista = lista;
		}

		
		
}
