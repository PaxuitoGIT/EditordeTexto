package EditorTexto;

public class FuncionesEditar {

    App app;

    // Heredar el constructor de la clase App
    public FuncionesEditar(App app) {

        this.app = app;

    }

    // Deshace la última acción
    public void Deshacer() {

        app.um.undo();
    }

    // Rehace la última acción
    public void Rehacer() {

        app.um.redo();
    }
}
