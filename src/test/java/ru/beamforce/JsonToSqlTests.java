package ru.beamforce;

import modelutil.test.Example;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.beamforce.tmp.Book;
import ru.beamforce.tmp.BookRepo;
import ru.beamforce.entity.GridEntity;
import ru.beamforce.repository.GridRepository;

import java.time.LocalDateTime;

/**
 * @author Andrey Korneychuk on 08-Apr-22
 * @version 1.0
 */
@SpringBootTest
public class JsonToSqlTests {

	@Autowired
	private GridRepository gridRepository;
	@Autowired
	private BookRepo bookRepo;

	@Test
	void test1() {
		try {
			GridEntity gridEntity = new GridEntity();
			gridEntity.setGridContainer(Example.getExample());
			gridEntity.setName("ABC");
			gridEntity.setCommentary("Commen");
			gridRepository.save(gridEntity);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	void test2() {
		try {
			bookRepo.save(
					new Book()
							.setIsbn("978-9730228236")
							.addProperty("title", "High-Performance Java Persistence")
							.addProperty("author", "Vlad Mihalcea")
							.addProperty("publisher", "Amazon")
							.addProperty("price", "$44.95")
			);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	void test3() {
		System.out.println(LocalDateTime.now());
	}
}