package com.estevanhernandes.workshopmongodb.repositories;

import com.estevanhernandes.workshopmongodb.domain.Post;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends MongoRepository<Post, String> { }
