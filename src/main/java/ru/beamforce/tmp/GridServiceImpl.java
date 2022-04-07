package ru.beamforce.tmp;

import modelutil.container.GridContainer;
import modelutil.gridparser.GridParser;
import org.springframework.stereotype.Service;
import ru.beamforce.dto.GridInputDTO;

import java.util.HashMap;

/**
 * @author Andrey Korneychuk on 07-Apr-22
 * @version 1.0
 */
@Service
public class GridServiceImpl implements GridService {

	private static HashMap<Integer, GridContainer> storage = new HashMap<>();
	private static HashMap<Integer, String> gridContainerAsStringStorage = new HashMap<>();
	private static HashMap<Integer, String> gridParserLogStorage = new HashMap<>();
	private static int idCounter = 1;

	@Override
	public void add(GridInputDTO gridInputDTO) {
		GridParser gridParser = new GridParser(0, 0, 0);
		// From textarea input contains "\r"
		// Remove this
		gridParser.parse(gridInputDTO.getAlong().replace("\r", "")
				+ "\n" + gridInputDTO.getAcross().replace("\r", ""));
		storage.put(idCounter, gridParser.getGridContainer());
		gridContainerAsStringStorage.put(idCounter, gridParser.getGridContainer().toString());
		gridParserLogStorage.put(idCounter, gridParser.getLogMessages());
		idCounter++;
		System.out.println("------------");
		System.out.println(gridContainerAsStringStorage.get(idCounter - 1));
		System.out.println("------------");
		System.out.println(gridParserLogStorage.get(idCounter - 1));
		System.out.println("------------");
	}

	@Override
	public GridContainer get(int id) {
		return storage.get(id);
	}
}