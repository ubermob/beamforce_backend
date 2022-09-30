package ru.beamforce.service;

/**
 * @author Andrey Korneychuk on 27-Apr-22
 * @version 1.0
 */
public class Limit {

	public static final byte USER_GRID_LIMIT;
	public static final byte USER_MODEL_LIMIT;
	public static final byte USER_FEEDBACK_LIMIT;

	static {
		USER_GRID_LIMIT = 20;
		USER_MODEL_LIMIT = 20;
		USER_FEEDBACK_LIMIT = Byte.MAX_VALUE;
	}
}