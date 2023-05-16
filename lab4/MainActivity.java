package com.example.lab4;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    EditText editName;
    EditText editPrice;
    EditText editDate;
    Button buttonSend;
    Button buttonView;
    TextView textViewResult;
    Button buttonAsk;
    VideoView videoView;

    DBHelper mydb;

    ArrayList<String> goods;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editName = findViewById(R.id.editName);
        editPrice = findViewById(R.id.editPrice);
        editDate = findViewById(R.id.editDate);
        buttonSend = findViewById(R.id.buttonSend);
        buttonView = findViewById(R.id.buttonView);
        textViewResult = findViewById(R.id.textViewResult);
        buttonAsk = findViewById(R.id.buttonAsk);
        videoView = findViewById(R.id.videoView);

        mydb = new DBHelper(this);

        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mydb.insertGood(editName.getText().toString(),editPrice.getText().toString(),editDate.getText().toString());

                Toast.makeText(MainActivity.this, "Dodano", Toast.LENGTH_LONG).show();
            }
        });


        buttonView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goods = mydb.getAllGoods();

                String tmp = "";
                for(String g : goods) {
                    tmp = tmp.concat(g + "\n");

                }

                textViewResult.setText(tmp);
            }
        });
    }

    public class DBHelper extends SQLiteOpenHelper {

        public static final String DATABASE_NAME = "MyDBName.db";
        public static final String GOODS_TABLE_NAME = "goods";
        public static final String GOODS_COLUMN_ID = "id";
        public static final String GOODS_COLUMN_NAME = "name";
        public static final String GOODS_COLUMN_PRICE = "price";
        public static final String GOODS_COLUMN_DATE = "date";
        private HashMap hp;

        public DBHelper(Context context) {
            super(context, DATABASE_NAME , null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            // TODO Auto-generated method stub
            db.execSQL("create table goods " +"(id integer primary key, name,price,date)");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            // TODO Auto-generated method stub
            db.execSQL("DROP TABLE IF EXISTS goods");
            onCreate(db);
        }

        public boolean insertGood (String name, String price, String date) {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put("name", name);
            contentValues.put("price", price);
            contentValues.put("date", date);
            db.insert("goods", null, contentValues);
            return true;
        }

        public Cursor getData(int id) {
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor res =  db.rawQuery( "select * from goods where id="+id+"", null );
            return res;
        }

        public int numberOfRows(){
            SQLiteDatabase db = this.getReadableDatabase();
            int numRows = (int) DatabaseUtils.queryNumEntries(db, GOODS_TABLE_NAME);
            return numRows;
        }

        /*public boolean updateContact (Integer id, String name, String phone, String email, String street,String place) {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put("name", name);
            contentValues.put("phone", phone);
            contentValues.put("email", email);
            contentValues.put("street", street);
            contentValues.put("place", place);
            db.update("contacts", contentValues, "id = ? ", new String[] { Integer.toString(id) } );
            return true;
        }

        public Integer deleteContact (Integer id) {
            SQLiteDatabase db = this.getWritableDatabase();
            return db.delete("contacts",
                    "id = ? ",
                    new String[] { Integer.toString(id) });
        }*/

        @SuppressLint("Range")
        public ArrayList<String> getAllGoods() {
            ArrayList<String> array_list = new ArrayList<String>();

            hp = new HashMap();
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor res =  db.rawQuery( "select * from goods", null );
            res.moveToFirst();

            while(res.isAfterLast() == false){
                array_list.add(res.getString(res.getColumnIndex(GOODS_COLUMN_NAME)));
                res.moveToNext();
            }
            return array_list;
        }
    }
}