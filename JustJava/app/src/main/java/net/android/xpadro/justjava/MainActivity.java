package net.android.xpadro.justjava;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.TextView;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends ActionBarActivity {
    private int quantity = 2;
    private int price = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        displayPrice();
        updateOrderMessage("Thank you!");
    }

    public void increment(View view) {
        display(++quantity);
        displayPrice();
    }

    public void decrement(View view) {
        display(--quantity);
        displayPrice();
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(
                R.id.quantity_text_view);
        quantityTextView.setText("" + number);
        updateOrderMessage("");
    }

    /**
     * This method displays the given price on the screen.
     */
    private void displayPrice() {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(quantity * price));
    }

    private void updateOrderMessage(String message) {
        TextView priceTextView = (TextView) findViewById(R.id.order_text_view);
        priceTextView.setText(message);
    }
}