package ru.beamforce;

import modelutil.test.Example;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.beamforce.entity.GridEntity;
import ru.beamforce.repository.GridRepository;
import ru.beamforce.tmp.GridService;

/**
 * @author Andrey Korneychuk on 07-Apr-22
 * @version 1.0
 */
@SpringBootTest
public class GridTests {

	@Autowired
	private GridService gridService;
	@Autowired
	private GridRepository gridRepository;

	@Test
	void test1() {
		GridEntity gridEntity = new GridEntity();
		System.out.println(gridEntity.getLocalDateTime());
	}

	@Test
	void test2() {
		GridEntity gridEntity = new GridEntity(
				Example.getExampleWithOffsets()
				, 0
				, "grid with offsets"
				, "grid with offsets"
		);
		gridRepository.save(gridEntity);
	}
}