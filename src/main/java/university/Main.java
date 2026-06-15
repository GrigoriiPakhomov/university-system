package university;

import university.service.UniversityManager;
import university.ui.ConsoleUI;

public class Main {
    public static void main(String[] args) {
        UniversityManager manager = UniversityManager.getInstance();

        manager.loadData();
        ConsoleUI consoleUI = new ConsoleUI();
        consoleUI.start();
        manager.saveData();
    }
}