package com.imcode.controllers.html;

import com.imcode.services.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by vitaly on 05.03.15.
 */
@Controller
@RequestMapping("mock")
public class MockControler {

    @RequestMapping(value = "jsonp",params = "callback")
    @ResponseBody
    public String jsonpTest(@RequestParam("callback") String functionName) {
        return functionName + "(" +
                                    "[" +
                                        "{\"firstName\": \"Vitaly\", \"lastName\": \"Sereda\"}, " +
                                        "{\"firstName\": \"Andrey\", \"lastName\": \"Demidov\"}" +
                                    "]" +
                                ");";
    }
//    @Autowired
//    private ApplicationService applicationService;

//    @Autowired
//    SchoolClassService classService;

//    @RequestMapping("/")
//    public String listSchools(Map<String, Object> map, Principal principal) {
//        map.put("school", new School());
//        map.put("schoolList", schoolService.findAll());
//
//        return "school";
//    }
//
//    @RequestMapping(value = "/xml", method = RequestMethod.POST)
//    public String importApplication(@RequestParam("body") String body, Model model) {
//
//
//        try {
//            Application statement = new Application();
//            statement.setStatus(StatementStatus.created);
//            applicationService.save(statement);
//            model.asMap().clear();
//            model.addAttribute("message", new Message(MessageType.SUCCESS, "SUCCESS"));
//        } catch (Exception e) {
//            model.addAttribute("message", new Message(MessageType.ERROR, "ERROR"));
//        }
//
//        return "xml/show";
//    }
//
//    @RequestMapping(value = "/xml", method = RequestMethod.GET)
//    public String showImportApplicationForm() {
//
//        return "xml/show";
//    }

//    @RequestMapping(value = "/add", method = RequestMethod.POST)
//    public String addContact(@ModelAttribute("contact") School school,
//                             BindingResult result) {
//
//        schoolService.save(school);
//
//        return "redirect:/index";
//    }
//
//    @RequestMapping("/delete/{schoolId}")
//    public String deleteContact(@PathVariable("schoolId") Long schoolId) {
//
//        schoolService.delete(schoolId);
//
//        return "redirect:/index";
//    }
//
//    @RequestMapping("/tok")
////    @ResponseBody
//    String takeToken(@RequestParam Map<String,String> allRequestParams, HttpServletRequest req) {
//        String thisUri = req.getScheme() + "://" +   // "http" + "://
//                req.getServerName() +       // "myhost"
//                ":" +                           // ":"
//                req.getServerPort();
//        if (allRequestParams.get("redirect_uri") != null) {
//            System.out.println(allRequestParams.get("redirect_uri"));
//        }
//        RestTemplate restTemplate = new RestTemplate();
//        allRequestParams.put("grant_type", "password");
//        allRequestParams.put("client_id", "restapp2");
//        allRequestParams.put("client_secret", "restapp2");
//        allRequestParams.put("scope", "read+write+trust");
////        allRequestParams.put("", "");
//        String uri = thisUri + "/oauth/token?grant_type=password&client_id=restapp2&client_secret=restapp2&scope=read+write+trust&username=" + allRequestParams.get("username") + "&password=" + allRequestParams.get("password");
//        Map result = restTemplate.getForObject(uri, Map.class);
//
//        if (allRequestParams.get("redirect_uri") != null) {
//            restTemplate.postForObject(allRequestParams.get("redirect_uri") + "?access_token=" + result.get("access_token") + "&refresh_token=" + result.get("refresh_token"), result, String.class, result);
//        }
//
////        return result.toString();
//        return "redirect:" + allRequestParams.get("redirect_uri");
//    }
//   @RequestMapping("/resp")
//   @ResponseBody
//    String getToken(@RequestParam Map<String,String> allRequestParams) {
////        if (allRequestParams.get("redirect_uri") != null) {
////            System.out.println(allRequestParams.get("redirect_uri"));
////        }
////        RestTemplate restTemplate = new RestTemplate();
////        allRequestParams.put("grant_type", "password");
////        allRequestParams.put("client_id", "restapp2");
////        allRequestParams.put("client_secret", "restapp2");
////        allRequestParams.put("scope", "read+write+trust");
//////        allRequestParams.put("", "");
////        String uri = "http://localhost:8080/oauth/token?grant_type=password&client_id=restapp2&client_secret=restapp2&scope=read+write+trust&username="+ allRequestParams.get("username") + "&password=" + allRequestParams.get("password");
////        Map result = restTemplate.getForObject(uri, Map.class);
//
//        return allRequestParams.toString();
//    }
////
////    @RequestMapping("/login")
////    public String home() {
////        return "redirect:/index";
////    }
////
////    @RequestMapping("/")
////    public String home() {
////        return "redirect:/index";
////    }
//
//
    
}

