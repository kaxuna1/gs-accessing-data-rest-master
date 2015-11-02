package main.controllers;

import main.Repositorys.ServiceTypeRepository;
import main.Repositorys.SessionRepository;
import main.models.Enum.UserType;
import main.models.Format;
import main.models.ServiceType;
import main.models.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by KGelashvili on 11/2/2015.
 */
@Controller
public class ServiceTypeController {
    @RequestMapping("/createservicetype")
    @ResponseBody
    public ServiceType createServiceType(@CookieValue("projectSessionId") String sessionId,
                                         @RequestParam(value="name",required = true, defaultValue="")String name,
                                         @RequestParam(value="price",required = true, defaultValue="")float price){
        if(sessionId!=null){
            Session session=sessionRepository.findOne(Long.parseLong(sessionId));
            if(session.getUser().getType()== UserType.sa.getCODE()||session.getUser().getType()== UserType.admin.getCODE()){
                ServiceType serviceType =new ServiceType(name,price);
                serviceTypeRepository.save(serviceType);
                return serviceType;
            }else return null;
        }else return null;
    }

    @RequestMapping("/getservicetypes")
    @ResponseBody
    public List<ServiceType> getFormats(){
        return serviceTypeRepository.findAll();
    }



    @Autowired
    private ServiceTypeRepository serviceTypeRepository;
    @Autowired
    private SessionRepository sessionRepository;
}
