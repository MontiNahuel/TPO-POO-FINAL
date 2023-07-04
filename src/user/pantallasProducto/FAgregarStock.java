package user.pantallasProducto;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.producto.Producto;

import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FAgregarStock extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private static Producto p;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			FAgregarStock dialog = new FAgregarStock(p);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public FAgregarStock(Producto p) {
		FAgregarStock.p = p;
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			textField = new JTextField();
			textField.setBounds(175, 61, 86, 20);
			contentPanel.add(textField);
			textField.setColumns(10);
		}
		{
			JLabel lblNewLabel = new JLabel("Stock a Agregar:");
			lblNewLabel.setBounds(64, 64, 101, 14);
			contentPanel.add(lblNewLabel);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						aceptar();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	
	private void aceptar() {
		if (Integer.parseInt(textField.getText()) > 0) {
			p.agregarStock(Integer.parseInt(textField.getText()));
			this.setVisible(false);
		} else {
			FPantallaInvalida invalida = new FPantallaInvalida("El agregado de stock debe ser un valor positivo");
			invalida.setModal(true);
			invalida.setVisible(true);
		}
	}
}
