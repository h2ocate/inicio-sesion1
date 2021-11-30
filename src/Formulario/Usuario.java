package Formulario;

import java.awt.BorderLayout;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import java.sql.Connection;

public class Usuario extends JFrame {
	Conexion conn = new Conexion();
	private JPanel cpInico;
	private JTextField txtUsuario;
	private JPasswordField pfContraseña;
	private void limpiar () {
		txtUsuario.setText("");
		pfContraseña.setText("");
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Usuario frame = new Usuario();
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
	public Usuario() {
		getContentPane().setBackground(Color.LIGHT_GRAY);
		getContentPane().setLayout(null);
		
		JLabel lbNombre = new JLabel("Usuario:");
		lbNombre.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lbNombre.setBounds(27, 130, 53, 13);
		getContentPane().add(lbNombre);
		
		JLabel lblContrasea = new JLabel("Contrase\u00F1a:");
		lblContrasea.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblContrasea.setBounds(27, 190, 80, 13);
		getContentPane().add(lblContrasea);
		
		txtUsuario = new JTextField();
		txtUsuario.setBounds(102, 128, 148, 19);
		getContentPane().add(txtUsuario);
		txtUsuario.setColumns(10);
		
		pfContraseña = new JPasswordField();
		pfContraseña.setBounds(102, 188, 148, 19);
		getContentPane().add(pfContraseña);
		
		JLabel lblInicioDeSesion = new JLabel("Inicio de sesion");
		lblInicioDeSesion.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 18));
		lblInicioDeSesion.setBounds(185, 28, 135, 13);
		getContentPane().add(lblInicioDeSesion);
		
		JButton btnRegistro = new JButton("Registrarse");
		btnRegistro.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		btnRegistro.setBounds(98, 279, 119, 36);
		getContentPane().add(btnRegistro);
		
		JButton btnIniciarSesion = new JButton("Iniciar sesion");
		btnIniciarSesion.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		btnIniciarSesion.setBounds(271, 279, 135, 36);
		getContentPane().add(btnIniciarSesion);
		getContentPane().setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 487, 372);
		cpInico = new JPanel();
		cpInico.setBackground(Color.LIGHT_GRAY);
		cpInico.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(cpInico);
		cpInico.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Usuario:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblNewLabel.setBounds(10, 106, 72, 13);
		cpInico.add(lblNewLabel);
		
		JLabel lblContrasea_1 = new JLabel("Contrase\u00F1a:");
		lblContrasea_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblContrasea_1.setBounds(10, 155, 111, 13);
		cpInico.add(lblContrasea_1);
		
		txtUsuario = new JTextField();
		txtUsuario.setBounds(109, 105, 145, 19);
		cpInico.add(txtUsuario);
		txtUsuario.setColumns(10);
		
		pfContraseña = new JPasswordField();
		pfContraseña.setBounds(109, 154, 145, 19);
		cpInico.add(pfContraseña);
		
		JLabel lblInicioDeSesion_1 = new JLabel("Inicio de sesion");
		lblInicioDeSesion_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		lblInicioDeSesion_1.setBounds(124, 28, 169, 27);
		cpInico.add(lblInicioDeSesion_1);
		
		JButton btnRegistrarse = new JButton("Registrarse");
		btnRegistrarse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Agregar a1 = new Agregar();
				a1.setVisible(true);
				dispose();
			}
		});
		btnRegistrarse.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnRegistrarse.setBounds(76, 235, 111, 27);
		cpInico.add(btnRegistrarse);
		
		JButton btnIniciarSesion_1 = new JButton("Iniciar Sesion");
		btnIniciarSesion_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int resultado = 0;
				try {
					String Usuario = txtUsuario.getText();
					String Contraseña = String.valueOf(pfContraseña.getPassword());
					Connection con = conn.conectar();
					String SQL = ("Select * from Usuario where Usuario ='"+Usuario+"' and Contraseña ='"+Contraseña+"' ");
					PreparedStatement ps = con.prepareStatement(SQL);
					ResultSet rs = ps.executeQuery(SQL);
					if(Usuario.isEmpty()||Contraseña.isEmpty()) {
						JOptionPane.showMessageDialog(null, "debe ingresar su usuario y contraseña,\r\n"
								+ " si no está registrado debe registrarse");
					} else {
						
					if (rs.next()) {
						resultado = 1;
								if (resultado==1) {
									Sistema s1 = new Sistema();
									s1.setVisible(true);
									dispose();
					}else {
						JOptionPane.showMessageDialog(null, "Error al iniciar sesion, vuelva a intentarlo.");
						}
					}
					limpiar();
				}	
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Error al iniciar sesion, vuelva a intentarlo."+ e1.getMessage());

				}
				
			}
		});
		btnIniciarSesion_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnIniciarSesion_1.setBounds(258, 240, 145, 27);
		cpInico.add(btnIniciarSesion_1);
		
	}
}
