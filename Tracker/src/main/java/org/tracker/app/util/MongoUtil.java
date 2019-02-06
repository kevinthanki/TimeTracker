package org.tracker.app.util;

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
//	private static MongoUtil mongoUtil = null;

	@Autowired
	MongoTemplate mongoTemplate;

//	private MongoUtil() {
//	}

//	public static MongoUtil get() {
//		if (mongoUtil == null)
//			mongoUtil = new MongoUtil();
//		return mongoUtil;
//	}

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
}
