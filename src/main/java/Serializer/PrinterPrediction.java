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

/**
 *
 * @author Arnaud
 */
public class PrinterPrediction extends Printer{

    @Override
    public void execute(PrintWriter out, HttpServletRequest request) {
        List<String> prediction = (List<String>) request.getAttribute("prediction");
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        
        JsonObject obj = new JsonObject();
        if (prediction.size()>2){
            obj.addProperty("amour", prediction.get(0));
            obj.addProperty("travail", prediction.get(2));
            obj.addProperty("sante", prediction.get(1));
        }
                    
        out.println(gson.toJson(obj));
    }
    
}
