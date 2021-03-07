package com.example.catfacts3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CatFactAdapter extends RecyclerView.Adapter<CatFactAdapter.CatFactViewHolder> {
    CatFact[] mCatFacts;
    CatFactItemListener mListener;
    public CatFactAdapter(int numberOfFacts, CatFactItemListener listener) {
        mCatFacts = new CatFact[numberOfFacts];
        mListener = listener;
    }
    @NonNull
    @Override
    public CatFactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int itemId = R.layout.cat_fact_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        View inflatedView = inflater.inflate(itemId, parent, false);
        return new CatFactViewHolder(inflatedView);
    }

    @Override
    public void onBindViewHolder(@NonNull CatFactViewHolder holder, int position) {
        holder.bind(mCatFacts[position].getText());
        holder.itemView.setOnClickListener(v -> {
            mListener.onClick(mCatFacts[position].getText(), mCatFacts[position].getUser());
        });
    }

    @Override
    public int getItemCount() {
        if(mCatFacts == null) return 0;
        return mCatFacts.length;
    }

    class CatFactViewHolder extends RecyclerView.ViewHolder {
        TextView mCatFactItemText;
        public CatFactViewHolder(@NonNull View itemView) {
            super(itemView);
            mCatFactItemText = itemView.findViewById(R.id.cat_fact_item_text);

        }
        void bind(String text) {
            mCatFactItemText.setText(text);
        }
    }

    public void setCatFacts(List<CatFact> data) {
        CatFact[] newCatFacts = new CatFact[getItemCount()];
        for(int i = 0; i < data.size() && i < 50; i++) {
            newCatFacts[i] = data.get(i);
        }
        mCatFacts = newCatFacts;
    }
}
interface CatFactItemListener {
    void onClick(String catFact, String author);
}
