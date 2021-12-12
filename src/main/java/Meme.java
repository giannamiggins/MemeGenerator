import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.*;
import java.awt.image.BufferedImage;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;

import java.util.Arrays;
import java.util.Scanner;

public class Meme{
    static Meme m = new Meme();
    static int textSize = 20;

    public static void main(String[] args) throws IOException {
        Scanner s = new Scanner(System.in);
        Scanner s2 = new Scanner(System.in);
        System.out.println("Welcome to the meme generator. Would you like to Browse old memes or Create a new one?"+
                "please type browse or create.");
        String action = s.next();

        if (action.equals("create")) {
            System.out.println("Here is our list of available memes." +
                    " Which meme would you like to create?");
            String[] list = m.loadMemes();
            boolean g = true;
            String base = "";
            //keeps asking user for the name of a meme until it is found in the folder
            while (g) {
                base = s.next();
                if (Arrays.asList(list).contains(base)) {
                    System.out.println("loading " + base + "...");
                    try {
                        Image picture = ImageIO.read(new File("samples/" + base));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    g = false;
                } else {
                    System.out.println("Please select a meme from the list:");
                    for (String d : list) {
                        System.out.println(d);
                    }
                }
            }
            //asks for text size
            System.out.print("What's your preferred text size? (50 by default): ");
            s.nextLine();
            String txtsiz = s.nextLine();
            int txtSizInt = Integer.parseInt(txtsiz);
            System.out.println(txtSizInt);
            textSize = txtSizInt;
            
            //asks for font
            
                      
            //asks for and saves the text for the meme
            System.out.print("text for the top of the meme: ");
           
            String topText = s.nextLine();

            System.out.print("text for the bottom of the meme: ");
            String bottomText = s.nextLine();
            
           

            System.out.println("what would you like to name your meme? ");
            String name = s.nextLine();

            //trying to show a pop up of the edited meme
            //m.showImage(name);
            m.addText(base, name, topText, bottomText);
            m.showImage("output/" + name + ".png");
        }
        else{ //if user chooses browse
            System.out.println("Here is our list of previously made memes." +
                    " Which meme would you like to view?");
            String[] list = m.browse();
            boolean j = true;
            String base = "";
            //keeps asking user for the name of a meme until it is found in the folder
            while (j) {
                base = s.next();
                if (Arrays.asList(list).contains(base)) {
                    System.out.println("loading " + base + "...");
                    try {
                        Image picture = ImageIO.read(new File("output/" + base));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    j = false;
                } else {
                    System.out.println("Please select a meme from the list:");
                    for (String d : list) {
                        System.out.println(d);
                    }
                }
            }
            //show selected meme to browse
            m.showImage("output/" + base);
        }
    }

    //creates a pop-up with the meme template with the provided text added to it
    public void showImage(String base){
        JFrame frame = new JFrame();
        ImageIcon icon = new ImageIcon(base);
        JLabel label = new JLabel(icon);
        frame.add(label);
        frame.setDefaultCloseOperation
                (JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public void addText(String base, String name, String top, String bottom) throws IOException {
        Color color = Color.black;
        if (base.equals("spongebob.png")){
            color = Color.white;
        }
        BufferedImage img = ImageIO.read(new File("samples/" + base));
        Graphics g = img.getGraphics();
        g.setFont(new Font("TimesRoman", Font.PLAIN, textSize));
        g.setColor(color);
        g.drawString(top, 20, 60);
        g.drawString(bottom, 20, img.getHeight()-40);
        g.dispose();
        ImageIO.write(img, "png", new File("output/" + name + ".png"));
    }

    //looks through the sample folder and prints the names of all the available png files
    public String[] loadMemes(){
        File dir = new File("samples");
        String[] list = (dir.list(
                new FilenameFilter() {
                    @Override public boolean accept(File dir, String base) {
                        return base.endsWith(".png");
                    }
                }
        ));
        assert list != null;
        for(String l: list){
            System.out.println(l);
        }
        return list;
    }

    //looks through the output folder and prints the names of all the available png files
    public String[] browse(){
        File dir = new File("output");
        String[] list = (dir.list(
                new FilenameFilter() {
                    @Override public boolean accept(File dir, String base) {
                        return base.endsWith(".png");
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
