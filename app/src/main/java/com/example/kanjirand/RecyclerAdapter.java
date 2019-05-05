package com.example.kanjirand;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    ArrayList<String> id = new ArrayList<>();
    ArrayList<String> title = new ArrayList<>();
    ArrayList<String> description = new ArrayList<>();
    Context context;

    public RecyclerAdapter(Context applicationContext, ArrayList<String> id, ArrayList<String> title, ArrayList<String> description) {

        this.context = applicationContext;
        this.id = id;
        this.title = title;
        this.description = description;

    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView idTextView;
        public TextView titleTextView;
        public TextView descriptionTextView;
        int currentCardPosition;

        Context mContext;


         ViewHolder(final View itemView) {
            super(itemView);

            idTextView = itemView.findViewById(R.id.no);
            titleTextView = itemView.findViewById(R.id.title);
            descriptionTextView = itemView.findViewById(R.id.description);
            mContext=context;
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    //Toast.makeText(context, "Что-то нажали"+Integer.toString(currentCardPosition), Toast.LENGTH_SHORT).show();
                    //((MainActivity)mContext).showSnackbar(currentCardPosition);

                    if(mContext instanceof KanjiMenu){
                        ((KanjiMenu)mContext).onClicker(itemView,currentCardPosition);
                    }

                }
            });
        }

    }

    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.kanji_menu_element, parent, false);

        RecyclerAdapter.ViewHolder viewHolder = new RecyclerAdapter.ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.idTextView.setText(id.get(position));
        holder.titleTextView.setText(title.get(position));
        holder.descriptionTextView.setText(description.get(position));
        holder.currentCardPosition = position;

    }

    @Override
    public int getItemCount() {
        return id.size();
    }

}
