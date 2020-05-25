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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import net.miginfocom.swing.MigLayout;
import Table.CursoTableModel;
import Controller.HistoricoEscolarController;
import Entity.Curso;

import java.awt.Toolkit;

public class PainelCursoForm extends JFrame {

	private JLabel lblNome;
	private JTextField txtNome;
	private JPanel panelAdd, panelTable, panelButtons;
	private JButton btnNew, btnSave, btnUpdate, btnRemove, btnCancel, btnVoltar;
	private JTable table;
	private JScrollPane scrollPane;
	
	private List<Curso> cursoList;
	private Long idCurso;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PainelCursoForm frame = new PainelCursoForm();
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
	public PainelCursoForm() {
		setTitle("Faculdade Matriz");
		setIconImage(Toolkit.getDefaultToolkit().getImage(PainelCursoForm.class.getResource("/Imagens/logofaculdade.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 400);
		JPanel panel = new JPanel();
		setContentPane(panel);
		getContentPane().setLayout(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panelAdd = new JPanel (new MigLayout());
		panelAdd.setBorder(BorderFactory.createTitledBorder("Adicionar Cursos"));
		panelAdd.setBounds(5, 39, 540, 79);
		
		lblNome = new JLabel ("Nome");
		
		txtNome = new JTextField(75);
		
		panelAdd.add(lblNome);
		panelAdd.add(txtNome, "span, growx");
		
		panelButtons = new JPanel (new MigLayout("", "[][][][][][][]", "[]"));
		panelButtons.setBorder(BorderFactory.createEtchedBorder());
		panelButtons.setBounds(5, 129, 540, 40);
		
		ClassLoader loader = getClass().getClassLoader();
		btnNew = new JButton("Novo", new ImageIcon(loader.getResource("imagens/new.png")));
		btnCancel = new JButton("Cancelar",new ImageIcon(loader.getResource("imagens/cancel.png")));
		
		panelButtons.add(btnNew, "cell 0 0");
		panelButtons.add(btnCancel, "cell 1 0");
		
		panelTable = new JPanel(new MigLayout("", "[]", "[]"));
		panelTable.setBorder(BorderFactory.createTitledBorder("Lista de Cursos"));
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
				onSaveCursos();
			}
		});
		btnUpdate = new JButton("Editar",new ImageIcon(loader.getResource("imagens/edit.png")));
		panelButtons.add(btnUpdate, "cell 5 0,gapx unrelated");
		
		btnUpdate.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				onAlterarCursos();
			}
		});
		btnRemove = new JButton("Deletar",new ImageIcon(loader.getResource("imagens/trash.png")));
		panelButtons.add(btnRemove, "cell 6 0");
		
		btnRemove.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				onRemoverCursos();
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
		btnVoltar.setIcon(new ImageIcon(PainelCursoForm.class.getResource("/Imagens/back.png")));
		btnVoltar.setBounds(5, 11, 109, 23);
		panel.add(btnVoltar);
		setMinimumSize(new Dimension(560,420));
		setVisible(true);
		
		btnCancel.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				onCancelarCursos();
			}
		});
		
		btnNew.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				onNovosCursos();
			}
		});
		
	}
	
	private void onAlterarCursos() {
        int rowIndex = table.getSelectedRow();
		
		if (rowIndex == -1){
			JOptionPane.showMessageDialog(this, "Selecione o curso a ser alterado");
			return;
		}
		
		Curso curso = new CursoTableModel(cursoList).get(rowIndex);
		
		idCurso = curso.getId();
		
		txtNome.setText(curso.getNome());
		
		enableFields(true);
	}

	private void onRemoverCursos() {
		int rowIndex = table.getSelectedRow();
		
		if (rowIndex == -1){
			JOptionPane.showMessageDialog(this, "Selecione o curso a ser removido");
			return;
		}
		
		Curso curso = new CursoTableModel(cursoList).get(rowIndex);
		
		int confirm = JOptionPane.showConfirmDialog(this, "Confirmar Exclusão?", "Faculdade Matriz", JOptionPane.YES_NO_OPTION);
		
		if (confirm != 0){
			return;
		}
		
		int result = new HistoricoEscolarController().removerCurso(curso.getId());
		
		if (result == 1){
			JOptionPane.showMessageDialog(this, "Valor removido com sucesso!");
			refreshTable();
		} else {
			JOptionPane.showMessageDialog(this, "Valor não removido, Teste Novamente!");
		}
	}

	private void onNovosCursos() {
		enableFields(true);
	}

	private void onSaveCursos() {
		Curso curso = new Curso();
		
		if (txtNome.getText().length() > 0){
			curso.setNome(txtNome.getText());
		} else {
			JOptionPane.showMessageDialog(this, "Todos os Campos são Obrigatórios!");
			return;
		}
		
		int result = 0;
		if(idCurso == null){
			result = new HistoricoEscolarController().salvarCurso(curso);
		} else {
			curso.setId(idCurso);
			result = new HistoricoEscolarController().atualizarCurso(curso);
			idCurso = null;
		}
		
		if (result == 1){
			JOptionPane.showMessageDialog(this, "Valor inserido com sucesso!");
			enableFields(false);
			onCancelarCursos();
			refreshTable();
			
		} else {
			JOptionPane.showMessageDialog(this, "Valor não inserido, Teste Novamente!");
		}
	}
	
	private void onCancelarCursos() {
		txtNome.setText("");
		enableFields(false);
	}
	
	private void enableFields(boolean b) {
		txtNome.setEnabled(b);
	}

	private void refreshTable(){
		cursoList = new HistoricoEscolarController().EncontreAllCurso();
		if (cursoList != null){
			table.setModel(new CursoTableModel(cursoList));
	    }
	}
	
}

