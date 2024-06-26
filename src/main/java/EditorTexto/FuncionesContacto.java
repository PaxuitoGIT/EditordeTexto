package EditorTexto;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FuncionesContacto extends JPanel {

    App app;
    JTextField nombre;
    JTextField Descripcion;
    JTextField telefono;
    JTextField email;
    JButton guardarBoton;
    JButton cancelarBoton;

    // Verifica si el correo electrónico es válido
    boolean esEmailValido(String email) {
        String regex = "^[A-Za-z0-9+_.-]+@(.+)\\.[A-Za-z0-9+_.-]+$"; // Un correo electrónico válido debe tener un @ y un punto
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }


    // Clase Contacto
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
    // Constructor de la clase FuncionesContacto
    public FuncionesContacto(App app) {
        this.app = app;

        setLayout(new BorderLayout());
        add(createFieldsPane(), BorderLayout.CENTER);
        add(createButtonsPane(), BorderLayout.SOUTH);

    }
// Cuando se pinche en el botón de agregar contacto, se abrirá una ventana para agregar un contacto
    public void agregarContacto() {
        FuncionesContacto formPanel = new FuncionesContacto(app);
        JFrame frame = new JFrame("Agregar Contacto");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().add(formPanel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
// Cuando se pinche en el botón de mostrar contacto, se abrirá una ventana para seleccionar el archivo del contacto guardado
    public void mostrarContacto() {
        FileDialog fd = new FileDialog((Frame) null, "Selecciona el archivo de contacto");
        fd.setDirectory(System.getProperty("user.dir")); // Esto apunta al directorio del proyecto supuestamente
        fd.setMode(FileDialog.LOAD);
        fd.setVisible(true);

        if (fd.getFile() != null) {
            String nombreArchivo = fd.getFile();
            String direccionArchivo = fd.getDirectory();
            app.ventana.setTitle(nombreArchivo);

            try {
                BufferedReader br = new BufferedReader(new FileReader(direccionArchivo + nombreArchivo));

                app.textArea.setText("");

                String line;

                while ((line = br.readLine()) != null) {
                    app.textArea.append(line + "\n");
                }
                br.close();
            } catch (Exception e) {
                System.out.println("Error al abrir el archivo");
            }
        }
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

        // Verifica si el correo electrónico es válido
        email.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                verificarEmail();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                verificarEmail();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                verificarEmail();
            }

            private void verificarEmail() {
                String textoEmail = email.getText();
                if (esEmailValido(textoEmail)) {
                    // Si el correo electrónico es válido, cambia el color del texto a verde
                    email.setForeground(new Color(0,128, 0));
                } else {
                    // Si el correo electrónico no es válido, cambia el color del texto a rojo
                    email.setForeground(new Color(172, 0, 0));
                }
            }
        });

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
