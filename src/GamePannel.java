import tools.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GamePannel extends JPanel {

    public static final int WIDTH = 256*3;
    public static final int TAB_WIDTH = 3;
    public static final int SIZE = WIDTH / TAB_WIDTH;

    //couleurs
    public static final Color BLACK_COLOR = Color.decode("#2F2F5B");
    public static final Color WHITE_COLOR = Color.decode("#B7C0eB");

    private PictureManager pictureManager;


    //variables relative a la selection
    private boolean selected = false;
    private Vector2Int selectedCoors;

    private int[][] plateau = new int[TAB_WIDTH][TAB_WIDTH];
    private int[][] winPlateau = new int[TAB_WIDTH][TAB_WIDTH];

    /*
    * 0 - vide
    * 1 - blanc
    * 2 - noir
    * */

    public GamePannel(){
        //on initialise les tableaux
        pictureManager = new PictureManager(getClass().getResourceAsStream("/assets/pawns.png"),64);

        pictureManager.addFromPicture("white",0,0);
        pictureManager.addFromPicture("black",1,0);

        //on initialise le tableau
        for(int i = 0; i < TAB_WIDTH; i++){
            for(int j = 0; j < TAB_WIDTH; j ++){
                if((i == 0 && j == 0) || (i == TAB_WIDTH-1 && j == 0)){
                    plateau[i][j] = 1;
                    winPlateau[i][j] = 2;
                }else if((i == 0 && j == TAB_WIDTH -1) || (i == TAB_WIDTH-1 && j == TAB_WIDTH-1)){
                    plateau[i][j] = 2;
                    winPlateau[i][j] = 1;
                }else{
                    plateau[i][j] = 0;
                    winPlateau[i][j] = 0;
                }
            }
        }

        this.setFocusable(true);
        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                Vector2Int coors = new Vector2Int(mouseEvent.getX() / SIZE,mouseEvent.getY()/SIZE);
                if(selected){
                    //on execute la machine
                    Vector2Int[] relativeCoors = {new Vector2Int(-2,-1),new Vector2Int(-2,+1),
                                                  new Vector2Int(-1,-2),new Vector2Int(+1,-2),
                                                  new Vector2Int(+2,-1),new Vector2Int(+2,+1),
                                                  new Vector2Int(-1,+2),new Vector2Int(+1,+2),
                                                  new Vector2Int(0,0)};
                    for(int i = 0; i < 9; i ++){
                        if(selectedCoors.x+relativeCoors[i].x == coors.x && selectedCoors.y+relativeCoors[i].y == coors.y &&
                                plateau[coors.x][coors.y] == 0){

                            i = 10;
                            int value = plateau[selectedCoors.x][selectedCoors.y];
                            plateau[selectedCoors.x][selectedCoors.y] = 0;
                            plateau[coors.x][coors.y] = value;

                            if(isWin()){
                                System.out.print("TA GAGNÉ LOLILOL");
                            }
                        }
                    }
                    selected = false;
                    repaint();
                }else{
                    if(plateau[coors.x][coors.y] != 0) {
                        selectedCoors = coors;
                        selected = true;
                        repaint();
                    }
                }


            }

            @Override
            public void mousePressed(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseReleased(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseEntered(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {

            }
        });
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
                g2d.fillRect(i*SIZE,j*SIZE,SIZE,SIZE);

                if(plateau[i][j] == 1){
                    g2d.drawImage(pictureManager.getBufferedPictureFromName("white"),i*SIZE,j*SIZE,SIZE,SIZE,null);
                }else if (plateau[i][j] == 2){
                    g2d.drawImage(pictureManager.getBufferedPictureFromName("black"),i*SIZE,j*SIZE,SIZE,SIZE,null);
                }
            }
        }

        if(selected){
            int offset = 40;
            g2d.setColor(Color.red);
            g2d.fillRect(selectedCoors.x*SIZE + offset, selectedCoors.y *SIZE + offset,SIZE - 2*offset,SIZE - 2*offset);
        }

    }


    private boolean isWin(){
        for(int i = 0; i < TAB_WIDTH; i++){
            for(int j = 0; j < TAB_WIDTH; j++){
                if(plateau[i][j] != winPlateau[i][j]) return false;
            }
        }
        return true;
    }
}
