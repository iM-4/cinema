import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.*;
import java.time.format.DateTimeFormatter;

public class Menu extends JFrame {
    ImageIcon
            background_img = new ImageIcon("./Images/background2.png"),
            sP = new ImageIcon("./Images/smallPepsi.png"),
            water = new ImageIcon("./Images/water.png"),
            pOp = new ImageIcon("./Images/popcorn.png"),
            cupCorn = new ImageIcon("./Images/cupcorn.png"),
            nacHos = new ImageIcon("./Images/nachos.png"),
            Seven = new ImageIcon("./Images/7up.png");
    Image
            resizesmallPepsi = sP.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH),
            resizeWater = water.getImage().getScaledInstance(40, 65, Image.SCALE_SMOOTH),
            resizepop = pOp.getImage().getScaledInstance(40, 65, Image.SCALE_SMOOTH),
            resizecup = cupCorn.getImage().getScaledInstance(40, 65, Image.SCALE_SMOOTH),
            resizenachos = nacHos.getImage().getScaledInstance(40, 65, Image.SCALE_SMOOTH),
            resize7upp = Seven.getImage().getScaledInstance(40, 65, Image.SCALE_SMOOTH);
    ImageIcon
            smallPepsi = new ImageIcon(resizesmallPepsi),
            waterpic = new ImageIcon(resizeWater),
            popcorne = new ImageIcon(resizepop),
            cupc = new ImageIcon(resizecup),
            nachose = new ImageIcon(resizenachos),
            sefen = new ImageIcon(resize7upp);
    JLabel background = new JLabel(background_img);

    public JLabel smallPep = new JLabel(smallPepsi), Water = new JLabel(waterpic), ppp = new JLabel(popcorne), cpp = new JLabel(cupc), nashos = new JLabel(nachose), seveen = new JLabel(sefen);
    public JLabel
            popLabel,
            cupLabel,
            nahLabel,
            pepsiLabel,
            sevenLabel,
            waterLabel,
            subtotalLabel;
    public JButton calculateBill, Back, details = new JButton("Details"), confrim = new JButton("Confirm Booking");
    public JSpinner pop_counter, cup_counter, nah_counter, pep_counter, seven_counter, water_counter;
    public JPanel jPanel1, jPanel2, jPanel3;

    JLabel thanks = new JLabel("Thank you for using Cinema Jeddah App");

    private double PopPrice, CupPrice, NahPrice, PepPrice, SevPrice, WaterPrice;
    private static double subTotal;

    public double
            smallPop_cost = 10.0, largePop_cost = 15.0,
            cup_cost = 5.0,
            largeNah_cost = 18.0, smallnachos_cost = 12.0,
            smallPepsi_cost = 8.0, largePepsi_cost = 15.0,
            smallSevenUP_cost = 7.0, largeSevenUP_cost = 12.0,
            smallWater_cost = 3.0, largeWater_cost = 6.0;

    Font panelFont = new Font("Dialog", Font.BOLD, 18);
    Color Yellow = new Color(255, 211, 105), TextColor = new Color(238, 238, 238);

    String[] sizes = {"Small", "Large"};
    JComboBox<String> pepsiSize = new JComboBox<>(sizes), sevenSize = new JComboBox<>(sizes),
            waterSize = new JComboBox<>(sizes), popSize = new JComboBox<>(sizes),
            cupSize = new JComboBox<>(sizes), nachSize = new JComboBox<>(sizes);

    public Menu() {
        setSize(1000, 700);
        setTitle("Food And Drinks");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        jPanel1 = new JPanel(null);
        jPanel1.setBounds(570, 100, 350, 315);
        jPanel1.setOpaque(false);
        TitledBorder foodBorder = BorderFactory.createTitledBorder(null, "Food");
        foodBorder.setTitleFont(panelFont);
        foodBorder.setTitleColor(Color.decode("#e4b718"));
        jPanel1.setBorder(foodBorder);

        JLabel jLabel2 = new JLabel("Popcorn");
        pop_counter = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));
        popLabel = new JLabel("0.0 SR");
        setComponents(jLabel2, 20, 30, pop_counter, popLabel, jPanel1);

        JLabel jLabel3 = new JLabel("Cup corn");
        cup_counter = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));
        cupLabel = new JLabel("0.0 SR");
        setComponents(jLabel3, 20, 140, cup_counter, cupLabel, jPanel1);
        jLabel3.setBounds(20, 160, 100, 24);

        JLabel jLabel4 = new JLabel("Nachos");
        nah_counter = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));
        nahLabel = new JLabel("0.0 SR");
        setComponents(jLabel4, 20, 240, nah_counter, nahLabel, jPanel1);

        setCombo(popSize, cupSize, nachSize, jPanel1, 20, 60);
        cupSize.setVisible(false);

        jPanel3 = new JPanel(null);
        TitledBorder totalBorder = BorderFactory.createTitledBorder(null, "Total");
        totalBorder.setTitleFont(panelFont);
        totalBorder.setTitleColor(Color.decode("#e4b718"));
        jPanel3.setBorder(totalBorder);
        jPanel3.setBounds(100, 450, 300, 150);

        subtotalLabel = new JLabel("TOTAL: 0.0 SR");
        subtotalLabel.setBounds(10, 30, 250, 24);
        subtotalLabel.setFont(new Font("Dialog", Font.BOLD, 20));
        subtotalLabel.setForeground(TextColor);
        jPanel3.add(subtotalLabel);

        calculateBill = new JButton("Calculate Total");
        calculateBill.setBounds(10, 80, 150, 30);
        calculateBill.setFont(new Font("Dialog", Font.BOLD, 15));
        jPanel3.add(calculateBill);
        jPanel3.setOpaque(false);

        jPanel2 = new JPanel(null);
        TitledBorder drinksBorder = BorderFactory.createTitledBorder(null, "Drinks");
        drinksBorder.setTitleFont(panelFont);
        drinksBorder.setTitleColor(Color.decode("#e4b718"));
        jPanel2.setBorder(drinksBorder);
        jPanel2.setBounds(100, 100, 350, 315);
        jPanel2.setOpaque(false);

        JLabel jLabel8 = new JLabel("Pepsi");
        pep_counter = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));
        pepsiLabel = new JLabel("0.0 SR");
        setComponents(jLabel8, 20, 30, pep_counter, pepsiLabel, jPanel2);

        JLabel jLabel9 = new JLabel("7 Up");
        seven_counter = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));
        sevenLabel = new JLabel("0.0 SR");
        setComponents(jLabel9, 20, 140, seven_counter, sevenLabel, jPanel2);

        JLabel jLabel10 = new JLabel("Water");
        water_counter = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));
        waterLabel = new JLabel("0.0 SR");
        setComponents(jLabel10, 20, 240, water_counter, waterLabel, jPanel2);

        setCombo(pepsiSize, sevenSize, waterSize, jPanel2, 20, 60);


        Back = new JButton("< Back");
        Back.setBounds(0, 630, 90, 40);
        Back.setFont(new Font("Dialog", Font.BOLD, 15));
        Back.setForeground(TextColor);
        Back.setContentAreaFilled(false);
        Back.setBorderPainted(false);
        Back.setOpaque(false);

        add(jPanel1);
        add(jPanel2);
        add(jPanel3);
        add(Back);

        thanks.setFont(new Font("Dialog", Font.BOLD, 13));
        thanks.setBounds(400, 520, 1000, 40);
        this.add(thanks);
        thanks.setForeground(TextColor);
        thanks.setVisible(false);

        // Create confirmation label
        JLabel done = new JLabel("<html>Your booking has been successfully confirmed!<br>" +
                "We look forward to serving you.</html>");
        done.setBounds(400, 485, 1000, 40);  // Adjust width for full message
        // done.setFont(new Font("Dialog", Font.BOLD, 15));
        done.setForeground(Color.GREEN);
        this.add(done);
        done.setVisible(false);


        setButton(details, 700, 490);
        setButton(confrim, 700, 550);

        pop_counter.addChangeListener(_ -> updatePrice());
        cup_counter.addChangeListener(_ -> updatePrice());
        nah_counter.addChangeListener(_ -> updatePrice());
        pep_counter.addChangeListener(_ -> updatePrice());
        seven_counter.addChangeListener(_ -> updatePrice());
        water_counter.addChangeListener(_ -> updatePrice());

        pepsiSize.addActionListener(_ -> updatePrice());
        sevenSize.addActionListener(_ -> updatePrice());
        waterSize.addActionListener(_ -> updatePrice());
        popSize.addActionListener(_ -> updatePrice());
        nachSize.addActionListener(_ -> updatePrice());

        details.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                new frameDetails();
            }

        });
        calculateBill.addActionListener(_ -> calculateBillActionPerformed());
        confrim.addActionListener(_ -> {
            try {
                File file = new File("Bookings.txt");
                FileWriter write = new FileWriter(file, true);
                BufferedWriter bw = new BufferedWriter(write);

                // Write the booking details to the file
                String format = "Username : " + login.getUserName()
                        + "\nEmail : " + login.getEmail()
                        + "\nMovie Name : " + Movie.getMovieName()
                        + "\nNumber of tickets : " + Seat.getTickets()
                        + "\nNumber of seats : " + Seat.getSeats()
                        + "\nDate of Booking : " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
                        + "\n" + "-".repeat(20);
                bw.write(format);
                bw.newLine();
                bw.close();

                done.setVisible(true);
                thanks.setVisible(true);
                // Add label to the container and refresh
                revalidate();  // Refresh layout to show new label
                repaint();     // Redraw the component

                // Disable the button to prevent multiple clicks
                confrim.setEnabled(false);

            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });


        Back.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new Seat();
                Seat.Seats.clear();
                setVisible(false);
            }

            public void mouseEntered(MouseEvent e) {
                Back.setForeground(Yellow);
            }

            public void mouseExited(MouseEvent e) {
                Back.setForeground(TextColor);
            }

        });
        smallPep.setBounds(130, 30, 60, 80);
        jPanel2.add(smallPep);
        seveen.setBounds(140, 130, 45, 100);
        jPanel2.add(seveen);


        Water.setBounds(120, 220, 80, 100);
        jPanel2.add(Water);
        background.setBounds(0, 0, 1000, 700);
        add(background);
        setVisible(true);


        ppp.setBounds(140, 10, 80, 100);
        jPanel1.add(ppp);

        cpp.setBounds(140, 110, 80, 100);
        jPanel1.add(cpp);
        nashos.setBounds(135, 230, 90, 70);
        jPanel1.add(nashos);

    }



    private void updatePrice() {
        // Update prices based on selected sizes
        PopPrice = pop_counter.getValue() != null && popSize.getSelectedItem().equals("Large") ?
                largePop_cost * (int) pop_counter.getValue() : smallPop_cost * (int) pop_counter.getValue();
        CupPrice = cup_cost * (int) cup_counter.getValue();
        NahPrice = nah_counter.getValue() != null && nachSize.getSelectedItem().equals("Large") ?
                largeNah_cost * (int) nah_counter.getValue() : smallnachos_cost * (int) nah_counter.getValue();
        PepPrice = pep_counter.getValue() != null && pepsiSize.getSelectedItem().equals("Large") ?
                largePepsi_cost * (int) pep_counter.getValue() : smallPepsi_cost * (int) pep_counter.getValue();
        SevPrice = seven_counter.getValue() != null && sevenSize.getSelectedItem().equals("Large") ?
                largeSevenUP_cost * (int) seven_counter.getValue() : smallSevenUP_cost * (int) seven_counter.getValue();
        WaterPrice = water_counter.getValue() != null && waterSize.getSelectedItem().equals("Large") ?
                largeWater_cost * (int) water_counter.getValue() : smallWater_cost * (int) water_counter.getValue();

        // Update labels
        popLabel.setText(PopPrice + " SR");
        cupLabel.setText(CupPrice + " SR");
        nahLabel.setText(NahPrice + " SR");
        pepsiLabel.setText(PepPrice + " SR");
        sevenLabel.setText(SevPrice + " SR");
        waterLabel.setText(WaterPrice + " SR");
    }

    private void calculateBillActionPerformed() {
        subTotal = PopPrice + CupPrice + NahPrice + PepPrice + SevPrice + WaterPrice;
        subtotalLabel.setText("TOTAL: " + subTotal + " SR");
    }

    public void setComponents(JLabel mainLabel, int x, int y, JSpinner counter, JLabel sub, JPanel panel) {
        mainLabel.setBounds(x, y, 100, 24);
        mainLabel.setFont(new Font("Dialog", Font.BOLD, 18));
        mainLabel.setForeground(TextColor);
        counter.setBounds(x + 215, y + 20, 40, 24);
        counter.setFont(new Font("Dialog", Font.BOLD, 15));
        counter.setForeground(TextColor);
        sub.setBounds(x + 258, y + 20, 90, 24);
        sub.setFont(new Font("Dialog", Font.BOLD, 15));
        sub.setForeground(TextColor);
        panel.add(mainLabel);
        panel.add(counter);
        panel.add(sub);
    }

    public void setCombo(JComboBox<String> c1, JComboBox<String> c2, JComboBox<String> c3, JPanel panel, int x, int y) {
        c1.setBounds(x, y, 60, 24);
        c2.setBounds(x, y + 110, 60, 24);
        c3.setBounds(x, y + 210, 60, 24);
        panel.add(c1);
        panel.add(c2);
        panel.add(c3);
    }

    public void setButton(JButton button, int x, int y) {
        button.setFont(new java.awt.Font("Dialog", Font.BOLD, 18));
        button.setBounds(x, y, 200, 40);
        button.setBackground(TextColor);
        button.setFocusPainted(false);
        add(button);
    }

    public static double getSubTotal() {
        return subTotal;
    }
}
