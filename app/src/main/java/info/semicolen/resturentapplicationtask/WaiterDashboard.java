package info.semicolen.resturentapplicationtask;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import info.semicolen.resturentapplicationtask.Models.Tables;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class WaiterDashboard extends AppCompatActivity {

    GridView Home_GridView;
    CustomAdapter customAdapter;
    ArrayList<info.semicolen.resturentapplicationtask.Models.Tables> Tables = new ArrayList<>();


    FirebaseDatabase database;
    DatabaseReference myRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waiter_dashboard);

        //Getting Tables from Database
        GettingTablesFromDatabase();

        Home_GridView = (GridView) findViewById(R.id.tableGridView);
        customAdapter = new CustomAdapter();
        Home_GridView.setAdapter(customAdapter);

        Home_GridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), waiter_table_menu.class);
                intent.putExtra("TableNumber", Tables.get(position).getTableName());
                startActivity(intent);
            }
        });

        NotificationChannel channel = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            channel = new NotificationChannel("MyNotification", "MyNotification", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("CallWaiter");

        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                String tableNumber = dataSnapshot.getValue(String.class);
                Toast.makeText(WaiterDashboard.this, "Data Changed : " + tableNumber, Toast.LENGTH_SHORT).show();
                sendNotification("Hello Waiter", "Table # " + tableNumber + " is calling you");


            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    public void sendNotification(String tittle, String message) {

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "MyNotification")
                .setContentTitle(tittle)
                .setSmallIcon(R.drawable.logoandnamelogin)
                .setAutoCancel(true)
                .setContentText(message);

        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(this);
        managerCompat.notify(999, builder.build());
    }

    public void GettingTablesFromDatabase() {
        Tables tables = new Tables("1", 3, 2, 10, false);
        Tables.add(tables);
        Tables tables1 = new Tables("2", 10,7, 32, true);
        Tables.add(tables1);
        Tables tables2 = new Tables("3", 7,7, 40, false);
        Tables.add(tables2);
        Tables tables3 = new Tables("4", 6, 5,50, false);
        Tables.add(tables3);
        Tables tables4 = new Tables("5", 3,1, 4, true);
        Tables.add(tables4);
    }

    private class CustomAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return Tables.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View vieww1 = getLayoutInflater().inflate(R.layout.waiter_custom_home_grid_tables,null);

            TextView reserved = vieww1.findViewById(R.id.tv_Home_Grid_Reserve);
            TextView totalBooking = vieww1.findViewById(R.id.tv_Home_Grid_TotalReservations);
            TextView tableNumber = vieww1.findViewById(R.id.tv_Home_Grid_TableNumber);
            TextView tableSeats = vieww1.findViewById(R.id.tv_Home_Grid_TableCapacity);
            ImageView iv_chairsAndTable = vieww1.findViewById(R.id.iv_Home_Grid_Chairs);


            LinearLayout item = vieww1.findViewById(R.id.waiterHomeGrid);

            reserved.setVisibility(View.GONE);

            if(Tables.get(position).isReserved()) {
                //Table is Reserved
                reserved.setVisibility(View.VISIBLE);
                iv_chairsAndTable.setImageResource(R.drawable.ic_waiter_home_chairs_reserved);
                item.setBackgroundResource(R.drawable.waiter_home_grid_background_reserved);
                tableNumber.setBackgroundResource(R.color.home_reserved_light);
            }else if(Tables.get(position).getTotalSeats() == Tables.get(position).getTotalSeatsFree()) {
                //this means that table is fully available
                reserved.setVisibility(View.GONE);
                iv_chairsAndTable.setImageResource(R.drawable.ic_waiter_home_chairs_available);
                item.setBackgroundResource(R.drawable.waiter_home_grid_background_available);
                tableNumber.setBackgroundResource(R.color.home_Available_light);
            }
            else if(Tables.get(position).getTotalSeatsFree() > 0 ){
                //Table is not Reserved and Some Seats are free
                //means Partial
                reserved.setVisibility(View.GONE);
                iv_chairsAndTable.setImageResource(R.drawable.ic_waiter_home_chairs_partial);
                item.setBackgroundResource(R.drawable.waiter_home_grid_background_partial);
                tableNumber.setBackgroundResource(R.color.home_partial_light);
            }else {
                //this means that table is fully available
                reserved.setVisibility(View.GONE);
                item.setBackgroundResource(R.drawable.waiter_home_grid_background_available);
                tableNumber.setBackgroundResource(R.color.home_Available_light);
            }

            tableNumber.setText("Table : " + Tables.get(position).getTableName());
            totalBooking.setText(Tables.get(position).getNoOfBookings()+"");
            tableSeats.setText("Seats : " + Tables.get(position).getTotalSeats()+"");

            return vieww1;
        }


    }
}
