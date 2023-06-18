package com.example.pricecalculator;




import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private LinearLayout itemLayout;
    private Button addItemButton;
    private Button calculateButton;
    private TextView totalPriceTextView;
    private int itemCount = 1;
    private List<Item> itemList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity2_main);

        itemLayout = findViewById(R.id.itemLayout);
        addItemButton = findViewById(R.id.addItemButton);
        calculateButton = findViewById(R.id.calculateButton);
        totalPriceTextView = findViewById(R.id.totalPriceTextView);

        // Initialize the item list with items and their values
        itemList.add(new Item("Upma", 30));
        itemList.add(new Item("Sheera", 40));
        itemList.add(new Item("Kanda Poha", 30));
        itemList.add(new Item("Misal Pav/Usal Pav", 40));
        itemList.add(new Item("Idli-Sambar", 40));
        itemList.add(new Item("Vada Sambar", 40));
        itemList.add(new Item("Bread Butter/Jam", 20));
        itemList.add(new Item("Batata Vada", 30));
        itemList.add(new Item("Samosa", 30));
        itemList.add(new Item("Thalipeeth", 40));
        itemList.add(new Item("Dal Pakwan", 40));
        itemList.add(new Item("Dhokla", 30));
        itemList.add(new Item("Dosa/Uttappa", 40));
        itemList.add(new Item("Jalebi Fafda", 40));
        itemList.add(new Item("Puri Bhaji", 40));
        itemList.add(new Item("Chole Bhature", 50));
        itemList.add(new Item("Sabudana Vada", 30));
        itemList.add(new Item("Sabudana Khichadi", 40));
        itemList.add(new Item("Farali Misal", 40));
        itemList.add(new Item("Farali Pattice", 30));
        itemList.add(new Item("Sweet Kachori", 30));
        itemList.add(new Item("Sabudana Poori", 30));
        itemList.add(new Item("Kothimbir Vadi", 40));
        itemList.add(new Item("Alu Vadi", 40));
        itemList.add(new Item("Veg Cutlet", 30));
        itemList.add(new Item("Mini Batata Vada", 30));
        itemList.add(new Item("Mix Bhaji/Moong Bhaji", 30));
        itemList.add(new Item("Veg Pakoda", 30));
        itemList.add(new Item("Paneer Pakoda", 40));
        itemList.add(new Item("Hara Bhara Kabab", 40));
        itemList.add(new Item("Corn Crackers", 30));
        itemList.add(new Item("Corn Pattice", 30));
        itemList.add(new Item("Corn Tikki", 30));
        itemList.add(new Item("Gold Coins", 40));
        itemList.add(new Item("Mini Samosa", 30));
        itemList.add(new Item("Cocktail Samosa", 30));
        itemList.add(new Item("Chinese Samosa", 40));
        itemList.add(new Item("Khandvi", 40));
        itemList.add(new Item("Sandwich Dhokla", 40));
        itemList.add(new Item("Matar Karanji", 30));
        itemList.add(new Item("Dahi Vada", 40));
        itemList.add(new Item("Paneer Chilli", 50));
        itemList.add(new Item("Spring Roll", 40));
        itemList.add(new Item("Cheese Ball", 40));
        itemList.add(new Item("Veg Lollipop", 40));
        itemList.add(new Item("Manchurian Dry", 40));
        itemList.add(new Item("Mushroom Chilli Dry", 50));
        itemList.add(new Item("Veg Roll", 40));
        itemList.add(new Item("American Roll", 40));
        itemList.add(new Item("Paneer Tikka", 50));
        itemList.add(new Item("Tandoori Mushroom", 50));
        itemList.add(new Item("Subz Seekh Kabab", 50));
        itemList.add(new Item("French Fries", 30));
        itemList.add(new Item("Lasagne", 60));
        itemList.add(new Item("Panini", 40));
        itemList.add(new Item("Nachos", 40));
        itemList.add(new Item("Mushroom in Garlic Sauce", 50));
        itemList.add(new Item("Italian Sponge Wada", 30));
        itemList.add(new Item("Buttermilk Fried Onion", 30));
        itemList.add(new Item("Chole Koliwada", 40));
        itemList.add(new Item("Tea", 10));
        itemList.add(new Item("Coffee", 15));
        itemList.add(new Item("Masala Milk", 20));
        itemList.add(new Item("Cold Drink", 20));
        itemList.add(new Item("Limbu Pani", 15));
        itemList.add(new Item("Watermelon Juice", 20));
        itemList.add(new Item("Pineapple Juice", 20));
        itemList.add(new Item("Rasna Flavour", 10));
        itemList.add(new Item("Kokam Sarbat", 20));
        itemList.add(new Item("Pina Colada", 30));
        itemList.add(new Item("Bluebird", 25));
        itemList.add(new Item("Mango Blast", 25));
        itemList.add(new Item("Lassi", 20));
        itemList.add(new Item("Kairi Panna", 20));
        itemList.add(new Item("Madhur Piyush", 25));
        itemList.add(new Item("Solkadhi", 20));
        itemList.add(new Item("Buttermilk", 15));
        itemList.add(new Item("Tropical Lychee", 20));
        itemList.add(new Item("Tomato Soup", 25));
        itemList.add(new Item("Palak Soup", 25));
        itemList.add(new Item("Veg Clear Soup", 20));
        itemList.add(new Item("Veg Manchow Soup", 25));
        itemList.add(new Item("Sweet Corn Soup", 25));
        itemList.add(new Item("Schezwan Soup", 30));
        itemList.add(new Item("Cocktail Soup", 25));
        itemList.add(new Item("Mushroom Soup", 25));
        itemList.add(new Item("Hot & Sour Soup", 25));
        itemList.add(new Item("Banana Milkshake", 25));
        itemList.add(new Item("Chikoo Milkshake", 25));
        itemList.add(new Item("Chocolate Milkshake", 25));
        itemList.add(new Item("Vanilla Milkshake", 25));
        itemList.add(new Item("Rose Milkshake", 25));
        itemList.add(new Item("Steamed Rice", 15));
        itemList.add(new Item("Jeera Rice", 20));
        itemList.add(new Item("Triple Schezwan Rice", 30));
        itemList.add(new Item("Veg Pulao", 30));
        itemList.add(new Item("Dal Khichdi", 30));
        itemList.add(new Item("Veg Biryani", 40));
        itemList.add(new Item("Green Peas Pulao", 30));
        itemList.add(new Item("Tava Pulao", 30));
        itemList.add(new Item("Navratan Pulao", 30));
        itemList.add(new Item("Fried Rice", 30));
        itemList.add(new Item("Schezwan Rice", 30));
        itemList.add(new Item("Hong Kong Rice", 30));
        itemList.add(new Item("Combination Rice", 40));
        itemList.add(new Item("Singapore Rice", 40));
        itemList.add(new Item("Varan", 30));
        itemList.add(new Item("Kadhi Pakodi", 40));
        itemList.add(new Item("Turdal Amti", 40));
        itemList.add(new Item("Palak Dal", 40));
        itemList.add(new Item("Desi Ghee Dal Tadka", 40));
        itemList.add(new Item("Sambar", 40));
        itemList.add(new Item("Dal Fry", 40));
        itemList.add(new Item("Dal Dhokli", 40));
        itemList.add(new Item("Dal Makhani", 50));
        itemList.add(new Item("Maa ki Dal", 50));
        itemList.add(new Item("Puri", 10));
        itemList.add(new Item("Phulka", 5));
        itemList.add(new Item("Paratha", 20));
        itemList.add(new Item("Bhakri", 15));
        itemList.add(new Item("Tandoori Roti", 15));
        itemList.add(new Item("Roomali Roti", 20));
        itemList.add(new Item("Naan", 20));
        itemList.add(new Item("Missi Roti", 20));
        itemList.add(new Item("Palak Puri", 15));
        itemList.add(new Item("Paneer Kadai", 60));
        itemList.add(new Item("Paneer Makhani", 70));
        itemList.add(new Item("Palak Paneer", 60));
        itemList.add(new Item("Methi Matar Malai", 60));
        itemList.add(new Item("Paneer Tikka Masala", 70));
        itemList.add(new Item("Paneer Pasanda", 70));
        itemList.add(new Item("Paneer Kurma", 60));
        itemList.add(new Item("Paneer Manchurian Gravy", 60));
        itemList.add(new Item("Matar Paneer", 60));
        itemList.add(new Item("Paneer Masala", 60));
        itemList.add(new Item("Shahi Paneer", 70));
        itemList.add(new Item("Paneer Lababdar", 70));
        itemList.add(new Item("Mushroom Paneer", 70));
        itemList.add(new Item("Paneer Makhanwala", 70));
        itemList.add(new Item("Paneer Kolhapuri", 70));
        itemList.add(new Item("Paneer Handi", 60));
        itemList.add(new Item("Matki Usal", 40));
        itemList.add(new Item("Kala Vatana Usal", 40));
        itemList.add(new Item("Bharli Vangi", 50));
        itemList.add(new Item("Navratan Korma", 60));
        itemList.add(new Item("Sukhi Batata Bhaji", 30));
        itemList.add(new Item("Paneer Bhurji", 60));
        itemList.add(new Item("Kaju Lazeez", 70));
        itemList.add(new Item("Veg Kadai", 50));
        itemList.add(new Item("Baingan Bharta", 50));
        itemList.add(new Item("Veg Handi", 50));
        itemList.add(new Item("Bhindi Masala/Fry", 50));
        itemList.add(new Item("Aloo Methi Dry", 50));
        itemList.add(new Item("Kobi Chana Bhaji", 50));
        itemList.add(new Item("Chana Masala", 50));
        itemList.add(new Item("Chole", 50));
        itemList.add(new Item("Phool Vatana Batata", 50));
        itemList.add(new Item("Valache Birde", 40));
        itemList.add(new Item("Palak Kofta", 60));
        itemList.add(new Item("Stuffed Tomato", 50));
        itemList.add(new Item("Veg Korma", 60));
        itemList.add(new Item("Malai Kofta", 60));
        itemList.add(new Item("Dum Aloo", 50));
        itemList.add(new Item("Batata Chips Bhaji", 40));
        itemList.add(new Item("Veg Kolhapuri", 60));
        itemList.add(new Item("Tava Mehfil", 50));
        itemList.add(new Item("Pav Bhaji", 50));
        itemList.add(new Item("Chinese", 70));
        itemList.add(new Item("Popcorn", 20));
        itemList.add(new Item("Masala Pan/Mukhwas", 10));
        itemList.add(new Item("Ice Cream/Kulfi Falooda", 50));
        itemList.add(new Item("Pizza Pasta", 70));
        itemList.add(new Item("Shrikhand/Amrakhand", 40));
        itemList.add(new Item("Aamras", 50));
        itemList.add(new Item("Gulab Jamun", 30));
        itemList.add(new Item("Jalebi", 30));
        itemList.add(new Item("Jalebi Rabri", 40));
        itemList.add(new Item("Moong Dal Halwa", 50));
        itemList.add(new Item("Kesar Basundi", 40));
        itemList.add(new Item("Sitaphal Basundi", 40));
        itemList.add(new Item("Gajar Halwa/Dudhi Halwa", 40));
        itemList.add(new Item("Madhur Milan", 50));
        itemList.add(new Item("Pineapple Sheera Kharvas", 40));
        itemList.add(new Item("Puran Poli", 40));
        itemList.add(new Item("Shevai Kheer", 40));
        itemList.add(new Item("Mohanthal", 40));
        itemList.add(new Item("Papad, Salad", 20));
        itemList.add(new Item("Veg Raita", 20));
        itemList.add(new Item("Boondi Raita", 20));
        itemList.add(new Item("Pineapple Raita", 20));
        itemList.add(new Item("Russian Salad", 40));
        itemList.add(new Item("Red Chutney", 20));
        itemList.add(new Item("Green Chutney", 20));
        itemList.add(new Item("Chana Chaat", 30));
        itemList.add(new Item("Peanut Chaat", 30));
        itemList.add(new Item("Moong Chaat", 30));


        addItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addItem();
            }
        });

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateTotalPrice();
            }
        });

        // Add the initial item
        addItem();
    }

    private void addItem() {
        Spinner itemSpinner = new Spinner(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            itemSpinner.setId(View.generateViewId());
        }

        // Set the layout parameters for the Spinner
        LinearLayout.LayoutParams spinnerLayoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                100
        );
        spinnerLayoutParams.setMargins(0, 16, 0, 0);
        itemSpinner.setLayoutParams(spinnerLayoutParams);

        // Sort the item list in ascending order alphabetically
        Collections.sort(itemList, new Comparator<Item>() {
            @Override
            public int compare(Item item1, Item item2) {
                return item1.getName().compareToIgnoreCase(item2.getName());
            }
        });

        // Create an ArrayAdapter for the Spinner using the sorted item list
        ArrayAdapter<Item> spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, itemList);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        itemSpinner.setAdapter(spinnerAdapter);

        // Add the Spinner to the itemLayout
        itemLayout.addView(itemSpinner);

        itemCount++;
    }


    private void calculateTotalPrice() {
        int total = 0;

        // Loop through all the added items and calculate the total price
        for (int i = 0; i < itemLayout.getChildCount(); i++) {
            View itemView = itemLayout.getChildAt(i);
            if (itemView instanceof Spinner) {
                Spinner itemSpinner = (Spinner) itemView;
                Item selectedItem = (Item) itemSpinner.getSelectedItem();

                if (selectedItem != null) {
                    total += selectedItem.getValue();
                }
            }
        }

        // Display the total price
        totalPriceTextView.setText("Total Price: ₹" + total);
    }




    private class Item {
        private String name;
        private int value;

        public Item(String name, int value) {
            this.name = name;
            this.value = value;
        }

        public String getName() {
            return name;
        }

        public int getValue() {
            return value;
        }

        @Override
        public String toString() {
            return name + ": ₹" + value;
        }
    }
}


