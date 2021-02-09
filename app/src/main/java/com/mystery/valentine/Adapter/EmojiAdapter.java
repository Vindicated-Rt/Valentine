package com.mystery.valentine.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.mystery.valentine.Bean.Emoji_Info;
import com.mystery.valentine.R;

import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by Vindicated-Rt
 * 2020-02-07 09:44
 */
public class EmojiAdapter extends RecyclerView.Adapter<EmojiAdapter.ViewHolder> {

    private Emoji_Info emoji_info;

    public EmojiAdapter() {
        this.emoji_info = new Emoji_Info();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.emoji_chat, parent, false);
        return new EmojiAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (emoji_info.getIsMale_list().get(position)) {
            holder.meIcIv.setVisibility(View.VISIBLE);
            holder.chatMIv.setVisibility(View.VISIBLE);
            int view1 = emoji_info.getEmoji_M_list().get(position)[2];
            if (view1 != 0){
                holder.emojiS1Iv.setImageResource(view1);
            }else {
                holder.emojiS1Iv.setImageResource(0);
            }
            int view2 = emoji_info.getEmoji_M_list().get(position)[1];
            if (view2 != 0){
                holder.emojiS2Iv.setImageResource(view2);
            }else {
                holder.emojiS2Iv.setImageResource(0);
            }
            int view3 = emoji_info.getEmoji_M_list().get(position)[0];
            if (view3 != 0){
                holder.emojiS3Iv.setImageResource(view3);
            }else {
                holder.emojiS3Iv.setImageResource(0);
            }
            holder.emojiS4Iv.setImageResource(0);
            holder.emojiS5Iv.setImageResource(0);
            holder.emojiS6Iv.setImageResource(0);
            holder.sheIcIv.setVisibility(View.GONE);
            holder.chatFIv.setVisibility(View.GONE);
        }else {
            holder.meIcIv.setVisibility(View.GONE);
            holder.chatMIv.setVisibility(View.GONE);
            holder.sheIcIv.setVisibility(View.VISIBLE);
            holder.chatFIv.setVisibility(View.VISIBLE);
            int view4 = emoji_info.getEmoji_F_list().get(position)[0];
            holder.emojiS4Iv.setImageResource(view4);
            int view5 = emoji_info.getEmoji_F_list().get(position)[1];
            holder.emojiS5Iv.setImageResource(view5);
            int view6 = emoji_info.getEmoji_F_list().get(position)[2];
            holder.emojiS6Iv.setImageResource(view6);
            holder.emojiS1Iv.setImageResource(0);
            holder.emojiS2Iv.setImageResource(0);
            holder.emojiS3Iv.setImageResource(0);
        }
    }

    public void addView(boolean isMale,int[] views){
        this.emoji_info.addIsMale(isMale);
        this.emoji_info.addEmoji_M(views);
        this.emoji_info.addEmoji_F();
        notifyItemInserted(emoji_info.getSize());
    }

    @Override
    public int getItemCount() {
        return emoji_info.getSize();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView meIcIv;
        private ImageView chatMIv;
        private ImageView sheIcIv;
        private ImageView chatFIv;
        private ImageView emojiS1Iv;
        private ImageView emojiS2Iv;
        private ImageView emojiS3Iv;
        private ImageView emojiS4Iv;
        private ImageView emojiS5Iv;
        private ImageView emojiS6Iv;

        public ViewHolder(View itemView) {
            super(itemView);
            meIcIv = itemView.findViewById(R.id.emoji_me_ic_iv);
            chatMIv = itemView.findViewById(R.id.emoji_chat_m_iv);
            sheIcIv = itemView.findViewById(R.id.emoji_she_ic_iv);
            chatFIv = itemView.findViewById(R.id.emoji_chat_f_iv);

            emojiS1Iv = itemView.findViewById(R.id.emoji_s_1_iv);
            emojiS2Iv = itemView.findViewById(R.id.emoji_s_2_iv);
            emojiS3Iv = itemView.findViewById(R.id.emoji_s_3_iv);
            emojiS4Iv = itemView.findViewById(R.id.emoji_s_4_iv);
            emojiS5Iv = itemView.findViewById(R.id.emoji_s_5_iv);
            emojiS6Iv = itemView.findViewById(R.id.emoji_s_6_iv);
        }
    }
}
