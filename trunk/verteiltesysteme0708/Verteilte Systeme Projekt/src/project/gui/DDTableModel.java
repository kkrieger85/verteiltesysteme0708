/**
 * 
 */
package project.gui;

import javax.swing.table.DefaultTableModel;
import java.util.Vector;

/**
 * @author <a href="mailto:reichert.sascha@googlemail.com">Sascha Reichert, reichert.sascha@googlemail.com</a>
 *
 */
public class DDTableModel extends DefaultTableModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7933298757026077102L;

		public DDTableModel(Object[][] data,String[] columnNames){
			super(data,columnNames); 
		}
		
		public DDTableModel(Vector<Vector<Object>> data,Vector<String> columnNames){
			super(data,columnNames); 
		}
		
		public boolean isCellEditable(int row, int col) {
			return false;
		}
		
	}

