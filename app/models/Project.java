package models;

import java.util.*;

import play.db.ebean.*;
import play.data.validation.Constraints.*;

import javax.persistence.*;

@Entity
public class Project extends Model{

    @Id
    public Long id;

    @Required
    public String title;

    @Required
    public String description;

    public String status;

    @OneToMany(cascade = CascadeType.ALL)
    public List<Task> tasks;

    public Date dueDate;

    public static Finder<Long,Project> find = new Finder(
            Long.class, Project.class
    );

    public static List<Project> all() {
        return find.all();
    }


    /**
     * Construtor para o projeto
     *
     * @param project
     */
    public Project(Project project){
        this.title = project.title;
        this.description = project.description;
    }

    /**
     * Salva um novo projeto
     *
     * @param task
     */
    public static Project create(Project project) {
        project.status = "aberto";
        project.save();
        return project;
    }

    public static void delete(Long id) {
        find.ref(id).delete();
    }



}