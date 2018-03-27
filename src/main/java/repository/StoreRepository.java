package repository;

import java.io.*;
import java.util.ArrayList;

import model.Product;

public class StoreRepository {
	private ArrayList<Product> allProducts;
	private String filename;
	
	public ArrayList<Product> getAllProducts() {
		return allProducts;
	}

	public StoreRepository(String filename) {
		allProducts = new ArrayList<Product>();
		this.filename = filename;
	}

	public void readFile() throws NumberFormatException, IOException{
		FileInputStream f=new FileInputStream(filename);
		DataInputStream in = new DataInputStream(f);
		BufferedReader buf =new BufferedReader(new InputStreamReader(in));
		String rd;
		String []line;
		while((rd=buf.readLine())!=null){
				line=rd.split(" ");
				this.allProducts.add(new Product(Integer.parseInt(line[0]),line[1],line[2],Integer.parseInt(line[3])));
		}
		in.close();
	}
	public String addNewProduct(Product p) throws IOException{

		if(p.getCode()>0 && p.getQuantity()>=0 && p.getCode()<=Integer.MAX_VALUE && p.getQuantity()<=Integer.MAX_VALUE&& !illegal(p.getName())){
			BufferedWriter out = new BufferedWriter(new FileWriter(filename,true));
			int k=1;
			for(Product i:allProducts){
				if(i.getCode()==p.getCode()){
					k=0;
				}
			}
			if(k==1){
				out.newLine();
				out.write(p.getCode()+" "+p.getName()+" "+p.getCategory()+" "+p.getQuantity());
				out.close();
				allProducts.add(p);
			}
			else{
				out.close();
				return("This code already exists");
			}
		}
		else{
			return("code q");
		}
		return("success");
	}

	private boolean illegal(String name) {
	    if(name.isEmpty())
	        return true;
		char c;
		for(int i=0;i<name.length();++i) {
			c = name.charAt(i);
			if (!((c <= 'z' && c >= 'a') || (c <= 'Z' && c >= 'A')|| (c <= '9' && c >= '1')))
				return true;
		}
		return false;
	}

	public ArrayList<Product> getProductsCategory(String cat){
		ArrayList<Product> cProducts=new ArrayList<Product>();
		for(Product p:allProducts){
			if(p.getCategory().compareTo(cat)==0){
				cProducts.add(p);
			}
		}
		return cProducts;
	}
	
	public ArrayList<Product> stockSituationProduct(String pname){
		ArrayList<Product> prods=new ArrayList<Product>();
		for(Product p:allProducts)
			if(p.getName().compareTo(pname)==0)
				prods.add(p);
		return prods;
	}
	public ArrayList<Product> stockSituation() {
		return allProducts;
	}

	public void clearFile() throws FileNotFoundException {
		PrintWriter pw = new PrintWriter(filename);
		pw.write("7 test test 0");
		pw.close();
	}
	
}
