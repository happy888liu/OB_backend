package com.liu.ob.ob_backend.repository;

import com.liu.ob.ob_backend.model.UpData;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UpDataRepository extends MongoRepository<UpData, ObjectId> {

}
