package com.siit.JourneyPlanApp.Controllers;

import com.siit.JourneyPlanApp.DAO.CarsDAO;
import com.siit.JourneyPlanApp.Model.Cars;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class CarController {

    @Autowired
    private CarsDAO carDAO;


    @GetMapping("/addCar")
    public String getAddCarPage(){
        return "addcar";
    }


    @RequestMapping(value = "/newCar", method = RequestMethod.POST, consumes = "application/x-www-form-urlencoded")
    //public @ResponseBody String addNewCar(@RequestBody Cars car, BindingResult result, Model model) throws Exception {
    //public String addNewCar(RedirectAttributes redirectAttributes, WebRequest request) throws Exception {
    public String addNewCar(RedirectAttributes redirectAttributes, WebRequest request){


        Cars carCheck = this.carDAO.findCars(request.getParameter("nr_masina"));

        if (carCheck != null) {
//            throw new Exception("Masina cu numarul " + request.getParameter("nr_masina") + " este deja in baza de date.");
            redirectAttributes.addFlashAttribute("message", "Mașina cu numărul " + request.getParameter("nr_masina").toUpperCase() + " nu a fost adăugată în sistem deoarece există deja.");
            redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
            redirectAttributes.addFlashAttribute("nr_masinaValue", request.getParameter("nr_masina") );
            redirectAttributes.addFlashAttribute("nr_locuriValue", request.getParameter("nr_locuri") );
            return "redirect:/addCar";
        }
        else if (request.getParameter("nr_masina").toUpperCase().startsWith("B-") && ( request.getParameter("nr_masina").toUpperCase().length() >= 8 && request.getParameter("nr_masina").toUpperCase().length() <=9) ) {
            if(request.getParameter("nr_masina").toUpperCase().matches("^[A-Z]{1,1}-[0-9]{1,3}-[A-Z]{1,3}$"))
            {
                carDAO.insertNewCar(request.getParameter("nr_masina").toUpperCase(), Integer.parseInt(request.getParameter("nr_locuri")));
                redirectAttributes.addFlashAttribute("message", "Mașina cu numărul " + request.getParameter("nr_masina").toUpperCase() + " a fost adăugată în sistem.");
                redirectAttributes.addFlashAttribute("alertClass", "alert-success");
                return "redirect:/addCar";
            }
            else {
                redirectAttributes.addFlashAttribute("message", "Mașina cu numărul " + request.getParameter("nr_masina").toUpperCase() + " nu respectă formatul.(B-123-YYY)");
                redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
                redirectAttributes.addFlashAttribute("nr_masinaValue", request.getParameter("nr_masina") );
                redirectAttributes.addFlashAttribute("nr_locuriValue", request.getParameter("nr_locuri") );
                return "redirect:/addCar";
            }
        }
        else if(!request.getParameter("nr_masina").toUpperCase().startsWith("B-") && ( request.getParameter("nr_masina").toUpperCase().length()  == 9) ) {
            if(request.getParameter("nr_masina").toUpperCase().matches("^[A-Z]{2,2}-[0-9]{1,2}-[A-Z]{1,3}$"))
            {
                carDAO.insertNewCar(request.getParameter("nr_masina").toUpperCase(), Integer.parseInt(request.getParameter("nr_locuri")));
                redirectAttributes.addFlashAttribute("message", "Mașina cu numărul " + request.getParameter("nr_masina").toUpperCase() + " a fost adăugată în sistem.");
                redirectAttributes.addFlashAttribute("alertClass", "alert-success");
                return "redirect:/addCar";
            }
            else {
                redirectAttributes.addFlashAttribute("message", "Mașina cu numărul " + request.getParameter("nr_masina").toUpperCase() + " nu respectă formatul. (XX-12-YYY)");
                redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
                redirectAttributes.addFlashAttribute("nr_masinaValue", request.getParameter("nr_masina") );
                redirectAttributes.addFlashAttribute("nr_locuriValue", request.getParameter("nr_locuri") );
                return "redirect:/addCar";
            }
        }
        else{
            redirectAttributes.addFlashAttribute("message", "Mașina cu numărul " + request.getParameter("nr_masina").toUpperCase() + " nu respectă lungime sau formatul.");
            redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
            return "redirect:/addCar";
        }


    }

}
