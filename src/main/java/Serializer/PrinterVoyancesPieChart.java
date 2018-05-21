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
import javafx.util.Pair;
import javax.servlet.http.HttpServletRequest;
import models.Employe;
import models.Medium;

/**
 *
 * @author Arnaud
 */
public class PrinterVoyancesPieChart extends Printer{
    
    @Override
    public void execute(PrintWriter out, HttpServletRequest request ){
        List<Pair<Employe, Integer>> listVoyances = (List<Pair<Employe, Integer>>) request.getAttribute("voyances");
        
        Gson gson = new GsonBuilder().setPrettyPrinting().create();  
        JsonArray array = new JsonArray();
                    
                    
        for (Pair<Employe, Integer> p:listVoyances){
            JsonObject obj = new JsonObject();
            obj.addProperty("idNomPrenom",p.getKey().getIdE() + p.getKey().getNom() + p.getKey().getPrenom());
            obj.addProperty("nbVoyance", p.getValue());
            System.out.println(p.getValue());
            array.add(obj);
        }

        JsonObject container = new JsonObject();
        container.add("voyances",array);
        out.println(gson.toJson(container));
        
    }
}
