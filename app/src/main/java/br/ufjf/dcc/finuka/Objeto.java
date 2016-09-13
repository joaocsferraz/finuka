package br.ufjf.dcc.finuka;

public class Objeto {

    private float x;
    private float y;
    private float deltaX;
    private float deltaY;

    public float getDeltaX() {
        return deltaX;
    }

    public void setDeltaX(float deltaX) {
        this.deltaX = deltaX;
    }

    public float getDeltaY() {
        return deltaY;
    }

    public void setDeltaY(float deltaY) {
        this.deltaY = deltaY;
    }

    private float height;
    private float width;
    private float vy = 0.0f;
    private float ay = 0.0f;
    private float vx = 0.0f;
    private float ax = 0.0f;

    public Objeto() {
        this(0.0f, 0.0f, 5.0f, 5.0f);
    }


    public Objeto(float x, float y, float height, float width) {
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;

    }

    public boolean isRectCollidedWith(Objeto p){
        //player a direita
        if(p.getX() > this.x + width)
            return false;
        //player abaixo
        if(p.getY() > this.y + this.height)
            return false;
        //player a esquerda
        if(p.getX() + p.getWidth() < this.x)
            return false;
        //player acima
        if(p.getY() + p.getHeight() < this.y)
            return false;
        //existe colisao
        return true;
    }

    public boolean isPointCollided(int x, int y){
        if(x > this.x + this.width)
            return false;
        if(y > this.y + this.height)
            return false;
        if(x < this.x)
            return false;
        if(y < this.x)
            return false;
        return true;
    }


    public float getAy() {
        return ay;
    }

    public void setAy(float ay) {
        this.ay = ay;
    }

    public float getVy() {
        return vy;
    }

    public void setVy(float vy) {
        this.vy = vy;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getVx() {
        return vx;
    }

    public void setVx(float vx) {
        this.vx = vx;
    }

    public float getAx() {
        return ax;
    }

    public void setAx(float ax) {
        this.ax = ax;
    }
}
