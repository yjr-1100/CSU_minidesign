package com.yjr1100.qrcode;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link edititem.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link edititem#newInstance} factory method to
 * create an instance of this fragment.
 */
public class edititem extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    EditText recname,recphone,recaddress,sendername,senderphone,goodname;
    Button submit;
    public edititem() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment edititem.
     */
    // TODO: Rename and change types and number of parameters
    public static edititem newInstance(String param1, String param2) {
        edititem fragment = new edititem();
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edititem, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        recname = getView().findViewById(R.id.recname);
        recphone = getView().findViewById(R.id.recphone);
        recaddress = getView().findViewById(R.id.recaddress);
        sendername = getView().findViewById(R.id.sendername);
        senderphone = getView().findViewById(R.id.senderphone);
        goodname = getView().findViewById(R.id.goodname);
        final int u_id = getArguments().getInt("u_id");
        submit = getView().findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                final NavController controller = Navigation.findNavController(view);
                /*向后端发送数据*/
                if(TextUtils.isEmpty(recname.getText().toString())||
                        TextUtils.isEmpty(recphone.getText().toString())||
                        TextUtils.isEmpty(recaddress.getText().toString())||
                        TextUtils.isEmpty(sendername.getText().toString())||
                        TextUtils.isEmpty(senderphone.getText().toString())){
                    //判断是不是空
                    Toast.makeText(getActivity(),"请输入完整信息",Toast.LENGTH_SHORT).show();
                    return;
                }else{
                    /*向后端提交数据*/
                    final Handler uiHandler = new Handler(){
                        @Override
                        public void handleMessage(Message msg) {
                            switch (msg.what){
                                case 1:
                                    Bundle bundle = new Bundle();
                                    bundle.putString("back","返回来了");
                                    controller.navigate(R.id.action_edititem_to_itemslist,bundle);
                                    break;
                                case 0:
                                    Toast.makeText(getActivity(), "寄件失败", Toast.LENGTH_SHORT).show();
                                    break;
                            }
                        }
                    };
                    final JSONObject jsondata = new JSONObject();
                    try {
                        jsondata.put("recname",recname.getText().toString());
                        jsondata.put("recphonenum",recphone.getText().toString());
                        jsondata.put("recaddress",recaddress.getText().toString());
                        jsondata.put("sendername",sendername.getText().toString());
                        jsondata.put("senderphonenum",senderphone.getText().toString());
                        jsondata.put("goodname",goodname.getText().toString());
                        jsondata.put("u_id",u_id);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    Thread subthread = new Thread(new Runnable(){
                        @Override
                        public void run() {
                            int result = submit(jsondata,view);
                            Message msg = new Message();
                            msg.what = result;
                            uiHandler.sendMessage(msg);
                        }
                    });
                    subthread.start();
                }

            }
        });
    }
    Handler handler;
    public int submit(JSONObject jsondata,View view) {
        try {
            System.out.println("123123123123123");
            System.out.println(jsondata);
            System.out.println("123123123123123");
            String path = getString(R.string.ip)+"addgoodServlet";
            URL url = new URL(path);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("ser-Agent", "Fiddler"); conn.setRequestProperty("Content-Type", "application/json"); conn.setConnectTimeout(5 * 1000);
            // 包装并上传数据
            OutputStream outputStream = conn.getOutputStream();
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
                    if(msg.equals("插入成功")){
                        /*查询成功了，要修改页面的东西*/
                        /*提交后回去*/
                        Looper.prepare();
                        Toast.makeText(getActivity(), "插入成功", Toast.LENGTH_SHORT).show();
                        NavController controller = Navigation.findNavController(view);
                        System.out.println("0000000000000000000000000000000000");
                        controller.navigate(R.id.action_edititem_to_itemslist);
                        Looper.loop();
                        return 1;
                    }else{
                        return 0;
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    return 0;
                }finally {
                    in.close();
                    conn.disconnect();
                }

            }else{
                Looper.prepare();
                Toast.makeText(getActivity(), "请稍后再试", Toast.LENGTH_SHORT).show();
                Looper.loop();
                return 0;
            }
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return 0;
        } catch (ProtocolException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return 0;
        }catch (SocketTimeoutException e) {

            handler.post(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(getActivity(), "连接超时", Toast.LENGTH_SHORT).show();
                    return;
                }
            });
            return 0;
        }catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return 0;
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
