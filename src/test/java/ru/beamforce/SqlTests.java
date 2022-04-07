package ru.beamforce;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.beamforce.dao.OrganizationDao;
import ru.beamforce.entity.Organization;
import ru.beamforce.service.OrganizationService;
import ru.beamforce.shortobject.Token;

/**
 * @author Andrey Korneychuk on 07-Apr-22
 * @version 1.0
 */
@SpringBootTest
public class SqlTests {

	@Autowired
	private OrganizationDao organizationDao;
	@Autowired
	private OrganizationService organizationService;

	@Test
	void contextLoads() {
	}

	@Test
	void test1() {
		Organization result = organizationDao.getOrganizationWithToken(new Token("1"));
		System.out.println(result);
	}

	@Test
	void test2() {
		Organization result = organizationDao.getOrganizationWithToken(new Token("Z84Dwz;czIaMADLDb2lp*kKc2CDY-kMu"));
		System.out.println(result);
	}

	@Test
	void test3() {
		Organization result = organizationDao.getOrganizationWithToken(new Token("double-token"));
		System.out.println(result);
	}

	@Test
	void test4() {
		Organization organization = new Organization();
		organization.setName("qwertyiop");
		boolean result = organizationService.nameIsUnique(organization);
		System.out.println(result);
		organization.setName("123456");
		result = organizationService.nameIsUnique(organization);
		System.out.println(result);
	}
}