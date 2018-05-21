/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action;

import javax.servlet.http.HttpServletRequest;
import models.Medium;
import services.Services;

/**
 *
 * @author Arnaud
 */
public class ActionAfficherDetailsMedium extends Action{

    @Override
    public boolean execute(HttpServletRequest request) {
        
        Services services = new Services();
        int id = Integer.parseInt(request.getParameter("idmedium"));
        Medium medium = services.recupererMediumById(id);
        request.setAttribute("infosMedium", medium);
        return true;
    }
    
}
