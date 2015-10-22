package main.Repositorys;

import main.models.Parcel;
import main.models.Region;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Created by vakhtanggelashvili on 10/23/15.
 */
public interface ParcelRepository extends JpaRepository<Parcel, Long> {



    @Query("SELECT u FROM Parcel u WHERE (u.barcode LIKE CONCAT('%',:barcode,'%') OR u.reciever LIKE CONCAT('%',:reciever,'%') OR u.address LIKE CONCAT('%',:address,'%') OR u.recievedby LIKE CONCAT('%',:recievedby,'%')) AND u.organisationId=:organisationId")
    Page<Parcel> findByBarcodeOrRecieverOrAddressOrRecievedbyAndOrganisationId(@Param("barcode")String barcode,@Param("reciever")String reciever,@Param("address")String address,@Param("recievedby")String recievedby,@Param("organisationId")String organisationId,Pageable pageable);
}
