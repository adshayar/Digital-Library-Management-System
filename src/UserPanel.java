import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class UserPanel extends JFrame {
    JTable table;
    DefaultTableModel model;

    public UserPanel() {
        setTitle("User Panel");
        setSize(700,400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JButton issue = new JButton("Issue");
        JButton ret = new JButton("Return");
        JButton logout = new JButton("Logout");

        JPanel top = new JPanel();
        top.add(issue); top.add(ret); top.add(logout);

        model = new DefaultTableModel(new String[]{"ID","Title","Author","Status"},0);
        table = new JTable(model);
        loadBooks();

        issue.addActionListener(e -> updateStatus("Issued"));
        ret.addActionListener(e -> updateStatus("Available"));
        logout.addActionListener(e -> { new RoleSelection(); dispose(); });

        add(top,BorderLayout.NORTH);
        add(new JScrollPane(table),BorderLayout.CENTER);
        setVisible(true);
    }

    void loadBooks() {
        model.setRowCount(0);
        try {
            Connection con = DBConnection.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM books");
            while(rs.next()){
                model.addRow(new Object[]{
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4)
                });
            }
        } catch (Exception ignored) {}
    }

    void updateStatus(String status) {
        int r = table.getSelectedRow();
        if(r==-1){
            JOptionPane.showMessageDialog(this,"Select Book");
            return;
        }
        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(
                    "UPDATE books SET status=? WHERE id=?"
            );
            ps.setString(1,status);
            ps.setString(2,model.getValueAt(r,0).toString());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(this,"Updated");
            loadBooks();
        } catch (Exception ignored) {}
    }
}
