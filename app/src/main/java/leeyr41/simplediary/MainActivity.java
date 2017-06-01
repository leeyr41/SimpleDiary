package leeyr41.simplediary;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    DatePicker date;
    EditText edit;
    Button but;
    String fileName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        date=(DatePicker)findViewById(R.id.date_pick);
        edit=(EditText)findViewById(R.id.edit);
        but=(Button)findViewById(R.id.but);

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        final int month=cal.get(Calendar.MONTH);
        final int day = cal.get(Calendar.DATE);

        date.init(year, month, day, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                fileName=year+"_"+(month+1)+"_"+day+".txt";
                String readDate=readDiary(fileName);
                edit.setText(readDate);
                but.setEnabled(true);
            }
        });

    }
    public String readDiary(String fileName){
        String diaryStr=null;
        FileInputStream  fIn=null;
        try {
            fIn = openFileInput(fileName);
            byte[] buf = new byte[500];
            fIn.read(buf);
            diaryStr=new String(buf).trim();
            but.setText("수정 하기");
        } catch (FileNotFoundException e) {
            edit.setText("일기가 존재하지 않습니다.");
            but.setText("새로 저장");
        } catch (IOException e) {

        }
        return diaryStr;
    }
}
