package controllers;

import play.*;
import play.mvc.*;
import play.data.*;

import models.*;
import views.html.*;

public class Projects extends Controller {
    static Form<Project> projectForm = Form.form(Project.class);
    static Form<Task> taskForm = Form.form(Task.class);

    /** CRUD de projetos **/

    public static Result projects() {
        return ok(
                views.html.projects.render(Project.all(), projectForm)
        );
    }

    public static Result show(Long id) {
        Project project = Project.find.byId(id);

        return ok(
                views.html.show.render(project)
        );
    }

    public static Result newProject() {
        Form<Project> filledForm = projectForm.bindFromRequest();
        if(filledForm.hasErrors()) {
            return badRequest(
                    views.html.projects.render(Project.all(), filledForm)
            );
        } else {
            Project project = Project.create(filledForm.get());
            return redirect(routes.Projects.show(project.id));
        }
    }


    public static Result addTasks(Long id) {
        Project project = Project.find.byId(id);

        return ok(
                views.html.tasks.render(Task.list_all(project.id), taskForm, project)
        );
    }

    public static Result saveTask() {
        Form<Task> filledForm = taskForm.bindFromRequest();
        // Instancia uma tarefa
        Task task = new Task(filledForm.get());

        // ID do projeto que veio do formulario
        Project project = Project.find.byId(task.project.id);

        // Caso tenha erros, mostramos o formulario novamente
        if(filledForm.hasErrors()) {
            return badRequest(
                    // Mostra o formulario com os erros
                    views.html.tasks.render(Task.list_all(project.id), taskForm, project)
            );
        } else {
            // Salva a tarefa, ja com o projeto anexado
            Task.create(task);
            // Retorna para a listagem de tarefas do projeto
            return redirect(routes.Projects.addTasks(task.project.id));
        }
    }

    public static Result deleteProject(Long id) {
        Project.delete(id);
        return redirect(routes.Projects.projects());
    }

    public static Result finishProject(Long id) {
        Project project = Project.find.byId(id);
        project.status = "Concluido";
        project.save();
        return redirect(routes.Projects.projects());
    }

}
