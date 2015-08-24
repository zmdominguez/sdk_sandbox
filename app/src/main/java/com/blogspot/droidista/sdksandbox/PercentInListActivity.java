package com.blogspot.droidista.sdksandbox;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class PercentInListActivity extends Activity {

    public static final String EXTRA_WITH_MIN_HEIGHT = "extra_with_min_height";

    @Bind(R.id.percent_list_items)
    ListView mListView;

    boolean isUseMinHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_percent_in_list);
        ButterKnife.bind(this);

        isUseMinHeight = getIntent().getBooleanExtra(EXTRA_WITH_MIN_HEIGHT, false);
        mListView.setAdapter(new CheesesAdapter());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_percent_in_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    class CheesesAdapter extends BaseAdapter {

        String[] items = {"Item 1", "Item 2", "Item 3", "Item 4", "Item 5"};

        CheesesAdapter() {
        }

        @Override
        public int getCount() {
            return items.length;
        }

        @Override
        public Object getItem(int position) {
            return items[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View view, ViewGroup parent) {
            ViewHolder holder;
            if (view != null) {
                holder = (ViewHolder) view.getTag();
            } else {
                view = getLayoutInflater().inflate(isUseMinHeight ? R.layout.list_item_percent_min_height : R.layout.list_item_percent,
                        parent, false);
                holder = new ViewHolder(view);
                view.setTag(holder);
            }

            holder.mHeader.setText("Header " + position);
            String value = "Subheader " + position;
            if (TextUtils.isEmpty(value)) {
                holder.mSubHeader.setVisibility(View.GONE);
            } else {
                holder.mSubHeader.setText(value);
                holder.mSubHeader.setVisibility(View.VISIBLE);
            }

            return view;
        }

        class ViewHolder {
            @Bind(R.id.header)
            TextView mHeader;

            @Bind(R.id.subheader)
            TextView mSubHeader;

            ViewHolder(View view) {
                ButterKnife.bind(this, view);
            }
        }
    }
}
