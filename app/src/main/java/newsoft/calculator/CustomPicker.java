package newsoft.calculator;

import android.content.Context;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import java.text.DecimalFormat;

public class CustomPicker extends RelativeLayout {


    private EditText valueTv;

    private ImageButton upBtn;

    private ImageButton downBtn;

    private DecimalFormat formatter;

    public CustomPicker(Context context) {
        super(context);
        init();
    }

    public CustomPicker(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomPicker(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public CustomPicker(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }


    private void init() {
        View view = inflate(getContext(), R.layout.custom_picker_layout, this);
        upBtn = view.findViewById(R.id.up_btn);
        downBtn = view.findViewById(R.id.down_btn);
        valueTv = view.findViewById(R.id.value_tv);
        upBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                clickUp();
            }
        });

        downBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                clickDown();
            }
        });
        formatter = new DecimalFormat("##0.00");
    }

    private void clickUp() {
        float value = Float.valueOf(valueTv.getText().toString());
        setValue(value + 1f);
    }

    private void clickDown() {
        float value = Float.valueOf(valueTv.getText().toString());
        float nextValue = value - 1f;
        if (nextValue > 0) {
            setValue(nextValue);

        } else {
            setValue(0);
        }
    }

    public void setValue(float value) {
        valueTv.setText(formatter.format(value));
    }

    public float getValue() {
        float value;
        try {
            value = Float.valueOf(valueTv.getText().toString());

        } catch (NumberFormatException e) {
            e.printStackTrace();
            value = 0;
        }
        return value;
    }

    public void addTextListener(TextWatcher textChangeListener) {
        valueTv.addTextChangedListener(textChangeListener);
    }

    public void removeTextListener(TextWatcher textChangeListener) {
        valueTv.removeTextChangedListener(textChangeListener);
    }
}
