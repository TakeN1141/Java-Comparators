package fmi.informatics.gui;

import fmi.informatics.AscendingComparators.PersonComparator;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.*;

/* При натискане на бутон да се даде възможност на потребителя да избере по коя колона да сортира
 *	Създаване на диалог
 *	Добавяне на текст за избор
 *	Добавяне на текстово поле, където потребителя да въведе своя избор
 *	Добавяне на бутон, който спрямо избора ще сортира съответната колона
 *	Добавяне на Custom Comparators
 */
public class CustomDialogDesc extends JDialog implements ActionListener, PropertyChangeListener {

    private static final long serialVersionUID = 1L;
    protected int sortOrder = -1;

    private String typedText;
    private JTextField textField;
    private JOptionPane optionPane;

    private String okLabel = "OK";
    private String cancelLabel = "Cancel";

    private PersonDataGUI parentGui;

    // Създаване на диалог
    public CustomDialogDesc(String text, PersonDataGUI parent) {
        setTitle("Избор на сортиране");

        this.parentGui = parent;
        this.textField = new JTextField(2);

        // Създаване на масив с текст и с компоненти, които да се визуализират
        Object[] array = {text, textField};
        Object[] options = {okLabel, cancelLabel};

        optionPane = new JOptionPane(array, JOptionPane.QUESTION_MESSAGE,
                JOptionPane.OK_CANCEL_OPTION, null, options, options[0]);

        // Визуализиране на диалога
        setContentPane(optionPane);

        // Прихващане на затварянето на диалога
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                // Вместо да затваряме директно прозореца, ще се промени стойността на JOptionPane
                optionPane.setValue(new Integer(JOptionPane.CLOSED_OPTION));
            }
        });

        // Уверяваме се, че текстовото поле винаги получава първия фокус
        addComponentListener(new ComponentAdapter() {
            public void componentShown(ComponentEvent ce) {
                textField.requestFocusInWindow();
            }
        });

        // регистриране на event handler за текстовото поле
        textField.addActionListener(this);

        // регистриране на event handler, който реагира при промяна на състоянието на ОptionPane
        optionPane.addPropertyChangeListener(this);
    } // end CustomDialog constructor

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        String propertyName = evt.getPropertyName();

        // проверка, дали има промяна в ОptionPane стойността
        if (isVisible() && (evt.getSource() == optionPane)
                && (JOptionPane.VALUE_PROPERTY.equals(propertyName) ||
                JOptionPane.INPUT_VALUE_PROPERTY.equals(propertyName))) {

            Object value = optionPane.getValue();

            if (value == JOptionPane.UNINITIALIZED_VALUE) {
                return;
            }

            /*
             * Нулиране на стойността на JOptionPane.
             * Ако не направите това, тогава, ако потребителят натиска същия бутон следващия път,
             * няма да се прихване събитие за промяна.
             */
            optionPane.setValue(JOptionPane.UNINITIALIZED_VALUE);

            if (value.equals(okLabel)) {
                if (textField.getText() != null && !textField.getText().isEmpty()) {
                    typedText = textField.getText();
                    CatchParsing();

                }

            }
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        optionPane.setValue(okLabel);

    }

    // Този метод изчиства диалога и го скрива
    private void clearAndHide() {
        textField.setText(null);
        setVisible(false);
    }


    protected void CatchParsing() {
        try {
            int Parsing = Integer.parseInt(typedText);
            if (Parsing >= 1 && Parsing <= 5) {
                parentGui.sortTable(Parsing, parentGui.table, PersonDataGUI.people);
                clearAndHide();
            } else {
                textField.selectAll();

                JOptionPane.showMessageDialog(CustomDialogDesc.this,
                        "Съжалявам, стойността: " + typedText + " не е валидна!",
                        "Грешка", JOptionPane.ERROR_MESSAGE);

                typedText = null;
                textField.requestFocusInWindow();

            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(CustomDialogDesc.this,
                    "Въведеното : " + typedText + " Не е валидно число" + " !",
                    "Грешка", JOptionPane.ERROR_MESSAGE);
        }
    }
}
