package org.hugo.dein.jasperejercicio4.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Controlador para la ventana de generación de informes médicos.
 * Esta clase gestiona la interacción del usuario con los controles de la ventana
 * para generar un informe a partir de los datos proporcionados en el formulario.
 */
public class VentanaController {

    @FXML
    private Button btGenerar;

    @FXML
    private Button btLimpiar;

    @FXML
    private Button btSalir;

    @FXML
    private TextArea txaTratamiento;

    @FXML
    private TextField txtCodMedico;

    @FXML
    private TextField txtDirPaciente;

    @FXML
    private TextField txtEspMedico;

    @FXML
    private TextField txtNomMedico;

    @FXML
    private TextField txtNomPaciente;

    @FXML
    private TextField txtNumPaciente;

    /**
     * Metodo que se ejecuta al presionar el botón "Generar".
     * Valida los campos del formulario y, si son correctos, genera el informe con los datos proporcionados.
     *
     * @param event El evento que dispara este metodo (presionar el botón).
     */
    @FXML
    void generar(ActionEvent event) {
        StringBuilder errores = new StringBuilder();

        // Validación de los campos del formulario
        if (!esNumeroEntero(txtNumPaciente.getText())) {
            errores.append("El 'Número de paciente' debe ser un número entero.\n");
        }

        if (!esNumeroEntero(txtCodMedico.getText())) {
            errores.append("El 'Código del médico' debe ser un número entero.\n");
        }

        if (txtNomPaciente.getText().trim().isEmpty()) {
            errores.append("El 'Nombre del paciente' no puede estar vacío.\n");
        }

        if (txtNomMedico.getText().trim().isEmpty()) {
            errores.append("El 'Nombre del médico' no puede estar vacío.\n");
        }

        if (txtEspMedico.getText().trim().isEmpty()) {
            errores.append("La 'Especialidad del médico' no puede estar vacía.\n");
        }

        if (txaTratamiento.getText().trim().isEmpty()) {
            errores.append("El 'Tratamiento' no puede estar vacío.\n");
        }

        // Si hay errores, se muestra el mensaje correspondiente
        if (errores.length() > 0) {
            mostrarError("Errores en el formulario", errores.toString());
        } else {
            // Si no hay errores, se preparan los parámetros y se genera el informe
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("NUM_PACIENTE", Integer.parseInt(txtNumPaciente.getText()));
            parameters.put("NOM_PACIENTE", txtNomPaciente.getText());
            parameters.put("DIR_PACIENTE", txtDirPaciente.getText());
            parameters.put("COD_MEDICO", Integer.parseInt(txtCodMedico.getText()));
            parameters.put("NOM_MEDICO", txtNomMedico.getText());
            parameters.put("ESP_MEDICO", txtEspMedico.getText());
            parameters.put("TRATAMIENTO", txaTratamiento.getText());
            parameters.put("IMAGE_PATH", getClass().getResource("/imagenes/").toString());

            // Llamar al metodo para generar el reporte
            generarReporte("/Jasper/formulario.jasper", parameters);
        }
    }

    /**
     * Metodo que genera el reporte usando los parámetros proporcionados.
     *
     * @param reportePath La ruta al archivo .jasper del reporte.
     * @param parameters Los parámetros que se utilizarán en el reporte.
     */
    private void generarReporte(String reportePath, Map<String, Object> parameters) {
        try {
            // Cargar el reporte desde el archivo .jasper
            InputStream reportStream = getClass().getResourceAsStream(reportePath);

            if (reportStream == null) {
                System.out.println("El archivo no está allí: " + reportePath);
                return;
            }

            // Cargar el reporte y generar el informe con los parámetros
            JasperReport report = (JasperReport) JRLoader.loadObject(reportStream);
            JasperPrint jprint = JasperFillManager.fillReport(report, parameters, new JREmptyDataSource());

            // Mostrar el informe generado en un visor
            JasperViewer viewer = new JasperViewer(jprint, false);
            viewer.setVisible(true);

        } catch (JRException e) {
            e.printStackTrace();
            mostrarError("Error al generar el informe", "No se pudo generar el informe.");
        }
    }

    /**
     * Metodo para verificar si el texto proporcionado es un número entero válido.
     *
     * @param texto El texto a verificar.
     * @return true si el texto es un número entero, false en caso contrario.
     */
    private boolean esNumeroEntero(String texto) {
        try {
            Integer.parseInt(texto);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Metodo para mostrar una ventana de error con el mensaje proporcionado.
     *
     * @param titulo El título de la ventana de error.
     * @param mensaje El mensaje de error que se mostrará.
     */
    private void mostrarError(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    /**
     * Metodo que se ejecuta al presionar el botón "Limpiar".
     * Limpia los campos del formulario.
     *
     * @param event El evento que dispara este metodo (presionar el botón).
     */
    @FXML
    void borrar(ActionEvent event) {
        txtNumPaciente.clear();
        txtNomPaciente.clear();
        txtDirPaciente.clear();
        txtCodMedico.clear();
        txtNomMedico.clear();
        txtEspMedico.clear();
        txaTratamiento.clear();
    }

    /**
     * Metodo que se ejecuta al presionar el botón "Salir".
     * Cierra la aplicación.
     *
     * @param event El evento que dispara este metodo (presionar el botón).
     */
    @FXML
    void salir(ActionEvent event) {
        System.exit(0);
    }
}
