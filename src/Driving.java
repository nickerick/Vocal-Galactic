import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.*;

public class Driving {
    private static JFrame Game = new JFrame();
    public static Panel1 play = new Panel1();
    private static Panel2 title = new Panel2();
    private static Panel3 tutorial = new Panel3();
    private static  Panel4 settings = new Panel4();
    
	public static void main(String[] args) throws IOException {
	String str = "Not Found";
	BufferedWriter writer = new BufferedWriter(new FileWriter("src\\link.txt"));
	writer.write(str);
	writer.close();
	    
        MusicPlayer.playMusic();
	    
        Game.getContentPane().add(title);
        Game.pack();
        Game.setVisible(true);
        Game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Game.setResizable(false);
    }

    public static void switchToGame()
    {
        Game.setVisible(false);
        Game.getContentPane().remove(title);
        Game.getContentPane().add(play);
        Game.pack();
        Game.setVisible(true);
    }

    public static void switchToTitle()
    {
        Game.setVisible(false);
        Game.getContentPane().remove(play);
        Game.getContentPane().remove(tutorial);
        Game.getContentPane().remove(settings);
        Game.getContentPane().add(title);
        Game.pack();
        Game.setVisible(true);
        play = new Panel1();
    }

    public static void switchToSettings() {
        Game.setVisible(false);
        Game.getContentPane().remove(title);
        Game.getContentPane().add(settings);
        Game.pack();
        Game.setVisible(true);
    }
    
    public static void switchToTutorial(){
        Game.setVisible(false);
        Game.getContentPane().remove(title);
        Game.getContentPane().add(tutorial);
        Game.pack();
        Game.setVisible(true);
    }
    
}

