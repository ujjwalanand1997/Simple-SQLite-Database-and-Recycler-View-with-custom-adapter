package ujjwal.anand.globallogic.admin.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ujjwal.anand.globallogic.admin.R;
import ujjwal.anand.globallogic.admin.pojo.User;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {

    Context context;
    List<User> usersList;
    String user;

    public UserAdapter(Context context, List<User> usersList,String user) {
        this.context = context;
        this.usersList = usersList;
        this.user = user;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.user_list_layout,null);
        UserViewHolder userViewHolder = new UserViewHolder(view);
        return userViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {

        if((usersList.get(position).getName()).equals(user)){
            holder.name.setText(usersList.get(position).getName());
            holder.name.setTextColor(Color.RED);
        }else{
            holder.name.setText(usersList.get(position).getName());
        }

        holder.remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

    @Override
    public int getItemCount() {
        return usersList.size();
    }

    class UserViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        ImageView remove;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.user);
            remove = itemView.findViewById(R.id.remove);
        }
    }
}
