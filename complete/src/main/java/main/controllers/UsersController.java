package main.controllers;

import main.Repositorys.UserTypeRepository;
import main.models.User;
import main.models.UserBuilder;
import main.Repositorys.UserRepository;
import main.models.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by vakhtanggelashvili on 10/21/15.
 */
@Controller
public class UsersController {

    @RequestMapping("/createuser")
    @ResponseBody
    public String create(@RequestParam(value="username",required = true, defaultValue="")String username,
                         @RequestParam(value="password",required = true, defaultValue="")String password,
                         @RequestParam(value="email",required = true, defaultValue="")String email,
                         @RequestParam(value="name",required = false, defaultValue="")String name,
                         @RequestParam(value="surname",required = false, defaultValue="")String surname,
                         @RequestParam(value="address",required = false,defaultValue="")String address,
                         @RequestParam(value="organisationId",required = false,defaultValue="0")long organisationId,
                         @RequestParam(value="mobile",required = false,defaultValue="")String mobile,
                         @RequestParam(value="personalNumber",required = false,defaultValue="")String personalNumber,
                         @RequestParam(value="type",required = true,defaultValue = "0")int type,
                         @RequestParam(value="regionId",required = false, defaultValue="0")int regionId,
                         @RequestParam(value="zoneId",required = false, defaultValue="0")int zoneId) {
        User user = null;
        user=new UserBuilder().setAddress(address)
                .setUsername(username)
                .setPassword(password)
                .setEmail(email)
                .setName(name)
                .setSurname(surname)
                .setAddress(address)
                .setOrganisationId(organisationId)
                .setMobile(mobile)
                .setPersonalNumber(personalNumber)
                .setType(type)
                .setRegionId(regionId)
                .setZoneId(zoneId)
                .createUser();
        try {

            userDao.save(user);
        }
        catch (Exception ex) {
            return "Error creating the user: " + ex.toString();
        }
        return "მომხმარებელი შეიქმნა წარმატებით! (id = " + user.getId() + ")";
    }

    private Pageable constructPageSpecification(int pageIndex) {
        Pageable pageSpecification = new PageRequest(pageIndex, 3);
        return pageSpecification;
    }
    @RequestMapping("/getusers")
    @ResponseBody
    public Page<User> getusers(int index,String search){
        Page<User> userList=null;

        userList=userDao.findByUsernameOrEmailOrAddress(search,search,search,constructPageSpecification(index));

        //userList.

        return userList;
    }

    @RequestMapping("/edituser")
    @ResponseBody
    public User editUser(long id,User k){
        User user=userDao.findOne(id);

        if(k.getAddress()!=null){
            user.setAddress(k.getAddress());
        }
        if(k.getEmail()!=null){
            user.setEmail(k.getEmail());
        }
        if(k.getUsername()!=null){
            user.setUsername(k.getUsername());
        }
        if(k.getPassword()!=null){
            user.setPassword(k.getPassword());
        }
        if(k.getName()!=null){
            user.setName(k.getName());
        }
        if(k.getSurname()!=null){
            user.setSurname(k.getSurname());
        }
        if(k.getMobile()!=null){
            user.setMobile(k.getMobile());
        }
        if(k.getPersonalNumber()!=null){
            user.setPersonalNumber(k.getPersonalNumber());
        }
        if(k.getType()!=0){
            user.setType(k.getType());
        }
        if(k.getRegionId()!=0){
            user.setRegionId(k.getRegionId());
        }
        if(k.getZoneId()!=0){
            user.setAddress(k.getAddress());
        }

        userDao.save(user);

        return user;
    }
    @RequestMapping("/deleteuser")
    @ResponseBody
    public String deleteUser(long id){
        try{
            userDao.delete(id);
            return "წაიშალა წარმატებით";
        }catch (Exception e){
            return "წაშლის დროს მოხდა შეცდომა";
        }


    }

    @Autowired
    private UserRepository userDao;
}