package se.edu.inclass;

import se.edu.inclass.data.DataManager;
import se.edu.inclass.task.Deadline;
import se.edu.inclass.task.Task;
import se.edu.inclass.task.TaskNameComparator;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class Main {

    private TaskNameComparator taskNameComparator;

    public static void main(String[] args) {
        DataManager dm = new DataManager("./data/data.txt");
        ArrayList<Task> tasksData = dm.loadData();

        System.out.println("Printing deadlines");
        printDeadlines(tasksData);

        System.out.println("Total number of deadlines: " + countDeadlines(tasksData));

        printData(tasksData);
        printTaskWithStream(tasksData);
        printDeadlineWithStream(tasksData);
        System.out.println("Count of Deadlines with Stream: " + countDeadlineWithStream(tasksData));
    }

    private static int countDeadlines(ArrayList<Task> tasksData) {
        int count = 0;
        for (Task t : tasksData) {
            if (t instanceof Deadline) {
                count++;
            }
        }
        return count;
    }

    public static void printTaskWithStream(ArrayList<Task> tasks) {
        System.out.println("Print with streams:");
        tasks.stream().forEach(System.out::println);
    }

    public static void printDeadlineWithStream(ArrayList<Task> tasks) {
        System.out.println("Print Deadlines with stream:");
        tasks.stream()
                .filter((t) -> (t instanceof Deadline))
                .forEach(System.out::println);
    }

    private static int countDeadlineWithStream(ArrayList<Task> tasks) {
        int count = 0;
        count = (int) tasks.stream().
                filter((t) -> (t instanceof Deadline)).count();
        return count;
    }

    public static void printData(ArrayList<Task> tasksData) {
        for (Task t : tasksData) {
            System.out.println(t);
        }
    }

    public static void printDeadlines(ArrayList<Task> tasksData) {
        for (Task t : tasksData) {
            if (t instanceof Deadline) {
                System.out.println(t);
            }
        }
    }
}
