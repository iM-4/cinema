import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Movie extends JFrame{
    JLabel
            movie1 =  new JLabel(),
            movie2 =  new JLabel(),
            movie3 =  new JLabel(),
            movie4 =  new JLabel(),
            movie5 =  new JLabel(),
            movie6 =  new JLabel(),
            movie7 =  new JLabel(),
            movie8 =  new JLabel(),
            movie9 =  new JLabel();
    ImageIcon
            m1,m100,
            m2,m200,
            m3,m300,
            m4,m400,
            m5,m500,
            m6,m600,
            m7,m700,
            m8,m800,
            m9,m900;





    static String movieName;

    public static String getMovieName(){
        return movieName;
    }


    public Movie(){
        setTitle("Movie");
        setSize(600,825);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(3,3,2,2));

        m1 = new ImageIcon("./Images/Joker2.png");
        m100 = new ImageIcon("./Images/joker3.png");

        m2 = new ImageIcon("./Images/TakeCover.png");
        m200 = new ImageIcon("./Images/take.png");

        m3 = new ImageIcon("./Images/Avengers3.png");
        m300 = new ImageIcon("./Images/Avengers300.png");

        m4 = new ImageIcon("./Images/TheSilentHour.png");
        m400 = new ImageIcon("./Images/the silent.png");

        m5 = new ImageIcon("./Images/PandaPlan.png");
        m500 = new ImageIcon("./Images/panda.png");

        m6 = new ImageIcon("./Images/Furiosa.png");
        m600 = new ImageIcon("./Images/madmax.png");

        m7 = new ImageIcon("./Images/CellarDoor.png");
        m700 = new ImageIcon("./Images/cellar.png");

        m8 = new ImageIcon("./Images/TheWildRobot.png");
        m800 = new ImageIcon("./Images/ropot2.png");

        m9 = new ImageIcon("./Images/oppenHeimer.png");
        m900 = new ImageIcon("./Images/open.png");

        setLabel(movie1,0,0);setLabel(movie2,0,1);setLabel(movie3,0,2);
        setLabel(movie4,1,0);setLabel(movie5,2,0);setLabel(movie6,3,0);
        setLabel(movie7,3,0);setLabel(movie8,3,0);setLabel(movie9,3,0);
        movie1.setIcon(m1);
        movie2.setIcon(m2);
        movie3.setIcon(m3);
        movie4.setIcon(m4);
        movie5.setIcon(m5);
        movie6.setIcon(m6);
        movie7.setIcon(m7);
        movie8.setIcon(m8);
        movie9.setIcon(m9);

        movie1.addMouseListener(new MouseAdapter() {
                                    @Override
                                    public void mouseEntered(MouseEvent e) {
                                        movie1.setIcon(m100);
                                    }
                                    public void mouseExited(MouseEvent e) {
                                        movie1.setIcon(m1);
                                    }
                                    public void mouseClicked(MouseEvent e) {
                                        movie1.setIcon(m1);
                                        movieName = "Joker";
                                        new Seat();
                                        System.out.println(movieName);
                                        setVisible(false);
                                    }
                                }
        );
        movie2.addMouseListener(new MouseAdapter() {
                                    @Override
                                    public void mouseEntered(MouseEvent e) {
                                        movie2.setIcon(m200);
                                    }
                                    public void mouseExited(MouseEvent e) {
                                        movie2.setIcon(m2);
                                    }
                                    public void mouseClicked(MouseEvent e) {
                                        movie2.setIcon(m2);
                                        movieName = "Take Cover";
                                        new Seat();
                                        setVisible(false);
                                    }
                                }
        );
        movie3.addMouseListener(new MouseAdapter() {
                                    @Override
                                    public void mouseEntered(MouseEvent e) {
                                        movie3.setIcon(m300);
                                    }
                                    public void mouseExited(MouseEvent e) {
                                        movie3.setIcon(m3);
                                    }
                                    public void mouseClicked(MouseEvent e) {
                                        movie3.setIcon(m3);
                                        movieName = "Avengers";
                                        new Seat();
                                        setVisible(false);
                                    }
                                }
        );
        movie4.addMouseListener(new MouseAdapter() {
                                    @Override
                                    public void mouseEntered(MouseEvent e) {
                                        movie4.setIcon(m400);
                                    }
                                    public void mouseExited(MouseEvent e) {
                                        movie4.setIcon(m4);
                                    }
                                    public void mouseClicked(MouseEvent e) {
                                        movie4.setIcon(m4);
                                        movieName = "The Silent Hour";
                                        new Seat();
                                        setVisible(false);
                                    }
                                }
        );
        movie5.addMouseListener(new MouseAdapter() {
                                    @Override
                                    public void mouseEntered(MouseEvent e) {
                                        movie5.setIcon(m500);
                                    }
                                    public void mouseExited(MouseEvent e) {
                                        movie5.setIcon(m5);
                                    }
                                    public void mouseClicked(MouseEvent e) {
                                        movie5.setIcon(m5);
                                        movieName = "Panda Plan";
                                        new Seat();
                                        setVisible(false);
                                    }
                                }
        );
        movie6.addMouseListener(new MouseAdapter() {
                                    @Override
                                    public void mouseEntered(MouseEvent e) {
                                        movie6.setIcon(m600);
                                    }
                                    public void mouseExited(MouseEvent e) {
                                        movie6.setIcon(m6);
                                    }
                                    public void mouseClicked(MouseEvent e) {
                                        movie6.setIcon(m6);
                                        movieName = "Furiosa";
                                        new Seat();
                                        setVisible(false);
                                    }
                                }
        );
        movie7.addMouseListener(new MouseAdapter() {
                                    @Override
                                    public void mouseEntered(MouseEvent e) {
                                        movie7.setIcon(m700);
                                    }
                                    public void mouseExited(MouseEvent e) {
                                        movie7.setIcon(m7);
                                    }
                                    public void mouseClicked(MouseEvent e) {
                                        movie7.setIcon(m7);
                                        movieName = "Cellar Door";
                                        new Seat();
                                        setVisible(false);
                                    }
                                }
        );
        movie8.addMouseListener(new MouseAdapter() {
                                    @Override
                                    public void mouseEntered(MouseEvent e) {
                                        movie8.setIcon(m800);
                                    }
                                    public void mouseExited(MouseEvent e) {
                                        movie8.setIcon(m8);
                                    }
                                    public void mouseClicked(MouseEvent e) {
                                        movie8.setIcon(m8);
                                        movieName = "The Wild Robot";
                                        new Seat();
                                        setVisible(false);
                                    }

                                }
        );
        movie9.addMouseListener(new MouseAdapter() {
                                    @Override
                                    public void mouseEntered(MouseEvent e) {
                                        movie9.setIcon(m900);
                                    }
                                    public void mouseExited(MouseEvent e) {
                                        movie9.setIcon(m9);
                                    }
                                    public void mouseClicked(MouseEvent e) {
                                        movie9.setIcon(m9);
                                        movieName = "Oppen Heimer";
                                        new Seat();
                                        setVisible(false);
                                    }
                                }
        );

        setTitle("Movies");
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void setLabel(JLabel label , int row , int column){
        label.setBackground(new Color(43, 43, 43));
        this.add(label,row,column);
        label.setOpaque(true);

    }
}







