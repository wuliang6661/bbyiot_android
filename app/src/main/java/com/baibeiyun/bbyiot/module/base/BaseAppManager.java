/*
 * Copyright (c) 2015 [1076559197@qq.com | tchen0707@gmail.com]
 *
 * Licensed under the Apache License, Version 2.0 (the "License”);
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

//          ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
//                              _oo0oo_
//                             o8888888o
//                             88" . "88
//                             (| -_- |)
//                             0\  =  /0
//                           ___/`---‘\___
//                        .' \\\|     |// '.
//                       / \\\|||  :  |||// \\
//                      / _ ||||| -:- |||||- \\
//                      | |  \\\\  -  /// |   |
//                      | \_|  ''\---/''  |_/ |
//                      \  .-\__  '-'  __/-.  /
//                    ___'. .'  /--.--\  '. .'___
//                 ."" '<  '.___\_<|>_/___.' >'  "".
//                | | : '-  \'.;'\ _ /';.'/ - ' : | |
//                \  \ '_.   \_ __\ /__ _/   .-' /  /
//            ====='-.____'.___ \_____/___.-'____.-'=====
//                              '=---='
//
//
//          ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
//
//                  佛祖保佑                 永无BUG

package com.baibeiyun.bbyiot.module.base;

import android.app.Activity;
import android.util.Log;


import java.util.Stack;


public class BaseAppManager {

    private static final String TAG = BaseAppManager.class.getSimpleName();

    private static BaseAppManager instance = null;
    private static Stack<Activity> mActivities = new Stack<>();

    private BaseAppManager() {

    }

    public static BaseAppManager getInstance() {
        if (null == instance) {
            synchronized (BaseAppManager.class) {
                if (null == instance) {
                    instance = new BaseAppManager();
                }
            }
        }
        return instance;
    }

    public int size() {
        return mActivities.size();
    }


    public synchronized Activity getForwardActivity() {
        return size() > 0 ? mActivities.get(size() - 1) : null;
    }

    public synchronized Activity getForward2Activity() {
        return size() > 1 ? mActivities.get(size() - 2) : null;
    }

    public synchronized void addActivity(Activity activity) {
        mActivities.push(activity);
    }

    public synchronized void removeActivity(Activity activity) {
        if (mActivities.contains(activity)) {
            mActivities.remove(activity);
        }
    }

    public synchronized void clear() {
        int index = size()-1;
        Log.d("liugang","index:"+index);
        while (index>=0){
            Activity activity = mActivities.pop();
            removeActivity(activity);
            activity.finish();
            index--;
        }
    }

    public synchronized void clearToTop() {
        int index = size()-1;
        Log.d("liugang","index:"+index);
        while (index>0){
            Activity activity = mActivities.pop();
            removeActivity(activity);
            activity.finish();
            index--;
        }
    }

}
