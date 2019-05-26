package chat.client;

import chat.network.TCPConnection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class LoginWindow extends JFrame {

    private static final String IPaddr="192.168.0.3";
    private static final int Port=52548;
    private static final int WIDTH=600;
    private static final int HEIGHT=400;
    private TCPConnection connection;

    //private final JTextArea log= new JTextArea();
    private final JTextField fieldLogin= new JTextField("Enter login");
    private final JTextField fieldPassword= new JTextField("Enter password");
    private final JButton Login = new JButton("Login");
    private final JButton CreateLogin = new JButton("CreateLogin");

    public LoginWindow() {
        super("Login");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        //окно по середине
        setLocationRelativeTo(null);
        //всегда сверху
        setAlwaysOnTop(true);
        Box box =Box.createVerticalBox();
        box.add(fieldLogin);
        box.add(Box.createVerticalStrut(10));
        box.add(fieldPassword);
        box.add(Box.createVerticalStrut(10));
        box.add(Login);
        box.add(Box.createVerticalStrut(10));
        box.add(CreateLogin);
        setContentPane(box);
        setSize(400, 200);
        setLocationRelativeTo(null);
        ActionListener LoginActionListner = new LoginActionListner();
        ActionListener CreateLoginActionListner = new CreateLoginActionListner();
        Login.addActionListener(LoginActionListner);
        CreateLogin.addActionListener(CreateLoginActionListner);
    }

public static void main(String[] args){
    LoginWindow app = new LoginWindow();
    app.setVisible(true);
}


public class LoginActionListner implements ActionListener{
    @Override
    public void actionPerformed(ActionEvent e) {
        String Login,Password;
        Login = fieldLogin.getText();
        Password = fieldPassword.getText();
    }
}

public class CreateLoginActionListner implements ActionListener{
    @Override
    public void actionPerformed(ActionEvent e) {
        String Login,Password;
        Login = fieldLogin.getText();
        Password = fieldPassword.getText();
    }
}
}
