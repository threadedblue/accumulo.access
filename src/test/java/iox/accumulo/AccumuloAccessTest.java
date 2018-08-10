package iox.accumulo;

import static org.junit.Assert.assertNotNull;

import java.net.URL;

import org.apache.accumulo.core.client.Connector;
import org.junit.BeforeClass;
import org.junit.Test;

public class AccumuloAccessTest {

	static AccumuloAccess sut;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		URL url = AccumuloAccess.class.getClassLoader().getResource("accumulo-creds.yml");
		sut = new AccumuloAccess("haz00:2181","accumulo", url);
	}

	@Test
	public void testGetConnection() {
		Connector conn = sut.getConnection();
		assertNotNull(conn);
	}

}
