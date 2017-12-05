
package DbClasses;

import java.time.LocalDate;
import java.util.regex.Pattern;


  
public class Product{
    private String title;
    private String type;
    private LocalDate dateOfProduction;
    private int price;
    private LocalDate shiftTime;
    
  
    
    public Product(Builder builder) {
        title = builder.title;
        type = builder.type;
        price = builder.price;
        dateOfProduction = builder.dateOfProduction;
        shiftTime = builder.shiftTime;
    }
  
    public static void main(String... strings) {
        Product p;
        p =new Product.Builder().setTitle("Butter").setType("Dairy").setPrice(35).
                setDateOfProduction(2017, 10, 5).
                setShiftTime(2017, 11,20).createProduct();
		
	}
    //get
    public String getTitle()
    { 
        return title;
    }

     public String getType()
    { 
        return type;
    }
     
    public LocalDate getDateOfProduction()
    { 
        return dateOfProduction;
    }

    public int getPrice()
    { 
        return price;
    }
    public LocalDate getShiftTime()
    { 
        return shiftTime;
    }
    
    
      //set
    public Product setTitle(String title)
    { 
        this.title=title;
        return this;
    }
    public Product setType(String type)
    { 
        this.type=type;
        return this;
    }
    public Product setDateOfProduction(int year, int month, int day)
    { 
        this.dateOfProduction= LocalDate.of(year, month, day);
        return this;
    }
    
    public Product setDateOfProduction(LocalDate date){
      
        dateOfProduction=date;
          return this;
        
    }
            
    public Product setShiftTime(int year, int month, int day)
    { 
        this.shiftTime = LocalDate.of(year, month, day);
        return this;
    }
    public Product setPrice(int price)
    { 
        this.price=price;
        return this;
    }
    public boolean suitableProduct()
    {
       
        return LocalDate.now().compareTo(shiftTime)<0;
               
        
    }
      @Override
    public boolean equals (Object obj){
//TODO verify if obj isProduct
       // if(obj.getClass()==Product);
        return  (title == ((Product) obj).title &&
                type == ((Product) obj).type &&
                price == ((Product) obj).price);
    }
    @Override
    public String toString(){
        return "\nTitle: " + title + "\nType: " + type +
                "\nPrice: " + price + "\nDateOfProduction: " + dateOfProduction +
                "\nShiftTime: " + shiftTime+ "\n";
    }
  
   
   public static class Builder {
        private static final String PATTERN = "^[A-Z][a-z]+";
        private String title;
        private String type;
        private  int price;
        private LocalDate dateOfProduction;
        private LocalDate shiftTime;

        public Builder setTitle(String title){
            Pattern p1=Pattern.compile(PATTERN);
            if(p1.matcher(title).matches()) this.title=title;
            else
                throw new IllegalArgumentException("Illegal Title");
            return this;
        }

        public Builder setType(String type){
            Pattern p1=Pattern.compile(PATTERN);
            if(p1.matcher(type).matches()) this.type=type;
            else
                throw new IllegalArgumentException("Illegal Type");
            return this;
        }
         
       public Builder setPrice(int price)
       {
           if((price>0) &&(price<1000))
               this.price=price;
           else
               throw new IllegalArgumentException("Illegal price");
            return this;
                       
       }
       

        public Builder setDateOfProduction(int year, int month, int day){
            if ((year > LocalDate.now().getYear()-2  ))
                this.dateOfProduction = LocalDate.of(year, month, day);
            else
                throw new IllegalArgumentException("Illegal Date of Production");
            return this;
        }

         public Builder setShiftTime(int year, int month, int day){
             LocalDate temp;
             temp=LocalDate.of(year, month, day);
            if ((temp.compareTo(this.dateOfProduction )>0 ) )
                this.shiftTime = LocalDate.of(year, month, day);
            else
                throw new IllegalArgumentException("Illegal Date of Shift Time");
            return this;
        }

        
        public Product createProduct() {
            return new Product(this);
        }

    }
}








