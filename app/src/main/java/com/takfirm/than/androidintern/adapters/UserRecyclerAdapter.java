package com.takfirm.than.androidintern.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.takfirm.than.androidintern.R;
import com.takfirm.than.androidintern.models.User;
import com.takfirm.than.androidintern.viewholders.UserViewHolder;

import java.util.List;

public class UserRecyclerAdapter extends RecyclerView.Adapter<UserViewHolder> {
    private List<User> mUsers;
    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private OnItemClickListener mOnItemClickListener;

    public UserRecyclerAdapter(List<User> users, Context context) {
        mUsers = users;
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = mLayoutInflater.inflate(R.layout.layout_user_item,viewGroup,false);
        return new UserViewHolder(view,mOnItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder userViewHolder, int i) {
        userViewHolder.mUserNameTv.setText(mUsers.get(i).getName());
    }

    @Override
    public int getItemCount() {
        return mUsers.size();
    }

    public interface OnItemClickListener{
        void onItemClick(View view, int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.mOnItemClickListener = onItemClickListener;
    }
}
