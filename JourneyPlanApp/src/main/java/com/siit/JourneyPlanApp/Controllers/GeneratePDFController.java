package com.siit.JourneyPlanApp.Controllers;


import com.siit.JourneyPlanApp.DAO.AllocationDAO;
import com.siit.JourneyPlanApp.DAO.CarsDAO;
import com.siit.JourneyPlanApp.DAO.ReservationDAO;
import com.siit.JourneyPlanApp.DAO.RoutesDAO;
import com.siit.JourneyPlanApp.Model.Cars;
import com.siit.JourneyPlanApp.Model.GeneratePdfReport;
import com.siit.JourneyPlanApp.Model.Reservation;
import com.siit.JourneyPlanApp.Model.Route;
import javassist.bytecode.ByteArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.ByteArrayInputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Controller
public class GeneratePDFController {

    @Autowired
    private AllocationDAO allocationDAO;

    @Autowired
    private ReservationDAO reservationDAO;

    @Autowired
    private RoutesDAO routesDAO;

    @Autowired
    private CarsDAO carsDAO;

    private List<Route> listOfRoutes;

    private List<LocalDate> listOfDates = new ArrayList<>();

    private List<Cars> listOfAllCars;

    @GetMapping("/pdf")
    public ModelAndView getGeneratedPdfView(RedirectAttributes redirectAttributes){
        listOfRoutes = this.routesDAO.findAllRoutes();
        ModelAndView mav = new ModelAndView("generatepdf");
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

        listOfAllCars = this.carsDAO.findAllCars();

        mav.addObject("listOfAllCars", this.listOfAllCars);

        return mav;
    }

    @RequestMapping(value = "/generatePDF", method = RequestMethod.POST, consumes = "application/x-www-form-urlencoded", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> generatePDFReport(RedirectAttributes redirectAttributes, WebRequest request){


        List<Reservation> listOfReservationsToPDF = this.reservationDAO.findAllocatedReservation(request.getParameter("dataSelectata").toString(), request.getParameter("rutaSelectata"), request.getParameter("masinaSelectata"));
        ByteArrayInputStream bis = GeneratePdfReport.foaieTraseuReport( request.getParameter("dataSelectata"), request.getParameter("rutaSelectata"), request.getParameter("masinaSelectata"), listOfReservationsToPDF);

        var headers = new HttpHeaders();
        String pdfFileName = "foaie_cursa_" + request.getParameter("dataSelectata").replace('-','_').toString() + "_" + request.getParameter("masinaSelectata").replace('-', '_').toString() + ".pdf";
        headers.add("Content-Disposition", "inline; filename=" + pdfFileName);



        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }
}
