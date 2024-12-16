package com.oriol.batalla_naval;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;



public class MainActivity extends AppCompatActivity {
    int numFilas=11;
    int numColumnas=11;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        GridLayout gridLayout = findViewById(R.id.tablero);
        gridLayout.setColumnCount(numColumnas);
        gridLayout.setRowCount(numFilas);

        TextView view = new TextView(this);
        view.setLayoutParams(new GridLayout.LayoutParams());
        view.setGravity(Gravity.CENTER);
        view.setText("esto es un texto de prueba");
        view.setTextSize(16);
        GridLayout.LayoutParams params = new GridLayout.LayoutParams();
        params.width = 0;
        params.height = 0;
        params.columnSpec = GridLayout.spec(0);
        params.rowSpec = GridLayout.spec(0);
        view.setLayoutParams(params);
        

        //primer hem de definir les files i columnes (ABCD...) (1234...)
        for (int i = 0;i<numFilas;i++){
            for (int j = 0;j<numColumnas;j++){
                if (i==0 || j==0){
                    afegirCella(gridLayout,i,j);
                }else{
                    afegirBoto(gridLayout,i,j);
                }
                gridLayout.requestLayout();
            }
        }


    }


    private void afegirCella(GridLayout gridLayout, int fila, int columna) {
        TextView cella = new TextView(this);
        Log.i("INFO","Cella afegida a " +fila+columna);
        if (fila == 0 && columna == 0) {
            cella.setLayoutParams(new GridLayout.LayoutParams());
        } else {
            if (fila == 0) {
                Log.i("INFO","dins columna");
                cella.setText(String.valueOf((char) ('A' + columna - 1)));
            } else {
                Log.i("INFO","dins fila");
                cella.setText(String.valueOf(fila));
            }
            cella.setGravity(Gravity.CENTER);
            cella.setTextSize(16);
            cella.setTextColor(Color.BLACK);
            GridLayout.LayoutParams params = new GridLayout.LayoutParams();
            params.width = 1;
            params.height = 1;
            params.columnSpec = GridLayout.spec(columna,1f);
            params.rowSpec = GridLayout.spec(fila,1f);
            params.setMargins(2, 2, 2, 2);
            cella.setBackgroundColor(Color.GREEN);
            cella.setLayoutParams(params);
        }
        gridLayout.addView(cella);
    }


    private void afegirBoto(GridLayout gridLayout, int fila, int columna) {
        Button button = new Button(this);
        //establim les caracterisitiques
        GridLayout.LayoutParams params = new GridLayout.LayoutParams();
        params.width = 0;
        params.height = 0;
        params.columnSpec=GridLayout.spec(columna,1f);
        params.rowSpec=GridLayout.spec(fila,1f);
        params.setMargins(2, 2, 2, 2);
        //les alpiquem al boto
        button.setLayoutParams(params);
        //indiquem que no tindra text i que el color sera blau, quan es premi el farem negre
        button.setText("");
        button.setBackgroundColor(Color.BLUE);
        //indiquem la posicio del boto a la matriu
        button.setTag(new int[]{fila,columna});

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //per fer-ho mes net fem una funcio
                buttonClicked(view);
            }
        });
        gridLayout.addView(button);

    }



    private void buttonClicked(View view) {
        //identifiquem el boto
        Button button = (Button) view;
        int[] posicio = (int[]) button.getTag();
        int fila = posicio[0];
        int columna = posicio[1];

        //com s'ha premut el deshabilitem i li canviem el color
        button.setBackgroundColor(Color.BLACK);
        button.setEnabled(false);

        //generem un toast per a que es notifiqui al usuari que s'ha premut
        Toast.makeText(this,"S'ha premut la fila "+fila+"i la columna: "+(char) ('A' + columna - 1),Toast.LENGTH_SHORT).show();
    }
}