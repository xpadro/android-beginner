package net.android.xpadro.justjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {
    private int quantity = 2;
    private int price = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void increment(View view) {
        displayQuantity(++quantity);
        displayPrice(calculatePrice());
    }

    public void decrement(View view) {
        displayQuantity(--quantity);
        displayPrice(calculatePrice());
    }

    public void submitOrder(View view) {
        displayPrice(createOrderSummary());
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(
                R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method displays the given price on the screen.
     */
    private void displayPrice(String price) {
        TextView priceTextView = (TextView) findViewById(R.id.order_summary_text_view);
        priceTextView.setText(price);
    }

    private String createOrderSummary() {
        CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.cream_checkbox);
        boolean hasWhippedCream = whippedCreamCheckBox.isChecked();

        CheckBox chocolateCheckBox = (CheckBox) findViewById(R.id.chocolate_checkbox);
        boolean hasChocolate = chocolateCheckBox.isChecked();

        EditText nameInput = (EditText) findViewById(R.id.input_name_view);
        String name = nameInput.getText().toString();

        String price = calculatePrice();

        return "Name: " + name + "\n" +
                "Add whipped cream? " + hasWhippedCream + "\n" +
                "Add Chocolate? " + hasChocolate + "\n" +
                "Quantity: " + quantity + "\n" +
                "Total: " + price + "\n" +
                "Thank you!";
    }

    /**
     * Calculates the price of the order.
     */
    private String calculatePrice() {
        return NumberFormat.getCurrencyInstance().format(quantity * price);
    }
}