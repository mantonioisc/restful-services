package examples.hibernate.domain;

import java.util.List;

import org.hibernate.Transaction;
import org.junit.Test;

public class CompanyMappingTest extends BaseMappingTest {
	@SuppressWarnings("unchecked")
	@Test
	public void testCompanyMapping(){
		Transaction transaction = session.beginTransaction();
		
		List<Company> companies = session.createQuery("from Company").list();
		for(Company company:companies){
			System.out.println(company);
			if(company.getConsoles()!=null){
				for(Console console:company.getConsoles()){
					System.out.print("\t");
					System.out.println(console);
				}
			}
		}
		
		transaction.commit();
	}
}
