
public class Cat
{
    private static final int EYES_COUNT = 2;
    private static final int MIN_WEIGHT = 1000;
    private static final int MAX_WEIGHT = 9000;

    private Color color;

    private static int count = 0;

    private double originWeight;
    private double weight;

    private String name;
    private boolean hasTail;
    private boolean isAlive;

    private double food = 0;


    public Cat()
    {
        weight = 1500.0 + 3000.0 * Math.random();
        originWeight = weight;
        count = count + 1;
    }

    public Cat (double weight)
    {
        this();
        this.weight = weight;
    }

    public Cat(Cat origin)
    {
        this.weight = origin.getWeight();
        this.name = origin.getName();
        this.color = origin.getColor();
        this.isAlive = origin.getIsAlive();
        this.hasTail = origin.getHasTail();
        count = count + 1;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }

    public void setHasTail(boolean hasTail)
    {
        this.hasTail = hasTail;
    }

    public boolean getHasTail()
    {
        return hasTail;
    }

    public void setIsAlive(boolean isAlive)
    {
        this.isAlive = isAlive;
    }

    public boolean getIsAlive()
    {
        return isAlive;
    }

    public void meow()
    {
        if (weight >= MIN_WEIGHT & weight <= MAX_WEIGHT)
        {
            weight = weight - 1;
            System.out.println("Meow");
            if (weight < MIN_WEIGHT)
            {
                count = count - 1;
            }
        }
        else
        {
            System.out.println("Cat is not alive, it is not meow");
        }
    }

    public void feed(Double amount)
    {
        if (weight >= MIN_WEIGHT & weight <= MAX_WEIGHT)
        {
            weight = weight + amount;
            food = food + amount;
            System.out.println("Feed");
            if (weight > MAX_WEIGHT)
            {
                count = count - 1;
            }
        }
        else
        {
            System.out.println("Cat is not alive, it is not eat");
        }
    }

    public void drink(Double amount)
    {
        if (weight >= MIN_WEIGHT & weight <= MAX_WEIGHT)
        {
            weight = weight + amount;
            System.out.println("Drink");
            if (weight > MAX_WEIGHT)
            {
                count = count - 1;
            }
        }
        else
        {
            System.out.println("Cat is not alive, it is not drink");
        }
    }

    public void amountFood()
    {
        System.out.println("Amount of food: " + food + " gramm.");
    }

    public void pee()
    {
        if (weight >= MIN_WEIGHT & weight <= MAX_WEIGHT)
        {
            weight = weight - 20;
            System.out.println("Pee");
            if (weight < MIN_WEIGHT)
            {
                count = count - 1;
            }
        }
        else
        {
            System.out.println("Cat is not alive, it is not pee");
        }
    }

    public static int getCount()
    {
        return count;
    }

    public void setColor(Color color)
    {
        this.color = color;
    }

    public Color getColor()
    {
        return color;
    }


    public void setWeight(Double weight)
    {
        this.weight = weight;
    }

    public Double getWeight()
    {
        return weight;
    }

    public String getStatus()
    {
        if(weight < MIN_WEIGHT) {
            return "Dead";
        }
        else if(weight > MAX_WEIGHT) {
            return "Exploded";
        }
        else if(weight > originWeight) {
            return "Sleeping";
        }
        else {
            return "Playing";
        }
    }
}