package com.example.andy.footmark;

import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

/**
 * 显示用户创建的和加入的群组名称和ID
 */

public class GroupActivity extends AppCompatActivity {

    private ListView createdListView;
    private ListView addedListView;
    private Button createdButton;   //点击显示用户创建的群组
    private Button addedButton;     //点击显示用户加入的群组

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_groups);

        //自定义toolbar
        Toolbar toolbar = (Toolbar)findViewById(R.id.tb_group);
        setSupportActionBar(toolbar);
        Drawable drawable = ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_add_white_48px);
        toolbar.setOverflowIcon(drawable);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //用户创建的群组部分
        createdListView = (ListView)findViewById(R.id.lv_created_groups);
        String[] createdGroupsString = {"我创建的群组1 + ID", "我创建的群组2 + ID", "我创建的群组3 + ID", "我创建的群组4 + ID", "我创建的群组5 + ID"};
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, android.R.id.text1, createdGroupsString);
        createdListView.setAdapter(adapter1);
        createdListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });
        createdButton = (Button)findViewById(R.id.bt_created_groups);
        createdButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(createdListView.getVisibility() == View.VISIBLE) {

                } else {
                    createdListView.setVisibility(View.VISIBLE);
                    addedListView.setVisibility(View.INVISIBLE);
                }
            }
        });

        //用户加入的群组部分
        addedListView = (ListView)findViewById(R.id.lv_added_groups);
        String[] addedGroupsString = {"我加入的群组1 + ID", "我加入的群组2 + ID", "我加入的群组3 + ID", "我加入的群组4 + ID", "我加入的群组5 + ID"};
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, android.R.id.text1, addedGroupsString);
        addedListView.setAdapter(adapter2);
        addedListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });
        addedButton = (Button)findViewById(R.id.bt_added_groups);
        addedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(addedListView.getVisibility() == View.VISIBLE) {

                } else {
                    addedListView.setVisibility(View.VISIBLE);
                    createdListView.setVisibility(View.INVISIBLE);
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add_groups, menu);
        return true;
    }

    /**
     * 创建或加入群组的操作
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        //响应点击创建群组
        if(id == R.id.create_group) {
            EditText editText = new EditText(GroupActivity.this);
            editText.setMaxLines(1);
            editText.setHint("eg. XX级XX院XX班");
            editText.setInputType(android.text.InputType.TYPE_CLASS_TEXT | android.text.InputType.TYPE_TEXT_VARIATION_EMAIL_SUBJECT);

            final AlertDialog.Builder builder = new AlertDialog.Builder(GroupActivity.this);
            builder.setTitle("群组名");
            builder.setView(editText);
            builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(GroupActivity.this, "创建成功！\n您的ID是: 123456", Toast.LENGTH_LONG).show();
                }
            });
            builder.setNegativeButton("取消", null);
            builder.show();

            return true;
        }

        //点击响应加入群组
        if(id == R.id.join_group) {
            EditText editText = new EditText(GroupActivity.this);
            editText.setMaxLines(1);
            editText.setHint("12345678");
            editText.setInputType(android.text.InputType.TYPE_CLASS_TEXT | android.text.InputType.TYPE_TEXT_VARIATION_EMAIL_SUBJECT);

            AlertDialog.Builder builder = new AlertDialog.Builder(GroupActivity.this);
            builder.setTitle("群组ID");
            builder.setView(editText);
            builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(GroupActivity.this, "加入成功", Toast.LENGTH_LONG).show();
                }
            });
            builder.setNegativeButton("取消", null);
            builder.show();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
