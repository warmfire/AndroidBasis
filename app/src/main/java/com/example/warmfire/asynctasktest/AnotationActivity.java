package com.example.warmfire.asynctasktest;

import android.app.Activity;
import android.widget.Button;
import android.widget.TextView;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

/**
 * Created by warmfire_2 on 2016/8/19.
 */
@EActivity(R.layout.activity_anotation)
public class AnotationActivity extends Activity {

    @ViewById(R.id.anotation_back) Button anotation_back;
    @ViewById(R.id.anotation_show) Button anotation_show;
    @ViewById(R.id.anotation_txt) TextView anotation_txt;
    @Click(R.id.anotation_back) void anotation_back() {
        finish();
    }
    @Click(R.id.anotation_show) void anotation_show() {
        anotation_txt.setText("import android.app.Activity;\n" +
                "import android.widget.Button;\n" +
                "import android.widget.TextView;\n" +
                "\n" +
                "import org.androidannotations.annotations.Click;\n" +
                "import org.androidannotations.annotations.EActivity;\n" +
                "import org.androidannotations.annotations.ViewById;\n" +
                "\n" +
                "/**\n" +
                " * Created by warmfire_2 on 2016/8/19.\n" +
                " */\n" +
                "@EActivity(R.layout.activity_anotation)\n" +
                "public class AnotationActivity extends Activity {\n" +
                "\n" +
                "    @ViewById(R.id.anotation_back) Button anotation_back;\n" +
                "    @ViewById(R.id.anotation_show) Button anotation_show;\n" +
                "    @ViewById(R.id.anotation_txt) TextView anotation_txt;\n" +
                "    @Click(R.id.anotation_back) void anotation_back() {\n" +
                "        finish();\n" +
                "    }\n" +
                "    @Click(R.id.anotation_show) void anotation_show() {\n" +
                "        anotation_txt.setText(\"\");\n" +
                "    }\n" +
                "    \n" +
                "}");
    }

}
