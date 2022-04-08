package ru.beamforce.tmp;

import com.vladmihalcea.hibernate.type.json.JsonType;
import org.hibernate.annotations.TypeDef;

import javax.persistence.MappedSuperclass;

/**
 * @author Andrey Korneychuk on 08-Apr-22
 * @version 1.0
 */
@TypeDef(name = "json", typeClass = JsonType.class)
@MappedSuperclass
public class SomeStructure {
}