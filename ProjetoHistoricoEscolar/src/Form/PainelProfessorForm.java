package Form;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
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
import javax.swing.text.MaskFormatter;

import net.miginfocom.swing.MigLayout;
import Table.ProfessorTableModel;
import Controller.HistoricoEscolarController;
import Entity.Professor;

public class PainelProfessorForm extends JFrame {

	private JLabel lblCpf, lblNome;
	private JTextField txtNome;
	private JFormattedTextField txtCpf;
	private JPanel panelAdd, panelTable, panelButtons;
	private JButton btnNew, btnSave, btnUpdate, btnRemove, btnCancel, btnVoltar;
	private JTable table;
	private JScrollPane scrollPane;
	
	private List<Professor> professorList;
	private Long idProfessor;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PainelProfessorForm frame = new PainelProfessorForm();
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
	public PainelProfessorForm() throws ParseException {
		setTitle("Faculdade Matriz");
		setIconImage(Toolkit.getDefaultToolkit().getImage(PainelProfessorForm.class.getResource("/Imagens/logofaculdade.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 400);
		JPanel panel = new JPanel();
		setContentPane(panel);
		getContentPane().setLayout(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panelAdd = new JPanel (new MigLayout("", "[][]", "[][][]"));
		panelAdd.setBorder(BorderFactory.createTitledBorder("Adicionar Professores"));
		panelAdd.setBounds(5, 39, 540, 79);
		
		MaskFormatter mask = new MaskFormatter("###.###.###-##");
		mask.setPlaceholderCharacter('_');
		
		lblCpf = new JLabel ("CPF do Professor");
		lblNome = new JLabel ("Nome");
		
		txtNome = new JTextFieldSomenteLetras(50);
		txtNome.setColumns(50);
		
		txtCpf = new JFormattedTextField(mask);
		
		panelAdd.add(lblNome, "cell 0 0");
		panelAdd.add(txtNome, "span, growx");
		
		panelAdd.add(lblCpf, "cell 0 1");
		panelAdd.add(txtCpf, "cell 1 1");
		
		panelButtons = new JPanel (new MigLayout("", "[][][][][][][]", "[]"));
		panelButtons.setBorder(BorderFactory.createEtchedBorder());
		panelButtons.setBounds(5, 129, 540, 40);
		
		ClassLoader loader = getClass().getClassLoader();
		btnNew = new JButton("Novo", new ImageIcon(loader.getResource("imagens/new.png")));
		btnCancel = new JButton("Cancelar",new ImageIcon(loader.getResource("imagens/cancel.png")));
		
		panelButtons.add(btnNew, "cell 0 0");
		panelButtons.add(btnCancel, "cell 1 0");
		
		panelTable = new JPanel(new MigLayout("", "[]", "[]"));
		panelTable.setBorder(BorderFactory.createTitledBorder("Lista de Professores"));
		panelTable.setBounds(5, 180, 539, 210);
		
		table = new JTable();
		
		scrollPane = new JScrollPane(table);
		
		panelTable.add(scrollPane, "cell 0 0");
		
		refreshTable();
		enableFields(false);
		
		getContentPane().add(panelAdd);
		getContentPane().add(panelButtons);
		
		btnSave = new JButton("Salvar",new ImageIcon(loader.getResource("imagens/save.png")));
		panelButtons.add(btnSave, "cell 3 0,gapx unrelated");
		
		btnSave.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				onSaveProfessores();
			}
		});
		
		btnUpdate = new JButton("Editar",new ImageIcon(loader.getResource("imagens/edit.png")));
		panelButtons.add(btnUpdate, "cell 5 0,gapx unrelated");
		
		btnUpdate.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				onAlterarProfessores();
			}
		});
		
		btnRemove = new JButton("Deletar",new ImageIcon(loader.getResource("imagens/trash.png")));
		panelButtons.add(btnRemove, "cell 6 0");
		
		btnRemove.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				onRemoverProfessores();
			}
		});
		
		getContentPane().add(panelTable);
		
		btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new PainelSecretariaForm().setVisible(true);
				this.dispose();
			}

			private void dispose() {
				
			}
		});
		btnVoltar.setIcon(new ImageIcon(PainelProfessorForm.class.getResource("/Imagens/back.png")));
		btnVoltar.setBounds(5, 11, 109, 23);
		panel.add(btnVoltar);
		setMinimumSize(new Dimension(560,420));
		setVisible(true);
		
		btnCancel.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				onCancelarProfessores();
			}
		});
		
		btnNew.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				onNovosProfessores();
			}
		});
		
	}
	
	private void onAlterarProfessores() {
        int rowIndex = table.getSelectedRow();
		
		if (rowIndex == -1){
			JOptionPane.showMessageDialog(this, "Selecione o professor a ser alterado");
			return;
		}
		
		Professor professor = new ProfessorTableModel(professorList).get(rowIndex);
		
		idProfessor = professor.getId();
		
		txtCpf.setText(professor.getCpf());
		txtNome.setText(professor.getNome());
		
		enableFields(true);
	}

	private void onRemoverProfessores() {
		int rowIndex = table.getSelectedRow();
		
		if (rowIndex == -1){
			JOptionPane.showMessageDialog(this, "Selecione o professor a ser removido");
			return;
		}
		
		Professor professor = new ProfessorTableModel(professorList).get(rowIndex);
		
		int confirm = JOptionPane.showConfirmDialog(this, "Confirmar Exclusão?", "Faculdade Matriz", JOptionPane.YES_NO_OPTION);
		
		if (confirm != 0){
			return;
		}
		
		int result = new HistoricoEscolarController().removerProfessor(professor.getId());
		
		if (result == 1){
			JOptionPane.showMessageDialog(this, "Valor removido com sucesso!");
			refreshTable();
		} else {
			JOptionPane.showMessageDialog(this, "Valor não removido, Teste Novamente!");
		}
	}

	private void onNovosProfessores() {
		enableFields(true);
	}

	private void onSaveProfessores() {
		Professor professor = new Professor();
		
		if (txtCpf.getText().length() > 0 && txtNome.getText().length() > 0){
			professor.setCpf(txtCpf.getText());
			professor.setNome(txtNome.getText());
			
		} else {
			JOptionPane.showMessageDialog(this, "Todos os Campos são Obrigatórios!");
			return;
		}
		
		int result = 0;
		if(idProfessor == null){
			result = new HistoricoEscolarController().salvarProfessor(professor);
		} else {
			professor.setId(idProfessor);
			result = new HistoricoEscolarController().atualizarProfessor(professor);
			idProfessor = null;
		}
		
		if (result == 1){
			JOptionPane.showMessageDialog(this, "Valor inserido com sucesso!");
			enableFields(false);
			onCancelarProfessores();
			refreshTable();
			
		} else {
			JOptionPane.showMessageDialog(this, "Valor não inserido, Teste Novamente!");
		}
	}
	
	private void onCancelarProfessores() {
		txtCpf.setText("");
		txtNome.setText("");
		enableFields(false);
	}
	
	private void enableFields(boolean b) {
		txtCpf.setEnabled(b);
		txtNome.setEnabled(b);
	}

	private void refreshTable(){
		professorList = new HistoricoEscolarController().EncontreAllProfessor();
		if (professorList != null){
			table.setModel(new ProfessorTableModel(professorList));
	    }
	}
}

