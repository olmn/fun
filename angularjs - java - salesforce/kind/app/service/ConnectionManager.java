package service;

import com.sforce.soap.partner.Connector;
import com.sforce.soap.partner.PartnerConnection;
import com.sforce.ws.ConnectionException;
import com.sforce.ws.ConnectorConfig;
import java.net.InetSocketAddress;
import java.io.IOException;
// import net.spy.memcached.MemcachedClient;

public class ConnectionManager {
	
	private static ConnectionManager ref;
	private static PartnerConnection connection;
	private static ConnectorConfig config;
	private static final String username = "artyoung@gmail.com";
	private static final String password = "123SalesForce@";
    private static final String securityToken = "7c7H2mYG9q2C1jwwl1OaBhNU";
	private static final String SESSION_KEY = "sessionKey";
	private static final String SESSION_VALUE = "sessionValue";
	
	private ConnectionManager() { }

	public static ConnectionManager getConnectionManager() {
		if (ref == null)
			ref = new ConnectionManager();
		return ref;
	}

    private static PartnerConnection _Connection = null;
	// TODO - CACHE CONNECTION
	public PartnerConnection getConnection() {

        if(_Connection == null)
        {
            _Connection = CreateConnection();
        }
        return _Connection;
    }

    private PartnerConnection CreateConnection()
    {
		try { 
			
			config = new ConnectorConfig();
			config.setUsername(username);
			config.setPassword(password + securityToken);
            config.setTraceMessage(true);

            connection = Connector.newConnection(config);

            System.out.println("Auth EndPoint: "+config.getAuthEndpoint());
            System.out.println("Service EndPoint: "+config.getServiceEndpoint());
            System.out.println("Username: "+config.getUsername());
            System.out.println("SessionId: "+config.getSessionId());


        } catch ( ConnectionException ce) {
			System.out.println("ConnectionException " +ce.getMessage());
		}        	

        //System.out.println("===========> SessionId...."+config.getSessionId());
        
		return connection;
	}

	public Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException(); 
	}

}