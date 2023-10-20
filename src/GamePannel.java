import javax.swing.*;
import java.awt.*;

public class GamePannel extends JPanel {

    public static final int WIDTH = 600;
    public static final int SIZE = WIDTH /3;

    //couleurs
    public static final Color BLACK_COLOR = Color.decode("#0F0F3B");
    public static final Color WHITE_COLOR = Color.decode("#B7C0eB");


    public void GamePannel(){
        this.setFocusable(true);

    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(WIDTH,WIDTH);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        for(int i = 0; i < WIDTH/SIZE; i ++){
            for(int j = 0; j < WIDTH/SIZE; j++){
                if((i + j) %2 == 0){
                    g2d.setColor(BLACK_COLOR);
                }else{
                    g2d.setColor(WHITE_COLOR);
                }
                g2d.fillRect(i*SIZE,j*SIZE,(i+1)*SIZE,(j+1)*SIZE);
            }
        }

    }
}
