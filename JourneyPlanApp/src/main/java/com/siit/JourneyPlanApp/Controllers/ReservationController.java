package com.siit.JourneyPlanApp.Controllers;

import com.siit.JourneyPlanApp.DAO.ReservationDAO;
import com.siit.JourneyPlanApp.DAO.RoutesDAO;
import com.siit.JourneyPlanApp.Model.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.apache.commons.validator.routines.EmailValidator;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ReservationController {

    @Autowired
    private ReservationDAO reservationDAO;

    @Autowired
    private RoutesDAO routesDAO;

    //@Value("${route}")
    private Route routeInfo;

    //@Value("${listOfRoutes}")
    private List<Route> listOfRoutes;

    private List<LocalDate> listOfDates = new ArrayList<>();


    @GetMapping("/addReservation")
    public ModelAndView getAddReservation(){

        listOfRoutes = this.routesDAO.findAllRoutes();
        ModelAndView mav = new ModelAndView("addreservation");
        mav.addObject("listOfRoutes", this.listOfRoutes);

        if(!listOfDates.isEmpty()){
            listOfDates.clear();
        }

        LocalDate dateNow = LocalDate.now();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        String dateNowString = dateNow.format(formatter);


        listOfDates.add(LocalDate.parse(dateNowString, formatter));
        listOfDates.add(LocalDate.parse(dateNowString, formatter).plusDays(1));
        listOfDates.add(LocalDate.parse(dateNowString, formatter).plusDays(2));
        listOfDates.add(LocalDate.parse(dateNowString, formatter).plusDays(3));

        mav.addObject("listOfDates", this.listOfDates);


        return mav;
    }

    @RequestMapping(value = "/newReservation", method = RequestMethod.POST, consumes = "application/x-www-form-urlencoded")
    public String addNewReservation(RedirectAttributes redirectAttributes, WebRequest request) {

        if(request.getParameter("nume").toUpperCase().length() <=35){
            if(!request.getParameter("nume").toUpperCase().matches("^[a-zA-Z]+$")){
                redirectAttributes.addFlashAttribute("message", "Numele introdus " + request.getParameter("nume").toUpperCase() + " nu a fost adăugat în sistem deoarece conține și alte caractere în afară de litere.");
                redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
                redirectAttributes.addFlashAttribute("numeValue", request.getParameter("nume") );
                redirectAttributes.addFlashAttribute("prenumeValue", request.getParameter("prenume") );
                redirectAttributes.addFlashAttribute("emailValue", request.getParameter("email") );
                redirectAttributes.addFlashAttribute("nrTelefonValue", request.getParameter("nrTelefon") );
                return "redirect:/addReservation";
            }
        }
        else{

            redirectAttributes.addFlashAttribute("message", "Numele introdus " + request.getParameter("nume").toUpperCase() + " nu a fost adăugat în sistem deoarece conține mai mult de 35 de caractere.");
            redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
            redirectAttributes.addFlashAttribute("numeValue", request.getParameter("nume") );
            redirectAttributes.addFlashAttribute("prenumeValue", request.getParameter("prenume") );
            redirectAttributes.addFlashAttribute("emailValue", request.getParameter("email") );
            redirectAttributes.addFlashAttribute("nrTelefonValue", request.getParameter("nrTelefon") );
            return "redirect:/addReservation";
        }

        if(request.getParameter("prenume").toUpperCase().length() <=35){
            if(!request.getParameter("prenume").toUpperCase().matches("^[a-zA-Z]+$")){
                redirectAttributes.addFlashAttribute("message", "Prenumele introdus " + request.getParameter("prenume").toUpperCase() + " nu a fost adăugat în sistem deoarece conține și alte caractere în afară de litere.");
                redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
                redirectAttributes.addFlashAttribute("numeValue", request.getParameter("nume") );
                redirectAttributes.addFlashAttribute("prenumeValue", request.getParameter("prenume") );
                redirectAttributes.addFlashAttribute("emailValue", request.getParameter("email") );
                redirectAttributes.addFlashAttribute("nrTelefonValue", request.getParameter("nrTelefon") );
                return "redirect:/addReservation";
            }
        }
        else{

            redirectAttributes.addFlashAttribute("message", "Prenumele introdus " + request.getParameter("prenume").toUpperCase() + " nu a fost adăugat în sistem deoarece conține mai mult de 35 de caractere.");
            redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
            redirectAttributes.addFlashAttribute("numeValue", request.getParameter("nume") );
            redirectAttributes.addFlashAttribute("prenumeValue", request.getParameter("prenume") );
            redirectAttributes.addFlashAttribute("emailValue", request.getParameter("email") );
            redirectAttributes.addFlashAttribute("nrTelefonValue", request.getParameter("nrTelefon") );
            return "redirect:/addReservation";
        }

        if(request.getParameter("email").toUpperCase().length() <= 120){

            if(!EmailValidator.getInstance().isValid(request.getParameter("email").toUpperCase())){
                redirectAttributes.addFlashAttribute("message", "Adresa de email introdusă " + request.getParameter("email").toUpperCase() + " nu a fost adăugată în sistem deoarece nu conține caracterele corespunzătoare.");
                redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
                redirectAttributes.addFlashAttribute("numeValue", request.getParameter("nume") );
                redirectAttributes.addFlashAttribute("prenumeValue", request.getParameter("prenume") );
                redirectAttributes.addFlashAttribute("emailValue", request.getParameter("email") );
                redirectAttributes.addFlashAttribute("nrTelefonValue", request.getParameter("nrTelefon") );
                return "redirect:/addReservation";
            }
        }
        else{

            redirectAttributes.addFlashAttribute("message", "Adresa de email introdusă " + request.getParameter("email").toUpperCase() + " nu a fost adăugată în sistem deoarece conține mai mult de 120 de caractere.");
            redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
            redirectAttributes.addFlashAttribute("numeValue", request.getParameter("nume") );
            redirectAttributes.addFlashAttribute("prenumeValue", request.getParameter("prenume") );
            redirectAttributes.addFlashAttribute("emailValue", request.getParameter("email") );
            redirectAttributes.addFlashAttribute("nrTelefonValue", request.getParameter("nrTelefon") );
            return "redirect:/addReservation";
        }

        if(request.getParameter("nrTelefon").toUpperCase().length() == 10){
            if(!request.getParameter("nrTelefon").toUpperCase().matches("\\b\\d+\\b")){
                redirectAttributes.addFlashAttribute("message", "Numărul de telefon introdus " + request.getParameter("nrTelefon").toUpperCase() + " nu a fost adăugat în sistem deoarece nu conține doar cifre.");
                redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
                redirectAttributes.addFlashAttribute("numeValue", request.getParameter("nume") );
                redirectAttributes.addFlashAttribute("prenumeValue", request.getParameter("prenume") );
                redirectAttributes.addFlashAttribute("emailValue", request.getParameter("email") );
                redirectAttributes.addFlashAttribute("nrTelefonValue", request.getParameter("nrTelefon") );
                return "redirect:/addReservation";
            }
        }
        else{

            redirectAttributes.addFlashAttribute("message", "Numărul de telefon introdus " + request.getParameter("nrTelefon").toUpperCase() + " nu a fost adăugat în sistem deoarece conține mai mult de 10 caractere.");
            redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
            redirectAttributes.addFlashAttribute("numeValue", request.getParameter("nume") );
            redirectAttributes.addFlashAttribute("prenumeValue", request.getParameter("prenume") );
            redirectAttributes.addFlashAttribute("emailValue", request.getParameter("email") );
            redirectAttributes.addFlashAttribute("nrTelefonValue", request.getParameter("nrTelefon") );
            return "redirect:/addReservation";
        }


        long idRezervare = 0;
        idRezervare = this.reservationDAO.findMaxId();
        idRezervare += 1;
        String numeClient = request.getParameter("nume").toUpperCase();
        String prenumeClient = request.getParameter("prenume").toUpperCase();
        String email = request.getParameter("email").toUpperCase();
        String nrTelefon = request.getParameter("nrTelefon").toUpperCase();
        String rutaSelectata = request.getParameter("rutaSelectata").toUpperCase();
        String dataSelectata = request.getParameter("dataSelectata");


        Date dateToBeInserted;
        dateToBeInserted = Date.valueOf(request.getParameter("dataSelectata").toString());
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        int retValue = this.reservationDAO.insertNewReservation(idRezervare,numeClient,prenumeClient,email,nrTelefon, auth.getName().toString(), this.routesDAO.findIdByRouteName(rutaSelectata), dateToBeInserted);

        if(retValue >= 1) {
            redirectAttributes.addFlashAttribute("message", "Rezervarea a fost adăugată în sistem.");
            redirectAttributes.addFlashAttribute("alertClass", "alert-success");
            return "redirect:/addReservation";
        }

        return "redirect:/addReservation";
    }

}
