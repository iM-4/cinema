
import javax.swing.*;
import java.awt.*;

public class frameDetails extends JFrame {

    ImageIcon background_img = new ImageIcon("./Images/background20.png");
    JLabel background = new JLabel(background_img);

    JLabel
            showUserName = new JLabel("User Name : "),
            showMovieName= new JLabel("Movie : "),
            showTickets = new JLabel("Tickets : "),
            showSeats = new JLabel("Seats :"),
            showTotal = new JLabel("Total : "),

             UserName = new JLabel(login.getUserName()),
            MovieName= new JLabel(Movie.getMovieName()),
            Tickets = new JLabel(String.valueOf(Seat.getTickets())),
            Seats = new JLabel(Seat.getSeats()),
            Total = new JLabel(Seat.getPriceTicket() + Menu.getSubTotal() + "$");

    static double
            total = Seat.getPriceTicket() + getTotal();

    Color
            TextColor = new Color(238, 238, 238);

    public frameDetails(){
        setTitle("Details");
        setSize(250,220);
        setLayout(null);
        setResizable(false);

        setLabel(showUserName, UserName, 5,10,14,TextColor);
        setLabel(showMovieName, MovieName, 5,40,14,TextColor);
        setLabel(showTickets,Tickets, 5,70,14,TextColor);
        setSeat(showSeats,Seats, 5,100,14,TextColor);
        setLabel(showTotal,Total, 5,150,14,TextColor);

        background.setBounds(0,0,250,200);
        add(background);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void setLabel(JLabel label, JLabel label2, int x , int y , int size , Color color){
        label.setFont(new Font("Arial" , Font.BOLD , size));
        label.setForeground(color);
        label.setBounds(x,y,100,35);
        this.add(label);
        label2.setFont(new Font("Arial" , Font.BOLD , size));
        label2.setForeground(color);
        label2.setBounds(x+90,y,200,35);
        this.add(label2);
    }

    public void setSeat(JLabel label, JLabel label2, int x , int y , int size , Color color){
        label.setFont(new Font("Arial" , Font.BOLD , size));
        label.setForeground(color);
        label.setBounds(x,y,100,35);
        this.add(label);
        label2.setFont(new Font("Arial" , Font.BOLD , size));
        label2.setForeground(color);
        label2.setBounds(x,y+20,300,35);
        this.add(label2);
    }

    public static double getTotal(){
        return total;
    }

}