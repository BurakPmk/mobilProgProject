package com.example.test03;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PersonRecyclerAdapter extends RecyclerView.Adapter<PersonRecyclerAdapter.PersonHolder> {
    private final RecyclerViewInterface recyclerViewInterface;
    private ArrayList<Person> persons;
    public PersonRecyclerAdapter(ArrayList<Person> persons,RecyclerViewInterface recyclerViewInterface) {
        this.persons = persons;
        this.recyclerViewInterface=recyclerViewInterface;

    }

    public void setPersons(ArrayList<Person> persons) {
        this.persons = persons;
    }

    @NonNull
    @Override
    public PersonRecyclerAdapter.PersonHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.person_layout,parent,false);
        return new PersonRecyclerAdapter.PersonHolder(view,recyclerViewInterface);

    }

    @Override
    public void onBindViewHolder(@NonNull PersonHolder holder, int position)
    {
        holder.name.setText(persons.get(position).getName());
        holder.number.setText(persons.get(position).getNumber());
        if(persons.get(position).getPhoto()!=null)
        {
            holder.personPhoto.setImageBitmap(persons.get(position).getPhoto());
        }
        else
        {
            holder.personPhoto.setImageResource(R.drawable.ic_launcher_foreground);
        }
    }

    @Override
    public int getItemCount() {
        return persons.size();
    }

    public static class PersonHolder extends RecyclerView.ViewHolder
    {

        TextView name;
        TextView number;
        ImageView personPhoto;



        public PersonHolder(@NonNull View personLayout,RecyclerViewInterface recyclerViewInterface)
        {
            super((personLayout));
            name = personLayout.findViewById(R.id.name);
            number = personLayout.findViewById(R.id.number);
            personPhoto = personLayout.findViewById(R.id.person_photo);

            personLayout.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if(recyclerViewInterface!=null) {
                        int pos = getAdapterPosition();
                        if (pos != RecyclerView.NO_POSITION)
                            recyclerViewInterface.onLongItemClick(pos);
                    }
                    return true;
                }
            });

        }

    }
  }


