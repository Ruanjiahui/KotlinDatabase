package ruan.database.com;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import org.jetbrains.annotations.NotNull;

import ruan.mydatabase.com.BaseUser;
import ruan.mydatabase.com.CheckDatabase;
import ruan.mydatabase.com.DatabaseCallback;
import ruan.mydatabase.com.api.Establish;

public class MainActivity extends AppCompatActivity implements DatabaseCallback{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CheckDatabase.Companion.CheckData(this , "test" , "test" , this);


        User user = new User();
        user.setName("ruanjiahui");
        user.setId(12346);

        new BaseUser(this).INSERT("test" , "test" , user);

    }

    @NotNull
    @Override
    public Establish CreateTable(@NotNull String database, @NotNull String table, boolean state) {
        if (state)
            return null;
        else{
            Establish establish = new Establish();
            establish.put("id" , "int");
            establish.put("name" , "varchar(50)");
            return establish;
        }
    }
}
