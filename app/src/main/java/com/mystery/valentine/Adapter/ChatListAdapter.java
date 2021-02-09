package com.mystery.valentine.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import com.mystery.valentine.R;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by Vindicated-Rt
 * 2020-02-05 16:39
 */
public class ChatListAdapter extends RecyclerView.Adapter<ChatListAdapter.ViewHolder> {

    private final List<Boolean> list;
    private TranslateAnimation itemFAnimation;
    private TranslateAnimation itemMAnimation;

    public ChatListAdapter(){
        list = new ArrayList<>();
        list.add(false);
}

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_style, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (list.get(position)){
            holder.meIcIv.setVisibility(View.VISIBLE);
            holder.chatMIv.setVisibility(View.VISIBLE);
            holder.sheIcIv.setVisibility(View.GONE);
            holder.chatFIv.setVisibility(View.GONE);
        }else {
            holder.meIcIv.setVisibility(View.GONE);
            holder.chatMIv.setVisibility(View.GONE);
            holder.sheIcIv.setVisibility(View.VISIBLE);
            holder.chatFIv.setVisibility(View.VISIBLE);
        }
    }

    /*聊天记录增加*/
    public void addData(Boolean isMale){
        this.list.add(isMale);
        notifyItemInserted(list.size());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final ImageView meIcIv;
        private final ImageView chatMIv;
        private final ImageView sheIcIv;
        private final ImageView chatFIv;

        public ViewHolder(View itemView) {
            super(itemView);
            meIcIv = itemView.findViewById(R.id.me_ic_iv);
            chatMIv = itemView.findViewById(R.id.chat_m_iv);
            sheIcIv = itemView.findViewById(R.id.she_ic_iv);
            chatFIv = itemView.findViewById(R.id.chat_f_iv);
        }
    }
}
