package info.semicolen.resturentapplicationtask.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import info.semicolen.resturentapplicationtask.Models.Orders;
import info.semicolen.resturentapplicationtask.R;


public class Cook_Adaptor_Dashboard_OrderList extends RecyclerView.Adapter<Cook_Adaptor_Dashboard_OrderList.OrderList_ViewHolder>{

    public ArrayList<Orders> al_orders;

    Context context;

    public Cook_Adaptor_Dashboard_OrderList(ArrayList<Orders> al_orders, Context context) {
        this.al_orders = al_orders;
        this.context = context;

    }


    @NonNull
    @Override
    public OrderList_ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.cook_custom_dashboard_order_item, viewGroup, false);
        return new OrderList_ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final OrderList_ViewHolder holder, final int position) {

        holder.orderId.setText("Order : " + al_orders.get(position).getOrderId());
        holder.tableName.setText(al_orders.get(position).getTableName());
        holder.orderTime.setText(al_orders.get(position).getOrderTime());
        holder.orderTotalItems.setText(al_orders.get(position).getOrderTotalItems()+" Items");


        if(al_orders.get(position).getOrderProgress().equals("Just Order")) {
            holder.itemBackground.setBackgroundResource(R.color.cook_dashboard_item_backgrond_JustOrderState);
        }else if(al_orders.get(position).getOrderProgress().equals("Cooking")){
            holder.itemBackground.setBackgroundResource(R.color.cook_dashboard_item_backgrond_CookingStage);
        }else {
            holder.itemBackground.setBackgroundResource(R.color.cook_dashboard_item_backgrond_Deliverd);
        }

    }

    @Override
    public int getItemCount() {
        return al_orders.size();
    }

    public class OrderList_ViewHolder extends RecyclerView.ViewHolder {

        TextView orderId, tableName, orderTime, orderTotalItems;
        LinearLayout itemBackground;

        public OrderList_ViewHolder(@NonNull View itemView) {
            super(itemView);

            orderId = (TextView) itemView.findViewById(R.id.tv_cookDasboardOrderNumber);
            tableName = (TextView) itemView.findViewById(R.id.tv_cookDashboardTableName);
            orderTime = (TextView) itemView.findViewById(R.id.tv_cookDasboardOrderTime);
            orderTotalItems = (TextView) itemView.findViewById(R.id.cook_dashbaord_list_orderTotalItems);
            itemBackground = (LinearLayout) itemView.findViewById(R.id.cook_dashboard_item_background);
        }
    }
}
