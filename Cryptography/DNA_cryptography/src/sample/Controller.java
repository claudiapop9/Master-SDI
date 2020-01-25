package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;


public class Controller {
    @FXML
    public TextField plain_txt;
    @FXML
    public TextField cipher_txt;
    @FXML
    public Button encrypt_btn;
    @FXML
    public Button decrypt_btn;


    public void encrypt() {

        if (validatePlainTxt(plain_txt.getText().split(""))) {

            cipher_txt.setText("ENCRYPTED");
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Wrong plain text! LETTERS MUST BE lowercase");
            alert.showAndWait();
        }
    }

    private Boolean validatePlainTxt(String[] txt) {

        for (String e : txt) {
            if (!e.matches("[a-z ]")) {
                return false;
            }
        }

        return true;
    }

    public void decrypt() {
        if (validateCipherTxt(cipher_txt.getText().split(""))) {
            plain_txt.setText("decrypted");
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Wrong cipher text! Letters must be UPPERCASE");
            alert.showAndWait();
        }
    }

    private Boolean validateCipherTxt(String[] txt) {

        for (String e : txt) {
            if (!e.matches("[A-Z ]")) {
                return false;
            }
        }

        return true;
    }
}
