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
import javax.servlet.http.HttpSession;
import models.Client;

/**
 *
 * @author Greg
 */
public class PrinterProfilClient extends Printer {
    
    @Override
    public void execute(PrintWriter out, HttpServletRequest request ){
        HttpSession session = request.getSession(false);
        Client clt = (Client) session.getAttribute("Client");
        String civil, prenom,nom;
        civil = clt.getCivilite();
        prenom=clt.getPrenom();
        nom=clt.getNom();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        JsonObject jsonClient = new JsonObject();
        jsonClient.addProperty("civil", civil);
        jsonClient.addProperty("nom", nom);
        jsonClient.addProperty("prenom", prenom);

        JsonObject container = new JsonObject();
        container.add("profil", jsonClient);

        out.println(gson.toJson(container));
    }
}
