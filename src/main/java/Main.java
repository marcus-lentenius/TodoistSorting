import controllers.ConnectionController;
import controllers.GetController;
import controllers.RenameController;
import models.Catalog;
import models.ConnectionModel;
import models.UpdateList;

public class Main {
    public static void main(String[] args) {

        // Connection setup

        ConnectionModel conMod = new ConnectionModel(
                "https://api.todoist.com/rest/v1/",
                "src/main/resources/token.txt");

        ConnectionController connectionController = new ConnectionController(conMod);

        // Get data

        GetController.getProjects(connectionController);
        GetController.getTasks(connectionController);

        // Get tasks for renaming

        Catalog.getProjects().stream()
                .filter(project -> project.getName().equalsIgnoreCase("mat")) // refactor?
                .findAny()
                .ifPresent(project
                        -> project.getTasks()
                        .forEach(UpdateList::addTasks));

        // Rename tasks

        RenameController.runFullRenaming();

//        UpdateList.getTasksToRename().forEach(task ->
//                PostController.update(Path.TASKS,task,connectionController));
    }
}
