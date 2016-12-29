import utils.GameSetting;
import utils.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;

/**
 * Created by tungb on 12/29/2016.
 */
public class GameWindow extends Frame implements Runnable{
    private Image background;
    private TextField etName;
    private TextField etScore;
    private Button btSubmit;

    BufferedImage bufferedImage;
    Graphics bufferImageGraphic;
    Thread thread;

    public GameWindow() {
        configUI();
        background = Utils.loadImage("resources/background5.jpg");
        thread = new Thread(this);
        thread.start();
    }

    public void configUI(){
        this.setTitle(GameSetting.NAME);
        this.setResizable(true);
        this.bufferedImage = new BufferedImage(
                GameSetting.SCREEN_WIDTH,
                GameSetting.SCREEN_HEIGHT,
                BufferedImage.TYPE_INT_ARGB);
        this.bufferImageGraphic = bufferedImage.getGraphics();
        this.setVisible(true);
        this.setLocation(0, 0);
        this.setSize(GameSetting.SCREEN_WIDTH, GameSetting.SCREEN_HEIGHT);
        this.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }

            @Override
            public void windowClosed(WindowEvent e) {

            }

            @Override
            public void windowIconified(WindowEvent e) {

            }

            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {

            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }
        });
    }

    @Override
    public void update(Graphics g) {
        g.drawImage(background, 0, 0, null);
    }

    @Override
    public void run() {
        while (true){
            try {
                repaint();
                thread.sleep(17);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
