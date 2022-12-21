package view;
import java.awt.Color;
import java.awt.Component;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

class MyListCellRenderer extends DefaultListCellRenderer {
    private int[] disabledIndices; // array of indices of the items to be disabled

    public MyListCellRenderer(int[] disabledIndices) {
        this.disabledIndices = disabledIndices;
    }

    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        for (int disabledIndex : disabledIndices) {
            if (index == disabledIndex) {
                setForeground(Color.GRAY); // render the disabled items as gray
                setEnabled(false);
                break;
            } else {
                setForeground(Color.BLACK); // render other items as black
                setEnabled(true);
            }
        }
        return this;
    }
}
