package com.example.shriyachhabra.myapplication.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shriyachhabra.myapplication.Modals.Lexeme;
import com.example.shriyachhabra.myapplication.R;

import java.util.ArrayList;

public class LexemeAdapter extends RecyclerView.Adapter<LexemeAdapter.LexViewHolder> {


    private Context context;
    private ArrayList<Lexeme> lex;

    public LexemeAdapter(Context context, ArrayList<Lexeme> lex) {
        this.context = context;
        this.lex = lex;
    }

    @NonNull
    @Override
    public LexViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater li= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = li.inflate(i,viewGroup,false);
        return new LexViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull LexViewHolder lexViewHolder, int pos) {
       Lexeme item= lex.get(pos);
       lexViewHolder.r1.setImageResource(item.getRow1());
       lexViewHolder.r2.setImageResource(item.getRow2());
       lexViewHolder.r3.setImageResource(item.getRow3());
       lexViewHolder.c.setText(item.getCharacter()+"");
    }


    @Override
    public int getItemViewType(int position) {
        return R.layout.list_item;
    }



    @Override
    public int getItemCount() {
        return lex.size();
    }

    static class LexViewHolder extends RecyclerView.ViewHolder {
    ImageView r1,r2,r3;
    TextView c;

    public LexViewHolder(View item){
        super(item);
        r1=item.findViewById(R.id.line1);
        r2=item.findViewById(R.id.line2);
        r3=item.findViewById(R.id.line3);
        c=item.findViewById(R.id.ch);
    }

}

}
