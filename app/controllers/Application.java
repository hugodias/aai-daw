package controllers;

import play.*;
import play.mvc.*;
import play.data.*;

import models.*;
import views.html.*;

public class Application extends Controller {
//    static Form<Task> taskForm = Form.form(Task.class);
//
//    public static Result newTask() {
//        Form<Task> filledForm = taskForm.bindFromRequest();
//        if(filledForm.hasErrors()) {
//            return badRequest(
//                    views.html.index.render(Task.all(), filledForm)
//            );
//        } else {
//
//            Task tk = new Task(filledForm.get());
//            Task.create(tk);
//
//            return redirect(routes.Application.tasks());
//        }
//    }
//
    public static Result deleteTask(Long id) {
        Task.delete(id);
        return redirect(routes.Projects.projects());
    }

    public static Result finishTask(Long id) {
        Task task = Task.find.byId(id);
        task.done = true;
        task.save();
        return redirect(routes.Projects.projects());
    }
}
