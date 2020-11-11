import java.io.File;

public class Main {
    public static void main(String[] args) {
        File directory = new File("MyDirectory");
        ListNameInDirectory.setArryNems(directory.list());
        Thread thread = new Thread(new ThreadForMonitoring(directory));
        thread.start();
    }
}
