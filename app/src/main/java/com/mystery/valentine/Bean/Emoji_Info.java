package com.mystery.valentine.Bean;

import com.mystery.valentine.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Vindicated-Rt
 * 2020-02-07 09:47
 */
public class Emoji_Info {

    private final List<int[]> emoji_F_list;
    private final List<int[]> emoji_M_list;
    private final List<Boolean> isMale_list;
    private final int [] emoji_F_view = {
            R.mipmap.emoji_s_1,R.mipmap.emoji_s_2
            ,R.mipmap.emoji_s_3,R.mipmap.emoji_s_4};

    public Emoji_Info (){
        emoji_F_list = new ArrayList<>();
        emoji_M_list = new ArrayList<>();
        isMale_list = new ArrayList<>();
    }

    public List<int[]> getEmoji_F_list() {
        return emoji_F_list;
    }

    public void addEmoji_F() {
        int random = new Random().nextInt(4);
        int[] addArray = {
                emoji_F_view[new Random().nextInt(4)],
                emoji_F_view[new Random().nextInt(4)],
                emoji_F_view[new Random().nextInt(4)]};
        this.emoji_F_list.add(addArray);
    }

    public List<int[]> getEmoji_M_list() {
        return emoji_M_list;
    }

    public void addEmoji_M(int[] emoji_M_list) {
        this.emoji_M_list.add(emoji_M_list);
    }

    public List<Boolean> getIsMale_list() {
        return isMale_list;
    }

    public void addIsMale(Boolean isMale) {
        this.isMale_list.add(isMale);
    }

    public int getSize(){
        return isMale_list.size();
    }

}
