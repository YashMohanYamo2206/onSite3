package com.yash.onsite3;

import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements paths_adapter.OnItemClickListener {

    ArrayList<pathDetails> pathDetails;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    private paths_adapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        File file = new File((Environment.getExternalStorageDirectory()).getAbsolutePath());
        setPathDirectories(file);
    }

    private void setPathDirectories(File file) {
        File[] files = file.listFiles();
        pathDetails = new ArrayList<>();
        if (files != null) {
            for (File file1 : files) {
                if (file1.isDirectory())
                    pathDetails.add(new pathDetails(file1.getName(), file1.getAbsolutePath(), false, R.drawable.ic_closed));
                else
                    pathDetails.add(new pathDetails(file1.getName(), file1.getAbsolutePath(), true));
            }
        }
        init_recycler_view();
    }

    private void init_recycler_view() {
        recyclerView = findViewById(R.id.recycler_view);
        layoutManager = new LinearLayoutManager(this);
        adapter = new paths_adapter(pathDetails);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(this);
    }


    private void removeFileList(int position) {

        pathDetails path = pathDetails.get(position);
        ArrayList<pathDetails> pathDetails1 = new ArrayList<>();
        int k = 0;

        for (int i = position + 1; i < pathDetails.size(); i++) {
            if (pathDetails.get(i).getPath().startsWith(path.getPath())) {
                pathDetails1.add(pathDetails.get(i));
                k++;
            } else
                break;
        }
        pathDetails.removeAll(pathDetails1);
        adapter.notifyItemRangeRemoved(position + 1, k);
    }

    public void addFileList(int position) {
        pathDetails path = pathDetails.get(position);
        ArrayList<pathDetails> pathDetails1 = new ArrayList<>();

        File file1 = new File(path.getPath());
        File[] files = file1.listFiles();

        if (files != null) {
            for (File f : files) {
                if (f.isDirectory())
                    pathDetails1.add(new pathDetails(f.getName(), f.getAbsolutePath(), false, R.drawable.ic_closed));
                else
                    pathDetails1.add(new pathDetails(f.getName(), f.getAbsolutePath(), true));
            }
        }
        pathDetails.addAll(position + 1, pathDetails1);
        assert files != null;
        adapter.notifyItemRangeInserted(position + 1, files.length);
    }

    @Override
    public void onItemClick(int position) {
        pathDetails pathDetails1 = pathDetails.get(position);
        pathDetails1.InvertArrow();

        if (pathDetails1.isOpen()) {
            addFileList(position);
            pathDetails1.InvertStatus();
        } else {
            removeFileList(position);
            pathDetails1.InvertStatus();
        }
        adapter.notifyItemChanged(position);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}