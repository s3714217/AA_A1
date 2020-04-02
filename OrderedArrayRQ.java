import java.io.PrintWriter;
import java.lang.String;
import java.util.Arrays;


/**
 * Implementation of the Runqueue interface using an Ordered Array.
 *
 * Your task is to complete the implementation of this class.
 * You may add methods and attributes, but ensure your modified class compiles and runs.
 *
 * @author Sajal Halder, Minyi Li, Jeffrey Chan
 */
public class OrderedArrayRQ implements Runqueue {

    
	private int size;
	private String procArray[];
	private int procVT[];
	private final int INI_SIZE = 1;
	private boolean sorted = false;
	
    public OrderedArrayRQ() {
        size = INI_SIZE;
        procArray = new String[size];
    	procVT = new int[size];
    }  // end of OrderedArrayRQ()

    @Override
    public void enqueue(String procLabel, int vt) {
        // Implement me
    	
   		//Assign the proc and its vt
    	this.procArray[this.size-this.INI_SIZE] = procLabel;
    	this.procVT[this.size-this.INI_SIZE] = vt;	
    	this.size++;

    	//Expanding the array    		
   		this.procVT = Arrays.copyOf(this.procVT, this.size);
    	this.procArray = Arrays.copyOf(this.procArray, this.size);
    	this.sorted = false;
    	
    	
    
    } // end of enqueue()

    @Override
    public String dequeue() {
    	
        String temp = null;
        if(!sorted)
    	{this.Sorting();}
    	for(int x = 0; x < size; x++)
    	{
    		if(this.procArray[x] != null)
    		{
    			temp = procArray[x];
    			this.removeProcess(procArray[x]);break;
    		}
    	}
    	
    	return temp;
    	// placeholder,modify this
    } // end of dequeue()

    @Override
    public boolean findProcess(String procLabel){
        
    	String Proc = null;
    	for(String x: this.procArray)
    	{
    		if(x!= null && x.equals(procLabel))
    		{
    			Proc = x;
    		}
    	}
    	
    	if(Proc != null)
    	{
    		return true;
    	}
    	else
    	{
    		return false;
    	}
        
    } // end of findProcess()

    @Override
    public boolean removeProcess(String procLabel) {
    	
    	boolean removed = false;
    	for(int x= 0; x < this.size; x++)
    	{
    		if(this.procArray[x] != null && this.procArray[x].equals(procLabel))
    		{
    			this.procArray[x] = null;
    			this.procVT[x] = 0;
    			removed = true;break;
    		}
    	}
    	
    	return removed;

        // placeholder, modify this
    } // end of removeProcess()

    @Override
    public int precedingProcessTime(String procLabel) {
        
    	 int totalPt = -1;
         
         if(!sorted)
         {this.Sorting();}
         if(this.findProcess(procLabel))
     	{
         for(int x = 0; x< this.size;x++)
         {
        	
        		if(procLabel.equals(this.procArray[x]))
        		{	
        			totalPt++;
        			break;
        		}
        		System.out.println(this.procVT[x]);
        		totalPt += this.procVT[x]; 
        	}
       	  
       	  }
    	
       	  
         
       	//System.out.println(totalPT);
           return totalPt;
    }// end of precedingProcessTime()

    @Override
    public int succeedingProcessTime(String procLabel) {
      int totalPt = -1;
      
      if(!sorted)
      {this.Sorting();}
      
      for(int x = 0; x< this.size;x++)
      {
    	  if(procLabel.equals(this.procArray[x])
    	   && x+1 < this.size)
    	  {		totalPt++;
    		  for (int y = x+1; y<this.size; y++)
    		  {
    			  totalPt += this.procVT[y];
    		  }
    		  break;
    	  }
      }
      
      
    	//System.out.println(totalPT);
        return totalPt;
    	
    } // end of precedingProcessTime()

    @Override
    public void printAllProcesses(PrintWriter os) {
    	
    	String AllProc = "";
    	this.Sorting();
    	
    	for (int x = 0; x < this.size; x++)
    	{
    		if (this.procArray[x] != null)
    		{
    		 AllProc += this.procArray[x] + " ";
    		}
    	}
    	
    	os.println(AllProc);
    	
    	
    }// end of printAllProcesses()
    
    private void Sorting()
    {//bubble sorting 
    	
    	int checkingPos = 0;
    	int sortedProc = 0;
    	
    	while(sortedProc <= this.size)
    	{
    		if(checkingPos == this.size -2)
    		{
    			sortedProc++;
    			checkingPos = 0;
    		}
    		if(this.procVT[checkingPos] > this.procVT[checkingPos+1])
    		{
    			int temp;
    			String tempS;
    			
    			temp = this.procVT[checkingPos];
    			tempS = this.procArray[checkingPos];
    			
    			this.procVT[checkingPos] = this.procVT[checkingPos+1] ;
    			this.procArray[checkingPos] = this.procArray[checkingPos+1];
    			
    			this.procVT[checkingPos+1] = temp;
    			this.procArray[checkingPos+1] = tempS;
    			
    			
    		}
    		checkingPos++;
    			
    	}
    	this.sorted = true;
    }

} // end of class OrderedArrayRQ


