package ru.beamforce.tmp;

import com.vladmihalcea.hibernate.type.json.JsonType;
import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Andrey Korneychuk on 08-Apr-22
 * @version 1.0
 */
@Entity(name = "Book")
@Table(name = "book")
@TypeDef(name = "json", typeClass = JsonType.class)
public
class Book {

	@Id
	@GeneratedValue
	private Long id;

	@NaturalId
	@Column(length = 15)
	private String isbn;

	@Type(type = "json")
	@Column(columnDefinition = "json")
	private Map<String, String> properties = new HashMap<>();

	public String getIsbn() {
		return isbn;
	}

	public Book setIsbn(String isbn) {
		this.isbn = isbn;
		return this;
	}

	public Map<String, String> getProperties() {
		return properties;
	}

	public Book setProperties(Map<String, String> properties) {
		this.properties = properties;
		return this;
	}

	public Book addProperty(String key, String value) {
		properties.put(key, value);
		return this;
	}
}