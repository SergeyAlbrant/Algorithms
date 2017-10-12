package Percolation;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
	
	private WeightedQuickUnionUF id;
	private int gridSize;
	private boolean[][] isOpen; 
	private int counter;
	private int virtualTop;
	private int virtualBottom;

   public Percolation(int n){                // create n-by-n grid, with all sites blocked
	   if (n<=0) {
		   throw new IllegalArgumentException("n must be more than 0");
	   }
	   gridSize = n;
	   isOpen = new boolean[n][n];
	   id =  new WeightedQuickUnionUF(n*n+2);
	   
       virtualBottom = n * n + 1;
   }
   
   public    void open(int row, int col){    // open site (row, col) if it is not open already
	   //check if connected, then union
	   checkIndices(row,col);
	   
	   int currentSite = xyTo1D(row,col);
	   //System.out.println(isOpen[1]);
	   isOpen[row-1][col-1]=true;
	   counter++;
	   
	   if (row == 1) {
		   id.union(currentSite, virtualTop);
	   }
	   if (row == gridSize) {
		   id.union(currentSite, virtualBottom);
	   }
	   
	   if (col>1 && isOpen(row,col-1)) {
		   id.union(currentSite, xyTo1D(row,col-1));
	   }
	   
	   if (col<gridSize && isOpen(row,col+1)) {
		   id.union(currentSite, xyTo1D(row,col+1));
	   }
	   
	   if (row>1 && isOpen(row-1,col)) {
		   id.union(currentSite, xyTo1D(row-1,col));
	   }
	   
	   if (row<gridSize && isOpen(row+1,col)) {
		   id.union(currentSite, xyTo1D(row+1,col));
	   }
	   
   }
   
   
   
   private void checkIndices(int row, int col) {
	   if (row<1 || row>gridSize || col<1 || col>gridSize){
		   throw new IllegalArgumentException("indices must be in the range from 1 to n");
	   }
	
   }

   
   

   public boolean isOpen(int row, int col){  // is site (row, col) open?
		checkIndices(row,col);
		return isOpen[row-1][col-1];
   }
   
   public boolean isFull(int row, int col){  // is site (row, col) full?
	   checkIndices(row,col);
	   return id.connected(virtualTop, xyTo1D(row,col));
   }
   
   
   public     int numberOfOpenSites(){       // number of open sites
	   return counter;

   }
   
   public boolean percolates(){              // does the system percolate?
	   return id.connected(virtualTop, virtualBottom);
   }
   
   private int xyTo1D(int x, int y){
	   return gridSize*(x-1)+y;
   }

   public static void main(String[] args){   // test client (optional)
	   //Percolation a = new Percolation(3);
	   //a.open(1, 1);
	   //a.open(1, 2);
	   
   }
}