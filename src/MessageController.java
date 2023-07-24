/*import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class MessageController {

    @FXML
    private TextField op1;

    @FXML
    private TextField op2;

    @FXML
    private TextField op3;

    @FXML
    private ComboBox<String> type;

    public void initialize() {
    	type.setItems(FXCollections.observableArrayList("+", "-", "*"));
    }
    
    @FXML
    void calculate(ActionEvent event) {
    	int x1 , x2, result;
    	x1 = Integer.parseInt(op1.getText());
    	x2 = Integer.parseInt(op2.getText());
    	if(type.getValue().equals("+"))
    		result = x1 + x2;
    	else if(type.getValue().equals("-"))
    		result = x1 - x2;
    	else
    		result = x1 * x2;
    	op3.setText(result + "");
    }

}
*/

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;

public class MessageController {

    @FXML
    private Canvas canv;
    
    private GraphicsContext gc;
    
    public void initialize() {
    	gc = canv.getGraphicsContext2D();
    }

    @FXML
    void clickPress(MouseEvent event) {
    	gc.clearRect(0, 0, canv.getWidth(), canv.getHeight());
    	gc.fillText("clicked", event.getX(), event.getY());
    }

    @FXML
    void dragPress(MouseEvent event) {
    	gc.clearRect(0, 0, canv.getWidth(), canv.getHeight());
    	gc.fillText("Dragged", event.getX(), event.getY());
    }

    @FXML
    void movePress(MouseEvent event) {
    	gc.clearRect(0, 0, canv.getWidth(), canv.getHeight());
    	gc.fillText("Moved", event.getX(), event.getY());
    }

}