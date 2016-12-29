import controllers.UserWindow;
import models.Text;
import models.User;
import network.UserConnection;
import utils.GameSetting;
import utils.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

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

    private ArrayList<Text> textArrayList;
    private boolean hasConnection;

    public GameWindow() {
        configUI();
        background = Utils.loadImage("resources/background5.jpg");
        thread = new Thread(this);
        thread.start();

        openUserWIndow();
        setupText();
    }

    private void setupText() {
        textArrayList = new ArrayList<>();
        UserConnection.instance.connect();
        hasConnection = UserConnection.instance.hasConnection();
        fillDataToText();
    }

    private void fillDataToText(){
        if(hasConnection){
            UserConnection.instance.connect();
            User[] topUsers = new User[5];
            topUsers = UserConnection.instance.getTopUsers();
            User user = null;
            Text text = null;
            for(int i = 0; i < 5; i++){
                user = topUsers[i];
                text = new Text(
                        550,
                        250 + i * 100,
                        user.getName()
                );
                textArrayList.add(text);
                text = new Text(
                        950,
                        250 + i * 100,
                        user.getScore()+""
                );
                textArrayList.add(text);
            }
        }else{
            textArrayList.add(new Text(500, 300, "No connection"));
        }
    }


    private void openUserWIndow() {
        UserWindow userWindow = new UserWindow();
        userWindow.setVisible(true);
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
        g.setFont(new Font("Tahoma", Font.BOLD, 24));
        Text text;
        if(hasConnection){
            for(int i = 0; i < 10; i++) {
                text = textArrayList.get(i);
                g.drawString(text.getContent(), text.getX(), text.getY());
            }
        }else{
            text = textArrayList.get(0);
            g.drawString(text.getContent(), text.getX(), text.getY());
        }
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
