/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import models.Employe;
import services.Services;

/**
 *
 * @author Greg
 */
public class ActionConnexionEmploye extends Action{
    @Override
    public boolean execute(HttpServletRequest request){
        Services services = new Services();
        String mail = request.getParameter("mail");
                Employe employe = services.connexionEmploye(mail);
                if(employe!=null)
                {
                   HttpSession session = request.getSession(true);
                   session.setAttribute("employe", employe);
           request.setAttribute("status", "success");
           return true;
        }
        else{
            request.setAttribute("status", "failure");
            return false;
        }
    }
}
