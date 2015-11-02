package main.Repositorys;

import main.models.Parcel;
import main.models.Region;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by vakhtanggelashvili on 10/23/15.
 */
@Transactional
public interface ParcelRepository extends JpaRepository<Parcel, Long> {



    @Query("SELECT u FROM Parcel u WHERE (u.barcode LIKE CONCAT('%',:barcode,'%')" +
            " OR u.reciever LIKE CONCAT('%',:reciever,'%')" +
            " OR u.address LIKE CONCAT('%',:address,'%')" +
            " OR u.recievedBy LIKE CONCAT('%',:recievedBy,'%'))" +
            " AND u.organisationId=:organisationId")
    Page<Parcel> findByBarcodeOrRecieverOrAddressOrRecievedByAndOrganisationId(@Param("barcode")String barcode,@Param("reciever")String reciever,@Param("address")String address,@Param("recievedBy")String recievedBy,@Param("organisationId")long organisationId,Pageable pageable);

    @Query("SELECT u FROM Parcel u WHERE (u.barcode LIKE CONCAT('%',:barcode,'%')" +
            " OR u.reciever LIKE CONCAT('%',:reciever,'%')" +
            " OR u.address LIKE CONCAT('%',:address,'%')" +
            " OR u.recievedBy LIKE CONCAT('%',:recievedBy,'%'))")
    Page<Parcel> findByBarcodeOrRecieverOrAddressOrRecievedBy(@Param("barcode")String barcode,@Param("reciever")String reciever,@Param("address")String address,@Param("recievedBy")String recievedBy,Pageable pageable);

    @Query("SELECT u FROM Parcel u WHERE (u.barcode LIKE CONCAT('%',:barcode,'%')" +
            " OR u.reciever LIKE CONCAT('%',:reciever,'%')" +
            " OR u.address LIKE CONCAT('%',:address,'%')" +
            " OR u.recievedBy LIKE CONCAT('%',:recievedBy,'%'))" +
            " AND u.regionId=:regionId")
    Page<Parcel> findByBarcodeOrRecieverOrAddressOrRecievedByAndRegionId(@Param("barcode")String barcode,@Param("reciever")String reciever,@Param("address")String address,@Param("recievedBy")String recievedBy,@Param("regionId")long regionId,Pageable pageable);

    @Query("SELECT u FROM Parcel u WHERE (u.barcode LIKE CONCAT('%',:barcode,'%')" +
            " OR u.reciever LIKE CONCAT('%',:reciever,'%')" +
            " OR u.address LIKE CONCAT('%',:address,'%')" +
            " OR u.recievedBy LIKE CONCAT('%',:recievedBy,'%'))" +
            " AND u.zoneId=:zoneId")
    Page<Parcel> findByBarcodeOrRecieverOrAddressOrRecievedByAndZoneId(@Param("barcode")String barcode,@Param("reciever")String reciever,@Param("address")String address,@Param("recievedBy")String recievedBy,@Param("zoneId")long zoneId,Pageable pageable);


    Page<Parcel> findByUserId(@Param("userId")int userId,Pageable pageable);
    Page<Parcel> findByOrganisationId(@Param("organisationId")int organisationId,Pageable pageable);
    @Query("SELECT u FROM Parcel u where (u.deliveryDate between to_date(:dateStart,'MM/DD/YYYY') and to_date(:dateEnd,'MM/DD/YYYY'))")
    Page<Parcel> findByDateRange(@Param("dateStart")Date dateStart,@Param("dateEnd")Date dateEnd,Pageable pageable);
    @Query("SELECT u FROM Parcel u where (u.deliveryDate between to_date(:dateStart,'MM/DD/YYYY') and to_date(:dateEnd,'MM/DD/YYYY')) AND u.organisationId=:organisationId")
    Page<Parcel> findByDateRangeAndId(@Param("dateStart")Date dateStart,@Param("dateEnd")Date dateEnd,@Param("organisationId")String organisationId,Pageable pageable);
    List<Parcel> findByBarcode(@Param("barcode")String barcode);
 }
