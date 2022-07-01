package com.example.hp.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.hp.myapplication.Common.Common;
import com.example.hp.myapplication.Model.Request;
import com.example.hp.myapplication.ViewHolder.OrderViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class OrderActivity extends AppCompatActivity {

    public RecyclerView recyclerView;
    public RecyclerView.LayoutManager layoutManager;

    FirebaseRecyclerAdapter<Request, OrderViewHolder> adapter;

    FirebaseDatabase database;
    DatabaseReference requests;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        database = FirebaseDatabase.getInstance();
        requests = database.getReference("Requests");

        recyclerView = findViewById(R.id.listOrder);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        loadOrder(Common.currentUser.getPhone());
    }

    private void loadOrder(String phone) {
        adapter = new FirebaseRecyclerAdapter<Request, OrderViewHolder>(Request.class, R.layout.order_layout, OrderViewHolder.class, requests.orderByChild("phone").equalTo(phone)) {
            @Override
            protected void populateViewHolder(OrderViewHolder viewHolder, Request model, int position) {
                viewHolder.txtorder_id.setText(adapter.getRef(position).getKey());
                viewHolder.txtorder_status.setText(convertCodeToStatus(model.getStatus()));
                viewHolder.txtorder_phone.setText(model.getPhone());
                viewHolder.txtorder_address.setText(model.getAddress());
            }
        };
        recyclerView.setAdapter(adapter);
    }

    private String convertCodeToStatus(String status) {
        if (status.equals("0")){
            return "Placed";
        }else if (status.equals("1")){
            return "On My Way";
        }else {
            return "Shipped";
        }



    }
}