import java.io.File;

public class ThreadForMonitoring implements Runnable {
    File directory;

    public ThreadForMonitoring(File directory) {
        this.directory = directory;
    }

    public File getDirectory() {
        return directory;
    }

    private String checkingDeletedFiles() {
        String rezultDell = "Deleted files";

        String[] newNamesFiles = directory.list();
        String[] oldNameFiles = ListNameInDirectory.getArryNems();
        for (int i = 0; i < oldNameFiles.length; i++) {
            int chechName = 0;
            for (int j = 0; j < newNamesFiles.length; j++) {
                if (oldNameFiles[i].equals(newNamesFiles[j])) {
                    chechName++;
                }
            }
            if (chechName == 0) {
                rezultDell = rezultDell + oldNameFiles[i] + "\n";
            }
        }
        return rezultDell;
    }

    private String checkingNewFiles() {
        String rezultNew = "Added file";
        String[] newNamesFiles = directory.list();
        String[] oldNameFiles = ListNameInDirectory.getArryNems();
        for (int i = 0; i < newNamesFiles.length; i++) {
            int chechName = 0;
            for (int j = 0; j < oldNameFiles.length; j++) {
                if (newNamesFiles[i].equals(oldNameFiles[j])) {
                    chechName++;
                }
            }
            if (chechName == 0) {
                rezultNew = rezultNew + newNamesFiles[i] + "\n";
            }
        }
        return rezultNew;
    }

    private String сheckDirectory(File directory) {
        if (!directory.isDirectory()) {
            System.out.println("passed name is not a directory");
            return null;
        }
        String rezult = "";
        String rezultDell = "Deleted files";
        String rezultNew = "Added file";
        int controlRezultDell = rezultDell.length();
        int controlRezultNew = rezultNew.length();

        String[] newNamesFiles = directory.list();
        String[] oldNameFiles = ListNameInDirectory.getArryNems();
        if (oldNameFiles.length == 0 || newNamesFiles.length == 0) {
            System.out.println("There are no files in the specified directory");
            return null;
        }
        rezultDell = checkingDeletedFiles();
        rezultNew = checkingNewFiles();
        ListNameInDirectory.setArryNems(directory.list());
        if (rezultDell.length() > controlRezultDell) {
            rezult = rezult + rezultDell + "\n";
        }
        if (rezultNew.length() > controlRezultNew) {
            rezult = rezult + rezultNew + "\n";
        }
        return rezult;
    }

    @Override
    public void run() {
        for (; ; ) {
            System.out.println(сheckDirectory(this.directory));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
