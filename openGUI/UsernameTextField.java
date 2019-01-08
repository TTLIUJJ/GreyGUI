package openGUI;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;

public class UsernameTextField extends JTextField implements DocumentListener {
    private String str;
    public UsernameTextField() {
        super(10);
        Document document = getDocument();
        document.addDocumentListener(this);
    }

    public String getTextString() {
        return str;
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        Document document = e.getDocument();
        try {
            str = document.getText(0, document.getLength());
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        Document document = e.getDocument();
        try {
            str = document.getText(0, document.getLength());
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    @Override
    public void changedUpdate(DocumentEvent e) {

    }
}
