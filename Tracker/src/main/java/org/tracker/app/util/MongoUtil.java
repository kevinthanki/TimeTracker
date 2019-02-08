package org.tracker.app.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

/**
 * 
 * @author kevin
 *
 */
@Repository
public class MongoUtil {

	@Autowired
	MongoTemplate mongoTemplate;

	public boolean checkIfRecordExists(String collectionName, String fieldName, Object value) {

		if (checkIfCollectionExists(collectionName)) {
			Query query = new Query();
			query.addCriteria(Criteria.where(fieldName).is(value));
			return mongoTemplate.exists(query, collectionName);
		} else {
			return false;
		}
	}

	public boolean checkIfCollectionExists(String collectionName) {

		if (collectionName == null || collectionName.isEmpty())
			return false;
		return mongoTemplate.collectionExists(collectionName);
	}

	public List<Object> findWithCriteria(String collectionName, String fieldName, Object value, Class clazz) {
		List<Object> list = new ArrayList<>();
		if (checkIfCollectionExists(collectionName)) {
			Query query = new Query();
			query.addCriteria(Criteria.where(fieldName).is(value));
			list = mongoTemplate.find(query, clazz);
		}
		return list;
	}
}
