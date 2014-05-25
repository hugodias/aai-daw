package models;

import java.util.*;

import play.db.ebean.*;
import play.data.validation.Constraints.*;

import javax.persistence.*;

@Entity
public class Task extends Model{

    @Id
    public Long id;

    @Required
    public String label;

    public boolean done;


    @ManyToOne
    @Required
    public Category category;

    public Date dueDate;

    public static Finder<Long,Task> find = new Finder(
            Long.class, Task.class
    );

    public static List<Task> all() {
        return find.all();
    }



    /**
     * Construtor
     * Por default a opção DONE é false
     * @param task
     */
    public Task(Task task){
        this.label = task.label;
        this.done = false;
        this.category = task.category;
    }

    public static void create(Task task) {
        task.save();
    }

    public static void delete(Long id) {
        find.ref(id).delete();
    }



}