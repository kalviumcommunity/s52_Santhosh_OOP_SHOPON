import java.util.*;

class ShopOn {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Welcome to 'SHOP-ON', a E-commerce stimulation!");
        System.out.println("Please login to continue.");
        System.out.print("Enter Your Name: ");
        
        String userName = in.next();  //creating instance to the product class
        Account user = new Account(userName); //using new keyword to create dynamic memory for objects
        
        System.out.println("Hey " + user.getName() + ", You got RS." + user.getBalance() + " as a login bonus");
        Products products = new Products();
        while(true){
            System.out.println("Your Balance: RS."+user.getBalance());
            System.out.println("[ Press 1 to buy products, Press 2 to sell products, Type 'exit' to Get Out ]");
            System.out.print("Your Input: ");
            String userInput = in.next();
            if(userInput.equals("1")){
                products.displayProducts();
                System.out.println("[ Enter the Item ID to Buy ]");
                System.out.print("Your Input: ");
                int ID = in.nextInt();
                if(!products.checkID(ID-1)){
                    System.out.println("Invalid Input");
                    continue;
                }
                int price = products.getProductPrice(ID-1);
                if(user.reduceBalance(price)){
                    products.buyProduct(ID-1);
                }else{
                    System.out.println("** Insufficient Balance **");
                }
            }else if(userInput.equals("2")){
                System.out.print("Enter Your Item Name: ");
                String itemName = in.next();
                System.out.print("Enter the Item Price: ");
                int itemPrice = in.nextInt();
                user.addBalance(itemPrice);
                products.addProduct(itemName,itemPrice);
            }else if(userInput.equals("exit")){
                break;
            }else{
                System.out.println("Invalid Input");
                continue;
            }
        }
        System.out.println("Come Again, Have a Nice Day!");
    }
}

class Products{
    private class Product {
        private String name;
        private int price;

        Product(String name,int price){
            this.name = name;
            this.price = price;
        }
    }
    private ArrayList<Product> productList;

    public Products() {
        productList = new ArrayList<>();
        productList.add(new Product("T-shirt", 999));
        productList.add(new Product("pant", 240));
        productList.add(new Product("Shoes", 410));
    }
    
    public boolean checkID(int index) {
        return (index >= 0 && index < productList.size());
    }

    public int getProductPrice(int index){
        return productList.get(index).price;
    }
    
    public void addProduct (String name,int price){
        productList.add(new Product(name,price));
        System.out.println("** Item Added Successfully **");
        this.displayProducts();
    }
    
    public void buyProduct(int index){
        productList.remove(index);
         System.out.println("** Purchase Successful **");
        this.displayProducts();
    }
    
    public void displayProducts (){
        int ID = 1;
        System.out.println("----------------------------------");
        System.out.println("ID    NAME    PRICE");
        System.out.println("----------------------------------");
        for(Product item : productList){
            System.out.println(ID+"    "+item.name+"    "+item.price);
            ID++;
        }
        System.out.println("----------------------------------");
    }
}


abstract class User {
    protected String userName;
    
    public User(String userName) {
        this.userName = userName;
    }
    
    public String getName() {
        return this.userName;
    }

    public Boolean reduceBalance(int itemPrice){
        System.out.println("Your balance is subtracted by "+ itemPrice);
    }
    
    public void addBalance(int itemPrice){
        System.out.println("Your balance is added by "+ itemPrice);
    }
}


class Account extends User {
    private int balance; 
    
    public Account(String userName) {
       super(userName);
       this.balance = 1000; 
    }
    
    public int getBalance(){
       return this.balance;
   }
   
   
   public Boolean reduceBalance(int itemPrice){
      if(this.balance >= itemPrice){
          this.balance -= itemPrice;
          return true;
      }else{
          return false;
      }
  }
  
  public void addBalance(int itemPrice){
      this.balance+=itemPrice;
    }
} 

