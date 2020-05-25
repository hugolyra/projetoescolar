package Form;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import Table.DepartamentoTableModel;
import Controller.HistoricoEscolarController;
import Entity.Departamento;
import java.awt.Toolkit;

public class PainelDepartamentoForm extends JFrame {

	private JLabel lblNome, lblCoordenador;
	private JTextField txtNome, txtCoordenador;
	private JPanel panelAdd, panelTable, panelButtons;
	private JButton btnNew, btnSave, btnUpdate, btnRemove, btnCancel, btnVoltar;
	private JTable table;
	private JScrollPane scrollPane;
	
	private List<Departamento> departamentoList;
	private Long idDepartamento;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PainelDepartamentoForm frame = new PainelDepartamentoForm();
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
	public PainelDepartamentoForm() {
		setTitle("Faculdade Matriz");
		setIconImage(Toolkit.getDefaultToolkit().getImage(PainelDepartamentoForm.class.getResource("/Imagens/logofaculdade.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 400);
		JPanel panel = new JPanel();
		setContentPane(panel);
		getContentPane().setLayout(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panelAdd = new JPanel (new MigLayout());
		panelAdd.setBorder(BorderFactory.createTitledBorder("Adicionar Departamentos"));
		panelAdd.setBounds(5, 39, 540, 79);
		
		lblNome = new JLabel ("Nome");
		lblCoordenador = new JLabel ("Coordenador");
		
		txtNome = new JTextFieldSomenteLetras(50);
		txtNome.setColumns(50);
		
		txtCoordenador = new JTextFieldSomenteLetras(50);
		txtCoordenador.setColumns(50);
		
		panelAdd.add(lblNome);
		panelAdd.add(txtNome, "span, growx");
		
		panelAdd.add(lblCoordenador);
		panelAdd.add(txtCoordenador, "span, growx");
		
		panelButtons = new JPanel (new MigLayout("", "[][][][][][][]", "[]"));
		panelButtons.setBorder(BorderFactory.createEtchedBorder());
		panelButtons.setBounds(5, 129, 540, 40);
		
		ClassLoader loader = getClass().getClassLoader();
		btnNew = new JButton("Novo", new ImageIcon(loader.getResource("imagens/new.png")));
		btnCancel = new JButton("Cancelar",new ImageIcon(loader.getResource("imagens/cancel.png")));
		
		panelButtons.add(btnNew, "cell 0 0");
		panelButtons.add(btnCancel, "cell 1 0");
		
		panelTable = new JPanel(new MigLayout("", "[]", "[]"));
		panelTable.setBorder(BorderFactory.createTitledBorder("Lista de Departamentos"));
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
				onSaveDepartamentos();
			}
		});
		btnUpdate = new JButton("Editar",new ImageIcon(loader.getResource("imagens/edit.png")));
		panelButtons.add(btnUpdate, "cell 5 0,gapx unrelated");
		
		btnUpdate.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				onAlterarDepartamentos();
			}
		});
		btnRemove = new JButton("Deletar",new ImageIcon(loader.getResource("imagens/trash.png")));
		panelButtons.add(btnRemove, "cell 6 0");
		
		btnRemove.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				onRemoverDepartamentos();
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
		btnVoltar.setIcon(new ImageIcon(PainelDepartamentoForm.class.getResource("/Imagens/back.png")));
		btnVoltar.setBounds(5, 11, 109, 23);
		panel.add(btnVoltar);
		setMinimumSize(new Dimension(560,420));
		setVisible(true);
		
		btnCancel.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				onCancelarDepartamentos();
			}
		});
		
		btnNew.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				onNovosDepartamentos();
			}
		});
		
	}
	
	private void onAlterarDepartamentos() {
        int rowIndex = table.getSelectedRow();
		
		if (rowIndex == -1){
			JOptionPane.showMessageDialog(this, "Selecione o departamento a ser alterado");
			return;
		}
		
		Departamento departamento = new DepartamentoTableModel(departamentoList).get(rowIndex);
		
		idDepartamento = departamento.getId();
		
		txtNome.setText(departamento.getNome());
		txtCoordenador.setText(departamento.getCoordenador());
		
		enableFields(true);
	}

	private void onRemoverDepartamentos() {
		int rowIndex = table.getSelectedRow();
		
		if (rowIndex == -1){
			JOptionPane.showMessageDialog(this, "Selecione o departamento a ser removido");
			return;
		}
		
		Departamento departamento = new DepartamentoTableModel(departamentoList).get(rowIndex);
		
		int confirm = JOptionPane.showConfirmDialog(this, "Confirmar Exclusão?", "Faculdade Matriz", JOptionPane.YES_NO_OPTION);
		
		if (confirm != 0){
			return;
		}
		
		int result = new HistoricoEscolarController().removerDepartamento(departamento.getId());
		
		if (result == 1){
			JOptionPane.showMessageDialog(this, "Valor removido com sucesso!");
			refreshTable();
		} else {
			JOptionPane.showMessageDialog(this, "Valor não removido, Teste Novamente!");
		}
	}

	private void onNovosDepartamentos() {
		enableFields(true);
	}

	private void onSaveDepartamentos() {
		Departamento departamento = new Departamento();
		
		if (txtNome.getText().length() > 0 && txtCoordenador.getText().length() > 0){
			departamento.setNome(txtNome.getText());
			departamento.setCoordenador(txtCoordenador.getText());
		} else {
			JOptionPane.showMessageDialog(this, "Todos os Campos são Obrigatórios!");
			return;
		}
		
		int result = 0;
		if(idDepartamento == null){
			result = new HistoricoEscolarController().salvarDepartamento(departamento);
		} else {
			departamento.setId(idDepartamento);
			result = new HistoricoEscolarController().atualizarDepartamento(departamento);
			idDepartamento = null;
		}
		
		if (result == 1){
			JOptionPane.showMessageDialog(this, "Valor inserido com sucesso!");
			enableFields(false);
			onCancelarDepartamentos();
			refreshTable();
			
		} else {
			JOptionPane.showMessageDialog(this, "Valor não inserido, Teste Novamente!");
		}
	}
	
	private void onCancelarDepartamentos() {
		txtNome.setText("");
		txtCoordenador.setText("");
		enableFields(false);
	}
	
	private void enableFields(boolean b) {
		txtNome.setEnabled(b);
		txtCoordenador.setEnabled(b);
	}

	private void refreshTable(){
		departamentoList = new HistoricoEscolarController().EncontreAllDepartamento();
		if (departamentoList != null){
			table.setModel(new DepartamentoTableModel(departamentoList));
	    }
	}
}

