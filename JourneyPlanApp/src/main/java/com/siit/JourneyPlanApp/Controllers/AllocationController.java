package com.siit.JourneyPlanApp.Controllers;


import com.siit.JourneyPlanApp.DAO.AllocationDAO;
import com.siit.JourneyPlanApp.DAO.CarsDAO;
import com.siit.JourneyPlanApp.DAO.ReservationDAO;
import com.siit.JourneyPlanApp.DAO.RoutesDAO;
import com.siit.JourneyPlanApp.Model.Cars;
import com.siit.JourneyPlanApp.Model.Reservation;
import com.siit.JourneyPlanApp.Model.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Controller
public class AllocationController {

    @Autowired
    private AllocationDAO allocationDAO;

    @Autowired
    private ReservationDAO reservationDAO;

    @Autowired
    private RoutesDAO routesDAO;

    @Autowired
    private CarsDAO carsDAO;



    private Route routeInfo;


    private List<Route> listOfRoutes;

    private List<LocalDate> listOfDates = new ArrayList<>();


    @GetMapping("/addAllocation")
    public ModelAndView getAddAllocation(RedirectAttributes redirectAttributes){

        listOfRoutes = this.routesDAO.findAllRoutes();
        ModelAndView mav = new ModelAndView("addallocation");
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
        mav.addObject("rezervareSelectata", new Reservation());


        return mav;
    }

    @RequestMapping(value = "/listReservation", method = RequestMethod.POST, consumes = "application/x-www-form-urlencoded")
    public ModelAndView listReservations(RedirectAttributes redirectAttributes, WebRequest request) {

        listOfRoutes = this.routesDAO.findAllRoutes();
        ModelAndView mav = new ModelAndView("addallocation");
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


        redirectAttributes.addFlashAttribute("dataSelectata",request.getParameter("dataSelectata").toString());
        redirectAttributes.addFlashAttribute("rutaSelectata",request.getParameter("rutaSelectata").toString());


        List<Reservation> listOfUnAllocatedReservations = this.reservationDAO.findUnAllocatedReservation(request.getParameter("dataSelectata").toString(),
                        request.getParameter("rutaSelectata").toString());

        List<Cars> listOfUnAllocatedCars = this.carsDAO.findCarUnAllocated(request.getParameter("dataSelectata").toString());

        listOfUnAllocatedCars.size();

        mav.addObject("listOfUnAllocatedReservations", listOfUnAllocatedReservations);
        mav.addObject("listOfUnAllocatedCars", listOfUnAllocatedCars);

        mav.addObject("lastRouteSelected", request.getParameter("rutaSelectata").toString());
        mav.addObject("lastDateSelected", request.getParameter("dataSelectata").toString());


        return mav;

    }





    @RequestMapping(value = "/listReservation", method = RequestMethod.POST, consumes = "application/x-www-form-urlencoded", params="action=aloca")
    public ModelAndView allocateReservation(@ModelAttribute(name="rezervareSelectata1") Reservation rezervareSelectata, RedirectAttributes redirectAttributes, WebRequest request) {

        listOfRoutes = this.routesDAO.findAllRoutes();
        ModelAndView mav = new ModelAndView("addallocation");
        mav.addObject("listOfRoutes", this.listOfRoutes);

        if(!listOfDates.isEmpty()){
            listOfDates.clear();
        }

        request.getParameter("rezervareSelectata");

        LocalDate dateNow = LocalDate.now();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        String dateNowString = dateNow.format(formatter);


        listOfDates.add(LocalDate.parse(dateNowString, formatter));
        listOfDates.add(LocalDate.parse(dateNowString, formatter).plusDays(1));
        listOfDates.add(LocalDate.parse(dateNowString, formatter).plusDays(2));
        listOfDates.add(LocalDate.parse(dateNowString, formatter).plusDays(3));


        mav.addObject("listOfDates", this.listOfDates);

        redirectAttributes.addFlashAttribute("dataSelectata",request.getParameter("dataSelectata").toString());
        redirectAttributes.addFlashAttribute("rutaSelectata",request.getParameter("rutaSelectata").toString());


        List<Reservation> listOfUnAllocatedReservations = this.reservationDAO.findUnAllocatedReservation(request.getParameter("dataSelectata").toString(),
                request.getParameter("rutaSelectata").toString());

        List<Cars> listOfUnAllocatedCars = this.carsDAO.findCarUnAllocated(request.getParameter("dataSelectata").toString());

        listOfUnAllocatedCars.size();

        mav.addObject("listOfUnAllocatedReservations", listOfUnAllocatedReservations);
        mav.addObject("listOfUnAllocatedCars", listOfUnAllocatedCars);

        mav.addObject("lastRouteSelected", request.getParameter("rutaSelectata").toString());
        mav.addObject("lastDateSelected", request.getParameter("dataSelectata").toString());

        mav.addObject("rezervareSelectata", rezervareSelectata);
        mav.addObject("lastReservationSelected", rezervareSelectata.getNumeClient() + " " + rezervareSelectata.getPrenumeClient() + " - " + rezervareSelectata.getNrTelefon());


        long idAllocation = 0;
        idAllocation = this.allocationDAO.findMaxId();
        idAllocation += 1;
        String nrMasina = request.getParameter("masinaSelectata").toString();



        if(this.allocationDAO.insertNewAllocation(idAllocation, Long.parseLong(request.getParameter("rezervareSelectata").toString()),nrMasina) >=1 ) {
            redirectAttributes.addFlashAttribute("messageSuccesful", "Allocation has been performed with success!");
            redirectAttributes.addFlashAttribute("alertClass", "alert-success");

            return new ModelAndView("redirect:/addAllocation");
        }
        else{
            redirectAttributes.addFlashAttribute("messageSuccesful", "Allocation has not been performed!");
            redirectAttributes.addFlashAttribute("alertClass", "alert-danger");

            return new ModelAndView("redirect:/addAllocation");
        }

    }

}
