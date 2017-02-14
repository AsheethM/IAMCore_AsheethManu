/**
 * 
 */
package fr.epita.iam.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.Format;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import fr.epita.iam.datamodel.Identity;

/**
 * @author tbrou
 *
 */
public class JDBCIdentityDAO {

	
	
	private Connection connection;
	
	/**
	 * @throws SQLException 
	 * 
	 */
	public JDBCIdentityDAO() throws SQLException {
		this.connection = DriverManager.getConnection("jdbc:derby://localhost:1527/IAM;create=true","asheeth","asheeth");
		System.out.println(connection.getSchema());
	}
	
	
	public void writeIdentity(Identity identity) throws SQLException {
		String insertStatement = "insert into IDENTITIES (IDENTITY_DISPLAYNAME, IDENTITY_EMAIL, IDENTITY_BIRTHDATE) "
				+ "values(?, ?, ?)";
		PreparedStatement pstmt_insert = connection.prepareStatement(insertStatement);
		pstmt_insert.setString(1, identity.getDisplayName());
		pstmt_insert.setString(2, identity.getEmail());
		Date now = new Date();
		pstmt_insert.setDate(3, new java.sql.Date(now.getTime()));

		pstmt_insert.execute();
		pstmt_insert.close();

	}
	
	public String select(String email,String displayname)throws SQLException {
		String uid = null;
		PreparedStatement pstmt_select = connection.prepareStatement("select IDENTITY_ID from IDENTITIES where IDENTITY_EMAIL=? and IDENTITY_DISPLAYNAME=?");
		pstmt_select.setString(1, email);
		pstmt_select.setString(2, displayname);
		ResultSet rs = pstmt_select.executeQuery();
		while (rs.next()) {
			
		 uid = String.valueOf(rs.getString("IDENTITY_ID"));
			//date("");
			
			}
		return uid;
	}
	
public void deleteIdentity(Identity identity) throws SQLException {
		
		String deleteStatement = "delete from IDENTITIES where IDENTITY_ID=? and IDENTITY_DISPLAYNAME=?";
		PreparedStatement pstmt_delete = connection.prepareStatement(deleteStatement);
		
		pstmt_delete.setString(1, identity.getUid());
		pstmt_delete.setString(2, identity.getDisplayName());
		pstmt_delete.executeUpdate();
		pstmt_delete.close();
	}
	
	public void modifyIdentity(Identity identity) throws SQLException {
		
		String updateStatement = "update IDENTITIES set IDENTITY_DISPLAYNAME=?, IDENTITY_EMAIL=? WHERE IDENTITY_ID=?";
		PreparedStatement pstmt_update = connection.prepareStatement(updateStatement);
		
		pstmt_update.setString(3, identity.getUid());
		pstmt_update.setString(2, identity.getEmail());
        pstmt_update.setString(1, identity.getDisplayName());
        pstmt_update.executeUpdate();
		connection.commit();
		pstmt_update.close();
	}
	
	
	public List<Identity> readAll() throws SQLException {
		List<Identity> identities = new ArrayList<Identity>();

		PreparedStatement pstmt_select = connection.prepareStatement("select * from IDENTITIES");
		ResultSet rs = pstmt_select.executeQuery();
		while (rs.next()) {
			String displayName = rs.getString("IDENTITY_DISPLAYNAME");
			String uid = String.valueOf(rs.getString("IDENTITY_ID"));
			String email = rs.getString("IDENTITY_EMAIL");
			 //date("");
			
			Identity identity = new Identity(uid, displayName, email);
			identities.add(identity);
		}
		return identities;

	}
}
