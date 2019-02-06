package org.tracker.app.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.tracker.app.entity.User;

/**
 * 
 * @author kevin
 *
 */
public interface UserRepository extends MongoRepository<User, String> {

}
