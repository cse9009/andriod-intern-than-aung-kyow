package com.takfirm.than.androidintern.viewholders;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.takfirm.than.androidintern.R;
import com.takfirm.than.androidintern.adapters.UserRecyclerAdapter;

public class UserViewHolder extends RecyclerView.ViewHolder {
    public TextView mUserNameTv;
    private UserRecyclerAdapter.OnItemClickListener mOnItemClickListener;

    public UserViewHolder(@NonNull View itemView, UserRecyclerAdapter.OnItemClickListener onItemClickListener) {
        super(itemView);
        mUserNameTv = itemView.findViewById(R.id.user_name_tv);

        this.mOnItemClickListener = onItemClickListener;

        mUserNameTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemClickListener.onItemClick(v,getAdapterPosition());
            }
        });
    }

}
