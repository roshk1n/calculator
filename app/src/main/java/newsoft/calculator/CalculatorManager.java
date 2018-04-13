package newsoft.calculator;

public class CalculatorManager {

    private EnterData mEnterData;

    private ResultData mResultData;

    private CalculatorCallback mCalculatorCallback;

    private boolean isResetting = false;

    public CalculatorManager(CalculatorCallback calculatorCallback) {
        mEnterData = new EnterData();
        mResultData = new ResultData();
        mCalculatorCallback = calculatorCallback;
        resetDefaultValue();
    }

    public boolean isResetting() {
        return isResetting;
    }

    public void resetDefaultValue() {
        mEnterData.setTotalHoursOfJob(500.00f);
        mEnterData.setQuotedMachineHourRate(48.00f);
        mEnterData.setQuotedProfit(15.00f);

        mEnterData.setQuotedCycleTime(20.00f);
        mEnterData.setActualCycleTime(20.00f);

        mEnterData.setQuotedRejectRate(0.01f);
        mEnterData.setActualRejectRate(0.01f);

        mEnterData.setQuotedUpTime(90.00f);
        mEnterData.setActualUpTime(90.00f);
        isResetting = true;
        mCalculatorCallback.onResetData(mEnterData);
        calculate();
        isResetting = false;
    }

    public void changeEnterData(float quotedCycleTime, float actualCycleTime, float quotedUpTime,
                                float actualUpTime, float quotedRejectRate, float actualRejectRate,
                                float totalHoursOfJob, float quotedMachineHourRate, float quotedProfit) {

        mEnterData.setTotalHoursOfJob(totalHoursOfJob);
        mEnterData.setQuotedMachineHourRate(quotedMachineHourRate);
        mEnterData.setQuotedProfit(quotedProfit);

        mEnterData.setQuotedCycleTime(quotedCycleTime);
        mEnterData.setActualCycleTime(actualCycleTime);

        mEnterData.setQuotedRejectRate(quotedRejectRate);
        mEnterData.setActualRejectRate(actualRejectRate);

        mEnterData.setQuotedUpTime(quotedUpTime);
        mEnterData.setActualUpTime(actualUpTime);

        calculate();
    }

    private void calculate() {

        mResultData.setEffectsActualMachineHourRate(calculateEffectActualMachineHourRate());

        mResultData.setEffectsGainLossMachineHours(calculateEffectsGainLossMachineHours());

        mResultData.setEffectsGainLossFromQuitedCycle(calculateEffectsGainLossQuoitedCycle());

        mResultData.setEffectsGainLossFromQuitedRejectRate(calculateEffectsGainLossQuoitedRejectRate());

        mResultData.setEffectsGainLossFromQuitedUpTime(calculateEffectsGainLossQuoitedUpTime());

        mResultData.setQuotedProfit(calculateQuoitedProfit());

        mResultData.setCycleTimeGainLoss(mResultData.getEffectsGainLossFromQuitedCycle());

        mResultData.setRejectRateGainLoss(mResultData.getEffectsGainLossFromQuitedRejectRate());

        mResultData.setUpTimeGainLoss(mResultData.getEffectsGainLossFromQuitedUpTime());

        mResultData.setTotalProfit(calculateTotalProfit());

        mResultData.setTotalLossOfQuotedProfit(calculateTotalProfitOfQuotedProfit());

        mCalculatorCallback.onResultChanged(mResultData);
    }

    private float calculateEffectActualMachineHourRate() {
        return mEnterData.getQuotedCycleTime()
                * mEnterData.getQuotedMachineHourRate() / mEnterData.getActualCycleTime();
    }

    private float calculateEffectsGainLossMachineHours() {
        return mResultData.getEffectsActualMachineHourRate()
                - mEnterData.getQuotedMachineHourRate();
    }

    private float calculateEffectsGainLossQuoitedCycle() {
        return mEnterData.getTotalHoursOfJob()
                * mResultData.getEffectsGainLossMachineHours();
    }

    private float calculateEffectsGainLossQuoitedRejectRate() {
        return ((mEnterData.getQuotedRejectRate() - mEnterData.getActualRejectRate()) / 100)
                * mEnterData.getTotalHoursOfJob() * mEnterData.getQuotedMachineHourRate();
    }

    private float calculateEffectsGainLossQuoitedUpTime() {
        return ((mEnterData.getActualUpTime() - mEnterData.getQuotedUpTime()) / 100)
                * mEnterData.getTotalHoursOfJob() * mEnterData.getQuotedMachineHourRate();
    }

    private float calculateQuoitedProfit() {
        return mEnterData.getTotalHoursOfJob() * mEnterData.getQuotedMachineHourRate() * mEnterData.getQuotedProfit() / 100;
    }

    private float calculateTotalProfit() {
        return mResultData.getQuotedProfit() + mResultData.getCycleTimeGainLoss() + mResultData.getRefectRateGainLoss()
                + mResultData.getUpTimeGainLoss();
    }

    private float calculateTotalProfitOfQuotedProfit() {
        return mResultData.getTotalProfit() - mResultData.getQuotedProfit();
    }
}
