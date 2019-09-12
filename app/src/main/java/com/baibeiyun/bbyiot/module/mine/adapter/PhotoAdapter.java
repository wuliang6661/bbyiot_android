package com.baibeiyun.bbyiot.module.mine.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.baibeiyun.bbyiot.R;
import com.baibeiyun.bbyiot.utils.ListUtils;

import java.util.List;

/**
 * 图片
 */

public class PhotoAdapter extends BaseAdapter {

    private List<Bitmap> photoPaths;

    private Context mContext;
    private int lastPosition;
    private int maxNum = 6;

    public PhotoAdapter(Context mContext, int maxNum) {
        this.mContext = mContext;
        this.maxNum = maxNum;
    }

    public void updaData(List<Bitmap> photoPaths) {
        this.photoPaths = photoPaths;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        if (ListUtils.isEmpty(photoPaths)) {
            lastPosition = 0;
            return 1;
        }
        if (photoPaths.size() >= maxNum) {
            lastPosition = maxNum;
            return maxNum;
        }
        lastPosition = photoPaths.size();
        return photoPaths.size() + 1;

    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        convertView = View.inflate(mContext, R.layout.item_gv_photo, null);

        RelativeLayout rl_kuang = (RelativeLayout) convertView.findViewById(R.id.item_gv_photo_rl_kuang);
        ImageView sivPhoto = (ImageView) convertView.findViewById(R.id.siv_photo);
        ImageView iv_delete = (ImageView) convertView.findViewById(R.id.iv_delete);

        if (position == lastPosition) {
            //sivPhoto.setImageResource(R.mipmap.icon_photo);
            sivPhoto.setImageResource(R.mipmap.icon_photo_six);
            iv_delete.setVisibility(View.GONE);
            rl_kuang.setVisibility(View.GONE);
        } else {

            rl_kuang.setVisibility(View.VISIBLE);
            Bitmap bitmap = photoPaths.get(position);
            if (bitmap != null) {
                sivPhoto.setImageBitmap(bitmap);
            } else {
                // sivPhoto.setImageResource(R.mipmap.ic_empty_goods);
            }

            iv_delete.setVisibility(View.VISIBLE);
        }


        if (listener != null) {
            iv_delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(position);
                }
            });
        }

        return convertView;
    }

    private DeleteClickListener listener;

    public void setOnDeleteClickListener(DeleteClickListener listener) {
        this.listener = listener;
    }

    public interface DeleteClickListener {
        void onItemClick(int position);
    }
}
