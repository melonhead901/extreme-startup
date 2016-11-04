package can.touch;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by rchew on 11/4/16.
 */
public class ContactDetailMapper implements ResultSetMapper<ContactDetail> {
    @Override
    public ContactDetail map(int index, ResultSet r, StatementContext ctx) throws SQLException {
        return new ContactDetail(r.getString("phonenumber"), r.getString("name"));
    }
}
