package ru.kkuzmichev.simpleappforespresso;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.matcher.BoundedMatcher;

import org.hamcrest.Description;
import org.hamcrest.Matcher;

public class CustomViewMatcher {
    public static Matcher<View> recyclerViewSizeMatcher(final int matcherSize) {
        return new BoundedMatcher<View, RecyclerView>(RecyclerView.class) {

            String result;

            @Override
            public void describeTo(Description description) {
                description.appendText("Item count: " + matcherSize);
                description.appendText("\nGot: ");
                description.appendText("result");
            }

            @Override
            protected boolean matchesSafely(RecyclerView recyclerView) {
                int items = recyclerView.getAdapter().getItemCount();
                result = "List size: " + items;
                return matcherSize == items;
            }
        };
    }
}
