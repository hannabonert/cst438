import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class criteriaPatternImplTest
{
   List<foodItem> foods = new ArrayList<foodItem>();
   List<foodItem> glutenFreeList = new ArrayList<foodItem>();
   List<foodItem> lactoseFreeList = new ArrayList<foodItem>();
   
   foodItem pear = new foodItem("pear", foodItem.FoodGroup.fruit, "green");
   foodItem oatmeal = new foodItem("oatmeal", foodItem.FoodGroup.grain, "white");
   foodItem squash = new foodItem("squash", foodItem.FoodGroup.vegetable, "yellow");
   foodItem cheese = new foodItem("cheese", foodItem.FoodGroup.dairy, "white");

   
   @Test
   public void testGlutenFreeMeetCrit()
   {
      foods.add(pear);
      foods.add(oatmeal);
      foods.add(squash);
      foods.add(cheese);
      
      glutenFreeList.add(pear);
      glutenFreeList.add(squash);
      glutenFreeList.add(cheese);
      
      List<foodItem> glutenFreeFoods = new ArrayList<foodItem>();
      
      Criteria glutenFree = new glutenFree();
      glutenFreeFoods = glutenFree.meetCriteria(foods);
            
      assertEquals(glutenFreeList, glutenFreeFoods);
   }
   
   @Test
   public void testLactoseFreeMeetCrit()
   {
      foods.add(pear);
      foods.add(oatmeal);
      foods.add(squash);
      foods.add(cheese);
      
      lactoseFreeList.add(pear);
      lactoseFreeList.add(oatmeal);
      lactoseFreeList.add(squash);
      
      List<foodItem> lactoseFreeFoods = new ArrayList<foodItem>();
      
      Criteria lactoseFree = new lactoseFree();
      lactoseFreeFoods = lactoseFree.meetCriteria(foods);
            
      assertEquals(lactoseFreeList, lactoseFreeFoods);
   }

}
