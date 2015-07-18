package net.android.xpadro.justjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
        if (quantity == 100) {
            Toast.makeText(this, getString(R.string.msg_upper_limit), Toast.LENGTH_SHORT).show();
            return;
        }
        displayQuantity(++quantity);
        displayPrice(calculatePrice());
    }

    public void decrement(View view) {
        if (quantity == 1) {
            Toast.makeText(this, getString(R.string.msg_lower_limit), Toast.LENGTH_SHORT).show();
            return;
        }
        displayQuantity(--quantity);
        displayPrice(calculatePrice());
    }

    public void submitOrder(View view) {
        sendOrderByMail();
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
        boolean hasWhippedCream = isWhippedCreamSelected();
        boolean hasChocolate = isChocolateSelected();

        EditText nameInput = (EditText) findViewById(R.id.input_name_view);
        String name = nameInput.getText().toString();

        String price = calculatePrice();

        return getString(R.string.order_name, name) + "\n" +
                getString(R.string.order_cream, hasWhippedCream) + "\n" +
                getString(R.string.order_chocolate, hasChocolate) + "\n" +
                getString(R.string.order_quantity, quantity) + "\n" +
                getString(R.string.order_total, price) + "\n" +
                getString(R.string.order_thanks);
    }

    /**
     * Calculates the price of the order.
     */
    private String calculatePrice() {
        int calculatedPrice = price;
        if (isWhippedCreamSelected()) calculatedPrice += 1;
        if (isChocolateSelected()) calculatedPrice += 2;

        return NumberFormat.getCurrencyInstance().format(quantity * calculatedPrice);
    }

    private boolean isWhippedCreamSelected() {
        return ((CheckBox) findViewById(R.id.cream_checkbox)).isChecked();
    }

    private boolean isChocolateSelected() {
        return ((CheckBox) findViewById(R.id.chocolate_checkbox)).isChecked();
    }

    private void sendOrderByMail() {
        String[] TO = {"your_mail@gmail.com"};
        Intent emailIntent = new Intent(Intent.ACTION_SEND);

        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("message/rfc822");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.mail_order));
        emailIntent.putExtra(Intent.EXTRA_TEXT, createOrderSummary());

        if (emailIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(emailIntent);
        }
    }
}