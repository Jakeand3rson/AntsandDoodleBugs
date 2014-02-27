public class Ant extends Organism
{
	private int breedingSchedule;
	public Ant(World world, int x, int y)
	{
		super(world, x, y);
		super.breedingSchedule = 0;
	}
	
	// Returns a string representation of the ant
	public String toString()
	{
		return "ant";
	}

	@Override
	protected void move() {
		this.randomMove();
		breedingSchedule++;
		
	}

	protected Organism littleBugs(World world, int x, int y)
	{
		if(breedingSchedule == 3)
		{
		return new Ant(world, x, y);
		}
		else
		{
			return null;
		}
	
	}	

	@Override
	protected void starve() {
		// Ants don't starve
		
	}


}