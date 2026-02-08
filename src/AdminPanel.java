import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class AdminPanel extends JFrame {
    JTextField id,title,author;
    JTable table;
    DefaultTableModel model;

    public AdminPanel() {
        setTitle("Admin Panel");
        setSize(700,400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        id = new JTextField(5);
        title = new JTextField(8);
        author = new JTextField(8);

        JButton add = new JButton("Add");
        JButton delete = new JButton("Delete");
        JButton logout = new JButton("Logout");

        JPanel top = new JPanel();
        top.add(new JLabel("ID")); top.add(id);
        top.add(new JLabel("Title")); top.add(title);
        top.add(new JLabel("Author")); top.add(author);
        top.add(add); top.add(delete); top.add(logout);

        model = new DefaultTableModel(new String[]{"ID","Title","Author","Status"},0);
        table = new JTable(model);
        loadBooks();

        add.addActionListener(e -> addBook());
        delete.addActionListener(e -> deleteBook());
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

    void addBook() {
        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("INSERT INTO books VALUES(?,?,?,?)");
            ps.setString(1,id.getText());
            ps.setString(2,title.getText());
            ps.setString(3,author.getText());
            ps.setString(4,"Available");
            ps.executeUpdate();
            JOptionPane.showMessageDialog(this,"Book Added");
            loadBooks();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,"Error");
        }
    }

    void deleteBook() {
        int r = table.getSelectedRow();
        if(r==-1){
            JOptionPane.showMessageDialog(this,"Select Book");
            return;
        }
        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("DELETE FROM books WHERE id=?");
            ps.setString(1,model.getValueAt(r,0).toString());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(this,"Deleted");
            loadBooks();
        } catch (Exception ignored) {}
    }
    }


