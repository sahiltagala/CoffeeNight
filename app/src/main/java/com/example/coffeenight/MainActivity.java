package com.example.coffeenight;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int quantity = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void increment(View view) {
        if(quantity == 30){
            Toast.makeText(this, "Quantity can't be more than 30 !", Toast.LENGTH_SHORT).show();
            return;
        }
        quantity++;
        displayQuantity(quantity);
    }

    public void decrement(View view) {
        if(quantity == 0){
            Toast.makeText(this, "Quantity can't be less than 1 !", Toast.LENGTH_SHORT).show();
            return;
        }
        quantity--;
        displayQuantity(quantity);
    }

    public void submitOrder(View view) {

        EditText nameField = findViewById(R.id.name);
        String name = nameField.getText().toString();

        CheckBox cb1 = findViewById(R.id.cb1);
        boolean b1 = cb1.isChecked();

        CheckBox cb2 = findViewById(R.id.cb2);
        boolean b2 = cb2.isChecked();

        int price = calculatePrice(b1, b2);

        String message = createSummary(name, price, b1, b2);

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.sub, name));
        intent.putExtra(Intent.EXTRA_TEXT, message);

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }


    }

    private int calculatePrice(boolean a, boolean b){
        int basePrice = 5;

        if(a){
            basePrice += 1;
        }
        if(b){
            basePrice += 2;
        }
        return quantity * basePrice;
    }

    private String createSummary(String name, int price, boolean b1, boolean b2){

        String priceMessage = "Name: " + name;
        priceMessage += "\nWhipped Cream? " + b1;
        priceMessage += "\nChocolate? " + b2;
        priceMessage += "\nCup of Coffee\'s: " + quantity;
        priceMessage += "\nTotal amount $" + price;
        priceMessage += "\nThank you :)";

        return priceMessage;
    }

    @SuppressLint("SetTextI18n")
    private void displayQuantity(int n){
        TextView textView = findViewById(R.id.t1);
        textView.setText("" + n);
    }

    public void exit(View view) {
        finish();
        System.exit(0);
    }
}
