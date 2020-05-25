package Form;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Toolkit;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.LayoutStyle.ComponentPlacement;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.SwingConstants;

import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.text.ParseException;

public class PainelSecretariaForm extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PainelSecretariaForm frame = new PainelSecretariaForm();
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
	public PainelSecretariaForm() {
		setTitle("Faculdade Matriz");
		setIconImage(Toolkit.getDefaultToolkit().getImage(PainelSecretariaForm.class.getResource("/Imagens/logofaculdade.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(PainelSecretariaForm.class.getResource("/Imagens/logofaculdade.png")));
		
		JLabel lblFaculdadeMatriz = new JLabel("Faculdade Matriz");
		lblFaculdadeMatriz.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		JLabel lblSecretaria = new JLabel("Secretaria");
		lblSecretaria.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JButton btnNewButton_1 = new JButton("Curso");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new PainelCursoForm().setVisible(true);
				this.dispose();
			}

			private void dispose() {
				
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JButton btnNewButton_2 = new JButton("Professor");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					new PainelProfessorForm().setVisible(true);
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
				this.dispose();
			}

			private void dispose() {
				
			}
		});
		btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JButton btnDisciplina = new JButton("Disciplina");
		btnDisciplina.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					new PainelDisciplinaForm().setVisible(true);
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
				this.dispose();
			}

			private void dispose() {
				
			}
		});
		btnDisciplina.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JButton btnNotas = new JButton("Notas");
		btnNotas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					new PainelNotasForm().setVisible(true);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				this.dispose();
			}

			private void dispose() {
				
			}
		});
		btnNotas.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JButton btnNewButton_3 = new JButton("Aluno");
		btnNewButton_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JButton btnCursa = new JButton("Cursa");
		btnCursa.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new LoginSecretariaForm().setVisible(true);
				this.dispose();
			}

			private void dispose() {
				
			}
		});
		btnVoltar.setIcon(new ImageIcon(PainelSecretariaForm.class.getResource("/Imagens/back.png")));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addComponent(btnVoltar)
							.addPreferredGap(ComponentPlacement.RELATED, 109, Short.MAX_VALUE)
							.addComponent(lblNewLabel)
							.addGap(198))
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addComponent(lblFaculdadeMatriz)
							.addGap(187))))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(233)
					.addComponent(lblSecretaria)
					.addContainerGap(222, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(129)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(btnNewButton_1)
						.addComponent(btnNewButton_2))
					.addGap(34)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(btnNewButton_3)
						.addComponent(btnNotas)
						.addComponent(btnDisciplina))
					.addContainerGap(119, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(206)
					.addComponent(btnCursa)
					.addContainerGap(197, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnVoltar)
							.addContainerGap())
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblNewLabel)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblFaculdadeMatriz)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblSecretaria)
							.addPreferredGap(ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(btnNewButton_3))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnNewButton_1)
								.addComponent(btnDisciplina))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnNewButton_2)
								.addComponent(btnNotas))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnCursa))))
		);
		gl_contentPane.linkSize(SwingConstants.HORIZONTAL, new Component[] {btnNewButton_1, btnNewButton_2, btnDisciplina, btnNotas, btnNewButton_3, btnCursa});
		contentPane.setLayout(gl_contentPane);
	}
}
