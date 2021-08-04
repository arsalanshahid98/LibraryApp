package com.example.libraryapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class AllBooksActivity extends AppCompatActivity {

    private RecyclerView booksrecyclerview;
    private BookRecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_books);

        adapter = new BookRecyclerViewAdapter(this, "allBooks");
        booksrecyclerview = findViewById(R.id.booksrecyclerview);
        booksrecyclerview.setAdapter(adapter);
        booksrecyclerview.setLayoutManager(new LinearLayoutManager(this));
        adapter.setBooks(utils.getInstance(this).getAllBooks());
    }
}