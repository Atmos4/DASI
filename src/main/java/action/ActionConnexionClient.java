/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action;

import fr.insalyon.ihm_positif.ActionServlet;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import models.Client;
import services.Services;

/**
 *
 * @author Greg
 */
public class ActionConnexionClient extends Action{
    @Override
    public boolean execute(HttpServletRequest request){
        Services services = new Services();
        String mail = request.getParameter("mail");
        Client client = services.connexionClient(mail);
        if(client!=null)
        {
           HttpSession session = request.getSession(true);
           session.setAttribute("Client", client);
           request.setAttribute("status", "success");
           return true;
        }
        else{
            request.setAttribute("status", "failure");
            return false;
        }
    }
}
