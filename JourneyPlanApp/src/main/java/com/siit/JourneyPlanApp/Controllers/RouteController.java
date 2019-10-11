package com.siit.JourneyPlanApp.Controllers;

import com.siit.JourneyPlanApp.DAO.RoutesDAO;
import com.siit.JourneyPlanApp.Model.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.regex.Pattern;

@Controller
public class RouteController {

    @Autowired
    private RoutesDAO routeDAO;


    @GetMapping("/addRoute")
    public String getAddRoutePage(){
        return "addroute";
    }


    @RequestMapping(value = "/newRoute", method = RequestMethod.POST, consumes = "application/x-www-form-urlencoded")
    public String addNewRoute(RedirectAttributes redirectAttributes, WebRequest request) {
        long idRuta = 0;
        Route routeCheck = this.routeDAO.findRoute(request.getParameter("plecare").toUpperCase() + "-" + request.getParameter("destinatie").toUpperCase());

        if (routeCheck != null) {
            redirectAttributes.addFlashAttribute("message", "Ruta " + request.getParameter("plecare").toUpperCase() + "-" + request.getParameter("destinatie").toUpperCase() + " nu a fost adăugată în sistem deoarece există deja.");
            redirectAttributes.addFlashAttribute("alertClass", "alert-danger");

            redirectAttributes.addFlashAttribute("plecareValue", request.getParameter("plecare") );
            redirectAttributes.addFlashAttribute("destinatieValue", request.getParameter("destinatie") );
            return "redirect:/addRoute";
        }
        else if (Pattern.matches("[a-zA-Z]+",request.getParameter("plecare").toUpperCase()) && Pattern.matches("[a-zA-Z]+",request.getParameter("destinatie").toUpperCase())){
            if( (request.getParameter("plecare").toUpperCase() + "-" + request.getParameter("destinatie").toUpperCase()).length() <=40){
                idRuta = routeDAO.findMaxId();
                idRuta += 1;

                routeDAO.insertNewRoute(request.getParameter("plecare").toUpperCase() + "-" + request.getParameter("destinatie").toUpperCase(), idRuta);
                redirectAttributes.addFlashAttribute("message", "Ruta " + request.getParameter("plecare").toUpperCase() + "-" + request.getParameter("destinatie").toUpperCase() + " a fost adăugată în sistem.");
                redirectAttributes.addFlashAttribute("alertClass", "alert-success");
                return "redirect:/addRoute";
            }
            else{
                redirectAttributes.addFlashAttribute("message", "Ruta " + request.getParameter("plecare").toUpperCase() + "-" + request.getParameter("destinatie").toUpperCase() + " depășește limita de caractere admisă. (40 de caractere)");
                redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
                redirectAttributes.addFlashAttribute("plecareValue", request.getParameter("plecare") );
                redirectAttributes.addFlashAttribute("destinatieValue", request.getParameter("destinatie") );
                return "redirect:/addRoute";
            }

        }
        else{
            redirectAttributes.addFlashAttribute("message", "Ruta " + request.getParameter("plecare").toUpperCase() + "-" + request.getParameter("destinatie").toUpperCase() + " nu conține doar litere.");
            redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
            redirectAttributes.addFlashAttribute("plecareValue", request.getParameter("plecare") );
            redirectAttributes.addFlashAttribute("destinatieValue", request.getParameter("destinatie") );
            return "redirect:/addRoute";
        }
    }
}
