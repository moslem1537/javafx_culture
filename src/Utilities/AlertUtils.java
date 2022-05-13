/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilities;

import javafx.scene.control.Alert;

/**
 *
 * @author takwa
 */
public class AlertUtils {

    public static void information(String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public static void erreur(String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public static void notice(String content) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Notice");
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
