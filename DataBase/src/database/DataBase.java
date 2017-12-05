/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import DbClasses.ShopDB;
import DbClasses.Product;
import DbClasses.ShoppingCart;

/**
 *
 * @author andri
 */
public class DataBase {

    
    public static void main(String[] args) {
         Product p1, p2, p3, p4, p5, p6;
        p1 = new Product.Builder().setTitle("Butter").setType("Dairy").setPrice(100).
                setDateOfProduction(2017, 10, 5).
                setShiftTime(2017, 11,20).createProduct();
        p2 = new Product.Builder().setTitle("Pork").setType("Meat").setPrice(100).
                setDateOfProduction(2017, 10, 25).
                setShiftTime(2017, 11,12).createProduct();
        
        p3 = new Product.Builder().setTitle("Chocolate").setType("Sweets").setPrice(30).
                setDateOfProduction(2017, 9, 15).
                setShiftTime(2018, 2, 15).createProduct();
        p4 = new Product.Builder().setTitle("Tomatoes").setType("Vegetables").setPrice(40).
                setDateOfProduction(2017, 11, 2).
                setShiftTime(2018, 11, 15).createProduct();
        p5 = new Product.Builder().setTitle("Branbread").setType("Baked").setPrice(10).
                setDateOfProduction(2017, 11, 3).
                setShiftTime(2018, 11, 5).createProduct();
        p6 = new Product.Builder().setTitle("Cream").setType("Dairy").setPrice(12).
                setDateOfProduction(2017, 11, 30).
                setShiftTime(2018, 05, 5).createProduct();
        
       ShoppingCart cart1 = new ShoppingCart();
           cart1.setTitle("cart1");
       ShoppingCart cart2 = new ShoppingCart();
           cart2.setTitle("cart2");
        ShoppingCart cart3 = new ShoppingCart();
           cart3.setTitle("cart3");   
        ShopDB.initialize();
        
        ShopDB.createCartsTable();
        ShopDB.createProductTable();
        /*
       ShopDB.addCart(cart1);
       ShopDB.addProduct(p1, 1);
       ShopDB.addProduct(p1, 1);
       ShopDB.addProduct(p2, 1);
       ShopDB.addProduct(p3, 1);
       
       ShopDB.addCart(cart2);
        ShopDB.addProduct(p4, 2);
        ShopDB.addProduct(p5, 2);
        ShopDB.addProduct(p6, 2);*/
       
     // ShopDB.addCart(cart3);
     // ShopDB.deleteCartById(4);
       int s = ShopDB.getCountOfSameProduct(p1, cart1);
       
        System.out.println(s);
       // ShopDB.deleteProductById(8);
       //ShopDB.deleteCartById(1);
       /*
       ShopDB.deleteProductById(1);*/
        
        /*ShopDB.dropProduct();
        ShopDB.dropCarts();*/
        
       /*
        
       */
        
        
        
       // ShopDB.outputCart();
      //  int s = ShopDB.getCountOfProduct(cart1);
     /* int s;
       // s = ShopDB.getSumOfCart(cart2);
       s = ShopDB.getCountOfProduct(cart1);
        System.out.println(s);*/
        
        
              
        
        
    }
    
}
