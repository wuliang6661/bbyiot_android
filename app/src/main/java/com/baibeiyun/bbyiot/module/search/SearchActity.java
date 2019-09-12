package com.baibeiyun.bbyiot.module.search;

import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import com.baibeiyun.bbyiot.R;
import com.baibeiyun.bbyiot.common.IConstant;
import com.baibeiyun.bbyiot.module.base.presenter.IPresenter;
import com.baibeiyun.bbyiot.module.base.ui.BaseActivity;
import com.baibeiyun.bbyiot.module.search.adapter.SearchHistoryAdapter;
import com.baibeiyun.bbyiot.utils.LogUtils;
import com.baibeiyun.bbyiot.utils.SpUtils;
import com.baibeiyun.bbyiot.utils.StringUtils;
import com.baibeiyun.bbyiot.utils.ToastUtils;
import com.baibeiyun.bbyiot.view.dialog.CommonDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 搜索
 */
public class SearchActity extends BaseActivity {

    @BindView(R.id.act_search_listview)
    ListView listView;


    @BindView(R.id.act_search_et_search)
    EditText et_search;
    private String searchText;
    private SearchHistoryAdapter searchHistoryAdapter;

    List<String> searchHistoryList = new ArrayList<>();
    private CommonDialog commonDialog;

    @Override
    protected void getBundleExtras(android.os.Bundle extras) {

    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.act_search;
    }

    @Override
    protected void initViewsAndEvents() {

        searchHistoryAdapter = new SearchHistoryAdapter(this,R.layout.item_search_history);
        listView.setAdapter(searchHistoryAdapter);

        updateHistory(null);
    }

    @Override
    protected IPresenter getPresenter() {
        return null;
    }

    @OnClick({R.id.act_search_rl_back, R.id.act_search_iv_search, R.id.act_search_iv_clear})
    void click(View view) {
        switch (view.getId()) {
            case R.id.act_search_rl_back:
                this.finish();
                break;

            case R.id.act_search_iv_search:
                searchText = et_search.getText().toString().trim();
                if (StringUtils.isEmpty(searchText)) {
                    ToastUtils.showToast("请输入搜索关键字");
                    return;
                }

                updateHistory(searchText);


                break;

            case R.id.act_search_iv_clear:
                deteleHistroy();
                break;

        }
    }

    private void deteleHistroy() {
        if (commonDialog == null) {
            commonDialog = new CommonDialog(this);
        }
        commonDialog.show();

        commonDialog.setTxet("提示", "确定清空全部搜索历史？");

        commonDialog.setOnDialogLinsenter(new CommonDialog.OnDialogLinsenter() {
            @Override
            public void confirm() {
                SpUtils.put(IConstant.SEARCH_HISTORY, "");
                SpUtils.remove(IConstant.SEARCH_HISTORY);
                searchHistoryList.clear();
                searchHistoryAdapter.update(null);
                commonDialog.dismiss();
            }

            @Override
            public void cancel() {

            }
        });


    }

    private void updateHistory(String searchText) {
        try {
            String spString = SpUtils.getString(IConstant.SEARCH_HISTORY, "");

            LogUtils.w("spString == " + spString);
            String saveString = spString;

            if (!StringUtils.isEmpty(searchText)) {
                saveString = spString + "," + searchText;
                SpUtils.put(IConstant.SEARCH_HISTORY, saveString);
            }

            LogUtils.w("SAVE_String == " + saveString);
            searchHistoryList.clear();

            String[] split = saveString.split(",");
            for (String aSplit : split) {
                if (!StringUtils.isEmpty(aSplit)) {
                    searchHistoryList.add(aSplit);
                }
            }


            searchHistoryAdapter.update(searchHistoryList);
        } catch (Exception e) {
            LogUtils.w(e.toString());
        }
    }


}
