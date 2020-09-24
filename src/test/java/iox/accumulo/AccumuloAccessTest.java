package iox.accumulo;

import static org.junit.Assert.assertNotNull;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.apache.accumulo.core.client.Connector;
import org.junit.BeforeClass;
import org.junit.Test;

public class AccumuloAccessTest {

	static AccumuloAccess sut;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		String credsfile = "/accumulo-creds.yml";
		URL url = AccumuloAccessTest.class.getResource(credsfile);
		byte[] encoded = Files.readAllBytes(Paths.get(url.toURI()));
		String credentials = new String(encoded, "UTF-8");
		sut = new AccumuloAccess("haz00:2181","accumulo", credentials);
	}

	@Test
	public void testGetConnection() {
		Connector conn = sut.getConnection();
		assertNotNull(conn);
	}

}
