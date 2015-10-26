package main.controllers;

import main.Repositorys.SessionRepository;
import main.Repositorys.UserRepository;
import main.models.Session;
import main.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * Created by KGelashvili on 10/26/2015.
 */
@Controller
public class SessionController {
    @RequestMapping("/login")
    @ResponseBody
    public Session login(String username,String password){
        Session session;
        User user;
        List<User> users=userDao.findByUsernameAndPassword(username,password);

        if(users.size()==0){
            return null;
        }else{
            user=users.get(0);
            session=new Session(new Date(),user);
            user.getSessions().add(session);
            userDao.save(user);
            return session;
        }
    }
    @Autowired
    private UserRepository userDao;
    @Autowired
    private SessionRepository sessionDao;
}
