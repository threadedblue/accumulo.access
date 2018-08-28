package iox.accumulo;

import java.io.IOException;
import java.net.URL;

import org.apache.accumulo.core.client.AccumuloException;
import org.apache.accumulo.core.client.AccumuloSecurityException;
import org.apache.accumulo.core.client.Connector;
import org.apache.accumulo.core.client.Instance;
import org.apache.accumulo.core.client.ZooKeeperInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

public class AccumuloAccess {

	private static final Logger log = LoggerFactory.getLogger(AccumuloAccess.class);

	private Connector conn;

	private final String zookeeperURI;
	private final String accumuloInstance;
	private final URL credsFile;

	public AccumuloAccess(final String zookeeperURI, final String accumuloInstance, final URL credsFile) {
		this.zookeeperURI = zookeeperURI;
		this.accumuloInstance = accumuloInstance;
		this.credsFile = credsFile;
	}

	public Connector getConnection() {
		if (conn == null) {
			log.trace("Connector was null.");
			ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
			try {
				AccumuloConfig config = mapper.readValue(credsFile, AccumuloConfig.class);
				Instance inst = new ZooKeeperInstance(accumuloInstance, zookeeperURI);
				log.debug("inst=" + inst);
				conn = inst.getConnector(config.getUser(), config.getPassword());
				log.debug("conn=" + conn);
				config = null;
			} catch (AccumuloException | IOException | AccumuloSecurityException e) {
				log.error("configFile=" + credsFile);
				log.error("", e);
			}
		}
		return conn;
	}
}