package com.example.hikai.hikaiplayer;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.hikai.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ChapterLectureFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ChapterLectureFragment extends Fragment implements RecyclerViewClickInterface{

    //MAIN
    private RecyclerView rvChapterLecture;
    LinearLayoutManager layoutManager;
    ChapterAdapter chapterAdapter;
    RequestQueue queue;
    JsonArrayRequest jsonObjReq;
    String jsonUrl;
    JSONObject jresponse;

    //Recycler View
    View v;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ChapterLectureFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ChapterLectureFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ChapterLectureFragment newInstance(String param1, String param2) {
        ChapterLectureFragment fragment = new ChapterLectureFragment();
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
        v = inflater.inflate(R.layout.fragment_chapter_lecture, container, false);

        rvChapterLecture = v.findViewById(R.id.rv_chapter_lecture);
        layoutManager = new LinearLayoutManager(getContext());
        chapterAdapter = new ChapterAdapter(buildItemList(),ChapterLectureFragment.this);
        rvChapterLecture.setAdapter(chapterAdapter);
        rvChapterLecture.setLayoutManager(layoutManager);

        return v;
    }

    private List<Chapter> buildItemList() {
        //onCreateView
        queue = Volley.newRequestQueue(getContext());
        jsonUrl ="https://rkmshillong.online/public/BEG.cache";
        final List<Chapter> chapterList = new ArrayList<>();
        jsonObjReq = new JsonArrayRequest(Request.Method.GET,
                jsonUrl, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d("VOLLEY", response.toString());
                        try {

                            for(int i=0;i<response.length();i++){
                                //Parent Recycler View
                                jresponse = response.getJSONObject(i);
                                String chapterTitle = jresponse.getString("title");
                                String totalLecture = jresponse.getString("items");
                                JSONArray lectureArray=jresponse.getJSONArray("lecture");
                                Log.d("Lecture",jresponse.getJSONArray("lecture").toString());
                                //Log.d("chapterNo",chapterNo);
                                // Log.d("title",title);
                                Chapter chapter = new Chapter((i+1)+" "+chapterTitle,"Total lectures "+totalLecture, buildSubItemList(lectureArray));
                                chapterList.add(chapter);

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        chapterAdapter.notifyDataSetChanged();
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("VOLLEY", "Error: " + error.getMessage());
            }
        });
        queue.add(jsonObjReq);
        return chapterList;
    }

    private List<Lecture> buildSubItemList(JSONArray lectureArray) {
        final List<Lecture> lectureList = new ArrayList<>();

        try {
            for (int i=0; i<lectureArray.length(); i++) {

                JSONObject lectureObject=lectureArray.getJSONObject(i);
                String content = lectureObject.getString("content");
                if(content.equals("video")) {
                    String lectureTitle = lectureObject.getString("title");
                    String lectureUrl = lectureObject.getString("file");
                    Lecture lecture = new Lecture(lectureTitle, lectureUrl);
                    lectureList.add(lecture);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return lectureList;
    }

    @Override
    public void onItemClick(String name) {
        VideoViewMainFragment f = new VideoViewMainFragment();
        //f.iniExoplayer("http://techslides.com/demos/sample-videos/small.mp4");
        Bundle bundle = new Bundle();
        bundle.putString("path",name);
        bundle.putLong("seek",0);
        f.setArguments(bundle);
        getFragmentManager().beginTransaction().replace(R.id.frame_layout_main,f).commit();
    }

    @Override
    public void onDownloadClick(String name) {

    }

    @Override
    public void onLongItemClick(int position) {

    }
}