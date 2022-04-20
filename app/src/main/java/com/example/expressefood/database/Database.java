package com.example.expressefood.database;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;

import com.example.expressefood.model.Order;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;
import java.util.List;

public class Database extends SQLiteAssetHelper {

    private static final String DB_NAME="EatltDB.db";
    private static final int DB_VER=1;
    public Database(Context context) {
        super(context,DB_NAME,null,DB_VER);
    }
    @SuppressLint("Range")
    public List<Order> getCards(){
        SQLiteDatabase sqLiteDatabase=getReadableDatabase();
        SQLiteQueryBuilder sqLiteQueryBuilder=new SQLiteQueryBuilder();

        String[] sqlSelect={"ProductName","ProductID","Qualitity","Price","Discount"};
        String sqlTable="OrderDetail";

        sqLiteQueryBuilder.setTables(sqlTable);
        Cursor c=sqLiteQueryBuilder.query(sqLiteDatabase,sqlSelect,null,null,null,null,null);

        final List<Order> result=new ArrayList<>();
        if(c.moveToFirst()){
            do{
                result.add(new Order(c.getString(c.getColumnIndex("ProductID")),
                        c.getString(c.getColumnIndex("ProductName")),
                        c.getString(c.getColumnIndex("Qualitity")),
                        c.getString(c.getColumnIndex("Price")),
                        c.getString(c.getColumnIndex("Discount"))
                ));
            }while (c.moveToFirst());
        }
        return result;

    }
    public void addToCard(Order order){
        SQLiteDatabase database=getReadableDatabase();
        String query=String.format("INSERT INTO OrderDetail(ProductID,ProductName,Qualitity,Price,Discount) VALUES('%s','%s','%s','%s','%s');",
                order.getProductID(),
                order.getProductName(),
                order.getQualitity(),
                order.getPrice(),
                order.getDiscount());
        database.execSQL(query);
    }
    public void cleanCard(Order order){
        SQLiteDatabase database=getReadableDatabase();
        String query=String.format("DELETE FROM OrderDetail");
        database.execSQL(query);
    }
}
