package com.example.qewqs.gridview_exam;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 1. 다량의 데이터
        // 2. Adapter
        // 3. AdapterView - GridView
        int img[] = {
                R.drawable.c, R.drawable.e, R.drawable.j };

        // 커스텀 아답타 생성
        MyAdapter adapter = new MyAdapter(
                getApplicationContext(),
                R.layout.row,       // GridView 항목의 레이아웃 row.xml
                img);    // 데이터

        GridView gv = (GridView) findViewById(R.id.gridView1);
        gv.setAdapter(adapter);  // 커스텀 아답타를 GridView 에 적용

        tv = (TextView) findViewById(R.id.textView1);

        // GridView 아이템을 클릭하면 상단 텍스트뷰에 position 출력
        // JAVA8 에 등장한 lambda expression 으로 구현했습니다. 코드가 많이 간결해지네요
        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Toast.makeText(getApplicationContext(), "position : " + position + "\nID : " + id, Toast.LENGTH_LONG ).show();
            }
        });
    } // end of onCreate

    class MyAdapter extends BaseAdapter {
        Context context;
        int layout;
        int img[];
        LayoutInflater inf;

        public MyAdapter(Context context, int layout, int[] img) {
            this.context = context;
            this.layout = layout;
            this.img = img;
            inf = (LayoutInflater) context.getSystemService
                    (Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return img.length;
        }

        @Override
        public Object getItem(int position) {
            return img[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null)
                convertView = inf.inflate(layout, null);
            ImageView iv = (ImageView) convertView.findViewById(R.id.imageView1);
            iv.setImageResource(img[position]);
            iv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ImageView imageView = (ImageView) v;
                    imageView.setImageResource(R.drawable.q);
                }
            });

            return convertView;
        }

    } // end of class
}