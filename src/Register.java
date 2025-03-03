import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Register extends JFrame {
    ImageIcon
            background_img = new ImageIcon("./Images/background4.png"),
            showPassword_img = new ImageIcon("./Images/showPassword.png"),
            hidePassword_img = new ImageIcon("./Images/HidePassword.png");
    Image
            editImage1 = showPassword_img.getImage().getScaledInstance(16,16,Image.SCALE_SMOOTH),
            editImage2 = hidePassword_img.getImage().getScaledInstance(16,16,Image.SCALE_SMOOTH);
    ImageIcon
            resizedIcon1 = new ImageIcon(editImage1),
            resizedIcon2 = new ImageIcon(editImage2);

    JLabel
            background = new JLabel(background_img);
    JLabel
            // Basic Label
            Register = new JLabel("Register"),
            FirstName = new JLabel("First Name "),
            LastName = new JLabel("Last Name "),
            UserName = new JLabel("User Name"),
            Email = new JLabel("Email"),
            Pwd = new JLabel("Password"),
            rePwd = new JLabel("Re-Password"),
    //  Errors Label
    wrongName = new JLabel("Can't have spaces,symbols and numbers !"),
            lengthUser = new JLabel("Length should be between 3 and 20 !"),
            usedUser = new JLabel("This user is used !"),
            symUser = new JLabel("Can't have spaces or symbols !"),
            startByNumber = new JLabel("Can't Start By Number !"),
            formatEmail = new JLabel("Format email address is wrong !"),
            usedEmail = new JLabel("Email is already used !"),
            conPwd = new JLabel(" Password must to : "),
            lenPwd = new JLabel("• length be from 8-20 characters"),
            capPwd = new JLabel("• have a capital letters"),
            smallPwd = new JLabel("• have a small letters"),
            numPwd = new JLabel("• have a number"),
            symPwd = new JLabel("• have a symbol"),
            rPwd = new JLabel("The passwords entered do not match."),
            Back = new JLabel("< Back");

    JTextField
            FName = new JTextField(25);
    JTextField FLast = new JTextField(25);
    JTextField FUsername = new JTextField(25);
    JTextField FEmail = new JTextField(100);

    JPasswordField FPwd = new JPasswordField(16),
            FRPwd = new JPasswordField(16);
    JButton
            register = new JButton("Register"),
            showPasswordButton = new JButton(resizedIcon1);


    //-- Color
    Color
            Yellow = new Color(255, 211, 105)
            ,TextColor = new Color(238, 238, 238)
            ,errorColor = new Color(255, 0, 58);

    static public String
            SFirstName ,
            SLastName ,
            SUsername,
            SEmail ,
            SPwd;

    public Register() {
        setTitle("Register");
        setSize(350, 530);
        setLayout(null);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setLabel(Register, 125, 10, 25, TextColor);
        setLabel(FirstName, 50, 65, 14, TextColor);
        setLabel(LastName, 200, 65, 14, TextColor);
        setLabel(UserName, 50, 135, 14, TextColor);
        setLabel(Email, 50, 205, 14, TextColor);
        setLabel(Pwd, 50, 275, 14, TextColor);
        setLabel(rePwd, 180, 275, 14, TextColor);

        setLabel(wrongName,50,110,10, errorColor);
        wrongName.setVisible(false);

        setLabel(lengthUser,50,185,10,errorColor);
        lengthUser.setVisible(false);
        setLabel(usedUser,50,185,10,errorColor);
        usedUser.setVisible(false);
        setLabel(symUser,50,185,10,errorColor);
        symUser.setVisible(false);
        setLabel(startByNumber,50,185,10,errorColor);
        startByNumber.setVisible(false);

        setLabel(formatEmail,50,255,10,errorColor);
        formatEmail.setVisible(false);
        setLabel(usedEmail,50,255,10,errorColor);
        usedEmail.setVisible(false);

        setLabel(conPwd, 50, 325, 10, TextColor);
        setLabel(capPwd, 50, 340, 10, TextColor);
        setLabel(smallPwd, 180, 340, 10, TextColor);
        setLabel(numPwd, 50, 355, 10, TextColor);
        setLabel(symPwd, 180, 355, 10, TextColor);
        setLabel(lenPwd, 50, 370, 10, TextColor);
        setPassIns(false);
        setLabel(rPwd, 50, 345, 10, errorColor);
        rPwd.setVisible(false);
        setLabel(Back, 5, 455, 15, TextColor);

        setFiled(FName, 50, 90, 80, 30);
        setFiled(FLast, 200, 90, 80, 30);
        setFiled(FUsername, 50, 160, 240, 30);
        setFiled(FEmail, 50, 230, 240, 30);
        setFiled(FPwd, 50, 300, 110, 30);
        setFiled(FRPwd, 180, 300, 110, 30);
        Actions();

        register.setBackground(TextColor);
        register.setFont(new Font("Arial" , Font.BOLD , 20));
        register.setBounds(70,415,200,30);
        add(register);

        register.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (checkingInput()) {
                    String username = FUsername.getText().trim();
                    String email = FEmail.getText().trim();

                    // Check for duplicate username
                    if (isUserTaken(username)) {
                        JOptionPane.showMessageDialog(null, "The username is already taken. Please choose a different one.", "Duplicate Username", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    // Check for duplicate email
                    if (isEmailTaken(email)) {
                        JOptionPane.showMessageDialog(null, "The email is already registered. Please use a different email.", "Duplicate Email", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    // Save details and proceed if all checks pass
                    SFirstName = FName.getText().trim();
                    SLastName = FLast.getText().trim();
                    SUsername = username;
                    SEmail = email;
                    SPwd = new String(FPwd.getPassword());

                    saveUserDetails(SFirstName, SLastName, SUsername, SEmail, SPwd);

                    // Close registration screen and open login screen
                    setVisible(false);
                    new login(); // Assuming a login screen class exists
                }
            }
        });

        Back.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new login();
                setVisible(false);
            }
            public void mouseEntered(MouseEvent e) {
                Back.setForeground(Yellow);
            }
            public void mouseExited(MouseEvent e) {
                Back.setForeground(TextColor);
            }

        });

        background.setBounds(0,0,350,500);
        showPasswordButton.setBounds(158,310,25,25);
        showPasswordButton.setBorderPainted(false);
        showPasswordButton.setContentAreaFilled(false);
        showPasswordButton.setFocusPainted(false);
        showPasswordButton.setToolTipText("Show Passwords");
        add(showPasswordButton);
        add(background);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    public void Actions(){
        // Name have something wrong
        FName.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) {
                if(checkName(FName.getText())){
                    FirstName.setForeground(errorColor);
                }
                else{
                    FirstName.setForeground(TextColor);
                }
            }
            public void removeUpdate(DocumentEvent e) {
                if(checkName(FName.getText())){
                    FirstName.setForeground(errorColor);
                }
                else{
                    FirstName.setForeground(TextColor);
                }
            }
            public void changedUpdate(DocumentEvent e) {
                if(checkName(FName.getText())){
                    FirstName.setForeground(errorColor);
                }
                else{
                    FirstName.setForeground(TextColor);
                }
            }
        });
        FName.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
                if(checkName(FName.getText())){
                    FirstName.setForeground(errorColor);
                }
                else{
                    FirstName.setForeground(TextColor);
                }
            }
            public void focusLost(FocusEvent e) {
                wrongName.setVisible(false);
            }
        });
        FLast.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) {
                if(checkName(FLast.getText())){
                    LastName.setForeground(errorColor);
                }
                else{
                    LastName.setForeground(TextColor);
                }
            }
            public void removeUpdate(DocumentEvent e) {
                if(checkName(FLast.getText())){
                    LastName.setForeground(errorColor);
                }
                else{
                    LastName.setForeground(TextColor);
                }
            }
            public void changedUpdate(DocumentEvent e) {
                if(checkName(FLast.getText())){
                    LastName.setForeground(errorColor);
                }
                else{
                    LastName.setForeground(TextColor);
                }
            }
        });
        FLast.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
                if(checkName(FLast.getText())){
                    LastName.setForeground(errorColor);
                }
                else{
                    LastName.setForeground(TextColor);
                }
            }
            public void focusLost(FocusEvent e) {
                wrongName.setVisible(false);
            }
        });
        FUsername.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) {
                checkUsername(FUsername.getText());
            }
            public void removeUpdate(DocumentEvent e) {

                checkUsername(FUsername.getText());

            }
            public void changedUpdate(DocumentEvent e) {
                checkUsername(FUsername.getText());
            }
        });
        FUsername.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                checkUsername(FUsername.getText());
            }

            @Override
            public void focusLost(FocusEvent e) {
                startByNumber.setVisible(false);
                symUser.setVisible(false);
                lengthUser.setVisible(false);
                usedUser.setVisible(false);
            }
        });
        FEmail.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) {
                 checkEmail(FEmail.getText());
            }
            public void removeUpdate(DocumentEvent e) {
                    checkEmail(FEmail.getText());
            }
            public void changedUpdate(DocumentEvent e) {
                checkEmail(FEmail.getText());
            }
        });
        FEmail.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
                checkEmail(FEmail.getText());
            }
            public void focusLost(FocusEvent e) {
                formatEmail.setVisible(false);
                usedEmail.setVisible(false);
            }
        });
        // Show the password
        showPasswordButton.addActionListener(_ -> {
            if (FPwd.getEchoChar() != (char) 0) {
                FPwd.setEchoChar((char) 0); // Show password
                FRPwd.setEchoChar((char) 0); // Show password
                showPasswordButton.setIcon(resizedIcon2);
                showPasswordButton.setToolTipText("Hide Passwords");
            } else {
                FPwd.setEchoChar('•'); // Show password
                FRPwd.setEchoChar('•'); // Show password
                showPasswordButton.setIcon(resizedIcon1);
                showPasswordButton.setToolTipText("Show Passwords");
            }
        });
        // Check the password
        FPwd.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                checkPassword(FPwd.getText());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                checkPassword(FPwd.getText());
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                checkPassword(FPwd.getText());
            }
        });

        FPwd.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                setPassIns(true);
            }
            public void focusLost(FocusEvent e) {
                setPassIns(false);
            }
        });
        // Check passwords are match
        FRPwd.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) {
                matchPasswords(FPwd,FRPwd);
            }
            public void removeUpdate(DocumentEvent e) {
                matchPasswords(FPwd,FRPwd);
            }
            public void changedUpdate(DocumentEvent e) {
                matchPasswords(FPwd,FRPwd);
            }
        });
        FRPwd.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                rPwd.setVisible(!matchPasswords(FPwd, FRPwd));
            }
            public void focusLost(FocusEvent e) {
                rPwd.setVisible(false);
            }
        });
    }

    public void setFiled(JTextField field , int x , int y , int width , int height){
        field.setOpaque(false);
        field.setBorder(null);
        field.setForeground(TextColor);
        field.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, TextColor));
        field.setBounds(x,y,width,height);
        add(field);
    }

    public void setLabel(JLabel label , int x , int y , int size ,Color color){
        label.setFont(new Font("Arial" , Font.PLAIN , size));
        label.setForeground(color);
        label.setBounds(x,y,200,40);
        add(label);
    }
    public void setPassIns(boolean b){
        conPwd.setVisible(b);
        capPwd.setVisible(b);
        smallPwd.setVisible(b);
        numPwd.setVisible(b);
        symPwd.setVisible(b);
        lenPwd.setVisible(b);
    }

    public boolean checkingInput() {
        boolean first = false , last = false , user = false , email = false , pwd = false , repwd = false;
        boolean result = false;
        if (!isCorrectName(FName.getText())){
            FirstName.setForeground(errorColor);
        }else{
            FirstName.setForeground(TextColor);
            first = true;
        }
        if (!isCorrectName(FLast.getText())){
            LastName.setForeground(errorColor);
        }else{
            LastName.setForeground(TextColor);
            last = true;
        }

        if (!isCorrectUser(FUsername.getText())){
            UserName.setForeground(errorColor);
        }else{
            UserName.setForeground(TextColor);
            user = true;
        }

        if (!isCorrectEmail(FEmail.getText())){
            Email.setForeground(errorColor);
        }else{
            Email.setForeground(TextColor);
            email = true;
        }

        if (!isCorrectPassword(FPwd.getText())){
            Pwd.setForeground(errorColor);
        }else{
            Pwd.setForeground(TextColor);
            pwd = true;
        }

        if (!FPwd.getText().equals(FRPwd.getText()) || FRPwd.getText().isEmpty() || !isCorrectPassword(FPwd.getText())){
            rePwd.setForeground(errorColor);
        }else{
            rePwd.setForeground(TextColor);
            repwd = true;
        }

        if (first && last && user && email && pwd && repwd) {
            result = true;
        }
        if (isUserTaken(FUsername.getText())) {
            UserName.setForeground(errorColor);
            JOptionPane.showMessageDialog(null, "Username already taken!");
            return false;
        }

        if (isEmailTaken(FEmail.getText())) {
            Email.setForeground(errorColor);
            JOptionPane.showMessageDialog(null, "Email already taken!");
            return false;
        }
        return result;
    }

    public static boolean isCorrectName(String name){
        return Pattern.matches("([A-Z][a-z]*)" , name);
    }

    public static boolean isCorrectUser(String user){
        return Pattern.matches("([A-aZ-z_][A-Za-z0-9_]*)" , user);
    }

    public static boolean isCorrectEmail(String email){
        return Pattern.matches("([A-Za-z][A-Za-z0-9]*)(@[A-Za-z].+)(\\.[A-Za-z].+)" , email);
    }

    public static boolean isCorrectPassword(String pwd){
        return Pattern.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–\\[{}\\]:;',?/*~$^+=<>]).{8,20}$" , pwd);
    }
    public boolean checkName(String name){
        boolean Name = true,appear;
        if(name.isEmpty()){
            wrongName.setVisible(false);
        } else if(!Pattern.matches("([A-Za-z]*)" , name)){
            wrongName.setVisible(true);
            wrongName.setForeground(errorColor);
            Name = false;
        }
        else{
            wrongName.setVisible(false);
            Name = true;
        }
        if(!Name){
            appear = true;
        }
        else{
            appear = false;
        }
        return appear;
    }

    public Boolean checkUsername(String user) {
        boolean len, sym, num, used, status;

        if (user == null || user.isEmpty()) {
            UserName.setForeground(TextColor);
            startByNumber.setVisible(false);
            symUser.setVisible(false);
            lengthUser.setVisible(false);
            usedUser.setVisible(false);
            return false;
        }

        if (Character.isDigit(user.charAt(0))) {
            num = false;
            startByNumber.setVisible(true);
        } else {
            num = true;
            startByNumber.setVisible(false);
        }

        if (!(Pattern.matches("([A-Za-z_][A-Za-z0-9_]*)", user)) && num) {
            sym = false;
            symUser.setVisible(true);
        } else {
            sym = true;
            symUser.setVisible(false);
        }

        if ((user.length() < 3 || user.length() > 20) && sym && num) {
            len = false;
            lengthUser.setVisible(true);
        } else {
            len = true;
            lengthUser.setVisible(false);
        }

        if (isUserTaken(user)) {
            usedUser.setVisible(true);
            used = false;
        } else {
            usedUser.setVisible(false);
            used = true;
        }

        if (len && sym && num && used) {
            UserName.setForeground(TextColor);
            status = true;
        } else {
            UserName.setForeground(errorColor);
            status = false;
        }

        return status;
    }

    private boolean isUserTaken(String username) {
        try {
            File file = new File("details.txt");
            if (!file.exists()) {
                return false;
            }

            Scanner inputFile = new Scanner(file);
            while (inputFile.hasNextLine()) {
                String[] details = inputFile.nextLine().split(" ");
                if (details.length >= 3) { // Ensure username exists in the line
                    String existingUsername = details[2];
                    if (existingUsername.equalsIgnoreCase(username)) {
                        inputFile.close();
                        return true; // Username already exists
                    }
                }
            }
            inputFile.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Error in file");
        }
        return false;
    }


    public Boolean checkEmail(String email){
        boolean format,used,status;
        if(email == null || email.isEmpty()){
            Email.setForeground(TextColor);
            formatEmail.setVisible(false);
            usedEmail.setVisible(false);
        }
        if(!Pattern.matches("([A-Za-z][A-Za-z0-9]*)(@[A-Za-z].+)(\\.[A-Za-z].+)" , email)){
            formatEmail.setVisible(true);
            format = false;
        }
        else{
            format = true;
            formatEmail.setVisible(false);
        }
        if(isEmailTaken(email)){
            usedEmail.setVisible(true);
            used = false;
        }
        else{
            usedEmail.setVisible(false);
            used = true;
        }
        if(format && used){
            Email.setForeground(TextColor);
            status = true;
        }
        else{
            Email.setForeground(errorColor);
            status = false;
        }
        return status;

    }
    public static boolean isEmailTaken(String email) {
        try {
            File file = new File("details.txt");
            if (!file.exists()) {
                return false;
            }

            Scanner inputFile = new Scanner(file);
            while (inputFile.hasNextLine()) {
                String[] details = inputFile.nextLine().split(" ");
                if (details.length >= 4) { // Ensure email exists in the line
                    String existingEmail = details[3];
                    if (existingEmail.equalsIgnoreCase(email)) {
                        inputFile.close();
                        return true; // Email already exists
                    }
                }
            }
            inputFile.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Error in file");
        }
        return false;
    }
    public void checkPassword(String password){
        boolean cap,small,num,sym,len;
        if(!Pattern.matches(".*[A-Z].*",password)){
            capPwd.setForeground(errorColor);
            cap = false;
        }
        else{
            capPwd.setForeground(TextColor);
            cap = true;
        }
        if(!Pattern.matches(".*[a-z].*",password)){
            smallPwd.setForeground(errorColor);
            small = false;
        }
        else{
            smallPwd.setForeground(TextColor);
            small = true;
        }
        if(!Pattern.matches(".*[0-9].*",password)){
            numPwd.setForeground(errorColor);
            num = false;
        }
        else{
            numPwd.setForeground(TextColor);
            num = true;
        }
        if(!Pattern.matches(".*[!@#&(){}\\[\\]:;',?/*~$^+=<>_-].*",password)){
            symPwd.setForeground(errorColor);
            sym = false;
        }
        else{
            symPwd.setForeground(TextColor);
            sym = true;
        }
        if(password.length() < 8 || password.length() > 20){
            lenPwd.setForeground(errorColor);
            len = false;
        }
        else{
            lenPwd.setForeground(TextColor);
            len = true;
        }
        if(cap&&small&&num&&sym&&len){
            Pwd.setForeground(TextColor);
        }
        else{
            Pwd.setForeground(errorColor);
        }
    }

    public boolean matchPasswords(JTextField t1,JTextField t2){
        String
                p1 = t1.getText(),
                p2 = t2.getText();
        boolean result = false;
        if(!p1.equals(p2)){
            rPwd.setVisible(true);
            rPwd.setForeground(errorColor);
            rePwd.setForeground(errorColor);
        }
        else{
            rPwd.setVisible(false);
            rePwd.setForeground(TextColor);
            result = true;
        }
        if(result){
            rePwd.setForeground(TextColor);
        }
        else{
            rePwd.setForeground(errorColor);
        }
        return result;
    }
    private void saveUserDetails(String firstName, String lastName, String username, String email, String password) {
        try (PrintWriter writer = new PrintWriter(new FileWriter("details.txt", true))) {
            writer.println(firstName + " " + lastName + " " + username + " " + email + " " + password);
        } catch (IOException ex) {
            System.out.println("Error writing to file");
        }
    }
}