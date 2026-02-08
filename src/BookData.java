import javax.swing.table.DefaultTableModel;

public class BookData {
    static DefaultTableModel model = new DefaultTableModel(
            new String[]{"Book ID", "Title", "Author", "Status"}, 0);
}
