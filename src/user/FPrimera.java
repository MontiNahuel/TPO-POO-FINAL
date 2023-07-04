package user;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.catalogo.CatalogoDeProductos;
import controller.producto.Producto;
import controller.venta.RegistroDeVentas;
import controller.venta.Venta;
import user.pantallasProducto.FAgregarStock;
import user.pantallasProducto.FEliminarProducto;
import user.pantallasProducto.FIngresarProducto;
import user.pantallasProducto.FModificarProducto;
import user.pantallasProducto.FPantallaInvalida;
import user.pantallasVenta.FAgregarVenta;
import user.pantallasVenta.FElegirTipoDeVenta;
import user.pantallasVenta.FMasDetallesVenta;
import user.pantallasProducto.masDetallesProducto;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JTable;

public class FPrimera extends JFrame {

	private JPanel contentPane;
	private CatalogoDeProductos catalogo;
	private RegistroDeVentas registroVentas;
	private JList listProductos;
	private JList listRegistros;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FPrimera frame = new FPrimera();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FPrimera() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 829, 609);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Productos");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Agregar Producto");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				agregarProducto();
			}
		});
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Eliminar Producto");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eliminarProducto();
			}
		});
		mnNewMenu.add(mntmNewMenuItem_1);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Productos");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(20, 22, 287, 14);
		contentPane.add(lblNewLabel);
		
		JList listProductos = new JList();
		listProductos.setBounds(20, 47, 724, 177);
		contentPane.add(listProductos);
		
		this.listProductos = listProductos;
		
		JButton btnNewButton = new JButton("Agregar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				agregarProducto();
			}
		});
		btnNewButton.setBounds(20, 235, 89, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Eliminar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eliminarProducto();
			}
		});
		btnNewButton_1.setBounds(119, 235, 89, 23);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Modificar");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modificarProducto();
			}
		});
		btnNewButton_2.setBounds(218, 235, 89, 23);
		contentPane.add(btnNewButton_2);
		
		JLabel lblNewLabel_1 = new JLabel("Ventas");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(20, 290, 284, 14);
		contentPane.add(lblNewLabel_1);
		
		JList listRegistrosVenta = new JList();
		listRegistrosVenta.setBounds(20, 315, 724, 177);
		contentPane.add(listRegistrosVenta);
		
		listRegistros = listRegistrosVenta;
		
		JButton btnNewButton_3 = new JButton("Agregar Stock");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				agregarStock();
			}
		});
		btnNewButton_3.setBounds(317, 235, 150, 23);
		contentPane.add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("Agregar");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				agregarVenta();
			}
		});
		btnNewButton_4.setBounds(20, 503, 89, 23);
		contentPane.add(btnNewButton_4);
		
		JButton btnNewButton_5 = new JButton("Mas Detalles");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				masDetallesProducto();
			}
		});
		btnNewButton_5.setBounds(477, 235, 108, 23);
		contentPane.add(btnNewButton_5);
		
		JButton btnNewButton_5_1 = new JButton("Mas Detalles");
		btnNewButton_5_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				masDetallesVenta();
			}
		});
		btnNewButton_5_1.setBounds(119, 503, 108, 23);
		contentPane.add(btnNewButton_5_1);
		
		this.addWindowListener(new java.awt.event.WindowAdapter() {
			public void windowClosing(java.awt.event.WindowEvent e) {
				if(catalogo.guardarse() && registroVentas.guardarse())
					JOptionPane.showMessageDialog(null, "GUARDADO OK") ;
				else
					JOptionPane.showMessageDialog(null, "ERROR AL GUARDAR");
			}
		});
		
		catalogo = CatalogoDeProductos.recuperarse();
		registroVentas = RegistroDeVentas.recuperarse();
		listProductos.setListData(catalogo.getCatalogoList().toArray());
		listRegistros.setListData(registroVentas.getRegistroVentas().toArray());
	}
	
	private void agregarProducto() {
		FIngresarProducto ingresarProducto = new FIngresarProducto();
		
		ingresarProducto.setModal(true);
		ingresarProducto.setVisible(true);
		
		Producto producto = ingresarProducto.getProducto();
		if (producto != null) { // Por si se usa el boton de cancelar
			boolean existe = catalogo.cargarProducto(producto);
			if (existe) {
				FPantallaInvalida invalidoEliminar = new FPantallaInvalida("El ID ingresado ya existe...");
				invalidoEliminar.setModal(true);
				invalidoEliminar.setVisible(true);
			}
		}
		
		listProductos.setListData(catalogo.getCatalogoList().toArray());
	}
	
	private void eliminarProducto() {
		FEliminarProducto eliminarProducto = new FEliminarProducto();
		
		eliminarProducto.setModal(true);
		eliminarProducto.setVisible(true);
		
		int id = eliminarProducto.getID();
		
		if (id != -1) {
			boolean existe = catalogo.bajaDeProducto(id);
			
			if (existe) {
				listProductos.setListData(catalogo.getCatalogoList().toArray());
			} else {
				FPantallaInvalida invalidoEliminar = new FPantallaInvalida("El ID ingresado no existe...");
				
				invalidoEliminar.setVisible(true);
				invalidoEliminar.setModal(true);
			}
		}
	}
	
	private void modificarProducto() {
		Producto p;
		
		p = (Producto) listProductos.getSelectedValue();
		
		if (p != null) {
			FModificarProducto modificar = new FModificarProducto(p);
			modificar.setVisible(true);
			modificar.setModal(true);
			listProductos.setListData(catalogo.getCatalogoList().toArray());
		}
	}
	
	private void agregarStock() {
		Producto producto;
		producto = (Producto) listProductos.getSelectedValue();
		if (producto != null) {
			FAgregarStock agregarStock = new FAgregarStock(producto);
			agregarStock.setVisible(true);
			agregarStock.setModal(true);
			listProductos.setListData(catalogo.getCatalogoList().toArray());
		}
	}
	
	private void agregarVenta() {
		FElegirTipoDeVenta elegirTipoDeVenta = new FElegirTipoDeVenta();
		elegirTipoDeVenta.setModal(true);
		elegirTipoDeVenta.setVisible(true);
		Venta v = elegirTipoDeVenta.getVentaCreada();
		if (v != null) {
			FAgregarVenta agregarVenta = new FAgregarVenta(catalogo.getCatalogoList().toArray(), registroVentas.getRegistroVentas().toArray(), v);
			agregarVenta.setModal(true);
			agregarVenta.setVisible(true);
			Venta ventaFinal = agregarVenta.getVentaCreadaFinal();
			if (ventaFinal != null && !ventaFinal.getListaVenta().isEmpty()) {
				// Armar validación aca sobre ID de las ventas y en caso de que ya esté en uso, generar FPantalla1Dato pidiendo un nuevo codigo
				registroVentas.registrarVenta(ventaFinal);
				listRegistros.setListData(registroVentas.getRegistroVentas().toArray());
				listProductos.setListData(catalogo.getCatalogoList().toArray());
			}
		
		}
	}
	
	private void masDetallesProducto() {
		Producto producto = (Producto) listProductos.getSelectedValue();
		if (producto != null) {
			masDetallesProducto detalles = new masDetallesProducto(producto);
			detalles.setModal(true);
			detalles.setVisible(true);
		}
	}
	
	private void masDetallesVenta() {
		Venta venta = (Venta) listRegistros.getSelectedValue();
		if (venta != null) {
			FMasDetallesVenta detalles = new FMasDetallesVenta(venta);
			detalles.setModal(true);
			detalles.setVisible(true);
		}
	}
}
