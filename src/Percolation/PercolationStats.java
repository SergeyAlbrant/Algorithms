package Percolation;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
	
	private double[] experiments;
	
   public PercolationStats(int n, int trials) {   // perform trials independent experiments on an n-by-n grid
	   
	   if (n<=0 || trials<=0) {
		   throw new IllegalArgumentException("n and trials must be more than 0");
	   }
	   
	   experiments = new double[trials];
	   
	   for (int i = 0; i < trials; i++) {
			Percolation perc = new Percolation(n);
			
			while (!perc.percolates()) {
				int x = StdRandom.uniform(n) + 1;
				int y = StdRandom.uniform(n) + 1;
				if (!perc.isOpen(x, y)) {
					perc.open(x, y);
				}
			} 
			experiments[i]=(double) perc.numberOfOpenSites()/(n *n);
	   }

   }
   
   public double mean()  {                        // sample mean of percolation threshold
	   return StdStats.mean(experiments);
   }
   
   public double stddev()  {                      // sample standard deviation of percolation threshold
	   return StdStats.stddev(experiments);
   }
   
   public double confidenceLo()  {                // low  endpoint of 95% confidence interval
	   return mean()-(1.96*stddev()/Math.sqrt(experiments.length));
   }
   
   public double confidenceHi()   {               // high endpoint of 95% confidence interval
	   return mean()+(1.96*stddev()/Math.sqrt(experiments.length));
   }

   public static void main(String[] args)    {    // test client (described below)
	   PercolationStats stats = new PercolationStats(200, 1000);
	   System.out.println("mean   = "+ stats.mean());
	   System.out.println("stddev = "+ stats.stddev());
	   System.out.println("95% confidence interval = ["+ stats.confidenceLo() + ", " + stats.confidenceHi() +"]");
   }
}