package models;

/**
 * Created by tungb on 12/29/2016.
 */
public class Text {
    private int x;
    private int y;

    private String content;

    public Text(int x, int y, String content) {
        this.x = x;
        this.y = y;
        this.content = content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
