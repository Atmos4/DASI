/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action;

import java.util.List;
import javafx.util.Pair;
import javax.servlet.http.HttpServletRequest;
import models.Employe;
import services.Services;

/**
 *
 * @author Greg
 */
public class ActionRecupererDataBarChartEmploye extends Action{

    @Override
    public boolean execute(HttpServletRequest request){
        Services services = new Services();
                
        List<Pair<Employe, Integer>> voyances = services.recupererVoyancesParEmploye();
        request.setAttribute("voyances", voyances);
        return true;
    }
}
