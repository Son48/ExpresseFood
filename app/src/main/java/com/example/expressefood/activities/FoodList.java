package com.example.expressefood.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.expressefood.R;
import com.example.expressefood.adapter.FoodViewApdapter;
import com.example.expressefood.adapter.MenuViewAdapter;
import com.example.expressefood.interface_food.ItemClickListener;
import com.example.expressefood.model.Category;
import com.example.expressefood.model.Food;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class FoodList extends AppCompatActivity {

    private RecyclerView rvFood;
    private LinearLayoutManager linearLayoutManager;
    String categoryId="";
    FirebaseDatabase firebaseDatabase;
    DatabaseReference foodList;

    private FirebaseRecyclerAdapter<Food, FoodViewApdapter> firebaseRecyclerAdapter=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_list);

        firebaseDatabase=FirebaseDatabase.getInstance();
        foodList=firebaseDatabase.getReference("Food");

        rvFood=findViewById(R.id.rvFood);
        rvFood.setHasFixedSize(true);
        linearLayoutManager =new LinearLayoutManager(this);
        rvFood.setLayoutManager(linearLayoutManager);

        //get Intent Here
        if(getIntent()!=null){
            categoryId=getIntent().getStringExtra("CategoryId");
        }
        if(!categoryId.isEmpty()&&categoryId!=null){
            loadListFood(categoryId);
        }
    }

    private void loadListFood(String categoryId) {
        FirebaseRecyclerOptions<Food> options =
                new FirebaseRecyclerOptions.Builder<Food>()
                        .setQuery(foodList.orderByChild("MenuId").equalTo(categoryId), Food.class)
                        .build();
        firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Food, FoodViewApdapter>(options) {
            @Override
            protected void onBindViewHolder(@NonNull FoodViewApdapter holder, int position, @NonNull Food model) {
                    holder.foodName.setText(model.getName());
                    Picasso.get().load(model.getImage()).into(holder.foodImage);

                    final Food food=model;
                    holder.setItemClickListener(new ItemClickListener() {
                        @Override
                        public void onClick(View view, int position, boolean isLongClick) {
                            Toast.makeText(FoodList.this, ""+model.getName(), Toast.LENGTH_SHORT).show();
                        }
                    });
            }

            @NonNull
            @Override
            public FoodViewApdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.food_item, parent, false);
                return new FoodViewApdapter(view);
            }
        };
        rvFood.setAdapter(firebaseRecyclerAdapter);
        firebaseRecyclerAdapter.startListening();
    }
}