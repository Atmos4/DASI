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
import static java.util.Collections.list;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.util.Pair;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import models.Client;
import models.Employe;
import models.Voyance;
import services.Services;

/**
 *
 * @author Greg
 */
public class ActionRecupererDataPieChart extends Action{

    @Override
    public boolean execute(HttpServletRequest request){
        Services services = new Services();
                
        List<Pair<Employe, Integer>> voyances = services.recupererVoyancesParEmployePourcentage();
        request.setAttribute("voyances", voyances);
        return true;
    }
}
