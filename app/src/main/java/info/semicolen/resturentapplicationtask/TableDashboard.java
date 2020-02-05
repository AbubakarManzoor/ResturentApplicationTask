package info.semicolen.resturentapplicationtask;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import info.semicolen.resturentapplicationtask.Adapter.Waiter_Adaptor_Menu;
import info.semicolen.resturentapplicationtask.Adapter.itemListCustomerAdapter;
import info.semicolen.resturentapplicationtask.FirebaseModel.fMenuItem;
import info.semicolen.resturentapplicationtask.Models.MenuItems;

import android.os.Bundle;
import android.view.View;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class TableDashboard extends AppCompatActivity {

    ArrayList<MenuItems> menuItems = new ArrayList<>();
    RecyclerView menuList;
    itemListCustomerAdapter adaptorMenu;

    FirebaseDatabase database;
    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_dashboard);

        gettingMenuItemsFromDatabase();
        menuList = (RecyclerView) findViewById(R.id.waiter_menu_list);
        menuList.setLayoutManager(new LinearLayoutManager(TableDashboard.this));
        adaptorMenu = new itemListCustomerAdapter(menuItems,getApplicationContext());
        menuList.setAdapter(adaptorMenu);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("CallWaiter");

        findViewById(R.id.callawaiterButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference keyRef = myRef.push();
                String key = keyRef.getKey();

                myRef.child("Calling Table Name: ").setValue("2");


            }
        });

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
}
