import java.util.*;

class Main {
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

class Display{
    //Single Responsibility principle
    public void display (int count, ArrayList<Product> productList){
        int ID = 1;
        System.out.println("Total number of products available: "+count);
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

class Product {
    //Single Responsibility principle
    public String name;
    public int price;
    
    //constructor
    Product(String name,int price){
        this.name = name;
        this.price = price;

    }
}
class Products extends Display{
    //Single Responsibility principle
    // Open/Closed Principle (OCP)

    private ArrayList<Product> productList;

    private static int counter = 0;
    private static final int limit = 5;

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
        if(counter < limit){
            productList.add(new Product(name,price));
            counter++;
            System.out.println("** Item Added Successfully **");
            this.displayProducts();
        }else{
            System.out.println("Limit Reached!");
        }
    }
    
    public void buyProduct(int index){
        productList.remove(index);
         System.out.println("** Purchase Successful **");
        this.displayProducts();
    }

    public void displayProducts (){
        display(getCount(),productList);
    }


    public static int getCount(){
         return counter;
    }
}

abstract class User {
    // Abstraction
    // Open/Closed Principle (OCP)
    protected String userName;

    //constructor
    public User(String userName) {
        this.userName = userName;
    }
    
    public String getName() {
        return this.userName;
    }

    public Boolean reduceBalance(int itemPrice){
        System.out.println("Your balance is subtracted by "+ itemPrice);
        return true;
    }
    
    public void addBalance(int itemPrice){
        System.out.println("Your balance is added by "+ itemPrice);
    }
}

class Account extends User {
    //Single Responsibility principle

    private int balance; 

    //constructor
    public Account(String userName) {
       super(userName);
       this.balance = 1000; //Setter
    }
    
    public int getBalance(){ //Getter
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