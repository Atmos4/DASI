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
 * @author Greg
 */
public class PrinterStatusRequest extends Printer {
    
    @Override
    public void execute(PrintWriter out, HttpServletRequest request ){
        if (request.getAttribute("status") == "success"){
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
        
        JsonObject obj = new JsonObject();
        obj.addProperty("status", "success");

        out.println(gson.toJson(obj));
        }
        
    }
}
