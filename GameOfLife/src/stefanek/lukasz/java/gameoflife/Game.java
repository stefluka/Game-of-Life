package stefanek.lukasz.java.gameoflife;


import java.util.Scanner;

public class Game
{
	private final char ALIVE = '#';
	private final char DEAD = '.';

	private int rows;
	private int columns;
	private boolean board[][];

	// contructor - sets amount rows and columns, a initialize boolean array
	// constisting of [rows] and [columns]
	public Game(int rows, int columns)
	{
		this.rows = rows;
		this.columns = columns;
		board = new boolean[rows][columns];

	}

	public int getRows()
	{
		return rows;
	}

	public void setRows(int rows)
	{
		this.rows = rows;
	}

	public int getColumns()
	{
		return columns;
	}

	public void setColumns(int columns)
	{
		this.columns = columns;
	}

	public void fillTable()
	{

		board[5][5] = true;
		board[5][7] = true;
		board[5][9] = true;
		board[6][5] = true;
		board[6][9] = true;
		board[7][5] = true;
		board[7][9] = true;
		board[8][5] = true;
		board[8][9] = true;
		board[9][5] = true;
		board[9][7] = true;
		board[9][9] = true;

		/*
		 * for (int i = 0; i < getRows(); i++) { for (int j = 0; j <
		 * getColumns(); j++) { board[i][j] =
		 * ThreadLocalRandom.current().nextBoolean(); } }
		 */
	}

	public void showTable()
	{
		for (boolean[] rows : board)
		{
			System.out.println();
			for (boolean element : rows)
			{
				System.out.print((element) ? ALIVE : DEAD);
			}
		}
	}

	public void nextGeneration()
	{
		boolean[][] temporaryBoard = new boolean[rows][columns];

		for (int i = 0; i < getRows(); i++)
		{
			for (int j = 0; j < getColumns(); j++)
			{
				temporaryBoard[i][j] = generateNewValue(i, j);
			}
		}
		board = temporaryBoard;
	}

	private boolean generateNewValue(int x, int y)
	{

		int aliveNeighborsNumber = countALiveNeighbors(x, y);

		if (board[x][y])
		{

			if (aliveNeighborsNumber <= 1 || aliveNeighborsNumber > 3)
				return false;

			if (aliveNeighborsNumber == 3 || aliveNeighborsNumber == 2)
				return true;

		} else
		{

			if (aliveNeighborsNumber == 3)
				return true;

		}

		return false;
	}

	private int countALiveNeighbors(int x, int y)
	{
		int neighbors = 0;

		for (int i = x - 1; i <= x + 1; i++)
		{
			if (i < 0 || i > getRows() - 1)
				continue;

			for (int j = y - 1; j <= y + 1; j++)
			{
				if (j < 0 || j > getColumns() - 1 || (i == x && j == y))
					continue;

				if (board[i][j])
					neighbors++;
			}
		}
		return neighbors;
	}

	// running and invoking method
	public static void main(String[] args)
	{
		System.out.println("Welcome to game of life");
		Scanner sc = new Scanner(System.in);

		Game game = new Game(15, 15);

		game.fillTable();
		game.showTable();

		while (sc.nextLine().equals(""))
		{
			game.nextGeneration();
			game.showTable();
		}
	}

}
