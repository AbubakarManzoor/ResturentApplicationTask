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


public class itemListCustomerAdapter extends RecyclerView.Adapter<itemListCustomerAdapter.MenuList_ViewHolder>{

    public ArrayList<MenuItems> menuItems;

    Context context;

    public itemListCustomerAdapter(ArrayList<MenuItems> menuItems, Context context) {
        this.menuItems = menuItems;
        this.context = context;

    }


    @NonNull
    @Override
    public MenuList_ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.table_items, viewGroup, false);
        return new MenuList_ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MenuList_ViewHolder holder, final int position) {

        holder.itemName.setText(menuItems.get(position).getItemName());
        holder.itemPrice.setText(menuItems.get(position).getItemPrice()+" Rs");

    }

    @Override
    public int getItemCount() {
        return menuItems.size();
    }

    public class MenuList_ViewHolder extends RecyclerView.ViewHolder {

        TextView itemName, itemPrice;

        public MenuList_ViewHolder(@NonNull View itemView) {
            super(itemView);

            itemName = (TextView) itemView.findViewById(R.id.tv_menu_item_name);
            itemPrice = (TextView) itemView.findViewById(R.id.tv_menu_item_price);


        }
    }
}
