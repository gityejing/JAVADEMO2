/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jodd.db.person;


import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import jodd.db.QueryMapper;

/**
 *
 * @author Administrator
 */
public class PersonQueryMapper implements QueryMapper<Person>{
	private Logger logger = Logger.getLogger(PersonQueryMapper.class);
    @Override
    public Person process(ResultSet resultSet) throws SQLException {
        while(resultSet.next()){
        	logger.info(resultSet.getString(2));
            if(resultSet.getString(1).equals("yejing")){
                Person person = new Person(resultSet.getLong(4),resultSet.getString(1), resultSet.getString(2), resultSet.getInt(3));
                return person;
            }else {
                return new Person();
            }
        }
        return new Person();
    }
}
