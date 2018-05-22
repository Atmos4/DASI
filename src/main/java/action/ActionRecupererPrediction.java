/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import models.Client;
import models.ProfilAstro;
import services.Services;

/**
 *
 * @author Arnaud
 */
public class ActionRecupererPrediction extends Action{

    @Override
    public boolean execute(HttpServletRequest request) {
        Services services = new Services();
        
        HttpSession session = request.getSession(false);
        Client clt = (Client) session.getAttribute("Client");
        
        int amour = Integer.parseInt(request.getParameter("amour"));
        int travail = Integer.parseInt(request.getParameter("travail"));
        int sante = Integer.parseInt(request.getParameter("sante"));
        
        if (clt==null){
            clt = new Client();
            ProfilAstro astro = new ProfilAstro("Verseau", "Cochon", "Bleu", "Serpent");
            clt.setProfilAstro(astro);
        }
        List<String> prediction=null;
        try {
            prediction=services.recupererPrediction(clt, amour, travail, sante);
        } catch (IOException ex) {
            Logger.getLogger(ActionRecupererPrediction.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        request.setAttribute("prediction", prediction);
        return true;
    }
    
}
