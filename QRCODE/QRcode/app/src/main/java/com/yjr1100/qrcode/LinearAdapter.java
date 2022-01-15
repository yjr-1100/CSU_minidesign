package com.yjr1100.qrcode;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class LinearAdapter extends RecyclerView.Adapter<LinearAdapter.LinearViewHolder> {
    private Context mContext;
    private OnItemClickListener mlistener;

    private JSONArray mlist;
    private JSONArray senderlist;
    public LinearAdapter(Context context,OnItemClickListener listener,JSONArray list,JSONArray senders){
        this.mContext = context;
        this.mlistener = listener;
        this.mlist = list;
        this.senderlist = senders;
    }
    @NonNull
    @Override
    public LinearAdapter.LinearViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new LinearViewHolder(LayoutInflater.from(mContext).inflate(R.layout.layout_linear_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull LinearAdapter.LinearViewHolder holder, final int position) {
        try {
            JSONObject jsonObject = (JSONObject)mlist.get(position);
            holder.itemsenddate.setText("寄送时间："+jsonObject.getString("sendtime"));
            holder.itemname.setText("物品名称："+jsonObject.getString("goodname"));
            System.out.println("--"+jsonObject+"------");
            if(jsonObject.getString("s_id").equals("null")){
                holder.itemsernder.setText("暂未分配快递员");
                holder.imageView2.setImageResource(R.drawable.defaultqrcode);
            }else if(senderlist!=null){
                for(int i=0;i<senderlist.length();i++){
                    JSONObject j = new JSONObject(senderlist.get(i).toString()) ;
                    if(j.getInt("uid") == jsonObject.getInt("s_id")){
                        holder.itemsernder.setText("快递员为："+j.getString("username"));
                        Glide.with(mContext).load(mContext.getString(R.string.ip)+"getqrcodeservlet?goodesinfo="+jsonObject.toString()).into(holder.imageView2);
                    }
                }

            }else{
                holder.itemsernder.setText("快递员为：");

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mlistener.onClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mlist.length();
    }
    class LinearViewHolder extends RecyclerView.ViewHolder{
        private TextView itemsenddate;
        private TextView itemname;
        private TextView itemsernder;
        private ImageView imageView2;
        public LinearViewHolder(@NonNull View itemView) {
            super(itemView);
            itemsenddate = itemView.findViewById(R.id.itemsenddate);
            itemname = itemView.findViewById(R.id.itemname);
            itemsernder = itemView.findViewById(R.id.itemsender);
            imageView2 = itemView.findViewById(R.id.imageView2);
        }
    }

    public interface OnItemClickListener{
        void onClick(int pos);
    }
}
