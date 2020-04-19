/*package validators;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import gameManagment.PlayerManager;
import junit.framework.Assert;

public class PlayerManagerTest {
	private static PlayerManager pm;
	private List<String> fileLines;

	@Rule
	public final ExpectedException exception = ExpectedException.none();

	@BeforeClass
	public static void setup() {
		pm = new PlayerManager();
	}
	
	@Before
	public void init() {
		fileLines = new ArrayList<>();
	}
	
	@Test
	public void fileHasOnePlayer() throws Exception {
		String l1 = "Jeff 10";
		String l2 = "Jeff 3";
		String l3 = "Jeff 3";
		String l4 = "Jeff 3";
		String l5 = "Jeff 3";
		
		fileLines.add(l1);
		fileLines.add(l2);
		fileLines.add(l3);
		fileLines.add(l4);
		fileLines.add(l5);
		
		int numberOfPlayers = pm.getNumberOfPlayers(fileLines);
		
		assertEquals(1, numberOfPlayers);
	}

	@Test
	public void fileHasTwoPlayers() throws Exception {
		String l1 = "Jeff 10";
		String l2 = "John 3";
		String l3 = "John 3";
		String l4 = "Jeff 3";
		String l5 = "Jeff 3";
		String l6 = "John 5";
		
		fileLines.add(l1);
		fileLines.add(l2);
		fileLines.add(l3);
		fileLines.add(l4);
		fileLines.add(l5);
		fileLines.add(l6);
		
		int numberOfPlayers = pm.getNumberOfPlayers(fileLines);
		
		assertEquals(2, numberOfPlayers);
	}
	
	@Test
	public void fileHasThreePlayers() throws Exception {
		String l1 = "Jeff 10";
		String l2 = "John 3";
		String l3 = "Mark 3";
		String l4 = "Mark 3";
		String l5 = "John 3";
		String l6 = "Jeff 5";
		
		fileLines.add(l1);
		fileLines.add(l2);
		fileLines.add(l3);
		fileLines.add(l4);
		fileLines.add(l5);
		fileLines.add(l6);
		
		int numberOfPlayers = pm.getNumberOfPlayers(fileLines);
		
		assertEquals(3, numberOfPlayers);
	}
}
*/