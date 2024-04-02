package EditorTexto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FuncionesContacto extends JPanel {

    JTextField nombre;
    JTextField apellidos;
    JTextField telefono;
    JTextField email;
    JButton guardarBoton;
    JButton cancelarBoton;

    public FuncionesContacto() {

        setLayout(new BorderLayout());
        add(createFieldsPane(), BorderLayout.CENTER);
        add(createButtonsPane(), BorderLayout.SOUTH);

    }

    public JPanel createButtonsPane() {

        JPanel panel = new JPanel(new FlowLayout());
        panel.add((guardarBoton = createButton("Guardar")));
        panel.add((cancelarBoton = createButton("Cancelar")));

        guardarBoton.addActionListener(e -> {

        });

        cancelarBoton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(FuncionesContacto.this);
                frame.dispose();
            }
        });

        return panel;

    }

    protected JButton createButton(String text) {

        return new JButton(text);

    }

    public JPanel createFieldsPane() {

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(2, 2, 2, 2);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;

        panel.add(createLabel("Nombre"), gbc);
        gbc.gridy++;
        panel.add(createLabel("Apellido"), gbc);
        gbc.gridy++;
        panel.add(createLabel("Teléfono"), gbc);
        gbc.gridy++;
        panel.add(createLabel("EMail:"), gbc);

        gbc.gridy = 0;
        gbc.gridx++;
        gbc.weightx = 1;
        panel.add((nombre = createField()), gbc);
        gbc.gridy++;
        panel.add((apellidos = createField()), gbc);
        gbc.gridy++;
        panel.add((telefono = createField()), gbc);
        gbc.gridy++;
        panel.add((email = createField()), gbc);

        JPanel filler = new JPanel();
        filler.setOpaque(false);

        gbc.gridy++;
        gbc.weightx = 1;
        gbc.weighty = 1;
        panel.add(filler, gbc);

        return panel;

    }

    protected JLabel createLabel(String text) {

        return new JLabel(text);

    }

    protected JTextField createField() {

        JTextField field = new JTextField(12);
        return field;

    }

}
