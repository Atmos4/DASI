/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import models.Employe;
import models.Voyance;
import services.Services;

/**
 *
 * @author Arnaud
 */
public class ActionRecupererDemandeVoyance extends Action {

    @Override
    public boolean execute(HttpServletRequest request) { 
        Services services = new Services();
        
        HttpSession session = request.getSession(false);
        Employe employe = (Employe)session.getAttribute("employe");
        //Voyance v = services.consulterDemandeVoyance(employe);
        //request.setAttribute("voyance", v);
        return true;
    }
    
}
