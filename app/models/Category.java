package models;

import java.util.*;

import play.db.ebean.*;
import play.data.validation.Constraints.*;

import javax.persistence.*;

@Entity
public class Category extends Model{

    private static final long serialVersionUID = 1L;

    @Id
    public Long id;

    @Required
    public String name;

    public static Finder<Long,Category> find = new Finder(
            Long.class, Category.class
    );

    public static List<Category> all() {
        return find.all();
    }

    public static Map<String,String> options() {
        LinkedHashMap<String,String> options = new LinkedHashMap<String,String>();
        for(Category c: Category.find.orderBy("name").findList()) {
            options.put(c.id.toString(), c.name);
        }
        return options;
    }

    public Category(Category category){
        this.name = category.name;
    }

    public static void create(Category category) {
        category.save();
    }

    public static void delete(Long id) {
        find.ref(id).delete();
    }



}