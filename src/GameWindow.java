import javax.swing.*;

public class GameWindow extends JFrame {

    private GamePannel gamePannel = new GamePannel();
    public GameWindow() {
        this.setTitle("Cavalier");
        this.setVisible(true);
        this.add(gamePannel);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();

        gamePannel.repaint();

    }

    public static void main(String[] args) {
        new GameWindow();
    }
}