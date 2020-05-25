package Form;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
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
import Table.DisciplinaTableModel;
import Controller.HistoricoEscolarController;
import Entity.Disciplina;

import java.awt.Toolkit;

public class PainelDisciplinaForm extends JFrame {

	private JLabel lblNome, lblSemestre;
	private JTextField txtNome;
	private JFormattedTextField txtSemestre;
	private JPanel panelAdd, panelTable, panelButtons;
	private JButton btnNew, btnSave, btnUpdate, btnRemove, btnCancel, btnVoltar;
	private JTable table;
	private JScrollPane scrollPane;
	
	private List<Disciplina> disciplinaList;
	private Long idDisciplina;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PainelDisciplinaForm frame = new PainelDisciplinaForm();
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
	public PainelDisciplinaForm() throws ParseException {
		setTitle("Faculdade Matriz");
		setIconImage(Toolkit.getDefaultToolkit().getImage(PainelDisciplinaForm.class.getResource("/Imagens/logofaculdade.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 400);
		JPanel panel = new JPanel();
		setContentPane(panel);
		getContentPane().setLayout(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panelAdd = new JPanel (new MigLayout("", "[][][][]", "[][]"));
		panelAdd.setBorder(BorderFactory.createTitledBorder("Adicionar Disciplinas"));
		panelAdd.setBounds(5, 39, 540, 85);
		
		MaskFormatter mask = new MaskFormatter("#");
		//mask.setPlaceholderCharacter('_');
		
		lblNome = new JLabel ("Nome da Disciplina");
		txtNome = new JTextFieldSomenteLetras(50);
		txtNome.setColumns(50);
		
		lblSemestre = new JLabel ("Semestre");
		txtSemestre = new JFormattedTextField(mask);
		txtSemestre.setColumns(3);
		
		panelAdd.add(lblNome, "cell 0 0");
		panelAdd.add(txtNome, "cell 1 0");
		
        panelAdd.add(lblSemestre, "cell 0 1");
	    panelAdd.add(txtSemestre, "flowx,cell 1 1 3 1");
		
		panelButtons = new JPanel (new MigLayout("", "[][][][][][][]", "[]"));
		panelButtons.setBorder(BorderFactory.createEtchedBorder());
		panelButtons.setBounds(5, 135, 540, 40);
		
		ClassLoader loader = getClass().getClassLoader();
		btnNew = new JButton("Novo", new ImageIcon(loader.getResource("imagens/new.png")));
		btnCancel = new JButton("Cancelar",new ImageIcon(loader.getResource("imagens/cancel.png")));
		
		panelButtons.add(btnNew, "cell 0 0");
		panelButtons.add(btnCancel, "cell 1 0");
		
		panelTable = new JPanel(new MigLayout("", "[]", "[]"));
		panelTable.setBorder(BorderFactory.createTitledBorder("Lista de Disciplinas"));
		panelTable.setBounds(5, 186, 539, 204);
		
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
				onSaveDisciplinas();
			}
		});
		btnUpdate = new JButton("Editar",new ImageIcon(loader.getResource("imagens/edit.png")));
		panelButtons.add(btnUpdate, "cell 5 0,gapx unrelated");
		
		btnUpdate.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				onAlterarDisciplinas();
			}
		});
		btnRemove = new JButton("Deletar",new ImageIcon(loader.getResource("imagens/trash.png")));
		panelButtons.add(btnRemove, "cell 6 0");
		
		btnRemove.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				onRemoverDisciplinas();
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
		btnVoltar.setIcon(new ImageIcon(PainelDisciplinaForm.class.getResource("/Imagens/back.png")));
		btnVoltar.setBounds(5, 11, 109, 23);
		panel.add(btnVoltar);
		setMinimumSize(new Dimension(560,420));
		setVisible(true);
		
		btnCancel.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				onCancelarDisciplinas();
			}
		});
		
		btnNew.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				onNovasDisciplinas();
			}
		});
		
	}
	
	private void onAlterarDisciplinas() {
        int rowIndex = table.getSelectedRow();
		
		if (rowIndex == -1){
			JOptionPane.showMessageDialog(this, "Selecione a disciplina a ser alterada");
			return;
		}
		
		Disciplina disciplina = new DisciplinaTableModel(disciplinaList).get(rowIndex);
		
		idDisciplina = disciplina.getId();
		
		txtNome.setText(disciplina.getNome());
		txtSemestre.setText(Integer.toString(disciplina.getSemestre()));
		enableFields(true);
	}

	private void onRemoverDisciplinas() {
		int rowIndex = table.getSelectedRow();
		
		if (rowIndex == -1){
			JOptionPane.showMessageDialog(this, "Selecione a disciplina a ser removida");
			return;
		}
		
		Disciplina disciplina = new DisciplinaTableModel(disciplinaList).get(rowIndex);
		
		int confirm = JOptionPane.showConfirmDialog(this, "Confirmar Exclusão?", "Excluir Disciplina", JOptionPane.YES_NO_OPTION);
		
		if (confirm != 0){
			return;
		}
		
		int result = new HistoricoEscolarController().removerDisciplina(disciplina.getId());
		
		if (result == 1){
			JOptionPane.showMessageDialog(this, "Valor removido com sucesso!");
			refreshTable();
		} else {
			JOptionPane.showMessageDialog(this, "Valor não removido, Teste Novamente!");
		}
	}

	private void onNovasDisciplinas() {
		enableFields(true);
	}

	private void onSaveDisciplinas() {
		Disciplina disciplina = new Disciplina();
		
		if (txtNome.getText().length() > 0 && txtSemestre.getText().length() > 0){
			disciplina.setNome(txtNome.getText());
			disciplina.setSemestre(Integer.parseInt(txtSemestre.getText()));
		} else {
			JOptionPane.showMessageDialog(this, "Todos os Campos são Obrigatórios!");
			return;
		}
		
		int result = 0;
		if(idDisciplina == null){
			result = new HistoricoEscolarController().salvarDisciplina(disciplina);
		} else {
			disciplina.setId(idDisciplina);
			result = new HistoricoEscolarController().atualizarDisciplina(disciplina);
			idDisciplina = null;
		}
		
		if (result == 1){
			JOptionPane.showMessageDialog(this, "Valor inserido com sucesso!");
			enableFields(false);
			onCancelarDisciplinas();
			refreshTable();
		} else {
			JOptionPane.showMessageDialog(this, "Valor não inserido, Teste Novamente!");
		}
	}
	
	private void onCancelarDisciplinas() {
		txtNome.setText("");
		txtSemestre.setText("");
		enableFields(false);
	}
	
	private void enableFields(boolean b) {
		txtNome.setEnabled(b);
		txtSemestre.setEnabled(b);
	}

	private void refreshTable(){
		disciplinaList = new HistoricoEscolarController().EncontreAllDisciplina();
		if (disciplinaList != null){
			table.setModel(new DisciplinaTableModel(disciplinaList));
	    }
	}
	
}

