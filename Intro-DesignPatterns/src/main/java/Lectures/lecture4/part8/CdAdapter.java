package Lectures.lecture4.part8;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class CdAdapter extends AbstractTableModel {

    private List<CD> cds;

    private String[] columns = {"Artist", "Album", "Label", "Year"};

    public CdAdapter() {
        cds = new ArrayList<>();
    }

    public void addCD(CD cd) {
        cds.add(cd);
    }

    @Override
    public int getRowCount() {
        return cds.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        CD temp = cds.get(rowIndex);
        Object returned = null;
        switch (columnIndex) {
            case 0:
                returned = temp.getName();
                break;
            case 1:
                returned = temp.getAlbum();
                break;
            case 2:
                returned = temp.getLabel();
                break;
            case 3:
                returned = temp.getYear();
                break;
        }
        return returned;
    }
}
