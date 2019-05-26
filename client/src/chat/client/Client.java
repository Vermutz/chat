package chat.client;

import chat.network.TCPConnection;
import chat.network.TCPConnectionListener;
import chat.network.UserData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Client extends JFrame implements ActionListener, TCPConnectionListener {

    private static final String IPaddr="212.164.218.218";
    private static final int Port=54548;
    private static final int WIDTH=600;
    private static final int HEIGHT=400;
    private static TCPConnection connection;
    private boolean identification=false;

    public void setIdentification(boolean Identification){
        identification=Identification;
    }

    public static void main(String[] args){


        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Client();
            }
        });
    }

    private final JFrame frame1= new JFrame("Authorization");
    private final JFrame frame2= new JFrame("Chat");
    private final JTextArea log= new JTextArea();
    private final JTextField fieldNickname= new JTextField("Nick name");
    private final JTextField fieldInput= new JTextField();
    private final JTextField fieldLogin= new JTextField("Enter login");
    private final JTextField fieldPassword= new JTextField("Enter password");
    private final JButton Login = new JButton("Login");
    private final JButton CreateLogin = new JButton("CreateLogin");




    private Client() {
        super("login");
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
        box.setSize(200, 100);
        ActionListener LoginActionListner = new LoginActionListner();
        ActionListener CreateLoginActionListner = new CreateLoginActionListner();
        Login.addActionListener(LoginActionListner);
        CreateLogin.addActionListener(CreateLoginActionListner);
        box.setVisible(true);
        try {
            connection = new TCPConnection(this,IPaddr,Port,identification);
        } catch (IOException e) {
            printMsg("Connection Exception: "+e);
        }
    }

    public class LoginActionListner implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            String Login,Password;
            Login = fieldLogin.getText();
            Password = fieldPassword.getText();
            UserData UD = new UserData();
            UD.setLogin(Login);
            UD.setPassword(Password);
            UD.setNewUser(false);
            connection.Autorisation(UD);
        }
    }

    public class CreateLoginActionListner implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            String Login,Password;
            Login = fieldLogin.getText();
            Password = fieldPassword.getText();
            UserData UD = new UserData();
            UD.setLogin(Login);
            UD.setPassword(Password);
            UD.setNewUser(true);
            connection.Autorisation(UD);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String msg=fieldInput.getText();
        if(msg.equals("")) return;
        fieldInput.setText(null);
        connection.SendString(fieldNickname.getText() + ": " + msg);
    }

    @Override
    public void onConnectionReady(TCPConnection tcpConnection) {
        printMsg("Connection ready...");
    }

    @Override
    public void onReceiveString(TCPConnection tcpConnection, String value) {
        printMsg(value);
    }

    @Override
    public void onDisconnect(TCPConnection tcpConnection) {
        SWforMesseng.SWMesseng("Connection Close.");
    }

    @Override
    public void onException(TCPConnection tcpConnection, Exception e) {
        SWforMesseng.SWMesseng("Connection Exception:"+e);
    }

    @Override
    public void autorisation(TCPConnection tcpConnection, UserData UD) {
        Boolean identification=UD.getidentification();
        if (identification){
            setIdentification(identification);
            frame2.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            frame2.setSize(WIDTH, HEIGHT);
            //окно по середине
            frame2.setLocationRelativeTo(null);
            //всегда сверху
            frame2.setAlwaysOnTop(true);

            //запретить редактирование
            log.setEditable(false);
            //автоматический перенос
            log.setLineWrap(true);
            //добавить окно в центр
            frame2.add(log, BorderLayout.CENTER);

            fieldInput.addActionListener(this);
            //добавим окно ввода внизу
            frame2.add(fieldInput,BorderLayout.SOUTH);
            //поле с никнеймом
            frame2.add(fieldNickname,BorderLayout.NORTH);
            frame2.setVisible(false);
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    frame2.setVisible(true);
                }
            });

        }else {
            SWforMesseng.SWMesseng(UD.getMessengID());
        }
    }


   // public String LOgin(String login, String Password) {
      //  String res="";
              //  connection.LOGIN(login, Password);
       // return res;
   // }

   // public String CReateLogin(String login, String Password) {
     //   String res=connection.NEWUSER(login,Password);
       // return res;
    //}

    private synchronized void printMsg(String msg) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                log.append(msg+"\n");
                log.setCaretPosition(log.getDocument().getLength());
            }
        });
    }
}
