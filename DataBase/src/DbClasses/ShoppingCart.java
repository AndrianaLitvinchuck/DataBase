/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DbClasses;



import DbClasses.Product;
import java.time.LocalDate;
import java.util.regex.Pattern;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Collections;
import java.util.stream.Collectors;



/**
 *
 * @author andri
 */
public class ShoppingCart  implements Comparator<Product> {
    private ArrayList<Product> cart= new ArrayList<Product>();
    public String title;
     
      
    public void setTitle(String title) {
        this.title = title;
    }

     public String getTitle() {
        return title;
    }
     
    public int sumOfShoppingCart(){
        int sum = 0;
        for(int i=0; i<cart.size();i++)
        {
            sum+=cart.get(i).getPrice();
        }
        return sum;
    };
    
    public int sumOfShoppingCartStr(){
       
         int sum =  cart.stream().mapToInt(i->(i.getPrice())).sum();
  
       return sum;
        
    }
    
    @Override
    public int compare(Product p1, Product p2) {
		return p1.getTitle().compareTo(p2.getTitle());
	}
    
    
    public void addProduct(Product p1){
              
        cart.add(p1);
           
    }
    
     public boolean addProductByName(String title1){
          ArrayList<Product> temp= new ArrayList<Product>();  
         for(Product i :cart)
         {
         
             if(i.getTitle().equals(title1))
              temp.add(i);
             return true;
         }
        
        return false;
          
    }
    
    
     
     public ArrayList<Product> getCollection()
     {
     return cart;
     }
     
    public ArrayList<Product> theMostExpensiveProduct(){
		
        //hoppingCart tmp = new 
        ArrayList<Product> temp = new ArrayList<Product>();
		
        for(Product i :cart ){
            temp.add(i);
        }
        temp.sort(new ComparatorByPriceDesc());
        ArrayList<Product> res = new ArrayList<Product>();
        for(Product i: temp){
            
            if(i.getPrice()==temp.get(0).getPrice())
            res.add(i);
        }
          
               return res;  
    }
    
    public void outputGroup(){
        for (Product s: cart) {
            System.out.println(s.toString());
            System.out.println();
        }
    }
     public static void main(String... strings) {
         Product p1, p2, p3, p4, p5, p6;
          p1=new Product.Builder().setTitle("Butter").setType("Dairy").setPrice(35).
                setDateOfProduction(2017, 10, 5).
                setShiftTime(2017, 11,20).createProduct();
        p2=new Product.Builder().setTitle("Pork").setType("Meat").setPrice(100).
                setDateOfProduction(2017, 10, 25).
                setShiftTime(2017, 11,10).createProduct();
        p3=new Product.Builder().setTitle("Chocolate").setType("Sweets").setPrice(30).
                setDateOfProduction(2017, 9, 15).
                setShiftTime(2018, 2, 15).createProduct();
        p4=new Product.Builder().setTitle("Chocolate").setType("Sweets").setPrice(30).
                setDateOfProduction(2017, 9, 15).
                setShiftTime(2018, 2, 15).createProduct();
        p5=new Product.Builder().setTitle("Tomatoes").setType("Vegetables").setPrice(40).
                setDateOfProduction(2017, 11, 2).
                setShiftTime(2018, 11, 15).createProduct();
        p6=new Product.Builder().setTitle("Branbread").setType("Baked").setPrice(10).
                setDateOfProduction(2017, 11, 3).
                setShiftTime(2018, 11, 5).createProduct();
        ShoppingCart cart1 = new ShoppingCart();
         cart1.addProduct(p1);
        cart1.addProduct(p2);
        cart1.addProduct(p3);
        cart1.addProduct(p4); 
        cart1.addProduct(p5);
        cart1.addProduct(p6);
        cart1.outputGroup();
        
        System.out.println(cart1.sumOfShoppingCartStr()); 
       // ShoppingCart cart2 = new ShoppingCart();
         
         
         //cart2.getCollection() = cart1.theMostExpensiveProduct(3);
       
            //System.out.println(b);
        
		
      }
    
}


class ComparatorByPriceDesc implements Comparator<Product>{

    @Override
    public int compare(Product p1,  Product p2){
        if (p1.getPrice() > p2.getPrice())
            return -1;
        else if (p1.getPrice() < p2.getPrice())
            return 1;
        else
            return (p1.getTitle().compareTo(p2.getTitle()));
}
}