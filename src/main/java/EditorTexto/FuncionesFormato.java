package EditorTexto;

import java.awt.*;

public class FuncionesFormato {

    App app;
    Font arial, CSMS, TNR;
    String FuenteSelect;

    public FuncionesFormato(App app) {

        this.app = app;

    }

    public void SaltoLinea() {

        if (app.SaltoLineaOn == false) {
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

        public void crearFuente (int tamanoFuente) {

            arial = new Font("Arial", Font.PLAIN, tamanoFuente);
            CSMS = new Font("Comic Sans MS", Font.PLAIN, tamanoFuente);
            TNR = new Font("Times New Roman", Font.PLAIN, tamanoFuente);

            setFuente(FuenteSelect);

        }

        public void setFuente(String fuente) {

        FuenteSelect = fuente;

        switch(FuenteSelect) {
            case "Arial":
                app.textArea.setFont(arial);
                break;
            case "Comic Sans MS":
                app.textArea.setFont(CSMS);
                break;
            case "Times New Roman":
                app.textArea.setFont(TNR);
                break;
        }


    }
}
