import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import javax.swing.*;

public class Seat extends JFrame{
    ImageIcon
            green = new ImageIcon("./Images/chooser.png"),
            gray = new ImageIcon("./Images/free.png"),
            Screen = new ImageIcon("./Images/screen.png"),
            background = new ImageIcon("./Images/background.png"),
            screen_seat = new ImageIcon("./Images/screen_seats.png");

    JTextArea ShowSeat;

    static int count = 0;
    static int PriceTicket = 0;
    static ArrayList<String> Seats = new ArrayList<>();
    static String seats = null;

    JLabel
            a1 = new JLabel(gray), a2 = new JLabel(gray), a3 = new JLabel(gray), a4 = new JLabel(gray),
            a5 = new JLabel(gray), a6 = new JLabel(gray), a7 = new JLabel(gray), a8 = new JLabel(gray),
            a9 = new JLabel(gray), a10 = new JLabel(gray);

    JLabel
            b1 = new JLabel(gray), b2 = new JLabel(gray), b3 = new JLabel(gray), b4 = new JLabel(gray),
            b5 = new JLabel(gray), b6 = new JLabel(gray), b7 = new JLabel(gray), b8 = new JLabel(gray),
            b9 = new JLabel(gray), b10 = new JLabel(gray);

    JLabel
            c1 = new JLabel(gray), c2 = new JLabel(gray), c3 = new JLabel(gray), c4 = new JLabel(gray),
            c5 = new JLabel(gray), c6 = new JLabel(gray), c7 = new JLabel(gray), c8 = new JLabel(gray),
            c9 = new JLabel(gray), c10 = new JLabel(gray);

    JLabel back = new JLabel(background);

    JLabel[] array_a  = {a1,a2,a3,a4,a5,a6,a7,a8,a9,a10};
    JLabel[] array_b  = {b1,b2,b3,b4,b5,b6,b7,b8,b9,b10};
    JLabel[] array_c  = {c1,c2,c3,c4,c5,c6,c7,c8,c9,c10};

    List<JLabel> Seats_A = Arrays.asList(array_a);
    List<JLabel> Seats_B = Arrays.asList(array_b);
    List<JLabel> Seats_C = Arrays.asList(array_c);

    JLabel MovieName = new JLabel(Movie.getMovieName());
    JLabel Price = new JLabel("Price ");
    JLabel showPrice = new JLabel("");

    JLabel Tickets = new JLabel("Ticket");
    JLabel showTickets = new JLabel("");

    Color
            Yellow = new Color(255, 211, 105)
            ,TextColor = new Color(238, 238, 238);

    JLabel A = new JLabel("A");
    JLabel B = new JLabel("B");
    JLabel C = new JLabel("C");
    JLabel Back = new JLabel("< Back");
    JLabel seats_screen = new JLabel(screen_seat);

    JButton confirm = new JButton("Buy Tickets");

    AtomicInteger xAxis = new AtomicInteger(1);
    AtomicInteger TextSeats = new AtomicInteger(0);

    public Seat(){
        setTitle("Choose seat");
        setSize(700,400);

        setSeats(Seats_A, "A" , 80);
        setSeats(Seats_B, "B" , 150);  // Correct position for B seats
        setSeats(Seats_C, "C" , 220);  // Correct position for C seats

        setLayout(null);
        JLabel screen = new JLabel();
        screen.setIcon(Screen);
        screen.setBounds(50,-30,600,100);
        add(screen);

        seats_screen.setBounds(260,300,200,50);
        add(seats_screen);

        ShowSeat = new JTextArea(null,null,5,2);
        ShowSeat.setWrapStyleWord(true);
        ShowSeat.setOpaque(false);
        ShowSeat.setForeground(TextColor);
        ShowSeat.setFont(new Font("arial" , Font.BOLD , 15));
        ShowSeat.setBounds(260,300,200,50);
        ShowSeat.setEditable(false);
        ShowSeat.setLineWrap(true);
        add(ShowSeat);

        setLabel(Tickets , 180 , 290, 14 ,TextColor);
        setLabel(showTickets , 230 , 290, 14 ,TextColor);

        setLabel(A , 60 , 90, 20 ,TextColor);
        setLabel(B , 60 , 160, 20 ,TextColor);
        setLabel(C , 60 , 230, 20 ,TextColor);

        confirm.setBackground(TextColor);
        confirm.setBounds(470,310,100,30);

        confirm.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (count == 0) {
                    JOptionPane.showMessageDialog(null, "You should select one seat at least!");
                } else {
                    String[] split_seats  =  ShowSeat.getText().split(" ");
                    Seats.addAll(Arrays.asList(split_seats));
                    seats = Arrays.deepToString(Seats.toArray());
                    new Menu();
                    setVisible(false);
                }
            }

        });

        add(confirm);

        setLabel(MovieName , 300 , 20, 20 ,TextColor);
        setLabel(Price , 180 , 310, 14 ,TextColor);
        setLabel(showPrice , 220 , 310, 14 ,TextColor);
        setLabel2(Back , 10 , 330, 14 ,TextColor);

        back.setBounds(0,0,700,400);
        this.add(back);

        Back.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new Movie();
                count = 0;
                PriceTicket = 0;
                setVisible(false);
            }
            public void mouseEntered(MouseEvent e) {
                Back.setForeground(Yellow);
            }
            public void mouseExited(MouseEvent e) {
                Back.setForeground(TextColor);
            }

        });

        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void setLabel(JLabel label , int x , int y , int size , Color color){
        label.setFont(new Font("Arial" , Font.BOLD , size));
        label.setForeground(color);
        label.setBounds(x,y,500,30);
        add(label);
    }

    public void setLabel2(JLabel label , int x , int y , int size , Color color){
        label.setFont(new Font("Arial" , Font.BOLD , size));
        label.setForeground(color);
        label.setBounds(x,y,100,30);
        add(label);
    }

    public void setSeats(List<JLabel> Seats , String line , int y){
        Seats.forEach(
                (seat) -> {
                    int x = 1;
                    seat.setText(line + TextSeats.incrementAndGet());
                    seat.setBounds(x * (xAxis.incrementAndGet() * 50), y, 60, 60);
                    this.add(seat);
                    seat.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            if (seat.getIcon() == gray) {
                                // Select the seat
                                seat.setIcon(green);
                                if (!ShowSeat.getText().isEmpty()) {
                                    ShowSeat.append(","); // Add a comma if not the first seat
                                }
                                ShowSeat.append(seat.getText());
                                count += 1;
                                showTickets.setText(String.valueOf(count));
                                PriceTicket += 60;
                                showPrice.setText(PriceTicket + "$");
                            } else {
                                // Deselect the seat
                                seat.setIcon(gray);
                                count -= 1;
                                showTickets.setText(String.valueOf(count));
                                PriceTicket -= 60;
                                showPrice.setText(PriceTicket + "$");

                                // Remove the seat from ShowSeat
                                String seatsText = ShowSeat.getText();
                                List<String> seatList = new ArrayList<>(Arrays.asList(seatsText.split(",")));
                                seatList.remove(seat.getText()); // Remove the deselected seat
                                ShowSeat.setText(String.join(",", seatList)); // Update the text area
                            }
                        }
                    });
                });
        xAxis.setRelease(1);
        TextSeats.setRelease(0);
    }

    public static int getTickets() {
        return count;
    }

    public static int getPriceTicket() {
        return PriceTicket;
    }

    public static String getSeats() {
        return seats;
    }

}
