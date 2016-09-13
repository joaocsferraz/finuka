package br.ufjf.dcc.finuka;

/**
 * Created by LucasRezende on 23/07/2016.
 */
public class Objeto {

    private float x;
    private float y;
    private float deltax;
    private float deltay;

    public float getDeltax() {
        return deltax;
    }

    public void setDeltax(float deltax) {
        this.deltax = deltax;
    }

    public float getDeltay() {
        return deltay;
    }

    public void setDeltay(float deltay) {
        this.deltay = deltay;
    }

    private float height;
    private float width;
    private float vy;
    private float ay;
    private float vx;
    private float ax;

    public Objeto() {
    }


    public Objeto(float x, float y, float height, float width) {
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
        this.vy = 0;
        this.ay = 0;
        this.vx = 0;
        this.ax = 0;

    }

    public boolean ColideObjects(Objeto p){
        //player a direita
        if(p.getX() > x + width)return false;
        //player abaixo
        if(p.getY() > y + height)return false;
        //player a esquerda
        if(p.getX() + width < x)return false;
        //player acima
        if(p.getY() + height < x)return false;
        //existe colisao
        return true;
    }

    public boolean ColidePoints(int x2, int y2){
        if(x2 > x + width)return false;
        if(y2 > y + height)return false;
        if(x2 < x)return false;
        if(y2 < x)return false;
        //existe colisao
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
