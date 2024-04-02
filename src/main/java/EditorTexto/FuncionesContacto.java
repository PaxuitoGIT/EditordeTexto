package EditorTexto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;

public class FuncionesContacto extends JPanel {

    JTextField nombre;
    JTextField Descripcion;
    JTextField telefono;
    JTextField email;
    JButton guardarBoton;
    JButton cancelarBoton;


    public static class Contacto {

        String nombre;
        String telefono;
        String email;
        String descripcion;

        public Contacto(String nombre, String telefono, String email, String descripcion) {
            this.nombre = nombre;
            this.telefono = telefono;
            this.email = email;
            this.descripcion = descripcion;
        }
    }
    public FuncionesContacto() {

        setLayout(new BorderLayout());
        add(createFieldsPane(), BorderLayout.CENTER);
        add(createButtonsPane(), BorderLayout.SOUTH);

    }

    public JPanel createButtonsPane() {

        JPanel panel = new JPanel(new FlowLayout());
        panel.add((guardarBoton = createButton("Guardar")));
        panel.add((cancelarBoton = createButton("Cancelar")));

        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("Nombre");
        tableModel.addColumn("Teléfono");
        tableModel.addColumn("Email");
        tableModel.addColumn("Descripción");

        guardarBoton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String nombreContacto = nombre.getText();
                String telefonoContacto = telefono.getText();
                String emailContacto = email.getText();
                String descripcionContacto = Descripcion.getText();

                Contacto contacto = new Contacto(nombreContacto, telefonoContacto, emailContacto, descripcionContacto);
                App.listaContactos.add(contacto);
                tableModel.addRow(new Object[]{nombreContacto, telefonoContacto, emailContacto, descripcionContacto});

                displayContactsInTable();

                try {
                    FileWriter fw = new FileWriter(("contactos.txt"), true);
                    for (Contacto c : App.listaContactos) {
                        fw.write("Nombre: " + c.nombre + ", Teléfono: " + c.telefono + ", EMail: " + c.email + ", Descripción: " + c.descripcion + "\n");
                    }
                    fw.close();
                } catch (Exception ex) {
                    System.out.println("Error al guardar el archivo");

            }

                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(FuncionesContacto.this);
                frame.dispose();

        }});

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

        panel.add(createLabel("Nombre:"), gbc);
        gbc.gridy++;
        panel.add(createLabel("Teléfono:"), gbc);
        gbc.gridy++;
        panel.add(createLabel("EMail:"), gbc);
        gbc.gridy++;
        panel.add(createLabel("Descripción:"), gbc);

        gbc.gridy = 0;
        gbc.gridx++;
        gbc.weightx = 1;
        panel.add((nombre = createField()), gbc);
        gbc.gridy++;
        panel.add((telefono = createField()), gbc);
        gbc.gridy++;
        panel.add((email = createField()), gbc);
        gbc.gridy++;
        panel.add((Descripcion = createField()), gbc);

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

    public void displayContactsInTable() {
        String[] columnNames = {"Nombre", "Telefono", "Email", "Descripcion"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);

        for (Contacto contacto : App.listaContactos) {
            Object[] rowData = new Object[]{
                    contacto.nombre,
                    contacto.telefono,
                    contacto.email,
                    contacto.descripcion
            };
            tableModel.addRow(rowData);
        }

        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);
    }
}
