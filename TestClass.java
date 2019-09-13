import java.io.PrintWriter;

public class TestClass {
	
	//DO NOT SUBMIT THIS FILE! ONLY FOR TESTING
	
	public static void main(String[] args) {
		
		SequentialRepresentation<String> sequel = new SequentialRepresentation<String>();
		LinkedRepresentation<String> link = new LinkedRepresentation<String>();
		PrintWriter print = new PrintWriter(System.out, true);
		
		print(" ");
		print(" Sequel Representation ");
		print(" ");
		
		//split node
		sequel.setRootNode("A");
		sequel.splitNode("A", "B", "C");
		sequel.splitNode("B", "D", "E");
		sequel.splitNode("C", "F", null);
		
		//print node
		print("Parent of F: " + sequel.findParent("F"));
		print("Child of B: " + sequel.findChildren("B"));
		print("Parent of D: " + sequel.findParent("D"));
		print("Child of A: " + sequel.findChildren("A"));
		print("Parent of B: " + sequel.findParent("B"));
		print("Child of C: " + sequel.findChildren("C"));
		print("Parent of A: " + sequel.findParent("A"));
		print("Child of E: " + sequel.findChildren("E"));
		
		print("Print pre-order:");
		sequel.printInPreorder(print);
		print("Print post-order:");
		sequel.printInPostorder(print);
		print("Print in-order:");
		sequel.printInInorder(print);
		
		print(" ");
		print(" Linked Representation ");
		print(" ");
		
		//split node
		link.setRootNode("A");
		link.splitNode("A", "B", "C");
		link.splitNode("B", "D", "E");
		link.splitNode("C", "F", null);
		
		//print node
		print("Parent of F: " + link.findParent("F"));
		print("Child of B: " + link.findChildren("B"));
		print("Parent of D: " + link.findParent("D"));
		print("Child of A: " + link.findChildren("A"));
		print("Parent of B: " + link.findParent("B"));
		print("Child of C: " + link.findChildren("C"));
		print("Parent of A: " + link.findParent("A"));
		print("Child of E: " + link.findChildren("E"));
		print("Print pre-order:");
		link.printInPreorder(print);
		print("Print post-order:");
		link.printInPostorder(print);
		print("Print in-order:");
		link.printInInorder(print);
		
	}
	
	public static void print(String m)
	{
		System.out.println(m);
	}

}
