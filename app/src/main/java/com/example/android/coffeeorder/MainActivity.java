package
        com.example.android.coffeeorder;



import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.example.android.coffeeorder.R;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    Button bu;
    EditText edit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bu=(Button)findViewById(R.id.button);
        edit=(EditText)findViewById(R.id.et);
        bu.setOnClickListener(this);

    }

    @Override
    public void onClick(View view)
    {
        if(view.getId()==R.id.button)
        {
            String val=edit.getText().toString();
            Intent intent=new Intent(MainActivity.this, com.example.android.coffeeorder.Main2.class);
            intent.putExtra("val1",val);
            startActivity(intent);
        }

    }
    int number=2;

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {

        CheckBox cb=(CheckBox) findViewById(R.id.checkBox1);
        Boolean hasChecked=cb.isChecked();
        CheckBox cb1=(CheckBox)findViewById(R.id.checkBox2);
        Boolean hasC=cb1.isChecked();
        EditText edit=(EditText)findViewById(R.id.et);
        String name=edit.getText().toString();
        String sum=orderSummary(number,hasChecked,hasC,name);

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT,"Order for "+name);
        intent.putExtra(Intent.EXTRA_TEXT,sum);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }




    public int calprice(Boolean hasChecked,Boolean hasC)
    {
        int price=5;
        int total=0;
        if(hasChecked||hasC)
        {
            if(hasChecked)
                total=number*(price+1);
            if(hasC)
                total=number*(price+2);
            if(hasChecked&&hasC)
                total=number*(price+1+2);
        }
        else
        {
            total=number*5;
        }
        return total;



    }


    public String orderSummary(int number, Boolean hasChecked,Boolean hasC,String name)
    {
        int total;
        total=calprice(hasChecked,hasC);
        return ("Name:"+name+"\n"+"Add Whiped Cream?"+hasChecked+"\n"+"Add Chocolate?"+hasC+"\n"+"Quantity:"+number+"\n"+"Total:$"+total+"\n"+"Thank you!");
    }
    public void increment(View view) {
        if(number<100)
            display(++number);

    }
    public void decrement(View view) {
        if(number>0) {
            display(--number);
        }
    }



    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }


    /**
     * This method displays the given price on the screen.
     */

}