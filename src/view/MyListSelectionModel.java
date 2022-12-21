package view;

import javax.swing.DefaultListSelectionModel;

class MyListSelectionModel extends DefaultListSelectionModel {
    private int[] disabledIndices; // array of indices of the items to be disabled

    public MyListSelectionModel(int[] disabledIndices) {
        this.disabledIndices = disabledIndices;
    }

    @Override
    public void setSelectionInterval(int index0, int index1) {
        boolean disabled = false;
        for (int disabledIndex : disabledIndices) {
            if (index0 == disabledIndex || index1 == disabledIndex) {
                disabled = true;
                break;
            }
        }
        if (!disabled) {
            super.setSelectionInterval(index0, index1);
        } else {
            super.setSelectionInterval(-1, -1);
        }
    }
}

