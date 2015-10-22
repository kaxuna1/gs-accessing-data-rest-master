package main.controllers;

import main.Repositorys.OrganisationRepository;
import main.Repositorys.RegionRepository;
import main.models.Region;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by vakhtanggelashvili on 10/23/15.
 */
@Controller
public class RegionController {
    @RequestMapping("/createregion")
    @ResponseBody
    public Region create(String name){
        Region region=new Region(name);
        regionRepository.save(region);
        return region;
    }

    @RequestMapping("/editregion")
    @ResponseBody
    public Region edit(long id,Region region2){
        Region region=regionRepository.findOne(id);
        if(region2.getName()!=null){
            region.setName(region2.getName());
            try{
                regionRepository.save(region);
            }catch (Exception e){
                return null;
            }
        }
        return region;
    }


    @Autowired
    private RegionRepository regionRepository;
}
