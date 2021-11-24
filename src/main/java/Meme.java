import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;

public class Meme{
    static Meme m = new Meme();

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.println("Welcome to the meme generator. Here is our list of available memes." +
                " Which meme would you like to create?");
        String[] list = m.loadMemes();
        boolean g = true;
        String name = "";
        //keeps asking user for the name of a meme until it is found in the folder
        while (g){
            name = s.next();
            if (Arrays.asList(list).contains(name)) {
                System.out.println("loading " + name + "...");
                try
                {Image picture = ImageIO.read(new File("samples/" + name));}
                catch (IOException e) {e.printStackTrace();}
                g = false;
            }
            else{
                System.out.println("Please select a meme from the list:");
                for(String d: list){
                    System.out.println(d);
                }
            }
        }
        //asks for and saves the text for the meme
        System.out.print("text for the top of the meme: ");
        String topText = s.next();
        System.out.print("text for the bottom of the meme: ");
        String bottomText = s.next();

        System.out.println(topText + "  " + bottomText);

        //trying to show a pop up of the edited meme
        m.showImage(name, topText, bottomText);


    }

    //creates a pop-up with the meme template with the provided text added to it
    public void showImage(String name, String top, String bottom){
        JFrame frame = new JFrame();
        ImageIcon icon = new ImageIcon("samples/" + name);
        JLabel label = new JLabel(icon);
        frame.add(label);
        frame.setDefaultCloseOperation
                (JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    //looks through the sample folder and prints the names of all the available png files
    public String[] loadMemes(){
        File dir = new File("samples");
        String[] list = (dir.list(
                new FilenameFilter() {
                    @Override public boolean accept(File dir, String name) {
                        return name.endsWith(".png");
                    }
                }
        ));
        assert list != null;
        for(String l: list){
            System.out.println(l);
        }
        return list;
    }
}
