/**
 * 
 *
 */
package fr.epita.iam.datamodel;

/**
 * @author tbrou
 *
 */
public class Identity {
	
	private String uid;
	private String displayName;
	private String email;
	//private String birthdate;
	
	
	/**
	 * @param uid
	 * @param displayName
	 * @param email
	 */
	public Identity(String uid, String displayName, String email) {
		this.uid = uid;
		this.displayName = displayName;
		this.email = email;
		//this.birthdate= birthdate;
	}
	
	
	/**
	 * @return the uid
	 */
	public String getUid() {
		return uid;
	}
	/**
	 * @param uid the uid to set
	 */
	public void setUid(String uid) {
		this.uid = uid;
	}
	/**
	 * @return the displayName
	 */
	public String getDisplayName() {
		return displayName;
	}
	/**
	 * @param displayName the displayName to set
	 */
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the birthdate
	 */
	
	//public String getbirthdate() {
		//return birthdate;
	//}
	/**
	 * @param birthdate the birthdate to set 
	 */
	/*public void setbirthdate(String birthdate) {
		this.birthdate = birthdate;
	}*/

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Identity [uid=" + uid + ", displayName=" + displayName + ", email=" + email + "]";
	}
	
	

}
