package Form;

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
import javax.swing.text.MaskFormatter;

import net.miginfocom.swing.MigLayout;
import Table.NotasTableModel;
import Controller.HistoricoEscolarController;
import Entity.Notas;

import java.awt.Toolkit;

public class PainelNotasForm extends JFrame {

	private JLabel lblPrimeiraUnidade, lblSegundaUnidade, lblNotaFinal;
	private JTextField txtPrimeiraUnidade, txtSegundaUnidade, txtNotaFinal;
	private JPanel panelAdd, panelTable, panelButtons;
	private JButton btnNew, btnSave, btnUpdate, btnRemove, btnCancel, btnVoltar;
	private JTable table;
	private JScrollPane scrollPane;
	
	private List<Notas> notasList;
	private Long idNotas;

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
	 * @throws ParseException 
	 */
	public PainelNotasForm() throws ParseException {
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
		panelAdd.setBorder(BorderFactory.createTitledBorder("Adicionar Notas"));
		panelAdd.setBounds(5, 39, 540, 79);
		
		lblPrimeiraUnidade = new JLabel ("1° Unidade");
		txtPrimeiraUnidade = new JTextField(5);
		txtPrimeiraUnidade.setColumns(3);
		
		panelAdd.add(lblPrimeiraUnidade);
		panelAdd.add(txtPrimeiraUnidade);
		
		lblSegundaUnidade = new JLabel ("2° Unidade");
		txtSegundaUnidade = new JTextField(5);
		txtSegundaUnidade.setColumns(3);
		
		panelAdd.add(lblSegundaUnidade);
		panelAdd.add(txtSegundaUnidade);
		
		lblNotaFinal = new JLabel ("Final");
		txtNotaFinal = new JTextField(5);
		txtNotaFinal.setColumns(3);
		
		panelAdd.add(lblNotaFinal);
		panelAdd.add(txtNotaFinal);
		
		panelButtons = new JPanel (new MigLayout("", "[][][][][][][]", "[]"));
		panelButtons.setBorder(BorderFactory.createEtchedBorder());
		panelButtons.setBounds(5, 129, 540, 40);
		
		ClassLoader loader = getClass().getClassLoader();
		btnNew = new JButton("Novo", new ImageIcon(loader.getResource("imagens/new.png")));
		btnCancel = new JButton("Cancelar",new ImageIcon(loader.getResource("imagens/cancel.png")));
		
		panelButtons.add(btnNew, "cell 0 0");
		panelButtons.add(btnCancel, "cell 1 0");
		
		panelTable = new JPanel(new MigLayout("", "[]", "[]"));
		panelTable.setBorder(BorderFactory.createTitledBorder("Lista de Notas"));
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
				onSaveNotas();
			}
		});
		btnUpdate = new JButton("Editar",new ImageIcon(loader.getResource("imagens/edit.png")));
		panelButtons.add(btnUpdate, "cell 5 0,gapx unrelated");
		
		btnUpdate.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				onAlterarNotas();
			}
		});
		btnRemove = new JButton("Deletar",new ImageIcon(loader.getResource("imagens/trash.png")));
		panelButtons.add(btnRemove, "cell 6 0");
		
		btnRemove.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				onRemoverNotas();
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
				onCancelarNotas();
			}
		});
		
		btnNew.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				onNovasNotas();
			}
		});
		
	}
	
	private void onAlterarNotas() {
        int rowIndex = table.getSelectedRow();
		
		if (rowIndex == -1){
			JOptionPane.showMessageDialog(this, "Selecione o nota a ser alterado");
			return;
		}
		
		Notas notas = new NotasTableModel(notasList).get(rowIndex);
		
		idNotas = notas.getId();
		
		txtPrimeiraUnidade.setText(Float.toString(notas.getPrimeiraUnidade()));
		txtSegundaUnidade.setText(Float.toString(notas.getSegundaUnidade()));
		txtNotaFinal.setText(Float.toString(notas.getNotaFinal()));
		
		enableFields(true);
	}

	private void onRemoverNotas() {
		int rowIndex = table.getSelectedRow();
		
		if (rowIndex == -1){
			JOptionPane.showMessageDialog(this, "Selecione a nota a ser removida");
			return;
		}
		
		Notas notas = new NotasTableModel(notasList).get(rowIndex);
		
		int confirm = JOptionPane.showConfirmDialog(this, "Confirmar Exclusão?", "Faculdade Matriz", JOptionPane.YES_NO_OPTION);
		
		if (confirm != 0){
			return;
		}
		
		int result = new HistoricoEscolarController().removerNotas(notas.getId());
		
		if (result == 1){
			JOptionPane.showMessageDialog(this, "Valor removido com sucesso!");
			refreshTable();
		} else {
			JOptionPane.showMessageDialog(this, "Valor não removido, Teste Novamente!");
		}
	}

	private void onNovasNotas() {
		enableFields(true);
	}

	private void onSaveNotas() {
		Notas notas = new Notas();
		
		if (txtPrimeiraUnidade.getText().length() > 0 && txtSegundaUnidade.getText().length() > 0 && txtNotaFinal.getText().length() > 0){
			notas.setPrimeiraUnidade(Float.parseFloat(txtPrimeiraUnidade.getText()));
			notas.setSegundaUnidade(Float.parseFloat(txtSegundaUnidade.getText()));
			notas.setNotaFinal(Float.parseFloat(txtNotaFinal.getText()));
		} else {
			JOptionPane.showMessageDialog(this, "Todos os Campos são Obrigatórios!");
			return;
		}
		
		int result = 0;
		if(idNotas == null){
			result = new HistoricoEscolarController().salvarNotas(notas);
		} else {
			notas.setId(idNotas);
			result = new HistoricoEscolarController().atualizarNotas(notas);
			idNotas = null;
		}
		
		if (result == 1){
			JOptionPane.showMessageDialog(this, "Valor inserido com sucesso!");
			enableFields(false);
			onCancelarNotas();
			refreshTable();
			
		} else {
			JOptionPane.showMessageDialog(this, "Valor não inserido, Teste Novamente!");
		}
	}
	
	private void onCancelarNotas() {
		txtPrimeiraUnidade.setText("");
		txtSegundaUnidade.setText("");
		txtNotaFinal.setText("");
		enableFields(false);
	}
	
	private void enableFields(boolean b) {
		txtPrimeiraUnidade.setEnabled(b);
		txtSegundaUnidade.setEnabled(b);
		txtNotaFinal.setEnabled(b);
	}

	private void refreshTable(){
		notasList = new HistoricoEscolarController().EncontreAllNotas();
		if (notasList != null){
			table.setModel(new NotasTableModel(notasList));
	    }
	}
	
}


