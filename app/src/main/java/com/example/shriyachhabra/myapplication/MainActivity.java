package com.example.shriyachhabra.myapplication;

import android.content.Intent;
import android.speech.RecognizerIntent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shriyachhabra.myapplication.Adapters.LexemeAdapter;
import com.example.shriyachhabra.myapplication.Modals.Lexeme;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private TextView txtResult;
    private RecyclerView  rvLex;

    //array list of lexems
    ArrayList<Lexeme> lexArr;

    HashSet<String> hfn2Start= new HashSet<>();
    HashSet<String> lfn1Mid= new HashSet<>();
    HashSet<String> lfn2Mid= new HashSet<>();
    HashSet<String> hfn3Mid= new HashSet<>();
    HashSet<String> lfn1End= new HashSet<>();
    HashSet<String> lfw1End= new HashSet<>();
    HashSet<String> hfw2End= new HashSet<>();
    int h,lo,fw,fu,plain;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtResult= findViewById(R.id.txtResult);
        rvLex = findViewById(R.id.rvLex);

        fillSets();

    }

    public void getSpeechInput(View view){
        Intent i= new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        i.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM );
        i.putExtra(RecognizerIntent.EXTRA_LANGUAGE,Locale.getDefault());

        if(i.resolveActivity(getPackageManager())!=null){
            startActivityForResult(i,10);
        }else{
            Toast.makeText(this, "Device does not support speech input", Toast.LENGTH_SHORT).show();
        }

     }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode){
            case 10:
                if(resultCode==RESULT_OK&& data !=null){
                    ArrayList<String> result=data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    String res=result.get(0);


                    //creating the lexArr
                    lexArr= new ArrayList<>();

                    //logic to fill the lexArr

                    fillLexArr(res);

                    System.out.print("chinu"+ lexArr.size());

                    //inflate the view
                    rvLex.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
                    LexemeAdapter adapter= new LexemeAdapter(this,lexArr);
                    rvLex.setAdapter(adapter);
                    txtResult.setText(res);



                }
                break;
        }
    }


    private void fillLexArr(String str){

        String[] arr=str.split(" ");
        for(int i=0;i<arr.length;i++){
            String word=arr[i];
            for(int j=0;j<word.length();j++){
                Lexeme l= new Lexeme();
                boolean assigned=false;
               char ch=word.charAt(j);
               if(j==0){
                   if(hfn2Start.contains(ch+"")){
                      l.setRow1(plain);
                      l.setRow2(h);
                      l.setRow3(plain);
                      l.setC(ch);
                      assigned=true;
                   }
               }
               if(assigned==false&&j==word.length()-3){
                   if(hfw2End.contains(word.substring(j))){
                       l.setRow1(plain);
                       l.setRow2(fw);
                       l.setRow3(plain);
                       l.setC(ch);
                       assigned=true;
                   }else if(lfw1End.contains(word.substring(j))){
                       l.setRow1(fw);
                       l.setRow2(plain);
                       l.setRow3(plain);
                       l.setC(ch);
                       assigned=true;
                   }

               }
               if(assigned==false&&(j==word.length()-2||j==word.length()-1)){
                   if(lfw1End.contains(word.substring(j))){
                       l.setRow1(fw);
                       l.setRow2(plain);
                       l.setRow3(plain);
                       l.setC(ch);
                       assigned=true;
                   }else if(lfn1End.contains(word.substring(j))){
                       l.setRow1(lo);
                       l.setRow2(plain);
                       l.setRow3(plain);
                       l.setC(ch);
                       assigned=true;
                   }
               }
               if(assigned==false&&(j+2<=word.length())&&(lfn1Mid.contains(ch+"")|| lfn1Mid.contains(word.substring(j,j+2)))){
                   l.setRow1(lo);
                   l.setRow2(plain);
                   l.setRow3(plain);
                   l.setC(ch);
                   assigned=true;
               }
               if(assigned==false&&(j+2<=word.length())&&(lfn2Mid.contains(ch+"")|| lfn2Mid.contains(word.substring(j,j+2)))){
                   l.setRow1(plain);
                   l.setRow2(lo);
                   l.setRow3(plain);
                   l.setC(ch);
                   assigned=true;
               }
               if(assigned==false&&(j+2<=word.length())&&(hfn3Mid.contains(ch+"")|| hfn3Mid.contains(word.substring(j,j+2)))){
                   l.setRow1(plain);
                   l.setRow2(plain);
                   l.setRow3(h);
                   l.setC(ch);
                   assigned=true;
               }
                if(assigned==false){
                   l.setRow1(plain);
                   l.setRow2(plain);
                   l.setRow3(plain);
                   l.setC(ch);

               }

              System.out.print("hibi"+l);
              lexArr.add(l);

            }

            Lexeme le= new Lexeme();
            le.setRow1(plain);
            le.setRow2(plain);
            le.setRow3(plain);
            le.setC(' ');
            lexArr.add(le);
        }


    }
    private void fillSets(){
        hfn2Start.add("a");
        hfn2Start.add("e");
        hfn2Start.add("i");
        hfn2Start.add("o");
        hfn2Start.add("u");

        lfn1Mid.add("ei");
        lfn1Mid.add("ai");
        lfn1Mid.add("u");
        lfn1Mid.add("oo");
        lfn1Mid.add("ou");
        lfn1Mid.add("i");
        lfn1Mid.add("ui");
        lfn1Mid.add("y");
        lfn1Mid.add("e");
        lfn1Mid.add("ea");
        lfn1Mid.add("a");

        lfn2Mid.add("ee");
        lfn2Mid.add("ea");

        hfn3Mid.add("o");
        hfn3Mid.add("wa");
        hfn3Mid.add("oe");
        hfn3Mid.add("ow");

        lfn1End.add("ay");
        lfn1End.add("a");

        lfw1End.add("ly");
        lfw1End.add("ing");

        hfw2End.add("eer");
        hfw2End.add("ear");
        hfw2End.add("ier");
        hfw2End.add("ere");
        hfw2End.add("air");
        hfw2End.add("are");
        hfw2End.add("ear");
        hfw2End.add("ere");
        hfw2End.add("oor");
        hfw2End.add("our");
        hfw2End.add("ure");

        h=R.drawable.bigdot;
        lo=R.drawable.smalldot;
        fw=R.drawable.downglide;
        fu=R.drawable.upglide;
        plain=R.drawable.planeline;


    }
}
