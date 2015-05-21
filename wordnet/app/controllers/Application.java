package controllers;

import play.*;
import play.mvc.*;

import views.html.*;

public class Application extends Controller {

    public static Result index() {
        return ok(index.render());
    }

    public static Result edit() {
        return ok(edit.render());
    }

    public static Result importpage() {
        return ok(importpage.render());
    }

    public static Result register() {
        return ok(register.render());
    }

}
