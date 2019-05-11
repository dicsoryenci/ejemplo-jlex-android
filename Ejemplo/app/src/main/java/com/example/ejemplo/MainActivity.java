package com.example.ejemplo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        final TextView txt1 = (TextView) findViewById(R.id.txt1);

        Button btn1 = (Button) findViewById(R.id.btnAceptar);

        btn1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String str = ""+txt1.getText();
                Reader targetReader = new StringReader(str);

                Congreso lexer = new Congreso(targetReader);

                String resultado = "";

                while(true){
                    CongresoToken tk = null;
                    try {
                        tk = lexer.yylex();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    if (tk == null){
                        resultado = resultado + "EOF";
                        System.err.println(resultado);
                        return;
                    }

                    switch (tk){
                        case ERROR:
                            resultado=resultado+ "Error, simbolo no reconocido\n";
                            break;
                        default:
                            resultado=resultado+ "TOKEN: " + tk + " " + lexer.lexema + "\n";
                    }
                }

            }
        });

    }
}
