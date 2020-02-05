package info.semicolen.resturentapplicationtask.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import info.semicolen.resturentapplicationtask.Models.MenuItems;
import info.semicolen.resturentapplicationtask.Models.Selected_Menu_Items;
import info.semicolen.resturentapplicationtask.R;
import info.semicolen.resturentapplicationtask.waiter_table_menu;


public class Waiter_Adaptor_Menu extends RecyclerView.Adapter<Waiter_Adaptor_Menu.MenuList_ViewHolder>{

    public ArrayList<MenuItems> menuItems;

    Context context;

    public Waiter_Adaptor_Menu(ArrayList<MenuItems> menuItems, Context context) {
        this.menuItems = menuItems;
        this.context = context;

    }


    @NonNull
    @Override
    public MenuList_ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.wiater_curstom_menu_selection_item, viewGroup, false);
        return new MenuList_ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MenuList_ViewHolder holder, final int position) {

        holder.itemName.setText(menuItems.get(position).getItemName());
        holder.itemPrice.setText(menuItems.get(position).getItemPrice()+" Rs");

        holder.itemConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(holder.itemConfirm.isChecked()) {

                    if(!holder.itemQuantity.getText().toString().equals("0")) {
                        Selected_Menu_Items selected_menu_items = new Selected_Menu_Items(menuItems.get(position).getId(),
                                menuItems.get(position).getItemName(), Integer.parseInt(holder.itemQuantity.getText().toString()),
                                menuItems.get(position).getItemPrice());
                        waiter_table_menu.al_selectedMenuItems.add(selected_menu_items);

                        Toast.makeText(context, holder.itemName.getText() + " is Added in you Order.", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(context, "Please Select the Quantity you want", Toast.LENGTH_SHORT).show();

                        holder.itemConfirm.setChecked(false);
                    }

                }else {

                    for(int i = 0;i<waiter_table_menu.al_selectedMenuItems.size();i++) {
                        if(waiter_table_menu.al_selectedMenuItems.get(i).getId() == menuItems.get(position).getId()) {
                            waiter_table_menu.al_selectedMenuItems.remove(i);
                        }
                    }

                    holder.itemQuantity.setText("0");

                    Toast.makeText(context, holder.itemName.getText() + " is removed from your Order list.", Toast.LENGTH_SHORT).show();
                }
            }
        });


        holder.quantityIncrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               int total = Integer.parseInt( holder.itemQuantity.getText().toString());
               total++;
               holder.itemQuantity.setText(total+"");

                if(holder.itemConfirm.isChecked()) {


                    for(int i = 0;i<waiter_table_menu.al_selectedMenuItems.size();i++) {
                        if(waiter_table_menu.al_selectedMenuItems.get(i).getId() == menuItems.get(position).getId()) {
                            waiter_table_menu.al_selectedMenuItems.remove(i);
                        }
                    }

                    Selected_Menu_Items selected_menu_items = new Selected_Menu_Items(menuItems.get(position).getId(),
                            menuItems.get(position).getItemName(), Integer.parseInt(holder.itemQuantity.getText().toString()),
                            menuItems.get(position).getItemPrice());
                    waiter_table_menu.al_selectedMenuItems.add(selected_menu_items);
                }

            }
        });

        holder.quantityDecrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int total = Integer.parseInt( holder.itemQuantity.getText().toString());
                total--;
                if(total >= 0) {
                    holder.itemQuantity.setText(total+"");
                }

                if(holder.itemConfirm.isChecked()) {
                    if(total!=0) {

                        //removing item
                        for(int i = 0;i<waiter_table_menu.al_selectedMenuItems.size();i++) {
                            if(waiter_table_menu.al_selectedMenuItems.get(i).getId() == menuItems.get(position).getId()) {
                                waiter_table_menu.al_selectedMenuItems.remove(i);
                            }
                        }

                        //adding item
                        Selected_Menu_Items selected_menu_items = new Selected_Menu_Items(menuItems.get(position).getId(),
                                menuItems.get(position).getItemName(), Integer.parseInt(holder.itemQuantity.getText().toString()),
                                menuItems.get(position).getItemPrice());
                        waiter_table_menu.al_selectedMenuItems.add(selected_menu_items);
                    }else {

                        //removing item
                        for(int i = 0;i<waiter_table_menu.al_selectedMenuItems.size();i++) {
                            if(waiter_table_menu.al_selectedMenuItems.get(i).getId() == menuItems.get(position).getId()) {
                                waiter_table_menu.al_selectedMenuItems.remove(i);
                            }
                        }
                        holder.itemConfirm.setChecked(false);
                    }


                }

            }
        });

    }

    @Override
    public int getItemCount() {
        return menuItems.size();
    }

    public class MenuList_ViewHolder extends RecyclerView.ViewHolder {

        Button quantityIncrease, quantityDecrease;
        TextView itemName, itemPrice, itemQuantity;
        CheckBox itemConfirm;

        public MenuList_ViewHolder(@NonNull View itemView) {
            super(itemView);

            quantityIncrease = (Button) itemView.findViewById(R.id.btn_menu_quantity_increase);
            quantityDecrease = (Button) itemView.findViewById(R.id.btn_menu_quantity_decrease);

            itemName = (TextView) itemView.findViewById(R.id.tv_menu_item_name);
            itemPrice = (TextView) itemView.findViewById(R.id.tv_menu_item_price);
            itemQuantity = (TextView) itemView.findViewById(R.id.tv_menu_item_quantity);

            itemConfirm = (CheckBox) itemView.findViewById(R.id.cb_menu_confirm_item);

        }
    }
}
