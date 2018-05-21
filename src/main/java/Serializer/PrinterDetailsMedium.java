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
import models.Medium;

/**
 *
 * @author Arnaud
 */
public class PrinterDetailsMedium extends Printer {

    @Override
    public void execute(PrintWriter out, HttpServletRequest request) {
        Medium m = (Medium)request.getAttribute("medium");
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonObject obj = new JsonObject();
        obj.addProperty("nom",m.getNom());
        obj.addProperty("id",m.getIdM());
        obj.addProperty("bio",m.getBio());
        
        JsonObject container = new JsonObject();
        container.add("mediumInfos",obj);
        out.println(gson.toJson(container));
    }
    
}
