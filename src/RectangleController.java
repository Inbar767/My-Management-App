import java.util.LinkedList;
import java.util.Random;

import javax.swing.JOptionPane;

import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Rectangle;
import javafx.scene.input.MouseEvent;

public class RectangleController {

    @FXML
    private Canvas canv;
    
    private LinkedList<Rectangle> rects1;
    private GraphicsContext gc1;
    private final int NUM = 10;
    private int lost;
    
	public void initialize() {
		gc1 = canv.getGraphicsContext2D();
		rects1 = new LinkedList<Rectangle>();
		insertRec();
	}
	
	private void insertRec()
	{
				Random rnd = new Random();
		for (int i = 0; i < NUM; i++) {
			int size = rnd.nextInt(91) + 10;
			rects1.add(new Rectangle(rnd.nextInt(400), rnd.nextInt(140),size, size));
		}
		fillRects();
	}
	
	@FXML
	void pressed(MouseEvent e) {
		Rectangle rs = getSmallest();
		if (rs.contains(new Point2D(e.getX(), e.getY()))) {
			removeSmallest();
			if(rects1.size() == 0)
			{
				gameIsOver();
			}
		}
		else {
			JOptionPane.showConfirmDialog(null, "not the smallest!",
					      "Error", JOptionPane.CLOSED_OPTION);
			lost++;
		}
		fillRects();
	}
	
	private void fillRects() {
		gc1.clearRect(0, 0, canv.getWidth(), canv.getHeight());

		for (int i = 0; i < rects1.size(); i++)
			gc1.strokeRect(rects1.get(i).getX(), rects1.get(i).getY(),
					 rects1.get(i).getWidth(), rects1.get(i).getHeight());
	}
	
	private Rectangle getSmallest() {
		if (rects1.size() == 0)
		{
			return null;
		}	
		Rectangle smallest = rects1.get(0);
		for (int i = 1; i < rects1.size(); i++) {
			if (rects1.get(i).getWidth() < smallest.getWidth()) 
				smallest = rects1.get(i);
		}
		return smallest;
	}

	private void removeSmallest( ) {
		rects1.remove(getSmallest());
	}
	
	private void gameIsOver()
	{
		JOptionPane.showConfirmDialog(null, "lost" + lost + "times",
			      "Error", JOptionPane.CLOSED_OPTION);
		insertRec();
		lost = 0;
	}
}
