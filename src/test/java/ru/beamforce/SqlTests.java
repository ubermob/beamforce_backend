package ru.beamforce;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.beamforce.dao.ModelDao;
import ru.beamforce.dao.OrganizationDao;
import ru.beamforce.entity.ModelEntity;
import ru.beamforce.entity.OrganizationEntity;
import ru.beamforce.service.OrganizationService;
import ru.beamforce.shortobject.Token;

import java.util.List;

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
	@Autowired
	private ModelDao modelDao;

	@Test
	void contextLoads() {
	}

	@Test
	void test1() {
		OrganizationEntity result = organizationDao.getOrganizationWithToken(new Token("1"));
		System.out.println(result);
	}

	@Test
	void test2() {
		OrganizationEntity result = organizationDao.getOrganizationWithToken(new Token("Z84Dwz;czIaMADLDb2lp*kKc2CDY-kMu"));
		System.out.println(result);
	}

	@Test
	void test3() {
		OrganizationEntity result = organizationDao.getOrganizationWithToken(new Token("double-token"));
		System.out.println(result);
	}

	@Test
	void test4() {
		OrganizationEntity organization = new OrganizationEntity();
		organization.setName("qwertyiop");
		boolean result = organizationService.nameIsUnique(organization);
		System.out.println(result);
		organization.setName("123456");
		result = organizationService.nameIsUnique(organization);
		System.out.println(result);
	}

	@Test
	void test5() {
		List<ModelEntity> personalModelEntityList = modelDao.getPersonalModelEntityList(39);
		System.out.println(personalModelEntityList);
	}
}