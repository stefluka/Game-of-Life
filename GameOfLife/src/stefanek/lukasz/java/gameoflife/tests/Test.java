package stefanek.lukasz.java.gameoflife.tests;

import static org.junit.Assert.*;

import org.junit.Assert;

import stefanek.lukasz.java.gameoflife.Game;


public class Test

{
	@org.junit.Test
	public void testCreate() {
		int width = 15;
		int height = 15;
		Game game = new Game(width, height);
		Assert.assertNotNull(game);
		
		for (int i = 0; i < width; i++)
		{
			for (int j = 0; j < height; j++)
			{
				Assert.assertEquals(false, game.getCellValue(i, j));
			}
		}
	}

	//Test when we check if cell value is correctly set
	@org.junit.Test
	public void testSetValue()
	{
		Game game = new Game(10, 10);
	game.setCellValue(0, 0, true);
	Assert.assertEquals(true, game.getCellValue(0, 0));
	
	game.setCellValue(0, 1, true);
	Assert.assertEquals(true, game.getCellValue(0, 1));
	
	game.setCellValue(0, 2, false);
	Assert.assertEquals(false, game.getCellValue(0, 2));
	
	}
	
	//Test when alive cell has any alive neighbors and in next generation should get to die
	@org.junit.Test
	public void testAliveCellWithAnyAliveNeighbour()
	{
		Game game = new Game(4, 4);
		game.setCellValue(1, 1, true);
		Assert.assertEquals(0, game.countAliveNeighbors(1, 1));
		game.nextGeneration();
		Assert.assertEquals(false, game.getCellValue(1, 1));
	}
	
	//Test when dead cell has 3 alive neighbors and it should get to life
	@org.junit.Test
	public void testIfDeadCellWillALive()
	{
		Game game = new Game(3, 3);
		game.setCellValue(1, 1, false);
		game.setCellValue(0, 0, true);
		game.setCellValue(0, 1, true);
		game.setCellValue(1, 0, true);
		Assert.assertEquals(3, game.countAliveNeighbors(1, 1));
		game.nextGeneration();
		Assert.assertEquals(true, game.getCellValue(1, 1));
	
	}
	//Test when cell is surrounded by more than 3 neighbors and after next generation it should die
	@org.junit.Test
	public void testWhenOverpopulation()
	{
		Game game = new Game(10,10);
		game.setCellValue(5, 5, true);
		game.setCellValue(5, 4, true);
		game.setCellValue(5, 6, true);
		game.setCellValue(4, 5, true);
		game.setCellValue(6, 5, true);
		Assert.assertEquals(4, game.countAliveNeighbors(5, 5));
		game.nextGeneration();
		Assert.assertEquals(false, game.getCellValue(5, 5));
	}
	
	//Test when cell has two neighbour and chosen cell will survive in next generation
	@org.junit.Test
	public void testWhenAliveCellWillSurvive()
	{
		Game game = new Game(1,7);
		game.setCellValue(0, 3, true);
		game.setCellValue(0, 2, true);
		game.setCellValue(0, 4, true);
		
		Assert.assertEquals(2, game.countAliveNeighbors(0, 3));
		game.nextGeneration();
		Assert.assertEquals(true, game.getCellValue(0,3));
	}

}
