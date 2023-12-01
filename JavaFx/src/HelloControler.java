import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Button;
import javafx.fxml.FXML;

import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.scene.layout.GridPane;

public class HelloControler {

    @FXML
    private ResourceBundle resources;

    @FXML
    private boolean inGame = true;

    private char currentPlayer = 'X';
    private char gameField[][] = new char[3][3];

    @FXML
    void btnClick(ActionEvent event) {
        Button btn = (Button)event.getSource();

        if (!inGame || btn.getText() != "") return;

        int rowIdx = GridPane.getRowIndex(btn) == null ? 0 : GridPane.getRowIndex(btn);
        int colIdx = GridPane.getColumnIndex(btn) == null ? 0 : GridPane.getColumnIndex(btn);

        gameField[rowIdx][colIdx] = currentPlayer;

        btn.setText(String.valueOf(currentPlayer));

        if (checkWinner(currentPlayer)) {
            showAlert("У нас є переможець, гравець " + btn.getText() + " Перемiг!!");
            inGame = false;
        } else if (isBoardFull()) {
            showAlert("Гра закінчилася в нічию!");
            inGame = false;
        } else {
            currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
        }
    }

    private boolean checkWinner(char symbol) {
        for (int i = 0; i < 3; i++) {
            if ((gameField[i][0] == symbol && gameField[i][1] == symbol && gameField[i][2] == symbol) ||
                (gameField[0][i] == symbol && gameField[1][i] == symbol && gameField[2][i] == symbol)) {
                return true;
            }
        }

        return (gameField[0][0] == symbol && gameField[1][1] == symbol && gameField[2][2] == symbol) ||
               (gameField[0][2] == symbol && gameField[1][1] == symbol && gameField[2][0] == symbol);
    }

    private boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (gameField[i][j] == '\0') {
                    return false;
                }
            }
        }
        return true;
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, message, ButtonType.OK);
        alert.showAndWait();
    }

    @FXML
    void initialize() {
    }
}




