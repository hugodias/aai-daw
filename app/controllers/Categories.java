package controllers;

import play.*;
import play.mvc.*;
import play.data.*;

import models.*;
import views.html.*;

import java.lang.Exception;

public class Categories extends Controller {
    static Form<Category> categoryForm = Form.form(Category.class);

    /** CRUD de categorias **/
    public static Result index() {
        return ok(
                views.html.categories.render(Category.all(), categoryForm)
        );
    }

    public static Result newCategory() {
        Form<Category> filledForm = categoryForm.bindFromRequest();
        if(filledForm.hasErrors()) {
            return badRequest(
                    views.html.categories.render(Category.all(), filledForm)
            );
        } else {
            Category.create(filledForm.get());
            return redirect(routes.Categories.index());
        }
    }

    public static Result deleteCategory(Long id) {
        try {
            Category.delete(id);

            return redirect(routes.Categories.index());
        } catch (Exception e){
            return badRequest(
                    views.html.categories.render(Category.all(), categoryForm)
            );
        }
    }

}
