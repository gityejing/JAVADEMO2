/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jodd.db.person;

import java.util.Map;
import jodd.db.oom.ColumnData;
import jodd.db.oom.DbSqlGenerator;
import jodd.db.oom.sqlgen.ParameterValue;

/**
 *
 * @author Administrator
 */
public class PersonDbSqlGenerator implements DbSqlGenerator{

    @Override
    public String generateQuery() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Map<String, ParameterValue> getQueryParameters() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Map<String, ColumnData> getColumnData() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String[] getJoinHints() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
