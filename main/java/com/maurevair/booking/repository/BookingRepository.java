package com.maurevair.booking.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.maurevair.booking.entity.BookingDetails;

@Repository
public interface BookingRepository extends CrudRepository<BookingDetails, String>{

}
