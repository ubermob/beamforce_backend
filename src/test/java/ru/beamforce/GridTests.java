package ru.beamforce;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.beamforce.tmp.GridService;

/**
 * @author Andrey Korneychuk on 07-Apr-22
 * @version 1.0
 */
@SpringBootTest
public class GridTests {

	@Autowired
	private GridService gridService;

	@Test
	void test1() {
//		gridService.addSample();
//		System.out.println(gridService.get(0));
	}
}