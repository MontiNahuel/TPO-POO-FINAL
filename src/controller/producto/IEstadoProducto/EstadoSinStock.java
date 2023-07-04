package controller.producto.IEstadoProducto;

import java.io.Serializable;

import controller.producto.Producto;
import controller.venta.Venta;

public class EstadoSinStock extends IEstadoStockProducto implements Serializable{
	
	
	// Constructor
	public EstadoSinStock(int cantStock, int minStock) {
		stockMinimo = minStock;
		cantidadStock = cantStock;
	}
	
	// Getters
	public int getCantidadStock() {
		return cantidadStock;
	}

	public int getStockMinimo() {
		return stockMinimo;
	}
	
	// Setters
	public void setCantidadStock(int cantidad) {
		this.cantidadStock = cantidad;
	}

	public void setStockMinimo(int cantidad) {
		this.stockMinimo = cantidad;
	}
	
	public void setProducto(Producto p) {
		producto = p;
	}
	
	// Methods
	public String listarProducto() {
		return "ID: " + formatoCodigo(Integer.toString(producto.getCodigo())) + " | " + producto.getNombre() + " | $" + producto.getPrecioUnitario() + " | Stock: " + cantidadStock + " | Stock minimo: " + stockMinimo + " | Sin Stock";
	}

	public void agregarStock(int cantidad) {
		this.cantidadStock =+ cantidad;
		if (cantidadStock > stockMinimo) {
			producto.setState(new EstadoConStock(cantidadStock, stockMinimo));
		} else if (cantidadStock <= stockMinimo && cantidadStock != 0) {
			producto.setState(new EstadoConStockMinimo(cantidadStock, stockMinimo));
		}
	}

	public void restarStock(int cantidad) {
		// Nada
	}

	public String agregarProductoAVenta(Venta v, int cant) {
		return "El producto no posee stock";
	}
	
	protected String formatoCodigo(String s) {
		if (s.length() < 5) {
			for (int i = s.length(); i < 5; i++) {
				s = "0" + s;
			}
		}
		return s;
	}
}
