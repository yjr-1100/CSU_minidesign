package com.yjr1100.qrcode;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavArgument;
import androidx.navigation.NavController;
import androidx.navigation.NavGraph;
import androidx.navigation.Navigation;

import android.net.Uri;
import android.os.Bundle;

public class ContantActivity extends AppCompatActivity implements itemslist.OnFragmentInteractionListener,edititem.OnFragmentInteractionListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contant);
        System.out.println(getIntent().getStringExtra("userinfo"));
        Bundle bundle = new Bundle();
        bundle.putString("userinfo",getIntent().getStringExtra("userinfo"));
        NavController controller = Navigation.findNavController(this,R.id.fragment2);
        NavGraph graph = controller.getGraph();
        NavArgument argument = new NavArgument.Builder()
                .setDefaultValue(bundle)
                .build();
        graph.addArgument("videoCourseId", argument);
    }
    @Override public void onFragmentInteraction(Uri uri) {
    //留空即可
    }


}
