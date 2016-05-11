package main.controller;

import main.service.OkapiService;
import org.springframework.core.env.SystemEnvironmentPropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by sonic on 2/25/16.
 */
@Controller
public class IndexController {

    private OkapiService okapiService;

    public IndexController () {
        okapiService = new OkapiService();
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    String index(Model model){

        List<String> dbList = okapiService.listDatabase();

        // TODO: remove
        String result = okapiService.setDatabase("09article");

        for (String database : dbList) {
            System.out.println(database);
        }

        model.addAttribute("dbList", dbList);
        model.addAttribute("result", "");
        return "index";
    }


    @RequestMapping(value="/okapi", method=RequestMethod.POST)
    public String process (Model model, @RequestParam(value="action", required=true) String action, HttpServletRequest request) {

        String result;
        switch(action)
        {
            case "selectDB" :
                String dbName = request.getParameter("dbName");
                result = okapiService.setDatabase(dbName);
                System.out.println("DB result " + result);
                model.addAttribute("result", result);
                break;
            case "parse" :
                String terms = request.getParameter("parseTerm");
                result = okapiService.parseTerms(terms);
                System.out.println("parse result " + result);
                model.addAttribute("result", result);
                break;
            case "stem" :
                String term = request.getParameter("stemTerm");
                result = okapiService.stemTerm(term);
                System.out.println("stem result " + result);
                model.addAttribute("result", result);
                break;
            case "search" :
                String searchTerm = request.getParameter("searchTerm");
                result = okapiService.stemTerm(searchTerm);
                System.out.println("stem result " + result);
                model.addAttribute("result", result);
                break;
            case "weight" :
                String searchTerms = request.getParameter("searchTerm");
                result = okapiService.getWeigh(searchTerms);
                System.out.println("weight: " + result);
                model.addAttribute("result", result);
                break;
            case "displayResult" :
                result = okapiService.displayResultSet();
                System.out.println("result-set " + result);
                model.addAttribute("result", result);
                break;

            default :
                System.out.println("Invalid grade");
        }

        return "index";
    }

}