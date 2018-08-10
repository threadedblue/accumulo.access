package iox.accumulo;

import java.util.Map;

import org.apache.accumulo.core.client.security.tokens.AuthenticationToken;
import org.apache.accumulo.core.client.security.tokens.PasswordToken;

public class AccumuloConfig {
	
	private Map<String, String> creds;
	public final static String INSTANCE = "instance";
	public final static String USER = "user";
	public final static String PASSWORD = "password";
	
	public AccumuloConfig() {
		super();
	}
	
	public Map<String, String> getCreds() {
		return creds;
	}
	
	public String getInstance() {
		return creds.get(INSTANCE);
	}

	public String getUser() {
		return creds.get(USER);
	}
	
	public AuthenticationToken getPassword() {
		return new PasswordToken(creds.get(PASSWORD));
	}
}
