public class DoodleBug extends Organism
{
	private int timeSinceLastMeal;
	private int breedingSchedule;
	
	public DoodleBug(World world, int x, int y)
	{
		super(world, x, y);
		this.timeSinceLastMeal = 0;
		super.breedingSchedule = 0;
	}
	
	// String representation of Doodlebug
	public String toString()
	{
		return "doodlebug";
	}
	
	private boolean isAnt(Organism possibleAnt)
	{
		if (possibleAnt == null)
		{
			return false; // Definitely not an ant if it's nothing
		}
		else if (possibleAnt.toString().equals("doodlebug"))
		{
			return false; // Not an ant if it's a doodlebug
		}
		else
		{
			return true; // Everything else is an ant
		}
	}

	@Override
	protected void move() {
		
		// Start by looking at the ant above us
		Organism possibleAnt = this.world.getAt(this.x, this.y - 1);
		if (!this.isAnt(possibleAnt))
		{
			// No ant there. Maybe to the right?
			possibleAnt = this.world.getAt(this.x + 1, this.y);
			if (!this.isAnt(possibleAnt))
			{
				// No ant to the right. Let's look down.
				possibleAnt = this.world.getAt(this.x, this.y + 1);
				if (!this.isAnt(possibleAnt))
				{
					// Only one way left... Get it?
					possibleAnt = this.world.getAt(this.x - 1, this.y);
				}
			}
		}

		if (this.isAnt(possibleAnt))
		{
			// Eat the ant (move to his spot.)
			this.world.setAt(possibleAnt.x, possibleAnt.y, this);
			
			// We're no longer where we used to be--we're at the new place.
			this.world.setAt(this.x, this.y, null);
			this.x = possibleAnt.x;
			this.y = possibleAnt.y;
			
			// We ate an ant!
			this.timeSinceLastMeal = 0;
			this.breedingSchedule++;
		}
		else
		{
			// Let's just go somewhere.
			this.randomMove();
			this.timeSinceLastMeal++;
			this.breedingSchedule++;
		}
		
	}

//	@Override
//	protected void breed() {

		protected Organism littleBugs(World world, int x, int y)

		{
			if(breedingSchedule == 8)
			{
			return new DoodleBug(world, x, y);
			}
			else
			{
				return null;
			}
		}	

		
	

	@Override
	protected void starve() {
		if (this.timeSinceLastMeal == 3)
		{
			this.world.setAt(this.x, this.y, null);
		}

	}
}