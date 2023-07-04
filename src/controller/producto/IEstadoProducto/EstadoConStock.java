package controller.producto.IEstadoProducto;

import java.io.Serializable;

import controller.producto.Producto;
import controller.venta.Venta;

public class EstadoConStock extends IEstadoStockProducto implements Serializable {
	
	// Constructor
	public EstadoConStock(int cantStock, int minStock) {
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
		cantidadStock = cantidad;
	}

	public void setStockMinimo(int cantidad) {
		stockMinimo = cantidad;
		if (stockMinimo > cantidadStock) {
			producto.setState(new EstadoConStockMinimo(cantidadStock, stockMinimo));
		}
	}

	public void setProducto(Producto p) {
		producto = p;
	}
	
	// Methods
	public String listarProducto() {
		return "ID: " + formatoCodigo(Integer.toString(producto.getCodigo())) + " | " + producto.getNombre() + " | $" + producto.getPrecioUnitario() + " | Stock: " + cantidadStock + " |Stock minimo: " + stockMinimo;
	}

	public void agregarStock(int cantidad) {
		cantidadStock += cantidad;
	}

	public void restarStock(int cantidad) {
		cantidadStock -= cantidad;
		if (cantidadStock == 0) {
			producto.setState(new EstadoSinStock(cantidadStock, stockMinimo));
		} else if (cantidadStock <= stockMinimo) {
			producto.setState(new EstadoConStockMinimo(cantidadStock, stockMinimo));
		}
	}

	public String agregarProductoAVenta(Venta v, int cant) {
		if (cantidadStock >= cant) {
			v.agregarProducto(producto, cant);
			restarStock(cant);
			return "Producto agregado!";
		}else {
			return "No hay suficiente stock para el pedido";
		}
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
