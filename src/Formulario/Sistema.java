package Formulario;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import java.sql.*;

public class Sistema extends JFrame {
	Conexion conn = new Conexion();
	private JTable table;
	private JTextField txtUsuario1;
	private JTextField txtTelf;
	private JTextField txtCorreo;
	private void limpiar () {
		txtUsuario1.setText("");
		txtTelf.setText("");
		txtCorreo.setText("");
	}
	public void cargarTabla () {
		DefaultTableModel mt = (DefaultTableModel) table.getModel();
		mt.setRowCount(0);
		PreparedStatement ps;
		ResultSet rs;
		ResultSetMetaData rsmd;
		int columna;
		
		try {
			Connection con = conn.conectar();
			ps = con.prepareStatement("SELECT Nombre, Apellido, Telefono, Correo, Usuario, Contraseña, ComprobarContra FROM Usuario");
			rs= ps.executeQuery();
			rsmd = rs.getMetaData();
			columna = rsmd.getColumnCount();
			
			while(rs.next()) {
				Object [] fila = new Object [columna];
				for (int indice=0; indice < columna; indice++) {
					fila [indice] = rs.getObject(indice + 1);
				}
				mt.addRow(fila);
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.toString());
		}
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Sistema frame = new Sistema();
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
	public Sistema() {
		getContentPane().setBackground(Color.LIGHT_GRAY);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 909, 597);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 69, 543, 369);
		JButton btnCerrarSesion = new JButton("Cerrar Sesion");
		btnCerrarSesion.setBounds(554, 499, 153, 32);
		btnCerrarSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Usuario u1 = new Usuario();
				u1.setVisible(true);
				dispose();
			}
		});
		
		btnCerrarSesion.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		
		JButton btnActulizar = new JButton("Actulizar");
		btnActulizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String Telefono = txtTelf.getText();
				String Correo = txtCorreo.getText();
				String Usuario = txtUsuario1.getText();
				try {
				Connection con = conn.conectar();
				PreparedStatement ps = con.prepareStatement("Update Usuario set Telefono=?, Correo=? where Usuario=?");
				ps.setString(1, Telefono);
				ps.setString(2, Correo);
				ps.setString(3, Usuario);
				ps.executeUpdate();
				JOptionPane.showMessageDialog(null,"Registro actualizado");
				limpiar();
				cargarTabla();
				} catch (SQLException e2) {
					JOptionPane.showMessageDialog(null,e.toString());
					}
			}
		});
		btnActulizar.setBounds(92, 499, 153, 32);
		btnActulizar.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				String Usuario = txtUsuario1.getText();
				try {
				Connection con = conn.conectar();
				PreparedStatement ps = con.prepareStatement("DELETE FROM Usuario WHERE Usuario=?");
				ps.setString(1, Usuario);
				ps.executeUpdate();
				JOptionPane.showMessageDialog(null,"Registro eliminado");
				limpiar();
				cargarTabla();
				} catch (SQLException e2) {
					JOptionPane.showMessageDialog(null,e.toString());
					}
			}
		});
		btnEliminar.setBounds(315, 499, 153, 32);
		btnEliminar.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		
		JLabel lblNewLabel = new JLabel("Usuario:");
		lblNewLabel.setBounds(244, 301, 61, -159);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		txtUsuario1 = new JTextField();
		txtUsuario1.setBounds(680, 123, 159, 19);
		txtUsuario1.setColumns(10);
		getContentPane().setLayout(null);
		
		table = new JTable(	);
		table.setModel(new DefaultTableModel(

			new Object[][] {
			},
			new String[] {
				"Nombre", "Apellido", "Telefono", "Correo", "Usuario", "Contrase\u00F1a", "Comprobar Contrase\u00F1a"
			}
		) {
			Class[] columnTypes = new Class[] {
				Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			
		});
		scrollPane.setViewportView(table);
		getContentPane().add(scrollPane);
		getContentPane().add(btnCerrarSesion);
		getContentPane().add(btnActulizar);
		getContentPane().add(btnEliminar);
		getContentPane().add(lblNewLabel);
		getContentPane().add(txtUsuario1);
		
		JLabel lblNewLabel_1 = new JLabel("Usuario:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblNewLabel_1.setBounds(583, 123, 78, 19);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Telefono:");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblNewLabel_1_1.setBounds(583, 211, 78, 19);
		getContentPane().add(lblNewLabel_1_1);
		
		txtTelf = new JTextField();
		txtTelf.setColumns(10);
		txtTelf.setBounds(680, 213, 159, 19);
		getContentPane().add(txtTelf);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Correo:");
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblNewLabel_1_1_1.setBounds(583, 278, 78, 19);
		getContentPane().add(lblNewLabel_1_1_1);
		
		txtCorreo = new JTextField();
		txtCorreo.setColumns(10);
		txtCorreo.setBounds(680, 280, 159, 19);
		getContentPane().add(txtCorreo);
		
		JLabel lblNewLabel_2 = new JLabel("Datos para actualizar");
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 16));
		lblNewLabel_2.setBounds(625, 161, 199, 20);
		getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_2_1 = new JLabel("Datos de los usuarios");
		lblNewLabel_2_1.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 28));
		lblNewLabel_2_1.setBounds(21, 21, 330, 20);
		getContentPane().add(lblNewLabel_2_1);
		
		cargarTabla();	
		
	}
}
