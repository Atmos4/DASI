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
import models.Voyance;

/**
 *
 * @author Arnaud
 */
public class PrinterRecupererDemandeVoyance extends Printer{

    @Override
    public void execute(PrintWriter out, HttpServletRequest request) {
        Voyance v = (Voyance)request.getAttribute("voyance");
         Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonObject obj = new JsonObject();
        obj.addProperty("nomClient", v.getClient().getNom());
        obj.addProperty("prenomClient", v.getClient().getPrenom());
        obj.addProperty("nomMedium",v.getMedium().getNom());
        
        JsonObject container = new JsonObject();
        container.add("demande",obj);
        out.println(gson.toJson(container));
        
    }
    
}
