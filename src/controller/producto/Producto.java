package controller.producto;

import java.io.Serializable;

import controller.producto.IEstadoProducto.EstadoSinStock;
import controller.producto.IEstadoProducto.IEstadoStockProducto;
import controller.venta.Venta;

public class Producto implements Serializable{
	
	private int codigo;
    private String descripcion;
    private float precioUnitario;
    private IEstadoStockProducto stateStock;
    public String nombre;
    
    // Constructor
	public Producto(int codigo, String descripcion, String nombre, int precio, int minStock) {
		this.codigo = codigo; // Asignar el codigo del producto solo
		this.setDescripcion(descripcion);
		setState(new EstadoSinStock(0, minStock));
		this.nombre = nombre;
		this.precioUnitario = precio;
	}
	
	// Getters
	public int getCodigo() {return codigo;}
	
	public String getDescripcion() {return descripcion;}
	
	public float getPrecioUnitario() {return precioUnitario;}

    public String getNombre() {return nombre;}
    
    public IEstadoStockProducto getStateStock() {
		return stateStock;
	}
	// Setters
    public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
    
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public void setPrecioUnitario(int precio) {
		precioUnitario = precio;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public void setState(IEstadoStockProducto state) {
		stateStock = state;
		stateStock.setProducto(this);
	}
	
	// Methods
	public String listarProducto() {
		String msg = stateStock.listarProducto();
		return msg;
	}
	
	public String agregarAVenta(Venta v, int cant) {
		String msg = stateStock.agregarProductoAVenta(v, cant); 
		return msg;
	}
	
	public void agregarStock(int cant) {
		stateStock.agregarStock(cant);
	}
	
	public void restarStock(int cant) {
		stateStock.restarStock(cant);
	}
	
	public String toString() {
		return listarProducto();
	}
	
}
