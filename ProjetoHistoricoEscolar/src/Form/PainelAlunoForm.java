package Form;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import net.miginfocom.swing.MigLayout;
import Table.AlunoTableModel;
import Controller.HistoricoEscolarController;
import Entity.Aluno;

import java.awt.Toolkit;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class PainelAlunoForm extends JFrame {

	private JLabel lblNome, lblCPF, lblDtNascimento, lblSexo, lblEmail, lblTelefone;
	private JTextField txtNome, txtEmail;
	private JFormattedTextField txtCpf, txtDtNascimento, txtTelefone;
	private JPanel panelAdd, panelTable, panelButtons;
	private JButton btnNew, btnSave, btnUpdate, btnRemove, btnCancel, btnVoltar;
	private JComboBox cbSexo;
	private JTable table;
	private JScrollPane scrollPane;
	
	private List<Aluno> alunoList;
	private Long idAluno;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PainelAlunoForm frame = new PainelAlunoForm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws ParseException 
	 */
	public PainelAlunoForm() throws ParseException {
		setTitle("Faculdade Matriz");
		setIconImage(Toolkit.getDefaultToolkit().getImage(PainelAlunoForm.class.getResource("/Imagens/logofaculdade.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 400);
		JPanel panel = new JPanel();
		setContentPane(panel);
		getContentPane().setLayout(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panelAdd = new JPanel (new MigLayout("", "[][][][][][][][][][]", "[][][]"));
		panelAdd.setBorder(BorderFactory.createTitledBorder("Adicionar Departamentos"));
		panelAdd.setBounds(5, 39, 540, 104);
		
		MaskFormatter mask = new MaskFormatter("(##) ####-####");
		mask.setPlaceholderCharacter('_');
		
		MaskFormatter mask2 = new MaskFormatter("###.###.###-##");
		mask2.setPlaceholderCharacter('_');
		
		MaskFormatter mask3 = new MaskFormatter("##/##/####");
		mask3.setPlaceholderCharacter('_');
		
		lblNome = new JLabel ("Nome");
		txtNome = new JTextFieldSomenteLetras(50);
		txtNome.setColumns(75);
		
		panelAdd.add(lblNome, "cell 0 0");
		panelAdd.add(txtNome, "cell 1 0 7 1, growx");
		
		panelButtons = new JPanel (new MigLayout("", "[][][][][][][]", "[]"));
		panelButtons.setBorder(BorderFactory.createEtchedBorder());
		panelButtons.setBounds(5, 154, 540, 40);
		
		ClassLoader loader = getClass().getClassLoader();
		btnNew = new JButton("Novo", new ImageIcon(loader.getResource("imagens/new.png")));
		btnCancel = new JButton("Cancelar",new ImageIcon(loader.getResource("imagens/cancel.png")));
		
		panelButtons.add(btnNew, "cell 0 0");
		panelButtons.add(btnCancel, "cell 1 0");
		
		panelTable = new JPanel(new MigLayout("", "[]", "[]"));
		panelTable.setBorder(BorderFactory.createTitledBorder("Lista de Departamentos"));
		panelTable.setBounds(5, 205, 539, 185);
		
		table = new JTable();
		
		scrollPane = new JScrollPane(table);
		
		panelTable.add(scrollPane, "cell 0 0");
		
		refreshTable();
		enableFields(false);
		
		getContentPane().add(panelAdd);
		lblDtNascimento = new JLabel ("Data de Nascimento");
		
		panelAdd.add(lblDtNascimento, "cell 8 0");
		txtDtNascimento = new JFormattedTextField(mask3);
		txtDtNascimento.setColumns(5);
		panelAdd.add(txtDtNascimento, "cell 9 0");
		lblCPF = new JLabel ("CPF");
		
		panelAdd.add(lblCPF, "cell 0 1");
		txtCpf = new JFormattedTextField(mask2);
		panelAdd.add(txtCpf, "cell 7 1");
		
		lblTelefone = new JLabel("Telefone");
		
		panelAdd.add(lblTelefone, "cell 8 1");
		txtTelefone = new JFormattedTextField(mask);
		txtTelefone.setColumns(10);
		panelAdd.add(txtTelefone, "flowx,cell 9 1");
		
		lblEmail = new JLabel("Email");
		
		panelAdd.add(lblEmail, "cell 0 2");
		lblSexo = new JLabel ("Sexo");
		
				panelAdd.add(lblSexo, "cell 8 2");
				
				cbSexo = new JComboBox();
				cbSexo.setModel(new DefaultComboBoxModel(new String[] {"Masculino", "Feminino"}));
				
				panelAdd.add(cbSexo, "cell 9 2");
				txtEmail = new JTextField(50);
				panelAdd.add(txtEmail, "cell 7 2");

		getContentPane().add(panelButtons);
		btnSave = new JButton("Salvar",new ImageIcon(loader.getResource("imagens/save.png")));
		panelButtons.add(btnSave, "cell 3 0,gapx unrelated");
		
		btnSave.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				onSaveAlunos();
			}
		});
		btnUpdate = new JButton("Editar",new ImageIcon(loader.getResource("imagens/edit.png")));
		panelButtons.add(btnUpdate, "cell 5 0,gapx unrelated");
		
		btnUpdate.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				onAlterarAlunos();
			}
		});
		btnRemove = new JButton("Deletar",new ImageIcon(loader.getResource("imagens/trash.png")));
		panelButtons.add(btnRemove, "cell 6 0");
		
		btnRemove.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				onRemoverAlunos();
			}
		});
		getContentPane().add(panelTable);
		
		btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
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
		btnVoltar.setIcon(new ImageIcon(PainelAlunoForm.class.getResource("/Imagens/back.png")));
		btnVoltar.setBounds(5, 11, 109, 23);
		panel.add(btnVoltar);
		setMinimumSize(new Dimension(560,420));
		setVisible(true);
		
		btnCancel.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				onCancelarAlunos();
			}
		});
		
		btnNew.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				onNovosAlunos();
			}
		});
		
	}
	
	private void onAlterarAlunos() {
        int rowIndex = table.getSelectedRow();
		
		if (rowIndex == -1){
			JOptionPane.showMessageDialog(this, "Selecione o aluno a ser alterado");
			return;
		}
		
		Aluno aluno = new AlunoTableModel(alunoList).get(rowIndex);
		
		idAluno = aluno.getMatricula();
		
		txtNome.setText(aluno.getNome());
		txtCpf.setText(aluno.getCpf());
		//txtDtNascimento.setText(Date.parse(aluno.getDtNascimento());
		cbSexo.setToolTipText(aluno.getSexo());
		//txtEmail.setText(aluno.getEmail());
		//txtTelefone.setText(aluno.getTelefone());
		
		enableFields(true);
	}

	private void onRemoverAlunos() {
		int rowIndex = table.getSelectedRow();
		
		if (rowIndex == -1){
			JOptionPane.showMessageDialog(this, "Selecione o aluno a ser removido");
			return;
		}
		
		Aluno aluno = new AlunoTableModel(alunoList).get(rowIndex);
		
		int confirm = JOptionPane.showConfirmDialog(this, "Confirmar Exclusão?", "Excluir Aluno", JOptionPane.YES_NO_OPTION);
		
		if (confirm != 0){
			return;
		}
		
		int result = new HistoricoEscolarController().removerAluno(aluno.getMatricula());
		
		if (result == 1){
			JOptionPane.showMessageDialog(this, "Valor removido com sucesso!");
			refreshTable();
		} else {
			JOptionPane.showMessageDialog(this, "Valor não removido, Teste Novamente!");
		}
	}

	private void onNovosAlunos() {
		enableFields(true);
	}

	private void onSaveAlunos() {
		Aluno aluno = new Aluno();
		
		if (txtNome.getText().length() > 0 && txtCpf.getText().length() > 0 && cbSexo.getToolTipText().length() > 0){
			aluno.setNome(txtNome.getText());
			aluno.setCpf(txtCpf.getText());
			//aluno.setDtNascimento(txtDtNascimento.getText());
			aluno.setSexo(cbSexo.getToolTipText());
			//aluno.setEmail(txtEmail.getText());
			//aluno.setTelefone(txtTelefone.getText());
		} else {
			JOptionPane.showMessageDialog(this, "Todos os Campos são Obrigatórios!");
			return;
		}
		
		int result = 0;
		if(idAluno == null){
			result = new HistoricoEscolarController().salvarAluno(aluno);
		} else {
			aluno.setMatricula(idAluno);
			result = new HistoricoEscolarController().atualizarAluno(aluno);
			idAluno = null;
		}
		
		if (result == 1){
			JOptionPane.showMessageDialog(this, "Valor inserido com sucesso!");
			enableFields(false);
			onCancelarAlunos();
			refreshTable();
		} else {
			JOptionPane.showMessageDialog(this, "Valor não inserido, Teste Novamente!");
		}
	}
	
	private void onCancelarAlunos() {
		txtNome.setText("");
		txtCpf.setText("");
		txtDtNascimento.setText("");
		//cbSexo.setToolTipText("");
		txtEmail.setText("");
		txtTelefone.setText("");
		enableFields(false);
	}
	
	private void enableFields(boolean b) {
		//txtCpf.setEnabled(b);
		//txtDtNascimento.setEnabled(b);
		//txtTelefone.setEnabled(b);
	}

	private void refreshTable(){
		alunoList = new HistoricoEscolarController().EncontreAllAluno();
		if (alunoList != null){
			table.setModel(new AlunoTableModel(alunoList));
	    }
	}
}


