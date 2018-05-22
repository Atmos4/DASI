/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import models.Client;
import models.Medium;
import models.Voyance;
import services.Services;

/**
 *
 * @author Arnaud
 */
public class ActionDemarrerVoyance extends Action{

    @Override
    public boolean execute(HttpServletRequest request) {
        Services services = new Services();
        Medium medium = services.recupererMediumById(Integer.parseInt(request.getParameter("idmedium")));
        
        HttpSession session = request.getSession(false);
        Client clt = (Client) session.getAttribute("Client");
        boolean b = services.demanderVoyance(clt, medium);
        request.setAttribute("ok", b);
        return true;
    }
    
}
