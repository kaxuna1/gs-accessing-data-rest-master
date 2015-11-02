package main.controllers;

import main.Repositorys.FormatRepository;
import main.Repositorys.SessionRepository;
import main.models.Enum.UserType;
import main.models.Format;
import main.models.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by KGelashvili on 11/2/2015.
 */
@Controller
public class FormatController {
    @RequestMapping("/createformat")
    @ResponseBody
    public Format createFormat(@CookieValue("projectSessionId") String sessionId,
                               @RequestParam(value="name",required = true, defaultValue="")String name,
                               @RequestParam(value="price",required = true, defaultValue="")float price){

        if(sessionId!=null){
            Session session=sessionRepository.findOne(Long.parseLong(sessionId));
            if(session.getUser().getType()== UserType.sa.getCODE()||session.getUser().getType()== UserType.admin.getCODE()){
                Format format =new Format(name,price);
                formatRepository.save(format);
                return format;
            }else return null;
        }else return null;
    }

    @RequestMapping("/getformats")
    @ResponseBody
    public List<Format> getFormats(){
        return formatRepository.findAll();
    }

    @Autowired
    private FormatRepository formatRepository;
    @Autowired
    private SessionRepository sessionRepository;
}
