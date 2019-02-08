package org.tracker.app.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.tracker.app.entity.AccessToken;

public interface AccessTokenRepository extends MongoRepository<AccessToken, String> {

}
