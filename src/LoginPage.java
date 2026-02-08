import javax.swing.*;
import java.awt.*;

public class LoginPage extends JFrame {
    JTextField u;
    JPasswordField p;

    public LoginPage(String role) {
        setTitle(role.toUpperCase()+" LOGIN");
        setSize(300,150);
        setLayout(new GridLayout(3,2,5,10));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        u = new JTextField();
        p = new JPasswordField();
        JButton login = new JButton("LOGIN");

        login.addActionListener(e -> {
            if(role.equals("admin") && u.getText().equals("admin") && p.getText().equals("admin")){
                new AdminPanel(); dispose();
            } else if(role.equals("user") && u.getText().equals("user") && p.getText().equals("user")){
                new UserPanel(); dispose();
            } else {
                JOptionPane.showMessageDialog(this,"Invalid Login");
            }
        });

        add(new JLabel("Username")); add(u);
        add(new JLabel("Password")); add(p);
        add(new JLabel()); add(login);
        setVisible(true);
    }
}
