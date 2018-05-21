/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action;

import java.util.List;
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
public class ActionRecupererMediums extends Action{

    @Override
    public boolean execute(HttpServletRequest request) {
        Services services = new Services();
        List<Medium> mediums = services.consulterListeMediums();
        request.setAttribute("mediums", mediums);
        return true;
    }
    
}
