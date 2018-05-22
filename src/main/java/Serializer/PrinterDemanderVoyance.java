/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Serializer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Arnaud
 */
public class PrinterDemanderVoyance extends Printer{

    @Override
    public void execute(PrintWriter out, HttpServletRequest request) {
        boolean b = (boolean)request.getAttribute("ok");
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonObject container = new JsonObject();
        container.addProperty("isOk", b);
        out.println(gson.toJson(container));
    }
    
}
