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
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import models.Voyance;

/**
 *
 * @author Greg
 */
public class PrinterHistorique {
    public void execute(PrintWriter out, HttpServletRequest request ){
        List<Voyance> hist = (List<Voyance>) request.getAttribute("hist");
        
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        
        gson.toJson(hist);

        out.println(gson.toJson(hist));
    }
}
