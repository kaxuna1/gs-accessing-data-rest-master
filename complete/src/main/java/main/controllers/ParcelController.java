package main.controllers;

import main.Repositorys.ParcelRepository;
import main.models.Enum.MovementType;
import main.models.Movement;
import main.models.Parcel;
import main.models.ParcelBuilder;
import main.models.Region;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

/**
 * Created by KGelashvili on 10/23/2015.
 */
@Controller
public class ParcelController {
    @RequestMapping("/createparcel")
    @ResponseBody
    public Parcel create(@RequestParam(value="organisationId",required = true, defaultValue="0")long organisationId,
                         @RequestParam(value="userId",required = true, defaultValue="0")long userId,
                         @RequestParam(value="reciever",required = true, defaultValue="")String reciever,
                         @RequestParam(value="address",required = true, defaultValue="")String address,
                         @RequestParam(value="sentFrom",required = true, defaultValue="")String sentFrom,
                         @RequestParam(value="formatId",required = true, defaultValue="0")long formatId,
                         @RequestParam(value="serviceTypeId",required = true, defaultValue="0")long serviceTypeId,@RequestParam(value = "barcode",required = true,defaultValue = "0000000000")String barcode){

        //TODO მოსალოდნელი მიტანის დრო გამოითვალოს სერვისის ტიპის მიხედვით
        Date expectedDeliveryDate=new Date(new Date().getTime()+1000*60*60*24*3);

        int status= MovementType.Registered.getCODE();

        Parcel parcel=new ParcelBuilder()
                .setOrganisationId(organisationId)
                .setUserId(userId)
                .setReciever(reciever)
                .setAddress(address)
                .setSentFrom(sentFrom)
                .setExpectedDeliveryDate(expectedDeliveryDate)
                .setStatus(status)
                .setFormatId(formatId)
                .setServiceTypeId(serviceTypeId)
                .setBarCode(barcode)
                .createParcel();
        Movement movement=new Movement(MovementType.Registered.getCODE(),"დარეგისტრირდა",new Date(),parcel);
        parcel.getMovements().add(movement);
        try {
            parcelRepository.save(parcel);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
        return parcel;
    }
    @RequestMapping("/editparcel")
    @ResponseBody
    public Parcel editParcel(long id,Parcel parcelEdit){
        Parcel parcel=parcelRepository.getOne(id);
        if(parcelEdit.getUserId()!=0){
            parcel.setUserId(parcelEdit.getUserId());
        }
        if(parcelEdit.getBarcode()!=null){
            parcel.setBarcode(parcelEdit.getBarcode());
        }
        if(parcelEdit.getReciever()!=null){
            parcel.setReciever(parcelEdit.getReciever());
        }
        if(parcelEdit.getAddress()!=null){
            parcel.setAddress(parcelEdit.getAddress());
        }
        if(parcelEdit.getSentFrom()!=null){
            parcel.setSentFrom(parcelEdit.getSentFrom());
        }
        if(parcelEdit.getExpectedDeliveryDate()!=null){
            parcel.setExpectedDeliveryDate(parcelEdit.getExpectedDeliveryDate());
        }
        if(parcelEdit.getDeliveryDate()!=null){
            parcel.setDeliveryDate(parcelEdit.getDeliveryDate());
        }
        if(parcelEdit.getStatus()!=0){
            parcel.setStatus(parcelEdit.getStatus());
        }
        if(parcelEdit.getFormatId()!=0){
            parcel.setFormatId(parcelEdit.getFormatId());
        }
        if(parcelEdit.getServiceTypeId()!=0){
            parcel.setServiceTypeId(parcelEdit.getServiceTypeId());
        }
        if(parcelEdit.getComment()!=null){
            parcel.setComment(parcelEdit.getComment());
        }
        if(parcelEdit.getRecievedBy()!=null){
            parcel.setRecievedBy(parcelEdit.getRecievedBy());
        }
        try {
            parcelRepository.save(parcel);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
        return parcel;
    }
    @RequestMapping(value = "/uploadsignature",method = RequestMethod.POST)
    @ResponseBody
    public Parcel uploadSignature(long id,@RequestParam CommonsMultipartFile[] fileUpload){
        Parcel parcel=parcelRepository.findOne(id);
        if (fileUpload != null && fileUpload.length > 0) {
            for (CommonsMultipartFile aFile : fileUpload){
                parcel.setSignature(aFile.getBytes());
                try {
                    parcelRepository.save(parcel);
                }catch (Exception e){
                    e.printStackTrace();
                    return null;
                }
            }
        }
        return parcel;
    }
    @RequestMapping(value = "/parcelsignature/{id}")
    @ResponseBody
    public byte[] getParcelSignature(HttpServletResponse response , @PathVariable("id") long id){
        return parcelRepository.findOne(id).getSignature();
    }

    @RequestMapping(value = "/getparcels")
    @ResponseBody
    public List<Parcel> getPacels(int index,String search){

        return parcelRepository.findAll();
    }

    private Pageable constructPageSpecification(int pageIndex) {
        Pageable pageSpecification = new PageRequest(pageIndex, 10);
        return pageSpecification;
    }
    @Autowired
    private ParcelRepository parcelRepository;
}
