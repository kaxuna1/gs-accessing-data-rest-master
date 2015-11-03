package main.controllers;

import main.Repositorys.FormatRepository;
import main.Repositorys.ParcelRepository;
import main.Repositorys.SessionRepository;
import main.Repositorys.ZoneRepository;
import main.models.*;
import main.models.Enum.JsonReturnCodes;
import main.models.Enum.MovementType;
import main.models.Enum.UserType;
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
    public JsonMessage create(@CookieValue("projectSessionId") String sessionId,
                         @RequestParam(value="reciever",required = true, defaultValue="")String receiver,
                         @RequestParam(value="address",required = true, defaultValue="")String address,
                         @RequestParam(value="sentFrom",required = true, defaultValue="")String sentFrom,
                         @RequestParam(value="formatId",required = true, defaultValue="0")long formatId,
                         @RequestParam(value="serviceTypeId",required = true, defaultValue="0")long serviceTypeId,
                         @RequestParam(value = "barcode",required = true,defaultValue = "0000000000")String barcode){

        if(sessionId!=null){
            Session session=sessionRepository.findOne(Long.parseLong(sessionId));
            if(session.isIsactive()){
                if(session.getUser().getType()== UserType.sa.getCODE()||session.getUser().getType()==UserType.organisation.getCODE()||session.getUser().getType()==UserType.organisationUser.getCODE()){

                    if(parcelRepository.findByBarcode(barcode).size()>0){
                        return new JsonMessage(JsonReturnCodes.BARRCODEEXISTS.getCODE(),"ბარკოდი უკვე არის დარეგისტრირებული");
                    }
                  //TODO მოსალოდნელი მიტანის დრო გამოითვალოს სერვისის ტიპის მიხედვით
                    Date expectedDeliveryDate=new Date(new Date().getTime()+1000*60*60*24*3);

                    int status= MovementType.Registered.getCODE();

                    Parcel parcel=new ParcelBuilder()
                            .setOrganisation(session.getUser().getOrganisation())
                            .setUserId(session.getUser().getId())
                            .setReciever(receiver)
                            .setAddress(address)
                            .setSentFrom(sentFrom)
                            .setExpectedDeliveryDate(expectedDeliveryDate)
                            .setStatus(status)
                            .setFormat(formatRepository.findOne(formatId))
                            .setServiceTypeId(serviceTypeId)
                            .setBarCode(barcode)
                            .setRegion(session.getUser().getRegion())
                            .setZone(zoneRepository.findOne(1l))
                            .createParcel();
                    Movement movement=new Movement(MovementType.Registered.getCODE(),"დარეგისტრირდა",new Date(),parcel);
                    parcel.getMovements().add(movement);
                    try {
                        parcelRepository.save(parcel);
                    }catch (Exception e){
                        e.printStackTrace();
                        return new JsonMessage(JsonReturnCodes.ERROR.getCODE(),"ჩანაწერი შეიქმნისას მოხმდა შეცდომა");
                    }
                    return new JsonMessage(JsonReturnCodes.Ok.getCODE(),"ჩანაწერი შეიქმნა წარმატებით");
                }else return new JsonMessage(JsonReturnCodes.DONTHAVEPERMISSION.getCODE(),"არ გაქვთ შესაბამისი უფლება");
            }else return new JsonMessage(JsonReturnCodes.SESSIONEXPIRED.getCODE(),"სესიას გაუვიდა ვადა");
        }else return new JsonMessage(JsonReturnCodes.NOTLOGGEDIN.getCODE(),"საჭიროა სისტემაში შესვლა");


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
    public Parcel uploadSignature(@CookieValue("projectSessionId") String sessionId,long id,@RequestParam CommonsMultipartFile[] fileUpload){
        if(sessionId!=null){
            Session session=sessionRepository.findOne(Long.parseLong(sessionId));
            if(session.isIsactive()){
                if(session.getUser().getType()==UserType.courier.getCODE()){
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
                }else return null;
            }else return null;
        }else return null;
    }
    @RequestMapping(value = "/parcelsignature/{id}")
    @ResponseBody
    public byte[] getParcelSignature(HttpServletResponse response,@CookieValue("projectSessionId") String sessionId , @PathVariable("id") long id){
        if(sessionId!=null){
            Session session=sessionRepository.findOne(Long.parseLong(sessionId));
            if(session.isIsactive()){
                Parcel parcel=parcelRepository.findOne(id);
                if(session.getUser().getOrganisation()==parcel.getOrganisation()){
                    return parcel.getSignature();
                }else return null;

            }else return null;

        }else return null;

    }

    @RequestMapping(value = "/getparcels")
    @ResponseBody
    public Page<Parcel> getParcels(@CookieValue("projectSessionId") String sessionId,int index,String search){


        if(sessionId!=null){
            Session session=sessionRepository.findOne(Long.parseLong(sessionId));
            if(session.isIsactive()){
                if(session.getUser().getType()==UserType.sa.getCODE()||session.getUser().getType()==UserType.admin.getCODE())
                    return parcelRepository.findByBarcodeOrRecieverOrAddressOrRecievedBy(search,search,search,search,constructPageSpecification(index));
                else {
                    if(session.getUser().getType()==UserType.regionManager.getCODE()){
                        return parcelRepository.findByBarcodeOrRecieverOrAddressOrRecievedByAndRegionId(search, search, search, search, session.getUser().getRegion().getId(),constructPageSpecification(index));
                    }else{
                        if (session.getUser().getType()==UserType.zoneManager.getCODE()){
                            parcelRepository.findByBarcodeOrRecieverOrAddressOrRecievedByAndZoneId(search, search, search, search, session.getUser().getZone().getId(),constructPageSpecification(index));
                        }else
                            return parcelRepository.findByBarcodeOrRecieverOrAddressOrRecievedByAndOrganisation(search, search, search, search, session.getUser().getOrganisation().getId(),constructPageSpecification(index));

                    }return null;
                }
            }else return null;
        }else return null;
    }

    private Pageable constructPageSpecification(int pageIndex) {
        Pageable pageSpecification = new PageRequest(pageIndex, 10);
        return pageSpecification;
    }
    @Autowired
    private ZoneRepository zoneRepository;
    @Autowired
    private FormatRepository formatRepository;
    @Autowired
    private ParcelRepository parcelRepository;
    @Autowired
    private SessionRepository sessionRepository;
}
