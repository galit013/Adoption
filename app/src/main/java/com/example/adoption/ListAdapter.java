package com.example.adoption;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

public class ListAdapter extends BaseAdapter {
    Context context;
    String names[];
    LayoutInflater inflter;

    public ListAdapter(Context applicationContext, String[] names) {
        this.context = applicationContext;
        this.names = names;
        inflter = (LayoutInflater.from(applicationContext));

    }

    @Override
    public int getCount() {
        return names.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        // get TextView of name and score
        view = inflter.inflate(R.layout.list_item, null);
        TextView name = view.findViewById(R.id.name);
        Button adopt = view.findViewById(R.id.adoptBtn);

        // display names and buttons
        name.setText(names[i]);
        adopt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                if(name.getText().toString().equals("cats")) {
                    intent = new Intent(context, Cats.class);
                } else if(name.getText().toString().equals("dogs")) {
                    intent = new Intent(context, Dogs.class);
                } else {
                    // handle other cases as needed
                    return;
                }
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });


        return view;
    }

}
