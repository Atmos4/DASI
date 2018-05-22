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
import java.text.SimpleDateFormat;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import models.Voyance;

/**
 *
 * @author Greg
 */
public class PrinterHistorique extends Printer {
    
    @Override
    public void execute(PrintWriter out, HttpServletRequest request ){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        List<Voyance> hist = (List<Voyance>) request.getAttribute("hist");
        
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

                    
        JsonArray array = new JsonArray();
                    
                    
        for (Voyance v:hist){
            JsonObject obj = new JsonObject();
            obj.addProperty("voyant",v.getMedium().getNom());
            obj.addProperty("dateD",sdf.format(v.getStartDate()));
            obj.addProperty("dateF",sdf.format(v.getEndDate()));
            obj.addProperty("done",(v.getEndDate()!=null));
            array.add(obj);
        }

        JsonObject container = new JsonObject();
        container.add("historique",array);
        out.println(gson.toJson(container));
    }
}
