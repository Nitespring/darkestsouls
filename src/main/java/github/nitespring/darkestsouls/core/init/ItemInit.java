package github.nitespring.darkestsouls.core.init;

import github.nitespring.darkestsouls.DarkestSouls;

import net.minecraft.world.food.FoodProperties;

import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ItemInit {
	
	public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(DarkestSouls.MODID);
	
	
	
	public static final DeferredItem<Item> EXAMPLE_ITEM = ITEMS.registerSimpleItem("example_item");


    public static final DeferredItem<Item> EXAMPLE_FOOD_ITEM = ITEMS.registerSimpleItem("example_food_item", new Item.Properties().food(new FoodProperties.Builder()
            .alwaysEat().nutrition(1).saturationMod(2f).build()));
    
    
    
 
    

}
