package EditorTexto;

public class FuncionesFormato {

    App app;

    public FuncionesFormato(App app) {

        this.app = app;

    }

    public void SaltoLinea() {

        if(app.SaltoLineaOn == false) {
            app.SaltoLineaOn = true;
            app.textArea.setLineWrap(true);
            app.textArea.setWrapStyleWord(true);
            app.iSaltoLinea.setText("Salto de Línea: Activado");
        } else if (app.SaltoLineaOn == true) {
            app.SaltoLineaOn = false;
            app.textArea.setLineWrap(false);
            app.textArea.setWrapStyleWord(false);
            app.iSaltoLinea.setText("Salto de Línea: Desactivado");
        }

    }
}
