package Formulario;

import java.awt.BorderLayout;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.mysql.cj.exceptions.RSAException;
import com.mysql.cj.xdevapi.PreparableStatement;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Agregar extends JFrame {
	Conexion conn = new Conexion();
	private JPanel cpAgregar;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtCorreo;
	private JTextField txtTelefono;
	private JTextField txtUser;
	private JLabel lblNewLabel_1;
	private JButton btnguardar;
	private JButton btnRegresar;
	private JPasswordField pfContra;
	private JPasswordField pfComproContra;
	private void limpiar () {
		txtNombre.setText("");
		txtApellido.setText("");
		txtTelefono.setText("");
		txtCorreo.setText("");
		txtUser.setText("");
		pfContra.setText("");
		pfComproContra.setText("");
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Agregar frame = new Agregar();
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
	public Agregar() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 483, 367);
		cpAgregar = new JPanel();
		cpAgregar.setBackground(Color.LIGHT_GRAY);
		cpAgregar.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(cpAgregar);
		cpAgregar.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nombre:");
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblNewLabel.setBounds(21, 51, 63, 17);
		cpAgregar.add(lblNewLabel);
		
		JLabel lblApellido = new JLabel("Apellido:");
		lblApellido.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblApellido.setBounds(21, 82, 63, 17);
		cpAgregar.add(lblApellido);
		
		JLabel lblCorreo = new JLabel("Correo: ");
		lblCorreo.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblCorreo.setBounds(21, 136, 63, 17);
		cpAgregar.add(lblCorreo);
		
		JLabel lblTelefono = new JLabel("Telefono:");
		lblTelefono.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblTelefono.setBounds(21, 109, 63, 17);
		cpAgregar.add(lblTelefono);
		
		JLabel lblUsuario = new JLabel("Usuario:");
		lblUsuario.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblUsuario.setBounds(21, 163, 63, 17);
		cpAgregar.add(lblUsuario);
		
		JLabel lblContrasea = new JLabel("Contrase\u00F1a:");
		lblContrasea.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblContrasea.setBounds(21, 190, 121, 17);
		cpAgregar.add(lblContrasea);
		
		JLabel lblComprobarContrasea = new JLabel("Comprobar Contrase\u00F1a:");
		lblComprobarContrasea.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblComprobarContrasea.setBounds(10, 217, 138, 26);
		cpAgregar.add(lblComprobarContrasea);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(94, 51, 138, 19);
		cpAgregar.add(txtNombre);
		txtNombre.setColumns(10);
		
		txtApellido = new JTextField();
		txtApellido.setColumns(10);
		txtApellido.setBounds(94, 82, 138, 19);
		cpAgregar.add(txtApellido);
		
		txtCorreo = new JTextField();
		txtCorreo.setColumns(10);
		txtCorreo.setBounds(94, 136, 138, 19);
		cpAgregar.add(txtCorreo);
		
		txtTelefono = new JTextField();
		txtTelefono.setColumns(10);
		txtTelefono.setBounds(94, 109, 138, 19);
		cpAgregar.add(txtTelefono);
		
		txtUser = new JTextField();
		txtUser.setColumns(10);
		txtUser.setBounds(94, 163, 138, 19);
		cpAgregar.add(txtUser);
		
		lblNewLabel_1 = new JLabel("Registrarse");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 18));
		lblNewLabel_1.setBounds(168, 10, 104, 26);
		cpAgregar.add(lblNewLabel_1);

		btnguardar = new JButton("Guardar");
		btnguardar.addActionListener  (new ActionListener() {	
			public void actionPerformed(ActionEvent e) {
				String Nombre = txtNombre.getText();
				String Apellido = txtApellido.getText();
				String Telefono = txtTelefono.getText();
				String Correo = txtCorreo.getText();
				String Usuario = txtUser.getText();
				String Contraseña = String.valueOf(pfContra.getPassword());
				String ComproContra = String.valueOf(pfComproContra.getPassword());
			
				try {
				Connection con = conn.conectar();

				PreparedStatement ps = con.prepareStatement("INSERT INTO Usuario (Nombre, Apellido, Telefono, Correo, Usuario, Contraseña, ComprobarContra) VALUES (?,?,?,?,?,?,?)");
				ps.setString(1, Nombre);
				ps.setString(2, Apellido);
				ps.setString(3, Telefono);
				ps.setString(4, Correo);
				ps.setString(5, Usuario);
				ps.setString(6, Contraseña);
				ps.setString(7, ComproContra);
				
				if(Nombre.isEmpty()||Apellido.isEmpty()||Telefono.isEmpty()||Correo.isEmpty()||Usuario.isEmpty()||Contraseña.isEmpty()||ComproContra.isEmpty()) {
					JOptionPane.showMessageDialog(null, "debe llenar todos los campos");
				} else {
					if (Contraseña.equals(ComproContra ))  {
					JOptionPane.showMessageDialog(null,"Registro Guardado");
					ps.executeUpdate();
					limpiar();
					} else {
						JOptionPane.showMessageDialog(null,"Las contraseñas no coinciden");
					}
				
				}
			} catch (SQLException e2) {
					JOptionPane.showMessageDialog(null,e.toString());
					}
			}
		}
		);
		btnguardar.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		btnguardar.setBounds(71, 277, 104, 21);
		cpAgregar.add(btnguardar);
		
		btnRegresar = new JButton("Regresar");
		btnRegresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Usuario u1 = new Usuario();
				u1.setVisible(true);
				dispose();
			}
		});
		btnRegresar.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 14));
		btnRegresar.setBounds(225, 277, 110, 20);
		cpAgregar.add(btnRegresar);
		
		pfContra = new JPasswordField();
		pfContra.setBounds(94, 190, 138, 19);
		cpAgregar.add(pfContra);
		
		pfComproContra = new JPasswordField();
		pfComproContra.setBounds(147, 222, 138, 19);
		cpAgregar.add(pfComproContra);
	}
}
