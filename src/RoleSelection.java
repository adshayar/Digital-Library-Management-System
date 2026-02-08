import javax.swing.*;
import java.awt.*;

public class RoleSelection extends JFrame {
    public RoleSelection() {
        setTitle("Select Role");
        setSize(300,180);
        setLayout(new GridLayout(2,1,10,10));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JButton admin = new JButton("ADMIN");
        JButton user = new JButton("USER");

        admin.addActionListener(e -> { new LoginPage("admin"); dispose(); });
        user.addActionListener(e -> { new LoginPage("user"); dispose(); });

        add(admin); add(user);
        setVisible(true);
    }
}
