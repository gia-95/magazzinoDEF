package it.TNetwork.magazzino.service.utils;

import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.bson.types.ObjectId;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;



public class Utility {

    /**
     * encode clear string to BASE54 string
     *
     * @param stringToEncode
     * @return
     */
    public static String encodeBASE64(String stringToEncode) {
        return Base64.getEncoder().encodeToString(stringToEncode.getBytes());
    }

    /**
     * decode BASE64 string to clear string
     *
     * @param stringToDecode
     * @return
     */
    public static String decodeBASE64(String stringToDecode) {
        return new String(Base64.getDecoder().decode(stringToDecode));
    }

    /**
     * set default 'deleteDate' filter conditions
     *
     * @param filterMap
     * @return
     */
    public static Map<String, Object> setDefaultFilterCondition(Map<String, Object> filterMap) {
        Map<String, Object> newFilterMap = new HashMap<>();
        if (filterMap != null) {
            newFilterMap.putAll(filterMap);
        }

        if (!newFilterMap.containsKey("deleteDate")) {
            Map<String, Object> existDeleteMap = new HashMap<>();
            existDeleteMap.put("$exists", false);
            newFilterMap.put("deleteDate", existDeleteMap);
        }
        return newFilterMap;
    }

    /**
     * build JSON String from map
     *
     * @param map
     * @return
     * @throws JsonProcessingException
     */
    public static String mapToJSONString(Map<String, Object> map) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = objectMapper.writeValueAsString(map);
        return jsonString;
    }


    /**
     * get collection name by base document class
     *
     * @param clazz
     * @return
     */
    public static String getCollectionName(Class<?> clazz) {
        String collectionName = "";
        for (Document docAnn : clazz.getAnnotationsByType(Document.class)) {
            collectionName = docAnn.collection();
        }
        return collectionName;
    }

    /**
     * check if a string is null o empty
     *
     * @param value
     * @return
     */
    public static boolean checkValid(String value) {
        boolean isStringValid = value != null && !value.equals("");
        return isStringValid;
    }


    /**
     * check list valid
     *
     * @param list
     * @return
     */
    public static boolean checkValid(List<?> list) {
        boolean isListValid = list != null && !list.isEmpty();
        return isListValid;
    }

    /**
     * check if a date is null o empty
     *
     * @param value
     * @return
     */
    public static boolean checkValid(Date value) {
        return value != null;
    }

    /**
     * check if a string is null o empty
     *
     * @param value
     * @return
     */
    public static boolean checkValid(int value) {
        return value > 0;
    }

    /**
     * check if a double value is valid
     *
     * @param value
     * @return
     */
    public static boolean checkValid(double value) {
        return value > 0;
    }

    /**
     * check if an email is valid
     *
     * @param email
     * @return
     */
    public static boolean checkEmailValid(String email) {
        boolean isEmailValid = false;
        Pattern p = Pattern.compile(".+@.+\\.[a-z]+");
        Matcher m = p.matcher(email)
;
        isEmailValid = m.matches();
        return isEmailValid;
    }

}