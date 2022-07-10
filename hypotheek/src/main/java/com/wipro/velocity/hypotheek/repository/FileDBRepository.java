package com.wipro.velocity.hypotheek.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.wipro.velocity.hypotheek.model.FileDB;

public interface FileDBRepository extends MongoRepository<FileDB, String> {

}
