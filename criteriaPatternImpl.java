import java.util.ArrayList;
import java.util.List;

public class criteriaPatternImpl
{
   public static void main(String[] args)
   {
      List<foodItem> foods = new ArrayList<foodItem>();
      
      foodItem apple = new foodItem("apple", foodItem.FoodGroup.fruit, "red");
      foodItem milk = new foodItem("milk", foodItem.FoodGroup.dairy, "white");
      foodItem cracker = new foodItem("cracker", foodItem.FoodGroup.grain, "brown");
      foodItem cucumber = new foodItem("cucumber", foodItem.FoodGroup.vegetable, "green");
      foodItem egg = new foodItem("egg", foodItem.FoodGroup.protein, "white");
      foodItem tuna = new foodItem("tuna", foodItem.FoodGroup.protein, "pink");
      foodItem lemon = new foodItem("lemon", foodItem.FoodGroup.fruit, "yellow");
      foodItem blueCheese = new foodItem("Blue Cheese", foodItem.FoodGroup.dairy, "blue");
      
      foods.add(tuna);
      foods.add(cucumber);
      foods.add(cracker);
      foods.add(milk);
      foods.add(egg);
      foods.add(apple);
      foods.add(lemon);
      foods.add(blueCheese);
      
      System.out.println("Food List:");
      printFoodList(foods);
      System.out.println();
      
      Criteria lactoseFree = new lactoseFree();
      System.out.println("Which of these foods are safe for lactose intolerant individuals?");
      printFoodList(lactoseFree.meetCriteria(foods));
      System.out.println();
      
      Criteria glutenFree = new glutenFree();
      System.out.println("Which of these foods are gluten free?");
      printFoodList(glutenFree.meetCriteria(foods));
      System.out.println();
      
      Criteria primaryCol = new primaryColors();
      System.out.println("Which of these foods are primary colors?");
      printFoodList(primaryCol.meetCriteria(foods));
      System.out.println();
      
      Criteria primaryColAndLactoseFree = new andCriteria(lactoseFree, primaryCol);
      System.out.println("Which foods of primary colors can a lactose intolerant person eat?");
      printFoodList(primaryColAndLactoseFree.meetCriteria(foods));
      System.out.println();
      
      Criteria primaryColOrGlutFree = new orCriteria(glutenFree, primaryCol);
      System.out.println("Which foods are gluten free or are of primary colors?");
      printFoodList(primaryColOrGlutFree.meetCriteria(foods));
      System.out.println();
   }
   
   public static void printFoodList(List<foodItem> foods){
      
      for (foodItem food : foods) {
         System.out.println(food.toString());
      }
   }      
}


class foodItem {
   public enum FoodGroup
   {
      dairy, grain, vegetable, fruit, protein
   }
   
   private String name;
   private FoodGroup foodGroup;
   private String color;
   
   public foodItem(String name, FoodGroup foodGroup, String color)
   {
      this.name = name;
      this.foodGroup = foodGroup;
      this.color = color;
   }
   
   public String getName()
   {
      return name;
   }
   
   public FoodGroup getFoodGroup()
   {
      return foodGroup;
   }
   
   public String getColor()
   {
      return color;
   }
   
   public String toString()
   {
      return "Name: " + name + ", Food Group: " + foodGroup + ", Color: " + color;
   }
}

interface Criteria {
   public List<foodItem> meetCriteria(List<foodItem> foods);
}

class lactoseFree implements Criteria {
   @Override
   public List<foodItem> meetCriteria(List<foodItem> foods) {
      List<foodItem> lactoseFreeFoods = new ArrayList<foodItem>(); 
      
      for (foodItem food : foods) {
         if(food.getFoodGroup() != foodItem.FoodGroup.dairy){
            lactoseFreeFoods.add(food);
         }
      }
      return lactoseFreeFoods;
   }
}

class glutenFree implements Criteria {
   @Override
   public List<foodItem> meetCriteria(List<foodItem> foods) {
      List<foodItem> glutenFreeFoods = new ArrayList<foodItem>(); 
      
      for (foodItem food : foods) {
         if(food.getFoodGroup() != foodItem.FoodGroup.grain){
            glutenFreeFoods.add(food);
         }
      }
      return glutenFreeFoods;
   }
}

class primaryColors implements Criteria {
   @Override
   public List<foodItem> meetCriteria(List<foodItem> foods) {
      List<foodItem> primaryColorFoods = new ArrayList<foodItem>(); 
      
      for (foodItem food : foods) {
         if(food.getColor() == "red" || food.getColor() == "blue" || food.getColor() == "yellow"){
            primaryColorFoods.add(food);
         }
      }
      return primaryColorFoods;
   }
}

class orCriteria implements Criteria {
   private Criteria firstCriteria;
   private Criteria secondCriteria;
   
   public orCriteria(Criteria firstCriteria, Criteria secondCriteria) {
      this.firstCriteria = firstCriteria;
      this.secondCriteria = secondCriteria; 
   }

   @Override
   public List<foodItem> meetCriteria(List<foodItem> foods) {
      List<foodItem> firstCriteriaItems = firstCriteria.meetCriteria(foods);
      List<foodItem> secondCriteriaItems = secondCriteria.meetCriteria(foods);

      for (foodItem food : secondCriteriaItems) {
         if(!firstCriteriaItems.contains(food)){
            firstCriteriaItems.add(food);
         }
      }  
      return firstCriteriaItems;
   }
}

class andCriteria implements Criteria {

   private Criteria firstCriteria;
   private Criteria secondCriteria;

   public andCriteria(Criteria firstCriteria, Criteria secondCriteria) {
      this.firstCriteria = firstCriteria;
      this.secondCriteria = secondCriteria; 
   }

   @Override
   public List<foodItem> meetCriteria(List<foodItem> foods) {
   
      List<foodItem> firstCriteriaPersons = firstCriteria.meetCriteria(foods);     
      return secondCriteria.meetCriteria(firstCriteriaPersons);
   }
}

