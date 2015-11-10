/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jodd.db.person;

import jodd.db.DbSession;
import jodd.db.oom.DbOomQuery;
import jodd.db.oom.sqlgen.DbSqlBuilder;

/**
 *
 * @author Administrator
 */
public class PersonDao {
    public static void main(String [] args){
        save();
    }
    public static void save(){
        DbSession session = new DbSession(new TestdbProvider());
        session.beginTransaction();
        try {
            Person person = new Person("hello111","1234563",22);
            DbSqlBuilder b = DbSqlBuilder.sql().insert(Person.class, person);
            DbOomQuery q = new DbOomQuery(session,b);
            q.executeUpdate();
            session.commitTransaction();
            session.closeSession();
        } catch (Exception ex) {
            session.rollbackTransaction();
        }
    }
}
