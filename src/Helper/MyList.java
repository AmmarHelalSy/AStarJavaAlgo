/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Helper;

import java.util.ArrayList;

/**
 *
 * @author Ammar
 */
class MyList<T>{
     private ArrayList<T> list;

     public MyList(){
         list = new ArrayList<>();
     
     }
     public void add(T t){
         list.add(t); {
         //do other things you want to do when items are added 
                 }}
     public T remove(T t){
          list.remove(t); {
         return t;
     }
     }

}
