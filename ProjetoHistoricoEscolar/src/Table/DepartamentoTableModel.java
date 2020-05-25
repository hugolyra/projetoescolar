package Table;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import Entity.Departamento;

public class DepartamentoTableModel extends AbstractTableModel {

	private static final int COL_ID = 0;
	private static final int COL_NOME = 1;
	private static final int COL_COORDENADOR = 2;

	private List<Departamento> valores;
	
	public DepartamentoTableModel (List<Departamento> valores){
		this.valores = valores;
	}
	
	@Override
	public int getRowCount() {
		return valores.size();
	}
	
	@Override
	public int getColumnCount() {
		return 3;
	}
	
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Departamento departamento = valores.get(rowIndex);
		if (columnIndex == COL_ID){
			return departamento.getId();
		}else if (columnIndex == COL_NOME){
			return departamento.getNome();
		}else if (columnIndex == COL_COORDENADOR){
			return departamento.getCoordenador();
		}
		return null;
	}
	
	@Override
	public String getColumnName(int column){
		String coluna = "";
		switch (column){
		    case COL_ID:
			    coluna = "Código";
		        break;
		    case COL_NOME:
			    coluna = "Nome do Departamento";
		        break;
	        case COL_COORDENADOR:
		        coluna = "Coordenador";
	            break;
		    default:
			    throw new IllegalArgumentException("Coluna inválida");
		}
		return coluna;
	}
	
	@Override
	public Class<?> getColumnClass(int columnIndex){
		if(columnIndex == COL_ID){
			return Long.class;
		}else if (columnIndex == COL_NOME){
			return String.class;
		}else if (columnIndex == COL_COORDENADOR){
			return String.class;
		}
		return null;
	}
	
	public Departamento get(int row){
		return valores.get(row);
	}

}
