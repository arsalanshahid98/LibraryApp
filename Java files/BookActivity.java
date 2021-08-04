package com.example.libraryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class BookActivity extends AppCompatActivity {

    public static final String BOOK_ID_KEY = "bookId";

    private ImageView bookimage;

    private Button favorites,
            addtowishlist,
            addtoreadinglist,
            alreadyread;

    private TextView authorname,
            pagenumber,
            bookname,
            description,
            longdescription;

    public void initViews() {
        //image
        bookimage = findViewById(R.id.bookimage);

        //buttons
        favorites = findViewById(R.id.favorites);
        addtowishlist = findViewById(R.id.addtowishlist);
        addtoreadinglist = findViewById(R.id.addtoreadinglist);
        alreadyread = findViewById(R.id.alreadyread);

        //text views
        alreadyread = findViewById(R.id.alreadyread);
        authorname = findViewById(R.id.authorname);
        bookname = findViewById(R.id.bookname);
        pagenumber = findViewById(R.id.pagenumber);
        longdescription = findViewById(R.id.longdescription);
        description = findViewById(R.id.description);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);
        initViews();

        Intent intent = getIntent();
        if (null != intent) {
            int bookId = intent.getIntExtra(BOOK_ID_KEY, -1);

            if (bookId != -1) {
                Book incomingBook = utils.getInstance(this).getBookById(bookId);

                if (null != incomingBook) {
                    setData(incomingBook);

                    handleAlreadyRead(incomingBook);
                    handleWantToReadBooks(incomingBook);
                    handleCurrentlyReadingBooks(incomingBook);
                    handleFavoriteBooks(incomingBook);
                }
            }
        }
    }

    private void handleFavoriteBooks(final Book book) {
        ArrayList<Book> favoriteBooks = utils.getInstance(this).getFavoriteBook();

        boolean existsInFavoriteBooks = false;

        for (Book b: favoriteBooks) {
            if (b.getId() == book.getId()) {
                existsInFavoriteBooks = true;
            }
        }

        if (existsInFavoriteBooks) {
            favorites.setEnabled(false);
        }
        else {
            favorites.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (utils.getInstance(BookActivity.this).addToFavorite(book)) {
                        Toast.makeText(BookActivity.this,
                                "Book Added",
                                Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(BookActivity.this,
                                FavoriteBook.class);
                        startActivity(intent);
                    }
                    else {
                        Toast.makeText(BookActivity.this,
                                "Couldn't add book, please try again.",
                                Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void handleCurrentlyReadingBooks(final Book book) {
        ArrayList<Book> currentlyReadingBooks = utils.getInstance(this).getCurrentlyReadingBook();

        boolean existInCurrentlyReadingBooks = false;

        for (Book b: currentlyReadingBooks) {
            if (b.getId() == book.getId()) {
                existInCurrentlyReadingBooks = true;
            }
        }

        if (existInCurrentlyReadingBooks) {
            addtoreadinglist.setEnabled(false);
        }
        else {
            addtoreadinglist.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (utils.getInstance(BookActivity.this).addToCurrentlyReading(book)) {
                        Toast.makeText(BookActivity.this,
                                "Book Added",
                                Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(BookActivity.this,
                                CurrentlyReadingActivity.class);
                        startActivity(intent);
                    }
                    else {
                        Toast.makeText(BookActivity.this,
                                "Couldn't add book, please try again.",
                                Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void handleWantToReadBooks(final Book book) {
        ArrayList<Book> wantToReadBooks = utils.getInstance(this).getWantToReadBooks();

        boolean existInWantToReadBooks = false;

        for (Book b: wantToReadBooks) {
            if (b.getId() == book.getId()) {
                existInWantToReadBooks = true;
            }
        }

        if (existInWantToReadBooks) {
            addtowishlist.setEnabled(false);
        }
        else {
            addtowishlist.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (utils.getInstance(BookActivity.this).addWantToRead(book)) {
                        Toast.makeText(BookActivity.this,
                                "Book Added",
                                Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(BookActivity.this,
                                WantToReadActivity.class);
                        startActivity(intent);
                    }
                    else {
                        Toast.makeText(BookActivity.this,
                                "Couldn't add book, please try again.",
                                Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    // enable and disable the button and adding the book into already read section
    private void handleAlreadyRead(final Book book) {
        ArrayList<Book> alreadyReadBooks = utils.getInstance(this).getAlreadyReadBooks();

        boolean existInAlreadyReadBooks = false;

        for (Book b: alreadyReadBooks) {
            if (b.getId() == book.getId()) {
                existInAlreadyReadBooks = true;
            }
        }

        if (existInAlreadyReadBooks) {
            alreadyread.setEnabled(false);
        }
        else {
            alreadyread.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (utils.getInstance(BookActivity.this).addToAlreadyRead(book)) {
                        Toast.makeText(BookActivity.this,
                                "Book Added",
                                Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(BookActivity.this,
                                AlreadyReadBookActivity.class);
                        startActivity(intent);
                    }
                    else {
                        Toast.makeText(BookActivity.this,
                                "Couldn't add book, please try again.",
                                Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void setData(Book book) {
        bookname.setText(book.getName());
        authorname.setText(book.getAuthor());
        pagenumber.setText(String.valueOf(book.getPages()));
        description.setText(book.getShortDesc());
        longdescription.setText(book.getLongDesc());
        Glide.with(this)
                .asBitmap()
                .load(book.getImageUrl())
                .into(bookimage);

    }
}