package EditorTexto;

public class FuncionesEditar {

    App app;

    public FuncionesEditar(App app) {

        this.app = app;

    }

    public void Deshacer() {

        app.um.undo();
    }

    public void Rehacer() {

        app.um.redo();
    }
}
