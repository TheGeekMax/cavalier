package tools;

public class Vector2Int {
    public int x,y;
    public Vector2Int(int x, int y){
        this.x = x;
        this.y = y;
    }

    public float moduleSqred(){
        return x*x + y*y;
    }
}