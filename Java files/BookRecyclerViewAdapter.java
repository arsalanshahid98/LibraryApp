package com.example.libraryapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.util.Util;

import java.util.ArrayList;

import static com.example.libraryapp.BookActivity.BOOK_ID_KEY;

public class BookRecyclerViewAdapter extends RecyclerView.Adapter<BookRecyclerViewAdapter.ViewHolder> {

    private static final String TAG = "BookRecyclerViewAdapter";
    private ArrayList<Book> books = new ArrayList<>();
    private Context mContext;
    private String parentActivity;

    public BookRecyclerViewAdapter(Context mContext, String parentActivity) {
        this.mContext = mContext;
        this.parentActivity = parentActivity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_book, parent, false);
        return new ViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: Called");
        holder.textname.setText(books.get(position).getName());
        Glide.with(mContext)
                .asBitmap()
                .load(books.get(position).getImageUrl())
                .into(holder.imagebook);

        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, BookActivity.class);
                intent.putExtra(BOOK_ID_KEY, books.get(position).getId());
                mContext.startActivity(intent);
            }
        });

        holder.txtauthor.setText(books.get(position).getAuthor());
        holder.txtshortdescription.setText(books.get(position).getShortDesc());

        if (books.get(position).getIsExpanded()) {
            TransitionManager.beginDelayedTransition(holder.parent);
            holder.expandedrelativelayout.setVisibility(View.VISIBLE);
            holder.btndownarrow.setVisibility(View.GONE);

            if (parentActivity.equals("allBooks")) {
                holder.btnDelete.setVisibility(View.GONE);
            }
            else if(parentActivity.equals("alreadyRead")) {
                holder.btnDelete.setVisibility(View.VISIBLE);
                holder.btnDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                        builder.setMessage("Are you sure you want to delete this book " + books.get(position).getName() + "?");
                        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                if (utils.getInstance(mContext).removeFromAlreadyRead(books.get(position))) {
                                    Toast.makeText(mContext, "Book successfully removed", Toast.LENGTH_SHORT).show();
                                    notifyDataSetChanged();
                                }
                            }
                        });
                        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        });

                        AlertDialog alert = builder.create();
                        alert.show();
                        Button nbutton = alert.getButton(DialogInterface.BUTTON_NEGATIVE);
                        //Set negative button background
                        nbutton.setBackgroundColor(Color.parseColor("#FFFCB9B7"));
                        //Set negative button text color
                        nbutton.setTextColor(Color.RED);
                        Button pbutton = alert.getButton(DialogInterface.BUTTON_POSITIVE);
                        //Set positive button background
                        pbutton.setBackgroundColor(Color.parseColor("#FFD9E9FF"));
                        //Set positive button text color
                        pbutton.setTextColor(Color.BLUE);

                    }
                });
            }
            else if(parentActivity.equals("wantToRead")) {
                holder.btnDelete.setVisibility(View.VISIBLE);
                holder.btnDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                        builder.setMessage("Are you sure you want to delete this book " + books.get(position).getName() + "?");
                        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                if (utils.getInstance(mContext).removeFromWantToRead(books.get(position))) {
                                    Toast.makeText(mContext, "Book successfully removed", Toast.LENGTH_SHORT).show();
                                    notifyDataSetChanged();
                                }
                            }
                        });
                        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        });

                        AlertDialog alert = builder.create();
                        alert.show();
                        Button nbutton = alert.getButton(DialogInterface.BUTTON_NEGATIVE);
                        //Set negative button background
                        nbutton.setBackgroundColor(Color.parseColor("#FFFCB9B7"));
                        //Set negative button text color
                        nbutton.setTextColor(Color.RED);
                        Button pbutton = alert.getButton(DialogInterface.BUTTON_POSITIVE);
                        //Set positive button background
                        pbutton.setBackgroundColor(Color.parseColor("#FFD9E9FF"));
                        //Set positive button text color
                        pbutton.setTextColor(Color.BLUE);

                    }
                });
            }
            else if(parentActivity.equals("currentlyReading")) {
                holder.btnDelete.setVisibility(View.VISIBLE);
                holder.btnDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                        builder.setMessage("Are you sure you want to delete this book " + books.get(position).getName() + "?");
                        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                if (utils.getInstance(mContext).removeFromCurrentlyReading(books.get(position))) {
                                    Toast.makeText(mContext, "Book successfully removed", Toast.LENGTH_SHORT).show();
                                    notifyDataSetChanged();
                                }
                            }
                        });
                        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        });

                        AlertDialog alert = builder.create();
                        alert.show();
                        Button nbutton = alert.getButton(DialogInterface.BUTTON_NEGATIVE);
                        //Set negative button background
                        nbutton.setBackgroundColor(Color.parseColor("#FFFCB9B7"));
                        //Set negative button text color
                        nbutton.setTextColor(Color.RED);
                        Button pbutton = alert.getButton(DialogInterface.BUTTON_POSITIVE);
                        //Set positive button background
                        pbutton.setBackgroundColor(Color.parseColor("#FFD9E9FF"));
                        //Set positive button text color
                        pbutton.setTextColor(Color.BLUE);

                    }
                });
            }
            else {
                holder.btnDelete.setVisibility(View.VISIBLE);
                holder.btnDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                        builder.setMessage("Are you sure you want to delete this book " + books.get(position).getName() + "?");
                        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                if (utils.getInstance(mContext).removeFromFavorites(books.get(position))) {
                                    Toast.makeText(mContext, "Book successfully removed", Toast.LENGTH_SHORT).show();
                                    notifyDataSetChanged();
                                }
                            }
                        });
                        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        });

                        AlertDialog alert = builder.create();
                        alert.show();
                        Button nbutton = alert.getButton(DialogInterface.BUTTON_NEGATIVE);
                        //Set negative button background
                        nbutton.setBackgroundColor(Color.parseColor("#FFFCB9B7"));
                        //Set negative button text color
                        nbutton.setTextColor(Color.RED);
                        Button pbutton = alert.getButton(DialogInterface.BUTTON_POSITIVE);
                        //Set positive button background
                        pbutton.setBackgroundColor(Color.parseColor("#FFD9E9FF"));
                        //Set positive button text color
                        pbutton.setTextColor(Color.BLUE);

                    }
                });
            }

        }
        else {
            TransitionManager.beginDelayedTransition(holder.parent);
            holder.expandedrelativelayout.setVisibility(View.GONE);
            holder.btndownarrow.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    public void setBooks(ArrayList<Book> books) {
        this.books = books;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private CardView parent;
        private ImageView imagebook;
        private TextView textname, txtauthor, txtshortdescription;
        private ImageView btndownarrow, btnuparrow;
        private RelativeLayout expandedrelativelayout;
        private TextView btnDelete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            parent = itemView.findViewById(R.id.parent);
            imagebook = itemView.findViewById(R.id.imagebook);
            textname = itemView.findViewById(R.id.textname);
            btndownarrow = itemView.findViewById(R.id.btndownarrow);
            btnuparrow = itemView.findViewById(R.id.btnuparrow);
            expandedrelativelayout = itemView.findViewById(R.id.expandedrelativelayout);
            txtauthor = itemView.findViewById(R.id.txtauthor);
            txtshortdescription = itemView.findViewById(R.id.txtshortdescription);
            btnDelete = itemView.findViewById(R.id.btnDelete);

            btndownarrow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Book book  = books.get(getAdapterPosition());
                    book.setExpanded(!book.getIsExpanded());
                    notifyItemChanged(getAdapterPosition());
                }
            });

            btnuparrow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Book book  = books.get(getAdapterPosition());
                    book.setExpanded(!book.getIsExpanded());
                    notifyItemChanged(getAdapterPosition());
                }
            });
        }
    }
}
