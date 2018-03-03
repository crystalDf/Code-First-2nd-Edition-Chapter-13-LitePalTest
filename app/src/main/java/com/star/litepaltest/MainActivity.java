package com.star.litepaltest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;

import org.litepal.crud.DataSupport;
import org.litepal.tablemanager.Connector;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity";

    private Button mCreateDatabaseButton;
    private Button mInsertDataButton;
    private Button mUpdateDataButton;
    private Button mDeleteDataButton;
    private Button mQueryDataButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mCreateDatabaseButton = findViewById(R.id.create_database);
        mCreateDatabaseButton.setOnClickListener(v -> Connector.getDatabase());

        mInsertDataButton = findViewById(R.id.insert_data);
        mInsertDataButton.setOnClickListener(v -> {
            Book book = new Book();
            book.setName("The Da Vinci Code");
            book.setAuthor("Dan Brown");
            book.setPages(454);
            book.setPrice(16.96);
            book.setPress("Unknown");
            book.save();
        });

        mUpdateDataButton = findViewById(R.id.update_data);
        mUpdateDataButton.setOnClickListener(v -> {
//                Book book = new Book();
//                book.setName("The Lost Symbol");
//                book.setAuthor("Dan Brown");
//                book.setPages(510);
//                book.setPrice(19.95);
//                book.setPress("Unknown");
//                book.save();
//
//                book.setPrice(10.99);
//                book.save();

            Book book = new Book();
            book.setPrice(14.95);
            book.setPress("Anchor");
            book.updateAll("name = ? and author = ?", "The Lost Symbol", "Dan Brown");
        });

        mDeleteDataButton = findViewById(R.id.delete_data);
        mDeleteDataButton.setOnClickListener(v -> DataSupport.deleteAll(Book.class, "price < ?", "15"));

        mQueryDataButton = findViewById(R.id.query_data);
        mQueryDataButton.setOnClickListener(v -> {
            List<Book> books = DataSupport.findAll(Book.class);
            for (Book book : books) {
                Log.d(TAG, "book name is " + book.getName());
                Log.d(TAG, "book author is " + book.getAuthor());
                Log.d(TAG, "book pages is " + book.getPages());
                Log.d(TAG, "book price is " + book.getPrice());
                Log.d(TAG, "book press is " + book.getPress());
            }
        });
    }
}
