package com.liu.ob.ob_backend.repository;

import com.liu.ob.ob_backend.model.Up;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface UpRepository extends MongoRepository<Up, ObjectId> {
    @Query(value = "{}",
            fields = "{'cData':0}")
    Page<Up> findAllWithoutCData(Pageable pageRequest);

    Up findUpByMid(Long mid);
}
