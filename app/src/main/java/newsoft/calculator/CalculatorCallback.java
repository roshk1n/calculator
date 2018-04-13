package newsoft.calculator;

public interface CalculatorCallback {

    void onResultChanged(ResultData data);

    void onResetData(EnterData enterData);
}
