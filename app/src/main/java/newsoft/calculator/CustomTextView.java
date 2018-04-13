package newsoft.calculator;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.text.InputType;
import android.util.AttributeSet;

public class CustomTextView extends android.support.v7.widget.AppCompatEditText {

    public CustomTextView(Context context) {
        super(context);
        init();
    }

    public CustomTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setBackground(ContextCompat.getDrawable(getContext(), R.drawable.background_edit_text));
        setTextAlignment(TEXT_ALIGNMENT_TEXT_END);
        setTypeface(null, Typeface.BOLD);
        setTextSize(12);
        setInputType(InputType.TYPE_NULL);
        setMaxLines(1);
    }

    public void setValue(float value) {
        String str;
        if (value < 0) {
            str = getContext().getString(R.string.minus_dollar_holder, value * -1);
            setTextColor(ContextCompat.getColor(getContext(), R.color.colorRed));

        } else {
            str = getContext().getString(R.string.dollar_holder, value);
            setTextColor(ContextCompat.getColor(getContext(), R.color.colorBlack));
        }

        setText(str);
    }
}
