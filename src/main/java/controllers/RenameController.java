package controllers;

import models.DbConnection;
import models.UpdateList;

/**
 * Iterates through all Tasks, comparing to ReferenceList, renaming content(the name of the task) if matched
 */

public class RenameController {
    public static void renameTask() {
        UpdateList.getTasks().forEach(task ->
            DbController.renameTask(DbConnection.getConnection(), task));
    }
}




