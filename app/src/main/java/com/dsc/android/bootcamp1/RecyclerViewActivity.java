package com.dsc.android.bootcamp1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecyclerViewActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;
    private List<RecyclerViewData> UserList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        recyclerView = findViewById(R.id.recycler_view);
        //createMockList();
        apicall();
        setUpRecyclerView();

    }

    private void apicall() {
        ApiServices apiServices = AppClient.getInstance().createService(ApiServices.class);
        Call<UserWrapper> call = apiServices.getUserList();
        call.enqueue(new Callback<UserWrapper>() {
            @Override
            public void onResponse(Call<UserWrapper> call, Response<UserWrapper> response) {
                if (response.isSuccessful())
                {
                    if(response.body() != null)
                    {
                        UserList.addAll(response.body().getRecyclerViewData());
                    }
                }
            }

            @Override
            public void onFailure(Call<UserWrapper> call, Throwable t) {

            }
        });
    }

   /* private void createMockList() {
        RecyclerViewData data = new RecyclerViewData("https://bit.ly/2NT7svr", "9935864801", "Shreyansh Sharma");
        mockDataList.add(data);

        data = new RecyclerViewData("https://bit.ly/2NT7svr", "16464664646", "Shreyash");
        mockDataList.add(data);

        data = new RecyclerViewData("https://bit.ly/2NT7svr", "64875648355", "rishi");
        mockDataList.add(data);

        data = new RecyclerViewData("https://bit.ly/2NT7svr", "4499496264964", "John");
        mockDataList.add(data);

        data = new RecyclerViewData("https://bit.ly/2NT7svr", "4661651616", "January");
        mockDataList.add(data);

        data = new RecyclerViewData("https://bit.ly/2NT7svr", "66466445456", "February");
        mockDataList.add(data);

    }*/

    private void setUpRecyclerView() {
        recyclerViewAdapter = new RecyclerViewAdapter(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerViewAdapter.setRecyclerViewDataList(UserList);
        recyclerViewAdapter.notifyDataSetChanged();


    }

}
