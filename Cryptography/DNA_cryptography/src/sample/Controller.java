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
    public TextField key_txt;
    @FXML
    public Button encrypt_btn;
    @FXML
    public Button decrypt_btn;

    /*
        Pasii algoritmului:
        1. Generare/obtinere cheie
        2. Aplicare cheie, ex. XOR
        3. Codificare mesaj(binar->adn)
    */

    //pentru criptare
    public void encrypt() {
        //citim valoarea
        String plain_text=plain_txt.getText();
        //validam input, sa simplificam prelucrarea
        if (validatePlainTxt(plain_text)) {
            //convertim in binar
            //puteam la fel de bine sa aplicam la nivel de caractere, dar ar fi trebuit sa transformam caracterele generate prin xor in binar pentru generare adn
            String binary = Util.AsciiToBinary(plain_text);
            //obtinem cheia
            String key = key_txt.getText();
            //daca nu am primit cheie
            if(key.equals(""))
                //generam una
                key = Util.getKey(plain_text.length());

            //o transformam in binar
            String binary_key = Util.AsciiToBinary(key);
            //si o aplicam sirului dat pentru criptat
            String encrypted = Util.xor(binary, binary_key);

            //transformam informatia in adn
            encrypted = Util.getDna(encrypted);

            //si afisam rezultatul
            cipher_txt.setText(encrypted);
            key_txt.setText(key);
        } else {
            //daca datele oferite nu sunt corecte, dam un mesaj
            Alert alert = new Alert(Alert.AlertType.WARNING, "Wrong plain text!");
            alert.showAndWait();
        }
    }

    private Boolean validatePlainTxt(String txt) {
            //verificam daca am primit un sir de litere, mari si mici
//        for (String e : txt) {
            if (!txt.matches("[a-zA-Z 0-9]*")) {
                return false;
            }
//        }

        return true;
    }

    //pentru decriptare
    public void decrypt() {
        //citim o valoare data
        String encrypted=cipher_txt.getText();

        //vedem daca valoarea este bine formatata
        if (validateCipherTxt( encrypted )) {
            //citim cheia data
            String key = key_txt.getText();
            //daca nu am primit cheie
            if(key.equals("")){
                //e cam greu de descifrat mesajul
                Alert alert = new Alert(Alert.AlertType.WARNING, "Missing key!");
                alert.showAndWait();
            }

            //interpretam sirul adn
            encrypted = Util.getData(encrypted);

            //transformam cheia in binar
            String binary_key = Util.AsciiToBinary(key);
            //aplicam cheia sirului desfacut
            String decrypted = Util.xor(encrypted, binary_key);

            //si afisam rezultatul
            plain_txt.setText( Util.BinaryToString(decrypted) );
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Wrong cipher text!");
            alert.showAndWait();
        }
    }

    private Boolean validateCipherTxt(String txt) {
        //Mesajul codificat ar trebui sa contina numai valorile A, T, C si G
        //for (String e : txt) {
            if (!txt.matches("[ATCG]*")) {
            //if (!txt.matches("[a-zA-Z]*")) {
                return false;
            }
        //}

        return true;
    }
}
