package controller.producto.IEstadoProducto;

import java.io.Serializable;

import controller.producto.Producto;
import controller.venta.Venta;

public abstract class IEstadoStockProducto implements Serializable {
	int cantidadStock;
	int stockMinimo;
	Producto producto;
	
	// Getters
	public abstract int getCantidadStock();
	public abstract int getStockMinimo();
	
	// Setters
	public abstract void setCantidadStock(int cantidad);
	public abstract void setStockMinimo(int cantidad);
	public abstract void setProducto(Producto p);
	// Methods
	public abstract String listarProducto();
	
	public abstract void agregarStock(int cantidad);
	
	public abstract void restarStock(int cantidad);
	
	public abstract String agregarProductoAVenta(Venta v, int cant);
	
	protected abstract String formatoCodigo(String s);
}
