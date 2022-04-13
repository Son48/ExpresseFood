package com.example.expressefood.activities.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.MenuView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.expressefood.R;
import com.example.expressefood.activities.FoodList;
import com.example.expressefood.adapter.MenuViewAdapter;
import com.example.expressefood.databinding.FragmentHomeBinding;
import com.example.expressefood.interface_food.ItemClickListener;
import com.example.expressefood.model.Category;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private FirebaseDatabase database;
    private DatabaseReference category;
    private LinearLayoutManager linearLayoutManager;
    private FirebaseRecyclerAdapter<Category,MenuViewAdapter> firebaseRecyclerAdapter= null;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        //init Firebase
        database=FirebaseDatabase.getInstance();
        category=database.getReference("Category");
        //load menu
        binding.rvRestaurant.setHasFixedSize(true);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        binding.rvRestaurant.setLayoutManager(linearLayoutManager);
        loadMenu();
        return root;
    }

    private void loadMenu() {
        FirebaseRecyclerOptions<Category> options =
                new FirebaseRecyclerOptions.Builder<Category>()
                        .setQuery(category, Category.class)
                        .build();

            firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Category, MenuViewAdapter>(options) {
            @Override
            protected void onBindViewHolder(@NonNull MenuViewAdapter holder, int position, @NonNull Category model) {
                    holder.txtMenuName.setText(model.getName());
                    Picasso.get().load(model.getImage()).into(holder.imgView);
                    Category onClick=model;
                    holder.setItemClickListener(new ItemClickListener() {
                        @Override
                        public void onClick(View view, int position, boolean isLongClick) {
                            Intent intent=new Intent(getContext(), FoodList.class);
                            intent.putExtra("CategoryId",firebaseRecyclerAdapter.getRef(position).getKey());
                            startActivity(intent);
                        }
                    });
            }

            @NonNull
            @Override
            public MenuViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.menu_item, parent, false);
                return new MenuViewAdapter(view);
            }



        };
        binding.rvRestaurant.setAdapter(firebaseRecyclerAdapter);
        firebaseRecyclerAdapter.startListening();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}