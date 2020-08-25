package com.mindex.challenge.dao;

import com.mindex.challenge.data.Compensation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository Interface for task 2 of the assessment to provide
 * basic CRUD functionality with the database and find compensations
 * for a given employee
 *
 * @author Erwin Palani
 */
@Repository
public interface CompensationRepository extends MongoRepository<Compensation, String> {

    Compensation findCompensationByEmployeeId(String employeeId);
}
