package com.yjr1100.qrcode;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavArgument;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link itemslist.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link itemslist#newInstance} factory method to
 * create an instance of this fragment.
 */
public class itemslist extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public itemslist() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment itemslist.
     */
    // TODO: Rename and change types and number of parameters
    public static itemslist newInstance(String param1, String param2) {
        itemslist fragment = new itemslist();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }
    JSONObject userinfo;
    private JSONObject jsondata;
    Handler handler;
    RecyclerView mRvMain;
    JSONArray goodeslist;
    JSONArray senderlist;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Map<String, NavArgument> map = NavHostFragment.findNavController(this).getGraph().getArguments();
        NavArgument navArgument = map.get("videoCourseId");
        Bundle videoCourseId = (Bundle)navArgument.getDefaultValue();
        System.out.println("uiuiuiuuuuuuuuuuuuuuu");
        System.out.println(videoCourseId.getString("userinfo"));
        try {
            userinfo = new JSONObject(videoCourseId.getString("userinfo"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        /*发送请求得到用户寄送的东西*/
        final Handler uiHandler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what){
                    case 1:
                        break;
                }
            }
        };

        Thread subthread = new Thread(new Runnable(){
            @Override
            public void run() {
                try {
                    submit(userinfo.getInt("uid"));
                    Message msg = new Message();
                    msg.what = 1;
                    uiHandler.sendMessage(msg);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        subthread.start();
        while (subthread.isAlive() ==true){

        }

        return inflater.inflate(R.layout.fragment_itemslist, container, false);
    }
    public void submit(int uid) {
        try {
            String path = getString(R.string.ip)+"usersendgoodes";
            URL url = new URL(path);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("ser-Agent", "Fiddler"); conn.setRequestProperty("Content-Type", "application/json"); conn.setConnectTimeout(5 * 1000);
            // 包装并上传数据
            OutputStream outputStream = conn.getOutputStream();
            jsondata = new JSONObject();
            jsondata.put("uid",String.valueOf(uid));
            outputStream.write(URLEncoder.encode(jsondata.toString(), "UTF-8").getBytes());
            System.out.println(jsondata.toString());
            // 如果请求响应码是200，则表示成功
            System.out.println(conn.getResponseCode());
            if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                //获取服务器上的数据
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
                try {
                    //解码
                    String jsonStr = URLDecoder.decode(in.readLine(), "UTF-8");
                    final JSONObject objectT = new JSONObject(jsonStr);
                    String msg = objectT.getString("msg");
                    Log.i("objectT", objectT.getString("msg"));
                    if(msg.equals("查询成功")){
                        /*查询成功了，要修改页面的东西*/
                        Looper.prepare();
                        Toast.makeText(getActivity(), "查询成功", Toast.LENGTH_SHORT).show();
                        goodeslist = objectT.getJSONArray("goodes");
                        senderlist = objectT.getJSONArray("senders");
                    }else{
                        goodeslist = new JSONArray();
                        senderlist = new JSONArray();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                in.close();
                conn.disconnect();
            }else{
                Looper.prepare();
                Toast.makeText(getActivity(), "寄件加载失败请重试", Toast.LENGTH_SHORT).show();
                Looper.loop();
            }
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ProtocolException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }catch (SocketTimeoutException e) {

            handler.post(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(getActivity(), "连接超时", Toast.LENGTH_SHORT).show();
                    return;
                }
            });
        }catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void updateshow(){


    }
    Intent intent = new Intent();
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mRvMain = getView().findViewById(R.id.usergoodesRvMain);
        mRvMain.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRvMain.addItemDecoration(new MyDecoration());
        mRvMain.setAdapter(new LinearAdapter(getActivity(), new LinearAdapter.OnItemClickListener() {
            @Override
            public void onClick(int pos) {
                intent.setClass(getActivity(),showqrcode.class);
                try {
                    String goodeinfo = goodeslist.get(pos).toString();
                    intent.putExtra("goodesinfo", goodeinfo);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                startActivity(intent);
            }
        },goodeslist,senderlist));

        /*点击添加货物*/
        getView().findViewById(R.id.createnewitem).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*拿到登录的信息*/
                Bundle bundle = new Bundle();
                try {
                    bundle.putInt("u_id",userinfo.getInt("uid"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                /*查询用户的邮寄物品*/
                /*动态创建并显示*/
                NavController controller = Navigation.findNavController(view);
                /*到编辑信息的页面*/
                System.out.println("-097654567856789");
                controller.navigate(R.id.action_itemslist_to_edititem,bundle);
            }
        });

    }
    class MyDecoration extends RecyclerView.ItemDecoration{
        @Override
        public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
            super.getItemOffsets(outRect, view, parent, state);
            outRect.set(0,0,0,getResources().getDimensionPixelOffset(R.dimen.dividerHeight));
        }
    }
    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
