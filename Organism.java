import acm.util.RandomGenerator;

public abstract class Organism {
	protected World world;
	protected int x;
	protected int y;
	protected boolean simulated;
	protected int steps;
	private static final RandomGenerator rgen = new RandomGenerator();
	protected int breedingSchedule;
	
	public Organism(World world, int x, int y) {
		this.world = world;
		this.x = x;
		this.y = y;
		simulated = true;
		this.steps = 0;
	}

	// Returns the string representation of the organism
	public abstract String toString();


    protected abstract Organism littleBugs(World world, int x, int y);

	public void simulate() {
		// Don't simulate twice in a round
		if (simulated)
			return;
		simulated = true;
		this.steps++;
		this.move();
		this.breed();
		this.starve();
		breedingSchedule++;
		
		// Now move, breed, ....

	}

	// Indicate that the organism hasn't simulated this round
	public void resetSimulation() {
		simulated = false;
	}

	// Move will be moving in a random direction
	protected abstract void move();

	protected void randomMove() {
		int direction = rgen.nextInt(0, 3);
		int newX = this.x;
		int newY = this.y;
		switch (direction) {
		case 0:
			newY = newY - 1; // UP
			break;
		case 1:
			newX = newX + 1; // RIGHT
			break;
		case 2:
			newY = newY + 1; // DOWN
			break;
		case 3:
			newX = newX - 1; // LEFT
			break;
		}

		if (this.world.pointInGrid(newX, newY)) {
			if (this.world.getAt(newX, newY) == null) {
				this.world.setAt(this.x, this.y, null);
				this.world.setAt(newX, newY, this);
				this.x = newX;
				this.y = newY;
			}
		}
	}

	protected void breed()

	{

		int[][] pointsAroundMe = new int[][]{{this.x-1,this.y},{this.x+1,this.y},{this.x,this.y-1},{this.x,this.y+1}};

		for(int[] point:pointsAroundMe)

		{

			if(world.getAt(point[0], point[1]) == null && world.pointInGrid(point[0], point[1]))

			{

				world.setAt(point[0], point[1], littleBugs(world, point[0], point[1]));

				return;

			}

		}

	}

	// Starve will starve the creature if it doesn't eat
	protected abstract void starve();

	{

	}

}