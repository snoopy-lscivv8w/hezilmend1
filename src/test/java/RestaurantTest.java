import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RestaurantTest {
    Restaurant restaurant;

    List<Item> selectedItem = new ArrayList<>();
    //REFACTOR ALL THE REPEATED LINES OF CODE
    public void defaultRestaurantDetails() {
        LocalTime openingTime = LocalTime.parse("10:00:00");
        LocalTime closingTime = LocalTime.parse("21:00:00");
        restaurant = new Restaurant("Amelie's cafe", "Chennai", openingTime, closingTime);
        restaurant.addToMenu("Sweet corn soup", 100);
        restaurant.addToMenu("Vegetable lasagne", 200);
        restaurant.addToMenu("Non-Veg lasagne", 300);

    }

    //>>>>>>>>>>>>>>>>>>>>>>>>>OPEN/CLOSED<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    //-------FOR THE 2 TESTS BELOW, YOU MAY USE THE CONCEPT OF MOCKING, IF YOU RUN INTO ANY TROUBLE
    @Test
    public void is_restaurant_open_should_return_true_if_time_is_between_opening_and_closing_time(){
        //WRITE UNIT TEST CASE HERE
        defaultRestaurantDetails();
        restaurant.setOpeningTime(LocalTime.parse("07:00:00"));
        restaurant.setClosingTime(LocalTime.parse("21:00:00"));

        assertEquals(true, restaurant.isRestaurantOpen());

    }

    @Test
    public void is_restaurant_open_should_return_false_if_time_is_outside_opening_and_closing_time(){
        //WRITE UNIT TEST CASE HERE
        defaultRestaurantDetails();
        restaurant.setOpeningTime(LocalTime.parse("07:30:00"));
        restaurant.setClosingTime(LocalTime.parse("12:00:00"));
        assertEquals(false, restaurant.isRestaurantOpen());

    }

    //<<<<<<<<<<<<<<<<<<<<<<<<<OPEN/CLOSED>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


    //>>>>>>>>>>>>>>>>>>>>>>>>>>>MENU<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void adding_item_to_menu_should_increase_menu_size_by_1(){
        defaultRestaurantDetails();
        int initialMenuSize = restaurant.getMenu().size();
        restaurant.addToMenu("Sizzling brownie",319);
        assertEquals(initialMenuSize+1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_from_menu_should_decrease_menu_size_by_1() throws itemNotFoundException {
        defaultRestaurantDetails();
        int initialMenuSize = restaurant.getMenu().size();
        restaurant.removeFromMenu("Vegetable lasagne");
        assertEquals(initialMenuSize-1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_that_does_not_exist_should_throw_exception() {
       defaultRestaurantDetails();
        assertThrows(itemNotFoundException.class,
                ()->restaurant.removeFromMenu("French fries"));
    }
    //<<<<<<<<<<<<<<<<<<<<<<<MENU>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

    @Test
    public void total_order_value_returned_when_item_is_present_in_menu() {
        defaultRestaurantDetails();

        selectedItem = restaurant.getMenu();
        assertEquals(600,restaurant.getOrderValue(selectedItem));

    }

}