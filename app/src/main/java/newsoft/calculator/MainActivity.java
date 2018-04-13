package newsoft.calculator;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.print.PrintHelper;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button resetBtn, printBtn;

    private CustomPicker totalHoursPick, machineHoursPick, profitPick,
            quotedCycleTimePick, actualCycleTimePick, quotedRejectRatePick,
            actualRejectRatePick, quotedUpTimePick, actualUpTimePick;

    private CustomTextView effectCycleActualVal, effectGainMachineHourRateVal,
            effectGainQuotedCycleTimeVal, effectGainQoutedRejectRateVal,
            effectGainQuotedUpTimeVal, sumQuotedProfitVal, sumCycleTimeVal, sumRejectRateVal,
            sumUpTimeVal, sumTotalProfitVal, sumTotalProfitQuotedProfitVal;

    private ConstraintLayout mRootWrapper;

    private CalculatorManager mCalculatorManager;

    private final CalculatorCallback mCalculatorCallback = new CalculatorCallback() {
        @Override
        public void onResultChanged(final ResultData data) {
            fillResultData(data);
        }

        @Override
        public void onResetData(EnterData enterData) {
            fillEnterData(enterData);
        }
    };

    private TextWatcher mTextChangeWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            if (mCalculatorManager != null && !mCalculatorManager.isResetting()) {
                mCalculatorManager.changeEnterData(quotedCycleTimePick.getValue(),
                        actualCycleTimePick.getValue(),
                        quotedUpTimePick.getValue(),
                        actualUpTimePick.getValue(),
                        quotedRejectRatePick.getValue(),
                        actualRejectRatePick.getValue(),
                        totalHoursPick.getValue(),
                        machineHoursPick.getValue(),
                        profitPick.getValue());
            }
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };

    private View.OnClickListener resetClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (mCalculatorManager != null)
                mCalculatorManager.resetDefaultValue();
        }
    };

    private View.OnClickListener printClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            doPhotoPrint();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUI();
        mCalculatorManager = new CalculatorManager(mCalculatorCallback);
    }

    @Override
    protected void onStart() {
        super.onStart();
        addListeners();
    }

    @Override
    protected void onStop() {
        super.onStop();
        removeListeners();
    }

    private void doPhotoPrint() {
        PrintHelper photoPrinter = new PrintHelper(this);
        photoPrinter.setScaleMode(PrintHelper.SCALE_MODE_FIT);
        photoPrinter.printBitmap("result.jpg", getBitmapFromView(mRootWrapper));
    }

    public Bitmap getBitmapFromView(View view) {
        int totalHeight = view.getHeight();
        int totalWidth = view.getWidth();
        Bitmap returnedBitmap = Bitmap.createBitmap(totalWidth, totalHeight, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(returnedBitmap);
        Drawable bgDrawable = view.getBackground();
        if (bgDrawable != null)
            bgDrawable.draw(canvas);
        else
            canvas.drawColor(Color.WHITE);
        view.draw(canvas);
        return returnedBitmap;
    }

    private void removeListeners() {
        totalHoursPick.removeTextListener(mTextChangeWatcher);
        machineHoursPick.removeTextListener(mTextChangeWatcher);
        profitPick.removeTextListener(mTextChangeWatcher);
        quotedCycleTimePick.removeTextListener(mTextChangeWatcher);
        actualCycleTimePick.removeTextListener(mTextChangeWatcher);
        quotedRejectRatePick.removeTextListener(mTextChangeWatcher);
        actualRejectRatePick.removeTextListener(mTextChangeWatcher);
        quotedUpTimePick.removeTextListener(mTextChangeWatcher);
        actualUpTimePick.removeTextListener(mTextChangeWatcher);
    }

    private void addListeners() {
        resetBtn.setOnClickListener(resetClick);
        printBtn.setOnClickListener(printClick);
        totalHoursPick.addTextListener(mTextChangeWatcher);
        machineHoursPick.addTextListener(mTextChangeWatcher);
        profitPick.addTextListener(mTextChangeWatcher);
        quotedCycleTimePick.addTextListener(mTextChangeWatcher);
        actualCycleTimePick.addTextListener(mTextChangeWatcher);
        quotedRejectRatePick.addTextListener(mTextChangeWatcher);
        actualRejectRatePick.addTextListener(mTextChangeWatcher);
        quotedUpTimePick.addTextListener(mTextChangeWatcher);
        actualUpTimePick.addTextListener(mTextChangeWatcher);
    }

    private void fillEnterData(EnterData enterData) {
        totalHoursPick.setValue(enterData.getTotalHoursOfJob());
        machineHoursPick.setValue(enterData.getQuotedMachineHourRate());
        profitPick.setValue(enterData.getQuotedProfit());
        quotedCycleTimePick.setValue(enterData.getQuotedCycleTime());
        actualCycleTimePick.setValue(enterData.getActualCycleTime());
        quotedRejectRatePick.setValue(enterData.getQuotedRejectRate());
        actualRejectRatePick.setValue(enterData.getActualRejectRate());
        quotedUpTimePick.setValue(enterData.getQuotedUpTime());
        actualUpTimePick.setValue(enterData.getActualUpTime());
    }

    private void fillResultData(ResultData data) {
        effectCycleActualVal.setValue(data.getEffectsActualMachineHourRate());
        effectGainMachineHourRateVal.setValue(data.getEffectsGainLossMachineHours());
        effectGainQuotedCycleTimeVal.setValue(data.getEffectsGainLossFromQuitedCycle());
        effectGainQoutedRejectRateVal.setValue(data.getEffectsGainLossFromQuitedRejectRate());
        effectGainQuotedUpTimeVal.setValue(data.getEffectsGainLossFromQuitedUpTime());
        sumQuotedProfitVal.setValue(data.getQuotedProfit());
        sumCycleTimeVal.setValue(data.getCycleTimeGainLoss());
        sumRejectRateVal.setValue(data.getRefectRateGainLoss());
        sumUpTimeVal.setValue(data.getUpTimeGainLoss());
        sumTotalProfitVal.setValue(data.getTotalProfit());
        sumTotalProfitQuotedProfitVal.setValue(data.getTotalLossOfQuotedProfit());
    }

    private void initUI() {
        resetBtn = findViewById(R.id.reset_btn);
        printBtn = findViewById(R.id.print_btn);
        totalHoursPick = findViewById(R.id.total_hours_pick);
        machineHoursPick = findViewById(R.id.machine_hours_pick);
        profitPick = findViewById(R.id.profit_pick);
        quotedCycleTimePick = findViewById(R.id.quoted_cycle_time_pick);
        actualCycleTimePick = findViewById(R.id.actual_cycle_time_pick);
        quotedRejectRatePick = findViewById(R.id.reject_rate_quoted_pick);
        actualRejectRatePick = findViewById(R.id.reject_rate_actual_pick);
        quotedUpTimePick = findViewById(R.id.up_time_quoted_pick);
        actualUpTimePick = findViewById(R.id.up_time_actual_pick);

        effectCycleActualVal = findViewById(R.id.effect_of_cycle_actual_val);
        effectGainMachineHourRateVal = findViewById(R.id.effect_of_cycle_gain_in_machine_val);
        effectGainQuotedCycleTimeVal = findViewById(R.id.effect_of_cycle_gain_from_quoted_val);
        effectGainQoutedRejectRateVal = findViewById(R.id.effect_of_reject_quoted_val);
        effectGainQuotedUpTimeVal = findViewById(R.id.effect_of_up_time_quoted_val);
        sumQuotedProfitVal = findViewById(R.id.summary_quoted_profit_val);
        sumCycleTimeVal = findViewById(R.id.summary_cycle_time_val);
        sumRejectRateVal = findViewById(R.id.summary_reject_rate_val);
        sumUpTimeVal = findViewById(R.id.summary_up_time_val);
        sumTotalProfitVal = findViewById(R.id.summary_total_profit_val);
        sumTotalProfitQuotedProfitVal = findViewById(R.id.summary_total_gain_val);

        mRootWrapper = findViewById(R.id.content_wrapper);
    }
}
