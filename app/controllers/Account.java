package controllers;

import play.mvc.Controller;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Leo on 15/01/16.
 */
public class Account extends Controller {
    public static void login(String mail, String password) {
        Map result = new HashMap<String, Object>();
        result.put("status", "todo");
        renderJSON(result);
    }
    public static void register(String mail, String password) {
        Map result = new HashMap<String, Object>();
        result.put("status", "todo");
        renderJSON(result);
    }
}
