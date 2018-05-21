/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Serializer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import models.Medium;

/**
 *
 * @author Arnaud
 */
public class PrinterMedium extends Printer{
    public void execute(PrintWriter out, HttpServletRequest request ){
        List<Medium> mediums = (List<Medium>) request.getAttribute("mediums");
        
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

                    
        JsonArray array = new JsonArray();
                    
                    
        for (Medium m:mediums){
            JsonObject obj = new JsonObject();
            obj.addProperty("nom",m.getNom());
            array.add(obj);
        }

        JsonObject container = new JsonObject();
        container.add("mediums",array);
        out.println(gson.toJson(container));
    }
}
