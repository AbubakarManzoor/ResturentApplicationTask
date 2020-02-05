package info.semicolen.resturentapplicationtask;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.Time;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import info.semicolen.resturentapplicationtask.Adapter.Waiter_Adaptor_Menu;
import info.semicolen.resturentapplicationtask.FirebaseModel.fMenuItem;
import info.semicolen.resturentapplicationtask.Models.MenuItems;
import info.semicolen.resturentapplicationtask.Models.Selected_Menu_Items;

public class waiter_table_menu extends AppCompatActivity {

    //Widgets
    Button buttonOrder;

    int Hour,Minute;
    ArrayList<MenuItems> menuItems = new ArrayList<>();
    RecyclerView menuList;
    Waiter_Adaptor_Menu adaptorMenu;
    FirebaseDatabase database;
    DatabaseReference myRef;


    public static ArrayList<Selected_Menu_Items> al_selectedMenuItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waiter_table_menu);

        //Widget Initilization
        buttonOrder = (Button) findViewById(R.id.activity_waiter_buton_order);
        al_selectedMenuItems = new ArrayList<>();

        Intent intent = getIntent();
        final String TableName = intent.getStringExtra("TableNumber");

        getDateAndTime();
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Order");

        gettingMenuItemsFromDatabase();
        menuList = (RecyclerView) findViewById(R.id.waiter_menu_list);
        menuList.setLayoutManager(new LinearLayoutManager(waiter_table_menu.this));
        adaptorMenu = new Waiter_Adaptor_Menu(menuItems,getApplicationContext());
        menuList.setAdapter(adaptorMenu);


        buttonOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                StringBuffer buffer = new StringBuffer();
                int itemPriceAccordingToQunatity = 0;
                int totalOrderPrice = 0;

                DatabaseReference keyRef = myRef.push();
                String key = keyRef.getKey();

                for(int i = 0;i<al_selectedMenuItems.size();i++) {


                    itemPriceAccordingToQunatity = al_selectedMenuItems.get(i).getQuantity()
                            *al_selectedMenuItems.get(i).getItemprice();

                    fMenuItem item = new fMenuItem(al_selectedMenuItems.get(i).getName()+"",
                            al_selectedMenuItems.get(i).getQuantity()+"",
                            al_selectedMenuItems.get(i).getItemprice()+"", itemPriceAccordingToQunatity+"",
                            "Just Order",Hour + ":" + Minute);



                    myRef.child("Table " + TableName).child(key).push().setValue(item);


                    buffer.append("item " + i +" : "+ al_selectedMenuItems.get(i).getName() + " | Quantity : "
                            + al_selectedMenuItems.get(i).getQuantity()+ " | Price : " + itemPriceAccordingToQunatity + "\n\n");

                    totalOrderPrice = totalOrderPrice + itemPriceAccordingToQunatity;
                }

                buffer.append("Total Price : " + totalOrderPrice);
                //show all the data
                showMessage("Your Order", buffer.toString());

            }
        });



    }

    public void showMessage(String tittle, String Message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(tittle);
        builder.setMessage(Message);
        builder.show();
    }


    public void gettingMenuItemsFromDatabase() {
        MenuItems items;

        items = new MenuItems(0,"Chicken Burger", 130);
        menuItems.add(items);

        items = new MenuItems(1,"Zinger Burger", 180);
        menuItems.add(items);

        items = new MenuItems(2,"Chicken Shwarma", 100);
        menuItems.add(items);

        items = new MenuItems(3,"Zinger Shwarma", 150);
        menuItems.add(items);

        items = new MenuItems(4,"Chicken Sandwitch", 130);
        menuItems.add(items);

        items = new MenuItems(5,"Grilled Sandwitch", 180);
        menuItems.add(items);

        items = new MenuItems(6,"Shami Burger", 60);
        menuItems.add(items);

        items = new MenuItems(7,"Regular Drink", 30);
        menuItems.add(items);

        items = new MenuItems(8,"Half Liter Drink", 50);
        menuItems.add(items);

        items = new MenuItems(9,"1.5 Liter Drink", 90);
        menuItems.add(items);

    }

    private void getDateAndTime() {
        Time today = new Time(Time.getCurrentTimezone());
        today.setToNow();

        Hour = today.hour;
        Minute = today.minute;

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
