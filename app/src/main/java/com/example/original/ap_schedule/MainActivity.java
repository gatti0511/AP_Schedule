package com.example.original.ap_schedule;

import android.graphics.Color;
import android.icu.util.Calendar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {

    Realm realm;

    String days_name[] = {"sunday", "monday", "tuesday", "wednesday", "thursday", "friday", "saturday"};
    String items_name[] = {"security", "architecture", "program", "network", "database", "audit"};

    final int AP_YEAR = 2019;
    final int AP_MONTH = 10;
    final int AP_DATE = 20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 残り日数を表示させる
        String RemainingDays = String.valueOf(DaysCalculation(AP_YEAR, AP_MONTH, AP_DATE));
        ((TextView) findViewById(R.id.day_text)).setText("あと " + RemainingDays + "日");

        // 今日やる科目の色を変える
        Doing_item();

        Realm.init(this);
        realm = Realm.getDefaultInstance();

        RealmQuery<Info> query = realm.where(Info.class);
        RealmResults<Info> results = query.findAll();

        // 何か保存されている場合
        if(0 < results.size()) {
            Toast.makeText(this, "読み込み", Toast.LENGTH_LONG).show();

            RealmResults<Info> results_day = realm.where(Info.class)
                                                .equalTo("flg_security", true).or()
                                                .equalTo("flg_architecture", true).or()
                                                .equalTo("flg_program", true).or()
                                                .equalTo("flg_network", true).or()
                                                .equalTo("flg_database", true).or()
                                                .equalTo("flg_audit", true).or()
                                                .findAll();

            String str;
            int viewId;
            TextView textView;
            for(Info info : results_day){

                if(info.isFlg_security()){
                    // xx曜日の情報セキュリティを〇にする
                    str = "security_" + info.getName();
                    viewId = getResources().getIdentifier(str, "id", getPackageName());

                    textView = (TextView) findViewById(viewId);
                    ((TextView) textView).setText("〇");
                    textView.setBackgroundColor(Color.rgb(255, 165, 0));
                }

                if(info.isFlg_architecture()){
                    // xx曜日のアーキテクチャを〇にする
                    str = "architecture_" + info.getName();
                    viewId = getResources().getIdentifier(str, "id", getPackageName());

                    textView = (TextView) findViewById(viewId);
                    ((TextView) textView).setText("〇");
                    textView.setBackgroundColor(Color.rgb(255, 165, 0));
                }

                if(info.isFlg_program()){
                    // xx曜日のプログラミングを〇にする
                    str = "program_" + info.getName();
                    viewId = getResources().getIdentifier(str, "id", getPackageName());

                    textView = (TextView) findViewById(viewId);
                    ((TextView) textView).setText("〇");
                    textView.setBackgroundColor(Color.rgb(255, 165, 0));
                }

                if(info.isFlg_network()){
                    // xx曜日のネットワークを〇にする
                    str = "network_" + info.getName();
                    viewId = getResources().getIdentifier(str, "id", getPackageName());

                    textView = (TextView) findViewById(viewId);
                    ((TextView) textView).setText("〇");
                    textView.setBackgroundColor(Color.rgb(255, 165, 0));
                }

                if(info.isFlg_database()){
                    // xx曜日のデータベースを〇にする
                    str = "database_" + info.getName();
                    viewId = getResources().getIdentifier(str, "id", getPackageName());

                    textView = (TextView) findViewById(viewId);
                    ((TextView) textView).setText("〇");
                    textView.setBackgroundColor(Color.rgb(255, 165, 0));
                }

                if(info.isFlg_audit()){
                    // xx曜日のシステム監査を〇にする
                    str = "audit_" + info.getName();
                    viewId = getResources().getIdentifier(str, "id", getPackageName());

                    textView = (TextView) findViewById(viewId);
                    ((TextView) textView).setText("〇");
                    textView.setBackgroundColor(Color.rgb(255, 165, 0));
                }
            }
        }
        // 何も保存されていない場合、曜日を追加する
        else{
            Toast.makeText(this, "初期登録", Toast.LENGTH_LONG).show();

            realm.beginTransaction();

            for(String day_name :  days_name){
                Info info = new Info(
                        day_name,
                        false,
                        false,
                        false,
                        false,
                        false,
                        false
                    );

                Info infoRealm = realm.copyToRealm(info);
            }

            // コミットは、本当に最後にやる。コミットするとfor文抜ける。たぶんRealmが閉じる
            realm.commitTransaction();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }

    // 残り日数を計算する
    public long DaysCalculation(int year, int month, int date){

        // 試験日を設定
        Calendar goal = Calendar.getInstance();
        goal.set(year , month - 1, date);

        long timeMillis = goal.getTimeInMillis();
        long currentTimeMillis = System.currentTimeMillis();

        // 試験日までの差分（ミリ秒）
        long diff = timeMillis - currentTimeMillis;

        // ミリ秒 から 秒
        // 秒 から 分
        // 分 から 時
        // 時 から 日
        diff = diff / 1000;
        diff = diff / 60;
        diff = diff / 60;
        diff = diff / 24;

        return diff;
    }

    // 今日実施する科目の色を変える
    public void Doing_item(){
        Calendar today = Calendar.getInstance();

        switch (today.get(Calendar.DAY_OF_WEEK)){
            // 日
            case 1:
                // 特殊な処理を書く
                break;

            // 月
            case 2:
                ((TextView) findViewById(R.id.security_monday)).setBackgroundColor(Color.YELLOW);
                ((TextView) findViewById(R.id.architecture_monday)).setBackgroundColor(Color.YELLOW);
                break;

            // 火
            case 3:
                ((TextView) findViewById(R.id.security_tuesday)).setBackgroundColor(Color.YELLOW);
                ((TextView) findViewById(R.id.architecture_tuesday)).setBackgroundColor(Color.YELLOW);
                break;

            // 水
            case 4:
                ((TextView) findViewById(R.id.program_wednesday)).setBackgroundColor(Color.YELLOW);
                ((TextView) findViewById(R.id.network_wednesday)).setBackgroundColor(Color.YELLOW);
                break;

            // 木
            case 5:
                ((TextView) findViewById(R.id.program_thursday)).setBackgroundColor(Color.YELLOW);
                ((TextView) findViewById(R.id.network_thursday)).setBackgroundColor(Color.YELLOW);
                break;

            // 金
            case 6:
                ((TextView) findViewById(R.id.database_friday)).setBackgroundColor(Color.YELLOW);
                ((TextView) findViewById(R.id.audit_friday)).setBackgroundColor(Color.YELLOW);
                break;

            // 土
            case 7:
                ((TextView) findViewById(R.id.database_saturday)).setBackgroundColor(Color.YELLOW);
                ((TextView) findViewById(R.id.audit_saturday)).setBackgroundColor(Color.YELLOW);
                break;
        }
    }

    // タップ時の処理
    // 1. 空白 -> 黄色と〇, 黄色と〇 -> 空白
    // 2. 変わった後、やるべき科目を黄色で表示する
    public void ChangeColor(View view){
        // 入力されていたTextViewのid名をFullで取得
        String xml_name = getResources().getResourceName(view.getId());

        // 取得した「Fullパス/id名」はいらないものまでくっついているので、id名だけを抽出する
        int Fullpath_idName_index = xml_name.lastIndexOf("/");
        String id_name = xml_name.substring(Fullpath_idName_index + 1);

        // id名が「科目名_曜日名」なので、分ける
        int _index = id_name.indexOf("_");
        String item_name = id_name.substring(0, _index);
        String day_name = id_name.substring(_index + 1);


        Log.v("day_name", item_name);
        Log.v("day_name", day_name);

        // 入力されている文字を取得
        String input_text = ((TextView) view).getText().toString();

        // 黄色と〇 -> 空白にする
        if(input_text.equals("〇")){
            ((TextView) view).setText("");
            view.setBackgroundColor(Color.argb(255, 255, 255, 255));

            Update_info(day_name, item_name, false);

            return_Doing_item(view, day_name, item_name);
        }
        // 空白 -> 黄色と〇にする
        else{
            ((TextView) view).setText("〇");
            view.setBackgroundColor(Color.rgb(255, 165, 0));

            Update_info(day_name, item_name, true);
        }
    }

    // タップ時、やるべき科目を黄色にする
    public void return_Doing_item(View view, String day_name, String item_name){
        // 今日の曜日を取得する
        Calendar today = Calendar.getInstance();
        int TODAY_DAY_OF_WEEK = today.get(Calendar.DAY_OF_WEEK);

        // タップした曜日と今日の曜日が同じとき
        if(day_name.equals(days_name[TODAY_DAY_OF_WEEK - 1])){

            // 今日の曜日ごとに色を変えるviewを変える
            switch (TODAY_DAY_OF_WEEK) {
                // 日曜日
                case 1:
                    // 特別な処理を書く
                    break;

                // 月曜日
                case 2:

                    // 月曜日なら「security」と「architecture」は黄色にする
                    if (item_name.equals("security")) {
                        view.setBackgroundColor(Color.YELLOW);
                    } else if (item_name.equals("architecture")) {
                        view.setBackgroundColor(Color.YELLOW);
                    }
                    break;

                // 火曜日
                case 3:
                    // 火曜日なら「security」と「architecture」は黄色にする
                    if (item_name.equals("security")) {
                        view.setBackgroundColor(Color.YELLOW);
                    } else if (item_name.equals("architecture")) {
                        view.setBackgroundColor(Color.YELLOW);
                    }
                    break;

                // 水曜日
                case 4:

                    // 水曜日なら「program」と「network」は黄色にする
                    if (item_name.equals("program")) {
                        view.setBackgroundColor(Color.YELLOW);
                    } else if (item_name.equals("network")) {
                        view.setBackgroundColor(Color.YELLOW);
                    }
                    break;

                // 木曜日
                case 5:

                    // 木曜日なら「program」と「network」は黄色にする
                    if (item_name.equals("program")) {
                        view.setBackgroundColor(Color.YELLOW);
                    } else if (item_name.equals("network")) {
                        view.setBackgroundColor(Color.YELLOW);
                    }
                    break;

                // 金曜日
                case 6:

                    // 金曜日なら「database」と「audit」は黄色にする
                    if (item_name.equals("database")) {
                        view.setBackgroundColor(Color.YELLOW);
                    } else if (item_name.equals("audit")) {
                        view.setBackgroundColor(Color.YELLOW);
                    }
                    break;

                // 土曜日
                case 7:

                    // 土曜日なら「database」と「audit」は黄色にする
                    if (item_name.equals("database")) {
                        view.setBackgroundColor(Color.YELLOW);
                    } else if (item_name.equals("audit")) {
                        view.setBackgroundColor(Color.YELLOW);
                    }
                    break;
            }
        }
    }

    // Realmに更新をかける
    public void Update_info(String day_name, String item_name, boolean flg){
        realm.beginTransaction();

        RealmResults<Info> result_day = realm.where(Info.class)
                                            .equalTo("name", day_name)
                                            .findAll();

        switch (item_name){
            case "security":
                result_day.get(0).setFlg_security(flg);
                break;

            case "architecture":
                result_day.get(0).setFlg_architecture(flg);
                break;

            case "program":
                result_day.get(0).setFlg_program(flg);
                break;

            case "network":
                result_day.get(0).setFlg_network(flg);
                break;

            case "database":
                result_day.get(0).setFlg_database(flg);
                break;

            case "audit":
                result_day.get(0).setFlg_audit(flg);
                break;
        }
        realm.commitTransaction();
    }
}
