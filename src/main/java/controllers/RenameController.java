package controllers;

import enums.ReferenceList;
import models.Catalog;
import models.Task;
import models.UpdateList;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * Iterates through all Tasks, comparing to ReferenceList, renaming content(the name of the task) if matched
 */

public class RenameController {

    public static void runFullRenaming() {
        RenameController.renameKnownTasks();

        RenameController.removeRenamedTasks();

        RenameController.renameUnknownTasks();
    }

    public static void renameTask(Task task) {

        Predicate<ReferenceList> itemFinder = item -> (item.getContent().equalsIgnoreCase(task.getContent())); // skip non identical

        Consumer<ReferenceList> rename = (item) -> {
            task.setContent(item.getCategory().concat(task.getContent()));
        };

        EnumSet.allOf(ReferenceList.class).stream()
                .filter(itemFinder)
                .findAny()
                .ifPresent(rename);
    }

    public static void renameList(ArrayList<Task> tasks) {
        tasks.forEach(RenameController::renameTask);
    }

    public static void renameListById(Long project_id) {
        Catalog.getProjects().stream()
                .filter(project -> project.getId().equals(project_id)) //FIXME change to enum?
                .findFirst() //
                .ifPresent(project -> project.getTasks()
                        .forEach(RenameController::renameTask));
    }




    public static void removeRenamedTasks() {
        UpdateList.getTasks().forEach(task -> {
            EnumSet.allOf(ReferenceList.class).stream()
                    .filter(category -> task.getContent().contains(category.getCategory()) || task.getContent().contains("_"))
                    .findAny()
                    .ifPresent(item -> UpdateList.addRenamedTask(task));
        });

        UpdateList.getRenamedTasks().forEach(task -> UpdateList.getTasks().remove(task));
    }

    public static void renameKnownTasks() {
        UpdateList.getTasks().forEach(task -> {
            EnumSet.allOf(ReferenceList.class).stream()
                    .filter(category -> task.getContent().equalsIgnoreCase(category.getContent()))
                    .findAny()
                    .ifPresent(item -> {
                        renameTask(task);
                        UpdateList.addTasksToRename(task);
                    });
        });
        UpdateList.getTasksToRename().forEach(task -> UpdateList.getTasks().remove(task));
    }

    public static void renameUnknownTasks() {
        UpdateList.getTasks().forEach(task -> {
            task.setContent("_".concat(task.getContent()));
            UpdateList.addTasksToRename(task);
        });

        UpdateList.getTasksToRename().forEach(task -> UpdateList.getTasks().remove(task));
    }

}




