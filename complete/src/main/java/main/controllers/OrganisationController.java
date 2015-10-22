package main.controllers;

import main.Repositorys.OrganisationRepository;
import main.Repositorys.UserRepository;
import main.models.Organisation;
import main.models.OrganisationBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by vakhtanggelashvili on 10/22/15.
 */
@Controller
public class OrganisationController {

    @RequestMapping("/createorganisation")
    @ResponseBody
    public Organisation createorganisation(@RequestParam(value="name",required = true, defaultValue="")String name,
                                           @RequestParam(value="address",required = true, defaultValue="")String address,
                                           @RequestParam(value="mobileNumber",required = true, defaultValue="")String mobileNumber,
                                           @RequestParam(value="email",required = true, defaultValue="")String email,
                                           @RequestParam(value="regionId",required = true, defaultValue="0")int regionId){
        Organisation organisation=null;
        organisation= new OrganisationBuilder().setAddress(address).setEmail(email).setMobileNumber(mobileNumber).setName(name)
                .setRegionId(regionId).createOrganisation();
        try {
            organisationRepository.save(organisation);
        }
        catch (Exception e){
            return null;
        }

        return organisation;
    }
    @RequestMapping("/getorganisations")
    @ResponseBody
    public Page<Organisation> getOrganisations(int index,String search){

        return organisationRepository.findByNameOrEmailOrAddress(search,search,search,constructPageSpecification(index));

    }
    @RequestMapping("/editorganisation")
    @ResponseBody
    public Organisation editOrganisation(long id,Organisation organisationEdit){
        Organisation organisation=organisationRepository.findOne(id);
        if(organisationEdit.getAddress()!=null){
            organisation.setAddress(organisationEdit.getAddress());
        }
        if(organisationEdit.getEmail()!=null){
            organisation.setEmail(organisationEdit.getEmail());
        }
        if(organisationEdit.getName()!=null){
            organisation.setName(organisationEdit.getName());
        }
        if(organisationEdit.getRegionId()!=0){
            organisation.setRegionId(organisationEdit.getRegionId());
        }
        if(organisationEdit.getMobileNumber()!=null){
            organisation.setMobileNumber(organisationEdit.getMobileNumber());
        }
        try {
            organisationRepository.save(organisation);
        }catch (Exception e){
            return null;
        }


        return organisation;
    }

    @RequestMapping("/getorganisation")
    @ResponseBody
    public Organisation getOrganisation(long id){
        return organisationRepository.findOne(id);
    }



    private Pageable constructPageSpecification(int pageIndex) {
        Pageable pageSpecification = new PageRequest(pageIndex, 10);
        return pageSpecification;
    }
    @Autowired
    private OrganisationRepository organisationRepository;
}