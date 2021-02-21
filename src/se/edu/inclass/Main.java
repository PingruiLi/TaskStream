package se.edu.inclass;

import se.edu.inclass.data.DataManager;
import se.edu.inclass.task.Deadline;
import se.edu.inclass.task.Task;
import se.edu.inclass.task.TaskNameComparator;

import java.util.ArrayList;
import java.util.Locale;
import java.util.stream.Collectors;

public class Main {

    private TaskNameComparator taskNameComparator;

    public static void main(String[] args) {
        DataManager dm = new DataManager("./data/data.txt");
        ArrayList<Task> tasksData = dm.loadData();

        System.out.println("Printing deadlines");
        printDeadlines(tasksData);

        System.out.println("Total number of deadlines: " + countDeadlines(tasksData));

        printDeadlineWithStream(tasksData);
        ArrayList<Task> filteredTasks = filterTaskByString(tasksData, "11");
        System.out.println("Filtered tasks");
        printData(filteredTasks);

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

    public static void printDeadlineWithStream(ArrayList<Task> tasks) {
        System.out.println("Print with stream:");
        tasks.stream().
                filter((t) -> (t instanceof Deadline)).
                sorted((t1, t2) -> t1.getDescription().toLowerCase(Locale.ROOT).compareTo(t2.getDescription().toLowerCase(Locale.ROOT))).
                forEach(System.out::println);
    }

    public static ArrayList filterTaskByString(ArrayList<Task> tasks, String filterString) {
        ArrayList<Task> filteredTasks = (ArrayList<Task>) tasks.stream()
                .filter((t) -> t.getDescription().contains(filterString))
                .collect(Collectors.toList());
        return filteredTasks;
    }
}
