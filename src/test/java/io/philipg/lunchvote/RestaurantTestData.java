package io.philipg.lunchvote;

import io.philipg.lunchvote.model.Restaurant;
import io.philipg.lunchvote.model.State;
import io.philipg.lunchvote.model.User;
import org.springframework.test.web.servlet.ResultMatcher;

import java.util.Arrays;
import java.util.List;

import static io.philipg.lunchvote.model.AbstractBaseEntity.START_SEQ;
import static io.philipg.lunchvote.web.json.JsonUtil.writeIgnoreProps;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

public class RestaurantTestData {

    public static final int RESTAURANT1_ID = START_SEQ + 2;
    public static final int RESTAURANT3_ID = START_SEQ + 4;

    public static final Restaurant RESTAURANT1 = new Restaurant(RESTAURANT1_ID, "Corsar", "Rehov Onyon 1, Ashdod, Israel", "+972 8-855-5090", "https://corsar.com", State.STATE_ACTIVE);
    public static final Restaurant RESTAURANT2 = new Restaurant(RESTAURANT1_ID + 1, "Kira", "Givat Yona 4, Yair Stern, Ashdod 7722611, Israel", "+972 8-856-1632", "https://kira.com", State.STATE_DISABLED);
    public static final Restaurant RESTAURANT3 = new Restaurant(RESTAURANT1_ID + 2, "Pinta", "1 Ha Ha-Banaim, Ashdod 7760901, Israel", "+972 8-856-6069", "https://pinta.com", State.STATE_ACTIVE);


    public static Restaurant getCreated() {
        return new Restaurant(null, "new restaurant", "address", "+123 123-123-1234", "https://test.com", State.STATE_ACTIVE);
    }

    public static Restaurant getUpdated() {
        return new Restaurant(RESTAURANT1_ID, "update restaurant", "address", "+123 123-123-1234", "https://test.com", State.STATE_ACTIVE);
    }
    public static Restaurant getRemoved() {
        return new Restaurant(RESTAURANT1_ID, "Corsar", "Rehov Onyon 1, Ashdod, Israel", "+972 8-855-5090", "https://corsar.com", State.STATE_REMOVED);
    }

    public static final List<Restaurant> RESTAURANTS_ALL_ACTIVE = Arrays.asList(RESTAURANT1, RESTAURANT3);



    public static void assertMatch(Restaurant actual, Restaurant expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "dishes");
    }

    public static void assertMatch(Iterable<Restaurant> actual, Restaurant... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Restaurant> actual, Iterable<Restaurant> expected){
        assertThat(actual).usingElementComparatorIgnoringFields("dishes").isEqualTo(expected);
    }

    public static ResultMatcher contentJson(Restaurant... expected) {
        return content().json(writeIgnoreProps(Arrays.asList(expected), "dishes"));
    }

    public static ResultMatcher contentJson(Restaurant expected) {
        return content().json(writeIgnoreProps(expected, "dishes"));
    }
}