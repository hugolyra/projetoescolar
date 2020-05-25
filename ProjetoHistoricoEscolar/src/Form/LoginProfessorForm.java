package Form;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import java.awt.Toolkit;
public class LoginProfessorForm extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginProfessorForm frame = new LoginProfessorForm();
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
	public LoginProfessorForm() {
		setTitle("Faculdade Matriz");
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage("E:\\Documentos de LeO\\Cursos\\Faculdade de S.I\\4\u00B0 Per\u00EDodo\\Cadeira (Projeto de Banco de Dados)\\Imagens do Projeto de BD\\Logo FaculdadeMenor.png"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("E:\\Documentos de LeO\\Cursos\\Faculdade de S.I\\4\u00B0 Per\u00EDodo\\Cadeira (Projeto de Banco de Dados)\\Imagens do Projeto de BD\\Logo FaculdadeMenor.png"));
		
		JLabel lblFaculdadeMatriz = new JLabel("Faculdade Matriz");
		lblFaculdadeMatriz.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		JLabel lblLogin = new JLabel("Login:");
		lblLogin.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		textField = new JTextField();
		textField.setColumns(10);
		
		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		passwordField = new JPasswordField();
		
		JLabel lblProfessor = new JLabel("Professor");
		lblProfessor.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null, "Operação Cancelada");
				textField.setText("");
				passwordField.setText("");
			}

			private void dispose() {
				
			}
		});
		btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnCancelar.setIcon(new ImageIcon("E:\\Documentos de LeO\\Cursos\\Faculdade de S.I\\4\u00B0 Per\u00EDodo\\Cadeira (Projeto de Banco de Dados)\\Imagens do Projeto de BD\\Errado.png"));
		
		JButton btnAcessar = new JButton("Acessar");
		btnAcessar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (textField.getText().equals("adm") && (passwordField.getText().equals("123"))){
					JOptionPane.showMessageDialog(null, "Acesso Concedido");
					//new PainelSecretariaForm().setVisible(true);
					this.dispose();
				}else{
					JOptionPane.showMessageDialog(null, "Acesso Negado");
					textField.setText("");
					passwordField.setText("");
				}
			}

			private void dispose() {
			
			}
		});
		btnAcessar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnAcessar.setIcon(new ImageIcon("E:\\Documentos de LeO\\Cursos\\Faculdade de S.I\\4\u00B0 Per\u00EDodo\\Cadeira (Projeto de Banco de Dados)\\Imagens do Projeto de BD\\Correto.png"));
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new TelaPrincipalForm().setVisible(true);
				this.dispose();
			}

			private void dispose() {
				
			}
		});
		btnVoltar.setIcon(new ImageIcon("E:\\Documentos de LeO\\Cursos\\Faculdade de S.I\\4\u00B0 Per\u00EDodo\\Cadeira (Projeto de Banco de Dados)\\Imagens do Projeto de BD\\VoltarPNG.png"));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnVoltar)
					.addPreferredGap(ComponentPlacement.RELATED, 114, Short.MAX_VALUE)
					.addComponent(lblNewLabel)
					.addGap(203))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(203, Short.MAX_VALUE)
					.addComponent(lblFaculdadeMatriz)
					.addGap(192))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(193, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblSenha)
							.addGap(18))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblLogin, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)))
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addComponent(passwordField)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE))
					.addGap(182))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(238)
					.addComponent(lblProfessor, GroupLayout.DEFAULT_SIZE, 69, Short.MAX_VALUE)
					.addGap(227))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(144, Short.MAX_VALUE)
					.addComponent(btnAcessar, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)
					.addGap(35)
					.addComponent(btnCancelar)
					.addGap(133))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
					.addGap(9)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(btnVoltar)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblFaculdadeMatriz)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblProfessor)
							.addGap(38)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblLogin))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblSenha))
							.addGap(20)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnCancelar)
								.addComponent(btnAcessar, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap())
		);
		gl_contentPane.linkSize(SwingConstants.HORIZONTAL, new Component[] {btnCancelar, btnAcessar});
		contentPane.setLayout(gl_contentPane);
	}
}
