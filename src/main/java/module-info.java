module org.hugo.dein.jasperejercicio4 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires net.sf.jasperreports.core;


    opens org.hugo.dein.jasperejercicio4 to javafx.fxml;
    opens org.hugo.dein.jasperejercicio4.Controller to javafx.fxml;

    exports org.hugo.dein.jasperejercicio4;
}
