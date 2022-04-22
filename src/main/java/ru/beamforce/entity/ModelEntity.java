package ru.beamforce.entity;

import com.vladmihalcea.hibernate.type.json.JsonType;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import ru.beamforce.modelutil.container.ForceContainer;
import ru.beamforce.modelutil.container.ForceKeys;
import ru.beamforce.modelutil.container.ModelContainer;

import javax.persistence.*;
import java.util.List;

/**
 * @author Andrey Korneychuk on 10-Apr-22
 * @version 1.0
 */
@Entity
@Table(name = "models")
@TypeDef(name = "json", typeClass = JsonType.class)
public class ModelEntity extends AbstractModelAttributeEntity {

	private byte accessLevel;
	@Type(type = "json")
	@Column(name = "force_keys")
	private ForceKeys forceKeys;
	@Type(type = "json")
	@Column(name = "model_container")
	private ModelContainer modelContainer;
	@Type(type = "json")
	@Column(name = "force_container")
	private ForceContainer forceContainer;
	private byte stage;
	private int viewCounter;
	private int apiCallCounter;
	private int elementsNumber;
	private int nodesNumber;
	@CollectionTable(name = "model_comentaries", joinColumns = @JoinColumn(name = "model_id"))
	@ElementCollection(targetClass = String.class, fetch = FetchType.LAZY)
	private List<String> commentaryList;
	private long gridId;

	public ModelEntity() {
	}

//	public ModelEntity(String model, long authorId, String name, String commentary) {
//		super(authorId, name, commentary);
//		//this.model = model;
//		super.setLocalDateTime(LocalDateTime.now());
//	}


	public byte getAccessLevel() {
		return accessLevel;
	}

	public void setAccessLevel(byte accessLevel) {
		this.accessLevel = accessLevel;
	}

	public ForceKeys getForceKeys() {
		return forceKeys;
	}

	public void setForceKeys(ForceKeys forceKeys) {
		this.forceKeys = forceKeys;
	}

	public ModelContainer getModelContainer() {
		return modelContainer;
	}

	public void setModelContainer(ModelContainer modelContainer) {
		this.modelContainer = modelContainer;
	}

	public ForceContainer getForceContainer() {
		return forceContainer;
	}

	public void setForceContainer(ForceContainer forceContainer) {
		this.forceContainer = forceContainer;
	}

	public byte getStage() {
		return stage;
	}

	public void setStage(byte stage) {
		this.stage = stage;
	}

	public int getViewCounter() {
		return viewCounter;
	}

	public void setViewCounter(int viewCounter) {
		this.viewCounter = viewCounter;
	}

	public int getApiCallCounter() {
		return apiCallCounter;
	}

	public void setApiCallCounter(int apiCallCounter) {
		this.apiCallCounter = apiCallCounter;
	}

	public int getElementsNumber() {
		return elementsNumber;
	}

	public void setElementsNumber(int elementsNumber) {
		this.elementsNumber = elementsNumber;
	}

	public int getNodesNumber() {
		return nodesNumber;
	}

	public void setNodesNumber(int nodesNumber) {
		this.nodesNumber = nodesNumber;
	}

	public List<String> getCommentaryList() {
		return commentaryList;
	}

	public void setCommentaryList(List<String> commentaryList) {
		this.commentaryList = commentaryList;
	}

	public long getGridId() {
		return gridId;
	}

	public void setGridId(long gridId) {
		this.gridId = gridId;
	}
}