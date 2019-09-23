package com.garsol.currency;

public class Candle {

    private String date;

    private double open;

    private double high;

    private double low;

    private double close;

    public Candle(final String date, final double open, final double high, final double low, final double close) {
        this.date = date;
        this.open = open;
        this.high = high;
        this.low = low;
        this.close = close;
    }

    public String getDate() {
        return date;
    }

    public void setDate(final String date) {
        this.date = date;
    }

    public double getOpen() {
        return open;
    }

    public double getHigh() {
        return high;
    }

    public double getLow() {
        return low;
    }

    public double getClose() {
        return close;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Candle{");
        sb.append("date='").append(date).append('\'');
        sb.append(", open=").append(open);
        sb.append(", high=").append(high);
        sb.append(", low=").append(low);
        sb.append(", close=").append(close);
        sb.append('}');
        return sb.toString();
    }
}
