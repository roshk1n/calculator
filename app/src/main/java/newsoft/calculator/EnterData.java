package newsoft.calculator;

public class EnterData {

    private float quotedCycleTime;

    private float actualCycleTime;

    private float quotedUpTime;

    private float actualUpTime;

    private float quotedRejectRate;

    private float actualRejectRate;

    private float totalHoursOfJob;

    private float quotedMachineHourRate;

    private float quotedProfit;

    public EnterData() {
        totalHoursOfJob = 500.0f;
        quotedMachineHourRate = 48.0f;
    }

    public float getQuotedCycleTime() {
        return quotedCycleTime;
    }

    public void setQuotedCycleTime(float quotedCycleTime) {
        this.quotedCycleTime = quotedCycleTime;
    }

    public float getActualCycleTime() {
        return actualCycleTime;
    }

    public void setActualCycleTime(float actualCycleTime) {
        this.actualCycleTime = actualCycleTime;
    }

    public float getQuotedUpTime() {
        return quotedUpTime;
    }

    public void setQuotedUpTime(float quotedUpTime) {
        this.quotedUpTime = quotedUpTime;
    }

    public float getActualUpTime() {
        return actualUpTime;
    }

    public void setActualUpTime(float actualUpTime) {
        this.actualUpTime = actualUpTime;
    }

    public float getQuotedRejectRate() {
        return quotedRejectRate;
    }

    public void setQuotedRejectRate(float quotedRejectRate) {
        this.quotedRejectRate = quotedRejectRate;
    }

    public float getActualRejectRate() {
        return actualRejectRate;
    }

    public void setActualRejectRate(float actualRejectRate) {
        this.actualRejectRate = actualRejectRate;
    }

    public float getTotalHoursOfJob() {
        return totalHoursOfJob;
    }

    public void setTotalHoursOfJob(float totalHoursOfJob) {
        this.totalHoursOfJob = totalHoursOfJob;
    }

    public float getQuotedMachineHourRate() {
        return quotedMachineHourRate;
    }

    public void setQuotedMachineHourRate(float quotedMachineHourRate) {
        this.quotedMachineHourRate = quotedMachineHourRate;
    }

    public float getQuotedProfit() {
        return quotedProfit;
    }

    public void setQuotedProfit(float quotedProfit) {
        this.quotedProfit = quotedProfit;
    }
}
