package controllers;

import play.*;
import play.mvc.*;
import play.data.*;

import models.*;
import views.html.*;

public class Categories extends Controller {
    static Form<Category> categoryForm = Form.form(Category.class);

    public static Result index() {
        return redirect(routes.Application.tasks());
    }

    /** CRUD de categorias **/

    public static Result categories() {
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
            return redirect(routes.Categories.categories());
        }
    }

    public static Result deleteCategory(Long id) {
        Category.delete(id);
        return redirect(routes.Categories.categories());
    }

}
