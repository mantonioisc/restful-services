package examples.hibernate.domain.bindings;

import static org.junit.Assert.assertEquals;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import examples.hibernate.domain.Company;
import examples.hibernate.domain.Console;
import examples.hibernate.domain.Developer;
import examples.hibernate.domain.Game;
import examples.hibernate.domain.Tag;
import examples.hibernate.domain.User;

public class XmlBindingsTest {

	private static JAXBContext context = null;
	private static Marshaller m = null;
	private static Unmarshaller u = null;
	
	private static StringWriter writer;
	
	private static final Class<?>[] classes = 
		{Company.class, Console.class, Developer.class, 
			Game.class, Tag.class, User.class};
	
	@BeforeClass
	public static void setUpClass() throws JAXBException{
		context = JAXBContext.newInstance(classes);
		m = context.createMarshaller();
		m.setProperty("jaxb.formatted.output", true);
		u = context.createUnmarshaller();
	}
	
	@Before
	public void setUp(){
		writer = new StringWriter();
	}
	
	@Test
	public void testTagBindings() throws JAXBException{
		Tag tag = new Tag();
		tag.setName("tagme");
		
		test(tag, Tag.class);
	}
	
	@Test
	public void testDeveloperBindings() throws JAXBException{
		Developer developer = new Developer();
		developer.setCountry("Japan");
		developer.setName("Konami");
		developer.setWebsite("http://konami.jp");
		
		test(developer, Developer.class);
	}
	
	@Test
	public void testUserBindings() throws JAXBException{
		User user = new User();
		user.setName("me");
		user.setLastName("me & myself");
		user.setBirthDate(new Date());
		
		test(user, User.class);
	}
	
	/**
	 * We cannot bind the company to the console creating a recursive loop,
	 * because JAXB will throw an exception.
	 * @throws JAXBException
	 */
	@Test
	public void testCompanyBindings() throws JAXBException{
		Company company = new Company();
		company.setCountry("Japan");
		company.setFullName("Sony Computer Entertainment");
		company.setName("SCEJ");
		company.setWebSite("http://www.scej.jp");
		Console console = new Console();
		console.setName("PS2");
		console.setFullName("PlayStation 2");
		company.setConsoles(Collections.singleton(console));
		
		test(company, Company.class);
	}
	
	/**
	 * Same here, avoid recursive associations.
	 * @throws JAXBException 
	 */
	@Test
	public void testConsoleBindings() throws JAXBException{
		Console console = new Console();
		console.setName("PS2");
		console.setFullName("PlayStation 2");
		Company company = new Company();
		company.setCountry("Japan");
		company.setFullName("Sony Computer Entertainment");
		company.setName("SCEJ");
		company.setWebSite("http://www.scej.jp");
		console.setCompany(company);
		
		test(console, Console.class);
	}
	
	@Test
	public void testGameBindings() throws JAXBException{
		Game game = new Game();
		game.setCode("BLUS30109");
		game.setTitle("Metal Gear Solid 4");
		game.setDescription("Lead legendary hero Solid Snake in this final chapter of the Metal Gear Solid saga");
		game.setMedia("BluRay");
		game.setReleasedYear(2008);
		game.setRate('M');
		game.setAverageRaiting(94.00);
			Console console = new Console();
			console.setName("PS3");
			console.setFullName("PlayStation 3");
			console.setHasNetworkConnection(true);
			console.setHighDefinition(true);
			console.setWireless(true);
			console.setMedia("BluRay");
				Company company = new Company();
				company.setCountry("Japan");
				company.setFullName("Sony Computer Entertainment");
				company.setName("SCEJ");
				company.setWebSite("http://www.scej.jp");
				console.setCompany(company);
			Developer developer = new Developer();
			developer.setCountry("Japan");
			developer.setName("Konami");
			developer.setWebsite("http://konami.jp");
			Tag tag = new Tag();
			tag.setName("exclusive");
			Tag tag1 = new Tag();
			tag1.setName("1080p");
		game.setConsoles(Collections.singleton(console));
		game.setDeveloper(developer);
		game.setTags(new HashSet<Tag>());
		game.getTags().add(tag);
		game.getTags().add(tag1);
		
		test(game, Game.class);
	}
	
	private <T> void test(T originalObject, Class<T> objectClass) throws JAXBException{
		m.marshal(originalObject, writer);
		
		System.out.println(writer.toString());
		
		JAXBElement<T> element = u.unmarshal(new StreamSource(new StringReader(writer.toString())), objectClass);
		
		T unmarshaled = element.getValue();
		
		System.out.println(unmarshaled);
		
		assertEquals(originalObject, unmarshaled);
	}
	
}
