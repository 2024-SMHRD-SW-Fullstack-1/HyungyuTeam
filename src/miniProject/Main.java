package miniProject;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                String url = "jdbc:oracle:thin:@localhost:1521/XE";
                String username = "service";
                String password = "12345";
                UserRepository userRepository = new JDBCUserRepository(url, username, password);
                LocalImageRepository view = new LocalImageRepository(userRepository); // userRepository 전달
                LocalImageController controller = new LocalImageController(view, userRepository);
            }
        });
    }
}