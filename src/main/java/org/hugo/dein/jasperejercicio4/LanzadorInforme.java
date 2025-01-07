package org.hugo.dein.jasperejercicio4;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Clase principal de la aplicación que lanza la interfaz gráfica.
 * Esta clase extiende de {@link Application} para iniciar la aplicación JavaFX.
 * Se encarga de cargar y mostrar la ventana principal del formulario médico.
 */
public class LanzadorInforme extends Application {

    /**
     * Metodo de inicio de la aplicación. Carga el archivo FXML y configura la escena y el escenario.
     *
     * @param stage El escenario principal de la aplicación.
     * @throws IOException Si ocurre un error al cargar el archivo FXML.
     */
    @Override
    public void start(Stage stage) throws IOException {
        // Cargar el archivo FXML que define la interfaz gráfica
        FXMLLoader fxmlLoader = new FXMLLoader(LanzadorInforme.class.getResource("/fxml/ventana.fxml"));

        // Crear la escena a partir del archivo FXML cargado
        Scene scene = new Scene(fxmlLoader.load());

        // Configurar el título de la ventana
        stage.setTitle("Formulario medico");

        // Asignar la escena al escenario
        stage.setScene(scene);

        // Mostrar el escenario
        stage.show();
    }

    /**
     * Metodo principal que lanza la aplicación JavaFX.
     *
     * @param args Los argumentos de la línea de comandos.
     */
    public static void main(String[] args) {
        launch();
    }
}
