/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jodd.db.audit;

import java.util.Date;
import jodd.db.DbSession;
import jodd.db.oom.DbOomQuery;
import jodd.db.oom.sqlgen.DbSqlBuilder;

/**
 * 省财系统的打印模板的文件路径记录
 * @author yejing
 */
public class TblReportModelDao {
    public static void main(String [] args) {
        save();
    }
    
    public static void save(){
        DbSession session = new DbSession(new AuditSystemDB_ConnectionProvider());
        session.beginTransaction();
        try {
            TblReportModal modal = new TblReportModal();
            modal.setFsn("98");
            modal.setFprojectId("0");
            modal.setFreportName("项目收文办理");
            modal.setFreportCode("REPORT_PROJECTLETTER");
            modal.setFfilePath("项目收文办理.xml");
            modal.setFemployee("收件员1");
            modal.setFemployeeId(2);
            modal.setFtime(new Date());
            modal.setFmemo("请不要修改编码");
            modal.setForientation(2);
            modal.setForderby(98);
            DbSqlBuilder b = DbSqlBuilder.sql().insert(TblReportModal.class, modal);
            DbOomQuery q = new DbOomQuery(session,b);
            q.executeUpdate();
            session.commitTransaction();
            session.closeSession();
        } catch (Exception ex) {
            session.rollbackTransaction(); 
        }
    }
}
