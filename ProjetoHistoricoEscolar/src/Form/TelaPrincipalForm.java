package Form;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import DAO.DBConnection;

public class TelaPrincipalForm extends JFrame {

	private JPanel Painel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DBConnection.createTables();
					TelaPrincipalForm frame = new TelaPrincipalForm();
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
	public TelaPrincipalForm() {
		setTitle("Faculdade Matriz");
		setIconImage(Toolkit.getDefaultToolkit().getImage(TelaPrincipalForm.class.getResource("/Imagens/logofaculdade.png")));
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 400);
		Painel = new JPanel();
		Painel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(Painel);
		
		JLabel lblImagem = new JLabel("");
		lblImagem.setIcon(new ImageIcon(TelaPrincipalForm.class.getResource("/Imagens/logofaculdade.png")));
		
		JLabel lblFaculdadeMatriz = new JLabel("Faculdade Matriz");
		lblFaculdadeMatriz.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		JLabel lblLogin = new JLabel("Login");
		lblLogin.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JButton btnAluno = new JButton("Aluno");
		btnAluno.setIcon(new ImageIcon(TelaPrincipalForm.class.getResource("/Imagens/student.png")));
		btnAluno.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					new LoginAlunoForm().setVisible(true);
				} catch (HeadlessException e) {
					JOptionPane.showMessageDialog(null, "Falha ao ir para Login Aluno");
					e.printStackTrace();
				}
				this.dispose();
			}

			private void dispose() {
				// TODO Auto-generated method stub
				
			}
		});
		btnAluno.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		// DESABILITANDO BOTÃO ALUNO
		//btnAluno.setEnabled(false);
		
		JButton btnProfessor = new JButton("Professor");
		btnProfessor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					new LoginProfessorForm().setVisible(true);
				} catch (HeadlessException e1) {
					JOptionPane.showMessageDialog(null, "Falha ao ir para Login Professor");
					e1.printStackTrace();
				}
				this.dispose();
			}

			private void dispose() {
				// TODO Auto-generated method stub
				
			}
		});
		btnProfessor.setIcon(new ImageIcon(TelaPrincipalForm.class.getResource("/Imagens/teacher.png")));
		btnProfessor.setFont(new Font("Tahoma", Font.PLAIN, 14));
		   
		
		// DESABILITANDO BOTÃO PROFESSOR
	   // btnProfessor.setEnabled(false);
		
		JButton btnSecretaria = new JButton("Secretaria");
		btnSecretaria.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					new LoginSecretariaForm().setVisible(true);
				} catch (HeadlessException e1) {
					JOptionPane.showMessageDialog(null, "Falha ao ir para Login Secretaria");
					e1.printStackTrace();
				}
				this.dispose();
			}

			private void dispose() {
				// TODO Auto-generated method stub
				
			}
		});
		
		// DESABILITANDO BOTÃO SECRETARIA
		btnSecretaria.setEnabled(true);
		btnSecretaria.setIcon(new ImageIcon(TelaPrincipalForm.class.getResource("/Imagens/college.png")));
		btnSecretaria.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GroupLayout gl_Painel = new GroupLayout(Painel);
		gl_Painel.setHorizontalGroup(
			gl_Painel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_Painel.createSequentialGroup()
					.addGap(203)
					.addComponent(lblFaculdadeMatriz)
					.addContainerGap(192, Short.MAX_VALUE))
				.addGroup(gl_Painel.createSequentialGroup()
					.addContainerGap(213, Short.MAX_VALUE)
					.addComponent(lblImagem)
					.addGap(203))
				.addGroup(gl_Painel.createSequentialGroup()
					.addContainerGap(253, Short.MAX_VALUE)
					.addComponent(lblLogin)
					.addGap(243))
				.addGroup(gl_Painel.createSequentialGroup()
					.addContainerGap(38, Short.MAX_VALUE)
					.addComponent(btnAluno)
					.addGap(43)
					.addComponent(btnProfessor)
					.addGap(44)
					.addComponent(btnSecretaria)
					.addGap(28))
		);
		gl_Painel.setVerticalGroup(
			gl_Painel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_Painel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblImagem)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblFaculdadeMatriz)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblLogin)
					.addPreferredGap(ComponentPlacement.RELATED, 58, Short.MAX_VALUE)
					.addGroup(gl_Painel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnProfessor)
						.addComponent(btnAluno)
						.addComponent(btnSecretaria))
					.addGap(43))
		);
		gl_Painel.linkSize(SwingConstants.VERTICAL, new Component[] {btnSecretaria, btnAluno, btnProfessor});
		Painel.setLayout(gl_Painel);
	}
}
