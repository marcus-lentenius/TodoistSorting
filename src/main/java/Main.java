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

        // Fetch data

        GetController.getProjects(connectionController);
        GetController.getTasks(connectionController);


        // Add tasks

        Catalog.getProjects().stream()
                .filter(project -> project.getName().equalsIgnoreCase("mat")) // refactor?
                .findAny()
                .ifPresent(project
                        -> project.getTasks()
                        .forEach(UpdateList::addTasks));
        System.out.println("pre");
        UpdateList.getRenamedTasks().forEach(task -> System.out.println(task.getContent()));

        // Rename

        RenameController.renameTask();

        UpdateList.getRenamedTasks().forEach(task -> System.out.println(task.getContent()));

//        UpdateList.getRenamedTasks().forEach(task ->
//                PostController.update(Path.TASKS,task,connectionController));
    }
}