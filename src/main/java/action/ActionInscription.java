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
public class ActionInscription extends Action{
    @Override
    public boolean execute(HttpServletRequest request){
        Services services = new Services();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        String adresse = request.getParameter("coordonnees");
        String tel = request.getParameter("numeroTel");
        String mail = request.getParameter("mail");
        String confmail = request.getParameter("confmail");
        String civilite = request.getParameter("civilite");
        String dateNaissance = request.getParameter("dateNaissance");
        Date dateN = null;
        try {
            dateN = sdf.parse(dateNaissance);
        } catch (ParseException ex) {
            Logger.getLogger(ActionServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        Client clt = new Client(civilite, nom, prenom, dateN, adresse, mail, tel);
        
        try {
            if(services.inscription(clt))
            {
                HttpSession session = request.getSession(true);
                session.setAttribute("mailEmploye", mail);
                return true;
            }
            else
            {
                return false;
            }
        } catch (IOException ex) {
            Logger.getLogger(ActionInscription.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
