package MyAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.zeeshblogapp.MyInterface;
import com.example.zeeshblogapp.R;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

import Model.Data;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    MyInterface myInterface;
    List<Data> list;
    public MyAdapter(List<Data> list, MyInterface myInterface){
            this.list = list;
            this.myInterface = myInterface;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.costum_view, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        final Data data = list.get(position);
        holder.trip.setText(data.getTrip());
        holder.stroy.setText(data.getStory());
        Glide.with(holder.itemView).load(data.getImage()).centerCrop().into(holder.image);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // we will use interface here instead of bekar trika
               myInterface.onClickInterface(data);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
            TextView trip, stroy;
            ImageView image;
        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            trip = itemView.findViewById(R.id.tripID);
            stroy = itemView.findViewById(R.id.storyID);
            image = itemView.findViewById(R.id.imageID);


//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                   int position =  getAdapterPosition();
//                   Data myData = list.get(position);
//                   Snackbar.make(itemView, myData.getTrip(), Snackbar.LENGTH_SHORT).show();
//                }
//            });
        }
    }
}
