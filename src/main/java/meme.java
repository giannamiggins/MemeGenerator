import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;

public class meme extends IOException{
    static meme m = new meme();

    ArrayList<String> memes = new ArrayList<>();

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.println("Welcome to the meme generator. Here is our list of available memes." +
                " Which meme would you like to create?");
        String[] list = m.loadMemes();
        boolean g = true;
        String name = "";
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
        System.out.print("text for the top of the meme: ");
        String topText = s.next();
        System.out.print("text for the bottom of the meme: ");
        String bottomText = s.next();

        System.out.println(topText + "  " + bottomText);

        m.showImage(name, topText, bottomText);


    }

    public void getMemes(){
        for(int i=0; i< memes.size(); i++){
            System.out.println(i + ". " + memes.get(i));
        }
    }

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

    public String[] loadMemes(){
        File dir = new File("samples");
        String[] list = (dir.list(
                new FilenameFilter() {
                    @Override public boolean accept(File dir, String name) {
                        return name.endsWith(".png"); //change this to .png
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
